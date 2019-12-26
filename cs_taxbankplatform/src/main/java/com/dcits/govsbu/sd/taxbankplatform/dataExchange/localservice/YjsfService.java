package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能;
 * 企业征收信息
 * @author Administrator
 */
public class YjsfService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>YJSF.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BZ':'测试这里是备注','CBSX_DM':'3453','CZLX_DM':'755D','DJXH':'20123234','DJZCLX_DM':'D234342','DWLSGX_DM':'DY232','GLYZPZMXXH':'PZ34535','HY_DM':'HY2442','JDXZ_DM':'440201','JKQX':'2016-12-21','JMSE':'434999','JSYJ':'偷税漏税','KJDJXH':'扣缴登记序号','KJJZRQ':'2016-12-21','KMBLGKTSGZ_DM':'KM23984','KSSL':'340000','LRR_DM':'LLR55','LRRQ':'2016-12-21','NSSBRQ':'2016-12-21','QRMXUUID':'UU8738','RKRQ':'2016-12-21','SBFS_DM':'SB873738','SBSX_DM_1':'SSSX874874','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','SKCLLX_DM':'DM878738','SKSSQQ':'2016-12-21','SKSSQZ':'2016-12-21','SKSSSWJG_DM':'SK873783','SKSX_DM':'SKSX878373','SKZL_DM':'SKZL878733','SL_1':'0.1','SSGLY_DM':'GLY85854','SYBH_1':'BH5443','TZLX_DM':'TZ848483','XGR_DM':'XG737372','XGRQ':'2016-12-21','YBTSE':'6859994','YJKQX':'2016-12-21','YJSE':'540000','YJSKZT_DM':'YJ748473','YNSE':'450003','YYZPZMXXH':'2329323','YZCLRQ':'2016-12-21','YZFSRQ':'2016-12-21','YZGSRQ':'2016-12-21','YZPZMXXH':'88783','YZPZXH':'YZ88383','YZPZZL_DM':'YZP99382','ZGSWSKFJ_DM':'KS440203','ZSDLFS_DM':'ZSGL88473','ZSFS_DM':'883929','ZSPM_DM':'ZSPi38392','ZSSWJG_DM':'ZSJG440203','ZSUUID':'UUID0090','ZSXM_DM':'XM-03933','ZSZM_DM':'ZXM-DM8388383'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}