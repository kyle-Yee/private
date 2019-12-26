package com.dcits.govsbu.sd.taxbankplatform.role.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.role.model.RoleEntity;
import com.dcits.govsbu.sd.taxbankplatform.role.service.RoleService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsType;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;

/**
 * 
 * RoleController.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 角色
 */
@Controller
@Scope("prototype")
@RequestMapping("/role/")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RegionsService regionsService;
	
	@Autowired
	private OrganizationsService organizationsService;
	
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
			return Common.BACKGROUND_PATH + "/role/list";
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
		// 根据登录信息获取需要筛选的条件
		parameters.putAll(OrganizationsType.getParameters());
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "region_id,org_id,r_id DESC");
	
		List<RoleEntity> list = roleService.queryListByPage(parameters);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", list.size());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}
	
	
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		try
		{	
			//根据登录信息获取筛选条件参数
			Map<String, Object> parameters = OrganizationsType.getParameters();
			//区域以及组织列表
			List<RegionsEntity> regionsList = regionsService.queryListAll(parameters);
			String usertype = (String) OrganizationsType.getParameters().get("usertype");
			Integer type = Integer.valueOf(usertype);
			if (type!=1) {
				List<OrganizationEntity> orgList = organizationsService.queryListAll(parameters);
				model.addAttribute("orgsList", orgList);
			}
			
			model.addAttribute("regionsList", regionsList);
			
			model.addAttribute("usertype", usertype);
			
			return Common.BACKGROUND_PATH + "/role/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(RoleEntity roleEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			OrganizationEntity entity  = organizationsService.findById(roleEntity.getOrgid());
			roleEntity.setId(IDGenerate.getZJID("JS"));
			roleEntity.setCreateUid(Common.getloginUserId());
			roleEntity.setCreateTime(new Date());
			roleEntity.setRegionid(entity.getRegionid());
			int result = roleService.insert(roleEntity);
			if(result > 0)
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
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{	
			//根据登录信息获取筛选条件参数
			Map<String, Object> parameters = OrganizationsType.getParameters();
			//区域以及组织列表
			List<RegionsEntity> regionsList = regionsService.queryListAll(parameters);
			String usertype = (String) OrganizationsType.getParameters().get("usertype");
			
			List<OrganizationEntity> orgList = organizationsService.queryListAll(parameters);
			model.addAttribute("orgsList", orgList);
			
			model.addAttribute("regionsList", regionsList);
			
			model.addAttribute("usertype", usertype);
			
			RoleEntity roleEntity = roleService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("roleEntity", roleEntity);
			return Common.BACKGROUND_PATH + "/role/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(RoleEntity roleEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			int result = roleService.update(roleEntity);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}else
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
	public Object deleteBatch(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] roleIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : roleIds) {
				list.add(string);
			}
			int cnt = roleService.deleteBatchById(list);
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
	
	
	@RequestMapping("permissionUI.html")
	public String permissionUI(Model model, HttpServletRequest request, String id) {
		try
		{
			RoleEntity roleEntity = roleService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("roleEntity", roleEntity);
			return Common.BACKGROUND_PATH + "/role/permission";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	
	@RequestMapping("permission.html")
	@ResponseBody
	public Object permission(String roleId, String resourceIds)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			List<String> list = new ArrayList<String>();
			if(StringUtil.isNotBlank(resourceIds))
			{
				for (String id : resourceIds.split(",")) {
					list.add(id);
				}
			}
			boolean result = roleService.addRolePerm(roleId, list);
			if(result)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "授权成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "授权失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 根据区域id和组织id查询角色实现级联
	 * @param id
	 * @return
	 */
	@RequestMapping("regorgbyrole.html")
	@ResponseBody
	public void regionidgetorganization(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> parameters = new HashMap<String,Object>();
			String regionid = request.getParameter("regionid");
			String orgid = request.getParameter("orgid");
			parameters.put("regionid", regionid);
			parameters.put("orgid", orgid);
			List<RoleEntity> listRole = roleService.findRoleQuery(parameters);
			String json = JSON.toJSONString(listRole);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
