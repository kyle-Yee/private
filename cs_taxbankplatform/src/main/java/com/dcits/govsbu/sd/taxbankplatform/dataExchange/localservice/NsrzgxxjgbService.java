package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 纳税人资格信息结果表
 * @author Administrator
 */
public class NsrzgxxjgbService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>NSRZGXXJGB.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'SJTB_SJ':'2016-12-22','XGRQ':'2016-12-22','ZFBZ_1':'N','ZFR_DM':'DM-93932','ZFRQ_1':'2016-12-22','RDPZUUID':'UUID-098222','LCSLID':'ID-029282','DJXH':'20161221001','NSRZGLX_DM':'ZGDM-02928921''YXQQ':'2016-12-22','YXQZ':'2016-12-22','SJZZRQ':'2016-12-22','QXBZ':'Y','RDPZUUID':'440203','LRR_DM':'DM-928293','LRRQ':'2016-12-22','XGR_DM':'DM0292933','WSZG':'ZG928293'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}