package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 财报申报主表
 * @author Administrator
 */
public class ZlbscjbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>ZLBSCJB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'ZLBSCJUUID':'UUID-00929384','SSQQ':'2016-12-21','SSQZ':'2016-12-21','DJXH':'XH-092347','SJTB_SJ':'2016-12-21','BSZL_DM':'DM-029384','ZFBZ_1':'N','ZFRQ_1':'2016-12-21','ZFR_DM':'DM0923874','LRRQ':'2016-12-21','XGR_DM':'DM-029394897','XGRQ':'2016-12-21','SJGSDQ':'440203','ZLBSUUID':'ID-0923477','BBH':'V1.0','LRR_DM':'DM092378747'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}