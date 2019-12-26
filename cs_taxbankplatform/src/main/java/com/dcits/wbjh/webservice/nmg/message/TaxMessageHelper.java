package com.dcits.wbjh.webservice.nmg.message;

import java.util.Date;

import com.dcits.wbjh.webservice.nmg.message.contentControl.code;
import com.dcits.wbjh.webservice.nmg.message.contentControl.contentControl;
import com.dcits.wbjh.webservice.nmg.message.contentControl.encrypt;
import com.dcits.wbjh.webservice.nmg.message.contentControl.zip;
import com.dcits.wbjh.webservice.nmg.message.envelopeInfo.envelopeInfo;
import com.dcits.wbjh.webservice.nmg.message.packageInfo.packageInfo;
import com.dcits.wbjh.webservice.nmg.message.transferInfo.transferInfo;

public class TaxMessageHelper {
	public final static String DEST_TAX = "TAX";
	public final static String DEST_SYPT = "SYPT";
	public final static String BW_HZ = "BW_HZ";
	public final static String BW_REQ = "BW_REQ";
	public final static String BW_BUSINESS = "BW_BUSINESS";

	public static boolean isBusinessMessage(
			dataExchangePackage dataExchangePackage) {
		String businessType = dataExchangePackage.getEnvelopeInfo()
				.getBusinessType();
		boolean flag = false;
		if (businessType.startsWith("BW_BUSINESS")) {
			flag = true;
		}
		return flag;
	}
	public static boolean isReqMessage(
			dataExchangePackage dataExchangePackage) {
		String businessType = dataExchangePackage.getEnvelopeInfo()
				.getBusinessType();
		boolean flag = false;
		if (businessType.startsWith("BW_REQ")) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean isRespMessage(
			dataExchangePackage dataExchangePackage) {
		String businessType = dataExchangePackage.getEnvelopeInfo()
				.getBusinessType();
		boolean flag = false;
		if (businessType.startsWith("BW_HZ")) {
			flag = true;
		}
		return flag;
	}


	public static String createUUID() {
		String uuid = java.util.UUID.randomUUID().toString();
		return uuid;
	}

	public static dataExchangePackage buildMessHz(String srcId, String desId,
			String conent) {
		dataExchangePackage dataExchangePackage = new dataExchangePackage();
		envelopeInfo envelopeInfo = new envelopeInfo();
		envelopeInfo.setSourceID(srcId);
		envelopeInfo.setDestinationAppID(desId);
		envelopeInfo.setDestinationID(desId);
		String globalBusinessID = createUUID();
		envelopeInfo.setGlobalBusinessID(globalBusinessID);
		envelopeInfo.setBusinessType(BW_HZ);// 回执报文

		transferInfo transferInfo = new transferInfo();
		transferInfo.setSenderID(srcId);
		transferInfo.setReceiverID(desId);
		String sourceMessageID = globalBusinessID;
		transferInfo.setSourceMessageID(sourceMessageID);
		String messageID = createUUID();
		transferInfo.setMessageID(messageID);
		String isRetry = "N";
		transferInfo.setIsRetry(isRetry);
		String sendtime = org.apache.commons.lang.time.DateFormatUtils.format(
				new Date(), "yyyy-MM-dd HH:mm:ss");
		transferInfo.setSendTime(sendtime);

		contentControl contentControl = new contentControl();
		code code = new code();
		code.setCode(false);
		code.setCodeType("");
		encrypt encrypt = new encrypt();
		encrypt.setEncrypt(false);
		encrypt.setEncryptType("");
		zip zip = new zip();
		zip.setZipType("");
		zip.setZip(false);
		contentControl.setCode(code);
		contentControl.setEncrypt(encrypt);
		contentControl.setZip(zip);

		packageInfo packageInfo = new packageInfo();
		packageInfo.setSequence("1");
		packageInfo.setContent(conent);

		dataExchangePackage.setEnvelopeInfo(envelopeInfo);
		dataExchangePackage.setTransferInfo(transferInfo);
		dataExchangePackage.setContentControl(contentControl);
		dataExchangePackage.setPackageInfo(packageInfo);

		return dataExchangePackage;
	}
	
	public static dataExchangePackage buildMessBusiness(String srcId, String desId,
			String conent) {
		dataExchangePackage dataExchangePackage = new dataExchangePackage();
		envelopeInfo envelopeInfo = new envelopeInfo();
		envelopeInfo.setSourceID(srcId);
		envelopeInfo.setDestinationAppID(desId);
		envelopeInfo.setDestinationID(desId);
		String globalBusinessID = createUUID();
		envelopeInfo.setGlobalBusinessID(globalBusinessID);
		envelopeInfo.setBusinessType(BW_BUSINESS);// 回执报文

		transferInfo transferInfo = new transferInfo();
		transferInfo.setSenderID(srcId);
		transferInfo.setReceiverID(desId);
		String sourceMessageID = globalBusinessID;
		transferInfo.setSourceMessageID(sourceMessageID);
		String messageID = createUUID();
		transferInfo.setMessageID(messageID);
		String isRetry = "N";
		transferInfo.setIsRetry(isRetry);
		String sendtime = org.apache.commons.lang.time.DateFormatUtils.format(
				new Date(), "yyyy-MM-dd HH:mm:ss");
		transferInfo.setSendTime(sendtime);

		contentControl contentControl = new contentControl();
		code code = new code();
		code.setCode(false);
		code.setCodeType("");
		encrypt encrypt = new encrypt();
		encrypt.setEncrypt(false);
		encrypt.setEncryptType("");
		zip zip = new zip();
		zip.setZipType("");
		zip.setZip(false);
		contentControl.setCode(code);
		contentControl.setEncrypt(encrypt);
		contentControl.setZip(zip);

		packageInfo packageInfo = new packageInfo();
		packageInfo.setSequence("1");
		packageInfo.setContent(conent);

		dataExchangePackage.setEnvelopeInfo(envelopeInfo);
		dataExchangePackage.setTransferInfo(transferInfo);
		dataExchangePackage.setContentControl(contentControl);
		dataExchangePackage.setPackageInfo(packageInfo);

		return dataExchangePackage;
	}

	
	public static dataExchangePackage buildMessReq(String srcId, String desId,
			String conent) {
		dataExchangePackage dataExchangePackage = new dataExchangePackage();
		envelopeInfo envelopeInfo = new envelopeInfo();
		envelopeInfo.setSourceID(srcId);
		envelopeInfo.setDestinationAppID(desId);
		envelopeInfo.setDestinationID(desId);
		String globalBusinessID = createUUID();
		envelopeInfo.setGlobalBusinessID(globalBusinessID);
		envelopeInfo.setBusinessType(BW_REQ);// 回执报文

		transferInfo transferInfo = new transferInfo();
		transferInfo.setSenderID(srcId);
		transferInfo.setReceiverID(desId);
		String sourceMessageID = globalBusinessID;
		transferInfo.setSourceMessageID(sourceMessageID);
		String messageID = createUUID();
		transferInfo.setMessageID(messageID);
		String isRetry = "N";
		transferInfo.setIsRetry(isRetry);
		String sendtime = org.apache.commons.lang.time.DateFormatUtils.format(
				new Date(), "yyyy-MM-dd HH:mm:ss");
		transferInfo.setSendTime(sendtime);

		contentControl contentControl = new contentControl();
		code code = new code();
		code.setCode(false);
		code.setCodeType("");
		encrypt encrypt = new encrypt();
		encrypt.setEncrypt(false);
		encrypt.setEncryptType("");
		zip zip = new zip();
		zip.setZipType("");
		zip.setZip(false);
		contentControl.setCode(code);
		contentControl.setEncrypt(encrypt);
		contentControl.setZip(zip);

		packageInfo packageInfo = new packageInfo();
		packageInfo.setSequence("1");
		packageInfo.setContent(conent);

		dataExchangePackage.setEnvelopeInfo(envelopeInfo);
		dataExchangePackage.setTransferInfo(transferInfo);
		dataExchangePackage.setContentControl(contentControl);
		dataExchangePackage.setPackageInfo(packageInfo);

		return dataExchangePackage;
	}

	
	public static dataExchangePackage buildMessHzFromReq(
			dataExchangePackage req, String srcid, String desId, String content) {
		dataExchangePackage dataExchangePackage = new dataExchangePackage();
		envelopeInfo envelopeInfo = new envelopeInfo();
		envelopeInfo.setSourceID(srcid);
		envelopeInfo.setDestinationAppID(desId);
		envelopeInfo.setDestinationID(desId);
		String globalBusinessID = req.getEnvelopeInfo().getGlobalBusinessID();
		envelopeInfo.setGlobalBusinessID(globalBusinessID);
		envelopeInfo.setBusinessType(BW_HZ);// 回执报文

		transferInfo transferInfo = new transferInfo();
		transferInfo.setSenderID(srcid);
		transferInfo.setReceiverID(desId);
		String sourceMessageID = req.getTransferInfo().getSourceMessageID();
		transferInfo.setSourceMessageID(sourceMessageID);
		String messageID = createUUID();
		transferInfo.setMessageID(messageID);
		String isRetry = "N";
		transferInfo.setIsRetry(isRetry);
		String sendtime = new Date().toString();
		transferInfo.setSendTime(sendtime);

		contentControl contentControl = new contentControl();
		code code = new code();
		code.setCode(false);
		code.setCodeType("");
		encrypt encrypt = new encrypt();
		encrypt.setEncrypt(false);
		encrypt.setEncryptType("");
		zip zip = new zip();
		zip.setZipType("");
		zip.setZip(false);
		contentControl.setCode(code);
		contentControl.setEncrypt(encrypt);
		contentControl.setZip(zip);

		packageInfo packageInfo = new packageInfo();
		packageInfo.setSequence("1");
		packageInfo.setContent(content);

		dataExchangePackage.setEnvelopeInfo(envelopeInfo);
		dataExchangePackage.setTransferInfo(transferInfo);
		dataExchangePackage.setContentControl(contentControl);
		dataExchangePackage.setPackageInfo(packageInfo);

		return dataExchangePackage;
	}
	
	public static dataExchangePackage buildBusinessMessFromReq(
			dataExchangePackage req, String srcid, String desId, String content) {
		dataExchangePackage dataExchangePackage = new dataExchangePackage();
		envelopeInfo envelopeInfo = new envelopeInfo();
		envelopeInfo.setSourceID(srcid);
		envelopeInfo.setDestinationAppID(desId);
		envelopeInfo.setDestinationID(desId);
		String globalBusinessID = req.getEnvelopeInfo().getGlobalBusinessID();
		envelopeInfo.setGlobalBusinessID(globalBusinessID);
		envelopeInfo.setBusinessType(BW_BUSINESS);// 回执报文

		transferInfo transferInfo = new transferInfo();
		transferInfo.setSenderID(srcid);
		transferInfo.setReceiverID(desId);
		String sourceMessageID = req.getTransferInfo().getSourceMessageID();
		transferInfo.setSourceMessageID(sourceMessageID);
		String messageID = createUUID();
		transferInfo.setMessageID(messageID);
		String isRetry = "N";
		transferInfo.setIsRetry(isRetry);
		String sendtime = new Date().toString();
		transferInfo.setSendTime(sendtime);

		contentControl contentControl = new contentControl();
		code code = new code();
		code.setCode(false);
		code.setCodeType("");
		encrypt encrypt = new encrypt();
		encrypt.setEncrypt(false);
		encrypt.setEncryptType("");
		zip zip = new zip();
		zip.setZipType("");
		zip.setZip(false);
		contentControl.setCode(code);
		contentControl.setEncrypt(encrypt);
		contentControl.setZip(zip);

		packageInfo packageInfo = new packageInfo();
		packageInfo.setSequence("1");
		packageInfo.setContent(content);

		dataExchangePackage.setEnvelopeInfo(envelopeInfo);
		dataExchangePackage.setTransferInfo(transferInfo);
		dataExchangePackage.setContentControl(contentControl);
		dataExchangePackage.setPackageInfo(packageInfo);

		return dataExchangePackage;
	}

}
