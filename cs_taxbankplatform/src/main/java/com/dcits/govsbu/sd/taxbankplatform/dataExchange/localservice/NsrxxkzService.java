package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能:
 * 企业基础信息扩展
 * @author Administrator
 */
public class NsrxxkzService{

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>NSRXXKZ.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'BSRDZXX':'Shylock@qq.com','BSRGDDH':'0755-12389456','BSRSFZJHM':'440203000000000089','BSRSFZJZL_DM':'99','BSRXM':'夏洛克','BSRYDDH':'15900000009','BZFS_DM':'32','CWFZRDZXX':'xialuoke@qq.com','CWFZRGDDH':'0755-12389456','CWFZRSFZJHM':'440203000000000089','CWFZRSFZJZL_DM':'99','CWFZRXM':'夏洛克','CWFZRYDDH':'15900000009','CYRS':'108','DJXH':'20161221001','FDDBRDZXX':'xialuoke@qq.com','FDDBRGDDH':'0755-12389456','FDDBRYDDH':'15900000009','GDGRS':'89','GGRS':'35','GJHDQSZ_DM':'440203','GYKGLX_DM':'56','GYTZBL':'35','HHRS':'2','HJSZD':'440203','HSFS_DM':'90','JYFW':'办公套件','KJZDZZ_DM':'3453','LSSWDJYXQQ':'2016-12-21','LSSWDJYXQZ':'2016-12-21','SCJYDLXDH':'15900000009','SCJYDYZBM':'5830000','SWDLRDZXX':'Shylock@qq.com','SWDLRLXDH':'0755-12389456','SWDLRMC':'夏洛克','SWDLRNSRSBH':'440203000000000089','TZZE':'20000000','WJCYRS':'23','WZ':'www.shylock.com','WZTZBL':'32','YGZNSRLX_DM':'45','YHSJNFS_DM':'65','ZCDLXDH':'0755-12389456','ZCDYZBM':'5830000','ZCZB':'20000000','ZRRTZBL':'21','ZSXMCXBZ_DM':'32','ZZJGLX_DM':'57','ZZSJYLB':'76','ZZSQYLX_DM':'435'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}