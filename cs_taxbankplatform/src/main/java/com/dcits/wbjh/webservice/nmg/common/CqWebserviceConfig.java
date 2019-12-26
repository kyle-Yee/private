package com.dcits.wbjh.webservice.nmg.common;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.wbjh.webservice.nmg.server.ServiceForSyptPoint;

public class CqWebserviceConfig {


	/**
	 * 静态获取接口服务
	 * @return
	 */
	public static ServiceForSyptPoint getService() throws Exception{

		// TODO Auto-generated method stub
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		// 注册WebService接口
		factory.setServiceClass(ServiceForSyptPoint.class);

		//获取Javabean方法
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

		ParametersService parametersService = (ParametersService) wac.getBean("parametersServiceImpl");
		// 从数据库查询出WebService地址
		String url = parametersService.QueryValueByCode("cq_tax_url");

		factory.setAddress(url);

		ServiceForSyptPoint  invoker = (ServiceForSyptPoint) factory.create();

		Client proxy = ClientProxy.getClient(invoker);

		HTTPConduit conduit = (HTTPConduit) proxy.getConduit();

		HTTPClientPolicy policy = new HTTPClientPolicy();

		policy.setConnectionTimeout(10000); //连接超时时间

		policy.setReceiveTimeout(30000);    //响应超时时间

		conduit.setClient(policy);

		return invoker;
	}


	/**
	 * 动态获取服务接口
	 * @param xmlString
	 * @return
	 */
	/*public static String invokeWcxfs(String xmlString){

		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		Client client = dcf.createClient("http://localhost:8081/wbjh-server-web/WSServiceInvokerForSypt?wsdl");

		// url为调用webService的wsdl地址
		QName name = new QName("http://server.nmg.webservice.wbjh.dcits.com/", "execute");

		// namespace是命名空间，methodName是方法名

		// paramvalue为参数值
		Object[] objects = null;
		try {
			objects = client.invoke(name, xmlString);
			System.out.println(objects[0].toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects[0].toString();
	}*/


}
