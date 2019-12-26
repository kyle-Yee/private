package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业资产负债表(企业会计制度)
 * @author Administrator
 */
public class QyzcfzbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>QYZCFZB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BNLJS':'340093','BYS':'34500','EWBHXH':'EW0902398','HMC':'计算机信息','LRR_DM':'Dm-090898','LRRQ':'2016-12-21','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','UUID':'UUID-09293887','XGR_DM':'DM92738478','XGRQ':'2016-12-21','ZLBSCJUUID':'ID-09238747'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}