package com.dcits.govsbu.sd.taxbankplatform.cqsyyhtotax;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * 重庆的 重庆银行、建设银行、工商银行、浙商银行等银行组装报文调用税局接口
 * @author admin
 *
 */
public class SendCqXdsq {

	
	/**
	 * 重庆银行取数报文
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static String sendGetTaxRequestToTax(Map<String, Object> dataObject,String sqxh) {

		StringBuffer xmlContent = parseXmlContentCq(dataObject, sqxh);
		// 发送报文
		String nrsData = InvokeCqWebService.sendMessageToGsWbjh(xmlContent.toString());
		
		return nrsData;

	}
	
	/**
	 * 建设、工行、民生、浙商银行取数报文  下载报告
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static String sendGetTaxRequestToTaxJH(Map<String, Object> dataObject,String sqxh) {

		StringBuffer xmlContent = parseXmlContentJH(dataObject, sqxh);
		// 发送报文
		String nrsData = InvokeCqWebService.sendMessageToGsWbjh(xmlContent.toString());
		
		return nrsData;

	}
	
	
	/**
	 * 光大银行取数报文
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static String sendGetTaxRequestToTaxGD(Map<String, Object> dataObject,String sqxh) {

		StringBuffer xmlContent = parseXmlContentGD(dataObject, sqxh);
		// 发送报文
		String nrsData = InvokeCqWebService.sendMessageToGsWbjh(xmlContent.toString());
		
		return nrsData;

	}
	
	
	
	
	/**
	 * 重庆银行拼装报文
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static StringBuffer parseXmlContentCq(Map<String, Object> reqMap,
			String sqxh) {
		// 3.调用webservice获取局端税务数据

		StringBuffer xmlContent = new StringBuffer();

		String nsrsbh = (String) reqMap.get("NSRSBH");

		String dateyear = new SimpleDateFormat("yyyy").format(new Date());

		Integer dateyearInteger = Integer.valueOf(dateyear);

		Integer integer1 = dateyearInteger-3;
		Integer integer2 = dateyearInteger-2;
		Integer integer3 = dateyearInteger-1;
		
		//一定要顺着传年份  不然最新年的数据取不到    String nd = "2014,2015,2016,2017";
		String nd = integer1+","+integer2+","+integer3+","+dateyearInteger;
		
		//查询gs_syptqysdsnd 的年份  2014,2015,2016
		String ndnd = integer1+","+integer2+","+integer3;

		System.out.println(nd);
		
		xmlContent.append("<?xml version='1.0' encoding='GBK'?><syptRequest>")

		.append("<syptGsdjxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptGsdjxx>")
				.append("<syptSwdjxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptSwdjxx>")
				.append("<syptSwbgxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptSwbgxx>").append("<syptGdxx>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptGdxx>").append("<syptWfwzxx>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptWfwzxx>").append("<syptSsyhzcxx>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptSsyhzcxx>").append("<syptQsye>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptQsye>").append("<syptQlqs>").append("<nsrsbh>")
				.append(nsrsbh).append("</nsrsbh>").append("<sqxh>")
				.append(sqxh).append("</sqxh>").append("</syptQlqs>")
				.append("<syptQysdsA1>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsA1>")
				.append("<syptQysdsA2>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsA2>")
				.append("<syptQysdsA3>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsA3>")
				.append("<syptQysdsB1>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsB1>")
				.append("<syptQysdsB2>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsB2>")
				.append("<syptQysdsB3>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsB3>")
				.append("<syptQysdsNd>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(ndnd)
				.append("</qsnd>").append("</syptQysdsNd>")
				.append("<syptZzsybnsr>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptZzsybnsr>")
				.append("<syptZzsxgm>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptZzsxgm>")
				.append("<syptSwpjxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptSwpjxx>")
				.append("</syptRequest>");

		return xmlContent;
	}
	
	
	/**
	 * 建行通用版银行取数报文
	 * 
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static StringBuffer parseXmlContentJH(Map<String, Object> reqMap,
			String sqxh) {
		// 3.调用webservice获取局端税务数据

		StringBuffer xmlContent = new StringBuffer();

		String dateyear = new SimpleDateFormat("yyyy").format(new Date());

		Integer dateyearInteger = Integer.valueOf(dateyear);

		Integer integer1 = dateyearInteger-3;
		Integer integer2 = dateyearInteger-2;
		Integer integer3 = dateyearInteger-1;
		
		//一定要顺着传年份  不然最新年的数据取不到    String nd = "2014,2015,2016,2017";
		String nd = integer1+","+integer2+","+integer3+","+dateyearInteger;
		
		//取数年度 2015，2016,2017
		String qsnd =integer2+","+integer3+","+dateyearInteger;
		
		//查询gs_syptqysdsnd 的年份  2014,2015,2016
		String ndnd = integer1+","+integer2+","+integer3;

		System.out.println(nd);
		
		   Calendar calendar = Calendar.getInstance();
		     int jn = calendar.get(1);
		
		     String ksnd12 = String.valueOf(jn - 2);
		     String yf = String.valueOf(calendar.get(2) + 1);
		     String zznd12 = String.valueOf(jn);
		     		     
		String nsrsbh = (String) reqMap.get("NSRSBH");
		 xmlContent.append("<?xml version='1.0' encoding='GBK'?><syptRequest>")
		 .append("<syptQydjxx>").append("<nsrsbh>")
		 .append(nsrsbh).append("</nsrsbh>")
		 .append("<sqxh>").append(sqxh).append("</sqxh>")
		 .append("</syptQydjxx>")
		 .append("<syptSsxx>").append("<nsrsbh>")
		 .append(nsrsbh).append("</nsrsbh>")
		 .append("<sqxh>").append(sqxh).append("</sqxh>")
		 .append("<qsnd>").append(qsnd).append("</qsnd>")
		 .append("</syptSsxx>")
         .append("<syptSsxxcx>").append("<nsrsbh>")
         .append(nsrsbh).append("</nsrsbh>")
         .append("<sqxh>").append(sqxh).append("</sqxh>")
         .append("<cxrqq>").append(ksnd12 + "-" + yf).append("</cxrqq>")
         .append("<cxrqz>").append(zznd12 + "-" + yf).append("</cxrqz>")
         .append("<cfts>").append("2").append("</cfts>")
         .append("</syptSsxxcx>")
		 .append("<syptGsdjxx>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("</syptGsdjxx>")
		 .append("<syptSwdjxx>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("</syptSwdjxx>")
		 .append("<syptSwbgxx>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("</syptSwbgxx>").append("<syptGdxx>")
		 .append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		 .append("<sqxh>").append(sqxh).append("</sqxh>")
		 .append("</syptGdxx>").append("<syptWfwzxx>")
		 .append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		 .append("<sqxh>").append(sqxh).append("</sqxh>")
		 .append("</syptWfwzxx>").append("<syptSsyhzcxx>")
		 .append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		 .append("<sqxh>").append(sqxh).append("</sqxh>")
		 .append("</syptSsyhzcxx>").append("<syptQsye>")
		 .append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		 .append("<sqxh>").append(sqxh).append("</sqxh>")
		 .append("</syptQsye>").append("<syptQlqs>").append("<nsrsbh>")
		 .append(nsrsbh).append("</nsrsbh>").append("<sqxh>")
		 .append(sqxh).append("</sqxh>").append("</syptQlqs>")
		 .append("<syptQysdsA1>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptQysdsA1>")
		 .append("<syptQysdsA2>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptQysdsA2>")
		 .append("<syptQysdsA3>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptQysdsA3>")
		 .append("<syptQysdsB1>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptQysdsB1>")
		 .append("<syptQysdsB2>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptQysdsB2>")
		 .append("<syptQysdsB3>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptQysdsB3>")
		 .append("<syptQysdsNd>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(ndnd)
		 .append("</qsnd>").append("</syptQysdsNd>")
		 .append("<syptZzsybnsr>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptZzsybnsr>")
		 .append("<syptZzsxgm>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("<qsnd>").append(nd)
		 .append("</qsnd>").append("</syptZzsxgm>")
		 .append("<syptSwpjxx>").append("<nsrsbh>").append(nsrsbh)
		 .append("</nsrsbh>").append("<sqxh>").append(sqxh)
		 .append("</sqxh>").append("</syptSwpjxx>")
		 .append("</syptRequest>");
		return xmlContent;
	}
	
	
	/**
	 * 获取全部税务数据拼装报文
	 * 
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static StringBuffer parseXmlContent(Map<String, Object> reqMap,
			String sqxh) {
		// 3.调用webservice获取局端税务数据
		
		StringBuffer xmlContent = new StringBuffer();
		
		String nsrsbh = (String) reqMap.get("NSRSBH");
		
		String dateyear = new SimpleDateFormat("yyyy").format(new Date());
		
		Integer dateyearInteger = Integer.valueOf(dateyear);

		Integer integer1 = dateyearInteger-3;
		Integer integer2 = dateyearInteger-2;
		Integer integer3 = dateyearInteger-1;
		
		//一定要顺着传年份  不然最新年的数据取不到    String nd = "2014,2015,2016,2017";
		String nd = integer1+","+integer2+","+integer3+","+dateyearInteger;
		
		//查询gs_syptqysdsnd 的年份 2014,2015,2016
		String ndnd = integer1+","+integer2+","+integer3;

		System.out.println(nd);
		
		xmlContent.append("<?xml version='1.0' encoding='GBK'?><syptRequest>")

		.append("<syptGsdjxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptGsdjxx>")
				.append("<syptSwdjxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptSwdjxx>")
				.append("<syptSwbgxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptSwbgxx>").append("<syptGdxx>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptGdxx>").append("<syptWfwzxx>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptWfwzxx>").append("<syptSsyhzcxx>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptSsyhzcxx>").append("<syptQsye>")
				.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
				.append("<sqxh>").append(sqxh).append("</sqxh>")
				.append("</syptQsye>").append("<syptQlqs>").append("<nsrsbh>")
				.append(nsrsbh).append("</nsrsbh>").append("<sqxh>")
				.append(sqxh).append("</sqxh>").append("</syptQlqs>")
				.append("<syptQysdsA1>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsA1>")
				.append("<syptQysdsA2>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsA2>")
				.append("<syptQysdsA3>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsA3>")
				.append("<syptQysdsB1>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsB1>")
				.append("<syptQysdsB2>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsB2>")
				.append("<syptQysdsB3>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptQysdsB3>")
				.append("<syptQysdsNd>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(ndnd)
				.append("</qsnd>").append("</syptQysdsNd>")
				.append("<syptZzsybnsr>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptZzsybnsr>")
				.append("<syptZzsxgm>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("<qsnd>").append(nd)
				.append("</qsnd>").append("</syptZzsxgm>")
				.append("<syptSwpjxx>").append("<nsrsbh>").append(nsrsbh)
				.append("</nsrsbh>").append("<sqxh>").append(sqxh)
				.append("</sqxh>").append("</syptSwpjxx>")

				.append("</syptRequest>");

		return xmlContent;
	}

	/**
	 * 光大银行取数报文
	 * 
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	public static StringBuffer parseXmlContentGD(Map<String, Object> reqMap,
			String sqxh) {
		// 3.调用webservice获取局端税务数据
		StringBuffer xmlContent = new StringBuffer();

		//String nsrsbh = (String) reqMap.get("NSRSBH");

		String dateyear = new SimpleDateFormat("yyyy").format(new Date());

		Integer dateyearInteger = Integer.valueOf(dateyear);
		
		Integer integer1 = dateyearInteger-3;
		Integer integer2 = dateyearInteger-2;
		Integer integer3 = dateyearInteger-1;
		
		//请求参数
		//一定要顺着传年份  不然最新年的数据取不到    String nd = "2014,2015,2016,2017";
		String nd = integer2+","+integer3+","+dateyearInteger;
		
		String qsnd = integer2+","+integer3+","+dateyearInteger;
		
		//查询gs_syptqysdsnd 的年份2014,2015,2016
		String ndnd = integer1+","+integer2+","+integer3;
		
		System.out.println(nd);

		Calendar calendar = Calendar.getInstance();
		int jn = calendar.get(1);

		String ksnd12 = String.valueOf(jn - 2);
		String yf = String.valueOf(calendar.get(2) + 1);
		String zznd12 = String.valueOf(jn);

		String nsrsbh = (String) reqMap.get("NSRSBH");

		xmlContent.append("<?xml version='1.0' encoding='GBK'?><syptRequest>")
		.append("<syptJsncwxxzcfzb>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptJsncwxxzcfzb>")
		.append("<syptJsncwxxlrb>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptJsncwxxlrb>")
		.append("<syptJsncwxxxjllb>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptJsncwxxxjllb>")
		.append("<syptGdyhSbxx>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptGdyhSbxx>")
		.append("<syptQydjxx>").append("<nsrsbh>")
		.append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("</syptQydjxx>")
		.append("<syptSsxx>").append("<nsrsbh>")
		.append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("<qsnd>").append(qsnd).append("</qsnd>")
		.append("</syptSsxx>")
		.append("<syptSsxxcx>").append("<nsrsbh>")
		.append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("<cxrqq>").append(ksnd12 + "-" + yf).append("</cxrqq>")
		.append("<cxrqz>").append(zznd12 + "-" + yf).append("</cxrqz>")
		.append("<cfts>").append("2").append("</cfts>")
		.append("</syptSsxxcx>")
		.append("<syptGsdjxx>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("</syptGsdjxx>")
		.append("<syptSwdjxx>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("</syptSwdjxx>")
		.append("<syptSwbgxx>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("</syptSwbgxx>").append("<syptGdxx>")
		.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("</syptGdxx>").append("<syptWfwzxx>")
		.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("</syptWfwzxx>").append("<syptSsyhzcxx>")
		.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("</syptSsyhzcxx>").append("<syptQsye>")
		.append("<nsrsbh>").append(nsrsbh).append("</nsrsbh>")
		.append("<sqxh>").append(sqxh).append("</sqxh>")
		.append("</syptQsye>").append("<syptQlqs>").append("<nsrsbh>")
		.append(nsrsbh).append("</nsrsbh>").append("<sqxh>")
		.append(sqxh).append("</sqxh>").append("</syptQlqs>")
		.append("<syptQysdsA1>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptQysdsA1>")
		.append("<syptQysdsA2>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptQysdsA2>")
		.append("<syptQysdsA3>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptQysdsA3>")
		.append("<syptQysdsB1>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptQysdsB1>")
		.append("<syptQysdsB2>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptQysdsB2>")
		.append("<syptQysdsB3>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptQysdsB3>")
		.append("<syptQysdsNd>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(ndnd)
		.append("</qsnd>").append("</syptQysdsNd>")
		.append("<syptZzsybnsr>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptZzsybnsr>")
		.append("<syptZzsxgm>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("<qsnd>").append(nd)
		.append("</qsnd>").append("</syptZzsxgm>")
		.append("<syptSwpjxx>").append("<nsrsbh>").append(nsrsbh)
		.append("</nsrsbh>").append("<sqxh>").append(sqxh)
		.append("</sqxh>").append("</syptSwpjxx>")
		.append("</syptRequest>");

		return xmlContent;

	}


	/**
	 * 获取资格认定税务数据拼装报文
	 * 
	 * @param dataObject
	 * @param sqxh
	 * @return
	 */
	
	public static String getZgrdMsg(String nsrsbh, String sqxh, String nsrmc,String frsjh) {
		StringBuffer xmlContent = new StringBuffer();

		xmlContent.append("<?xml version='1.0' encoding='GBK'?><syptRequest><syptZgrdsq><nsrsbh>");
		xmlContent.append(nsrsbh);
		xmlContent.append("</nsrsbh><nsrmc>");
		xmlContent.append(nsrmc);
		xmlContent.append("</nsrmc><sqxh>");
		xmlContent.append(sqxh);
		xmlContent.append("</sqxh><frsjh>");
		xmlContent.append(frsjh);
		xmlContent.append("</frsjh></syptZgrdsq></syptRequest>");

		String nrsData = InvokeCqWebService.sendMessageToGsWbjh(xmlContent.toString());

		return nrsData;
	}
	
}
