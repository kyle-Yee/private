package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.cqsyyhtotax.SendCqXdsq;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqGsSwqdEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfSsyhzcEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqPdfWfwzEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.model.CqSwupjxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.pdf.service.CqPdfService;
import com.dcits.govsbu.sd.taxbankplatform.pdf.teplate.CqPdfTemplate;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.SpringBeanUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class AuthorizationrecordPDFTeplate {
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
    int maxWidth = 520;    
    
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
	public static void addMark(PdfStamper pdfStamper,int pageSize,String orgName) throws DocumentException, IOException{
 		BaseFont markFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
 		for(int i =1;i <= pageSize;i++){
		 PdfContentByte under = pdfStamper.getUnderContent(i);
		 under.beginText();  
         under.setColorFill(BaseColor.LIGHT_GRAY);  
         under.setFontAndSize(markFont, 12);     
         String waterMarkName=orgName;       
         
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
    public void generatePDF(File file, String id,AuthorizationrecordEntity authorizationrecordEntity) throws Exception {   
    		//授权开始时间
    		Date ksDate=new SimpleDateFormat("yyyy-MM-dd").parse(authorizationrecordEntity.getAt_sqkssj());
    		//授权结束时间
    		Date jsDate=new SimpleDateFormat("yyyy-MM-dd").parse(authorizationrecordEntity.getAt_sqjssj());
    		//当前时间
    		Date dqDate=new Date();
    		Calendar c = Calendar.getInstance();
    		c.setTime(ksDate);
    		Calendar c1 = Calendar.getInstance();
    		c1.setTime(jsDate);
    		Calendar c2 = Calendar.getInstance();
    		c2.setTime(dqDate);
    		Document doc = new Document(PageSize.A4);
    		PdfWriter.getInstance(doc, new FileOutputStream(file));
    		 //添加中文字体
            BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            
            //设置字体样式
            Font textFont = new Font(bfChinese,11,Font.NORMAL); //正常
            Font redTextFont = new Font(bfChinese,11,Font.NORMAL); //正常
            Font boldFont = new Font(bfChinese,11,Font.BOLD); //加粗
            Font redBoldFont = new Font(bfChinese,11,Font.BOLD); //加粗
            Font firsetTitleFont = new Font(bfChinese,22,Font.BOLD); //一级标题
            Font secondTitleFont = new Font(bfChinese,15,Font.BOLD); //二级标题
            Font underlineFont = new Font(bfChinese,11,Font.UNDERLINE); //下划线斜体
    		doc.open();
    	       //段落  
            Paragraph p1 = new Paragraph();  
            //短语
            Phrase ph1 = new Phrase();  
            //将短语添加到段落
            p1.add(ph1);
            //将段落添加到短语
            doc.add(p1);
            
            p1 = new Paragraph();  
            ph1 = new Phrase(); 
            p1.add(ph1);
            doc.add(p1);
            
            p1 = new Paragraph("涉税保密信息查询委托授权书", firsetTitleFont);
            p1.setLeading(50);
            p1.setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
                
            p1 = new Paragraph();  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_RIGHT);
            ph1 = new Phrase(); 
            Chunk c3 = new Chunk("编号：", textFont) ;
            Chunk c33 = new Chunk(authorizationrecordEntity.getAt_id(), underlineFont) ;
            ph1.add(c3);
            ph1.add(c33);
            p1.add(ph1);
            doc.add(p1);
                
            p1 = new Paragraph(authorizationrecordEntity.getAt_sjmc()+":", underlineFont);
            p1.setLeading(40);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
            
            p1 = new Paragraph(" ");  
            p1.setLeading(20);
            doc.add(p1);
            
            p1 = new Paragraph();  
            ph1 = new Phrase(); 
            Chunk c5 = new Chunk("由于本公司", textFont) ;
            Chunk c6 = new Chunk("   "+authorizationrecordEntity.getAt_qymc()+"、"+authorizationrecordEntity.getAt_nsrsbh()+"   ", underlineFont) ;
            Chunk c7 = new Chunk(" 办理与纳税信用相关信贷业", textFont);
            Chunk c8 = new Chunk("务的需要，决定委托授权 ", textFont) ;
            Chunk c91 = new Chunk(authorizationrecordEntity.getOrganizationEntity().getOrgname(), underlineFont) ;
            Chunk c101 = new Chunk("在办理本公司 ", textFont) ;
            Chunk c111 = new Chunk(authorizationrecordEntity.getFinancialProduct().getFpName(), underlineFont) ;
            Chunk c121 = new Chunk("业务申请及业务存续期间（申请贷款期限："+c1.get(Calendar.YEAR)+" 年 "+c1.get(Calendar.MONTH)+"  月  "+c1.get(Calendar.DATE)+" 日），" +
            						"向税务机关查询  "+c.get(Calendar.YEAR)+"  年 "+c.get(Calendar.MONTH)+" 月" +
            						"至     "+c1.get(Calendar.YEAR)+"  年  "+c1.get(Calendar.MONTH)+" 月（申报纳税期）相关涉税保密信息，并同意税务机关将相关涉税保密信息提供给该单位机构作为评价信贷风险适用。", textFont) ;
            ph1.add(c5);
            ph1.add(c6);
            ph1.add(c7);
            ph1.add(c8);
            ph1.add(c91);
            ph1.add(c101);
            ph1.add(c111);
            ph1.add(c121);
            p1.setLeading(20);
            p1.add(ph1);
            doc.add(p1);


            p1 = new Paragraph();  
            ph1 = new Phrase(); 
            Chunk c9 = new Chunk("本授权委托书有效期至 "+c1.get(Calendar.YEAR)+"  年 "+c1.get(Calendar.MONTH)+"  月 "+c1.get(Calendar.DATE)+" 日。", textFont) ;
            ph1.add(c9);
            p1.add(ph1);
            p1.setLeading(50);
            doc.add(p1);
                
            p1 = new Paragraph("本公司声明，本公司已知悉并理解上述委托授权条款。", textFont); 
            doc.add(p1);
            
            p1 = new Paragraph();  
            ph1 = new Phrase(); 
            Chunk c12 = new Chunk("法定代表人姓名："+authorizationrecordEntity.getAt_frxm()+"", textFont) ;
            ph1.add(c12);
            p1.add(ph1);
            p1.setLeading(50);
            doc.add(p1);
            
            p1 = new Paragraph("法人身份证件号码："+authorizationrecordEntity.getAt_frsfz()+"", textFont);  
            doc.add(p1);
            
            p1 = new Paragraph(authorizationrecordEntity.getAt_qymc()+"(章)     ", underlineFont);
            p1.setLeading(30);
            p1.setAlignment(Element.ALIGN_RIGHT);
            doc.add(p1);
            
            p1 = new Paragraph("授权日期： "+c2.get(Calendar.YEAR)+"  年  "+c2.get(Calendar.MONTH)+"  月  "+c2.get(Calendar.DATE)+"  日", textFont);  
            p1.setLeading(30);
            p1.setAlignment(Element.ALIGN_RIGHT);
            doc.add(p1);
            
            p1 = new Paragraph("附：授权查询涉税保密信息清单", textFont);  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
            p1 = new Paragraph("  1. 税务登记基础信息", textFont);  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
            p1 = new Paragraph("  2.纳税申报信息", textFont);  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
            p1 = new Paragraph("  3. 财务报表信息", textFont);  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
            p1 = new Paragraph("  4.违法违章信息", textFont);  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
            p1 = new Paragraph("  5. 纳税信用信息", textFont);  
            p1.setLeading(20);
            p1.setAlignment(Element.ALIGN_LEFT);
            doc.add(p1);
    		doc.close();
    	}

	public static double add(double v1, double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
}
