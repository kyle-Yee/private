package com.dcits.govsbu.sd.taxbankplatform.statistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BankOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BusinessRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.service.BankManagerService;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.util.ExportUtils;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.financialProducts.model.FinancialProductsEntity;
import com.dcits.govsbu.sd.taxbankplatform.financialProducts.service.FinancialProductsService;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.service.PortmagerService;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.CommonEntity;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanCompreInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.LoanDetailsInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.BankManagerInfo;
import com.dcits.govsbu.sd.taxbankplatform.statistics.service.StatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
/**
 * 报表统计Controller
 * @author Sigua.Huang
 * @date 2018年6月28日
 */
@Controller
@Scope("prototype")
@RequestMapping("/statistics/")
public class StatisticsController {
	@Autowired
	public StatisticsService statisticsService;
	
	@Autowired
	private ParametersService parametersService;
	
	@Autowired
	public BankManagerService bankManagerService;
	
	@Autowired
	public PortmagerService portMangerService; 
	/**
	 * 明细清册页面
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	@RequestMapping("loanDetailsListUI.html")
	public Object LoanDetailsUI(Model model,HttpServletRequest request) {
		try
		{
			//获取用户所在的区域id
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = sessionUser.getRegionid();
			Map<String,Object> paramaters = new HashMap<String,Object>();
			if(regionId!=null){
				paramaters.put("regionId", regionId);
			}
			//根据区域id查询旗下的所有银行
			//List<BankOrg> bankList = bankManagerService.queryBankList(paramaters);
			List<PortmagerEntity> bankList=portMangerService.queryBank();
			model.addAttribute("bankList", bankList);
			//获取行业门类
			List<CommonEntity> hyList = statisticsService.queryHYList();
			model.addAttribute("hyList", hyList);
			//获取登记注册类型大类
			List<CommonEntity> djzclxList = statisticsService.queryDJZCLXList();
			model.addAttribute("djzclxList", djzclxList);
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/statistics/loanDetailsList";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 明细数据清册报表数据展示及导出
	 * @author Sigua.Huang
	 * @date 2018年4月29日
	 */
	@ResponseBody
	@RequestMapping("loanDetailsList.html")
	public Object loanDetailsList(String gridPager,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ParametersEntity para = parametersService.findByCode("exportFilePath");
		String realPath = "/usr/local/install/";
//		if (para != null){
//			realPath = para.getValue();
//		}
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("flag", "details");
		parameters.put("realPath", realPath);
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		parameters.put("nsrzgswj", sessionUser.getSsswjDm());
		// 判断是否是导出操作
		if(pager.getIsExport()){
			if (!"".equals(realPath)){
				if(pager.getExportAllData()){
					// 导出全部数据
					List<LoanDetailsInfo> list = statisticsService.queryLoanDetailsList(parameters);
					ExportUtils.exportAll(response, pager, list);
				}else{
					// 导出当前页数据
					ExportUtils.export(response, pager);
				}
				// 导出请求结束，返回null
				return null;
			}
			parameters.put("isSuccess", Boolean.FALSE);
			return parameters;
			
		}
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		List<LoanDetailsInfo> list = statisticsService.queryLoanDetailsList(parameters);
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
	 * 贷款综合情况统计页面
	 * @author Sigua.Huang
	 * @date 2018年6月30日
	 */
	@RequestMapping("loanCompreListUI.html")
	public Object loanCompreListUI(Model model,HttpServletRequest request) {
		try
		{
			//获取用户所在的区域id
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = sessionUser.getRegionid();
			Map<String,Object> paramaters = new HashMap<String,Object>();
			if(regionId!=null){
				paramaters.put("regionId", regionId);
			}
			//根据区域id查询旗下的所有银行
			List<PortmagerEntity> bankList=portMangerService.queryBank();
			model.addAttribute("bankList", bankList);
			//判断该用户的所属税务局
//			List<CommonEntity> SWJDMList = statisticsService.querySsswjDmList(sessionUser.getSsswjDm());
//			model.addAttribute("SWJDMList", SWJDMList);
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/statistics/loanCompreList";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 贷款综合情况统计数据展示及导出
	 * @author Sigua.Huang
	 * @date 2018年4月30日
	 */
	@ResponseBody
	@RequestMapping("loanCompreList.html")
	public Object loanCompreList(String gridPager,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ParametersEntity para = parametersService.findByCode("exportFilePath");
		String realPath = "/usr/local/install/";
//		if (para != null){
//			realPath = para.getValue();
//		}
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("flag", "details");
		parameters.put("realPath", realPath);
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		if(!parameters.containsKey("nsrzgswj") 
				|| "1430".equals(parameters.get("nsrzgswj"))
				||StringUtils.isBlank(parameters.get("nsrzgswj").toString())){
			parameters.put("nsrzgswj", sessionUser.getSsswjDm());
		} 
		// 判断是否是导出操作
		if(pager.getIsExport()){
			if (!"".equals(realPath)){
				if(pager.getExportAllData()){
					// 导出全部数据
					List<LoanCompreInfo> list = statisticsService.queryLoanCompreList(parameters);
					ExportUtils.exportAll(response, pager, list);
				}else{
					// 导出当前页数据
					ExportUtils.export(response, pager);
				}
				// 导出请求结束，返回null
				return null;
			}
			parameters.put("isSuccess", Boolean.FALSE);
			return parameters;
			
		}
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		List<LoanCompreInfo> list = statisticsService.queryLoanCompreList(parameters);
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
	 * 银行管理设置统计页面
	 * @author Sigua.Huang
	 * @date 2018年6月30日
	 */
	@RequestMapping("bankManagerListUI.html")
	public Object bankManagerUI(Model model,HttpServletRequest request) {
		try
		{
			//获取用户所在的区域id
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = sessionUser.getRegionid();
			Map<String,Object> paramaters = new HashMap<String,Object>();
			if(regionId!=null){
				paramaters.put("regionId", regionId);
			}
			//根据区域id查询旗下的所有银行
			List<BankOrg> bankList = bankManagerService.queryBankList(paramaters);
			model.addAttribute("bankList", bankList);
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/statistics/bankManagerList";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	/**
	 * 银行管理设置展示及导出
	 * @author Sigua.Huang
	 * @date 2018年4月30日
	 */
	@ResponseBody
	@RequestMapping("bankManagerList.html")
	public Object bankManagerList(String gridPager,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ParametersEntity para = parametersService.findByCode("exportFilePath");
		String realPath = "/usr/local/install/";
//		if (para != null){
//			realPath = para.getValue();
//		}
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("flag", "details");
		parameters.put("realPath", realPath);
		if (parameters.size() < 0) {
			parameters.put("name", null);
		}
		// 判断是否是导出操作
		if(pager.getIsExport()){
			if (!"".equals(realPath)){
				if(pager.getExportAllData()){
					// 导出全部数据
					List<BankManagerInfo> list = statisticsService.queryBankManagerList(parameters);
					ExportUtils.exportAll(response, pager, list);
				}else{
					// 导出当前页数据
					ExportUtils.export(response, pager);
				}
				// 导出请求结束，返回null
				return null;
			}
			parameters.put("isSuccess", Boolean.FALSE);
			return parameters;
			
		}
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		List<BankManagerInfo> list = statisticsService.queryBankManagerList(parameters);
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
	 * 数据项的展示
	 * @author Sigua.Huang
	 * @date 2018年7月1日
	 */
	@RequestMapping("showBankCfgDtl.html")
	public String showBankCfgDtl(HttpServletRequest request) {		
		try
		{	
			String dataStr = request.getParameter("dataStr");
			List<BusinessRegInfo> businessRegInfo = statisticsService.queryBusinessRegInfo(dataStr);
			request.setAttribute("businessRegInfo", businessRegInfo);
			return Common.BACKGROUND_PATH + "/statistics/bankTaxDataCfg";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}		
	}
}
