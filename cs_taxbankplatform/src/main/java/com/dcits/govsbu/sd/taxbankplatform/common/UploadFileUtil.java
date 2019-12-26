package com.dcits.govsbu.sd.taxbankplatform.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * file工具类
 * @author admin
 *
 */
public class UploadFileUtil {

	/**
	 * 获取当前年月日
	 * @return
	 */
	public static String imageFolder(){
		String folder = new SimpleDateFormat("yyyyMMdd").format(new Date());
		return folder;
	}
	

	//文件名称处理  
	public static String newFileName() {  
	  //生成uuid名称 
	  String uuid = UUID.randomUUID()+"";
	  return uuid.replaceAll("-", "");  
	 }
	
	
	/**
	 * 处理文件名后缀名
	 * @param fileName
	 * @return
	 */
	
	public static String ExcString(String fileName){
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());		
		return suffix;
	}
	
	/**
	 * 获取项目的相对路径
	 * @param args
	 */
	public static String getstaticpath(){
		String path = Showproperties.Getstatic_path()+UploadFileUtil.imageFolder()+"/";
		return path;
	}
	
	/**
	 * 获取文件（变更登记税务表）的相对路径
	 * @param args
	 */
	public static String getFilePath(){
		String path = Showproperties.getFilePath();
		return path;
	}
	
	public static void main(String[] args) {
		System.out.println(UploadFileUtil.newFileName());
		System.out.println(UploadFileUtil.ExcString("asdfasdf.png"));
	}
	
}
