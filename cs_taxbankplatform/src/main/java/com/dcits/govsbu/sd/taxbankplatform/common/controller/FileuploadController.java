package com.dcits.govsbu.sd.taxbankplatform.common.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.common.UploadFileUtil;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.BgdjswbService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;



/**
 * 上传下载工具类
 * @author liwangxiong
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/upload/")
public class FileuploadController extends BaseController{
	@Autowired
	private BgdjswbService bgdjswbService;
	
	//图片上传windows和Linux文件//和\配置
	//Linux 则为File_BZD="/"  windows则为File_BZD="\\" 
	 static boolean osFlag = false;
	 static{
	 	if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("win")){  
	 		osFlag = true;	
	 	} 
	 
	 }
	//private static final String File_BZD = "/";

	/**
	 * 文件上传
	 */
	@RequestMapping("uploadFile.html")
	@ResponseBody 
	public void uploadFile(HttpServletRequest request,HttpServletResponse response,String fileCode,String fileLx) throws ServletException, IOException,Exception{
		JSONObject json = new JSONObject();
		//设置响应给前台内容的数据格式  
		response.setContentType("text/plain; charset=UTF-8");  
		//设置响应给前台内容的PrintWriter对象  
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		
		UserEntity userEntity = (UserEntity)request.getSession().getAttribute("userSession");
		
		String path = "";
		//文件保存到数据库的相对路径
		if(fileCode == null){
			path = UploadFileUtil.getstaticpath();
		}else if(fileCode.equals("fileUpload")&&fileLx.equals("B")){	
			//变更登记税务表
			path = UploadFileUtil.getFilePath() + "B/" + userEntity.getRegionid() + "/";
		}else if(fileCode.equals("fileUpload")&&fileLx.equals("M")){	
			//变更登记税务表模板
			path = UploadFileUtil.getFilePath() + "M/" + userEntity.getRegionid() + "/";
		}
		//文件保存的本地全路径
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		if(osFlag == false){
			rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
			rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
		}else {
			rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
			rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
		}
		
		
		
		String	folderPath = rootpath+ path;
		//String folderPath = path+UploadFileUtil.imageFolder()+"/";
		//String path          //保存到数据库的路径
		/*if (imagecode.equals("static")) {
			String realpath = request.getSession().getServletContext().getRealPath("/");
			path = Showproperties.Getstatic_path()+UploadFileUtil.imageFolder()+"/";

		}else{
			path = Showproperties.Getlocal_path()+Showproperties.Getstatic_path()+UploadFileUtil.imageFolder()+"/";
		}*/

		//检查form中是否有enctype="multipart/form-data"
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			File headpath = new File(folderPath);
			//判断文件路径是否存在不存在则创建
			if (!headpath.exists()) {
				headpath.mkdirs();	
			}
			//上传文件的原名(即上传前的文件名字)  
			String OriginalFilename = "";
			//提示信息
			String msg = "";
			String status = "";
			//将request变成多部分request
			//获取multiRequest 中所有的文件名
			for (Iterator<?> it = multiRequest.getFileNames(); it.hasNext();) {  
				String key = (String) it.next();  
				MultipartFile mulfile = multiRequest.getFile(key);  
				OriginalFilename = mulfile.getOriginalFilename();
				String fileName = UploadFileUtil.newFileName()+UploadFileUtil.ExcString(OriginalFilename);   //文件的新名称
				long Size = mulfile.getSize();		//文件的大小
				String type = UploadFileUtil.ExcString(OriginalFilename);   //文件的后缀名
				String savePath = folderPath+fileName;     //文件的全路径
				//String code = mulfile.getContentType();		//文件的类型
				//判断文件是否符合要求
				if (Size>10485760) {
					msg = "上传文件过大，请重新上传！";
					status = "1";
				}else{
					if(fileCode == null){
						//图片上传
						if(type.equals(".png")||type.equals(".gif")||type.equals(".jpg")||type.equals(".jpeg")) {
							File file = new File(savePath);  
							mulfile.transferTo(file);
							msg = "上传成功！";
							status = "0";
						}else{
							msg = "上传文件类型错误，请重新上传！";
							status = "2";
						}
					}else if(fileCode.equals("fileUpload")){
						//word文件上传
						if (type.equals(".doc")||type.equals(".docx")||type.equals(".rtf")) {
							File file = new File(savePath);  
							mulfile.transferTo(file);
							msg = "上传成功！";
							status = "0";
						}else{
							msg = "上传文件类型错误，请重新上传！";
							status = "2";
						}
					}
				}
				request.getSession().setAttribute("filePath", path+fileName);
				json.put("status", status);
				json.put("msg", msg);
				json.put("fileName", fileName);
				/*json.put("Size", Size);
				json.put("type", type);
				json.put("code", code);*/
				json.put("savePath", path+fileName);
			}  
		}
		response.getWriter().print(json);
	}

	/**
	 * 展示图片
	 */
	@RequestMapping("readImageFile.html")
	@ResponseBody 
	public void readImageFile(HttpServletRequest request,HttpServletResponse response){
		try {
			//获取项目路径
			//文件保存的本地全路径
			String rootpath = request.getSession().getServletContext().getRealPath("/");
			if(osFlag == false){
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
			}else {
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
			}
			String ImagePath = rootpath+request.getParameter("imagePath");
			if (ImagePath!=null && !ImagePath.equals("")) {
				File file = new File(ImagePath);
				if (file.exists()) {
					DataOutputStream temps = new DataOutputStream(response.getOutputStream());
					DataInputStream in = new DataInputStream(new FileInputStream(ImagePath));
					byte[] b = new byte[2048];  
					while ((in.read(b)) != -1) {  
						temps.write(b);  
						temps.flush();  
					}  
					in.close();  
					temps.close();  
				}   
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 查找文件下载的路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="getOnload.html")
	public Object getOnload(HttpServletRequest request,String code,String id) throws IOException{
		Map<String,Object> map = new HashMap<String, Object>();
		String rootpath = (String) request.getSession().getAttribute("filePath");		
		if(rootpath == null){
			//现在需要去后台查询地址
			rootpath = bgdjswbService.searchFilePath(id);
			map.put("success", Boolean.FALSE);
			map.put("success", "无查看文件");
		}else{
			
			rootpath = "/" + rootpath;
			map.put("success", Boolean.TRUE);
			map.put("data", rootpath);
		}
		return map;
	}

	/**
	 * 删除图片
	 */
	@RequestMapping("deleteImage.html")
	@ResponseBody 
	public void deleteImage(HttpServletRequest request,HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			String msg ="";
			response.setContentType("text/plain; charset=UTF-8");  
			//获取项目路径
			//String path = request.getSession().getServletContext().getRealPath("/");
			String rootpath = request.getSession().getServletContext().getRealPath("/");
			if(osFlag == false){
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
			}else {
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
				rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
			}
			
			String ImagePath = rootpath + request.getParameter("imagePath");
			if (ImagePath!=null && !ImagePath.equals("")) {
				File file = new File(ImagePath);
				if (file.exists()) {
					file.delete();
					msg = "图片删除成功！";
				}
			}
			json.put("msg", msg);
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
