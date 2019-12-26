package com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.impl;

import org.springframework.stereotype.Component;



@Component("StaticDateUtil")
public class StaticDateUtil {
	
	/**
	 * 需要查询的数据库表名
	 */
	private static String data_table;
	/***获取查询语句的表名***/
	private static String select_table;
	/***获取需要更新的表名***/
	private static String update_table;
	/***未成功插入的文件备份***/
	private static String TAX_Doing_Dir;
	/***成功插入表之后放到完成文件夹备份记录***/
	private static String TAX_Finish_Dir;
	
	public static String getUpdate_table() {
		return update_table;
	}
	public static void setUpdate_table(String update_table) {
		StaticDateUtil.update_table = update_table;
	}
	public static String getSelect_table() {
		return select_table;
	}
	public static void setSelect_table(String select_table) {
		StaticDateUtil.select_table = select_table;
	}
	public static String getData_table() {
		return data_table;
	}
	public static void setData_table(String data_table) {
		StaticDateUtil.data_table = data_table;
	}
	public static String getTAX_Doing_Dir() {
		return TAX_Doing_Dir;
	}
	public static void setTAX_Doing_Dir(String tAX_Doing_Dir) {
		TAX_Doing_Dir = tAX_Doing_Dir;
	}
	public static String getTAX_Finish_Dir() {
		return TAX_Finish_Dir;
	}
	public static void setTAX_Finish_Dir(String tAX_Finish_Dir) {
		TAX_Finish_Dir = tAX_Finish_Dir;
	}

	
	
	

}
