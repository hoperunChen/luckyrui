package com.luckyrui.code_review;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.luckyrui.utils.FileUtil;

public class CodeReviewHeandle {
	private FileUtil fu = new FileUtil();

	private String rootPath = null;

	public CodeReviewHeandle(String rootPath) {
		this.rootPath = rootPath;
	}

	private Iterator<File> getFiles() throws Exception {
		List<File> _f = fu.getFiles(rootPath);
		if (null != _f && _f.size() > 0) {
			return _f.iterator();
		} else {
			throw new Exception(rootPath + "目录中没有文件");
		}
	}

	public void reviewCode() throws Exception {
		final Iterator<File> files = getFiles();
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (files.hasNext()) {
						File f = null;
						synchronized (files) {
							f = files.next();
						}
						List<String> result = reviewCodeFile(f);
						if (result != null)
							outPutResult(f, result);
					}
				}
			}).start();
		}

	}

	/**
	 * 检查代码
	 * 
	 * @param file
	 * @author chenrui
	 * @date 2016年10月17日 下午4:03:03
	 * @version 201610
	 */
	private List<String> reviewCodeFile(File file) {
		List<String> rtn = null;
		String fileName = file.getName();
		if (fileName.lastIndexOf(".java") < 0) {
			return null;
		}
		//TODO 抽出去
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			rtn = new ArrayList<String>(); // 错误内容
			Stack<String> codeStack = new Stack<String>(); // 代码栈
			String tempString = null; // 每行的内容
			int line = 1; // 行号
			// 一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				tempString = tempString.trim();
				if ((tempString.contains("MySQL.getConnection()")
					|| tempString.contains("MySQL.getConnectionWithoutTran()")) 
						&& !tempString.contains("//")
						&& tempString.indexOf("//") < tempString.indexOf("MySQL.getConnection")) {
					// 有获取连接的代码,压栈
					codeStack.push("line " + line + ": " + tempString);
				}

				if (tempString.contains("finally")) {
					// 有finally,判断是否在获取连接之后,如果在入栈,不在跳过

					if (!codeStack.isEmpty() && (codeStack.peek().contains("MySQL.getConnection()")
							|| codeStack.peek().contains("MySQL.getConnectionWithoutTran()"))) {
						codeStack.push("line " + line + ": " + tempString);
					}
				}

				if (tempString.contains(".close(") && !tempString.contains("//")
						&& tempString.indexOf("//") < tempString.indexOf(".close(")) {
					// 代码中包含".close(" ,取栈顶元素判断是不是finally
					if (!codeStack.isEmpty()) {
						if (codeStack.peek().contains("finally")) {
							// 包含finally,将finally出栈
							codeStack.pop();

							// 判断finally出栈后栈顶是不是获取连接
							if (!codeStack.isEmpty() && codeStack.peek().contains("MySQL.getConnection()")
									|| codeStack.peek().contains("MySQL.getConnectionWithoutTran()")) {
								// 如果finally出栈后的栈顶是获取连接那么代码是正确的,出栈获取连接代码
								codeStack.pop();
							} else {
								// 如果finally出栈后的栈顶是空或不是获取连接,代表该close有异常,将close这一行放入异常list里
								rtn.add("连接未创建错误:[line " + line + ": " + tempString + "]");
							}
						} else {
							// rtn.add("close不在finally:[line " + line + ": " +
							// tempString + "]");
						}
					}
				}
				line++;
			}
			reader.close();
			// 文件读完后如果栈内还有内容,那么栈内留下的内容都是没有关闭的连接
			if (!codeStack.empty()) {
				while (!codeStack.isEmpty()) {
					String temp = codeStack.pop();
					if (temp.contains("MySQL.getConnection"))
						rtn.add("连接未关闭:" + temp.trim());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return rtn;
	}

	/**
	 * 输出结果
	 * 
	 * @param result
	 * @author chenrui
	 * @date 2016年10月17日 下午4:19:50
	 * @version 201610
	 * @param fileName
	 */
	private synchronized void outPutResult(File file, List<String> result) {
		if(result != null && !result.isEmpty())
		System.out.println(Thread.currentThread() + "result:{" + file.getPath() + "}" + result);
	}

}
