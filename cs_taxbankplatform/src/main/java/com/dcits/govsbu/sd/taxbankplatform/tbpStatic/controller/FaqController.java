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
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FaqEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.FaqService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/faq/")
public class FaqController extends BaseController {

	
	@Autowired
	private FaqService faqService;
	
	/**
	 * 常见问题页面
	 * @return
	 */
	@RequestMapping(value = "faqUI.html", method = RequestMethod.GET)
	public String faqUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/faq/faqList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "addUI.html", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/faq/faqForm";
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
			FaqEntity faqEntity = faqService.findById(id);
			faqEntity.setId(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("faqEntity", faqEntity);
			return Common.BACKGROUND_PATH + "/tbpStatic/faq/faqForm";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 常见问题列表搜索
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
		parameters.put("cityId", Common.getloginUserRegionid());
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "pf_id DESC");
		
		List<FaqEntity> list = faqService.queryListByPage(parameters);
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
	 * 查看详情
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("details.html")
	public String details(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			FaqEntity faqEntity = faqService.findById(id);
			model.addAttribute("faqEntity", faqEntity);
			return Common.BACKGROUND_PATH + "/tbpStatic/faq/faqDetails";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 数据添加
	 * @param faqEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(FaqEntity faqEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			String pfId = IDGenerate.getZJID("XH"); //tb_portal_faq
			faqEntity.setId(pfId);
			faqEntity.setCityId(Common.getloginUserRegionid());
			faqEntity.setCreatorid(Common.getloginUserId());
			faqEntity.setCreatetime(new Date());
			faqEntity.setUpdatorid(Common.getloginUserId());
			faqEntity.setUpdatetime(new Date());
			int result = faqService.insert(faqEntity);
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
	 * @param faqEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(FaqEntity faqEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			faqEntity.setUpdatorid(Common.getloginUserId());
			faqEntity.setUpdatetime(new Date());
			int result = faqService.update(faqEntity);
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


























