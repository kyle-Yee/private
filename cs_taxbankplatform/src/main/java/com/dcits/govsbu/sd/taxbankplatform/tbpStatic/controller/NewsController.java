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
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.NewsEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.NewsService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/news/")
public class NewsController extends BaseController{

	@Autowired
	private NewsService newsService;
	
	/**
	 * 新闻动态维护页面
	 * @return
	 */
	@RequestMapping(value = "newsUI.html", method = RequestMethod.GET)
	public String newsUI(Model model, HttpServletRequest request, String code) {
		model.addAttribute("code", code);
		if(code.equals("bank")){
			//银行动态页面
			model.addAttribute("bank", code);
		}else if(code.equals("policy")){
			//政策动态
			model.addAttribute("policy", code);
		}
		return Common.BACKGROUND_PATH + "/tbpStatic/news/list";
	}
	
	/**
	 * 编辑信息返回页面
	 * @return
	 */
	@RequestMapping(value = "editToUI.html", method = RequestMethod.GET)
	public String editToUI(Model model, HttpServletRequest request, String code) {
		String subCode = code.substring(0, code.indexOf("?"));
		model.addAttribute("code", subCode);
		if(subCode.equals("bank")){
			//免责声明页面
			model.addAttribute("bank", subCode);
			
		}else if(subCode.equals("policy")){
			//通知公告
			model.addAttribute("policy", subCode);
		}
		return Common.BACKGROUND_PATH + "/tbpStatic/news/list";
	}
	
	/**
	 * 新闻动态添加页面
	 * @return
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model, HttpServletRequest request, String code) {
		
		//根据微银类型和区域查询下拉列表名称 
		NewsEntity entity = new NewsEntity();
		entity.setDeptCode("micro_bank");
		entity.setCityId(Common.getloginUserRegionid());
		List<NewsEntity> newsEntity = newsService.findDeptCode(entity);
		
		model.addAttribute("deptEntity", newsEntity);
		model.addAttribute("code", code);
		if(code.equals("bank")){
			//免责声明添加页面
			model.addAttribute("newsName", "银行动态");
			model.addAttribute("bank", code);
		}else if(code.equals("policy")){
			//通知公告添加页面
			model.addAttribute("newsName", "政策动态");
			model.addAttribute("policy", code);
		}
		return Common.BACKGROUND_PATH + "/tbpStatic/news/form";
	}
	
	/**
	 * 新闻动态编辑页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			NewsEntity newsEntity = newsService.findById(id);
			newsEntity.setDate(newsEntity.getDate().toString());
			newsEntity.setId(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("newsEntity", newsEntity);
			
			String newsCode = newsEntity.getCode();
			model.addAttribute("code", newsCode);
			if(newsCode.equals("bank")){
				model.addAttribute("newsName", "银行动态");
				model.addAttribute("bank",newsCode);
				model.addAttribute("edit","true");
			}else if(newsCode.equals("policy")){
				model.addAttribute("newsName", "政策动态");
				model.addAttribute("policy",newsCode);
				model.addAttribute("edit","true");
			}
			
			return Common.BACKGROUND_PATH + "/tbpStatic/news/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 新闻信息列表搜索
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "n.createtime DESC");
	
		List<NewsEntity> list = newsService.queryListByPage(parameters);
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
	 * 动态信息添加
	 * @param newsEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(NewsEntity newsEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			newsEntity.setCityId(Common.getloginUserRegionid());
			newsEntity.setOrgId(Common.getloginUserOrgid());
			newsEntity.setCreatorid(Common.getloginUserId());
			newsEntity.setCreatetime(new Date());
			newsEntity.setUpdatorid(Common.getloginUserId());
			newsEntity.setUpdatetime(new Date());
			newsEntity.setId(IDGenerate.getZJID("XH"));
			int result = newsService.insertNews(newsEntity);
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
	 * 新闻动态信息编辑
	 * @param newsEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(NewsEntity newsEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			newsEntity.setUpdatorid(Common.getloginUserId());
			newsEntity.setUpdatetime(new Date());
			int result = newsService.update(newsEntity);
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
			NewsEntity newsEntity = newsService.findById(id);
			
			String code = newsEntity.getCode();
			if(code.equals("bank")){
				model.addAttribute("bank",code);
			}else if(code.equals("policy")){
				model.addAttribute("policy",code);
			}
			model.addAttribute("newsEntity", newsEntity);
			
			return Common.BACKGROUND_PATH + "/tbpStatic/news/details";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
}
