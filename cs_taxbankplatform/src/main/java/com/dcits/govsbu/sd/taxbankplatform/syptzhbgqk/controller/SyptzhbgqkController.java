package com.dcits.govsbu.sd.taxbankplatform.syptzhbgqk.controller;

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

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BankOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.service.BankManagerService;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.service.PortmagerService;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.syptzhbgqk.mapper.SyptzhbgqkMapper;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsType;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * RoleController.java
 * 
 * @author liuc
 * @date 2018年6月29日
 * @caption 涉税数据提供情况统计
 */
@Controller
@Scope("prototype")
@RequestMapping("/syptzhbgqk/")
public class SyptzhbgqkController extends BaseController {
	@Autowired
	public BankManagerService bankManagerService;
	

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private OrganizationsService organizationsService;
	
	@Autowired 
	private SyptzhbgqkMapper  syptzhbgqkMapper;
	
	@Autowired
	public PortmagerService portMangerService; 

	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil();
			if (request.getParameterMap().containsKey("page")) {
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			Map<String,Object> paramaters = new HashMap<String,Object>();
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = sessionUser.getRegionid();
			//if(regionId!=null){
				paramaters.put("regionId", regionId);
			//}
			List<PortmagerEntity> bankList=portMangerService.queryBank();
			List<Map<String,Object>> hydmList = syptzhbgqkMapper.getHydm();
			List<Map<String,Object>> djzclxdmList = syptzhbgqkMapper.getDjzclx();
			model.addAttribute("bankList", bankList);
			model.addAttribute("hydmList", hydmList);
			model.addAttribute("djzclxdmList", djzclxdmList);
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/syptzhbgqk/list";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager , HttpServletRequest request) throws Exception {
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		// 根据登录信息获取需要筛选的条件
		parameters.putAll(OrganizationsType.getParameters());
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),
				pager.getPageSize(), "f.LOANTIME DESC");
		
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		parameters.put("nsrzgswj", sessionUser.getSsswjDm());
		List<Map<String,Object>>  list = syptzhbgqkMapper.queryListByPage(parameters);
		if(null != list && list.size() > 0 ){
			for(int i = 0 ;i < list.size(); i ++ ){
				Map<String,Object> map = list.get(i);
				map.put("indexNo", i+1);
			}
		}
		
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		// 列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;

	}

 
 
}
