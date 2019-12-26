/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;

/**
 * @author 胡宝龙2016-8-25 下午5:44:49
 *
 */
public class Permission {
	public static Map<String,Object>  getParameters(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();
		
		Map<String, Object> parameters = new HashMap<String, Object>();	
		parameters.put("otid", otid);

		if("KTQY0002017010100000000000".equals(regionid) && "ZZ0002017010100000000000".equals(orgid)){ //总部人员登录
			parameters.put("regionid", null); //判断regionid 为null的时候   显示所有地方的所有产品
			parameters.put("orgid", null); //orgid为null的时候  显示所有组织的所有产品
			parameters.put("readOnly", true); //只能查看数据，不能进行操作
			parameters.put("regionShow",true); //是否需要显示区域列
			parameters.put("orgShow",true); //是否需要显示组织列
			
		}else if (!"KTQY0002017010100000000000".equals(regionid) && !"ZZ0002017010100000000000".equals(orgid)) {
			switch (otid) {
			case "ZZLX001":
				//tb_organizations_type表中ot_id=1
			    //地区微银
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("readOnly", true);
				parameters.put("regionShow",false);
				parameters.put("orgShow",true);
				break;
				
			case "ZZLX002":
				//tb_organizations_type表中ot_id=2
				//银行组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("readOnly", false);
				parameters.put("regionShow",false);
				parameters.put("orgShow",false);
				break;
			case "ZZLX003":
				//tb_organizations_type表中ot_id=3
				//监管机构组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("readOnly", true);
				parameters.put("regionShow",false);
				parameters.put("orgShow",true);
				break;
			case "ZZLX004":
				//tb_organizations_type表中ot_id=4
				//税务机构
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("readOnly", true);
				parameters.put("regionShow",false);
				parameters.put("orgShow",true);
				break;
			case "ZZLX005":
				//tb_organizations_type表中ot_id=5
				//纳税人组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("readOnly", true);
				parameters.put("regionShow",false);
				parameters.put("orgShow",true);
				break;
			default:
				//未定义的值
				throw new NullPointerException("Undefined Oragnization type");
			}
		}
		return parameters;	
	}
}
