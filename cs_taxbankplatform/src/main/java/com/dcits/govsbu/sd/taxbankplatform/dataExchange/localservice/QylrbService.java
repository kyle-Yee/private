package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业利润表(企业会计制度)
 * @author Administrator
 */
public class QylrbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>QYLRB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BNLJS':'9870000','BYS':'978000','EWBHXH':'EW983883','HMC':'计算机信息','LRR_DM':'DM-989237847','LRRQ':'2016-12-21','SJGSDQ':'440203''SJTB_SJ':'2016-12-21','UUID':'ID-09238487','XGR_DM':'DM92374','XGRQ':'2016-12-21','ZLBSCJUUID':'UUID02938874'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}