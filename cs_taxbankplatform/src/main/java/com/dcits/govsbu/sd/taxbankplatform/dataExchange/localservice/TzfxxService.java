package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业投资方信息服务
 * @author Administrator
 */
public class TzfxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>TZFXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'DJXH':'20161221001','DZ':'深圳市福田区福田路国际文化大厦','FPBL':'45%','GJHDQSZ_DM':'440102','LRR_DM':'234','LRRQ':'2016-12-21','SJGSDQ':'440203''SJTB_SJ':'2016-12-21','TZBL':'43%','TZFHHHRMC':'大宝','TZFHHHRZJHM':'440202098099999098','TZFHHHRZJZL_DM':'99','TZFJJXZ_DM':'43','TZJE':'5000000','UUID':'201601','XGR_DM':'23','XGRQ':'2016-12-21','YXQQ':'2016-12-21','YXQZ':'2016-12-21'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}