package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业批准机构信息服务
 * @author Administrator
 */
public class PzjgxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>PZJGXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'DJXH':'20161221001','KYSLRQ':'2016-12-21','LCSLID':'00001','LRR_DM':'21','LRRQ':'2016-12-21','PZJGUUID':1000023'PZSLJG_DM':'440203','PZSLJGLX_DM':'220348','PZSLJGMC':'测试名称','PZZMHWJH':'2016122100219389','SCJYQXQ':'2016-12-21','SCJYQXZ':'2016-12-21','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','XGR_DM':'33','XGRQ':'2016-12-21','ZZHM':'2834298347928364','ZZLX_DM':'28364823647824'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}