package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业资产负债表(一般企业会计)
 * @author Administrator
 */
public class YbqyzcfzbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>YBQYZCFZB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'EWBHXH':'EW9238487','LRR_DM':'DM09203998','LRRQ':'2016-12-21','NCYE_QY':'QY-02938','NCYE_ZC':'YE-928399','QMYE_QY':'QM-0923978','QMYE_ZC':'ZC-0293783','QYXMMC':'测试名称','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','UUID':'UUID-09092893','XGR_DM':'Dm-9897','XGRQ':'2016-12-21','ZCXMMC':'测试名称','ZLBSCJUUID':'ID-98293874'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}