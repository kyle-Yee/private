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
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.BgdjswbEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.BgdjswbService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/bgdjswb/")
public class BgdjswbController extends BaseController {
	
	@Autowired
	private BgdjswbService bgdjswbService;
	
	/**
	 * 变更登记页面
	 * @return
	 */
	@RequestMapping(value = "bgdjswbUI.html", method = RequestMethod.GET)
	public String bgdjswbUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/bgdjswb/bgdjswbList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "addUI.html", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request, Model model) {
		return Common.BACKGROUND_PATH + "/tbpStatic/bgdjswb/bgdjswbForm";
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
			BgdjswbEntity bgdjswbEntity = bgdjswbService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("bgdjswbEntity", bgdjswbEntity);
			return Common.BACKGROUND_PATH + "/tbpStatic/bgdjswb/bgdjswbForm";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}

	/**
	 * 数据判断
	 * @param faqEntity
	 * @return
	 */
	@RequestMapping("ckByRegionId.html")
	@ResponseBody
	@Transactional
	public Object ckByRegionId(HttpServletRequest request, Model model) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			String fileLx = request.getParameter("fileLx");
			String regionId = Common.getloginUserRegionid();
			BgdjswbEntity bgdjswbEntity = new BgdjswbEntity();
			bgdjswbEntity.setRegionId(regionId);
			bgdjswbEntity.setFileLx(fileLx);
			String result = bgdjswbService.ckByRegionId(bgdjswbEntity);
			if(result == null)
			{
				//没有数据
				map.put("success", Boolean.TRUE);
			}else{
				//有数据
				map.put("success", Boolean.FALSE);
				map.put("message", "数据已经存在");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 数据添加
	 * @param faqEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(BgdjswbEntity bgdjswbEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			bgdjswbEntity.setRegionId(Common.getloginUserRegionid());
			bgdjswbEntity.setCreatorid(Common.getloginUserId());
			bgdjswbEntity.setCreatetime(new Date());
			bgdjswbEntity.setUpdatorid(Common.getloginUserId());
			bgdjswbEntity.setUpdatetime(new Date());
			bgdjswbEntity.setId(IDGenerate.getZJID("XH"));
			int result = bgdjswbService.insert(bgdjswbEntity);
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
	 * 变更登记税务信息表列表
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
		parameters.put("regionId", Common.getloginUserRegionid());
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "b_id DESC");
		
		List<BgdjswbEntity> list = bgdjswbService.queryListByPage(parameters);
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
	 *数据编辑
	 * @param faqEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(BgdjswbEntity bgdjswbEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			bgdjswbEntity.setUpdatorid(Common.getloginUserId());
			bgdjswbEntity.setUpdatetime(new Date());
			int result = bgdjswbService.update(bgdjswbEntity);
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
}


























