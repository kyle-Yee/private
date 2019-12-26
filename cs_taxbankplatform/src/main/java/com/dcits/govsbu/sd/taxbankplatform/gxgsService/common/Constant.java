package com.dcits.govsbu.sd.taxbankplatform.gxgsService.common;




/**
 * 和银行对接定义的常量
 * @author admin
 *
 */
public abstract class Constant {

	public static final String RTN_CODE = "RtnCode";
	public static final String RTN_MSG = "RtnMsg";
	//银行接口
	// 银行名称常量
	public static final String BANK_NAME = "桂林银行";
	
	public static final String BANK_RTN_CODE = "ResponseCode";
	public static final String BANK_RTN_MSG = "ResponseMsg";
	public static final String BANK_SERVICE_NAME = "serviceName";
	public static final String BANK_METHOD_NAME = "methodName";
	public static final String BANK_REQUEST_JSON = "requestJson";
	
	public static final String BANK_Response_SucessCode = "00";//请求成功
	public static final String BANK_Response_SucessCodeMsg = "完成";//请求成功

	public static final String BANK_Response_NoDataCode = "01";//没有查到数据
	public static final String BANK_Response_NoDataCodeMsg = "完成";//没有查到数据

	public static final String BANK_Response_ParamErrNullCode = "08";//纳税人识别号为空
	public static final String BANK_Response_ParamErrNullMsg = "纳税人识别号为空";//纳税人识别号为空
	
	public static final String BANK_Response_NoRzCode = "07";//没有进行税银税服务认证
	public static final String BANK_Response_NoRzCodeMsg = "未完成税银税服务认证";//没有进行税银税服务认证
	public static final String BANK_Response_NoGsCode = "09";//纳税人未查询到工商登记记录
	public static final String BANK_Response_NoGsCodeMsg = "纳税人未查询到工商登记记录";//纳税人未查询到工商登记记录
	
	public static final String BANK_Response_YESORNoXDSQ = "02";//该企业在税银平台存在其他未处理授信
	public static final String BANK_Response_YESORNoXDSQMsg = "该企业在税银平台存在其他未处理授信";//该企业在税银平台存在其他未处理授信
	
	public static final String BANK_Response_YESORNOGUOQI = "03";//该企业认证过时，请重新认证
	public static final String BANK_Response_YESORNOGUOQIMsg = "该企业认证过时，请重新认证";//该企业认证过时，请重新认证
	/*********start ************钟尤举 2018-04-18 新增小V调用税银平台获取税务数据请求，税银平台返给小V的状态码********************************************/
	// N0101:未认证 N0102:认证未通过  N0201:未授权  N0202:授权失败 N0203:不在授权期限内  N0204:授权开始结束时间为空或者格式异常
	public static final String BANK_Response_NoRzCodeN0101 = "07";
	public static final String BANK_Response_NoRzCodeMsgN0101 = "未认证";
	
	public static final String BANK_Response_NoRzCodeN0102 = "07";
	public static final String BANK_Response_NoRzCodeMsgN0102 = "认证未通过";
	
	public static final String BANK_Response_NoRzCodeN0201 = "07";
	public static final String BANK_Response_NoRzCodeMsgN0201 = "未授权";
	
	public static final String BANK_Response_NoRzCodeN0202 = "07";
	public static final String BANK_Response_NoRzCodeMsgN0202 = "授权失败";
	
	public static final String BANK_Response_NoRzCodeN0203 = "07";
	public static final String BANK_Response_NoRzCodeMsgN0203 = "不在授权期限内 ";
	
	public static final String BANK_Response_NoRzCodeN0204 = "07";
	public static final String BANK_Response_NoRzCodeMsgN0204 = "授权开始结束时间为空或者格式异常 ";
	
	public static final String BANK_Response_ResultStatus = "000000";
	public static final String BANK_Response_ResultMsg = "获取税务数据成功";
	/*********end *********************钟尤举 2018-04-18 新增小V调用税银平台获取税务数据请求，税银平台返给小V的状态码***********************************/
	
	public static final String BIZ_CODE = "BizCode";
	public static final String BIZ_MSG = "BizMsg";
	public static final String ATTACH_MSG = "AttachMsg";
	public static final String RTN_CODE_SUCCESS = "00";
	public static final String RTN_CODE_NODATA = "10";
	public static final String RTN_CODE_FAILURE = "99";
	public static final String PROGRESS_INFO_KEY = "PROGRESS_INFO_KEY";
	public static final String SESSION_VALIDCODE_KEY = "SESSION_VALIDCODE_KEY";
	public static final String SESSION_REMARK_KEY = "SESSION_REMARK_KEY";
	public static final String HTTP_SERVLET_RESPONSE = "HTTP_SERVLET_RESPONSE";
	public static final String HTTP_SERVLET_REQUEST = "HTTP_SERVLET_REQUEST";
	public static final String FILELIST = "FILELIST";
	public static final String PAGE_NUMBER = "pageNumber";
	public static final int DEFAULT_PAGE_NUMBER = 1;
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final String PAGE_SIZE = "pageSize";
	public static final String SERVICE_NAME = "serviceName";
	public static final String METHOD_NAME = "methodName";
	public static final String REQUEST_JSON = "requestJson";
	public static final String ZSX_JGBM = "100142";
	public static final String YYS_QYBM = "000000000000000";
	public static final String DEFAULT_PASSWORD = "88888888";
	public static final String DEFAULT_JZGLKHDCS = "00001";
	public static final String DEFAULT_JZGLZDGD = "00002";
	
	
	
	

}