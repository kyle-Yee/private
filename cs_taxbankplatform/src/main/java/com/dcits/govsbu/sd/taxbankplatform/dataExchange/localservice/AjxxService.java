package com.dcits.govsbu.sd.taxbankplatform.dataExchange.localservice;

/**
 * 功能;
 * 企业稽查案件信息
 * @author Administrator
 */
public class AjxxService {

	public static String getTirIpPackage() {
		// TODO Auto-generated method stub
		String v_message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/dataspec\" version=\"1.0\">" +
		"<identity><serviceId>AJXX.QUERY</serviceId><password>123456</password></identity>" +
		"<contentControl><control><id>1</id><type>zip</type><impl>Zlib</impl></control><control><id>2</id><type>crypt</type><impl>**CA(CA 类型)</impl></control><control><id>3</id><type>code</type><impl>Base64</impl></control></contentControl>" +
		"<businessContent>" +
		"<subPackage>" +
		"<id>1</id>" +
		"<content>{'DBAJBZ':'AJBZ','ZGSWJG_DM':'440203','AJSZJG_DM':'440201','JCSSQJQ':'2016-12-21','JCSSQJZ':'2016-12-21','LASQRQ':'2016-12-21','LARQ':'2016-12-21','ZXDJRQ':'2016-12-21','ZXWBRQ':'2016-12-21','YJSKZE':'300002','YJFKZE':'210000','YJZNJZE':'230009','SJSKZE':'430009','SJZNJZE':'126090','SJFKZE':'4300980','DJZCLX_DM':'LXM','HY_DM':'HY98','SWJG_DM':'440201','JARQ':'2016-12-21','DYABZ':'YH','NSRSBH':'440203000989860009','NSRMC':'夏洛克','JCRQQ':'2016-12-21','SLYJ_2':'同意','CLWBBZ':'WC21','JCJZRQ':'2016-12-21','SLJZRQ':'2016-12-21','JCAJXXUUID':'UU2021','WFXXUUID':'UU312','JCAJBH':'JC123','AJMC':'偷税漏税','DJXH':'20161221001','AJLX_DM':'AJ1221','AJJCZT_DM':'YYSD','JCXM':'偷税漏税','RWXDRQ':'2016-12-21','JCDJRQ':'2016-12-21','SWJCTZSSDRQ':'2016-12-21','JCJSRQ':'2016-12-21','SLDJRQ':'2016-12-21','SLJSRQ':'2016-12-21','TZSJ':'2016-12-21','JCRY':'夏尔','SLRY':'大宝','ZXRY':'夏洛克','LABM_DM':'LA440203','JCBM_DM':'JC440201','SLBM_DM':'SL320932','ZXBM_DM':'ZX123456','LRR_DM':'LLR123','LRRQ':'2016-12-21','XGR_DM':'XXG1234','XGRQ':'2016-12-21','SJGSDQ':'440103','ZFBZ_1':'Y','ZFR_DM':'3232','ZFRQ_1':'2016-12-21','LCSLID':'ID1234','WTAJBZ':'WTYYY','ZDAJBZ':'ZD-123','SJTB_SJ':'2016-12-21','YSSFBZ':'Y'}</content>+" +
		"</subPackage>" +
		"</businessContent>" +
		"<returnState><returnCode>00000000</returnCode><returnMessage>成功</returnMessage></returnState>" +
		"</tiripPackage>";
		return v_message;
	}
}