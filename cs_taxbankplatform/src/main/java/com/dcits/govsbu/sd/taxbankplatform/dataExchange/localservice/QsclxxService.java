package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 欠税信息
 * @author Administrator
 */
public class QsclxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>QSCLXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BZ':'测试备注信息','CBSX_DM':'DM-0389492','CZLX_DM':'LX-9238234','DJXH':'20161221001','DJZCLX_DM':'ZC-02098374','DWLSGX_DM':'GX-0238748','GLYZPZMXXH':'GL-029374','HY_DM':'908','JDXZ_DM':'440203','JKQX':'2016-12-21','JMSE':'3200000','JSYJ':'YJ-923849','KCSE':'210000','KJDJXH':'XH029349857','KJJZRQ':'2016-12-21','KSSL':'93845','LRR_DM':'DM-0928374','LRRQ':'2016-12-21','NSSBRQ':'2016-12-21','QSCLUUID':'UUID09099098','SBFS_DM':'SB-0293984','SBSX_DM_1':'SX-9283984','SJGSDQ':'440203','SJTB_SJ':'2016-12-21','SKSSQQ':'2016-12-21','SKSSQZ':'2016-12-21','SKSSSWJG_DM':'440201','SKSX_DM':'202','SKZL_DM':'09','SL_1':'0.012','SSGLY_DM':'GL-029394','SYBH_1':'BM-0298384','TZLX_DM':'DM-Y98283874','XGR_DM':'DM-0923948Y','XGRQ':'2016-12-21','YBTSE':'98298','YJKQX':'2016-12-21','YJSKZT_DM':'SK-0293948','YNSE':'SE827738','YZCLRQ':'2016-12-21','YZFSRQ':'2016-12-21','YZPZMXXH':'XH-02939849','YZPZZL_DM':'YZ-90273846','ZGSWSKFJ_DM':'440204','ZSDLFS_DM':'DL987878928','ZSFS_DM':'ZS092098','ZSPM_DM':'PM-9283748','ZSSWJG_DM':'440203','ZSUUID':'UU-029389','ZSXM_DM':'DM92839497','ZSZM_DM':'ZM9283974',}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}