package com.dcits.govsbu.sd.taxbankplatform.util;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.govsbu.sd.taxbankplatform.qytzf.model.TaxQytzfEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.JtnsqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.NsqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.PdfMapEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.QsxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.SdsnsqkEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.SdsyyesrfxEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxBggsEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxGsdjEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfBggsEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfCbxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfDksqEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfGsdjEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfNsqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfQyjgEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwdjEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.TaxPdfSwqtEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.ZzsnsEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.model.ZzsxsefxEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.PdfService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfBggsService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfCbxxService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfDksqService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfGsdjService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfNsqdService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfQyjgService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfSwdjService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfSwqtService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



/**
 * @versions:1.0 
 * @filename：PDFReport.java
 * @Company:东方微银
 * @Created: 2017-4-20下午下午5:30:462:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName PDFReport
 */
public class PDFReport{ 
    //pageSize = new Color(0xFF, 0xFF, 0xDE);
    Document document = new Document();// 建立一个Document对象  
    static boolean osFlag = false;
    static{
    	if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("win")){  
    		osFlag = true;
    	} 
    }
    private static Font headfont ;// 设置字体大小    
    private static Font keyfont;// 设置字体大小    
    private static Font textfont;// 设置字体大小    
    private static ApplicationContext ac = null;
    private static TaxPdfDksqService taxPdfDksService = null;
    private static TaxPdfBggsService taxPdfBggsService = null;
    private static TaxPdfCbxxService taxPdfCbxxService = null;
    private static TaxPdfGsdjService taxPdfGsdjService = null;
    private static TaxPdfNsqdService taxPdfNsqdService = null;
    private static TaxPdfQyjgService taxPdfQyjgService = null;
    private static TaxPdfSwdjService taxPdfSwdjService = null;
    private static TaxPdfSwqtService taxPdfSwqtService = null;
    private static PdfService pdfService = null;
    BaseFont bfChinese;
        
    static{    
        BaseFont bfChinese;    
        try {    
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);    
            headfont = new Font(bfChinese, 24, Font.BOLD);// 设置字体大小    
            keyfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小    
            textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
            ac = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-applicationContext.xml");
            taxPdfDksService = (TaxPdfDksqService) ac.getBean("TaxPdfDksqService");
            taxPdfBggsService = (TaxPdfBggsService) ac.getBean("TaxPdfBggsService");
            taxPdfCbxxService = (TaxPdfCbxxService) ac.getBean("TaxPdfCbxxService");
            taxPdfGsdjService = (TaxPdfGsdjService) ac.getBean("TaxPdfGsdjService");
            taxPdfNsqdService = (TaxPdfNsqdService) ac.getBean("TaxPdfNsqdService");
            taxPdfQyjgService = (TaxPdfQyjgService) ac.getBean("TaxPdfQyjgService");
            taxPdfSwdjService = (TaxPdfSwdjService) ac.getBean("TaxPdfSwdjService");
            taxPdfSwqtService = (TaxPdfSwqtService) ac.getBean("TaxPdfSwqtService");
            pdfService = (PdfService)ac.getBean("PdfService");
        } catch (Exception e) {             
            e.printStackTrace();    
        }     
    }    
        
   /**
     *     
     * @param file
     */
    public PDFReport(File file) {   
         try {    
            PdfWriter.getInstance(document,new FileOutputStream(file));    
            document.setPageSize(PageSize.A4);// 设置页面大小
            HeaderFooter header=new HeaderFooter(new Phrase("企业涉税信息分析报告",keyfont),false);  
            
            //设置是否有边框等  
             header.setBorder(Rectangle.NO_BORDER);  
            //header.setBorder(Rectangle.BOTTOM);  
            header.setAlignment(1);  
            //header.setBorderColor(Color.red);  
            document.setHeader(header);  
            //document.newPage();
            HeaderFooter footer=new HeaderFooter(new Phrase("-",keyfont),new Phrase("-",keyfont));  
            /** 
             * 0是靠左 
             * 1是居中 
             * 2是居右 
             */
            footer.setAlignment(1);  
            //footer.setBorderColor(Color.red);  
            footer.setBorder(Rectangle.NO_BORDER);  
            document.setFooter(footer);
            document.open();     
        } catch (Exception e) {    
            e.printStackTrace();    
        }     
            
            
    }
    int maxWidth = 520;    
        
        
     public PdfPCell createCell(String value,com.lowagie.text.Font font,int align){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);            
         cell.setHorizontalAlignment(align);        
         cell.setPhrase(new Phrase(value,font));
        return cell;    
    }    
        
     public PdfPCell createCell(String value,com.lowagie.text.Font font){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);     
         cell.setPhrase(new Phrase(value,font));    
        return cell;    
    }    
    
     public PdfPCell createCell(String value,com.lowagie.text.Font font,String key){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(Element.ALIGN_CENTER);     
         cell.setPhrase(new Phrase(value,font));
         Color cl = new Color(127,255,212);
         cell.setBackgroundColor(cl);
        return cell;    
    } 
     
     public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(align);        
         cell.setColspan(colspan); 
         Color cl = new Color(127,255,212);
         cell.setBackgroundColor(cl);
         cell.setPhrase(new Phrase(value,font));    
        return cell;    
    }    
    public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan,boolean boderFlag){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(align);        
         cell.setColspan(colspan);    
         cell.setPhrase(new Phrase(value,font));    
         /*cell.setPadding(3.0f);
         Color cl = new Color(0,0,255);
         cell.setBackgroundColor(cl);*/
         if(!boderFlag){    
             cell.setBorder(0);    
             cell.setPaddingTop(15.0f);    
             cell.setPaddingBottom(8.0f);    
         }    
        return cell;    
    }    
     public PdfPTable createTable(int colNumber){    
        PdfPTable table = new PdfPTable(colNumber);    
        try{    
            table.setTotalWidth(maxWidth);    
            table.setLockedWidth(true);    
            table.setHorizontalAlignment(Element.ALIGN_CENTER);         
            table.getDefaultCell().setBorder(3); 
            
        }catch(Exception e){    
            e.printStackTrace();    
        }    
        return table;    
    }    
     public PdfPTable createTable(float[] widths){    
            PdfPTable table = new PdfPTable(widths);    
            try{    
                table.setTotalWidth(maxWidth);    
                table.setLockedWidth(true);    
                table.setHorizontalAlignment(Element.ALIGN_CENTER);         
                table.getDefaultCell().setBorder(1);    
            }catch(Exception e){    
                e.printStackTrace();    
            }    
            return table;    
        }    
        
     public PdfPTable createBlankTable(){    
         PdfPTable table = new PdfPTable(1);    
         table.getDefaultCell().setBorder(0);    
         table.addCell(createCell("", keyfont));             
         table.setSpacingAfter(20.0f);    
         table.setSpacingBefore(20.0f);    
         return table;    
     }    
   
	/**
	 * @Author Zhongyj
	 * @date 2017-4-20 下午5:30:31
	 * @param request
	 * @param id
	 * @throws Exception
	 */
	public void generatePDF(HttpServletRequest request,String id) throws Exception{ 
    	 Calendar now = Calendar.getInstance();
		  int currentMonth = now.get(Calendar.MONTH);
		  currentMonth = currentMonth+1;
		  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  String current = String.valueOf(now.get(Calendar.YEAR));
		  now.add(Calendar.YEAR,-1);
		  String first = String.valueOf(now.get(Calendar.YEAR));//去年
		  now.add(Calendar.YEAR,-1);
		  String second = String.valueOf(now.get(Calendar.YEAR));//前一年
		  now.add(Calendar.YEAR,-1);
		  String three = String.valueOf(now.get(Calendar.YEAR));//大前年
    	 List<PdfMapEntity> pdf =  pdfService.findPdfMap();
    	 String userId =  taxPdfBggsService.findUserIdById(id);
    	 String sqxh = taxPdfBggsService.findHNSqxh();
    	 Map<String,Object> params = new HashMap<String,Object>();
    	 params.put("userid", userId);
    	 params.put("sqxh", sqxh);
	    Paragraph blank = new Paragraph("", headfont);
	    blank.setSpacingAfter(100);
	    List<TaxPdfBggsEntity>  taxPdfBggsEntity = taxPdfBggsService.findByDjxh(params);
	    String qymc = null;
	    TaxBggsEntity taxBggsEntity = null;
	    if(taxPdfBggsEntity.size()>0){
	    	taxBggsEntity = taxPdfBggsEntity.get(0).getTaxBggsEntity();
	    }
		if(taxBggsEntity != null){
			 qymc = taxBggsEntity.getQymc();
		 
		 }
		
		Paragraph title = new Paragraph("企业涉税信息分析报告", headfont);
	    title.setIndentationLeft(130);
	    title.setSpacingAfter(250);
	    title.setFirstLineIndent(20);
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(title);
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));

        document.add(new Paragraph("企业名称: "+qymc,keyfont));
        String bgsj =  "报告时间: " + f.format(now.getTime());
        document.add(new Paragraph(bgsj,keyfont));
        String str = "制作单位: 东方微银银税互动服务平台";
        Paragraph titl = new Paragraph(str,keyfont);
        document.add(titl);
	    document.add(new Paragraph("\n"));
	    document.add(new Paragraph("\n"));
        Paragraph cTitle = new Paragraph("报告阅读须知", headfont);
        cTitle.setIndentationLeft(150);
        document.add(cTitle);
	    document.add(new Paragraph("\n"));
        String str1 = "1、本报告仅供查询机构参考，不起任何证明作用。且本报告仅限查询依照法律法规规章所规定的查询用途"+
        		"使用，不得用作法律诉讼依据；未经内蒙古自治区国家税务局书面同意或授权，不得向第三方透漏报告任何"+
				"内容；在任何情况下，对由于使用本报告所造成的损失，内蒙古自治区国家税务局不承担任何责任";
        document.add(new Paragraph(str1,keyfont));
        document.add(new Paragraph("\n"));
        String str2 = "2、未经书面许可，不得擅自复制、摘录、编辑和发表";
        document.add(new Paragraph(str2,keyfont));	
        document.add(new Paragraph("\n"));
        String str3 = "3、本报告的生成依据是截至报告时间为止的税务局数据库中的相关信息。";
        document.add(new Paragraph(str3,keyfont));
        document.add(new Paragraph("\n"));
        String str4 = "4、请妥善保管本报告，并注意保密。";
        document.add(new Paragraph(str4,keyfont));
        document.add(new Paragraph("\n"));
        TaxPdfNsqdEntity taxPdfNsdjEntity = null;
 		 CreateChartUtil chart = new CreateChartUtil();
	  	 //贷款申请信息
		 PdfPTable dksqxxTable = createTable(5);
		 dksqxxTable.addCell(createCell("贷款申请信息", keyfont,Element.ALIGN_LEFT,5,true));
		 dksqxxTable.addCell(createCell("申请贷款额度（万元）", textfont,""));
		 dksqxxTable.addCell(createCell("期望还款方式 ", textfont,""));
		 dksqxxTable.addCell(createCell("期望贷款期限（月）", textfont,""));
		 dksqxxTable.addCell(createCell("企业职工人数", textfont,""));
		 dksqxxTable.addCell(createCell("联系人手机号", textfont,""));
		 
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 map.put("id", id);
    	 map.put("sqxh", sqxh);
		 
		 TaxPdfDksqEntity taxPdfEntity = taxPdfDksService.findById(map);
		 String rwid = "";//还款方式	
		 String la_amount = "";//申请额度(万)	
		 String la_repay_loan_deadline = "";//贷款期限
		 String zcsjh = "";//注册手机号
		 String cyrs = "";//从业人数
		 
		 String rwname = "";
		 StringBuffer rwnameBuffer = new StringBuffer();
		 if(taxPdfEntity != null){
			/* String rwname = taxPdfEntity.getRw_name();*/
			/* 改动：多项期望还款方式*/
			 
			 rwid = taxPdfEntity.getRw_id();
			 if(rwid != null){
				 String[] ids = rwid.split("#");
				 for (String theId : ids) {
					 rwnameBuffer.append(taxPdfDksService.getHkfsName(theId));
				 }
				 rwname = new String(rwnameBuffer);
			 }
			
			 
			 la_amount = String.valueOf(taxPdfEntity.getLa_amount());
			 la_repay_loan_deadline = String.valueOf(taxPdfEntity.getLa_repay_loan_deadline());
			 zcsjh = taxPdfEntity.getZcsjh();
			 cyrs = String.valueOf(taxPdfEntity.getCyrs());
			 dksqxxTable.addCell(createCell(la_amount, textfont));
			 //dksqxxTable.addCell(createCell(rwid, textfont));
			 dksqxxTable.addCell(createCell(rwname, textfont));
			 dksqxxTable.addCell(createCell(la_repay_loan_deadline, textfont));
			 dksqxxTable.addCell(createCell(cyrs, textfont));
			 dksqxxTable.addCell(createCell(zcsjh, textfont));
		 }else{
			 dksqxxTable.addCell(createCell("暂无数据", textfont));
			 dksqxxTable.addCell(createCell("暂无数据", textfont));
			 dksqxxTable.addCell(createCell("暂无数据", textfont));
			 dksqxxTable.addCell(createCell("暂无数据", textfont));
			 dksqxxTable.addCell(createCell("暂无数据", textfont));
		 }
		 dksqxxTable.setSpacingAfter(20);
	 
	 document.add(dksqxxTable);
  	
  	 //企业基本信息
	 PdfPTable qijbxxTable = createTable(6);
		 qijbxxTable.addCell(createCell("企业基本信息", keyfont,Element.ALIGN_LEFT,6,true));
		 qijbxxTable.addCell(createCell("企业名称", textfont,""));
		 qijbxxTable.addCell(createCell("法人代表 ", textfont,""));
		 qijbxxTable.addCell(createCell("注册资本", textfont,""));
		 qijbxxTable.addCell(createCell("企业类别", textfont,""));
		 qijbxxTable.addCell(createCell("成立时间", textfont,""));
		 qijbxxTable.addCell(createCell("组织机构代码", textfont,""));
		 
		if(taxBggsEntity != null){
    	 	 String fddbrxm = null;
    	 	 String zczb = null;
    	 	 String zzjgkxdm = null;
    	 	 String dj = null;
    	 	 String hydm = null;
    	 	 
    	 	 String djzclxDm = null;
    	     String djrq = null;
    	     
    	     String swdjjh = null;//企业税务登记号、
	    	 fddbrxm = taxBggsEntity.getFddbrxm();
	    	 zczb = taxBggsEntity.getZczb();
	    	 int size = zczb.length();
 			if(size >= 8){
 				zczb = zczb.substring(0, size-4);
			}
	    	 zzjgkxdm = taxBggsEntity.getZzjgkxdm();
	    	 dj = taxBggsEntity.getDj();
	    	 hydm = taxBggsEntity.getHydm();
	    	//djzclxDm = String.valueOf(taxBggsEntity.getDjzclxDm());
	    	String djzclxmc=taxBggsEntity.getDjzclxmc();
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	if(taxBggsEntity.getDjrq()!=null){
	    		djrq=sdf.format(taxBggsEntity.getDjrq());
	    	}
	    	swdjjh = taxBggsEntity.getNsrsbh();
	    	qijbxxTable.addCell(createCell(qymc, textfont));
	    	qijbxxTable.addCell(createCell(fddbrxm, textfont));
	    	qijbxxTable.addCell(createCell(zczb, textfont));
	    	//qijbxxTable.addCell(createCell(djzclxDm, textfont));
	    	qijbxxTable.addCell(createCell(djzclxmc, textfont));
	    	qijbxxTable.addCell(createCell(djrq, textfont));
	    	qijbxxTable.addCell(createCell(zzjgkxdm, textfont));
		}else{
			qijbxxTable.addCell(createCell("暂无数据", textfont));
			qijbxxTable.addCell(createCell("暂无数据", textfont));
			qijbxxTable.addCell(createCell("暂无数据", textfont));
			qijbxxTable.addCell(createCell("暂无数据", textfont));
			qijbxxTable.addCell(createCell("暂无数据", textfont));
			qijbxxTable.addCell(createCell("暂无数据", textfont));
		}
		 qijbxxTable.setSpacingAfter(20);
	 
	 document.add(qijbxxTable);
  	
  	 //企业税务信息
	 PdfPTable qyswxxTable = createTable(4);
	 qyswxxTable.addCell(createCell("企业税务信息", keyfont,Element.ALIGN_LEFT,4,true));
	 qyswxxTable.addCell(createCell("企业税务登记号/社会信用统一", textfont,""));
	 qyswxxTable.addCell(createCell("纳税信用等级 ", textfont,""));
	 qyswxxTable.addCell(createCell("近两年总纳税额度", textfont,""));
	 qyswxxTable.addCell(createCell("年均纳税额度", textfont,""));
	 String hydm = null;
		if(taxBggsEntity != null){
			 hydm = taxBggsEntity.getHydm();
    	 	 String dj = null;
    	     String swdjjh = null;
	    	 dj = taxBggsEntity.getDj();
	    	swdjjh = taxBggsEntity.getNsrsbh();
	    	 BigDecimal zsned = null;
	    	 BigDecimal njnsed = null;
	    	 zsned = taxPdfBggsService.findZnsed(sqxh);
	    	 njnsed = zsned.divide(new BigDecimal("2"), 2, BigDecimal.ROUND_UP) ;
	    	 qyswxxTable.addCell(createCell(swdjjh, textfont));
	    	 qyswxxTable.addCell(createCell(dj, textfont));
	    	 qyswxxTable.addCell(createCell(zsned.toString(), textfont));
	    	 qyswxxTable.addCell(createCell(njnsed.toString(), textfont));
		}else{
   	    	 qyswxxTable.addCell(createCell("暂无数据", textfont));
   	    	 qyswxxTable.addCell(createCell("暂无数据", textfont));
   	    	 qyswxxTable.addCell(createCell("暂无数据", textfont));
   	    	 qyswxxTable.addCell(createCell("暂无数据", textfont));
		}
	 qyswxxTable.setSpacingAfter(20);
	 document.add(qyswxxTable);

		 
		 
	  	 //企业行业信息
		 PdfPTable qyhyxxTable = createTable(5);
		 qyhyxxTable.addCell(createCell("企业行业信息", keyfont,Element.ALIGN_LEFT,5,true));
		 qyhyxxTable.addCell(createCell("行业代码", textfont,""));
		 qyhyxxTable.addCell(createCell("小类 ", textfont,""));
		 qyhyxxTable.addCell(createCell("中类", textfont,""));
		 qyhyxxTable.addCell(createCell("大类", textfont,""));
		 qyhyxxTable.addCell(createCell("门类", textfont,""));
		 //String hydm = taxBggsEntity.getHydm();
		 if(hydm != null){
    		TaxPdfBggsEntity  hyxxEntity = taxPdfBggsService.findHyxx(hydm);
    		if(hyxxEntity != null){
    			qyhyxxTable.addCell(createCell(hydm, textfont));
    			qyhyxxTable.addCell(createCell(hyxxEntity.getXl(), textfont));
    			qyhyxxTable.addCell(createCell(hyxxEntity.getZl(), textfont));
    			qyhyxxTable.addCell(createCell(hyxxEntity.getDl(), textfont));
    			qyhyxxTable.addCell(createCell(hyxxEntity.getMl(), textfont));
    		}
    	}else{
    		qyhyxxTable.addCell(createCell("暂无数据", textfont));
    		qyhyxxTable.addCell(createCell("暂无数据", textfont));
    		qyhyxxTable.addCell(createCell("暂无数据", textfont));
    		qyhyxxTable.addCell(createCell("暂无数据", textfont));
    		qyhyxxTable.addCell(createCell("暂无数据", textfont));
    	}
	 qyhyxxTable.setSpacingAfter(20);
	 document.add(qyhyxxTable);
  	
  	
	 //工商登记信息
	 PdfPTable gsdjxxTable = createTable(12);
	 gsdjxxTable.addCell(createCell("工商登记信息", keyfont,Element.ALIGN_LEFT,12,true));
	 gsdjxxTable.addCell(createCell("企业名称", textfont,""));
	 gsdjxxTable.addCell(createCell("社会信用统一代码", textfont,""));
	 gsdjxxTable.addCell(createCell("税务登记号", textfont,""));
	 gsdjxxTable.addCell(createCell("所属行业", textfont,""));
	 gsdjxxTable.addCell(createCell("注册资本", textfont,""));
	 gsdjxxTable.addCell(createCell("组织机构代码", textfont,""));
	 gsdjxxTable.addCell(createCell("注册地址", textfont,""));
	 gsdjxxTable.addCell(createCell("登记注册类型", textfont,""));
	 gsdjxxTable.addCell(createCell("纳税人状态", textfont,""));
	 gsdjxxTable.addCell(createCell("税务登记时间", textfont,""));
	 gsdjxxTable.addCell(createCell("从业人数", textfont,""));
	 gsdjxxTable.addCell(createCell("经营范围", textfont,""));
		 
	 List<TaxPdfGsdjEntity> taxPdfGsdjEntityList = taxPdfGsdjService.findByDjxh(params);
	 TaxGsdjEntity taxGsdjEntity = null;
	 if(taxPdfGsdjEntityList.size()>0){
		 taxGsdjEntity = taxPdfGsdjEntityList.get(0).getTaxGsdjEntity();
	 }
		if(taxGsdjEntity != null){
	    	String shtydm = taxGsdjEntity.getNsrsbh();
	    	String swdjxh = taxGsdjEntity.getNsrsbh();
	    	String sshy = taxGsdjEntity.getHymc();
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	    	String swdjsj = null;
	    	if(taxGsdjEntity.getDjrq()!=null){
	    		swdjsj = sdf.format(taxGsdjEntity.getDjrq());
	    	}
	    	
	    	qymc = taxGsdjEntity.getQymc();
	    	String zczb = taxGsdjEntity.getZczb();
	    	int size = zczb.length();
			if(size >= 8){
				zczb = zczb.substring(0, size-4);
			}
	    	String zzjgdm = taxGsdjEntity.getZzjgkxdm();
	    	String zcdz = taxGsdjEntity.getZcdz();
	    	//String djzclx = taxGsdjEntity.getDjzclxdm();
	    	String djzclxmc=taxGsdjEntity.getDjzclxmc();
	    	//String nsrzt = taxGsdjEntity.getNsrztdm();
	    	String nsrztMC = taxGsdjEntity.getNsrztmc();
	    	cyrs = taxGsdjEntity.getCyrs();
	    	String jyfw = taxGsdjEntity.getJyfw();
	    	gsdjxxTable.addCell(createCell(qymc, textfont));
	    	gsdjxxTable.addCell(createCell(shtydm, textfont));
	    	gsdjxxTable.addCell(createCell(swdjxh, textfont));
	    	gsdjxxTable.addCell(createCell(sshy, textfont));
	    	gsdjxxTable.addCell(createCell(zczb, textfont));
	    	gsdjxxTable.addCell(createCell(zzjgdm, textfont));
	    	gsdjxxTable.addCell(createCell(zcdz, textfont));
	    	//gsdjxxTable.addCell(createCell(djzclx, textfont));
	    	gsdjxxTable.addCell(createCell(djzclxmc, textfont));
	    	//gsdjxxTable.addCell(createCell(nsrzt, textfont));
	    	gsdjxxTable.addCell(createCell(nsrztMC, textfont));
	    	gsdjxxTable.addCell(createCell(swdjsj, textfont));
	    	gsdjxxTable.addCell(createCell(cyrs, textfont));
	    	gsdjxxTable.addCell(createCell(jyfw, textfont));
		}else{
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
	    	gsdjxxTable.addCell(createCell("暂无数据", textfont));
		}
		gsdjxxTable.setSpacingAfter(20);
	 document.add(gsdjxxTable);	 
		 
	TaxPdfSwdjEntity  taxPdfSwdjEntity = taxPdfSwdjService.findByDjxh(params);
	String dqxydj = "";
	String dqdjpjsj = "";
	String nsrrdzg = "";
	String zgswjg = "";
	if(taxPdfSwdjEntity != null){
	 // List<TaxSwdjListEntity>  taxSwdjListEntity= null;
	  dqxydj = taxPdfSwdjEntity.getDj();
	  dqdjpjsj = taxPdfSwdjEntity.getYear();
	  //taxSwdjListEntity = taxPdfSwdjEntity.getTaxSwdjList();
	}
	 //纳税信用等级
	 PdfPTable nsrxxdjTable = createTable(2);
	 nsrxxdjTable.addCell(createCell("纳税信用等级", keyfont,Element.ALIGN_LEFT,2,true));
	 nsrxxdjTable.addCell(createCell("当前纳税信用等级评价", textfont,""));
	 nsrxxdjTable.addCell(createCell("评定年份", textfont,""));
	  if(dqxydj!=null||dqdjpjsj!=null){
		  if(dqxydj==null||"".equals(dqxydj)){
			  dqxydj ="暂无数据";
		  }
		  if(dqdjpjsj==null||"".equals(dqdjpjsj)){
			  dqdjpjsj ="暂无数据";
		  }
		  nsrxxdjTable.addCell(createCell(dqxydj, textfont));
		  nsrxxdjTable.addCell(createCell(dqdjpjsj, textfont));
	  }else{
		  nsrxxdjTable.addCell(createCell("暂无数据", textfont));
		  nsrxxdjTable.addCell(createCell("暂无数据", textfont));
	  }
	  nsrxxdjTable.setSpacingAfter(20);
	  document.add(nsrxxdjTable);	
	  //纳税人资格认定
	 PdfPTable nsrzgrdTable = createTable(2);
	 nsrzgrdTable.addCell(createCell("纳税人资格认定 ", keyfont,Element.ALIGN_LEFT,2,true));
	 nsrzgrdTable.addCell(createCell("纳税人资格 ", textfont,""));
	 nsrzgrdTable.addCell(createCell("主管税务机关", textfont,""));
	 if(nsrrdzg!= null || zgswjg!= null){
		  if(nsrrdzg==null||"".equals(nsrrdzg)){
			  nsrrdzg ="暂无数据";
		  }
		  if(zgswjg==null||"".equals(zgswjg)){
			  zgswjg ="暂无数据";
		  }
		 nsrzgrdTable.addCell(createCell(nsrrdzg, textfont));
		 nsrzgrdTable.addCell(createCell(zgswjg, textfont));
	 }
	 nsrzgrdTable.setSpacingAfter(20);
	 document.add(nsrzgrdTable);

	TaxPdfSwqtEntity  taxPdfSwqtEntity = taxPdfSwqtService.findByDjxh(params);
	String bgxm = null;
	String bgq = null;
	String bgh = null;
	String bgrq = null;
	//企业税务变更记录
	 PdfPTable qyswbgjlTable = createTable(4);
	 qyswbgjlTable.addCell(createCell("企业税务变更记录", keyfont,Element.ALIGN_LEFT,4,true));
	 qyswbgjlTable.addCell(createCell("变更项目（名称）", textfont,""));
	 qyswbgjlTable.addCell(createCell("变更前 ", textfont,""));
	 qyswbgjlTable.addCell(createCell("变更后", textfont,""));
	 qyswbgjlTable.addCell(createCell("变更日期", textfont,""));
	if(taxPdfSwqtEntity != null){
		if(taxPdfSwqtEntity.getTaxSwqtListEntity().size() > 0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int n=0;n<taxPdfSwqtEntity.getTaxSwqtListEntity().size();n++){
				
				bgxm = taxPdfSwqtEntity.getTaxSwqtListEntity().get(n).getTaxQybgxxEntity().getTthbBgxmDm();
				bgq = taxPdfSwqtEntity.getTaxSwqtListEntity().get(n).getTaxQybgxxEntity().getTthbBgqnr();
				bgh = taxPdfSwqtEntity.getTaxSwqtListEntity().get(n).getTaxQybgxxEntity().getTthbBghnr();
				bgrq = sdf.format(taxPdfSwqtEntity.getTaxSwqtListEntity().get(n).getTaxQybgxxEntity().getTthbXgrq());
				
				qyswbgjlTable.addCell(createCell(bgxm, textfont));
				qyswbgjlTable.addCell(createCell(bgq, textfont));
				qyswbgjlTable.addCell(createCell(bgh, textfont));
				qyswbgjlTable.addCell(createCell(bgrq, textfont));
			}
		} else {
			qyswbgjlTable.addCell(createCell("暂无数据", textfont));
			qyswbgjlTable.addCell(createCell("暂无数据", textfont));
			qyswbgjlTable.addCell(createCell("暂无数据", textfont));
			qyswbgjlTable.addCell(createCell("暂无数据", textfont));
		}
	} else {
		qyswbgjlTable.addCell(createCell("暂无数据", textfont));
		qyswbgjlTable.addCell(createCell("暂无数据", textfont));
		qyswbgjlTable.addCell(createCell("暂无数据", textfont));
		qyswbgjlTable.addCell(createCell("暂无数据", textfont));
	}
	qyswbgjlTable.setSpacingAfter(20);
	document.add(qyswbgjlTable);
	
	//违法违章信息
	PdfPTable qywzxxTable = createTable(8);
	qywzxxTable.addCell(createCell("违法违章信息", keyfont,Element.ALIGN_LEFT,8,true));
	qywzxxTable.addCell(createCell("企业名称", textfont,""));
	qywzxxTable.addCell(createCell("税务登记号", textfont,""));
	qywzxxTable.addCell(createCell("登记日期", textfont,""));
	qywzxxTable.addCell(createCell("违法违章事实 ", textfont,""));
	qywzxxTable.addCell(createCell("违法违章类型", textfont,""));
	qywzxxTable.addCell(createCell("违法违章状态", textfont,""));
	qywzxxTable.addCell(createCell("处理处罚决定", textfont,""));
	qywzxxTable.addCell(createCell("处理办法", textfont,""));
	String swdjxh = "";
	String djrq = "";
	String wfss = "";
	String wflx = "";
	String wfzt = "";
	String cljd = "";
	String clbf = "";
	if(taxPdfSwqtEntity != null){
		qymc = taxPdfSwqtEntity.getQymc();
		swdjxh = taxPdfSwqtEntity.getNsrsbh();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		if(taxPdfSwqtEntity.getTaxSwqtListEntity().size() > 0){
			for(int i=0;i<taxPdfSwqtEntity.getTaxSwqtListEntity().size();i++){
				djrq = sdf.format(taxPdfSwqtEntity.getTaxSwqtListEntity().get(i).getTaxSswfxwdjEntity().getTtsDjrq());
				wfss = taxPdfSwqtEntity.getTaxSwqtListEntity().get(i).getTaxSswfxwdjEntity().getTtsWfsx();
				wflx = taxPdfSwqtEntity.getTaxSwqtListEntity().get(i).getTaxSswfxwdjEntity().getTtsSswflxDm();
				wfzt = taxPdfSwqtEntity.getTaxSwqtListEntity().get(i).getTaxSswfxwdjEntity().getTtsSswfxwclztDm();
				qywzxxTable.addCell(createCell(qymc, textfont));
				qywzxxTable.addCell(createCell(swdjxh, textfont));
				qywzxxTable.addCell(createCell(djrq, textfont));
				qywzxxTable.addCell(createCell(wfss, textfont));
				qywzxxTable.addCell(createCell(wflx, textfont));
				qywzxxTable.addCell(createCell(wfzt, textfont));
				qywzxxTable.addCell(createCell("待定", textfont));
				qywzxxTable.addCell(createCell("待定", textfont));
			}
			
		} else {
			qywzxxTable.addCell(createCell("暂无数据", textfont));
			qywzxxTable.addCell(createCell("暂无数据", textfont));
			qywzxxTable.addCell(createCell("暂无数据", textfont));
			qywzxxTable.addCell(createCell("暂无数据", textfont));
			qywzxxTable.addCell(createCell("暂无数据", textfont));
			qywzxxTable.addCell(createCell("暂无数据", textfont));
			qywzxxTable.addCell(createCell("待定", textfont));
			qywzxxTable.addCell(createCell("待定", textfont));
		}
	}else{
		qywzxxTable.addCell(createCell("暂无数据", textfont));
		qywzxxTable.addCell(createCell("暂无数据", textfont));
		qywzxxTable.addCell(createCell("暂无数据", textfont));
		qywzxxTable.addCell(createCell("暂无数据", textfont));
		qywzxxTable.addCell(createCell("暂无数据", textfont));
		qywzxxTable.addCell(createCell("暂无数据", textfont));
		qywzxxTable.addCell(createCell("待定", textfont));
		qywzxxTable.addCell(createCell("待定", textfont));
	}
	qywzxxTable.setSpacingAfter(20);
	document.add(qywzxxTable);	
 				
	//欠税信息
	PdfPTable qsxxTable = createTable(5);
	qsxxTable.addCell(createCell("欠税信息", keyfont,Element.ALIGN_LEFT,5,true));
	qsxxTable.addCell(createCell("序号", textfont,""));
	qsxxTable.addCell(createCell("项目", textfont,""));
	qsxxTable.addCell(createCell("国税税额", textfont,""));
	qsxxTable.addCell(createCell("地税税额", textfont,""));
	qsxxTable.addCell(createCell("合计税额", textfont,""));
	TaxPdfSwqtEntity  qyxxEntity = taxPdfSwqtService.findQsxx(sqxh);
	if(qyxxEntity != null){
		if(qyxxEntity.getQsxxEntity()!= null){
			for(int d=0;d<qyxxEntity.getQsxxEntity().size();d++){
				QsxxEntity qsxxEntity = qyxxEntity.getQsxxEntity().get(d);
				if(qsxxEntity != null){
					String zsxmmc = qsxxEntity.getZsxmmc()+"";
					String ybse = qsxxEntity.getYbse()+"";
					
					qsxxTable.addCell(createCell(d+"", textfont));
					qsxxTable.addCell(createCell(zsxmmc, textfont));
					qsxxTable.addCell(createCell(ybse, textfont));
					qsxxTable.addCell(createCell("无数据", textfont));
					qsxxTable.addCell(createCell(ybse, textfont));
				}
			}	
		}
	}else{
		qsxxTable.addCell(createCell("暂无数据", textfont));
		qsxxTable.addCell(createCell("暂无数据", textfont));
		qsxxTable.addCell(createCell("暂无数据", textfont));
		qsxxTable.addCell(createCell("暂无数据", textfont));
		qsxxTable.addCell(createCell("暂无数据", textfont));
	}
    	
	qsxxTable.setSpacingAfter(20);
	document.add(qsxxTable);	
 
		
		
		
		
	TaxPdfQyjgEntity taxPdfQyjgEntity = taxPdfQyjgService.findByDjxh(params);
		//法人信息
	PdfPTable fsxxTable = createTable(5);
	fsxxTable.addCell(createCell("法人信息", keyfont,Element.ALIGN_LEFT,5,true));
	fsxxTable.addCell(createCell("法人姓名", textfont,""));
	fsxxTable.addCell(createCell("法人证件类型", textfont,""));
	fsxxTable.addCell(createCell("证件号", textfont,""));
	fsxxTable.addCell(createCell("法人联系方式/固话 ", textfont,""));
	fsxxTable.addCell(createCell("法人联系方式/手", textfont,""));
	if(taxPdfQyjgEntity != null){
		String fddbrxm = "";
		String fddbrsfzjlmdm = "";
		String sfzjlxmc = "";
		String fddbrsfzjhm = "";
		String fddbrgddh = "";
		String fddbryddh = "";
		fddbrxm = taxPdfQyjgEntity.getFddbrxm();
		fddbrsfzjlmdm = taxPdfQyjgEntity.getFddbrsfzjlmdm();
		sfzjlxmc = taxPdfQyjgEntity.getSfzjlxmc();
		fddbrsfzjhm = taxPdfQyjgEntity.getFddbrsfzjhm();
		fddbrgddh = taxPdfQyjgEntity.getFddbrgddh();
		fddbryddh = taxPdfQyjgEntity.getFddbryddh();
		fsxxTable.addCell(createCell(fddbrxm, textfont));
		//fsxxTable.addCell(createCell(fddbrsfzjlmdm, textfont));
		fsxxTable.addCell(createCell(sfzjlxmc, textfont));
		fsxxTable.addCell(createCell(fddbrsfzjhm, textfont));
		fsxxTable.addCell(createCell(fddbrgddh, textfont));
		fsxxTable.addCell(createCell(fddbryddh, textfont));
		}else{
			fsxxTable.addCell(createCell("暂无数据", textfont));
			fsxxTable.addCell(createCell("暂无数据", textfont));
			fsxxTable.addCell(createCell("暂无数据", textfont));
			fsxxTable.addCell(createCell("暂无数据", textfont));
			fsxxTable.addCell(createCell("暂无数据", textfont));
		}
		fsxxTable.setSpacingAfter(20);
		document.add(fsxxTable);
       	    		
	//股东信息
	PdfPTable gdxxTable = createTable(4);
	gdxxTable.addCell(createCell("股东信息", keyfont,Element.ALIGN_LEFT,4,true));
	gdxxTable.addCell(createCell("序号", textfont,""));
	gdxxTable.addCell(createCell("股东名称", textfont,""));
	gdxxTable.addCell(createCell("出资额(万)", textfont,""));
	gdxxTable.addCell(createCell("持股比例(% ) ", textfont,""));
	//gdxxTable.addCell(createCell("股东性质", textfont,""));
	List<TaxQytzfEntity> taxQytzfEntity = taxPdfQyjgService.findByUserid(params);
	
	if(taxQytzfEntity!=null){
		if(taxQytzfEntity.size()!=0){
			for(int i =0;i<taxQytzfEntity.size();i++){
				String number = i +""; 
				if(taxQytzfEntity.get(i) != null){
					String gumc = taxQytzfEntity.get(i).getTthyTzfhhhrmc();
					String cze = taxQytzfEntity.get(i).getTthyTzje();
					String cgbl = taxQytzfEntity.get(i).getTthyTzbl();
					String gdxz = taxQytzfEntity.get(i).getTthyTzfjjxzDm();
					gdxxTable.addCell(createCell(number, textfont));
					gdxxTable.addCell(createCell(gumc, textfont));
					gdxxTable.addCell(createCell(cze, textfont));
					gdxxTable.addCell(createCell(cgbl, textfont));
					//gdxxTable.addCell(createCell(gdxz, textfont));
				}
			}
		}else{
			gdxxTable.addCell(createCell("暂无数据", textfont));
			gdxxTable.addCell(createCell("暂无数据", textfont));
			gdxxTable.addCell(createCell("暂无数据", textfont));
			gdxxTable.addCell(createCell("暂无数据", textfont));
		}
	} else {
			gdxxTable.addCell(createCell("暂无数据", textfont));
			gdxxTable.addCell(createCell("暂无数据", textfont));
			gdxxTable.addCell(createCell("暂无数据", textfont));
			gdxxTable.addCell(createCell("暂无数据", textfont));
			//gdxxTable.addCell(createCell("暂无数据", textfont));
	}
	gdxxTable.setSpacingAfter(20);
	document.add(gdxxTable);
 				
 				
	//营运能力分析
	PdfPTable yynlfxTable = createTable(4);
	yynlfxTable.addCell(createCell("营运能力分析", keyfont,Element.ALIGN_LEFT,4,true));
	yynlfxTable.addCell(createCell("年份", textfont,""));
	yynlfxTable.addCell(createCell("总资产周转率", textfont,""));
	yynlfxTable.addCell(createCell("应收帐款周转率", textfont,""));
	yynlfxTable.addCell(createCell("存货周转率", textfont,""));
	
	yynlfxTable.addCell(createCell("暂无数据", textfont));
	yynlfxTable.addCell(createCell("暂无数据", textfont));
	yynlfxTable.addCell(createCell("暂无数据", textfont));
	yynlfxTable.addCell(createCell("暂无数据", textfont));
	yynlfxTable.setSpacingAfter(20);
	document.add(yynlfxTable);
	//短期偿债能力分析
	PdfPTable dqcznlfxTable = createTable(6);
	dqcznlfxTable.addCell(createCell("短期偿债能力分析", keyfont,Element.ALIGN_LEFT,6,true));
	dqcznlfxTable.addCell(createCell("年份", textfont,""));
	dqcznlfxTable.addCell(createCell("资产负债率", textfont,""));
	dqcznlfxTable.addCell(createCell("流动比率", textfont,""));
	dqcznlfxTable.addCell(createCell("速动比率", textfont,""));
	dqcznlfxTable.addCell(createCell("息税折摊前利润保障比率", textfont,""));
	dqcznlfxTable.addCell(createCell("经营所得现金/借入资金", textfont,""));
	
	dqcznlfxTable.addCell(createCell("暂无数据", textfont));
	dqcznlfxTable.addCell(createCell("暂无数据", textfont));
	dqcznlfxTable.addCell(createCell("暂无数据", textfont));
	dqcznlfxTable.addCell(createCell("暂无数据", textfont));
	dqcznlfxTable.addCell(createCell("暂无数据", textfont));
	dqcznlfxTable.addCell(createCell("暂无数据", textfont));
	dqcznlfxTable.setSpacingAfter(20);
	document.add(dqcznlfxTable);
       	    		
	//盈利能力分析
	PdfPTable ylnlfxTable = createTable(5);
	ylnlfxTable.addCell(createCell("盈利能力分析", keyfont,Element.ALIGN_LEFT,5,true));
	ylnlfxTable.addCell(createCell("年份", textfont,""));
	ylnlfxTable.addCell(createCell("总资产利润率", textfont,""));
	ylnlfxTable.addCell(createCell("净资产收益率", textfont,""));
	ylnlfxTable.addCell(createCell("销售利润率", textfont,""));
	ylnlfxTable.addCell(createCell("成本费用利润率", textfont,""));
	ylnlfxTable.addCell(createCell("暂无数据", textfont));
	ylnlfxTable.addCell(createCell("暂无数据", textfont));
	ylnlfxTable.addCell(createCell("暂无数据", textfont));
	ylnlfxTable.addCell(createCell("暂无数据", textfont));
	ylnlfxTable.addCell(createCell("暂无数据", textfont));
	ylnlfxTable.setSpacingAfter(20);
	document.add(ylnlfxTable);
       	  
    TaxPdfNsqdEntity taxPdfNsqdEntity = taxPdfNsqdService.findByDjxh();
    //纳税清单 
    PdfPTable nsqdTable = createTable(11);
	       nsqdTable.addCell(createCell("纳税清单", keyfont,Element.ALIGN_LEFT,11,true));
	       nsqdTable.addCell(createCell("年度", textfont,""));
	       nsqdTable.addCell(createCell("增值税", textfont,""));
	       nsqdTable.addCell(createCell("企业所得税", textfont,""));
	       nsqdTable.addCell(createCell("城市维护建设", textfont,""));
	       nsqdTable.addCell(createCell("印花税", textfont,""));
	       nsqdTable.addCell(createCell("城镇土地使用", textfont,""));
	       nsqdTable.addCell(createCell("教育费附加", textfont,""));
	       nsqdTable.addCell(createCell("地方教育附加", textfont,""));
	       nsqdTable.addCell(createCell("水利建设专项", textfont,""));
	       nsqdTable.addCell(createCell("其他收入", textfont,""));
	       nsqdTable.addCell(createCell("合计", textfont,""));
    if(taxPdfNsqdEntity !=null){
			BigDecimal tpnZzs = null;
			BigDecimal tpnQysds = null;
			BigDecimal tpnCswfjs = null;
			BigDecimal tpnYhs = null;
			BigDecimal tpnCztdsy = null;
			BigDecimal tpnJyffj = null;
			BigDecimal tpnDfjyfj = null;
			BigDecimal tpnSljszx = null;
			BigDecimal tpnQtsr = null;
			BigDecimal tpnTotal = null;
	  		 if(taxPdfNsqdEntity.getNsqdEntity() != null){
	  			 for(int b=0;b<taxPdfNsqdEntity.getNsqdEntity().size();b++){
	  				 NsqdEntity nsqdEntity =taxPdfNsqdEntity.getNsqdEntity().get(b);
	  				 if(nsqdEntity != null){
	  					tpnZzs = nsqdEntity.getTpnZzs();
	  					tpnQysds = nsqdEntity.getTpnQysds();
	  					tpnCswfjs = nsqdEntity.getTpnCswfjs();
	  					tpnYhs = nsqdEntity.getTpnYhs();
	  					tpnCztdsy = nsqdEntity.getTpnCztdsy();
	  					tpnJyffj = nsqdEntity.getTpnJyffj();
	  					tpnDfjyfj = nsqdEntity.getTpnDfjyfj();
	  					tpnSljszx = nsqdEntity.getTpnSljszx();
						tpnQtsr = nsqdEntity.getTpnQtsr();
	  					tpnTotal = nsqdEntity.getTpnTotal();
	  					tpnZzs = tpnZzs.add(tpnZzs);
	  					tpnQysds = tpnQysds.add(tpnQysds);
	  					tpnCswfjs = tpnCswfjs.add(tpnCswfjs);
	  					tpnYhs = tpnYhs.add(tpnYhs);
	  					tpnCztdsy = tpnCztdsy.add(tpnCztdsy);
	  					tpnJyffj = tpnJyffj.add(tpnJyffj);
	  					tpnDfjyfj = tpnDfjyfj.add(tpnDfjyfj);
	  					tpnSljszx = tpnSljszx.add(tpnSljszx);
	  					tpnQtsr = tpnQtsr.add(tpnQtsr);
	  					tpnTotal = tpnTotal.add(tpnTotal);
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnNf()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnZzs()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnQysds()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnCswfjs()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnYhs()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnCztdsy()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnJyffj()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnDfjyfj()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnSljszx()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnQtsr()+"", textfont));
	  					 nsqdTable.addCell(createCell(nsqdEntity.getTpnTotal()+"", textfont));
	  				 }
	  			 }
	  		 }
				 nsqdTable.addCell(createCell("合计", textfont));
				 nsqdTable.addCell(createCell(tpnZzs+"", textfont));
				 nsqdTable.addCell(createCell(tpnQysds+"", textfont));
				 nsqdTable.addCell(createCell(tpnCswfjs+"", textfont));
				 nsqdTable.addCell(createCell(tpnYhs+"", textfont));
				 nsqdTable.addCell(createCell(tpnCztdsy+"", textfont));
				 nsqdTable.addCell(createCell(tpnJyffj+"", textfont));
				 nsqdTable.addCell(createCell(tpnDfjyfj+"", textfont));
				 nsqdTable.addCell(createCell(tpnSljszx+"", textfont));
				 nsqdTable.addCell(createCell(tpnQtsr+"", textfont));
				 nsqdTable.addCell(createCell(tpnTotal+"", textfont));
	  	 }
	    nsqdTable.setSpacingAfter(20);
	    document.add(nsqdTable);
	    
