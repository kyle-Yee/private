package com.dcits.govsbu.sd.taxbankplatform.amount.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.amount.service.AmountService;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.dcits.govsbu.sd.taxbankplatform.amount.model.AmountEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * 
 * @author 赵宝庆
 * @date 2016年8月8日
 * @caption 贷款金额基础信息维护
 */
@Controller
@Scope("prototype")
@RequestMapping("/amount/")
public class AmountController extends BaseController {

	@Autowired
	private AmountService amountService;
	
	/**
	 * 贷款金额维护
	 * @return
	 */
	@RequestMapping(value = "listUI.html", method = RequestMethod.GET)
	public String listUI(Model model, HttpServletRequest request) throws AjaxException {
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
			return Common.BACKGROUND_PATH + "/amount/list";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * 贷款金额添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add.html")
	public Object add(AmountEntity amountEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			String laID = IDGenerate.getZJID("ED");
			amountEntity.setId(laID);
			String codeNum = amountEntity.getAmountName();
			String amountName = codeNum + "万元";
			amountEntity.setAmountName(amountName);
			amountEntity.setCode(codeNum);
			amountEntity.setEnabled("Y");
			int result = amountService.insert(amountEntity);;
			if(result == 1)
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
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	/**
	 * 贷款金额是否已经存在
	 * @return
	 */
	@RequestMapping("validateAmountName.html")
	@ResponseBody
	public Object validateAmountName(String amountName) throws AjaxException{
		try
		{
			AmountEntity amountEntity = amountService.findByName(amountName);
			if(amountEntity == null)
			{
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
	 * 贷款金额数据搜索
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
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "la_id ASC");
	
		List<AmountEntity> list = amountService.queryListByPage(parameters);
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setOrgId(Common.getloginUserOrgid());
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
	 * 贷款金额数据删除（逻辑删除）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delAmount.html")
	public Object delAmount(String id) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			int result = amountService.delAmount(id);
			
			if(result == 1)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "删除成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "删除失败");
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
}
