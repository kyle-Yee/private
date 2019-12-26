package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业利润表(一般企业会计制度)
 * @author Administrator
 */
public class YbqylrbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>YBQYLRB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BQJE':'980000','EWBHXH':'EW9838749','HMC':'名称信息','LRR_DM':'DM-092937','LRRQ':'2016-12-21','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','SQJE_1':'860056','UUID':'ID-98237','XGR_DM':'Dm-982374','XGRQ':'2016-12-21','ZLBSCJUUID':'UUID-092347'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}