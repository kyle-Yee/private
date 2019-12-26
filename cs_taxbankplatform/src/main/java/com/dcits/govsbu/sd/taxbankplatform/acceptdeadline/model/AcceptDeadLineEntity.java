package com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.model;

import java.util.Date;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class AcceptDeadLineEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
		
	    private String tl_id;
	    
		private String regionId;

	    private String orgId;

	    private String laId;

	    private Long tlTotalDays;

	    private Date createTime;

	    private String createUser;
        private String regionName;
        private String orgName;
        
        
        
		public String getTl_id() {
			return tl_id;
		}

		public void setTl_id(String tl_id) {
			this.tl_id = tl_id;
		}

		public String getRegionName() {
			return regionName;
		}

		public void setRegionName(String regionName) {
			this.regionName = regionName;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		public String getRegionId() {
			return regionId;
		}

		public void setRegionId(String regionId) {
			this.regionId = regionId;
		}

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		public String getLaId() {
			return laId;
		}

		public void setLaId(String laId) {
			this.laId = laId;
		}

		public Long getTlTotalDays() {
			return tlTotalDays;
		}

		public void setTlTotalDays(Long tlTotalDays) {
			this.tlTotalDays = tlTotalDays;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getCreateUser() {
			return createUser;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}
}
