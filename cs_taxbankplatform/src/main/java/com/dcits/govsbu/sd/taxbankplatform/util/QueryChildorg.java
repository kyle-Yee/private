package com.dcits.govsbu.sd.taxbankplatform.util;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;


/**
 * 根据权限查询组织
 * @author liwangxiong
 *
 */
public class QueryChildorg {

	//获取当前节点下所有的子节点（包括当前节点）的所有组织id的字符串
	public static String getorglistIdquery(List <OrganizationEntity> organizationList,String pid){
		StringBuffer sb = new StringBuffer();
		if (pid != null) {
			OrganizationFind find = new OrganizationFind();
			List<OrganizationEntity> child = find.findChildOrg(organizationList, pid);
			for (OrganizationEntity organizationEntity : child) {
				String orgid = organizationEntity.getId().toString();
				sb.append(orgid+",");
			}
			sb.append(String.valueOf(pid));
		}
		return sb.toString();
	}
	
	//获取区域级别比当前级别低的所有组织id的字符串
	public static String getorglistIdclass(List <OrganizationEntity> organizationList,int regionclass){
		StringBuffer sb = new StringBuffer();
		if (Integer.valueOf(regionclass) != null || organizationList.size()>0) {
			for (OrganizationEntity organizationEntity : organizationList) {
				if (Integer.valueOf(organizationEntity.getRcid()) >= regionclass) {
					String orgid = organizationEntity.getId().toString();
					sb.append(orgid+",");
				}
			}
		}
		if (','==sb.charAt(sb.length()-1)) {
			sb = sb.deleteCharAt(sb.length() - 1); 
		}
		return sb.toString();
	}
	
	
	
	//获取当前区域下的所有子区域的id
	public static String getregionidByregioncode(List<ProvinceCitiesEntity> citiesEntities ,String parentpid){
		StringBuffer sb = new StringBuffer();
		if (null != Integer.valueOf(parentpid)) {
			OrganizationFind find = new OrganizationFind();
			List<ProvinceCitiesEntity> child = find.findChildRegion(citiesEntities, parentpid);
			for (ProvinceCitiesEntity citiesEntity : child) {
				String pccode = citiesEntity.getPccode().toString();
				sb.append(pccode+",");
			}
		}
		if (','==sb.charAt(sb.length()-1)) {
			sb = sb.deleteCharAt(sb.length() - 1); 
		}
		return sb.toString();
	}
}
