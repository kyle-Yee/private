package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ParametersEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/parameters/")
public class ParametersController extends BaseController{

	@Autowired
	private ParametersService parametersService;
	
	/**
	 * 网站参数页面
	 * @return
	 */
	@RequestMapping(value = "parametersUI.html", method = RequestMethod.GET)
	public String parametersUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/parameters/parametersList";
	}
	
	/**
	 * 网站参数列表搜索
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager){
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "p_id DESC");
		
		List<ParametersEntity> list = parametersService.queryListByPage(parameters);
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
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "addUI.html", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/parameters/parametersForm";
	}
	
	/**
	 * 编辑页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			ParametersEntity parametersEntity = parametersService.findById(id);
			parametersEntity.setId(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("parametersEntity", parametersEntity);
			return Common.BACKGROUND_PATH + "/tbpStatic/parameters/parametersForm";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 数据添加
	 * @param parametersEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(ParametersEntity parametersEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			parametersEntity.setCreatorid(Common.getloginUserId());
			parametersEntity.setCreatetime(new Date());
			parametersEntity.setUpdatorid(Common.getloginUserId());
			parametersEntity.setUpdatetime(new Date());
			parametersEntity.setId(IDGenerate.getZJID("XH"));
			int result = parametersService.insert(parametersEntity);
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
	 *数据编辑
	 * @param parametersEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(ParametersEntity parametersEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			parametersEntity.setUpdatorid(Common.getloginUserId());
			parametersEntity.setUpdatetime(new Date());
			int result = parametersService.update(parametersEntity);
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
	
	/**
	 * CODE是否已经存在
	 * @return
	 */
	@RequestMapping("ckCode.html")
	@ResponseBody
	public Object ckCode(String code,String id) throws AjaxException{
		try
		{
			int result = parametersService.findByCode(code,id);
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
}
