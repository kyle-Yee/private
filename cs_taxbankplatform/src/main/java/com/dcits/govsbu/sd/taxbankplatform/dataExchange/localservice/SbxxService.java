package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业申报信息
 * @author Administrator
 */
public class SbxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>SBXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'JSYJ':'偷税漏税','PZXH':'PZ12345432','SKSSQZ':'2016-12-21','SKSSQQ':'2016-12-21','NSSBRQ':'2016-12-21','SBQX':'2016-12-21','DJXH':'20161221001','ZSPM_DM':'计算机','ZSXM_DM':'0021','PZMXXH':'PZMX001','SBFS_DM':'SB123','ZSFS_DM':'ZS1231','YNSE':'4500983','YJSE':'3409806','JMSE':'1000021','YBTSE':'4500000','SBUUID':'UUID123201','YSX':'1100092','YSBQX':'2016-12-21','YZPZZL_DM':'23','HY_DM':'HY7887','ZSDLFS_DM':'DL8728','JDXZ_DM':'440201','ZFRQ_1':'2016-12-21','ZFBZ_1':'ZF01','ZFR_DM':'983','SSGLY_DM':'SS123','ZGSWSKFJ_DM':'DM440203','LRR_DM':'LLR121','LRRQ':'2016-12-21','XGR_DM':'XXRDM1234','XGRQ':'2016-12-21','SJTB_SJ':'2016-12-21','SL_1':'1.2%','ZSZM_DM':'ZS43092','SBXXUUID':'UUID09879','ZSPZMXXH':'ZS7574383','RDPZUUID':'UUID09803','SJGSDQ':'GSD440203','DJZCLX_DM':'DM-1212','GZLX_DM_1':'454','SBSX_DM_1':'SBDM-34534'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}