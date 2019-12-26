package com.dcits.govsbu.sd.taxbankplatform.dkxx.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;


public class RegisterEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String nsryhxxId;
	//是否认证
	private String enabled;
	
	private String userId;
	//登记序号
	private String djxh;
	
	//新增数据返回ID（插入creatorId）
	private String userIds;
	
	//登录信息状态表的ID
	private String loginId;
	
	private String regionId;

	private String city;
	
	private String userName;
	
	private String password;
	
	//纳税人识别号
	private String single;
	
	//纳税人识别号
	private String nsrsbh;
	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	private String phone;
	
	private String nsrmc;
	//法人手机号
	private String frsjh;
		
	//企业名称
	private String qymc;
	
	//行业代码
	private String hydm;
	
	//法定代表人
	private String frmc;
	
	private String zcdz;
	//企业注册地址
	private String qyzcdz;
	//行政区划
	private String qyxzqh;

	//信用等级
	private String rank;
	
	//授权编码
	private String lpbm;
	
	//有效期
	private String yxqq;
	
	private String yxqz;
	
	//证件类型
	private String papersName;
	
	//证件号码
	private String papersNumb;
	
	private String newPassword;
	//用户协议标题
	private String title;
	//用户协议内容
	private String content;
	//职工人数
	private String zgrs;
	//认证时间
	private String rzsj;
	//审核结果
	private String shjg;
	//对比情况
	private String dbqk;
	//经营范围
	private String jyfw;
	//注册资本
	private String zczb;
	//成立时间
	private String clsj;
	//主管税务局
	private String zgswjdm;
	//渠道标识
	private String qdcode;
    //合同号有效标志
	private  String  yxs;
	
	public String getYxs() {
		return yxs;
	}

	public void setYxs(String yxs) {
		this.yxs = yxs;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public String getZczb() {
		return zczb;
	}

	public void setZczb(String zczb) {
		this.zczb = zczb;
	}

	public String getClsj() {
		return clsj;
	}

	public void setClsj(String clsj) {
		this.clsj = clsj;
	}

	public String getZgswjdm() {
		return zgswjdm;
	}

	public void setZgswjdm(String zgswjdm) {
		this.zgswjdm = zgswjdm;
	}

	private String swjgdm;
	

	private String passwordSalt;

	/*
	 * 加密盐
	 */
	private String credentialsSalt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZgrs() {
		return zgrs;
	}

	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}

	public String getRzsj() {
		return rzsj;
	}

	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getDbqk() {
		return dbqk;
	}

	public void setDbqk(String dbqk) {
		this.dbqk = dbqk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getYxqq() {
		return yxqq;
	}

	public void setYxqq(String yxqq) {
		this.yxqq = yxqq;
	}

	public String getYxqz() {
		return yxqz;
	}

	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getFrmc() {
		return frmc;
	}

	public void setFrmc(String frmc) {
		this.frmc = frmc;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getPapersName() {
		return papersName;
	}

	public void setPapersName(String papersName) {
		this.papersName = papersName;
	}

	public String getPapersNumb() {
		return papersNumb;
	}

	public void setPapersNumb(String papersNumb) {
		this.papersNumb = papersNumb;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

	@Override
	public Date getCreatetime() {
		return createtime;
	}

	@Override
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNsryhxxId() {
		return nsryhxxId;
	}

	public void setNsryhxxId(String nsryhxxId) {
		this.nsryhxxId = nsryhxxId;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSwjgdm() {
		return swjgdm;
	}

	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}

	public String getFrsjh() {
		return frsjh;
	}

	public void setFrsjh(String frsjh) {
		this.frsjh = frsjh;
	}


	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
	public String getQyzcdz() {
		return qyzcdz;
	}

	public void setQyzcdz(String qyzcdz) {
		this.qyzcdz = qyzcdz;
	}

	public String getQyxzqh() {
		return qyxzqh;
	}

	public void setQyxzqh(String qyxzqh) {
		this.qyxzqh = qyxzqh;
	}

	public String getDjxh() {
		return djxh;
	}

	public void setDjxh(String djxh) {
		this.djxh = djxh;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getHydm() {
		return hydm;
	}

	public void setHydm(String hydm) {
		this.hydm = hydm;
	}

	public String getLpbm() {
		return lpbm;
	}

	public void setLpbm(String lpbm) {
		this.lpbm = lpbm;
	}

	public String getQdcode() {
		return qdcode;
	}

	public void setQdcode(String qdcode) {
		this.qdcode = qdcode;
	}
}
