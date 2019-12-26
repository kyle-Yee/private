package com.dcits.govsbu.sd.taxbankplatform.pdf.teplate;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.dcits.govsbu.sd.taxbankplatform.cqsyyhtotax.SendCqXdsq;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqGsSwqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfSsyhzcEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfWfwzEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqSwupjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.service.CqPdfService;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.SpringBeanUtil;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
	    
public class CqPdfTemplate {
	private static Logger log = Logger.getLogger(CqPdfTemplate.class);
	
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
//    private static ApplicationContext ac = null;
    private static CqPdfService cqPdfService = null;
    private static BaseFont bfChinese;    
    int maxWidth = 520;
    static{    
        try {    
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);    
            headfont = new Font(bfChinese, 24, Font.BOLD);    
            keyfont = new Font(bfChinese, 12, Font.BOLD);    
            textfont = new Font(bfChinese, 12, Font.NORMAL);
//            ac = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-applicationContext.xml");
            cqPdfService = (CqPdfService)SpringBeanUtil.getInstance().getBeanById("CqPdfService");
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
    /*
     * 无数据是合并多列 
     */
    public static PdfPCell createCell(String value,Font font,int align,int colspan,String status){    
        PdfPCell cell = new PdfPCell();    
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);    
        cell.setHorizontalAlignment(align);        
        cell.setColspan(colspan); 
        //cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
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
     
     
 	//添加水印
	public static void addMark(PdfStamper pdfStamper,int pageSize) throws DocumentException, IOException{
 		BaseFont markFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
 		for(int i =1;i <= pageSize;i++){
		 PdfContentByte under = pdfStamper.getUnderContent(i);
		 under.beginText();  
         under.setColorFill(BaseColor.LIGHT_GRAY);  
         under.setFontAndSize(markFont, 12);     
         String waterMarkName="重庆税银互动服务平台";       
         
         float left=150f;    
         for (int n=0;n<2;n++){
         float buttom=100f;
         for(int m=0;m<7;m++){
         	
         under.setTextMatrix(left, buttom); //设置左边距，下边距     
         int rise = 0;//开始文字距下方位置
         for (int k = 0; k <waterMarkName.length(); k++) {  
         under.setTextRise(rise); //设置倾斜角度 
         char c = waterMarkName.charAt(k);  
         under.showText(c + " ");  
         rise += 5;  
         }  
        // left+=200f;
         buttom+=100f;        
         }
         left+=200f;
         }
      
         under.endText();
         under.stroke();
 		}
 		
 		pdfStamper.close();// 关闭   
 		
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
    		SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    		Calendar start = Calendar.getInstance();    
    		start.add(Calendar.MONTH, -1);
    		//设置为1号,当前日期既为本月第一天 
    		start.set(Calendar.DAY_OF_MONTH,1);
    		String startTime = format1.format(start.getTime());
    		System.out.println(first);
    		//获取当前月最后一天
    		SimpleDateFormat format2 =  new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    		Calendar end = Calendar.getInstance(); 
    		end.add(Calendar.MONTH, -1);
    		end.set(Calendar.DAY_OF_MONTH, 
    			end.getActualMaximum(Calendar.DAY_OF_MONTH));  
    		String endTime = format2.format(end.getTime());
    		Map<String,Object> requstMap = new HashMap<String,Object>();
    		requstMap.put("nsrsbh", nsrsbh);
    		requstMap.put("startTime", startTime);
    		requstMap.put("endTime", endTime);
    		
    		int status = cqPdfService.findStatusByNsrsbh(requstMap);
    		/*
    		 * 根据返回的状态来判断是否需要调用税务数据接口
    		 * 如果status大于0 表中存在上个月的最新数据 此时不需要调取税务局数据接口
    		 * 否则调用税务数据接口
    		 * 
    		 */
    		if(status>0){
				//获取最新的批次号
				sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
    		}else{
    			//if(nsrsbh!=null){
				try {
					//获取最新的批次号
					sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
					//调接口查找数据
					if(sqxh!=null){
						Map<String, Object> dataObject = new HashMap<String, Object>();
						dataObject.put("NSRSBH", nsrsbh);
						//生成申请序号，查询税务数据，防止序号重复
						String sqxhSw = IDGenerate.generateMessageID();
						SendCqXdsq.sendGetTaxRequestToTaxJH(dataObject,sqxhSw);
					}else{
    					Map<String, Object> dataObject = new HashMap<String, Object>();
    					dataObject.put("NSRSBH", nsrsbh);
    					//生成申请序号，查询税务数据，防止序号重复
    					String sqxhSw = IDGenerate.generateMessageID();
    					SendCqXdsq.sendGetTaxRequestToTaxJH(dataObject,sqxhSw);
    					
    					//停20秒查找数据
    					Thread.sleep(25000);
    					
    					sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
					}
				} catch (Exception e) {
					log.info("=========================重庆点击下载报告调取税务接口出现错误");
					e.printStackTrace();
				}
    			/*}else{
    				try {
    					//调接口查找数据
    					Map<String, Object> dataObject = new HashMap<String, Object>();
    					dataObject.put("NSRSBH", nsrsbh);
    					//生成申请序号，查询税务数据，防止序号重复
    					String sqxhSw = IDGenerate.generateMessageID();
    					SendCqXdsq.sendGetTaxRequestToTaxJH(dataObject,sqxhSw);
    					
    					//停20秒查找数据
    					Thread.sleep(25000);
    					
    					sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
    				} catch (Exception e) {
    					log.info("=========================重庆点击下载报告调取税务接口出现错误");
    					e.printStackTrace();
    				}
    			}*/
    		}
    		
    		Paragraph title = new Paragraph("小微企业"+fpName+"业务纳税证明", headfont);
//    	    title.setIndentationLeft(115);
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
        		if(zzsl !=null ){
        			int size = zzsl.length();
        			if(size >= 8){
        				zzsl = zzsl.substring(0, size-4);
        			}
        		}else{
        			zzsl = "税局查无数据";
        		}
    			sdsl = cqPdfEntity.getSdsl();
        		if(sdsl !=null ){
        			int size = sdsl.length();
        			if(size >= 8){
        				sdsl = sdsl.substring(0, size-4);
        			}
        		}else{
        			sdsl = "税局查无数据";
        		}
    			fddbr = cqPdfEntity.getFddbrhfzrhyzxm();
    			fddbrsfzjhm = cqPdfEntity.getFddbrhfzrhyzsfzjhm();
    			fddbryddh = cqPdfEntity.getFddbrhfzrhyzyddh();
    			cwfzrxm = cqPdfEntity.getCwfzrxm();
    			cwfzrsfzjhm = cqPdfEntity.getCwfzrsfzjhm();
    			cwfzryddhhm = cqPdfEntity.getCwfzryddh();
    			scjydz = cqPdfEntity.getScjydz();
    			 		
    		}
    		if(clrq == null){
    			clrq = "税局查无数据";
    		}
    		if(shtyxydm == null){
    			shtyxydm = "税局查无数据";
    		}
    		if(zzjgdm == null){
    			zzjgdm = "税局查无数据";
    		}
    		if(scjydzlxdh == null){
    			scjydzlxdh = "税局查无数据";
    		}
    		if(hymx == null){
    			hymx = "税局查无数据";
    		}
    		if(zgshs == null){
    			zgshs = "税局查无数据";
    		}
    		if(nsrzg == null){
    			nsrzg = "税局查无数据";
    		}
    		if(sfxwxlqy == null){
    			sfxwxlqy = "税局查无数据";
    		}
    		if(fddbr == null){
    			fddbr = "税局查无数据";
    		}
    		if(fddbrsfzjhm == null){
    			fddbrsfzjhm = "税局查无数据";
    		}
    		if(fddbryddh == null){
    			fddbryddh = "税局查无数据";
    		}
    		if(cwfzrxm == null){
    			cwfzrxm = "税局查无数据";
    		}
    		if(cwfzrsfzjhm == null){
    			cwfzrsfzjhm = "税局查无数据";
    		}
    		if(cwfzryddhhm == null){
    			cwfzryddhhm = "税局查无数据";
    		}
    		if(scjydz == null){
    			scjydz = "税局查无数据";
    		}
    		// 创建需要填入文档的元素
    		PdfPTable jcxxtable = new PdfPTable(4);
    		jcxxtable.addCell(createCell("申请企业名称",keyfont));
    		jcxxtable.addCell(createCell(nsrmc,textfont));
    		jcxxtable.addCell(createCell("纳税人识别号",keyfont));
    		jcxxtable.addCell(createCell(nsrsbh,textfont));
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
    		document.add(jcxxtable);
    		
    		document.add(jcxx);
    		//工商登记信息
    		PdfPTable gsdjTable = new PdfPTable(2);
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
    		document.add(gsdjTable);
    		//税务登记信息
    		PdfPTable swdjTable = new PdfPTable(2);
    		swdjTable.addCell(createCell("税务登记信息", keyfont,Element.ALIGN_LEFT,2,true));
    		swdjTable.addCell(createCell("主管税务所(科)", textfont));
    		swdjTable.addCell(createCell(zgshs, textfont));
    		swdjTable.addCell(createCell("纳税人资格 ", textfont));
    		swdjTable.addCell(createCell(nsrzg, textfont));
    		swdjTable.addCell(createCell("是否小型微利企业", textfont));
    		swdjTable.addCell(createCell(sfxwxlqy, textfont));
    		swdjTable.addCell(createCell("增值税率（包括征收率）", textfont));
    		swdjTable.addCell(createCell(zzsl, textfont));
    		swdjTable.addCell(createCell("所得税率", textfont));
    		swdjTable.addCell(createCell(sdsl, textfont));
    		swdjTable.addCell(createCell("法定代表人", textfont));
    		swdjTable.addCell(createCell(fddbr, textfont));
    		swdjTable.addCell(createCell("法定代表人身份证件号码", textfont));
    		swdjTable.addCell(createCell(fddbrsfzjhm, textfont));
    		swdjTable.addCell(createCell("法定代表人移动电话", textfont));
    		swdjTable.addCell(createCell(fddbryddh, textfont));
    		swdjTable.addCell(createCell("财务负责人姓名 ", textfont));
    		swdjTable.addCell(createCell(cwfzrxm, textfont));
    		swdjTable.addCell(createCell("财务负责人身份证件号码", textfont));
    		swdjTable.addCell(createCell(cwfzrsfzjhm, textfont));
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
    				swpjTable.addCell(createCell("税局查无数据 ", textfont,Element.ALIGN_CENTER,2,""));
    				/*swpjTable.addCell(createCell("税局查无数据 ", textfont));
    				swpjTable.addCell(createCell("税局查无数据 ", textfont));*/
    			}
    		}else{
    			swpjTable.addCell(createCell("税局查无数据 ", textfont,Element.ALIGN_CENTER,2,""));
				/*swpjTable.addCell(createCell("税局查无数据 ", textfont));
				swpjTable.addCell(createCell("税局查无数据 ", textfont));*/
    		}
    		document.add(swpjTable);
    		//纳税情况（单位：元）
    		PdfPTable nsqktable = new PdfPTable(5);
    		cell = new PdfPCell(new Paragraph(
    				new Paragraph("纳税情况（单位：元）", keyfont)));
    		cell.setColspan(5); 
    		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		nsqktable.addCell(cell);
    		cell = new PdfPCell(new Paragraph(
    				new Paragraph("税款缴纳年度", textfont))); 
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		nsqktable.addCell(cell);
    		
    		cell = new PdfPCell(new Paragraph(
    				new Paragraph("税种名称", textfont))); 
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		nsqktable.addCell(cell);
    		
    		cell = new PdfPCell(new Paragraph(
    				new Paragraph("实际纳税额", textfont))); 
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		nsqktable.addCell(cell);
    		
    		cell = new PdfPCell(new Paragraph(
    				new Paragraph("减、免、退税额", textfont))); 
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		nsqktable.addCell(cell);
    		
    		cell = new PdfPCell(new Paragraph(
    				new Paragraph("应纳税额合计", textfont))); 
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		nsqktable.addCell(cell);
    		parameters.put("nsrsbh", nsrsbh);
    		List <CqGsSwqdEntity> list = cqPdfService.findNsqdDataByNsrsbh(parameters);
    		if(list != null && list.size()>0){
    			for(int i=0;i<list.size();i++){
    				parameters.put("sqxh", sqxh);
    				parameters.put("starttime", second);
    				parameters.put("endtime", second);
    				parameters.put("yfstarttime", "01");
    				parameters.put("yfendtime", "12");
    				CqGsSwqdEntity cqGsSwqdEntity = list.get(i);
    				
    				
    				cqPdfEntity = cqPdfService.findCqNsqd(parameters);
    				if(cqGsSwqdEntity != null){
    					//查询日期起
    					String cxrqq =  cqGsSwqdEntity.getCxrqq();
    					
    					if(cxrqq!=null){
    						int size = cxrqq.length();
    						if(size >= 8){
    							cxrqq = cxrqq.substring(0, size-10);
    						}
    					}else{
    						cxrqq = "税局查无数据";
    					}
    					//查询日期止
    					String cxrqz =  cqGsSwqdEntity.getCxrqz();
    					if(cxrqz!=null){
    						int size = cxrqz.length();
    						if(size >= 8){
    							cxrqz = cxrqz.substring(0, size-10);
    						}
    					}else{
    						cxrqz = "税局查无数据";
    					}
    					//所得税实际纳税额
    					BigDecimal sdsjnse = cqGsSwqdEntity.getQysdsse();
    					//所得税减，面，退税额
    					BigDecimal sdsjmse = cqGsSwqdEntity.getQysdsjmse();
    					BigDecimal sdsTotal = sdsjnse.add(sdsjmse);
    					
    					//增值税实际纳税额
    					BigDecimal zzsjnse = cqGsSwqdEntity.getZzsse();
    					//增值税减，面，退税额
    					BigDecimal zzsjmse = cqGsSwqdEntity.getZzsjmse();
    					BigDecimal zzsTotal = zzsjnse.add(zzsjmse);
    					
    					BigDecimal total = sdsTotal.add(zzsTotal);
    					
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(cxrqq+"至"+cxrqz, textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					cell.setRowspan(3);
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
    							new Paragraph(sdsjnse.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(sdsjmse.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(sdsTotal.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph("增值税", textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(zzsjnse.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(zzsjmse.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(zzsTotal.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph("合计", textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					nsqktable.addCell(cell);
    					cell = new PdfPCell(new Paragraph(
    							new Paragraph(total.toPlainString(), textfont)));  
    					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    					cell.setColspan(3);
    					nsqktable.addCell(cell);
    				}else{
    					nsqktable.addCell(createCell("税局查无数据", textfont,Element.ALIGN_CENTER,5,""));
    					/*cell = new PdfPCell(new Paragraph(
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
    								new Paragraph("合计", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph("--", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setColspan(3);
    						nsqktable.addCell(cell);*/
    				}
    				/*if(i==0){
    				}else if(i==1){
    					parameters.put("sqxh", sqxh);
    					parameters.put("starttime", first);
    					parameters.put("endtime", first);
    					parameters.put("yfstarttime", "01");
    					parameters.put("yfendtime", "12");
    					CqGsSwqdEntity cqGsSwqdEntity = list.get(i);
    					cqPdfEntity = cqPdfService.findCqNsqd(parameters);
    					if(cqGsSwqdEntity != null){
    						
    						//查询日期起
    						String cxrqq =  cqGsSwqdEntity.getCxrqq();
    						
    						if(cxrqq!=null){
    							int size = cxrqq.length();
    							if(size >= 8){
    								cxrqq = cxrqq.substring(0, size-10);
    							}
    						}else{
    							cxrqq = "税局查无数据";
    						}
    						//查询日期止
    						String cxrqz =  cqGsSwqdEntity.getCxrqz();
    						if(cxrqz!=null){
    							int size = cxrqz.length();
    							if(size >= 8){
    								cxrqz = cxrqz.substring(0, size-10);
    							}
    						}else{
    							cxrqz = "税局查无数据";
    						}
    						//所得税实际纳税额
    						BigDecimal sdsjnse = cqGsSwqdEntity.getQysdsse();
    						//所得税减，面，退税额
    						BigDecimal sdsjmse = cqGsSwqdEntity.getQysdsjmse();
    						BigDecimal sdsTotal  = sdsjnse.add(sdsjmse);
    						//增值税实际纳税额
    						BigDecimal zzsjnse = cqGsSwqdEntity.getZzsse();
    						//增值税减，面，退税额
    						BigDecimal zzsjmse = cqGsSwqdEntity.getZzsjmse();
    						BigDecimal zzsTotal  = zzsjnse.add(zzsjmse);
    						
    						//纳税合计
    						BigDecimal total = sdsTotal.add(zzsTotal);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(cxrqq+"至"+cxrqz, textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setRowspan(3);
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
    								new Paragraph(sdsjnse.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(sdsjmse.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(sdsTotal.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph("增值税", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(zzsjnse.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(zzsjmse.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(zzsTotal.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph("合计", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(total.toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setColspan(3);
    						nsqktable.addCell(cell);
    					}else{
    						nsqktable.addCell(createCell("税局查无数据", textfont,Element.ALIGN_CENTER,5,""));
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
    								new Paragraph("合计", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph("--", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setColspan(3);
    						nsqktable.addCell(cell);
    					}
    				}*//*else{
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
    						cell.setRowspan(3);
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
    								new Paragraph("合计", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph(new BigDecimal(Double.toString(toatl.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue())).toPlainString(), textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setColspan(3);
    						nsqktable.addCell(cell);
    					}else{
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph("", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setRowspan(3);
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
    								new Paragraph("合计", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						nsqktable.addCell(cell);
    						cell = new PdfPCell(new Paragraph(
    								new Paragraph("--", textfont)));  
    						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    						cell.setColspan(3);
    						nsqktable.addCell(cell);
    					}
    				}*/
    			}
    		}else{
    			
    			nsqktable.addCell(createCell("税局查无数据", textfont,Element.ALIGN_CENTER,5,""));
				/*cell = new PdfPCell(new Paragraph(
						new Paragraph("", textfont)));  
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setRowspan(3);
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
						new Paragraph("合计", textfont)));  
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				nsqktable.addCell(cell);
				cell = new PdfPCell(new Paragraph(
						new Paragraph("--", textfont)));  
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setColspan(3);
				nsqktable.addCell(cell);*/
			
    		}
    		
    		
    		document.add(nsqktable);
    		//累计欠税额（单位：元）
    		String ljqse  = "";
    		ljqse = cqPdfService.findCqLjqsxx(sqxh);
    		if(ljqse!=null){
    			
    			Double e = Double.parseDouble(ljqse);
    			if(e<1){
    				ljqse = "税局查无数据";
    			}
    			/*int size = ljqse.length();
    			if(size >= 8){
    				ljqse = ljqse.substring(0, size-4);
    			}*/
    		}else{
    			ljqse = "税局查无数据";
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
    					String jzed = CqPdfSsyhzcList.get(i).getJzed();
    		       		if(jzed !=null ){
    	        			int size = jzed.length();
    	        			if(size >= 8){
    	        				jzed = jzed.substring(0, size-4);
    	        			}
    	        		}else{
    	        			jzed = "税局查无数据";
    	        		}
    					String jzfd = CqPdfSsyhzcList.get(i).getJzfd();
    		       		if(jzfd !=null ){
    	        			int size = jzfd.length();
    	        			if(size >= 8){
    	        				jzfd = jzfd.substring(0, size-4);
    	        			}
    	        		}else{
    	        			jzfd = "税局查无数据";
    	        		}
    					ssyhzcTable.addCell(createCell(CqPdfSsyhzcList.get(i).getJmqxq()+"至"+CqPdfSsyhzcList.get(i).getJmqxz(), textfont));
    					ssyhzcTable.addCell(createCell(jzed, textfont));
    					ssyhzcTable.addCell(createCell(jzfd, textfont));
    				}
    			}else{
    				ssyhzcTable.addCell(createCell("税局查无数据", textfont,Element.ALIGN_CENTER,3,""));
    				/*ssyhzcTable.addCell(createCell("税局查无数据 ", textfont));
    				ssyhzcTable.addCell(createCell("税局查无数据 ", textfont));
    				ssyhzcTable.addCell(createCell("税局查无数据 ", textfont));*/
    			}
    		}else{
    			ssyhzcTable.addCell(createCell("税收优惠政策", textfont,Element.ALIGN_CENTER,3,""));
				/*ssyhzcTable.addCell(createCell("税局查无数据 ", textfont));
				ssyhzcTable.addCell(createCell("税局查无数据 ", textfont));
				ssyhzcTable.addCell(createCell("税局查无数据 ", textfont));*/
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
    		parameters.put("nsrsbh", nsrsbh);
    		List<CqPdfWfwzEntity> cqPdfWfwzList = null;
    		if(sqxh != null){
    			cqPdfWfwzList = cqPdfService.findCqWfwzmx(parameters);
    			if(cqPdfWfwzList.size() > 0){
    				for(int i=0;i<cqPdfWfwzList.size();i++){
    					String djrq = cqPdfWfwzList.get(i).getDjrq();
    		       		if(djrq ==null ){
    		       			djrq = "税局查无数据";
    	        		}
    					String wfss = cqPdfWfwzList.get(i).getWfss();
    		       		if(wfss ==null ){
    		       			wfss = "税局查无数据";
    	        		}
    					String wfwzlx = cqPdfWfwzList.get(i).getWfwzlx();
    		       		if(wfwzlx ==null ){
    		       			wfwzlx = "税局查无数据";
    	        		}
    					String wfxwclztdm = cqPdfWfwzList.get(i).getWfxwclztdm();
    		       		if(wfxwclztdm ==null ){
    		       			wfxwclztdm = "税局查无数据";
    	        		}
    					String larq = cqPdfWfwzList.get(i).getLarq();
    		       		if(larq ==null ){
    		       			larq = "税局查无数据";
    	        		}
    					wfwzmxTable.addCell(createCell(djrq, textfont));
    					wfwzmxTable.addCell(createCell(wfss, textfont));
    					wfwzmxTable.addCell(createCell(wfwzlx, textfont));
    					wfwzmxTable.addCell(createCell(wfxwclztdm, textfont));
    					wfwzmxTable.addCell(createCell(larq, textfont));
    				}
    			}else{
    				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont,Element.ALIGN_CENTER,5,""));
    				/*wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
    				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
    				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
    				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
    				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));*/
    			}
    		}else{
    			
    			wfwzmxTable.addCell(createCell("税局查无数据 ", textfont,Element.ALIGN_CENTER,5,""));
				/*wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));
				wfwzmxTable.addCell(createCell("税局查无数据 ", textfont));*/
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
    
    /*public static void main(String[] args) throws Exception { 
    	CqPdfTemplate("C:/Users/Administrator/Desktop/itext-demo.pdf"); 
    	System.out.println("it'is ok !");
    }*/
	public static double add(double v1, double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
}
