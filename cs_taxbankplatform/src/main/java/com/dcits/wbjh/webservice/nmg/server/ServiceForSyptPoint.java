package com.dcits.wbjh.webservice.nmg.server;

import javax.jws.WebService;

@WebService
public interface ServiceForSyptPoint {
	public String execute(String reqXml);
}
