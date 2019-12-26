package com.dcits.govsbu.sd.taxbankplatform.cqsyyhtotax;

import com.dcits.wbjh.webservice.nmg.common.CqWebserviceConfig;
import com.dcits.wbjh.webservice.nmg.message.MessageBuider;
import com.dcits.wbjh.webservice.nmg.message.TaxMessageHelper;
import com.dcits.wbjh.webservice.nmg.message.dataExchangePackage;
import com.dcits.wbjh.webservice.nmg.server.ServiceForSyptPoint;

public class InvokeCqWebService {
	
	/**
	 * 访问国税数据交换平台
	 * @param xmlConent
	 * @return
	 */
	public synchronized static String sendMessageToGsWbjh(String xmlConent) {
		String repXml = "";
		try {
			//税银平台访问国税
			String srcId = TaxMessageHelper.DEST_SYPT;
			String desId = TaxMessageHelper.DEST_TAX;
			dataExchangePackage respPack = TaxMessageHelper.buildMessBusiness(srcId,desId, xmlConent);
			
			String reqXml = MessageBuider.toXml(respPack);
			System.out.println("reqXml====="+reqXml);
			
			//获取服务
			ServiceForSyptPoint invoker = CqWebserviceConfig.getService();
			
			//调用webservice接口
			repXml =  invoker.execute(reqXml);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repXml;
	}
	
	/**
	 * 访问地税数据交换平台
	 * @param xmlConent
	 */
	/*public static void sendMessageToDsWbjh(String xmlConent) {
		try {
			//税银平台访问地税参数
			String srcId = TaxMessageHelper.DEST_SYPT;
			String desId = TaxMessageHelper.DEST_TAX;
			dataExchangePackage respPack = TaxMessageHelper.buildMessBusiness(srcId,
					desId, xmlConent);
			SyptPointWsClient.sendMess(respPack);
			
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*public static String getBussines(String serviceId,List<String> paramNames,List<String> paramValues,String nameSpace){
		
		if("".equals(nameSpace)){
			nameSpace="http://server.nmg.webservice.wbjh.dcits.com/";
		}
		if("".equals(serviceId)){
			serviceId="execute";
		}
		
		String url=PopertiesConfig.getWsdlForSypt();

		String re = SoapCXFUtil.invokeWs(url, nameSpace, serviceId,
				paramNames, paramValues);
		String re = "";
		return re;
	}*/
	
	/*public static Map getNsrxx(String nsrsbh){
		
		StringBuffer xmlContent=new StringBuffer();
		
		xmlContent.append("<?xml version='1.0' encoding='GBK'?><syptQydjxx>")
					.append("<nsrsbhss").append(nsrsbh).append("</nsrsbh>")
					.append("</syptQydjxx>");
		
		List<String> paramNames = new ArrayList<String>();
		List<String> paramValues = new ArrayList<String>();
		paramNames.add("arg0");
		paramValues.add("WA4110");

		paramNames.add("arg1");
		paramValues.add("getNsqk");

		paramNames.add("arg2");
		paramValues.add(xmlContent.toString());
		
		String rtn = getBussines("",paramNames,paramValues,"");
		Map<String, String> map = new HashMap<String, String>();
		
		try{
			Document document = DocumentHelper.parseText(rtn);
			
			Element root=document.getRootElement(); 
			
			String bsucc=getItemValue(root,"bsucc");
			
			//todo 拼装map
		}
		catch(Exception e){
			
		}
		map.put("Content", rtn);
		
		return map;
	}
	
	public static String getItemValue(Element e,String nodeName){
		Element node=e.element(nodeName);
		String returnV=node.getText();
		return returnV;
	}
*/
}
