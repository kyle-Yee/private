package com.dcits.govsbu.sd.taxbankplatform.auth.model;

import java.io.Serializable;

/**
 * 
	* @author dms
	* @date 2019年1月16日
	* @Description:身份认证返回的数据实体类
 */

public class AuthResponseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

		private  String resultCode;
		
		private String resultMsg;
		
		private String grandCode;
		
		public AuthResponseEntity() {}
		
		public AuthResponseEntity(String resultCode,String resultMsg,String grandCode) {
			this.resultCode = resultCode;
			this.resultMsg = resultMsg;
			this.grandCode = grandCode;
		}
		
		public String getResultCode() {
			return resultCode;
		}

		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultMsg() {
			return resultMsg;
		}

		public void setResultMsg(String resultMsg) {
			this.resultMsg = resultMsg;
		}

		public String getGrandCode() {
			return grandCode;
		}

		public void setGrandCode(String grandCode) {
			this.grandCode = grandCode;
		}
		

	
}
