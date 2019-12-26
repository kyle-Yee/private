package com.dcits.govsbu.sd.taxbankplatform.loanstatus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
import com.dcits.govsbu.sd.taxbankplatform.loanstatus.model.LoanStatusEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanstatus.service.LoanStatusService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Controller
@Scope("prototype")
@RequestMapping("/loanstatus/")
public class LoanStatusController extends BaseController{
	@Autowired
	private LoanStatusService loanStatusService;
	
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
			return Common.BACKGROUND_PATH + "/loanstatus/list";
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "ls_id DESC");
	
		List<LoanStatusEntity> list = loanStatusService.queryListByPage(parameters);
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
	
	@RequestMapping("addUI.html")
	public String addUI(Model model) {		
		try
		{	
			/*//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			*/
			//查询出所有的图片分类
			return Common.BACKGROUND_PATH + "/loanstatus/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(LoanStatusEntity loanStatusEntity) throws AjaxException
	{
		//获取登录用户
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		@SuppressWarnings("unused")
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			loanStatusEntity.setEnabled("Y");
			//设置用户所属集合标识，数据隔离
			int result = loanStatusService.insert(loanStatusEntity);
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
	
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{	
			//获取登录用户
			HttpServletRequest request2 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			@SuppressWarnings("unused")
			UserEntity sessionUser = (UserEntity)request2.getSession().getAttribute("userSession");
			
			LoanStatusEntity loanStatusEntity = loanStatusService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("loanStatusEntity", loanStatusEntity);
			return Common.BACKGROUND_PATH + "/loanstatus/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(LoanStatusEntity loanStatusEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			int result = loanStatusService.update(loanStatusEntity);
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
			String[] EntityIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : EntityIds) {
				list.add(string);
			}
			int cnt = loanStatusService.deleteBatchById(list);
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
	
}