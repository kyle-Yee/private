package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPdfTeplate {
	
	/**
	 * 实训报告替换模板方法
	 * @param templatecont
	 * @param printModel
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "unused" })
	private String replaceText(String templatecont,AuthorizationrecordEntity entity) throws ParseException{
		String ksyear="";
		String ksmonth="";
		String ksdate="";
		String jsyear="";
		String jsmonth="";
		String jsdate="";
		String dkqx="";
		if(entity.getAt_sqkssj()==null){
			
		}else{
			//授权开始时间
			Date ksDate=new SimpleDateFormat("yyyy-MM-dd").parse(entity.getAt_sqkssj());
			//开始时间
			Calendar c = Calendar.getInstance();
			c.setTime(ksDate);
			//开始年份
			Integer iksyear=c.get(Calendar.YEAR);
			 ksyear=iksyear.toString();
			//开始月份
			Integer iksmonth=c.get(Calendar.MONTH)+1;
			 ksmonth=iksmonth.toString();
			//开始日份
			Integer iksdate=c.get(Calendar.DATE);
			 ksdate=iksdate.toString();
		}
		
		if(entity.getAt_sqsj()==null){
			
		}else{
			//贷款申请期限
			dkqx=entity.getAt_sqsj();
		}
		
		if(entity.getAt_sqkssj()==null){
			
		}else{
			//授权结束时间
			Date jsDate=new SimpleDateFormat("yyyy-MM-dd").parse(entity.getAt_sqjssj());
			//结束时间
			Calendar c1 = Calendar.getInstance();
			c1.setTime(jsDate);
			//结束年份
			Integer ijsyear=c1.get(Calendar.YEAR);
			 jsyear=ijsyear.toString();
			//结束月份
			Integer ijsmonth=c1.get(Calendar.MONTH)+1;
			 jsmonth=ijsmonth.toString();
			//结束日份
			Integer ijsdate=c1.get(Calendar.DATE);
			 jsdate=ijsdate.toString();
		}
		
		
		
		//当前时间
		Date dqDate=new Date();
		
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(dqDate);
		
		
		
		
		//当前年份
		Integer idqyear=c2.get(Calendar.YEAR);
		String dqyear=idqyear.toString();
		//当前月份
		Integer idqmonth=c2.get(Calendar.MONTH)+1;
		String dqmonth=idqmonth.toString();
		//当前日份
		Integer idqdate=c2.get(Calendar.DATE);
		String dqdate=idqdate.toString();
		if ("ZZ0012018030114581305002".equals(entity.getOrg_id())) {
			//编号
	    	templatecont=templatecont.replaceAll("\\#at_id", nvl(entity.getAt_id()));
	    	//授权开始（日）
	    	templatecont=templatecont.replaceAll("\\#at_ksdate", nvl(ksdate));
	    	//税局名称
	    	templatecont=templatecont.replaceAll("\\#at_sjmc", nvl(entity.getAt_sjmc()));
	    	//产品名称
	    	templatecont=templatecont.replaceAll("\\#at_fpname", nvl(entity.getFinancialProduct().getFpName()));
	    	//纳税人识别号
	    	templatecont=templatecont.replaceAll("\\#at_nsrsbh", nvl(entity.getAt_nsrsbh()));
	    	//企业名称
	    	templatecont=templatecont.replaceAll("\\#at_qymc", nvl(entity.getAt_qymc()));//
	    	//银行名称
	    	templatecont=templatecont.replaceAll("\\#at_orgname", nvl(entity.getOrganizationEntity().getOrgname()));//
	    	//法人姓名
	    	templatecont=templatecont.replaceAll("\\#at_frxm", nvl(entity.getAt_frxm()));//
	    	//法人身份证号码
	    	templatecont=templatecont.replaceAll("\\#at_frsfz", nvl(entity.getAt_frsfz()));//
	    	//贷款期限xx个月
	    	templatecont=templatecont.replaceAll("\\#at_dkqx", nvl(dkqx));//
	    	//授权开始（年）
	    	templatecont=templatecont.replaceAll("\\#at_ksyear", nvl(ksyear));//
	    	//授权开始（月）
	    	templatecont=templatecont.replaceAll("\\#at_ksmonth", nvl(ksmonth));//
	    	//授权结束（年）
	    	templatecont=templatecont.replaceAll("\\#at_jsyear", nvl(jsyear));//
	    	//授权结束（月）
	    	templatecont=templatecont.replaceAll("\\#at_jsmonth", nvl(jsmonth));//
	    	//授权结束（日）
	    	templatecont=templatecont.replaceAll("\\#at_jsdate", nvl(jsdate));//
	    	//当前（年）
	    	templatecont=templatecont.replaceAll("\\#at_dqyear", nvl(dqyear));//
	    	//当前（月）
	    	templatecont=templatecont.replaceAll("\\#at_dqmonth", nvl(dqmonth));//
	    	//当前（日）
	    	templatecont=templatecont.replaceAll("\\#at_dqdate", nvl(dqdate));//
	    	
		}else {
			
			//编号
			templatecont=templatecont.replaceAll("\\#at_id", nvl(entity.getAt_id()));
			//税局名称
			templatecont=templatecont.replaceAll("\\#at_sjmc", nvl(entity.getAt_sjmc()));
			//企业名称
			templatecont=templatecont.replaceAll("\\#at_qymc", nvl(entity.getAt_qymc()));
			//纳税人识别号
			templatecont=templatecont.replaceAll("\\#at_nsrsbh", nvl(entity.getAt_nsrsbh()));
			//银行名称
			templatecont=templatecont.replaceAll("\\#at_orgname", nvl(entity.getOrganizationEntity().getOrgname()));
			//产品名称
			templatecont=templatecont.replaceAll("\\#at_fpname", nvl(entity.getFinancialProduct().getFpName()));
			//法人姓名
			templatecont=templatecont.replaceAll("\\#at_frxm", nvl(entity.getAt_frxm()));
			//法人身份证号码
			templatecont=templatecont.replaceAll("\\#at_frsfz", nvl(entity.getAt_frsfz()));
			//贷款期限xx个月
			templatecont=templatecont.replaceAll("\\#at_dkqx", nvl(dkqx));
			//授权开始（年）
			templatecont=templatecont.replaceAll("\\#at_ksyear", nvl(ksyear));
			//授权开始（月）
			templatecont=templatecont.replaceAll("\\#at_ksmonth", nvl(ksmonth));
			//授权开始（日）
			templatecont=templatecont.replaceAll("\\#at_ksdate", nvl(ksdate));
			
			//授权结束（年）
			templatecont=templatecont.replaceAll("\\#at_jsyear", nvl(jsyear));
			//授权结束（月）
			templatecont=templatecont.replaceAll("\\#at_jsmonth", nvl(jsmonth));
			//授权结束（日）
			templatecont=templatecont.replaceAll("\\#at_jsdate", nvl(jsdate));
			
			//当前（年）
			templatecont=templatecont.replaceAll("\\#at_dqyear", nvl(dqyear));
			//当前（月）
			templatecont=templatecont.replaceAll("\\#at_dqmonth", nvl(dqmonth));
			//当前（日）
			templatecont=templatecont.replaceAll("\\#at_dqdate", nvl(dqdate));
		}
    	
    	
    	
		return templatecont;
	} 
	
	
	/**
	 * 向一段字符串中加入<br />
	 * @param str
	 * @return
	 */
	public static String onetoself(String str){
		String newstr = "";
		int size = ((str.length())%36 == 0) ? ((str.length())/36):((str.length())/36 + 1);
		for(int i=0;i<size ;i++){
		    int endIndex = (i+1)*36;
		    if((i+1)==size){
		         endIndex = str.length();
		    }
		    if(i==0){
		        newstr += str.substring(i,endIndex);
		    }else{
		        newstr += "<br />"+str.substring(i*36, endIndex);
		    }
		}
		return newstr;
	}
	
	/**
	 * 模板替换字段为空时用的方法
	 * @param string
	 * @return
	 */
	private String nvl(String string){
		if(isNotNull(string)){
			return string;
		}else{
			return "";
		}
	}
	
	private boolean isNotNull(String string){
		return !isNull(string);
	}
	
	private boolean isNull(String string){
		if(string == null ||
				string.isEmpty()||
				string.length()==0||
				string.equalsIgnoreCase("NULL")
				){
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 判断文件是否存在
	 * @param name
	 * @return
	 */
	public boolean isexists(String name) {
		File file = new File(name);
		boolean boo = false;
		if (file.exists()) {
			boo = true;
		}
		return boo;
	}
	
	
	   /**
     * 创建html文件
     * createHtml 方法
     * @descript：TODO
     * @param htmlPath
     * @param str
     * @return void
     * @author 谭伟军
     * @date 2016-1-14-下午5:35:09
     */
    public void createHtml(String htmlPath,String str){
        try{
            StringBuffer html = new StringBuffer(); 
            html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");       
            html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").     
                 append("<head>")       
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")     
                .append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")      
                .append("</head>")       
                .append("<body>");       
            html.append("<div>"+str+"</div>");       
            html.append("</body></html>"); 
            String val = html.toString();
            writeFile(htmlPath, val);
        }catch (Exception e){
           e.printStackTrace();
        }
    }
    
    
    /**
     * 写数据到html
     * writeFile 方法
     * @descript：TODO
     * @param path
     * @param str
     * @return void
     * @author 谭伟军
     * @date 2016-1-14-下午5:35:29
     */
    private void writeFile(String path,String str){
        try{
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file, false); // 如果追加方式用true
            out.write(str.toString().getBytes("utf-8"));// 注意需要转换对应的字符集
            out.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * 创建Pdf
     * createPdf 方法
     * @descript：TODO
     * @param htmlPath
     * @param pdfPath
     * @return void
     * @author 谭伟军
     * @date 2016-1-14-下午5:35:58
     */
    
    /**
     * 删除文件
     * delFile 方法
     * @descript：TODO
     * @param path
     * @return void
     * @author 谭伟军
     * @date 2016-1-14-下午5:35:47
     */
    private void delFile(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }

	public void createPdf(String htmlPath, File file,AuthorizationrecordEntity entity,String htmlstr) {
		try {
			//OutputStream out = new FileOutputStream(outputFile);
			String str=replaceText(htmlstr, entity);
			createHtml(htmlPath, str);
			// step 1
	        Document document = new Document();
	        // step 2
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
	        // step 3
	        document.open();
	        // step 4
	        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
	                new FileInputStream(htmlPath), Charset.forName("UTF-8"));
	        // step 5
	        document.close();
	        if (isexists(htmlPath)) {
				delFile(htmlPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
