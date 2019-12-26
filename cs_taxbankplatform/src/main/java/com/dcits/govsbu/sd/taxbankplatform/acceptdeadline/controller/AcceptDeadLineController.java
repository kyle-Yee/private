package com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.controller;

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

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.model.AcceptDeadLineEntity;
import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.service.AcceptDeadLineService;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;

import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsType;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/acceptdeadline/")
public class AcceptDeadLineController {
	@Autowired
	private AcceptDeadLineService acceptDeadLineService;
	@Autowired
	private RegionsService regionsService;
	@Autowired
	private OrganizationsService organizationsService;
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request){
		
		return Common.BACKGROUND_PATH + "/acceptdeadline/list";
		
	}
	
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager, HttpServletRequest request) throws Exception{
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		String orgid = sessionUser.getOrgid();
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("orgid", orgid);
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "tl_id DESC");
	
		List<AcceptDeadLineEntity> list = acceptDeadLineService.queryListByPage(parameters);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndexNo(i+1);
		}
		for (AcceptDeadLineEntity acceptDeadLineEntity : list) {
			String fp_regionId=acceptDeadLineEntity.getRegionId();
			String fp_orgId=acceptDeadLineEntity.getOrgId();
			String regionName = regionsService.findById(fp_regionId).getRegionname();
			OrganizationEntity org = organizationsService.findById(fp_orgId);
			String orgName = org.getOrgname();
			acceptDeadLineEntity.setRegionName(regionName);
			acceptDeadLineEntity.setOrgName(orgName);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}
	/**
	 * 跳转到增加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model) {		
		try
		{		
			Map<String, Object> parameters = OrganizationsType.getParameters();
			List<RegionsEntity> regionList = regionsService.queryListAll(parameters);
			model.addAttribute("regionsList", regionList);
			List<OrganizationEntity> orgList = organizationsService.queryListAll(parameters);
			model.addAttribute("orgsList", orgList);
			return Common.BACKGROUND_PATH + "/acceptdeadline/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}		
	}
	/**
	 * 增加税务机关的方法
	 * @param taxAuthorityEntity
	 * @return
	 * @throws AjaxException
	 */
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(HttpServletRequest request,AcceptDeadLineEntity acceptDeadLineEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		UserEntity userEntity = (UserEntity)request.getSession().getAttribute("userSession");
		String user = null;
		if(userEntity != null){
			user = userEntity.getUserName();
		}
		acceptDeadLineEntity.setCreateUser(user);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("orgId",acceptDeadLineEntity.getOrgId());
	
		OrganizationEntity orgList = organizationsService.findById(acceptDeadLineEntity.getOrgId());
		acceptDeadLineEntity.setRegionId(orgList.getRegionid().toString());
		Map<String, Object> parameter = null;
	    int code=0;
		List<AcceptDeadLineEntity> list = acceptDeadLineService.queryListByPage(parameter);
		try{	
			for (AcceptDeadLineEntity acceptDeadLineEntity2 : list) {
				if(acceptDeadLineEntity2.getOrgId()==acceptDeadLineEntity.getOrgId()){
					code++;
				}
			}
			if(code==0){
				int result = acceptDeadLineService.insert(acceptDeadLineEntity);
				if(result == 1){
					map.put("success", Boolean.TRUE);
					map.put("data", null);
					map.put("message", "添加成功");
				}else{
					map.put("success", Boolean.FALSE);
					map.put("data", null);
					map.put("message", "添加失败");
				}
			}else {
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "该组织已存在受理时间");
			}
			
		}catch(ServiceException e){
			throw new AjaxException(e);
		}
		return map;
	}
	/**
	 * 跳转到编辑税务机关页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try{	
			AcceptDeadLineEntity acceptDeadLineEntity = acceptDeadLineService.findById(id);
			Map<String, Object> parameters = OrganizationsType.getParameters();
			List<RegionsEntity> regionList = regionsService.queryListAll(parameters);
			model.addAttribute("regionsList", regionList);
			List<OrganizationEntity> orgList = organizationsService.queryListAll(parameters);
			model.addAttribute("orgsList", orgList);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("acceptDeadLineEntity", acceptDeadLineEntity);
			model.addAttribute("disable",true);
			return Common.BACKGROUND_PATH + "/acceptdeadline/form";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	/**
	 * 编辑税务机关后台方法
	 * @return
	 */
	@ResponseBody
	@RequestMapping("edit.html")
	public Object update(AcceptDeadLineEntity acceptDeadLineEntity,String orgId) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		acceptDeadLineEntity.setOrgId(orgId);
		parameters.put("orgId",acceptDeadLineEntity.getOrgId());
	
		OrganizationEntity orgList = organizationsService.findById(acceptDeadLineEntity.getOrgId());
		acceptDeadLineEntity.setRegionId(orgList.getRegionid().toString());

		try{	
			int result = acceptDeadLineService.update(acceptDeadLineEntity);
			if(result == 1){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
		}catch(ServiceException e){
			throw new AjaxException(e);
		}
		return map;
	}
	/**
	 * 删除税务机关后台方法
	 * @param ids
	 * @return
	 */
	@RequestMapping("deleteUI.html")
	@ResponseBody
	public Object deleteBatch(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{   
			String[] roleIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : roleIds) {
				list.add(string);
			}	   
			int cnt = acceptDeadLineService.deleteBatchById(list);
			if(cnt == list.size()){
				result.put("success", true);
				result.put("data", null);
				result.put("message", "删除成功");
			}else{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "删除失败");
			}
		}catch(Exception e){
			throw new AjaxException(e);
		}
		return result;
	}
	/**
	 * 查询所有的税务机关
	 * @param request
	 * @param response
	 */
	@RequestMapping("findAuthority.html")
	public void findCities(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			List<AcceptDeadLineEntity> list = acceptDeadLineService.queryListAll(null);
			String taxauthoritys = JSON.toJSONString(list);
			response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
			response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
			//((ServletRequest) response).setCharacterEncoding("utf-8");
			response.getWriter().write(taxauthoritys);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取该银行已有的贷款受理时间个数（需求是：一个银行一个）
	 * @param request
	 * @return
	 */
	@RequestMapping("deadLineCount.html")
	@ResponseBody
	public Object deadLineCount(HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<>();
		UserEntity userEntity = (UserEntity)request.getSession().getAttribute("userSession");
		String orgid = userEntity.getOrgid();
		int count = acceptDeadLineService.deadLineCount(orgid);
		returnMap.put("count", count);
		return returnMap;
	}
	
	
}