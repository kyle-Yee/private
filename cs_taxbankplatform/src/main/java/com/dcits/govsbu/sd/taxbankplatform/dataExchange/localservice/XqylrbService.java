package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业利润表(小企业会计制度)
 * @author Administrator
 */
public class XqylrbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>XQYLRB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BNLJJE':'99839473','BYJE':'8387382','EWBHXH':'EW8373829','HMC':'计算机软件','LRR_DM':'LLR748373','LRRQ':'2016-12-22','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','UUID':'UU098373','XGR_DM':'XGR47483','XGRQ':'2016-12-21','ZLBSCJUUID':'UUD832928'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}