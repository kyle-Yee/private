package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 一般纳税人申报明细主表
 * @author Administrator
 */
public class YnbsrService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>YNBSR.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'SBUUID':'UUID99038933','SJTB_SJ':'2016-12-21','LRR_DM':'DM-883229','LRRQ':'2016-12-21','XGR_DM':'XG-28237','XGRQ':'2016-12-21','SJGSDQ':'440203','QMWJSE':'892282','QMWJSE_QJSE':'839222','BQYBTSE':'93822','JZJTSJTSE':'83728222','QCWJCBSE':'233242','BQRKCBSE':'1200000','QMWJCBSE':'2100000','SYSL_NSJCYBJSE':'2232322','YDKSEHJ':'123432243','SJDKSE':'3221211','YNSE':'232222','QMLDSE':'454444','JYBF_YNSE':'565554','MDTYTSE':'5666666','JYBF_NSJCYBJSE':'4555555','YNSEJZE':'890000','YNSEHJ':'9999890','QCWJSE':'9990000','SSCKKJZYJKSTSE':'9900988','BQYJSE':'8888777','FCYJSE':'844444','CKKJZYJKSYJSE':'4455555','BQJNSQYNSE':'9900000','BQJNQJSE':'444433333','EWBLXH':'900000','LMC':'测试列名称','ASYSLJSXSE':'90000090','YSHWXSE':'9800000','YSLWXSE':'980000','SYSL_NSJCTZXSE':'99404','AJYBFJSXSE':'499409','JYBF_NSJCTZXSE':'88393933','MDTBFCKXSE':'3332222','MSXSE':'2211111','MSHWXSE':'233333','MSLWXSE':'210000','XXSE':'3200000','JXSE':'4100000','SQLDSE':'790000','JXSEZC':'9780000','UUID':'UUID00902982','PZXH':'PZXH88393822'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}