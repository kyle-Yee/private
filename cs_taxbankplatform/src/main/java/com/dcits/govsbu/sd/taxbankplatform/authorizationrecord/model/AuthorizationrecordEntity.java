package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;

public class AuthorizationrecordEntity  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 授权编号
	 */
	private String at_id;
	/**
	 * 申请时间
	 */
	private String at_sqsj;
	/**
	 * 企业名称
	 */
	private String at_qymc;
	/**
	 * 纳税人识别号
	 */
	private String at_nsrsbh;
	/**
	 * 授权开始时间
	 */
	private String at_sqkssj;
	/**
	 * 授权结束时间
	 */
	private String at_sqjssj;
	/**
	 * 授权时间期限
	 */
	private String at_sqsjqx;
	/**
	 * 申请状态
	 */
	private Integer at_sqzt;
	/**
	 * 申请原因
	 */
	private String at_sqyy;
	
	/**
	 * 法人姓名
	 */
	private String at_frxm;
	
	/**
	 * 法人身份证
	 */
	private String at_frsfz;
	
	/**
	 * 税局名称
	 */
	private String at_sjmc;
	/**
	 * 组织id
	 */
	private String org_id;
	/**
	 * 产品id
	 */
	private String fp_id;
	/**
	 * 协议id
	 */
	private String ag_id;
	/**
	 * 授权书有效期
	 */
    private String at_sqsyxq;
    /**
     * 申请单编号
     */
    private String at_sqdid;
    /**
     * 申请单
     */
    private String la_id;
	/**
	 * 授权状态
	 */
	private String sqzt;
	/**
	 * 授权原因
	 */
	private String sqjg;
	/**
	 * 关联组织表
	 */
	private OrganizationEntity organizationEntity;
	/**
	 * 关联产品表
	 */
	private FinancialProduct financialProduct;
	/**
	 * 关联产品申请初始表
	 */
	private LoanApplyQueryEntity loanApplyQueryEntity;
	/**
	 * 授权编码
	 */
	private String grantCode;
	/**
	 * 认证授权方式
	 */
	private String authType;
	
	/**
	 * 贷款期限
	 * */
	private String at_dkqx;
	
	public String getAt_dkqx() {
		return at_dkqx;
	}
	public void setAt_dkqx(String at_dkqx) {
		this.at_dkqx = at_dkqx;
	}
	public String getGrantCode() {
		return grantCode;
	}
	public void setGrantCode(String grantCode) {
		this.grantCode = grantCode;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	
	public String getSqjg() {
		return sqjg;
	}
	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}
	public String getLa_id() {
		return la_id;
	}
	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}
	public String getAt_id() {
		return at_id;
	}
	public void setAt_id(String at_id) {
		this.at_id = at_id;
	}
	public String getAt_sqsj() {
		return at_sqsj;
	}
	public void setAt_sqsj(String at_sqsj) {
		this.at_sqsj = at_sqsj;
	}
	public String getAt_qymc() {
		return at_qymc;
	}
	public void setAt_qymc(String at_qymc) {
		this.at_qymc = at_qymc;
	}
	public String getAt_nsrsbh() {
		return at_nsrsbh;
	}
	public void setAt_nsrsbh(String at_nsrsbh) {
		this.at_nsrsbh = at_nsrsbh;
	}
	public String getAt_sqkssj() {
		return at_sqkssj;
	}
	public void setAt_sqkssj(String at_sqkssj) {
		this.at_sqkssj = at_sqkssj;
	}
	public String getAt_sqjssj() {
		return at_sqjssj;
	}
	public void setAt_sqjssj(String at_sqjssj) {
		this.at_sqjssj = at_sqjssj;
	}
	public Integer getAt_sqzt() {
		return at_sqzt;
	}
	public void setAt_sqzt(Integer at_sqzt) {
		this.at_sqzt = at_sqzt;
	}
	public String getAt_sqyy() {
		return at_sqyy;
	}
	public void setAt_sqyy(String at_sqyy) {
		this.at_sqyy = at_sqyy;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getFp_id() {
		return fp_id;
	}
	public void setFp_id(String fp_id) {
		this.fp_id = fp_id;
	}
	public String getAg_id() {
		return ag_id;
	}
	public void setAg_id(String ag_id) {
		this.ag_id = ag_id;
	}
	public String getAt_sqsyxq() {
		return at_sqsyxq;
	}
	public void setAt_sqsyxq(String at_sqsyxq) {
		this.at_sqsyxq = at_sqsyxq;
	}
	public OrganizationEntity getOrganizationEntity() {
		return organizationEntity;
	}
	public void setOrganizationEntity(OrganizationEntity organizationEntity) {
		this.organizationEntity = organizationEntity;
	}
	public FinancialProduct getFinancialProduct() {
		return financialProduct;
	}
	public void setFinancialProduct(FinancialProduct financialProduct) {
		this.financialProduct = financialProduct;
	}
	public LoanApplyQueryEntity getLoanApplyQueryEntity() {
		return loanApplyQueryEntity;
	}
	public void setLoanApplyQueryEntity(LoanApplyQueryEntity loanApplyQueryEntity) {
		this.loanApplyQueryEntity = loanApplyQueryEntity;
	}
	public String getAt_sqsjqx() {
		return at_sqsjqx;
	}
	public void setAt_sqsjqx(String at_sqsjqx) {
		this.at_sqsjqx = at_sqsjqx;
	}
	public String getAt_frxm() {
		return at_frxm;
	}
	public void setAt_frxm(String at_frxm) {
		this.at_frxm = at_frxm;
	}
	public String getAt_frsfz() {
		return at_frsfz;
	}
	public void setAt_frsfz(String at_frsfz) {
		this.at_frsfz = at_frsfz;
	}
	public String getAt_sjmc() {
		return at_sjmc;
	}
	public void setAt_sjmc(String at_sjmc) {
		this.at_sjmc = at_sjmc;
	}
	public String getAt_sqdid() {
		return at_sqdid;
	}
	public void setAt_sqdid(String at_sqdid) {
		this.at_sqdid = at_sqdid;
	}
	
	
}
