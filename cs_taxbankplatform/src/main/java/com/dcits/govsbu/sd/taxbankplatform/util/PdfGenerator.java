package com.dcits.govsbu.sd.taxbankplatform.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate.AuthorizationrecordPDFTeplate;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate.HtmlToPdfTeplate;
import com.dcits.govsbu.sd.taxbankplatform.pdf.teplate.CqPdfTemplate;
import com.dcits.govsbu.sd.taxbankplatform.pdf.teplate.GlPdfTemplate;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.mapper.PdfMapper;
import com.digitalchina.framework.itextpdf.ConvertXmlTemplate2Pdf;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/** 
 * <p>Description: </p>
 * <p>版本:1.0 
 * <p>文件名：com.dcits.platframe.common.util.PdfGenerator.java 
 * <p>Copyright: Copyright (c) 2002-2015 Digitalchina CO.,LTD.  All rights reserved.</p>
 * <p>Company:神州数码信息系统有限公司</p>
 * <p>作者: dengwei 
 * <p>创建时间: 2015-12-19下午2:57:43 
 * <p>部门:政府SBU  
 */
@Service("pdfGenerator")
public class PdfGenerator {
	
	@Autowired
	private PdfMapper pdfMapper;
	
	public PdfMapper getPdfMapper() {
		return pdfMapper;
	}
	public void setPdfMapper(PdfMapper pdfMapper) {
		this.pdfMapper = pdfMapper;
	}
    
    /**
     * 模板的基路径
     */
    private String templateBasePath;

    /**
     * pdf文件存储的位置
     */
	@Value("${pdf_path}")
    private String pdfFileBasePath;
	
	static boolean osFlag = false;
    static{
    	if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("win")){  
    		osFlag = true;
    	} 
    }

    /**
     * 时间：2015-12-19 下午3:05:07
     * 作者：dengwei
     * @return the templateBasePath
     */
    public String getTemplateBasePath() {
        return templateBasePath;
    }

    /**
     * 时间：2015-12-19 下午3:05:07
     * 作者：dengwei
     * @param templateBasePath the templateBasePath to set
     */