/*    	taxPdfNsdjEntity=null;
		taxPdfNsdjEntity = taxPdfNsqdService.findSdsnsqk();
		String sdsseImgePath = chart.generatorSdssePath(taxPdfNsdjEntity,request);*/
		taxPdfNsdjEntity = null;
		taxPdfNsdjEntity = taxPdfNsqdService.findByDjxh();
		 List<String> fileNameList = chart.generatorNsqdPath(taxPdfNsdjEntity,request);
		if(fileNameList != null){
			for(int i=0;i<fileNameList.size();i++){
				String fileName = fileNameList.get(i);
				Image pieGif = Image.getInstance(fileName);
				document.add(pieGif);
			}
		}
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		
		
		PdfPTable table = createTable(8);
	  	 table.addCell(createCell("增值税纳税情况（增值税一般纳税人）", keyfont,Element.ALIGN_LEFT,8,true));
	  	 table.addCell(createCell("年份", textfont,""));
	  	 table.addCell(createCell("月份", textfont,""));
	  	 table.addCell(createCell("销售额", textfont,""));
	  	 table.addCell(createCell("销项税额", textfont,""));
	  	 table.addCell(createCell("进项税额", textfont,""));
	  	 table.addCell(createCell("应纳税额", textfont,""));
	  	 table.addCell(createCell("应抵扣税额", textfont,""));
	  	 table.addCell(createCell("实缴税额", textfont,""));
	  	TaxPdfNsqdEntity zzsns = taxPdfNsqdService.findZzsns();
	   if(zzsns !=null){
		 if(zzsns.getZzsnsEntity() != null){
			 for(int c=0;c<zzsns.getZzsnsEntity().size();c++){
				 ZzsnsEntity czsnsEntity =zzsns.getZzsnsEntity().get(c);
				 if(c<12){
					 table.addCell(createCell(czsnsEntity.getTpzNf()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzYf()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzJxse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzXxse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzJxse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzYnse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzYdkse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzSjse()+"", textfont));
				 }else if(c>=12 && c <24){
					 if(c==12){
					  	 table.addCell(createCell("年份", textfont,""));
					  	 table.addCell(createCell("月份", textfont,""));
					  	 table.addCell(createCell("销售额", textfont,""));
					  	 table.addCell(createCell("销项税额", textfont,""));
					  	 table.addCell(createCell("进项税额", textfont,""));
					  	 table.addCell(createCell("应纳税额", textfont,""));
					  	 table.addCell(createCell("应抵扣税额", textfont,""));
					  	 table.addCell(createCell("实缴税额", textfont,"")); 
					 }
					 table.addCell(createCell(czsnsEntity.getTpzNf()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzYf()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzJxse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzXxse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzJxse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzYnse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzYdkse()+"", textfont));
					 table.addCell(createCell(czsnsEntity.getTpzSjse()+"", textfont));
				 }else{
					 if(c==24){
					  	 table.addCell(createCell("年份", textfont,""));
					  	 table.addCell(createCell("月份", textfont,""));
					  	 table.addCell(createCell("销售额", textfont,""));
					  	 table.addCell(createCell("销项税额", textfont,""));
					  	 table.addCell(createCell("进项税额", textfont,""));
					  	 table.addCell(createCell("应纳税额", textfont,""));
					  	 table.addCell(createCell("应抵扣税额", textfont,""));
					  	 table.addCell(createCell("实缴税额", textfont,""));
					 }
					 if(czsnsEntity.getTpzYf() <= currentMonth){
						 table.addCell(createCell(czsnsEntity.getTpzNf()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzYf()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzJxse()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzXxse()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzJxse()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzYnse()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzYdkse()+"", textfont));
						 table.addCell(createCell(czsnsEntity.getTpzSjse()+"", textfont));
					 }
					 
				 }
			 }
		 }
	 }
	table.setSpacingAfter(20);
	document.add(table);
	
  	//增值税销售额分析
  	
  	PdfPTable zzsxsefxtable = createTable(6);
  	zzsxsefxtable.addCell(createCell("增值税销售额分析", keyfont,Element.ALIGN_LEFT,6,true));
  	zzsxsefxtable.addCell(createCell("月份", textfont,""));
  	zzsxsefxtable.addCell(createCell(second +"销售额", textfont,""));
  	zzsxsefxtable.addCell(createCell(first + "销售额", textfont,""));
  	zzsxsefxtable.addCell(createCell(second +"年同比"+first+"年", textfont,""));
  	zzsxsefxtable.addCell(createCell(current+"销售额", textfont,""));
  	zzsxsefxtable.addCell(createCell(first+"年同比"+current+"年", textfont,""));
  	
  	TaxPdfNsqdEntity zzsxsefx = taxPdfNsqdService.findZesxsefx();
  	BigDecimal tpzfP = null;
  	BigDecimal tpzfC = null;
  	BigDecimal tpzfN = null;
  	BigDecimal tpzfPc = null;
  	BigDecimal tpzfPn= null;
	 if(zzsxsefx !=null){
		 if(zzsxsefx.getZzsxsefxEntity() != null){
			 for(int c=0;c<zzsxsefx.getZzsxsefxEntity().size();c++){
				 ZzsxsefxEntity zzsxsefxEntity =zzsxsefx.getZzsxsefxEntity().get(c);
				if(zzsxsefxEntity!=null){
					tpzfP = zzsxsefxEntity.getTpzfP();
					tpzfC = zzsxsefxEntity.getTpzfC();
					tpzfN = zzsxsefxEntity.getTpzfN();
				  	tpzfPc = zzsxsefxEntity.getTpzfPc();
				  	tpzfPn= zzsxsefxEntity.getTpzfPn();
				  	tpzfP = tpzfP.add(tpzfP);
				  	tpzfC = tpzfC.add(tpzfC);
				  	tpzfN = tpzfN.add(tpzfN);
				  	tpzfPc = tpzfPc.add(tpzfPc);
				  	tpzfPn = tpzfPn.add(tpzfPn);
					if(zzsxsefxEntity.getTpzfYf() <= currentMonth){
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfYf()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfP()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfC()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfPc()+"%", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfN()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfPn()+"%", textfont));
					}else{
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfYf()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfP()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfC()+"", textfont));
						zzsxsefxtable.addCell(createCell(zzsxsefxEntity.getTpzfPc()+"%", textfont));
						zzsxsefxtable.addCell(createCell("--", textfont));
						zzsxsefxtable.addCell(createCell("--", textfont));
					}
				}
			 }
				zzsxsefxtable.addCell(createCell("合计", textfont));
				zzsxsefxtable.addCell(createCell(tpzfP+"", textfont));
				zzsxsefxtable.addCell(createCell(tpzfC+"", textfont));
				zzsxsefxtable.addCell(createCell(tpzfPc+"%", textfont));
				zzsxsefxtable.addCell(createCell(tpzfN+"", textfont));
				zzsxsefxtable.addCell(createCell(tpzfPn+"%", textfont));
		 }
		 zzsxsefxtable.setSpacingAfter(20);
	 }
	document.add(zzsxsefxtable);
	document.add(new Paragraph("\n"));
	document.add(new Paragraph("\n"));
	//document.newPage();
	taxPdfNsdjEntity=null;
	taxPdfNsdjEntity = taxPdfNsqdService.findZzsns();
	String zzsnsImgePath = chart.generatorZzsnsPath(taxPdfNsdjEntity,request);
    //增值销售收入图表
	Image zzsnslLineAndShap = null;
	if(osFlag == true){
		zzsnslLineAndShap = Image.getInstance(zzsnsImgePath+"\\"+"Zzsns.jpg");
	}else{
		zzsnslLineAndShap = Image.getInstance(zzsnsImgePath+"/"+"Zzsns.jpg");
	}
    document.add(zzsnslLineAndShap);
    
    document.add(new Paragraph("\n"));
    document.add(new Paragraph("\n"));
    document.newPage();
	//所得税月季纳税情况（A表）
	PdfPTable sdsyhbsqjTable = createTable(8);
	sdsyhbsqjTable.addCell(createCell("所得税月季纳税情况（A表）", keyfont,Element.ALIGN_LEFT,8,true));
	sdsyhbsqjTable.addCell(createCell("年份", textfont,""));
	sdsyhbsqjTable.addCell(createCell("月份", textfont,""));
	sdsyhbsqjTable.addCell(createCell("营业收入", textfont,""));
	sdsyhbsqjTable.addCell(createCell("利润总额", textfont,""));
	sdsyhbsqjTable.addCell(createCell("实际利润额", textfont,""));
	sdsyhbsqjTable.addCell(createCell("应纳税额", textfont,""));
	sdsyhbsqjTable.addCell(createCell("应抵扣减免税", textfont,""));
	sdsyhbsqjTable.addCell(createCell("实缴税额", textfont,""));
	
	TaxPdfNsqdEntity sdsnsqk = taxPdfNsqdService.findSdsnsqk();
	 if(sdsnsqk !=null){
		 if(sdsnsqk.getSdsnsqkEntity() != null){
			 for(int c=0;c<sdsnsqk.getSdsnsqkEntity().size();c++){
				 SdsnsqkEntity sdsnsqkEntity =sdsnsqk.getSdsnsqkEntity().get(c);
				 if(c<12){
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsNf()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYf()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYssr()+"", textfont));
					 /*sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsLrze()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsSjlre()+"", textfont));*/
					 sdsyhbsqjTable.addCell(createCell("暂无数据", textfont));
					 sdsyhbsqjTable.addCell(createCell("暂无数据", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYnse()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYdkjms()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsSjse()+"", textfont));
				 }else if(c>=12 && c <24){
					 if(c==12){
							sdsyhbsqjTable.addCell(createCell("年份", textfont,""));
							sdsyhbsqjTable.addCell(createCell("月份", textfont,""));
							sdsyhbsqjTable.addCell(createCell("营业收入", textfont,""));
							sdsyhbsqjTable.addCell(createCell("利润总额", textfont,""));
							sdsyhbsqjTable.addCell(createCell("实际利润额", textfont,""));
							sdsyhbsqjTable.addCell(createCell("应纳税额", textfont,""));
							sdsyhbsqjTable.addCell(createCell("应抵扣减免税", textfont,""));
							sdsyhbsqjTable.addCell(createCell("实缴税额", textfont,""));
					 }
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsNf()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYf()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYssr()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell("暂无数据", textfont));
					 sdsyhbsqjTable.addCell(createCell("暂无数据", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYnse()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYdkjms()+"", textfont));
					 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsSjse()+"", textfont));
				 }else{
					 if(c==24){
							sdsyhbsqjTable.addCell(createCell("年份", textfont,""));
							sdsyhbsqjTable.addCell(createCell("月份", textfont,""));
							sdsyhbsqjTable.addCell(createCell("营业收入", textfont,""));
							sdsyhbsqjTable.addCell(createCell("利润总额", textfont,""));
							sdsyhbsqjTable.addCell(createCell("实际利润额", textfont,""));
							sdsyhbsqjTable.addCell(createCell("应纳税额", textfont,""));
							sdsyhbsqjTable.addCell(createCell("应抵扣减免税", textfont,""));
							sdsyhbsqjTable.addCell(createCell("实缴税额", textfont,""));
					 }
					 if( sdsnsqkEntity.getTpsYf()<= currentMonth){
						 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsNf()+"", textfont));
						 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYf()+"", textfont));
						 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYssr()+"", textfont));
						 sdsyhbsqjTable.addCell(createCell("暂无数据", textfont));
						 sdsyhbsqjTable.addCell(createCell("暂无数据", textfont));
						 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYnse()+"", textfont));
						 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsYdkjms()+"", textfont));
						 sdsyhbsqjTable.addCell(createCell(sdsnsqkEntity.getTpsSjse()+"", textfont));
					 }
				 }
			 }
		 }
		 sdsyhbsqjTable.setSpacingAfter(20);
	 }
	
	 document.add(sdsyhbsqjTable);
	 document.newPage();
	 //所得税营业收入分析
	 PdfPTable sdsyysrfxTable = createTable(6);
		 sdsyysrfxTable.addCell(createCell("所得税营业收入分析", keyfont,Element.ALIGN_LEFT,6,true));
		 sdsyysrfxTable.addCell(createCell("月份", textfont,""));
		 sdsyysrfxTable.addCell(createCell(second+"营业额", textfont,""));
		 sdsyysrfxTable.addCell(createCell(first+"营业额", textfont,""));
		 sdsyysrfxTable.addCell(createCell(second+"年同比"+first+"年", textfont,""));
		 sdsyysrfxTable.addCell(createCell(current+"营业额", textfont,""));
		 sdsyysrfxTable.addCell(createCell(first+"年同比"+current+"年", textfont,""));
	 
    	 TaxPdfNsqdEntity sdsyyesrfx = taxPdfNsqdService.findSdsyyesrfx();
    	 if(sdsyyesrfx !=null){
    		 if(sdsyyesrfx.getSdsyyesrfxEntity() != null){
    			 	BigDecimal tpsfP = null;
    			 	BigDecimal tpsfC = null;
    			 	BigDecimal tpsfN = null;
    			 	BigDecimal tpsfPc = null;
    			 	BigDecimal tpsfPn = null;
    			 for(int c=0;c<sdsyyesrfx.getSdsyyesrfxEntity().size();c++){
    				 SdsyyesrfxEntity sdsyyesrfxEntity =sdsyyesrfx.getSdsyyesrfxEntity().get(c);
    				 if(sdsyyesrfxEntity != null){
    						tpsfP = sdsyyesrfxEntity.getTpsfP();
    						tpsfC = sdsyyesrfxEntity.getTpsfC();
    						tpsfN = sdsyyesrfxEntity.getTpsfN();
    	    			 	tpsfPc = sdsyyesrfxEntity.getTpsfPc();
    	    			 	tpsfPn = sdsyyesrfxEntity.getTpsfPn();
    	    			 	tpsfP = tpsfP.add(tpsfP);
    	    			 	tpsfC = tpsfC.add(tpsfC);
    	    			 	tpsfN = tpsfN.add(tpsfN);
    	    			 	tpsfPc = tpsfPc.add(tpsfPc);
    	    			 	tpsfPn = tpsfPn.add(tpsfPn);
    						if(sdsyyesrfxEntity.getTpsfYf() <= currentMonth){
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfYf()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfP()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfC()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfPc()+"%", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfN()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfPn()+"%", textfont));
    						}else{
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfYf()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfP()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfC()+"", textfont));
    							sdsyysrfxTable.addCell(createCell(sdsyyesrfxEntity.getTpsfPc()+"%", textfont));
    							sdsyysrfxTable.addCell(createCell("--", textfont));
    							sdsyysrfxTable.addCell(createCell("--", textfont));
    						}
    				 }
    			 }
    			 sdsyysrfxTable.addCell(createCell("合计", textfont));
    			 sdsyysrfxTable.addCell(createCell(tpsfP+"", textfont));
    			 sdsyysrfxTable.addCell(createCell(tpsfC+"", textfont));
    			 sdsyysrfxTable.addCell(createCell(tpsfPc+"%", textfont));
    			 sdsyysrfxTable.addCell(createCell(tpsfN+"", textfont));
    			 sdsyysrfxTable.addCell(createCell(tpsfPn+"%", textfont));
    		 }
    		 sdsyysrfxTable.setSpacingAfter(20);
    	 }
    	 document.add(sdsyysrfxTable);
    	 document.add(new Paragraph("\n"));
    	 document.add(new Paragraph("\n"));
    	 document.newPage();
     	taxPdfNsdjEntity=null;
 		taxPdfNsdjEntity = taxPdfNsqdService.findSdsnsqk();
 		String sdsseImgePath = chart.generatorSdssePath(taxPdfNsdjEntity,request);
  		Image lineAndShapPieGif = null;
  		 if(osFlag == true){
  			 lineAndShapPieGif = Image.getInstance(sdsseImgePath+"\\"+"Sdsse.jpg");
  		 }else{
  			 lineAndShapPieGif = Image.getInstance(sdsseImgePath+"/"+"Sdsse.jpg");
  		 }
         document.add(lineAndShapPieGif);
         document.add(new Paragraph("\n"));
         document.add(new Paragraph("\n"));
         document.newPage();
    	 //城市维护建设税综合清单
    	 PdfPTable cswfhssTable = createTable(5);
    	 cswfhssTable.addCell(createCell("城市维护建设税综合清单", keyfont,Element.ALIGN_LEFT,5,true));
    	 cswfhssTable.addCell(createCell("年份", textfont,""));
    	 cswfhssTable.addCell(createCell("月份 ", textfont,""));
    	 cswfhssTable.addCell(createCell("应纳所得额 ", textfont,""));
    	 cswfhssTable.addCell(createCell("应纳税额", textfont,""));
    	 cswfhssTable.addCell(createCell("实缴税额", textfont,""));
    	 TaxPdfNsqdEntity cswfjss = taxPdfNsqdService.findJtnsqd("10109");//城市维护建设税综合清单
    	 if(cswfjss !=null){
    		 if(cswfjss.getJtnsqdEntity() != null){
    			 for(int c=0;c<cswfjss.getJtnsqdEntity().size();c++){
    				 JtnsqdEntity jtnsqdEntity =cswfjss.getJtnsqdEntity().get(c);
    				 if(jtnsqdEntity != null){
    					 if(c<12){
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 /*cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));*/
    						 cswfhssTable.addCell(createCell("暂无数据", textfont));
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else if(c>=12 && c <24){
    						 if(c==12){
    							 cswfhssTable.addCell(createCell("年份", textfont,""));
    							 cswfhssTable.addCell(createCell("月份 ", textfont,""));
    							 cswfhssTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 cswfhssTable.addCell(createCell("应纳税额", textfont,""));
    							 cswfhssTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 cswfhssTable.addCell(createCell("暂无数据", textfont));
    						 //cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else{
    						 if(c==24){
    							 cswfhssTable.addCell(createCell("年份", textfont,""));
    							 cswfhssTable.addCell(createCell("月份 ", textfont,""));
    							 cswfhssTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 cswfhssTable.addCell(createCell("应纳税额", textfont,""));
    							 cswfhssTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 if(jtnsqdEntity.getTpjYf()<=currentMonth){
    							 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    							 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    							 cswfhssTable.addCell(createCell("暂无数据", textfont));
    							 //cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    							 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    							 cswfhssTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    						 }
    					 }
    				 }
    			 }
    		 }
    		 cswfhssTable.setSpacingAfter(20);
    	 }
    	 document.add(cswfhssTable);
    	 //印花税综合清单
    	 PdfPTable yhsTable = createTable(5);
    	 yhsTable.addCell(createCell("印花税综合清单", keyfont,Element.ALIGN_LEFT,5,true));
    	 yhsTable.addCell(createCell("年份", textfont,""));
    	 yhsTable.addCell(createCell("月份 ", textfont,""));
    	 yhsTable.addCell(createCell("应纳所得额 ", textfont,""));
    	 yhsTable.addCell(createCell("应纳税额", textfont,""));
    	 yhsTable.addCell(createCell("实缴税额", textfont,""));
    	 TaxPdfNsqdEntity yhs = taxPdfNsqdService.findJtnsqd("10111");//印花税综合清单
    	 if(yhs !=null){
    		 if(yhs.getJtnsqdEntity() != null){
    			 for(int c=0;c<yhs.getJtnsqdEntity().size();c++){
    				 JtnsqdEntity jtnsqdEntity =yhs.getJtnsqdEntity().get(c);
    				 if(jtnsqdEntity != null){
    					 if(c<12){
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 yhsTable.addCell(createCell("暂无数据", textfont));
    						 //yhsTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else if(c>=12 && c <24){
    						 if(c==12){
    							 yhsTable.addCell(createCell("年份", textfont,""));
    							 yhsTable.addCell(createCell("月份 ", textfont,""));
    							 yhsTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 yhsTable.addCell(createCell("应纳税额", textfont,""));
    							 yhsTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 yhsTable.addCell(createCell("暂无数据", textfont));
    						 //yhsTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 yhsTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else{
    						 if(c==24){
    							 yhsTable.addCell(createCell("年份", textfont,""));
    							 yhsTable.addCell(createCell("月份 ", textfont,""));
    							 yhsTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 yhsTable.addCell(createCell("应纳税额", textfont,""));
    							 yhsTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 if( jtnsqdEntity.getTpjYf() <= currentMonth){
    							 yhsTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    							 yhsTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    							 yhsTable.addCell(createCell("暂无数据", textfont));
    							 //yhsTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    							 yhsTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    							 yhsTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    						 }
    					 }
    				 }
    			 }
    		 }
    		 yhsTable.setSpacingAfter(20);
    	 }
    	 document.add(yhsTable);
    	 
    	 //城镇土地使用税综合清单
    	 PdfPTable cstdsysTable = createTable(5);
    	 cstdsysTable.addCell(createCell("城镇土地使用税综合清单", keyfont,Element.ALIGN_LEFT,5,true));
    	 cstdsysTable.addCell(createCell("年份", textfont,""));
    	 cstdsysTable.addCell(createCell("月份 ", textfont,""));
    	 cstdsysTable.addCell(createCell("应纳所得额 ", textfont,""));
    	 cstdsysTable.addCell(createCell("应纳税额", textfont,""));
    	 cstdsysTable.addCell(createCell("实缴税额", textfont,""));
    	 TaxPdfNsqdEntity cztdsys = taxPdfNsqdService.findJtnsqd("10112");//城镇土地使用税综合清单
    	 if(cztdsys !=null){
    		 if(cztdsys.getJtnsqdEntity() != null){
    			 for(int c=0;c<cztdsys.getJtnsqdEntity().size();c++){
    				 JtnsqdEntity jtnsqdEntity =cztdsys.getJtnsqdEntity().get(c);
    				 if(jtnsqdEntity != null){
    					 if(c<12){
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 cstdsysTable.addCell(createCell("暂无数据", textfont));
    						 //cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else if(c>=12 && c <24){
    						 if(c==12){
    							 cstdsysTable.addCell(createCell("年份", textfont,""));
    							 cstdsysTable.addCell(createCell("月份 ", textfont,""));
    							 cstdsysTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 cstdsysTable.addCell(createCell("应纳税额", textfont,""));
    							 cstdsysTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 cstdsysTable.addCell(createCell("暂无数据", textfont));
    						 //cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else{
    						 if(c==24){
    							 cstdsysTable.addCell(createCell("年份", textfont,""));
    							 cstdsysTable.addCell(createCell("月份 ", textfont,""));
    							 cstdsysTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 cstdsysTable.addCell(createCell("应纳税额", textfont,""));
    							 cstdsysTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 if(jtnsqdEntity.getTpjYf() <= currentMonth){
    							 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    							 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    							 cstdsysTable.addCell(createCell("暂无数据", textfont));
    							 //cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    							 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    							 cstdsysTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    						 }
    					 }
    				 }
    			 }
    		 }
    		 cstdsysTable.setSpacingAfter(20);
    	 }
    	 document.add(cstdsysTable);
    	 //教育费附加综合清单
    	 PdfPTable jyfjTable = createTable(5);
    	 jyfjTable.addCell(createCell("教育费附加综合清单", keyfont,Element.ALIGN_LEFT,5,true));
    	 jyfjTable.addCell(createCell("年份", textfont,""));
    	 jyfjTable.addCell(createCell("月份 ", textfont,""));
    	 jyfjTable.addCell(createCell("应纳所得额 ", textfont,""));
    	 jyfjTable.addCell(createCell("应纳税额", textfont,""));
    	 jyfjTable.addCell(createCell("实缴税额", textfont,""));
    	 TaxPdfNsqdEntity jyfj = taxPdfNsqdService.findJtnsqd("30203");//教育费附加综合清单
    	 if(jyfj !=null){
    		 if(jyfj.getJtnsqdEntity() != null){
    			 for(int c=0;c<jyfj.getJtnsqdEntity().size();c++){
    				 JtnsqdEntity jtnsqdEntity =jyfj.getJtnsqdEntity().get(c);
    				 if(jtnsqdEntity != null){
    					 if(c<12){
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 jyfjTable.addCell(createCell("暂无数据", textfont));
    						 //jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else if(c>=12 && c <24){
    						 if(c==12){
    							 jyfjTable.addCell(createCell("年份", textfont,""));
    							 jyfjTable.addCell(createCell("月份 ", textfont,""));
    							 jyfjTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 jyfjTable.addCell(createCell("应纳税额", textfont,""));
    							 jyfjTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 jyfjTable.addCell(createCell("暂无数据", textfont));
    						 //jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else{
    						 if(c==24){
    							 jyfjTable.addCell(createCell("年份", textfont,""));
    							 jyfjTable.addCell(createCell("月份 ", textfont,""));
    							 jyfjTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 jyfjTable.addCell(createCell("应纳税额", textfont,""));
    							 jyfjTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 if(jtnsqdEntity.getTpjYf() <= currentMonth){
    							 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    							 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    							 jyfjTable.addCell(createCell("暂无数据", textfont));
    							 //jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    							 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    							 jyfjTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    						 }
    					 }
    				 }
    			 }
    		 }
    		 jyfjTable.setSpacingAfter(20);
    	 }
    	 document.add(jyfjTable);
    	 //地方教育附加综合清单
    	 PdfPTable dfjyfjTable = createTable(5);
    	 dfjyfjTable.addCell(createCell("地方教育附加综合清单", keyfont,Element.ALIGN_LEFT,5,true));
    	 dfjyfjTable.addCell(createCell("年份", textfont,""));
    	 dfjyfjTable.addCell(createCell("月份 ", textfont,""));
    	 dfjyfjTable.addCell(createCell("应纳所得额 ", textfont,""));
    	 dfjyfjTable.addCell(createCell("应纳税额", textfont,""));
    	 dfjyfjTable.addCell(createCell("实缴税额", textfont,""));
    	 TaxPdfNsqdEntity dfjyfj = taxPdfNsqdService.findJtnsqd("30216");//地方教育附加综合清单
    	 if(dfjyfj !=null){
    		 if(dfjyfj.getJtnsqdEntity() != null){
    			 for(int c=0;c<dfjyfj.getJtnsqdEntity().size();c++){
    				 JtnsqdEntity jtnsqdEntity =dfjyfj.getJtnsqdEntity().get(c);
    				 if(jtnsqdEntity != null){
    					 if(c<12){
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 dfjyfjTable.addCell(createCell("暂无数据", textfont));
    						 //dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else if(c>=12 && c <24){
    						 if(c==12){
    							 dfjyfjTable.addCell(createCell("年份", textfont,""));
    							 dfjyfjTable.addCell(createCell("月份 ", textfont,""));
    							 dfjyfjTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 dfjyfjTable.addCell(createCell("应纳税额", textfont,""));
    							 dfjyfjTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    						 dfjyfjTable.addCell(createCell("暂无数据", textfont));
    						 //dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    						 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    					 }else{
    						 if(c==24){
    							 dfjyfjTable.addCell(createCell("年份", textfont,""));
    							 dfjyfjTable.addCell(createCell("月份 ", textfont,""));
    							 dfjyfjTable.addCell(createCell("应纳所得额 ", textfont,""));
    							 dfjyfjTable.addCell(createCell("应纳税额", textfont,""));
    							 dfjyfjTable.addCell(createCell("实缴税额", textfont,""));
    						 }
    						 if(jtnsqdEntity.getTpjYf() <= currentMonth){
    							 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
    							 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
    							 dfjyfjTable.addCell(createCell("暂无数据", textfont));
    							 //dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
    							 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
    							 dfjyfjTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
    						 }
    					 }
    				 }
    			 }
    		 }
    		 dfjyfjTable.setSpacingAfter(20);
    	 }
    	 document.add(dfjyfjTable);
    	 //水利建设专项收入综合清单
    	 PdfPTable sljszxsrTable = createTable(5);
    	 sljszxsrTable.addCell(createCell("水利建设专项收入综合清单", keyfont,Element.ALIGN_LEFT,5,true));
    	 sljszxsrTable.addCell(createCell("年份", textfont,""));
    	 sljszxsrTable.addCell(createCell("月份 ", textfont,""));
    	 sljszxsrTable.addCell(createCell("应纳所得额 ", textfont,""));
    	 sljszxsrTable.addCell(createCell("应纳税额", textfont,""));
    	 sljszxsrTable.addCell(createCell("实缴税额", textfont,""));    	 
    	 TaxPdfNsqdEntity sljszxsr = taxPdfNsqdService.findJtnsqd("30221");//水利建设专项收入综合清单
    	 if(sljszxsr !=null){
    		 if(sljszxsr.getJtnsqdEntity() != null){
    			int sljszxsrSize = sljszxsr.getJtnsqdEntity().size();
    			 for(int c=0;c<sljszxsrSize;c++){
    				 JtnsqdEntity jtnsqdEntity =sljszxsr.getJtnsqdEntity().get(c);
    				 if(jtnsqdEntity != null){
	    				 if(c<12){
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
	    					 sljszxsrTable.addCell(createCell("暂无数据", textfont));
	    					 //sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
	    				 }else if(c>=12 && c <24){
	    					 if(c==12){
	    				    	 sljszxsrTable.addCell(createCell("年份", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("月份 ", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("应纳所得额 ", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("应纳税额", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("实缴税额", textfont,"")); 
	    					 }
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
	    					 sljszxsrTable.addCell(createCell("暂无数据", textfont));
	    					 //sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
	    					 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
	    				 }else{
	    					 if(c==24){
	    				    	 sljszxsrTable.addCell(createCell("年份", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("月份 ", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("应纳所得额 ", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("应纳税额", textfont,""));
	    				    	 sljszxsrTable.addCell(createCell("实缴税额", textfont,"")); 
	    					 }
	    					 if(jtnsqdEntity.getTpjYf() <= currentMonth){
	    						 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjNf()+"", textfont));
	    						 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYf()+"", textfont));
	    						 sljszxsrTable.addCell(createCell("暂无数据", textfont));
	    						 //sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYnsde()+"", textfont));
	    						 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjYnse()+"", textfont));
	    						 sljszxsrTable.addCell(createCell(jtnsqdEntity.getTpjSjse()+"", textfont));
	    					 }
	    				 }
    				 }
    			 }
    		 }
    		 sljszxsrTable.setSpacingAfter(20);
    	 }
    	 document.add(sljszxsrTable);
    	 
			TaxPdfCbxxEntity taxPdfCbxxEntity = taxPdfCbxxService.findByDjxh(params);
			PdfPTable zcfzTable = createTable(8);
			zcfzTable.addCell(createCell("资产负债表", keyfont,Element.ALIGN_LEFT,8,true));
			zcfzTable.addCell(createCell("资产", textfont,""));
			zcfzTable.addCell(createCell("行次 ", textfont,""));
			zcfzTable.addCell(createCell("期末余额 ", textfont,""));
			zcfzTable.addCell(createCell("期初余额 ", textfont,""));
			zcfzTable.addCell(createCell("负债和所有者权益", textfont,""));
			zcfzTable.addCell(createCell("行次", textfont,"")); 
			zcfzTable.addCell(createCell("期末余额 ", textfont,""));
			zcfzTable.addCell(createCell("期初余额 ", textfont,""));
			
			//利润表
			PdfPTable lrbTable = createTable(4);
			lrbTable.addCell(createCell("利润表", keyfont,Element.ALIGN_LEFT,4,true));
			lrbTable.addCell(createCell("项目", textfont,""));
			lrbTable.addCell(createCell("行次 ", textfont,""));
			lrbTable.addCell(createCell("本年累计金额 ", textfont,""));
			lrbTable.addCell(createCell("本月金额 ", textfont,""));
			if(taxPdfCbxxEntity != null){
	       	 //资产负债表
				String zc = null;
				int row = 0;
				BigDecimal zqcye = null;
				BigDecimal zqmye = null;
				String qy = null;
				BigDecimal qqcye = null;
				BigDecimal qqmye = null;
				if(taxPdfCbxxEntity.getTaxCbxxListEntity() != null){
					for(int a=0;a<taxPdfCbxxEntity.getTaxCbxxListEntity().size();a++){
						row = a;
						if(taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity() != null){
							zc=  taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity().getTtzyZcxmmc();
							zqcye = taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity().getTtzyNcyeZc();
							zqcye = zqcye.add(zqcye);
							zqmye = taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity().getTtzyQmyeZc();
							zqmye = zqmye.add(zqmye);
							qy = taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity().getTtzyQyxmmc();
							qqcye = taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity().getTtzyNcyeQy();
							qqcye = qqcye.add(qqcye);
							qqmye = taxPdfCbxxEntity.getTaxCbxxListEntity().get(a).getTaxZcfzbYbqykjEntity().getTtzyQmyeQy();
							qqmye = qqmye.add(qqmye);
						}
							zcfzTable.addCell(createCell(zc, textfont));
							zcfzTable.addCell(createCell(a+"", textfont));
							zcfzTable.addCell(createCell(zqcye+"", textfont));
							zcfzTable.addCell(createCell(zqmye+"", textfont));
							zcfzTable.addCell(createCell(qy, textfont));
							zcfzTable.addCell(createCell(a+"", textfont));
							zcfzTable.addCell(createCell(qqcye+"", textfont));
							zcfzTable.addCell(createCell(qqmye+"", textfont));
							if(a==12 ||a==24){
								zcfzTable.addCell(createCell("资产总计", textfont));
								zcfzTable.addCell(createCell(a+1+"", textfont));
								zcfzTable.addCell(createCell(zqcye+"", textfont));
								zcfzTable.addCell(createCell(zqmye+"", textfont));
								zcfzTable.addCell(createCell("负债和所有者权益（或股东权益）总计", textfont));
								zcfzTable.addCell(createCell(a+1+"", textfont));
								zcfzTable.addCell(createCell(qqcye+"", textfont));
								zcfzTable.addCell(createCell(qqmye+"", textfont));
								zqcye = null;
								zqmye = null;
								qqcye = null;
								qqmye = null;
								zcfzTable.addCell(createCell("资产", textfont,""));
								zcfzTable.addCell(createCell("行次 ", textfont,""));
								zcfzTable.addCell(createCell("期末余额 ", textfont,""));
								zcfzTable.addCell(createCell("期初余额 ", textfont,""));
								zcfzTable.addCell(createCell("负债和所有者权益", textfont,""));
								zcfzTable.addCell(createCell("行次", textfont,"")); 
								zcfzTable.addCell(createCell("期末余额 ", textfont,""));
								zcfzTable.addCell(createCell("期初余额 ", textfont,""));
							}
					}
					
					zcfzTable.addCell(createCell("资产总计", textfont));
					zcfzTable.addCell(createCell(row+1+"", textfont));
					zcfzTable.addCell(createCell(zqcye+"", textfont));
					zcfzTable.addCell(createCell(zqmye+"", textfont));
					zcfzTable.addCell(createCell("负债和所有者权益（或股东权益）总计", textfont));
					zcfzTable.addCell(createCell(row+1+"", textfont));
					zcfzTable.addCell(createCell(qqcye+"", textfont));
					zcfzTable.addCell(createCell(qqmye+"", textfont));
				}else{
					zcfzTable.addCell(createCell("资产总计", textfont));
					zcfzTable.addCell(createCell("", textfont));
					zcfzTable.addCell(createCell("", textfont));
					zcfzTable.addCell(createCell("", textfont));
					zcfzTable.addCell(createCell("负债和所有者权益（或股东权益）总计", textfont));
					zcfzTable.addCell(createCell("", textfont));
					zcfzTable.addCell(createCell("", textfont));
					zcfzTable.addCell(createCell("", textfont));
				}
	    		zcfzTable.setSpacingAfter(20);
	    		
	    		
	    		document.add(zcfzTable);
    			 String xm = null;
   	    		 BigDecimal blljje = null;
   	    		 BigDecimal byje = null;
   	    		 if(taxPdfCbxxEntity.getTaxCbxxListEntity() != null){
   	    			 String row1 = null;
   	    			 for(int b=0;b<taxPdfCbxxEntity.getTaxCbxxListEntity().size();b++){
   	    				row1 = b+"";
   	    				 if(taxPdfCbxxEntity.getTaxCbxxListEntity().get(b).getTaxLrbXqykjzdEntity() != null){
   	    					 blljje=  taxPdfCbxxEntity.getTaxCbxxListEntity().get(b).getTaxLrbXqykjzdEntity().getTtlxBnljje();
   	    					 byje = taxPdfCbxxEntity.getTaxCbxxListEntity().get(b).getTaxLrbXqykjzdEntity().getTtlxByje();
   	    				 }
   	    				if(b==12 ||b==24){
   	    					lrbTable.addCell(createCell("项目", textfont,""));
   	    					lrbTable.addCell(createCell("行次 ", textfont,""));
   	    					lrbTable.addCell(createCell("本年累计金额 ", textfont,""));
   	    					lrbTable.addCell(createCell("本月金额 ", textfont,""));
						}
   	    				lrbTable.addCell(createCell(xm, textfont));
   	    				lrbTable.addCell(createCell(row1, textfont));
   	    				lrbTable.addCell(createCell(blljje+"", textfont));
   	    				lrbTable.addCell(createCell(byje+"", textfont));
   	    			 }
   	    		 }else{
	    				lrbTable.addCell(createCell("", textfont));
	    				lrbTable.addCell(createCell("", textfont));
	    				lrbTable.addCell(createCell("", textfont));
	    				lrbTable.addCell(createCell("", textfont));
   	    		 }
	    		lrbTable.setSpacingAfter(20);
	    		document.add(lrbTable);
	    	} else {
				zcfzTable.addCell(createCell("资产总计", textfont));
				zcfzTable.addCell(createCell("暂无数据", textfont));
				zcfzTable.addCell(createCell("暂无数据", textfont));
				zcfzTable.addCell(createCell("暂无数据", textfont));
				zcfzTable.addCell(createCell("负债和所有者权益（或股东权益）总计", textfont));
				zcfzTable.addCell(createCell("暂无数据", textfont));
				zcfzTable.addCell(createCell("暂无数据", textfont));
				zcfzTable.addCell(createCell("暂无数据", textfont));
	    		zcfzTable.setSpacingAfter(20);
	    		document.add(zcfzTable);
	    		
				lrbTable.addCell(createCell("暂无数据", textfont));
				lrbTable.addCell(createCell("暂无数据", textfont));
				lrbTable.addCell(createCell("暂无数据", textfont));
				lrbTable.addCell(createCell("暂无数据", textfont));
	    		lrbTable.setSpacingAfter(20);
	    		document.add(lrbTable);
	    	}

        document.close();
     }    
        
}  