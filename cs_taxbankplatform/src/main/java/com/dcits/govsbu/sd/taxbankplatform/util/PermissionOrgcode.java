/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;

/**
 * @author  yaofang
 *
 */
public class PermissionOrgcode {
	public static Map<String,Object>  getParameters(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();
		String orgcode=sessionUser.getOrganizationEntity().getOrgcode();
		Map<String, Object> parameters = new HashMap<String, Object>();	
		parameters.put("orgcode", orgcode);

		if("KTQY0002017010100000000000".equals(regionid) && "ZZ0002017010100000000000".equals(orgid)){ //总部人员登录
			parameters.put("regionid", null); //判断regionid 为null的时候   显示所有地方的所有产品
			parameters.put("orgid", null); //orgid为null的时候  显示所有组织的所有产品
			parameters.put("readOnly", true); //只能查看数据，不能进行操作
			parameters.put("regionShow",true); //是否需要显示区域列
			parameters.put("orgShow",true); //是否需要显示组织列
			parameters.put("ptdm",true);//隐藏添加信用卡按钮
			
		}else if (!"KTQY0002017010100000000000".equals(regionid) && !"ZZ0002017010100000000000".equals(orgid)) {
			switch (orgcode) {
			case "ZGGDYH":
			    //重庆光大银行-信用卡产品   允许操作
				parameters.put("ptdm",false);
				break;
			case "cqgs10001":
				  //重庆工商银行银行-信用卡产品   允许操作
				parameters.put("ptdm",false);
				break;
			default:
				//其他银行  不允许操作
				parameters.put("ptdm",true);
				break;
			}
		}
		return parameters;	
	}
}
