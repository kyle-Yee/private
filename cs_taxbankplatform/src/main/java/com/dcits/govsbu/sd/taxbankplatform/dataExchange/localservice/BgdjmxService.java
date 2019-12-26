package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业变更信息服务
 * @author Administrator
 */
public class BgdjmxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>BGDJMX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BGXM_DM':'2016122101','BGQNR':'法人姓名','BGHNR':'夏洛克','XGRQ':'2016-12-21','DJXH':'20161221001','BGDJMXUUID':'2016001','SJTB_SJ':'2016-12-21''PZJGMC':'批准测试机构名称','PZWJ':'23492834203489','LRR_DM':'23','LRRQ':'2016-12-21','XGR_DM':'323','GLUUID':'201601'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}