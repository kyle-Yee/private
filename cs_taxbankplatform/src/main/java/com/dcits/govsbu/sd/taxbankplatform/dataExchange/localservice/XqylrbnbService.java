package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 利润表(小企业会计制度)_年报
 * @author Administrator
 */
public class XqylrbnbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>XQYLRBNB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BNLJJE':'4456000','EWBHXH':'EW827638','HMC':'测试行业名称','LRR_DM':'DM-9028374','LRRQ':'2016-12-21','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','SNJE':'4788360','UUID':'UUID-092839874','XGR_DM':'DM9823847','XGRQ':'2016-12-21','ZLBSCJUUID':'ID-092839847'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}