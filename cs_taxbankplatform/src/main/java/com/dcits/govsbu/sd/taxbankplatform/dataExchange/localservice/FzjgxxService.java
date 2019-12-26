package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业分支机构信息
 * @author Administrator
 */
public class FzjgxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>FZJGXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'YXQQ':'2016-12-21','YXQZ':'2016-12-21','SJGSDQ':'440201','XGRQ':'2016-12-21','XGR_DM':'342','LRRQ':'2016-12-21','LRR_DM':6454'FZJGHFDLXDH':'0755-12345675','FZJGHFDDZ':'深圳市福田区福田路','FZJGHFDMC':'测试名称','FZJGHFDNSRSBH':'440202098909890867','FZJGHFDDJXH':'201602','DJXH':'2016001','FZJGFDUUID':'2132123','SJTB_SJ':'2016-12-21'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}