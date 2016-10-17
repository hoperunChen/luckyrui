package com.luckyrui.code_review;

/**
 * Hello world!
 *
 */
public class App {

	private static final String DEFAULT_PATH = "/Users/chenrui/workspace/dmdWorkspace/duomeidai-service";

	public static void main(String[] args) {
		String rootPath = "";
		if (null == args || args.length <= 0) {
			System.out.println("开发环境:使用默认路径");
			rootPath = DEFAULT_PATH;
		}
		
		CodeReviewHeandle crh = new CodeReviewHeandle(rootPath);
		try {
			crh.reviewCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
