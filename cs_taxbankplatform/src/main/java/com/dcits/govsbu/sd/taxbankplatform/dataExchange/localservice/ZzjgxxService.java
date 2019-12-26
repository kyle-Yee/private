package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业总分机构信息
 * @author Administrator
 */
public class ZzjgxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>ZZJGXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'SJTB_SJ':'2016-12-21','YXQQ':'2016-12-21','ZJGLXDH':'0755-12345678','ZJGFDDBRXM':'夏洛克','ZJGZCDZYZBM':'583000','ZJGZCDZ':'深圳市福田区福田路','ZJGMC':'洛克科技有限公司''ZJGDJXH':'20161221001','ZJGNSRSBH':'440203000000000098','DJXH':'20161221001','LRR_DM':'23','LRRQ':'2016-12-21','XGR_DM':'234','XGRQ':'2016-12-21','YXBZ':'Y','SJGSDQ':'440203','ZJGUUID':'201601','YXQZ':'2016-12-21','ZJGJYFW':'办公套件'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}