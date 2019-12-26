package com.dcits.govsbu.sd.taxbankplatform.util;

import java.util.ArrayList;
import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;



public class OrganizationFind {
	List<OrganizationEntity> childOrg = new ArrayList<OrganizationEntity>();
	
	List<ProvinceCitiesEntity> childcitycode = new ArrayList<ProvinceCitiesEntity>();

	public List<OrganizationEntity> findChildOrg(List<OrganizationEntity> OrgList,String pid){
		for (OrganizationEntity org : OrgList) {
			String uporgid = org.getUpOrgId();
			String orgid = org.getId();
			if (uporgid != orgid) {
				if (uporgid != null) {
					if (uporgid.equals(pid)) {
						//递归遍历下一级  
						findChildOrg(OrgList,org.getId());  
						childOrg.add(org); 
					}
				}
			}
		}
		return childOrg;
	}

	
	public List<ProvinceCitiesEntity> findChildRegion(List<ProvinceCitiesEntity> PcList,String parentpid){
		for (ProvinceCitiesEntity pc : PcList) {
			String pcid = pc.getId();
			String pcpid = pc.getPcpid();
			if (pcpid != pcid) {
				if (Integer.valueOf(pcpid) != 0) {
					if (pcpid.equals(parentpid)) {
						//递归遍历下一级  
						findChildRegion(PcList,pc.getId());  
						childcitycode.add(pc); 
					}
				}
			}
			
		}
		return childcitycode;
	}
}
