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
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.StaticEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.StaticService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/static/")
public class StaticController extends BaseController {
	
	@Autowired
	private StaticService staticService;
	
	/**
	 * 静态信息维护页面
	 * @return
	 */
	@RequestMapping(value = "staticUI.html", method = RequestMethod.GET)
	public String staticUI(Model model, HttpServletRequest request, String code) {
		model.addAttribute("code", code);		
		if(code.equals("statement")){
			//免责声明页面
			model.addAttribute("statement", code);
		}else if(code.equals("message")){
			//通知公告
			model.addAttribute("message", code);
		}else if(code.equals("aboutUs")){
			//关于我们
			model.addAttribute("aboutUs", code);
		}else if(code.equals("contactUs")){
			//联系我们
			model.addAttribute("contactUs", code);
		}else if(code.equals("accredit")){
			//授权委托书
			model.addAttribute("accredit", code);
		}else if(code.equals("faq")){
			//门户常见问题
			model.addAttribute("faq", code);
		}else if(code.equals("agreement")){
			//用户协议
			model.addAttribute("agreement", code);
		}
		return Common.BACKGROUND_PATH + "/tbpStatic/static/list";
	}
	
	/**
	 * 编辑信息返回页面
	 * @return
	 */
	@RequestMapping(value = "editToUI.html", method = RequestMethod.GET)
	public String editToUI(Model model, HttpServletRequest request, String code) {
		String subCode = code.substring(0, code.indexOf("?"));
		model.addAttribute("code", subCode);
		if(subCode.equals("statement")){
			//免责声明页面
			model.addAttribute("statement", subCode);
			
		}else if(subCode.equals("message")){
			//通知公告
			model.addAttribute("message", subCode);
		}else if(subCode.equals("aboutUs")){
			//关于我们
			model.addAttribute("aboutUs", subCode);
		}else if(subCode.equals("contactUs")){
			//联系我们
			model.addAttribute("contactUs", subCode);
		}else if(subCode.equals("accredit")){
			//授权委托书
			model.addAttribute("accredit", subCode);
		}else if(subCode.equals("faq")){
			//门户常见问题
			model.addAttribute("faq", subCode);
		}else if(subCode.equals("agreement")){
			//用户协议
			model.addAttribute("agreement", subCode);
		}
		return Common.BACKGROUND_PATH + "/tbpStatic/static/list";
	}
	
	/**
	 * 静态信息添加页面
	 * @return
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model, HttpServletRequest request, String code) {
		
		model.addAttribute("code", code);
		if(code.equals("statement")){
			//免责声明添加页面
			model.addAttribute("staticName", "免责声明");
			model.addAttribute("statement", code);
		}else if(code.equals("message")){
			//通知公告添加页面
			model.addAttribute("staticName", "通知公告");
			model.addAttribute("message", code);
		}else if(code.equals("aboutUs")){
			//关于我们添加页面
			model.addAttribute("staticName", "关于我们");
			model.addAttribute("aboutUs", code);
		}else if(code.equals("contactUs")){
			//联系我们添加页面
			model.addAttribute("staticName", "联系我们");
			model.addAttribute("contactUs", code);
		}else if(code.equals("accredit")){
			//授权委托书添加页面
			model.addAttribute("staticName", "授权委托书");
			model.addAttribute("accredit", code);
		}else if(code.equals("faq")){
			//授权委托书添加页面
			model.addAttribute("staticName", "门户常见问题");
			model.addAttribute("faq", code);
		}else if(code.equals("agreement")){
			//用户协议添加页面
			model.addAttribute("staticName", "用户协议");
			model.addAttribute("agreement", code);
		}
		return Common.BACKGROUND_PATH + "/tbpStatic/static/form";
	}
	
	/**
	 * 静态信息编辑页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			StaticEntity staticEntity = staticService.findById(id);
			staticEntity.setId(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("staticEntity", staticEntity);
			
			String staticCode = staticEntity.getCode();
			model.addAttribute("code", staticCode);
			if(staticCode.equals("statement")){
				model.addAttribute("staticName", "免责声明");
				model.addAttribute("statement",staticCode);
			}else if(staticCode.equals("message")){
				model.addAttribute("staticName", "通知公告");
				model.addAttribute("message",staticCode);
			}else if(staticCode.equals("aboutUs")){
				model.addAttribute("staticName", "关于我们");
				model.addAttribute("aboutUs",staticCode);
			}else if(staticCode.equals("contactUs")){
				model.addAttribute("staticName", "联系我们");
				model.addAttribute("contactUs",staticCode);
			}else if(staticCode.equals("accredit")){
				model.addAttribute("staticName", "授权委托书");
				model.addAttribute("accredit",staticCode);
			}else if(staticCode.equals("faq")){
				model.addAttribute("staticName", "门户常见问题");
				model.addAttribute("faq",staticCode);
			}else if(staticCode.equals("agreement")){
				model.addAttribute("staticName", "用户协议");
				model.addAttribute("agreement",staticCode);
			}
			
			return Common.BACKGROUND_PATH + "/tbpStatic/static/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 数据搜索
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "si_id asc");
	
		List<StaticEntity> list = staticService.queryListByPage(parameters);
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
	 * 静态信息添加
	 * @param statementEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(StaticEntity staticEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			staticEntity.setCityId(Common.getloginUserRegionid());
			staticEntity.setCreatorid(Common.getloginUserId());
			staticEntity.setCreatetime(new Date());
			staticEntity.setUpdatorid(Common.getloginUserId());
			staticEntity.setUpdatetime(new Date());
			staticEntity.setId(IDGenerate.getZJID("XD"));
			
			int result = staticService.insertStatic(staticEntity);
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
	 * 搜索数据是否存在
	 * @param staticEntity
	 * @return
	 */
	@RequestMapping("searchList.html")
	@ResponseBody
	@Transactional
	public Object searchList(HttpServletRequest request,StaticEntity staticEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			staticEntity.setCityId(Common.getloginUserRegionid());
			staticEntity.setCode(request.getParameter("code"));
			int result = staticService.searchListByRegionId(staticEntity);
			if(result > 0)
			{
				//有数据
				map.put("success", Boolean.TRUE);
			}else
			{
				map.put("success", Boolean.FALSE);
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	/**
	 * 静态信息编辑
	 * @param statementEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(StaticEntity staticEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			staticEntity.setUpdatorid(Common.getloginUserId());
			staticEntity.setUpdatetime(new Date());
			int result = staticService.update(staticEntity);
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
	 * 点击查看详情信息
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("details.html")
	public String details(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			StaticEntity staticEntity = staticService.findById(id);
			
			String code = staticEntity.getCode();
			if(code.equals("statement")){
				model.addAttribute("statement",code);
			}else if(code.equals("message")){
				model.addAttribute("message",code);
			}else if(code.equals("aboutUs")){
				model.addAttribute("aboutUs",code);
			}else if(code.equals("contactUs")){
				model.addAttribute("contactUs",code);
			}else if(code.equals("accredit")){
				model.addAttribute("accredit",code);
			}else if(code.equals("faq")){
				model.addAttribute("faq",code);
			}else if(code.equals("agreement")){
				model.addAttribute("agreement",code);
			}
			
			model.addAttribute("staticEntity", staticEntity);
			return Common.BACKGROUND_PATH + "/tbpStatic/static/details";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
}
