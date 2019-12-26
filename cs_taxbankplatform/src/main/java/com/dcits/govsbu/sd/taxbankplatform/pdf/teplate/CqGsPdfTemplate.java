/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：CqGsPdfTemplate.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-5-26下午下午2:53:242:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.pdf.teplate;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfSsyhzcEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfWfwzEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqSwupjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.service.CqPdfService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @versions:1.0 
 * @filename：CqGsPdfTemplate.java
 * @Company:dfwyBank
 * @Created: 2017-5-26下午下午2:53:242:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName CqGsPdfTemplate
 */
public class CqGsPdfTemplate {

	private static Logger log = Logger.getLogger(CqGsPdfTemplate.class);
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
    private static CqPdfService cqPdfService = null;
    private static BaseFont bfChinese;    
    int maxWidth = 520;
    static{    
        try {    
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);    
            headfont = new Font(bfChinese, 24, Font.BOLD);    
            keyfont = new Font(bfChinese, 12, Font.BOLD);    
            textfont = new Font(bfChinese, 12, Font.NORMAL);
            ac = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-applicationContext.xml");
            cqPdfService = (CqPdfService)ac.getBean("CqPdfService");
        } catch (Exception e) {             
            e.printStackTrace();    
        }     
    }    
    
    public PdfPCell createCell(String value,com.lowagie.text.Font font,int align,int colspan){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(align);        
         cell.setColspan(colspan); 
         cell.setBackgroundColor(BaseColor.WHITE);
        return cell;    
    } 
    
    public static PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(align);        
         cell.setColspan(colspan); 
         cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
         cell.setPhrase(new Paragraph(value,font)); 
        return cell;    
    }
    //合并后居中空四格显示段落
    public static PdfPCell createCell(String value,Font font,int align,int colspan){    
        PdfPCell cell = new PdfPCell();    
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
        cell.setHorizontalAlignment(align);
        cell.setBorder(0);
        cell.setIndent(10);
        cell.setColspan(colspan); 
        cell.setPhrase(new Paragraph(value,font));
       return cell;    
   }
    //合并后靠右显示
    public static PdfPCell createCell(String value,Font font,int colspan){    
        PdfPCell cell = new PdfPCell();    
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        cell.setColspan(colspan); 
        cell.setPhrase(new Paragraph(value,font));
       return cell;    
   }
     public static PdfPCell createCell(String value,Font font){    
         PdfPCell cell = new PdfPCell();    
         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         cell.setPhrase(new Paragraph(value,font));   
        return cell;    
    } 
     
     /**
      *     
      * @param file
      */
     public void generatePDF(File file, String id) throws Exception {   
     		Document document = new Document(PageSize.A4);
     		PdfWriter.getInstance(document, new FileOutputStream(file));
     		document.open();
     		

      	 	Calendar now = Calendar.getInstance();
     		int currentMonth = now.get(Calendar.MONTH);
     		String month = String.valueOf(currentMonth+1);
     		if(currentMonth<10){
     			month = "0"+month;
     		}
     		String current = String.valueOf(now.get(Calendar.YEAR));
     		int currentdate = now.get(Calendar.DATE);
     		String date = null;
     		if(currentdate<10){
     			date = "0"+String.valueOf(currentdate);
     		}else{
     			date = String.valueOf(currentdate);
     		}
     		
     		now.add(Calendar.YEAR,-1);
     		String first = String.valueOf(now.get(Calendar.YEAR));//去年
     		now.add(Calendar.YEAR,-1);
     		String second = String.valueOf(now.get(Calendar.YEAR));//前一年
     		String nsrsbh = null;
     		nsrsbh = cqPdfService.findNsrsbhById(id);
     		Map<String,Object> map = cqPdfService.findFpnameById(id);
     		String fpName = String.valueOf(map.get("fpName"));
     		String orgName = String.valueOf(map.get("orgName"));
     		String sqxh = null;
     		if(nsrsbh!=null){
     			try {
     				//获取最新的批次号
     			    sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
         			//调接口查找数据
         			/*Map<String, Object> dataObject = new HashMap<String, Object>();
         			dataObject.put("NSRSBH", nsrsbh);
         			//生成申请序号，查询税务数据，防止序号重复
     				String sqxhSw = IDGenerate.generateMessageID();
         			SendCqXdsq.sendGetTaxRequestToTax(dataObject,sqxhSw);*/
 				} catch (Exception e) {
 					log.info("=========================重庆点击下载报告调取税务接口出现错误");
 					e.printStackTrace();
 				}
     		}else{
     			try {
         			//调接口查找数据
         			/*Map<String, Object> dataObject = new HashMap<String, Object>();
         			dataObject.put("NSRSBH", nsrsbh);
         			//生成申请序号，查询税务数据，防止序号重复
     				String sqxhSw = IDGenerate.generateMessageID();
         			SendCqXdsq.sendGetTaxRequestToTax(dataObject,sqxhSw);
         			
         			//停20秒查找数据
         			Thread.sleep(25000);*/
         			
         			sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
     			} catch (Exception e) {
     				log.info("=========================重庆点击下载报告调取税务接口出现错误");
     				e.printStackTrace();
     			}
     		}
     		Paragraph title = new Paragraph("小微企业"+fpName+"业务纳税证明", headfont);
//     	    title.setIndentationLeft(115);
     		title.setAlignment(Element.ALIGN_CENTER);
     		document.add(title);
     		document.add(new Paragraph("\n"));
     		PdfPCell cell = null;
     		CqPdfEntity cqPdfEntity = null;
     		if(sqxh != null){
     			cqPdfEntity = cqPdfService.findCqQyjcxxBySqxh(sqxh);
     		}
     		String nsrmc      = null;
     		String clrq       = null;
     		String shtyxydm   = null;
     		String zzjgdm     = null;
     		String scjydzlxdh = null;
     		String hymx       = null;
     		String zgshs      = null;
     		String nsrzg      = null;
     		String sfxwxlqy   = null;
     		String zzsl       = null;
     		String sdsl       = null;
     		String fddbr      = null;
     		String fddbrsfzjhm= null;
     		String fddbryddh  = null;
     		String cwfzrxm    = null;
     		String cwfzrsfzjhm= null;
     		String cwfzryddhhm= null;
     		String scjydz = null;
     		if(cqPdfEntity != null){
     			nsrmc = cqPdfEntity.getNsrmc();
     			clrq = cqPdfEntity.getNd();
     			shtyxydm = cqPdfEntity.getShxydm();
     			zzjgdm = cqPdfEntity.getZzjg_dm();
     			scjydzlxdh = cqPdfEntity.getScjydlxdh();
     			hymx = cqPdfEntity.getHymx();
     			zgshs = cqPdfEntity.getZgswskfj_dm();
     			nsrzg = cqPdfEntity.getNsrzg_dm();
     			
     			sfxwxlqy = cqPdfEntity.getSfxxwlqy();
     			if("Y".equals(sfxwxlqy)){
     				sfxwxlqy="是";
     			}else if("N".equals(sfxwxlqy)){
     				sfxwxlqy="否";
     			}else{
     				sfxwxlqy="";
     			}
     			zzsl = cqPdfEntity.getZzsl();
     			sdsl = cqPdfEntity.getSdsl();
     			fddbr = cqPdfEntity.getFddbrhfzrhyzxm();
     			fddbrsfzjhm = cqPdfEntity.getFddbrhfzrhyzsfzjhm();
     			fddbryddh = cqPdfEntity.getFddbrhfzrhyzyddh();
     			cwfzrxm = cqPdfEntity.getCwfzrxm();
     			cwfzrsfzjhm = cqPdfEntity.getCwfzrsfzjhm();
     			cwfzryddhhm = cqPdfEntity.getCwfzryddh();
     			scjydz = cqPdfEntity.getScjydz();
     			 		
     		}
     		//申请企业名称
     		PdfPTable qymctable = new PdfPTable(2);
     		qymctable.addCell(createCell("申请企业名称",keyfont));
     		qymctable.addCell(createCell(nsrmc,textfont));
     		//纳税人识别号
     		PdfPTable nsrsbhtable = new PdfPTable(2);
     		nsrsbhtable.addCell(createCell("纳税人识别号",keyfont));
     		nsrsbhtable.addCell(createCell(nsrsbh,textfont));
     		if(nsrmc == null ){
     			nsrmc = "XXXX";
     		}
     		if(fpName == null ){
     			fpName = "XXXX";
     		}
     		if(orgName == null ){
     			orgName = "XXXX";
     		}
     		PdfPTable jcxx = new PdfPTable(1);
     		PdfPTable b = new PdfPTable(1);
     		String str = nsrmc +"因申请办理"+orgName+"小微企业"+fpName+"授信业务的需要"+
     				"，特授权重庆市国家税务局通过税银互动服务平台向"+orgName+"提供涉税数据。";
     		//jcxxtable.addCell(createCell(str, textfont,Element.ALIGN_LEFT,4));
     		b.addCell(createCell(str, textfont,Element.ALIGN_LEFT,1));
     		String sj = current+"年"+month+"月"+date+"日";
     		//jcxxtable.addCell(createCell(sj, textfont,4));
     		b.addCell(createCell(sj, textfont,1));
     		jcxx.addCell(b);
     		document.add(qymctable);
     		document.add(nsrsbhtable);
     		
     		document.add(jcxx);
     		//工商登记信息
     		/*PdfPTable gsdjTable = new PdfPTable(2);
     		gsdjTable.addCell(createCell("工商登记信息", keyfont,Element.ALIGN_LEFT,2,true));
     		gsdjTable.addCell(createCell("企业名称", textfont));
     		gsdjTable.addCell(createCell(nsrmc, textfont));
     		gsdjTable.addCell(createCell("生产经营地址 ", textfont));
     		gsdjTable.addCell(createCell(scjydz, textfont));
     		gsdjTable.addCell(createCell("成立年限 ", textfont));
     		gsdjTable.addCell(createCell(clrq, textfont));
     		gsdjTable.addCell(createCell("社会统一信用代码", textfont));
     		gsdjTable.addCell(createCell(shtyxydm, textfont));
     		gsdjTable.addCell(createCell("纳税人识别号", textfont));
     		gsdjTable.addCell(createCell(nsrsbh, textfont));
     		gsdjTable.addCell(createCell("组织机构代码", textfont));
     		gsdjTable.addCell(createCell(zzjgdm, textfont));
     		gsdjTable.addCell(createCell("生产经营地联系电话", textfont));
     		gsdjTable.addCell(createCell(scjydzlxdh, textfont));
     		gsdjTable.addCell(createCell("行业明细", textfont));
     		gsdjTable.addCell(createCell(hymx, textfont));
     		document.add(gsdjTable);*/
     		//税务登记信息
     		PdfPTable swdjTable = new PdfPTable(2);

     		
     		swdjTable.addCell(createCell("税务登记信息", keyfont,Element.ALIGN_LEFT,2,true));
     		swdjTable.addCell(createCell("企业名称", textfont));
     		swdjTable.addCell(createCell(nsrmc, textfont));
     		swdjTable.addCell(createCell("成立年限 ", textfont));
     		swdjTable.addCell(createCell(clrq, textfont));
     		/*swdjTable.addCell(createCell("主管税务所(科)", textfont));
     		swdjTable.addCell(createCell(zgshs, textfont));*/
     		swdjTable.addCell(createCell("纳税人资格 ", textfont));
     		swdjTable.addCell(createCell(nsrzg, textfont));
     		/*swdjTable.addCell(createCell("是否小型微利企业", textfont));
     		swdjTable.addCell(createCell(sfxwxlqy, textfont));*/
     		swdjTable.addCell(createCell("增值税率（包括征收率）", textfont));
     		swdjTable.addCell(createCell(zzsl, textfont));
     		swdjTable.addCell(createCell("所得税率", textfont));
     		swdjTable.addCell(createCell(sdsl, textfont));
     		swdjTable.addCell(createCell("法定代表人", textfont));
     		/*swdjTable.addCell(createCell(fddbr, textfont));
     		swdjTable.addCell(createCell("法定代表人身份证件号码", textfont));*/
     		swdjTable.addCell(createCell(fddbrsfzjhm, textfont));
     		swdjTable.addCell(createCell("法定代表人移动电话", textfont));
     		swdjTable.addCell(createCell(fddbryddh, textfont));
     		swdjTable.addCell(createCell("财务负责人姓名 ", textfont));
     		swdjTable.addCell(createCell(cwfzrxm, textfont));
     		/*swdjTable.addCell(createCell("财务负责人身份证件号码", textfont));
     		swdjTable.addCell(createCell(cwfzrsfzjhm, textfont));*/
     		swdjTable.addCell(createCell("财务负责人移动电话", textfont));
     		swdjTable.addCell(createCell(cwfzryddhhm, textfont));
     		document.add(swdjTable);
     		Map<String,Object> parameters = new HashMap<String,Object>();
     		parameters.put("sqxh", sqxh);
     		parameters.put("starttime", second);
     		parameters.put("endtime", current);
     		
     		//税务评级信息
     		PdfPTable swpjTable = new PdfPTable(2);
     		swpjTable.addCell(createCell("税务评级信息", keyfont,Element.ALIGN_LEFT,2,true));
     		List<CqSwupjxxEntity> cqSwupjxxList = null;
     		if(sqxh != null){
     			cqSwupjxxList = cqPdfService.findCqSwpjxx(parameters);
     			if(cqSwupjxxList.size() > 0){
     				for(int i=0;i<cqSwupjxxList.size();i++){
     					swpjTable.addCell(createCell(cqSwupjxxList.get(i).getSwxypjsj(), textfont));
     					swpjTable.addCell(createCell(cqSwupjxxList.get(i).getSwxypj(), textfont));
     				}
     			}else {
     				swpjTable.addCell(createCell("暂无数据", textfont));
     				swpjTable.addCell(createCell("暂无数据", textfont));
     			}
     		}else{
 				swpjTable.addCell(createCell("暂无数据", textfont));
 				swpjTable.addCell(createCell("暂无数据", textfont));
     		}
     		document.add(swpjTable);
     		//纳税情况（单位：元）
     		PdfPTable nsqktable = new PdfPTable(8);
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("纳税情况（单位：元）", keyfont)));
     		cell.setColspan(8); 
     		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("纳税期限", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("税种", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("销项税额", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("进项税额", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("应纳税额", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("减免税额", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("实际纳税额", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph("合计", textfont))); 
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     		nsqktable.addCell(cell);
     		
     		
     		if(sqxh != null){
     			for(int i=0;i<3;i++){
     				if(i==0){
     					parameters.put("sqxh", sqxh);
     					parameters.put("starttime", second);
     					parameters.put("endtime", second);
     					parameters.put("yfstarttime", "01");
     					parameters.put("yfendtime", "12");
     					
     					cqPdfEntity = cqPdfService.findCqNsqd(parameters);
     					if(cqPdfEntity != null){
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(second+"-01至"+second+"-12", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						
     						BigDecimal sjnse = new BigDecimal(cqPdfEntity.getSdssjnsed());
     						BigDecimal sdsjmtse = new BigDecimal(cqPdfEntity.getSdsjmtse());
     						BigDecimal sdstotal = sjnse.add(sdsjmtse);
     						BigDecimal zzssjnse = new BigDecimal(cqPdfEntity.getZzssjnsed());
     						BigDecimal zzsjmtse = new BigDecimal(cqPdfEntity.getZzsjmtse());
     						BigDecimal zzstotal = zzssjnse.add(zzsjmtse);
     						BigDecimal toatl = sdstotal.add(zzstotal);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("所得税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sjnse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("1234567", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("增值税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzssjnse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     					}else{
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("qwertyuio", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("所得税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("增值税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     					}
     				}else if(i==1){
     					parameters.put("sqxh", sqxh);
     					parameters.put("starttime", first);
     					parameters.put("endtime", first);
     					parameters.put("yfstarttime", "01");
     					parameters.put("yfendtime", "12");
     					
     					cqPdfEntity = cqPdfService.findCqNsqd(parameters);
     					if(cqPdfEntity != null){
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(first+"-01至"+first+"-12", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						
     						BigDecimal sjnse = new BigDecimal(cqPdfEntity.getSdssjnsed());
     						BigDecimal sdsjmtse = new BigDecimal(cqPdfEntity.getSdsjmtse());
     						BigDecimal sdstotal = sjnse.add(sdsjmtse);
     						BigDecimal zzssjnse = new BigDecimal(cqPdfEntity.getZzssjnsed());
     						BigDecimal zzsjmtse = new BigDecimal(cqPdfEntity.getZzsjmtse());
     						BigDecimal zzstotal = zzssjnse.add(zzsjmtse);
     						BigDecimal toatl = sdstotal.add(zzstotal);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("所得税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sjnse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("增值税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzssjnse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     					}else{
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("所得税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("增值税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     					}
     				}else{
     					parameters.put("sqxh", sqxh);
     					parameters.put("starttime", current);
     					parameters.put("endtime", current);
     					parameters.put("yfstarttime", "01");
     					parameters.put("yfendtime", month);
     					cqPdfEntity = cqPdfService.findCqNsqd(parameters);
     					if(cqPdfEntity != null){
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(current+"-01至"+current+"-12", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						
     						BigDecimal sjnse = new BigDecimal(cqPdfEntity.getSdssjnsed());
     						BigDecimal sdsjmtse = new BigDecimal(cqPdfEntity.getSdsjmtse());
     						BigDecimal sdstotal = sjnse.add(sdsjmtse);
     						BigDecimal zzssjnse = new BigDecimal(cqPdfEntity.getZzssjnsed());
     						BigDecimal zzsjmtse = new BigDecimal(cqPdfEntity.getZzsjmtse());
     						BigDecimal zzstotal = zzssjnse.add(zzsjmtse);
     						BigDecimal toatl = sdstotal.add(zzstotal);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("所得税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sjnse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(sdstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("增值税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzssjnse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzsjmtse.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph(new BigDecimal(Double.toString(zzstotal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     					}else{
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("qwertyuio", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("所得税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						cell.setRowspan(2);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("增值税", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     						cell = new PdfPCell(new Paragraph(
     								new Paragraph("--", textfont)));  
     						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     						nsqktable.addCell(cell);
     					}
     				}
     			}
     		}else{
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				cell.setRowspan(2);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("所得税", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
				cell = new PdfPCell(new Paragraph(
							new Paragraph("--", textfont)));  
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setRowspan(2);
				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("增值税", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 				cell = new PdfPCell(new Paragraph(
 						new Paragraph("--", textfont)));  
 				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
 				nsqktable.addCell(cell);
 			
     		}
     		
     		
     		document.add(nsqktable);
     		//累计欠税额（单位：元）
     		String ljqse  = "";
     		ljqse = cqPdfService.findCqLjqsxx(sqxh);
     		int size = ljqse.length();
     		if(size >= 8){
     			ljqse = ljqse.substring(0, size-4);
     		}
     		PdfPTable ljqsTable = new PdfPTable(2);
     		ljqsTable.addCell(createCell("累计欠税额（单位：元）", keyfont,Element.ALIGN_LEFT,2,true));
     		ljqsTable.addCell(createCell("截至申请前一日 ", textfont));
     		ljqsTable.addCell(createCell(ljqse, textfont));
     		document.add(ljqsTable);
     		//税收优惠政策
     		PdfPTable ssyhzcTable = new PdfPTable(3);
     		ssyhzcTable.addCell(createCell("税收优惠政策", keyfont,Element.ALIGN_LEFT,3,true));
     		ssyhzcTable.addCell(createCell("免税期起止 ", textfont));
     		ssyhzcTable.addCell(createCell("减征额度", textfont));
     		ssyhzcTable.addCell(createCell("减征幅度", textfont));
     		parameters = new HashMap<String ,Object>();
     		parameters.put("sqxh", sqxh);
     		parameters.put("starttime", second+"-"+month+"-"+"01");
     		parameters.put("endtime", current+"-"+month+"-"+date);
     		List<CqPdfSsyhzcEntity> CqPdfSsyhzcList = null;
     		if(sqxh != null){
     			CqPdfSsyhzcList = cqPdfService.findCqSsyhzc(parameters);
     			if(CqPdfSsyhzcList.size() > 0){
     				for(int i=0;i<CqPdfSsyhzcList.size();i++){
     					ssyhzcTable.addCell(createCell(CqPdfSsyhzcList.get(i).getJmqxq()+"至"+CqPdfSsyhzcList.get(i).getJmqxz(), textfont));
     					ssyhzcTable.addCell(createCell(CqPdfSsyhzcList.get(i).getJzed(), textfont));
     					ssyhzcTable.addCell(createCell(CqPdfSsyhzcList.get(i).getJzfd(), textfont));
     				}
     			}else{
     				ssyhzcTable.addCell(createCell("暂无数据 ", textfont));
     				ssyhzcTable.addCell(createCell("暂无数据", textfont));
     				ssyhzcTable.addCell(createCell("暂无数据", textfont));
     			}
     		}else{
 				ssyhzcTable.addCell(createCell("暂无数据 ", textfont));
 				ssyhzcTable.addCell(createCell("暂无数据", textfont));
 				ssyhzcTable.addCell(createCell("暂无数据", textfont));
 			}
     		
     		document.add(ssyhzcTable);
     		//违法违章明细
     		PdfPTable wfwzmxTable = new PdfPTable(5);
     		wfwzmxTable.addCell(createCell("违法违章明细", keyfont,Element.ALIGN_LEFT,5,true));
     		wfwzmxTable.addCell(createCell("登记日期", textfont));
     		wfwzmxTable.addCell(createCell("主要违法违章事实", textfont));
     		wfwzmxTable.addCell(createCell("违法违章类型", textfont));
     		wfwzmxTable.addCell(createCell("违法违章状态", textfont));
     		wfwzmxTable.addCell(createCell("立案日期", textfont));
     		parameters = new HashMap<String ,Object>();
     		parameters.put("sqxh", sqxh);
     		parameters.put("starttime", second+"-"+month+"-"+"01");
     		parameters.put("endtime", current+"-"+month+"-"+date);
     		List<CqPdfWfwzEntity> cqPdfWfwzList = null;
     		if(sqxh != null){
     			cqPdfWfwzList = cqPdfService.findCqWfwzmx(parameters);
     			if(cqPdfWfwzList.size() > 0){
     				for(int i=0;i<cqPdfWfwzList.size();i++){
     					wfwzmxTable.addCell(createCell(cqPdfWfwzList.get(i).getDjrq(), textfont));
     					wfwzmxTable.addCell(createCell(cqPdfWfwzList.get(i).getWfss(), textfont));
     					wfwzmxTable.addCell(createCell(cqPdfWfwzList.get(i).getWfwzlx(), textfont));
     					wfwzmxTable.addCell(createCell(cqPdfWfwzList.get(i).getWfxwclztdm(), textfont));
     					wfwzmxTable.addCell(createCell(cqPdfWfwzList.get(i).getLarq(), textfont));
     				}
     			}else{
     				wfwzmxTable.addCell(createCell("暂无数据", textfont));
     				wfwzmxTable.addCell(createCell("暂无数据", textfont));
     				wfwzmxTable.addCell(createCell("暂无数据", textfont));
     				wfwzmxTable.addCell(createCell("暂无数据", textfont));
     				wfwzmxTable.addCell(createCell("暂无数据", textfont));
     			}
     		}else{
 				wfwzmxTable.addCell(createCell("暂无数据", textfont));
 				wfwzmxTable.addCell(createCell("暂无数据", textfont));
 				wfwzmxTable.addCell(createCell("暂无数据", textfont));
 				wfwzmxTable.addCell(createCell("暂无数据", textfont));
 				wfwzmxTable.addCell(createCell("暂无数据", textfont));
 			}
     		document.add(wfwzmxTable);
     		//主管税务部门意见
     		PdfPTable zgswbmyjTable = new PdfPTable(1);
     		str = "主管税务部门意见";
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph(str, keyfont)));  
     		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
     		zgswbmyjTable.addCell(cell);
     		str = "重庆市国家税务局意见";
     		cell = new PdfPCell(new Paragraph(
     				new Paragraph(str, textfont)));  
     		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
     		zgswbmyjTable.addCell(cell);
     		
     		document.add(zgswbmyjTable);
     		PdfPTable e = new PdfPTable(1);
     		PdfPTable d = new PdfPTable(1);
     		str = "上述数据为重庆市国家税务局按规定原则提供的自身采集的原始数据，仅用于银税服务的参考。";
     		d.addCell(createCell(str, textfont,Element.ALIGN_LEFT,1));
     		d.addCell(createCell("重庆市国家税务局", textfont,1));
     		e.addCell(d);
     		document.add(e);
     		document.close();
     	}
     
 	public static double add(double v1, double v2)
 	{
 		BigDecimal b1 = new BigDecimal(Double.toString(v1));
 		BigDecimal b2 = new BigDecimal(Double.toString(v2));
 		return b1.add(b2).doubleValue();
 	}
     
}
