package com.dcits.govsbu.sd.taxbankplatform.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;




/**
 * 定义组织类型
 * @author admin
 * 组织类型基础工具类
 */
public class OrganizationsUtil {
	
	public static Map<String,Object>  getParameters(List<OrganizationEntity> listorg){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();				//用户登录之后  ，获取组织类型id
		String regionclass = sessionUser.getOrganizationEntity().getRcid();//用户登录之后  ，获取区域级别
		
		Map<String, Object> parameters = new HashMap<String, Object>();	
		parameters.put("otid", otid);
		
		/**
		 * 总部人员regionid 和 orgid 都为1   因为tb_regions表和 tb_organizations表  系统会初始化一条数据
		 * ot_id  属于表tb_organizations_type表，是作为系统初始数据 
		 */
		//总部人员登录

		if(("KTQY0002017010100000000000").equals(regionid)&&("ZZ0002017010100000000000").equals(orgid)){ //总部人员登录
			parameters.put("regionid", null); //判断regionid 为null的时候   显示所有地方的所有产品
			parameters.put("orgid", null); //orgid为null的时候  显示所有组织的所有产品
			parameters.put("readOnly", true); //只能查看数据，不能进行操作
			parameters.put("faqReadOnly",true); //常见问题编辑权限,false为可以编辑
			parameters.put("regionShow",true); //是否需要显示区域列
			parameters.put("orgShow",true); //是否需要显示组织列
										
		}else if (!("KTQY0002017010100000000000").equals(regionid)&&!("ZZ0002017010100000000000").equals(orgid)) {
			if (("ZZLX001").equals(otid)) {							//地区微银	
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String orgidlist2 = QueryChildorg.getorglistIdclass(listorg, 2);
					parameters.put("orgidlist", orgidlist2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String orgidlist3 = QueryChildorg.getorglistIdclass(listorg, 3);
					parameters.put("orgidlist", orgidlist3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else if (("ZZLX002").equals(otid)) {						//银行组织
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					String orgidlist1 = QueryChildorg.getorglistIdquery(listorg, orgid);
					parameters.put("orgidlist", orgidlist1);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", false);
					parameters.put("faqReadOnly", false);
					parameters.put("regionShow",false);
					parameters.put("orgShow",false);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String orgidlist2 = QueryChildorg.getorglistIdquery(listorg, orgid);
					parameters.put("orgidlist", orgidlist2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", false);
					parameters.put("faqReadOnly", false);
					parameters.put("regionShow",false);
					parameters.put("orgShow",false);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String orgidlist3 = QueryChildorg.getorglistIdquery(listorg, orgid);
					parameters.put("orgidlist", orgidlist3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", false);
					parameters.put("faqReadOnly", false);
					parameters.put("regionShow",false);
					parameters.put("orgShow",false);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else if (("ZZLX003").equals(otid)) {						//监管机构组织
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String orgidlist2 = QueryChildorg.getorglistIdclass(listorg, 2);
					parameters.put("orgidlist", orgidlist2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String orgidlist3 = QueryChildorg.getorglistIdclass(listorg, 3);
					parameters.put("orgidlist", orgidlist3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else if (("ZZLX004").equals(otid)) {						//税务机构
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String orgidlist2 = QueryChildorg.getorglistIdclass(listorg, 2);
					parameters.put("orgidlist", orgidlist2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String orgidlist3 = QueryChildorg.getorglistIdclass(listorg, 3);
					parameters.put("orgidlist", orgidlist3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else {												//未定义的值
				throw new NullPointerException("Undefined Oragnization type");
			}
		}
		return parameters;
	}

	public static Map<String,Object>  getPara(List<OrganizationEntity> listorg,List<ProvinceCitiesEntity> listpccity,String parentpid){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();				//用户登录之后  ，获取组织类型id
		String regionclass = sessionUser.getOrganizationEntity().getRcid();//用户登录之后  ，获取区域级别
		String pccode  = sessionUser.getRegionsEntity().getRegioncode();
		
		
		Map<String, Object> parameters = new HashMap<String, Object>();	
		parameters.put("otid", otid);
		
		/*String pccodes = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
		System.out.println(pccodes);*/
		
		/**
		 * 总部人员regionid 和 orgid 都为1   因为tb_regions表和 tb_organizations表  系统会初始化一条数据
		 * ot_id  属于表tb_organizations_type表，是作为系统初始数据 
		 */
		//总部人员登录
		if (("KTQY0002017010100000000000").equals(regionid)&&("ZZ0002017010100000000000").equals(orgid)) {	//总部人员登录	
			parameters.put("regionid", null); //判断regionid 为null的时候   显示所有地方的所有产品
			parameters.put("orgid", null); //orgid为null的时候  显示所有组织的所有产品
			parameters.put("readOnly", true); //只能查看数据，不能进行操作
			parameters.put("faqReadOnly",true); //常见问题编辑权限,false为可以编辑
			parameters.put("regionShow",true); //是否需要显示区域列
			parameters.put("orgShow",true); //是否需要显示组织列
										
		}else if (!("KTQY0002017010100000000000").equals(regionid)&&!("ZZ0002017010100000000000").equals(orgid)) {
			if (("ZZLX001").equals(otid)) {							//地区微银	
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					String pccodes1 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes1);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String pccodes2 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String pccodes3 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else if (("ZZLX002").equals(otid)) {						//银行组织
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					String orgidlist1 = QueryChildorg.getorglistIdquery(listorg, orgid);
					parameters.put("orgidlist", orgidlist1);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", false);
					parameters.put("faqReadOnly", false);
					parameters.put("regionShow",false);
					parameters.put("orgShow",false);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String orgidlist2 = QueryChildorg.getorglistIdquery(listorg, orgid);
					parameters.put("orgidlist", orgidlist2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", false);
					parameters.put("faqReadOnly", false);
					parameters.put("regionShow",false);
					parameters.put("orgShow",false);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String orgidlist3 = QueryChildorg.getorglistIdquery(listorg, orgid);
					parameters.put("orgidlist", orgidlist3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", false);
					parameters.put("faqReadOnly", false);
					parameters.put("regionShow",false);
					parameters.put("orgShow",false);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else if (("ZZLX003").equals(otid)) {						//监管机构组织
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					String pccodes1 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes1);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String pccodes2 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String pccodes3 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else if (("ZZLX004").equals(otid)) {						//税务机构
				switch (regionclass) {
				case "QYJB001":
					parameters.put("regionid", regionid);
					String pccodes1 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes1);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
				case "QYJB002":
					parameters.put("regionid", regionid);
					String pccodes2 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes2);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				case "QYJB003":
					parameters.put("regionid", regionid);
					String pccodes3 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+pccode;
					parameters.put("pccodes", pccodes3);
					parameters.put("orgid", orgid);
					parameters.put("readOnly", true);
					parameters.put("faqReadOnly", true);
					parameters.put("regionShow",false);
					parameters.put("orgShow",true);
					break;
				default:
					throw new NullPointerException("Undefined Oragnization type");
				}
			}else {												//未定义的值
				throw new NullPointerException("Undefined Oragnization type");
			}
		}
		return parameters;
	}
	
	/**
	 */
	public static Map<String,Object>  getParament(List<OrganizationEntity> listorg,List<ProvinceCitiesEntity> listpccity,
			UserEntity sessionUser,ProvinceCitiesEntity citiesEntity){
		String regionid = sessionUser.getRegionid();     							//用户登录之后  ，获取区域id
		String orgid = sessionUser.getOrgid();			 						//用户登录之后  ，获取组织id
		String otid = sessionUser.getOrganizationEntity().getOtid();				//用户登录之后  ，获取组织类型id
		String regionclass = sessionUser.getOrganizationEntity().getRcid();//用户登录之后  ，获取区域级别
		String regioncode  = sessionUser.getRegionsEntity().getRegioncode();
		
		Map<String, Object> parameters = new HashMap<String, Object>();	
		parameters.put("otid", otid);
		
		if (null != citiesEntity) {
			String parentpid = citiesEntity.getId();
				/**
				 * 总部人员regionid 和 orgid 都为1   因为tb_regions表和 tb_organizations表  系统会初始化一条数据
				 * ot_id  属于表tb_organizations_type表，是作为系统初始数据 
				 */
				//总部人员登录
				if (("KTQY0002017010100000000000").equals(regionid)&&("ZZ0002017010100000000000").equals(orgid)) {	//总部人员登录	
					parameters.put("regionid", null); //判断regionid 为null的时候   显示所有地方的所有产品
					parameters.put("orgid", null); //orgid为null的时候  显示所有组织的所有产品
					parameters.put("readOnly", true); //只能查看数据，不能进行操作
					parameters.put("faqReadOnly",true); //常见问题编辑权限,false为可以编辑
					parameters.put("regionShow",true); //是否需要显示区域列
					parameters.put("orgShow",true); //是否需要显示组织列
					
				}else if (!("KTQY0002017010100000000000").equals(regionid)&&!("ZZ0002017010100000000000").equals(orgid)) {
					if (("ZZLX001").equals(otid)) {							//地区微银	
						switch (regionclass) {
						case "QYJB001":
							parameters.put("regionid", regionid);
							String pccodes1 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes1);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
						case "QYJB002":
							parameters.put("regionid", regionid);
							String pccodes2 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes2);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
							break;
						case "QYJB003":
							parameters.put("regionid", regionid);
							String pccodes3 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes3);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
							break;
						default:
							throw new NullPointerException("Undefined Oragnization type");
						}
					}else if (("ZZLX002").equals(otid)) {						//银行组织
						switch (regionclass) {
						case "QYJB001":
							parameters.put("regionid", regionid);
							String orgidlist1 = QueryChildorg.getorglistIdquery(listorg, orgid);
							parameters.put("orgidlist", orgidlist1);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", false);
							parameters.put("faqReadOnly", false);
							parameters.put("regionShow",false);
							parameters.put("orgShow",false);
						case "QYJB002":
							parameters.put("regionid", regionid);
							String orgidlist2 = QueryChildorg.getorglistIdquery(listorg, orgid);
							parameters.put("orgidlist", orgidlist2);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", false);
							parameters.put("faqReadOnly", false);
							parameters.put("regionShow",false);
							parameters.put("orgShow",false);
							break;
						case "QYJB003":
							parameters.put("regionid", regionid);
							String orgidlist3 = QueryChildorg.getorglistIdquery(listorg, orgid);
							parameters.put("orgidlist", orgidlist3);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", false);
							parameters.put("faqReadOnly", false);
							parameters.put("regionShow",false);
							parameters.put("orgShow",false);
							break;
						default:
							throw new NullPointerException("Undefined Oragnization type");
						}
					}else if (("ZZLX003").equals(otid)) {						//监管机构组织
						switch (regionclass) {
						case "QYJB001":
							parameters.put("regionid", regionid);
							String pccodes1 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes1);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
						case "QYJB002":
							parameters.put("regionid", regionid);
							String pccodes2 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes2);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
							break;
						case "QYJB003":
							parameters.put("regionid", regionid);
							String pccodes3 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes3);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
							break;
						default:
							throw new NullPointerException("Undefined Oragnization type");
						}
					}else if (("ZZLX004").equals(otid)) {						//税务机构
						switch (regionclass) {
						case "QYJB001":
							parameters.put("regionid", regionid);
							String pccodes1 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes1);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
						case "QYJB002":
							parameters.put("regionid", regionid);
							String pccodes2 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes2);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
							break;
						case "QYJB003":
							parameters.put("regionid", regionid);
							String pccodes3 = QueryChildorg.getregionidByregioncode(listpccity, parentpid)+","+regioncode;
							parameters.put("pccodes", pccodes3);
							parameters.put("orgid", orgid);
							parameters.put("readOnly", true);
							parameters.put("faqReadOnly", true);
							parameters.put("regionShow",false);
							parameters.put("orgShow",true);
							break;
						default:
							throw new NullPointerException("Undefined Oragnization type");
						}
					}else {												//未定义的值
						throw new NullPointerException("Undefined Oragnization type");
					}
				}
		}else{
			parameters.put("regionid", null); //判断regionid 为null的时候   显示所有地方的所有产品
			parameters.put("orgid", null); //orgid为null的时候  显示所有组织的所有产品
			parameters.put("readOnly", true); //只能查看数据，不能进行操作
			parameters.put("faqReadOnly",true); //常见问题编辑权限,false为可以编辑
			parameters.put("regionShow",true); //是否需要显示区域列
			parameters.put("orgShow",true); //是否需要显示组织列
		}
		//需要返回的参数
		return parameters;
	}
	
}
