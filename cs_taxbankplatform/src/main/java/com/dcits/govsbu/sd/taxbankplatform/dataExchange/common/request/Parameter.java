package com.dcits.govsbu.sd.taxbankplatform.dataExchange.common.request;

/**
 * 功能:
 * 请求参数
 * @author Administrator
 */
public class Parameter {
	
	private String NSRDZDAH;				//登记序号
	private String NSRSBH;						//纳税人识别号
	private String NSRMC;						//纳税人名称
	private String SSSQ_Q;						//所属时期起
	private String SSSQ_Z;						//所属时期止
	private String LPBM;							//授权编码
	private String SQSJ;								//申请时间
	private String SQJE;								//申请金额
	private String YSXJE;							//预授信金额
	private String FKSJ;								//贷款开始时间
	private String JSSJ;								//贷款结束时间
	private String SXJE;								//授信金额
	private String MEMO;							//失败原因
	private String PRODUCT;					//产品名称
	private String PRODUCT_ID;			//产品编号
	private String BANK_ID;					//银行编号
	private String BANK_NAME;			//银行名称
	
	
	public String getNSRDZDAH() {
		return NSRDZDAH;
	}
	public void setNSRDZDAH(String nSRDZDAH) {
		NSRDZDAH = nSRDZDAH;
	}
	
	public String getNSRSBH() {
		return NSRSBH;
	}
	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}
	
	public String getNSRMC() {
		return NSRMC;
	}
	public void setNSRMC(String nSRMC) {
		NSRMC = nSRMC;
	}
	
	public String getSSSQ_Q() {
		return SSSQ_Q;
	}
	public void setSSSQ_Q(String sSSQ_Q) {
		SSSQ_Q = sSSQ_Q;
	}
	
	public String getSSSQ_Z() {
		return SSSQ_Z;
	}
	public void setSSSQ_Z(String sSSQ_Z) {
		SSSQ_Z = sSSQ_Z;
	}
	
	public String getLPBM() {
		return LPBM;
	}
	public void setLPBM(String lPBM) {
		LPBM = lPBM;
	}
	
	public String getSQSJ() {
		return SQSJ;
	}
	public void setSQSJ(String sQSJ) {
		SQSJ = sQSJ;
	}
	
	public String getSQJE() {
		return SQJE;
	}
	public void setSQJE(String sQJE) {
		SQJE = sQJE;
	}
	
	public String getYSXJE() {
		return YSXJE;
	}
	public void setYSXJE(String ySXJE) {
		YSXJE = ySXJE;
	}
	
	public String getFKSJ() {
		return FKSJ;
	}
	public void setFKSJ(String fKSJ) {
		FKSJ = fKSJ;
	}
	
	public String getJSSJ() {
		return JSSJ;
	}
	public void setJSSJ(String jSSJ) {
		JSSJ = jSSJ;
	}
	
	public String getSXJE() {
		return SXJE;
	}
	public void setSXJE(String sXJE) {
		SXJE = sXJE;
	}
	
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	
	public String getPRODUCT() {
		return PRODUCT;
	}
	public void setPRODUCT(String pRODUCT) {
		PRODUCT = pRODUCT;
	}
	
	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public void setPRODUCT_ID(String pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	
	public String getBANK_ID() {
		return BANK_ID;
	}
	public void setBANK_ID(String bANK_ID) {
		BANK_ID = bANK_ID;
	}
	
	public String getBANK_NAME() {
		return BANK_NAME;
	}
	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}
}