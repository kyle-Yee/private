package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.teplate;
/***
 * 授权编码验证返回代码
 * */
public enum  AuthorizeEnum {
		DM00("00", "成功"),
		DM08("08", "参数错误"), 
		DM09("09", "系统异常"),
		DM02("02", "验证码与纳税人信息不匹配"),
		DM03("03", "授权编码过期"),
		DM04("04", "无效授权码"),
		DM10("10", "授权失败"),
		DM11("11", "已经存在认证记录");
	    private String statusCode ;
	    private String statusMsg;
	     
	    private AuthorizeEnum( String statusCode , String statusMsg ){
	        this.statusCode = statusCode ;
	        this.statusMsg = statusMsg ;
	    }

		public String getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}

		public String getStatusMsg() {
			return statusMsg;
		}

		public void setStatusMsg(String statusMsg) {
			this.statusMsg = statusMsg;
		}
	     
}
