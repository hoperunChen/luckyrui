package com.luckyrui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 文件工具类
 * 
 * @author chenrui
 * @date 2016年9月2日下午2:24:33
 */
public class FileUtil {

	/**
	 * 获取系统所在目录
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年9月8日 下午7:22:40
	 * @version 2016_Anniversary
	 */
	public String getProjectPath() {
		System.out.println(this.getClass());
		// return this.getClass().getResource("/").getPath();\
		// String _path =
		// this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		// return _path.substring(0, _path.lastIndexOf(File.separator)+1);
		File directory = new File("");// 参数为空
		try {
			return directory.getCanonicalPath() + File.separator;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 读取工程中的文件内容
	 * 
	 * @param fileName
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午3:16:32
	 * @version 2016_Anniversary
	 */
	public String readFileInProject(String fileName) {
		String laststr = "";
		BufferedReader reader = null;
		try {
			InputStream is = this.getClass().getResourceAsStream("/" + fileName);
			InputStreamReader isr = new InputStreamReader(is);
			reader = new BufferedReader(isr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	/**
	 * 通过绝对路径读取文件
	 * 
	 * @param path
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午3:22:58
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException
	 */
	public String readFile(String path) throws FileNotFoundException {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	/**
	 * 获取目录下所有文件
	 * 
	 * @param path
	 * @return
	 * @author chenrui
	 * @date 2016年10月17日 下午3:01:34
	 * @version 201610
	 */
	public List<File> getFiles(String path) {
		List<File> fs = new ArrayList<File>();
		File root = new File(path);
		if (!root.isDirectory()) {
			throw new RuntimeException(path + ",is not a directory");
		}
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				fs.addAll(getFiles(file.getAbsolutePath()));
			} else {
				fs.add(file);
			}
		}
		return fs;
	}

	/**
	 * 加载工程中的jsonObject
	 * 
	 * @param fileName
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午2:24:37
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException
	 */
	public JSONObject getJsonFileInProject(String fileName) throws FileNotFoundException {
		return JSONObject.parseObject(readFileInProject(fileName));
	}

	/**
	 * 加载工程中的jsonArray
	 * 
	 * @param fileName
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午3:24:19
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException
	 */
	public JSONArray getJsonArrFileInProject(String fileName) throws FileNotFoundException {
		return (JSONArray) JSONArray.parse(readFileInProject(fileName));
	}

	/**
	 * 根据绝对路径加载jsonObject
	 * 
	 * @param Path
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午2:56:37
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException
	 */
	public JSONObject getJsonFile(String path) throws FileNotFoundException {
		return JSONObject.parseObject(readFile(path));
	}

	/**
	 * 根据绝对路径加载jsonObject
	 * 
	 * @param Path
	 * @return
	 * @author chenrui
	 * @date 2016年9月2日 下午2:56:37
	 * @version 2016_Anniversary
	 * @throws FileNotFoundException
	 */
	public JSONArray getJsonArrFile(String path) throws FileNotFoundException {
		return (JSONArray) JSONArray.parse(readFile(path));
	}

}
