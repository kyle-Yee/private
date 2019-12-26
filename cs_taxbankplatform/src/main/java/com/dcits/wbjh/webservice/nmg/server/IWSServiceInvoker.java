package com.dcits.wbjh.webservice.nmg.server;

import javax.jws.WebService;

 
/**
 * 功能:重庆数据交换平台请求接口
 * @author Administrator
 */
@WebService
public interface IWSServiceInvoker {
	//请求接口
	
	public String execute(String arg0); 
	
}
