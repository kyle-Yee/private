package com.dcits.govsbu.sd.taxbankplatform.links.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.links.model.LinksEntity;
import com.dcits.govsbu.sd.taxbankplatform.links.service.LinksService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/links/")
public class LinksController extends BaseController {

	@Autowired
	private LinksService linksService;
	
	/**
	 * 友情链接维护页面
	 * @return
	 */
	@RequestMapping(value = "listUI.html", method = RequestMethod.GET)
	public String listUI(HttpServletRequest request) throws AjaxException {
		try
		{
			return Common.BACKGROUND_PATH + "/links/list";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}

	/**
	 * order是否已经存在
	 * 参数名称要与JS中的相对应，否则取不到值
	 * @return
	 */
	@RequestMapping("ckByOrder.html")
	@ResponseBody
	public Object ckOrder(Long displayOrder,String id) throws AjaxException{
		try
		{
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity userEntity = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = userEntity.getRegionid();

			LinksEntity linksEntity = new LinksEntity();
			linksEntity.setId(id);
			linksEntity.setDisplayOrder(displayOrder);
			linksEntity.setCityId(regionId);
			
			int result = linksService.findByOrder(linksEntity);
			if(result == 0)
			{
				//数据库中不存在
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	/**
	 * name是否已经存在
	 * 参数名称要与JS中的相对应，否则取不到值
	 * @return
	 */
	@RequestMapping("ckByName.html")
	@ResponseBody
	public Object ckName(String linksName,String id) throws AjaxException{
		try
		{
			LinksEntity linksEntity = new LinksEntity();
			linksEntity.setCityId(Common.getloginUserRegionid());
			linksEntity.setId(id);
			linksEntity.setLinksName(linksName);
			int result = linksService.findByName(linksEntity);
			if(result == 0)
			{
				//数据库中不存在
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 合作伙伴数据添加
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(LinksEntity linksEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			linksEntity.setCreatorid(Common.getloginUserId());
			linksEntity.setCreatetime(new Date());
			linksEntity.setUpdatetime(new Date());
			linksEntity.setUpdatorid(Common.getloginUserId());
			linksEntity.setCityId(Common.getloginUserRegionid());
			linksEntity.setId(IDGenerate.getZJID("XH"));
			
			int result = linksService.insert(linksEntity);
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

	/**
	 * 合作伙伴数据编辑
	 * @return
	 */
	@ResponseBody
	@RequestMapping("edit.html")
	public Object update(LinksEntity linksEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			linksEntity.setUpdatorid(Common.getloginUserId());
			linksEntity.setUpdatetime(new Date());
			//数据更新
			int result = linksService.update(linksEntity);
			if(result == 1)
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
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 合作伙伴数据搜索
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager) throws Exception{		
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("cityId", Common.getloginUserRegionid());
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "l_display_order ASC");
	
		List<LinksEntity> list = linksService.queryListByPage(parameters);
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setIndexNo(i+1);
			}
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
	 * 合作伙伴需要编辑的数据搜索
	 * @return
	 */
	@RequestMapping("addUI.html")
	public String addUI() throws AjaxException {
		try
		{
			return Common.BACKGROUND_PATH + "/links/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 合作伙伴需要编辑的数据搜索
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			LinksEntity linksEntity = linksService.findById(id);
			model.addAttribute("linksEntity", linksEntity);
			return Common.BACKGROUND_PATH + "/links/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 检查数据是否重复
	 * @return
	 */
	/*@RequestMapping("ckLinksName.html")
	@ResponseBody
	public Object ckLinksName(HttpServletRequest request,LinksEntity linksEntity)
	{
		try
		{	
			linksEntity.setLinksName(request.getParameter("linksName"));
			linksEntity.setCityId(Common.getloginUserRegionid());
			
			int result = linksService.ckLinksName(linksEntity);
			if(result > 0)
			{
				//有数据
				return false;
			}else
			{
				return true;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}*/
	
}
