package com.dcits.wbjh.webservice.nmg.message;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dcits.wbjh.webservice.nmg.message.contentControl.*;
import com.dcits.wbjh.webservice.nmg.message.envelopeInfo.envelopeInfo;
import com.dcits.wbjh.webservice.nmg.message.packageInfo.packageInfo;
import com.dcits.wbjh.webservice.nmg.message.transferInfo.transferInfo;

public class MessageBuider {

	public static void main(String[] args){
		
		
		//parseFromXml("");
		
		dataExchangePackage dataExchangePackage =buildMessExample(); 
		            
		String xml = toXml(dataExchangePackage);
		dataExchangePackage =parseFromXml(xml);
		xml = toXml(dataExchangePackage);
		dataExchangePackage =parseFromXml(xml);
		  
		
	}
	
	public static dataExchangePackage buildMessExample(){
		dataExchangePackage dataExchangePackage = new dataExchangePackage();
		envelopeInfo envelopeInfo= new envelopeInfo();
		envelopeInfo.setBusinessType("SYPT");
		envelopeInfo.setDestinationAppID("SJJH");
		envelopeInfo.setDestinationID("WBJH");
		envelopeInfo.setGlobalBusinessID("全局交易标识");
		envelopeInfo.setSourceID("SY01");
		
		
		transferInfo transferInfo =new transferInfo();
		contentControl contentControl=new contentControl();;
		packageInfo packageInfo =new packageInfo();;
		
		dataExchangePackage.setEnvelopeInfo(envelopeInfo);
		dataExchangePackage.setTransferInfo(transferInfo);
		dataExchangePackage.setContentControl(contentControl);
		dataExchangePackage.setPackageInfo(packageInfo);
		
		return dataExchangePackage;
	}
	
	
	public static String toXml(dataExchangePackage dataExchangePackage){
		Document document = DocumentHelper.createDocument();  

		document.setXMLEncoding("GBK");  
		Element root = document.addElement("dataExchangePackage");  
		
		
		envelopeInfo envelopeInfo= dataExchangePackage.getEnvelopeInfo();
		Element envelopeInfoEle =root.addElement("envelopeInfo");
		envelopeInfoEle.addElement("sourceID").addText(envelopeInfo.getSourceID());
		envelopeInfoEle.addElement("destinationID").addText(envelopeInfo.getDestinationID());
		envelopeInfoEle.addElement("destinationAppID").addText(envelopeInfo.getDestinationAppID());
		envelopeInfoEle.addElement("globalBusinessID").addText(envelopeInfo.getGlobalBusinessID());
		envelopeInfoEle.addElement("businessType").addText(envelopeInfo.getBusinessType());
		
		
		transferInfo transferInfo =dataExchangePackage.getTransferInfo();
		Element transferInfoEle =root.addElement("transferInfo");		
		transferInfoEle.addElement("senderID").addText(transferInfo.getSenderID());
		transferInfoEle.addElement("receiverID").addText(transferInfo.getReceiverID());
		transferInfoEle.addElement("messageID").addText(transferInfo.getMessageID());
		transferInfoEle.addElement("sourceMessageID").addText(transferInfo.getSourceMessageID());
		transferInfoEle.addElement("isRetry").addText(transferInfo.getIsRetry());
		transferInfoEle.addElement("sendTime").addText(transferInfo.getSendTime());
		
		
		
		contentControl contentControl=dataExchangePackage.getContentControl();
		Element contentControlEle =root.addElement("contentControl");	
		Element zipEle = contentControlEle.addElement("zip");
		zipEle.addElement("isZip").addText(fromBoolean(contentControl.getZip().isZip()));
		zipEle.addElement("zipType").addText(contentControl.getZip().getZipType());
		
		Element encryptEle = contentControlEle.addElement("encrypt");
		zipEle.addElement("isEncrypt").addText(fromBoolean(contentControl.getEncrypt().isEncrypt()));
		zipEle.addElement("encryptType").addText(contentControl.getEncrypt().getEncryptType());
		
		Element codeEle = contentControlEle.addElement("code");
		zipEle.addElement("isCode").addText(fromBoolean(contentControl.getCode().isCode()));
		zipEle.addElement("getCodeType").addText(contentControl.getCode().getCodeType());	
		
		
		
		packageInfo packageInfo =dataExchangePackage.getPackageInfo();
		Element packageInfoEle =root.addElement("packageInfo");	
		packageInfoEle.addElement("sequence").addText(packageInfo.getSequence());
		packageInfoEle.addElement("content").addCDATA(packageInfo.getContent());
				
		return document.asXML();

	}
	public static dataExchangePackage parseFromXml(String xml){
//		String file ="D:\\catest\\tax.xml";
//		SAXReader reader = new SAXReader();  
//		
//		Document document = null;
//		try {
//			document =reader.read(file);
//			//
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Document document =null;
		try {
			document = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataExchangePackage dep = new dataExchangePackage();
		
		Element rootElm = document.getRootElement();  
		Element envelopeInfoEle =rootElm.element("envelopeInfo");
		Element transferInfoEle =rootElm.element("transferInfo");
		Element contentControlEle =rootElm.element("contentControl");
		Element packageInfoEle =rootElm.element("packageInfo");
		
		envelopeInfo envelopeInfo =parseEnvelopeInfoEle(envelopeInfoEle);		
		transferInfo transferInfo =parseTransferInfoEle(transferInfoEle);
		contentControl contentControl = parseContentControlEle(contentControlEle);
		packageInfo packageInfo= parsePackageInfoEle(packageInfoEle);
		
		dep.setEnvelopeInfo(envelopeInfo);
		dep.setTransferInfo(transferInfo);
		dep.setContentControl(contentControl);
		dep.setPackageInfo(packageInfo);
		
		return dep;
		
	}	
	private static envelopeInfo parseEnvelopeInfoEle(Element envelopeInfoEle){
		envelopeInfo envelopeInfo = new envelopeInfo();			
		String sourceID = elementText(envelopeInfoEle.element("sourceID"));
		String destinationID = elementText(envelopeInfoEle.element("destinationID"));
		String destinationAppID = elementText(envelopeInfoEle.element("destinationAppID"));
		String globalBusinessID = elementText(envelopeInfoEle.element("globalBusinessID"));
		String businessType = elementText(envelopeInfoEle.element("businessType"));
		envelopeInfo.setSourceID(sourceID);
		envelopeInfo.setDestinationAppID(destinationAppID);
		envelopeInfo.setDestinationID(destinationID);
		envelopeInfo.setGlobalBusinessID(globalBusinessID);
		envelopeInfo.setBusinessType(businessType);
		
		return envelopeInfo;
	}
	
	private static transferInfo parseTransferInfoEle(Element transferInfoEle){
		transferInfo transferInfo = new transferInfo();		
				
		
		String senderID = elementText(transferInfoEle.element("senderID"));
		String receiverID = elementText(transferInfoEle.element("receiverID"));
		String messageID = elementText(transferInfoEle.element("messageID"));
		String sourceMessageID = elementText(transferInfoEle.element("sourceMessageID"));
		String isRetry = elementText(transferInfoEle.element("isRetry"));
		String sendTime = elementText(transferInfoEle.element("sendTime"));
		
		
		transferInfo.setSenderID(senderID);
		transferInfo.setReceiverID(receiverID);
		transferInfo.setMessageID(messageID);
		transferInfo.setSourceMessageID(sourceMessageID);
		transferInfo.setIsRetry(isRetry);
		transferInfo.setSenderID(senderID);
		
		return transferInfo;
	}
	
	
	private static contentControl parseContentControlEle(Element contentControlEle){
		contentControl contentControl = new contentControl();		
				
				
		Element zipEle=contentControlEle.element("zip");
		String isZip = elementText(zipEle.element("isZip"));
		String zipType = elementText(zipEle.element("zipType"));
		
		Element encryptEle=contentControlEle.element("encrypt");
		String isEncrypt = elementText(encryptEle.element("isEncrypt"));
		String encryptType = elementText(encryptEle.element("encryptType"));
		
		Element codeEle=contentControlEle.element("code");
		String isCode = elementText(codeEle.element("isCode"));
		String codeType = elementText(codeEle.element("codeType"));
		
		zip zip = new zip();
		zip.setZip(toBoolean(isZip));
		zip.setZipType(zipType);
		
		encrypt encrypt = new encrypt();
		encrypt.setEncrypt(toBoolean(isEncrypt));
		encrypt.setEncryptType(encryptType);
		code code=new code();
		code.setCode(toBoolean(isCode));
		code.setCodeType(codeType);
		
		
		contentControl.setCode(code);
		contentControl.setEncrypt(encrypt);
		contentControl.setZip(zip);
		
		return contentControl;
	}
	
	
	private static packageInfo parsePackageInfoEle(Element packageInfoEle){
		packageInfo packageInfo = new packageInfo();
		
		String sequence =elementText( packageInfoEle.element("sequence"));
		Element contentEle = packageInfoEle.element("content");
		String content ="";
		if(contentEle!=null){
			content = contentEle.getData().toString();
		}
		packageInfo.setSequence(sequence);
		packageInfo.setContent(content);		
		
		return packageInfo;
	
	}
	
	private static String  elementText(Element e){
		if(e ==null){
			return "";
		}
		return e.getText();
		
	}
	
	private static String fromBoolean(boolean flag){
		if(flag){
			return "true";
		}
		return "false";
	}
	private static boolean toBoolean(String flag){
		if(flag ==null){
			return false;
		}
		if("true".equalsIgnoreCase(flag.toLowerCase())){
			return true;
		}
		return false;
	}
}
