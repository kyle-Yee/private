package com.dcits.govsbu.sd.taxbankplatform.taxauthority.controller;

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
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.service.TaxAuthorityService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
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
@RequestMapping("/taxauthority/")
public class TaxAuthorityController {
	@Autowired
	private TaxAuthorityService taxAuthorityService;
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request){
		
		return Common.BACKGROUND_PATH + "/taxauthority/list";
		
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "tax_id DESC");
	
		List<TaxAuthorityEntity> list = taxAuthorityService.queryListByPage(parameters);
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
			return Common.BACKGROUND_PATH + "/taxauthority/form";
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
	public Object add(TaxAuthorityEntity taxAuthorityEntity) throws AjaxException
	{
		int result;
		Map<String, Object> map = new HashMap<String, Object>();
		try{	
			if((taxAuthorityEntity.getTaxPrcId()).equals("")) {
				taxAuthorityEntity.setTaxPrcId("0");
				result = taxAuthorityService.insert(taxAuthorityEntity);
			}else {
				result = taxAuthorityService.insert(taxAuthorityEntity);
			}
			//taxAuthorityEntity.setId(IDGenerate.getZJID("XH"));
			
			if(result == 1){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
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
			TaxAuthorityEntity taxAuthorityEntity = taxAuthorityService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("taxAuthorityEntity", taxAuthorityEntity);
			return Common.BACKGROUND_PATH + "/taxauthority/form";
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
	public Object update(TaxAuthorityEntity taxAuthorityEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try{	
			int result = taxAuthorityService.update(taxAuthorityEntity);
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
			int cnt = taxAuthorityService.deleteBatchById(list);
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
			List<TaxAuthorityEntity> list = taxAuthorityService.queryListAll(null);
			String taxauthoritys = JSON.toJSONString(list);
			response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
			response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
			//((ServletRequest) response).setCharacterEncoding("utf-8");
			response.getWriter().write(taxauthoritys);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}