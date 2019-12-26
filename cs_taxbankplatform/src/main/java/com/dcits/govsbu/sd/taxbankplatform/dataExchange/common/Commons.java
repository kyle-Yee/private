package com.dcits.govsbu.sd.taxbankplatform.dataExchange.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request.RequestConfig;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.TirIpPackage;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.content.BusinessContent;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.content.SubPackage;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.control.ContentControl;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.control.Control;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.identity.Identity;
import com.dcits.govsbu.sd.taxbankplatform.dataExchange.message.state.ReturnState;




/**
 * 功能: 加载配置服务, 获取请求报文, 报文加密解密
 * @author Administrator
 */
public class Commons {
	
	//SERVICE服务id集合
	private static HashMap<String, String> serviceidmap = new HashMap<String, String>();
	
	/**
	 * 功能:
	 * 读取XML配置的服务接口
	 * @return HashMap
	 */
	public static String achieveService(String p_serviceid) {
		try {
			if(serviceidmap.size() < 1){
				Document document = null;
				SAXReader saxReader = new SAXReader();
				document = saxReader.read(Commons.class.getResourceAsStream("/config/service/tax-service-conf.xml"));
				Element rootElt = document.getRootElement();
				Iterator<?> iterator = rootElt.elementIterator();
				while (iterator.hasNext()){
					Element recordEle = (Element) iterator.next();
					serviceidmap.put(recordEle.attributeValue("id"), recordEle.attributeValue("className"));
			    }
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceidmap.get(p_serviceid);
	}
	
	
	/**
	 * 功能:
	 * 获取请求报文根据不同的参数
	 * @return
	 */
	public static String achieveMessage(RequestConfig requestConfig) {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("GBK");
		Element root = document.addElement("tiripPackage", "http://www.chinatax.gov.cn/dataspec").addAttribute("version", "1.0");
		
		//交易标识
		Element identityEle = root.addElement("identity");
		identityEle.addElement("serviceId").addText(requestConfig.getServiceId());
		identityEle.addElement("password").addText(requestConfig.getPassword());
		
		//内容控制
		Element contentControlEle = root.addElement("contentControl");	
		Element controlEle1 = contentControlEle.addElement("control");
		controlEle1.addElement("id").setText("1");
		controlEle1.addElement("type").setText("zip");
		controlEle1.addElement("impl").setText("Zlib");
		
		Element controlEle2 = contentControlEle.addElement("control");
		controlEle2.addElement("id").setText("2");
		controlEle2.addElement("type").setText("crypt");
		controlEle2.addElement("impl").setText("**(CA类型)");
		
		Element controlEle3 = contentControlEle.addElement("control");
		controlEle3.addElement("id").setText("3");
		controlEle3.addElement("type").setText("code");
		controlEle3.addElement("impl").setText("Base64");
		//业务内容
		Element businessContentEle = root.addElement("businessContent");
		Element subPackageEle = businessContentEle.addElement("subPackage");
		subPackageEle.addElement("id").setText("1");
		subPackageEle.addElement("content").setText(GsonSingleton.getInstance().toJson(requestConfig.getParameter()));
		return document.asXML();
	}
	
	
	/**
	 * 功能:
	 * 加密message请求报文
	 * @param xml
	 * @return
	 */
	public static String achieveEncryption(String p_message) {
		
		return p_message;
	}
	
	
	/**
	 * 功能:
	 * 解密message响应报文
	 * @param message
	 * @return
	 */
	public static String achieveDecrypt(String p_message) {
		
		return p_message;
	}
	
	
	/**
	 * 功能:
	 * 将响应报文转换成对象
	 * @param xmlPath
	 * @return
	 */
	public static TirIpPackage parseFromXml(String p_message) {
		Document document =null;
		try {
			document = DocumentHelper.parseText(p_message);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TirIpPackage TirIpPackage = new TirIpPackage();
		Element rootElm = document.getRootElement();
		
		//报文交易标识
		Element identityEle = rootElm.element("identity");
		Identity identity = parseIdentityEle(identityEle);
		TirIpPackage.setIdentity(identity);
		
		//报文内容控制
		Element contentControlEle = rootElm.element("contentControl");
		@SuppressWarnings("unchecked")
		List<Element> controlEleList = contentControlEle.elements("control");
		List<Control> controls = new ArrayList<>();
		for (Element controlEle : controlEleList) {
			Control control = parseControlEle(controlEle);
			controls.add(control);
		}
		ContentControl contentControl = new ContentControl();
		contentControl.setControl(controls);
		TirIpPackage.setContentControl(contentControl);
		
		Element businessContentEle = rootElm.element("businessContent");
		List<SubPackage> subPackages = new ArrayList<SubPackage>();
		@SuppressWarnings("unchecked")
		List<Element> subPackageEleList = businessContentEle.elements("subPackage");
		for (Element subPackageEle : subPackageEleList) {
			SubPackage subPackage = parseSubPackageEle(subPackageEle);
			subPackages.add(subPackage);
		}
		BusinessContent businessContent = new BusinessContent();
		businessContent.setSubPackage(subPackages);
		TirIpPackage.setBusinessContent(businessContent);
		
		Element returnStateEle = rootElm.element("returnState");
		if(null !=returnStateEle){
			ReturnState returnState = parseReturnStateEle(returnStateEle);
			TirIpPackage.setReturnState(returnState);
		}
		return TirIpPackage;
	}

	
	/**
	 * 功能:解析报文交易标识
	 * @param identityEle
	 * @return
	 */
	private static Identity parseIdentityEle(Element identityEle) {
		Identity identity = new Identity();
		String serviceId = elementText(identityEle.element("serviceId"));
		String password = elementText(identityEle.element("password"));
		identity.setServiceId(serviceId);
		identity.setPassword(password);
		return identity;
	}

	
	/**
	 * 功能:解析报文内容控制
	 * @param controlEle
	 * @return
	 */
	private static Control parseControlEle(Element controlEle) {
		Control control = new Control();
		String id = elementText(controlEle.element("id"));
		String type = elementText(controlEle.element("type"));
		String impl = elementText(controlEle.element("impl"));
		control.setId(id);
		control.setType(type);
		control.setImpl(impl);
		return control;
	}
	
	
	/**
	 * 功能:解析报文业务信息
	 * @param subPackageEle
	 * @return
	 */
	private static SubPackage parseSubPackageEle(Element subPackageEle) {
		SubPackage subPackage= new SubPackage();
		String id = elementText(subPackageEle.element("id"));
		String content = elementText(subPackageEle.element("content"));
		subPackage.setId(id);
		subPackage.setContent(content);
		return subPackage;
	}
	
	
	/**
	 * 功能:解析报文响应状态
	 * @param returnStateEle
	 * @return
	 */
	private static ReturnState parseReturnStateEle(Element returnStateEle) {
		ReturnState returnState = new ReturnState();
		String returnCode = elementText(returnStateEle.element("returnCode"));
		String returnMessage = elementText(returnStateEle.element("returnMessage"));
		returnState.setReturnCode(returnCode);
		returnState.setReturnMessage(returnMessage);
		return returnState;
	}
	
	
	/**
	 * 功能:返回节点数据
	 * @param element
	 * @return
	 */
	private static String elementText(Element element) {
		if (element == null) {
			return "";
		}
		return element.getText();
	}
}
