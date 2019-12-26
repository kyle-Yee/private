package com.dcits.govsbu.sd.taxbankplatform.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;



/**
 * 定义组织类型
 * @author admin
 * 组织类型基础工具类
 */
public class OrganizationsType {
	
	//----start   暂未使用    作为说明使用
	
	/**
	 * 纳税人组织类型code
	 */
	public static final String TAXPAYER = "taxpayer";   
	/**
	 * 微银管理员组织类型code
	 */
	public static final String MICROBANK = "micro_bank";   
	/**
	 * 银行组织类型code
	 */
	public static final String BANK = "bank";
	/**
	 * 监管机构组织类型code
	 */
	public static final String REGULATOR = "regulator";
	/**
	 * 税务局组织类型code
	 */
	public static final String TAXBUREAU = "tax_bureau";
	
	
	//----end
	
	
	
	/**
	 *  正在使用2016-8-24   用户登录查询列表数据过滤，用于queryListByPage 以及 queryListAll方法过滤   
	 */
	
	public static final String MICROBANKTYPE = "1";    //总部微银
	public static final String LOCAL_MICROBANKTYPE = "2"; //地区微银
	public static final String OTHERSTYPE = "3"; //其他

	/**
	 * 用户登录之后，根据不同的参数，得到返回参数，供查询作为筛选条件
	 *作为系统信息过滤
	 * @return
	 */
	public static Map<String,Object>  getParameters(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();				//用户登录之后  ，获取组织类型id
		
		Map<String, Object> parameters = new HashMap<String, Object>();	
		
		/**
		 * 总部人员regionid : KTQY0002017010100000000000 和 orgid : ZZ0002017010100000000000  因为tb_regions表和 tb_organizations表  系统会初始化一条数据
		 * ot_id  属于表tb_organizations_type表，是作为系统初始数据 
		 * ZZLX001 : 微银
		 * ZZLX002 : 银行
		 * ZZLX003 : 监管机构
		 * ZZLX004 : 税务局
		 */
		//总部人员登录
		if ("KTQY0002017010100000000000".equals(regionid) && "ZZ0002017010100000000000".equals(orgid)) {			
				parameters.put("usertype", OrganizationsType.MICROBANKTYPE);
		}else if (!"KTQY0002017010100000000000".equals(regionid) && !"ZZ0002017010100000000000".equals(orgid)) {
			switch (otid) {
			case "ZZLX001":
				//tb_organizations_type表中ot_id=1
			    //地区微银
				parameters.put("regionid", regionid);
				parameters.put("usertype", OrganizationsType.LOCAL_MICROBANKTYPE);
				break;
				
			case "ZZLX002":
				//tb_organizations_type表中ot_id=2
				//银行组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			case "ZZLX003":
				//tb_organizations_type表中ot_id=3
				//监管机构组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			case "ZZLX004":
				//tb_organizations_type表中ot_id=4
				//税务机构
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			case "ZZLX005":
				//tb_organizations_type表中ot_id=5
				//纳税人组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			default:
				//未定义的值
				throw new NullPointerException("Undefined Oragnization type");
			}
		}
		return parameters;
	}
	
	
	/**
	 * 用户登录之后，根据不同的参数，得到返回参数，供查询作为筛选条件
	 * 作为业务信息过滤
	 * @return
	 */
	public static Map<String,Object>  getWorkParameters(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();				//用户登录之后  ，获取组织类型id
		
		Map<String, Object> parameters = new HashMap<String, Object>();	
		
		/**
		 * 总部人员regionid 和 orgid 都为1   因为tb_regions表和 tb_organizations表  系统会初始化一条数据
		 * ot_id  属于表tb_organizations_type表，是作为系统初始数据 
		 */
		//总部人员登录
		if ("KTQY0002017010100000000000".equals(regionid) && "ZZ0002017010100000000000".equals(orgid)) {			
				parameters.put("usertype", OrganizationsType.MICROBANKTYPE);
		}else if (!"KTQY0002017010100000000000".equals(regionid) && !"ZZ0002017010100000000000".equals(orgid)) {
			switch (otid) {
			case "ZZLX001":
				//tb_organizations_type表中ot_id=1
			    //地区微银
				parameters.put("regionid", regionid);
				parameters.put("usertype", OrganizationsType.LOCAL_MICROBANKTYPE);
				break;
				
			case "ZZLX002":
				//tb_organizations_type表中ot_id=2
				//银行组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			case "ZZLX003":
				//tb_organizations_type表中ot_id=3
				//监管机构组织
				parameters.put("regionid", regionid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			case "ZZLX004":
				//tb_organizations_type表中ot_id=4
				//税务机构
				parameters.put("regionid", regionid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			case "ZZLX005":
				//tb_organizations_type表中ot_id=5
				//纳税人组织
				parameters.put("regionid", regionid);
				parameters.put("orgid", orgid);
				parameters.put("usertype", OrganizationsType.OTHERSTYPE);
				break;
			default:
				//未定义的值
				throw new NullPointerException("Undefined Oragnization type");
			}
		}
		return parameters;
	}
}
