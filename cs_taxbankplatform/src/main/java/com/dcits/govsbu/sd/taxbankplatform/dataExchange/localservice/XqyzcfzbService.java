package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能;
 * 企业资产负债表(小企业会计制度)
 * @author Administrator
 */
public class XqyzcfzbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>XQYZCFZB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'QMYE_QY':'7549QY','QYXMMC':'办公套件','NCYE_ZC':'4588909','QMYE_ZC':'83478374','ZCXMMC':'计算机办公套件','EWBHXH':'XL8373873','ZLBSCJUUID':'UUID09908','NCYE_QY':'YE8984743','LRR_DM':'LLR84574','XGRQ':'2016-12-22','UUID':'UU00098','SJTB_SJ':'2016-12-22','SJGSDQ':'SJ-99303','XGR_DM':'XXR00958','LRRQ':'2016-12-22'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}