package com.dcits.govsbu.sd.taxbankplatform.common;

import java.io.InputStream;
import java.util.Properties;

public class Showproperties {
	static private String local_path = null;
	
	static private String static_path = null;
	
	static private String filePath;
	
	static private String select_table = null;
	
	static private String update_table = null;
	
	static private String TAX_Doing_Dir= null;
	
	static private String TAX_Finish_Dir = null;
	
	static private String BD_Doing_Dir = null;
	
	
	
	static{
		loads();
	}
	synchronized static public void loads(){
		if(local_path == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				local_path = dbProps.getProperty("local_path");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		
		if(static_path == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				static_path = dbProps.getProperty("static_path");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		//**********************2018-03-13 zhongyj 增加目的是在配置文件中读取需要同步到税局内网的数据库表***********start**********
		if(select_table == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				select_table = dbProps.getProperty("select_table");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		
		if(update_table == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				update_table = dbProps.getProperty("update_table");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		
		if(TAX_Doing_Dir == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				TAX_Doing_Dir = dbProps.getProperty("TAX_Doing_Dir");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		
		if(TAX_Finish_Dir == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				TAX_Finish_Dir = dbProps.getProperty("TAX_Finish_Dir");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		
		if(BD_Doing_Dir == null)
		{
			InputStream is = Showproperties.class.getResourceAsStream("/config/webside.properties");
			Properties dbProps = new Properties();
			try {
				dbProps.load(is);
				BD_Doing_Dir = dbProps.getProperty("BD_Doing_Dir");
			}
			catch (Exception e) {
				System.err.println("不能读取属性文件. " +
						"请确保webside.properties在CLASSPATH指定的路径中");
			}
		}
		//**********************2018-03-13 zhongyj 增加目的是在配置文件中读取需要同步到税局内网的数据库表**************end*******
	}
	public static String Getlocal_path() {
		if(local_path==null)
			loads();
		return local_path;
	}
	public static String Getstatic_path() {
		if(static_path==null)
			loads();
		return static_path;
	}
	public static String getFilePath() {
		return filePath;
	}
	public static void setFilePath(String filePath) {
		Showproperties.filePath = filePath;
	}
	
	public static String GetSelect_table() {
		if(select_table==null)
			loads();
		return select_table;
	}
	public static String GetUpdate_table() {
		if(update_table==null)
			loads();
		return update_table;
	}
	public static String GetDoing_Dir() {
		if(TAX_Doing_Dir==null)
			loads();
		return TAX_Doing_Dir;
	}
	public static String GetFinish_Dir() {
		if(TAX_Finish_Dir==null)
			loads();
		return TAX_Finish_Dir;
	}
	public static String GetBd_Doing_Dir() {
		if(BD_Doing_Dir==null)
			loads();
		return BD_Doing_Dir;
	}
}
