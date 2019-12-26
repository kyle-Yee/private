package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 小规模纳税人申报明细主表
 * @author Administrator
 */
public class XgmnsrService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>XGMNSR.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'SBUUID':'UUID9088883','SJTB_SJ':'2016-12-22','XWQYMSXSE':'9800000','WDQZDXSE':'860000','QTMSXSE':'4300000','BQMSE':'21000','XWQYMSE':'230000','WDQZDMSE':'430000','HDXSE':'260000','HDYNSE':'540000','UUID':'UUID9840000','PZXH':'PZXH9983493','YZZZSBHSXSE':'870000','SWJGDKDZZSZYFPBHSXSE':'987000','SKQJKJDPTFPBHSXSE_1':'5460000','XSSYGDYSGDZCBHSXSE':'87960000','SKQJKJDPTFPBHSXSE':'9870000','MSXSE':'430000','SKQJKJDPTFPXSE':'9879000','CKMSXSE':'9810000','SKQJKJDPTFPXSE_1':'3250000','BQYNSEJZE':'5400021','YNSEHJ':'9900009','BQYJSE_1':'21120000','LRR_DM':'DM-9998393','LRRQ':'2016-12-22','XGR_DM':'DM009383','XGRQ':'2016-12-22','SJGSDQ':'440203','EWBLXH':'RW8939382','LMC':'列名称测试','BQYBTSE':'43220000','BQYNSE':'213000',}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}