/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：WriteContent.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-7-26下午上午11:19:432:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.gxgsService.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @versions:1.0 
 * @filename：WriteContent.java
 * @Company:dfwyBank
 * @Created: 2017-7-26下午上午11:19:432:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName WriteContent
 */
public class WriteContent {
	/**
	 * 写入内容到文件 
	 * @param c				文件内容
	 * @param dirname		路径地址
	 * @param filename		文件名
	 * @param isAppend		是否刷新
	 * @return
	 */
	public static synchronized boolean writeContent(String c, String dirname,String filename,boolean isAppend) { 
		File f=new File(dirname); 
		if (!f.exists()) 
		{ 
			f.mkdirs(); 
		} 
		try { 
			FileOutputStream fos = new FileOutputStream( dirname+File.separator+filename,isAppend); 
			OutputStreamWriter writer = new OutputStreamWriter(fos); 
			writer.write(c); 
			writer.close(); 
			fos.close(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
			return false; 
		} 
		return true; 
	} 


	/** 
	 * 从文件读取内容 
	 * 
	 * @param filename 
	 * @return 
	 */
	public static String readText(String filename) { 
		String content = ""; 
		try { 
			File file = new File(filename); 
			if (file.exists()) { 
				FileReader fr = new FileReader(file); 
				BufferedReader br = new BufferedReader(fr); 
				String str = ""; 
				String newline = ""; 
				while ((str = br.readLine()) != null) { 
					content += newline + str; 
					newline = "\n"; 
				} 
				br.close(); 
				fr.close(); 
			} 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		return content; 
	} 
}
