package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业违法违章信息
 * @author Administrator
 */
public class SswfxwdjService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>SSWFXWDJ.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'AJLY':'偷税漏税','DJJG_DM':'440203','DJR_DM':'34','DJRQ':'2016-12-21','DJXH':'20161221001','FZCZJYY':'偷税漏税','HDSLID':'23','JCAJBZ':'JC001','LCSLID':'21','LRR_DM':'34','LRRQ':'2016-12-21','NSRMC':'夏洛克','NSRSBH':'44010209000009089098','RGDJBZ':'BZ','SFSBFWF':'Y','SJGSDQ':'440203','SJSLID':'23','SJTB_SJ':'2016-12-21','SSQJQ_1':'2016-12-21','SSQJZ_1':'2016-12-21','SSWFLX_DM':'09','SSWFSD_DM':'89','SSWFXWCLZT_DM':'54','SSWFXWDJUUID':'UU0001','WFSS':'违法事实','WFXWBH':'201600021','WFXWMC':'偷税漏税','XGR_DM':'34','XGRQ':'2016-12-21','ZFBZ_1':'N','ZFR_DM':'43','ZFRQ_1':'2016-12-21','ZJLX_DM_1':'23','ZJSHRQ':'2016-12-21','ZJSHRY_DM':'65'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}