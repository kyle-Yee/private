package com.dcits.govsbu.sd.taxbankplatform.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
 /***
  * 获取ftp服务器文件下载
  * liuc
  * */
public class FTPUtil  {
 
    FtpClient ftpClient;
 
    /***
     * 连接ftp
     * @param url  必须是  192.168.8.1  否则提示异常
     * @param port
     * @param username
     * @param password
     * @return
     */
    public static FtpClient connectFTP(String url, int port, String username, String password) {  
        //创建ftp  
        FtpClient ftp = null;  
        try {  
            //创建地址  
            SocketAddress addr = new InetSocketAddress(url, port);  
            //连接  
            ftp = FtpClient.create();  
            ftp.connect(addr);  
            //登陆  
            ftp.login(username, password.toCharArray());  
            ftp.setBinaryType();  
        } catch (FtpProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return ftp;  
    }  
 
    public static List<String> download(String ftpFile, FtpClient ftp) {  
        List<String> list = new ArrayList<String>();
        String str = "";
        InputStream is = null; 
        BufferedReader br = null;
        try {
            // 获取ftp上的文件  
            is = ftp.getFileStream(ftpFile);
            //转为字节流
            br = new BufferedReader(new InputStreamReader(is));
            while((str=br.readLine())!=null){
                list.add(str);
            }
            br.close();
        }catch (FtpProtocolException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }  
    
    
    
    
    /**
    * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
    * @param fileName 文件的名
    */
    @SuppressWarnings("resource")
	public static List<String> readFileByBytes(String fileName) {
    	 InputStreamReader read = null;
    	 List<String> list = new ArrayList<String>();
	    try {
	    	File file = new File(fileName);
	        if (file.isFile() && file.exists()) { //判断文件是否存在
	            read = new InputStreamReader(
	                    new FileInputStream(file), "UTF-8");//考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while ((lineTxt = bufferedReader.readLine()) != null) {
	            	list.add(lineTxt);         
	             }       
	        }
	    } catch (Exception e1) {
	    e1.printStackTrace();
	    } finally {
	    if (read != null){
	    try {
	    	read.close();
	    } catch (IOException e1) {
	    }
	    }
	   }
	    
	    return list;
    }
    
    /**
     * 判断文件是否存在
     * */
    public static boolean isFilePath(String fileName) {
	    try {
	    	File file = new File(fileName);
	        if (file.isFile() && file.exists()) { //判断文件是否存在
	        	 return true;
	        }
	    } catch (Exception e1) {
	    e1.printStackTrace();
	    }  
	    return false;
   }
    
    
    
    
    public static void main(String[] args) {
       // FtpClient ftp = connectFTP("192.168.8.55",21,"test","test");
        //List<String> list = download("1.txt",ftp);
    	FTPUtil.readFileByBytes("C:\\Users\\liuc\\Desktop\\智能决策平台\\20170630\\ZNJC_LMT_APPLY_20170630.dat");
    } 
 }      