//    public void setTemplateBasePath(String templateBasePath) {
//        this.templateBasePath = templateBasePath;
//    }

    /**
     * 时间：2015-12-19 下午3:37:04
     * 作者：dengwei
     * @return the pdfFileBasePath
     */
    public String getPdfFileBasePath() {
        return pdfFileBasePath;
    }

    /**
     * 时间：2015-12-19 下午3:37:04
     * 作者：dengwei
     * @param pdfFileBasePath the pdfFileBasePath to set
     */
    public void setPdfFileBasePath(String pdfFileBasePath) {
        this.pdfFileBasePath = pdfFileBasePath;
    }

    /**
     * 检查目录是否存在
     * 时间：2015-12-19 下午3:46:57
     * 作者：dengwei
     * @param dirPath
     */
    private void checkCreateDir(String dirPath) {
        // 检查路径是否存在，不存在则创建
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
    }

    /**
     * 根据模板创建pdf文件
     * 时间：2015-12-19 下午3:14:26
     * 作者：dengwei
     * @param templateIds 多个模板则用逗号分隔
     * @param dataJson
     * @param outputFile
     * @return 返回文件名
     * @throws Exception
     */
    public String json2pdf(String templateIds, String dataJson,HttpServletRequest request) throws Exception {

        // 转换字符串为xml
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setRootName("_PDFXML_");
        xmlSerializer.setElementName("ITEM");//数组的每一个节点的名称
        xmlSerializer.setTypeHintsEnabled(false);
        JSON jsonObject = JSONSerializer.toJSON(dataJson);
        String dataXml = xmlSerializer.write(jsonObject, "GBK");
        // 替换掉根节点
        dataXml = dataXml.replaceAll("<_PDFXML_>", "");
        dataXml = dataXml.replaceAll("</_PDFXML_>", "");
        //logger.debug("转换后的xml报文:"+dataXml);
        String outputFile = xml2json(templateIds, dataXml,request);
        return outputFile;
    }

    /**
     * 时间：2015-12-19 下午3:53:24
     * 作者：dengwei
     * @param templateIds
     * @param dataXml
     * @return
     * @throws IOException
     * @throws Exception
     */
    
    public String xml2json(String templateIds, String dataXml,HttpServletRequest request) throws IOException,
            Exception {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ApplicationContext ac = new ClassPathXmlApplicationContext();
        String dateTag = dateFormat.format(date);
        
        /*if(osFlag == true){
        	pdfFileBasePath ="attached/pdf/";
		} else {
			pdfFileBasePath ="attached\\pdf\\";
		}*/
        
        String path = pdfFileBasePath + dateTag;
        //文件保存到数据库的相对路径
      	//String path = getPdfFileBasePath() ;
      	//文件保存的本地全路径
      	String rootpath = request.getSession().getServletContext().getRealPath("/");
      
		if(osFlag == true){
			 rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
      	     rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
		} else {
		 	rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
      		rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
		}
       
      	String	dirPath = rootpath+ path;
        // 检查目录是否存在，不存在则创建
        checkCreateDir(dirPath);
        String relativePath = String.valueOf(date.getTime()) + "-"
                        + String.valueOf(new Random().nextInt(100)) + ".pdf";
        String outputFile = dirPath + "/" + relativePath;
        // 准备模板
        ArrayList<InputStream> streamList = new ArrayList<InputStream>();
        String[] templateIdArr = templateIds.split(",");
        for (int i = 0; i < templateIdArr.length; i++) {
            String templateFileId = templateIdArr[i];
            // 获取资源
            Resource resource = ac.getResource(
                    getTemplateBasePath() + templateFileId + ".xml");
            streamList.add(resource.getInputStream());
        }
        // 生成pdf
        ConvertXmlTemplate2Pdf cxt = new ConvertXmlTemplate2Pdf();
        cxt.convertXml2Pdf(streamList, dataXml, outputFile);
        return dateTag+"/"+relativePath;
    }
    
    /**
     * 
     * 时间：2015-12-19 下午4:47:54
     * 作者：dengwei
     * @param relativePath
     * @return
     */
    public String getFullPath(String relativePath){
        return getPdfFileBasePath()+"/"+relativePath;
    }
    /**
     * 税务授权书PDF下载
     * @Function:PdfGenerator1 
     * @Author Fangren
     * @date 2017-4-20 下午3:39:13
     * @param request
     * @param id
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String pdfGenerator1(HttpServletRequest request,String id,AuthorizationrecordEntity authorizationrecordEntity)throws IOException,
    Exception{
    	Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //ApplicationContext ac = new ClassPathXmlApplicationContext();
        String dateTag = dateFormat.format(date);
        System.out.println(pdfFileBasePath);
        String path = pdfFileBasePath+ dateTag;
        System.out.println("===+++++===path===++++++==="+path);
        //文件保存到数据库的相对路径
      	//String path = getPdfFileBasePath() ;
      	//文件保存的本地全路径
      	String rootpath = request.getSession().getServletContext().getRealPath("/");
      	if(osFlag == true){
			 rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
      	     rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
		} else {
		 	rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
      		rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
		}
      	String	dirPath = rootpath+ path;
    	System.out.println("===+++++===rootpath===++++++==="+rootpath);
        // 检查目录是否存在，不存在则创建
        checkCreateDir(dirPath);
        String relativePath = String.valueOf(date.getTime()) + "-"
                        + String.valueOf(new Random().nextInt(100)) + ".pdf";
        String outputFile = dirPath + "/" + relativePath;
    
        	String temPath = dirPath+"/tem"+String.valueOf(new Random().nextInt(100)) + ".pdf";
        	File tempFile = new File(temPath);
        	/*if(org_code != null && "icbcbank".equals(org_code)){
        		new CqGsPdfTemplate().generatePDF(tempFile,id);
        	}else{
        	}*/
        	new AuthorizationrecordPDFTeplate().generatePDF(tempFile,id,authorizationrecordEntity);
        	//添加水印
        	PdfReader reader = new PdfReader(temPath);
        	OutputStream out = new FileOutputStream(outputFile);
        	PdfStamper stamp = new PdfStamper(reader,out);
        	AuthorizationrecordPDFTeplate.addMark(stamp,reader.getNumberOfPages(),"");
        	out.close();
        	reader.close();
        	//删除临时文件
        	if(tempFile.exists()) {  
        		tempFile.delete();  
        	}
        return dateTag+"/"+relativePath;
    }
    
    /**
     * 
     * @Function:PdfGenerator 
     * @Author Zhongyj
     * @date 2017-4-20 下午3:39:13
     * @param request
     * @param id
     * @param regionId
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String pdfGenerator(HttpServletRequest request,String id,String regionId,String region_code,String org_code)throws IOException,
    Exception{
    	Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //ApplicationContext ac = new ClassPathXmlApplicationContext();
        String dateTag = dateFormat.format(date);
//        String pdfFilePath = "";
//        if(osFlag == true){
//        	pdfFilePath ="attached"+File.pathSeparatorChar+"pdf"+File.pathSeparator;
//		} else {
//			pdfFilePath ="attached"+File.pathSeparator+"pdf"+File.pathSeparator;
//		}
        System.out.println(pdfFileBasePath);
        String path = pdfFileBasePath+ dateTag;
        System.out.println("===+++++===path===++++++==="+path);
        //文件保存到数据库的相对路径
      	//String path = getPdfFileBasePath() ;
      	//文件保存的本地全路径
      	String rootpath = request.getSession().getServletContext().getRealPath("/");
      	if(osFlag == true){
			 rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
      	     rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
		} else {
		 	rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
      		rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
		}
      	String	dirPath = rootpath+ path;
    	System.out.println("===+++++===rootpath===++++++==="+rootpath);
        // 检查目录是否存在，不存在则创建
        checkCreateDir(dirPath);
        String relativePath = String.valueOf(date.getTime()) + "-"
                        + String.valueOf(new Random().nextInt(100)) + ".pdf";
        String outputFile = dirPath + "/" + relativePath;
        
        //判断是否是重庆市
        String cQcode = pdfMapper.getRegionCodeByName("重庆市");
        
        //判断是否是广西壮族自治区
        String gXcode = pdfMapper.getRegionCodeByName("广西壮族自治区");
        
        if(cQcode!=null && cQcode.substring(0, 2).equals(region_code.substring(0, 2))){
        	String temPath = dirPath+"/tem"+String.valueOf(new Random().nextInt(100)) + ".pdf";
        	File tempFile = new File(temPath);
        	/*if(org_code != null && "icbcbank".equals(org_code)){
        		new CqGsPdfTemplate().generatePDF(tempFile,id);
        	}else{
        	}*/
        	new CqPdfTemplate().generatePDF(tempFile,id);
        	//添加水印
        	PdfReader reader = new PdfReader(temPath);
        	OutputStream out = new FileOutputStream(outputFile);
        	PdfStamper stamp = new PdfStamper(reader,out);
        	CqPdfTemplate.addMark(stamp,reader.getNumberOfPages());
        	out.close();
        	reader.close();
        	//删除临时文件
        	if(tempFile.exists()) {  
        		tempFile.delete();  
        	}
        } else if(gXcode!=null && gXcode.substring(0, 2).equals(region_code.substring(0, 2))){
        	String temPath = dirPath+"/tem"+String.valueOf(new Random().nextInt(100)) + ".pdf";
        	File tempFile = new File(temPath);
        	new GlPdfTemplate().generatePDF(tempFile,id);
        	//添加水印
        	PdfReader reader = new PdfReader(temPath);
        	OutputStream out = new FileOutputStream(outputFile);
        	PdfStamper stamp = new PdfStamper(reader,out);
        	CqPdfTemplate.addMark(stamp,reader.getNumberOfPages());
        	out.close();
        	reader.close();
        	//删除临时文件
        	if(tempFile.exists()) {  
        		tempFile.delete();  
        	}
        }else{
        	File File =new File(outputFile);    
        	File.createNewFile();
        	new PDFReport(File).generatePDF(request,id);
        }
        return dateTag+"/"+relativePath;
    }
    
    /**
     * 税务授权书PDF下载
     * htmlToPdfGenerator 
     * @Author Fangren
     * @date 2017-4-20 下午3:39:13
     * @param request
     * @param id
     * @return
     * @throws IOException
     * @throws Exception
     */
    public String htmlToPdfGenerator(HttpServletRequest request,String id,AuthorizationrecordEntity authorizationrecordEntity,String htmlstr)
    		throws IOException,
    Exception{
    	Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //ApplicationContext ac = new ClassPathXmlApplicationContext();
        String dateTag = dateFormat.format(date);
        System.out.println(pdfFileBasePath);
        String path = pdfFileBasePath+ dateTag;
        System.out.println("===+++++===path===++++++==="+path);
        //文件保存到数据库的相对路径
      	//String path = getPdfFileBasePath() ;
      	//文件保存的本地全路径
      	String rootpath = request.getSession().getServletContext().getRealPath("/");
      	if(osFlag == true){
			 rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\"));
      	     rootpath = rootpath.substring(0, rootpath.lastIndexOf("\\")+1);
		} else {
		 	rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
      		rootpath = rootpath.substring(0, rootpath.lastIndexOf("/")+1);
		}
      	String	dirPath = rootpath+ path;
    	System.out.println("===+++++===rootpath===++++++==="+rootpath);
        // 检查目录是否存在，不存在则创建
        checkCreateDir(dirPath);
        String relativePath = String.valueOf(date.getTime()) + "-"
                        + String.valueOf(new Random().nextInt(100)) + ".pdf";
        String outputFile = dirPath + "/" + relativePath;
    
        	String temPath = dirPath+"/tem"+String.valueOf(new Random().nextInt(100)) + ".pdf";
        	String htmlPath = dirPath+"/tem"+String.valueOf(new Random().nextInt(100)) + ".html";
        	// shen
        	File tempFile = new File(temPath);
        	
        	new HtmlToPdfTeplate().createPdf(htmlPath, tempFile,authorizationrecordEntity,htmlstr);
        	//添加水印
        	PdfReader reader = new PdfReader(temPath);
        	OutputStream out = new FileOutputStream(outputFile);
        	PdfStamper stamp = new PdfStamper(reader,out);
        	String sjmc="";
        	if(authorizationrecordEntity.getAt_sjmc()==null){
        		sjmc="广西省国家税务局";
        	}else{
        		sjmc=authorizationrecordEntity.getAt_sjmc();
        	}
        	
        	AuthorizationrecordPDFTeplate.addMark(stamp,reader.getNumberOfPages(),sjmc);
        	out.close();
        	reader.close();
        	//删除临时文件
        	if(tempFile.exists()) {  
        		tempFile.delete();  
        	}
        return dateTag+"/"+relativePath;
    }
}
