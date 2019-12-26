package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 获取企业基础信息NsrjcxxService服务
 * @author Administrator
 */
public class NsrjcxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>NSRJCXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'DJJG_DM':'440203','DJRQ':'2016-12-21','DJXH':'20161221001','DJZCLX_DM':201','DWLSGX_DM':'001','FDDBRSFZJHM':'440203000000000009','FDDBRSFZJLX_DM':'99','FDDBRXM':'夏洛克','FJMQYBZ':'Y','GDGHLX_DM':'44','GDSLX_DM':'4402','HY_DM':'200','JDXZ_DM':'440203','KQCCSZTDJBZ':'Y','LRR_DM':'22','LRRQ':'2016-12-21','NSRMC':'洛克科技有限公司','NSRSBH':'440203000000008888','NSRZT_DM':'Y','SCJYDZ':'深圳市福田区福田路国际文化大厦','SCJYDZXZQHSZ_DM':'440201','SJGSDQ':'440201','SJTB_SJ':'2016-12-21','SSDABH':'001','SSGLY_DM':'321','SWDJBLBZ':'N','XGR_DM':'102','XGRQ':'2016-12-21','ZCDZ':'深圳市福田区福田路国际文化大厦','ZCDZXZQHSZ_DM':'440201','ZGSWJ_DM':'440002','ZGSWSKFJ_DM':'440023','ZZJG_DM':'4402'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}