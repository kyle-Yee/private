package com.dcits.govsbu.sd.taxbankplatform.organization.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.RzxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.SqxyEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.TaxEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.model.OrganizationsTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.service.OrganizationsTypeService;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.model.OrgAutComareEntity;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.service.OrgAutComareService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.model.RegionclassEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.service.RegionclassService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.service.TaxAuthorityService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Controller
@Scope("prototype")
@RequestMapping("/organization/")
public class OrganizationsController extends BaseController{
	@Autowired
	private OrganizationsService organizationsService;
	@Autowired
	private ProvinceCitiesService provinceCitiesService;
	@Autowired
	private RegionsService regionsService;
	//组织类型代码
	@Autowired
	private OrganizationsTypeService organizationsTypeService;
	//区域级别
	@Autowired
	private RegionclassService regionclassService;
	//区域机关代码
	@Autowired
	private TaxAuthorityService taxAuthorityService;
	
	@Autowired
	private OrgAutComareService orgAutComareService;
	
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/organization/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "region_id,org_id DESC");
	
		List<OrganizationEntity> list = organizationsService.queryListByPage(parameters);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}
	
	/**
	 * 添加组织管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model) {		
		try
		{				
			List<RegionsEntity> regionsList = regionsService.queryListAll(null);
			List <OrganizationsTypeEntity> organizationstypeList = organizationsTypeService.queryListAll(null);
			List<RegionclassEntity> regionclassEntityList = regionclassService.queryListByPage(null);
			List<RzxyEntity> rzxyList=organizationsService.findrzxy();
			List<SqxyEntity> sqxyList=organizationsService.findsqxy();
			model.addAttribute("regionclassEntityList", regionclassEntityList);
			
			model.addAttribute("regionsList", regionsList);
			model.addAttribute("organizationstypeList", organizationstypeList);
			model.addAttribute("rzxyList",rzxyList);
			model.addAttribute("sqxyList",sqxyList);
			
			
			return Common.BACKGROUND_PATH + "/organization/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(OrganizationEntity organizationEntity) throws AjaxException
	{
		//获取登录用户
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		@SuppressWarnings("unused")
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameters =new HashMap<String, Object>();
		try
		{	
			this.createModel(organizationEntity);
			organizationEntity.setId(IDGenerate.getZJID("ZZ"));
			int result = 0;
			//判断 --判断组织名称和组织code是否存在
			if(organizationEntity.getOrgname()!=null&&!("").equals(organizationEntity.getOrgname())){
				parameters.put("orgName",organizationEntity.getOrgname());
				parameters.put("regionId",organizationEntity.getRegionid());
				List<OrganizationEntity> list = organizationsService.queryListByPage(parameters);
				if(list.size()!=0){
					result=1;//该名称已存在
				}
			}
			if(organizationEntity.getOrgcode()!=null&&!("").equals(organizationEntity.getOrgcode())){
				parameters.clear();
				parameters.put("orgCode",organizationEntity.getOrgcode());
				List<OrganizationEntity> list = organizationsService.queryListByPage(parameters);
				if(list.size()!=0){
					result=2;//该编码已存在
				}
			}
			//设置用户所属集合标识，数据隔离
			if(result!=1&&result!=2){
				if(organizationsService.insert(organizationEntity)>0){
					result=3;//添加成功
				}else {
					result=0;//添加失败
				}
			
				
			}
		
			if(result == 3)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}
			else if(result ==1){
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "该组织名称已存在 ");
			}
            else if(result==2){
            	map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "该组织编码已存在");
			}   
			else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{	
			//获取登录用户
//			HttpServletRequest request2 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//			UserEntity sessionUser = (UserEntity)request2.getSession().getAttribute("userSession");
			
			//查询出所有开通并且有效存在的区域
			List<RegionsEntity> regionsList = regionsService.queryListAll(null);
			List <OrganizationsTypeEntity> organizationstypeList = organizationsTypeService.queryListAll(null);
			model.addAttribute("regionsList", regionsList);
			model.addAttribute("organizationstypeList", organizationstypeList);
			//查询用户的级别
			List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
			model.addAttribute("organizationList", organizationList);
			//获取区域级别
			List<RegionclassEntity> regionclassEntityList = regionclassService.queryListByPage(null);
			model.addAttribute("regionclassEntityList", regionclassEntityList);
			
			OrganizationEntity organizationEntity = organizationsService.findById(id);
			
			if(organizationEntity!=null && !"ZZ0002017010100000000000".equals(id)){
				Map<String, Object> parameters = new HashMap<String,Object>();
				String regionid = organizationEntity.getRegionid();
				String otid = organizationEntity.getOtid();
				String rcid = organizationEntity.getRcid();
				String rcidQ = rcid.substring(0,rcid.length()-1);
				String rcidH = rcid.substring(rcid.length()-1,rcid.length());
				String rcId = rcidQ + (Integer.valueOf(rcidH)-1);
				parameters.put("regionid", regionid);
				parameters.put("otid", otid);
				parameters.put("rcid", rcId);
				RegionclassEntity regionclassEntity = regionclassService.findById(rcId);
				model.addAttribute("regionclassEntity", regionclassEntity);
				List<OrganizationEntity> upOrgIdEntityList = organizationsService.queryUpOrgIdListAll(parameters);
				model.addAttribute("upOrgIdEntityList", upOrgIdEntityList);
				
				
			}
			String pcid = organizationEntity.getPcid();
			ProvinceCitiesEntity provinceCitiesEntity = provinceCitiesService.findById(pcid);
			model.addAttribute("provinceCitiesEntity", provinceCitiesEntity);
			List<RzxyEntity> rzxyList=organizationsService.findrzxy();
			List<SqxyEntity> sqxyList=organizationsService.findsqxy();
			model.addAttribute("rzxyList",rzxyList);
			model.addAttribute("sqxyList",sqxyList);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("organizationEntity", organizationEntity);
			return Common.BACKGROUND_PATH + "/organization/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(OrganizationEntity organizationEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameters =new HashMap<String, Object>();
		try
		{	
			this.updateModel(organizationEntity);
			int result = 0;
			//判断 --判断组织名称和组织code是否存在
			if(organizationEntity.getOrgname()!=null&&!("").equals(organizationEntity.getOrgname())){
				OrganizationEntity organizationEntity2=organizationsService.findById(organizationEntity.getId());
				parameters.put("orgName",organizationEntity.getOrgname());
				parameters.put("regionId",organizationEntity2.getRegionid());
				List<OrganizationEntity> list = organizationsService.queryListByPage(parameters);
				
				if(organizationEntity2.getOrgname().equals(organizationEntity.getOrgname())){
					
				}else {
					if(list.size()!=0){
						result=1;//该名称已存在
					}
				}
				
			}
			if(organizationEntity.getOrgcode()!=null&&!("").equals(organizationEntity.getOrgcode())){
				parameters.clear();
				parameters.put("orgCode",organizationEntity.getOrgcode());
				List<OrganizationEntity> list = organizationsService.queryListByPage(parameters);
				OrganizationEntity organizationEntity2=organizationsService.findById(organizationEntity.getId());
				if((organizationEntity2.getOrgcode()).equals(organizationEntity.getOrgcode())){
				}else {
					if(list.size()!=0){
						result=2;//该编码已存在
					}
				}
				
			}
			//设置用户所属集合标识，数据隔离
			if(result!=1&&result!=2){
				if(organizationsService.update(organizationEntity)>0){
					result=3;//编辑成功
				}else {
					result=0;//编辑失败
				}
			
				
			}
		
			if(result == 3)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}
			else if(result ==1){
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "该组织名称已存在 ");
			}
            else if(result==2){
            	map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "该组织编码已存在");
			}   
			else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
			
			
			
			
			
			
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] pictrueEntityIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : pictrueEntityIds) {
				list.add(string);
			}
			int cnt = organizationsService.deleteBatchById(list);
			if(cnt == list.size())
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "删除成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "删除失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}	
	
	/**
	 * 根据区域id查询组织实现级联
	 * @param id
	 * @return
	 */
	@RequestMapping("regionidbyorg.html")
	@ResponseBody
	public void regionidgetorganization(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> parameters = new HashMap<String,Object>();
			String regionid = request.getParameter("regionid");
			parameters.put("regionid", regionid);
			List<OrganizationEntity> listOrg = organizationsService.queryListAll(parameters);
			String json = JSON.toJSONString(listOrg);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据条件返回返回查询的上级组织
	 * @param body
	 * @return
	 */
	@RequestMapping("findOrangeLevel.html")
	@ResponseBody
	public List<OrganizationEntity> findUpOrgLevel(HttpServletRequest request){
		List<OrganizationEntity> organizationEntityList = null;
		Map<String, Object> parameters = new HashMap<String,Object>();
		String regionid = request.getParameter("regionid");
		parameters.put("regionid", regionid);
		String  otid = request.getParameter("otid");
		parameters.put("otid", otid);
		String regionLevel = request.getParameter("regionLevel");
		String strq = regionLevel.substring(0,regionLevel.length()-1);	//取字符串中的前几位
		String strh = regionLevel.substring(regionLevel.length()-1,regionLevel.length());	//取字符串中的最后一位
		String rcid = strq + (Integer.parseInt(strh) - 1);
		parameters.put("rcid", rcid);
		organizationEntityList = organizationsService.queryUpOrgIdListAll(parameters);
		return organizationEntityList;
	}
	
	/**
	 * 分配税务机关页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("allocateUI.html")
	public String allocateUI(Model model, HttpServletRequest request, String id) {
		List<TaxAuthorityEntity> taxAuthorityEntity = null;
		OrganizationEntity organizationEntity = organizationsService.findById(id);
		model.addAttribute("organizationEntity", organizationEntity);
		if(id != null){
			taxAuthorityEntity = organizationsService.findTaxAuthorityListById(id);
			model.addAttribute("taxAuthorityEntity", taxAuthorityEntity);
		}
		List<TaxAuthorityEntity> taxAuthorityEntityList= taxAuthorityService.queryListAll(null);
		PageUtil page = new PageUtil();
		page.setPageNum(Integer.valueOf(request.getParameter("page")));
		page.setPageSize(Integer.valueOf(request.getParameter("rows")));
		page.setOrderByColumn(request.getParameter("sidx"));
		page.setOrderByType(request.getParameter("sord"));
		model.addAttribute("page", page);
		model.addAttribute("taxAuthorityEntityList", taxAuthorityEntityList);
		return Common.BACKGROUND_PATH + "/organization/allocateform";
	}
	/**
	 * 增加税务机关后台方法
	 * @param organizationEntity
	 * @return
	 * @throws AjaxException
	 */
	@RequestMapping("allocate.html")
	@ResponseBody
	public Object allocate(TaxEntity taxEntity,String idList) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		if(idList!=""){
			map.clear();
			String [] oldTaxArry = idList.split(",");
			for(int j=0;j<oldTaxArry.length;j++){
				if(taxEntity != null){
					String id = taxEntity.getId();
					String tax = taxEntity.getTax();
					String [] taxArray = tax.split(",");
					List<String> taxIdList = new ArrayList<String>();
					for(int i=0;i<taxArray.length;i++){
						if(!(oldTaxArry[j].toString().equals(taxArray[i].toString()))){
							taxIdList.add(oldTaxArry[j]);
//							map.put("orgId", id);
//							map.put("taxId", oldTaxArry[j]);
//							orgAutComareService.deleteDataByOrgIdAndTaxId(map);
						}
					}
					map.put("orgId", id);
					map.put("taxIdList", taxIdList);
					orgAutComareService.deleteDataByOrgIdAndTaxId(map);
				}
			}
		}
		if(taxEntity != null){
			String id = taxEntity.getId();
			String tax = taxEntity.getTax();
			String [] taxArray = tax.split(",");
			OrgAutComareEntity orgAutComareEntity = new OrgAutComareEntity();
			orgAutComareEntity.setOrgId(id);
			Long taxId = null;
			map.clear();
			for(int i=0;i<taxArray.length;i++){
				taxId = Long.parseLong(taxArray[i]);
				orgAutComareEntity.setTaxId(taxId);
				map.put("orgId", id);
				map.put("taxId", taxId);
				OrgAutComareEntity orgAutComareDate =orgAutComareService.queryAuthorityByOrgId(map);
				if(orgAutComareDate != null){
					count++;
				}else{
					int result = orgAutComareService.insert(orgAutComareEntity);
					if(result==1){
						count++;
					}
				}
			}
			if(taxArray.length == count)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}
		return map;
	}
}
