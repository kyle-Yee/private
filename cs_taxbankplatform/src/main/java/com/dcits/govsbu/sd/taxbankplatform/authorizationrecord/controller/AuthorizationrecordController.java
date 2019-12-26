package com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.AuthorizationrecordService;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.service.LoanAgreementService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.organizationstype.service.OrganizationsTypeService;
import com.dcits.govsbu.sd.taxbankplatform.orgautcomare.service.OrgAutComareService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regionclass.service.RegionclassService;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.EnterprisestatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.service.TaxAuthorityService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.StaticService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.PdfGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 税务授权控制类
 * @author Fangren
 * @time 2017年8月1日14:21:38
 */
@Controller
@Scope("prototype")
@RequestMapping("/authorizationrecord/")
public class AuthorizationrecordController {
	/**
	 * 日志
	 */
	public Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AuthorizationrecordService authorizationrecordService;
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private EnterprisestatisticsService enterprisestatisticsService;
	
	@Autowired
	private LoanAgreementService loanAgreementService; //贷款协议
	
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
			return Common.BACKGROUND_PATH + "/authorizationrecord/list";
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "at_sqsj DESC");
	
		List<AuthorizationrecordEntity> list = authorizationrecordService.queryListByPage(parameters);
		String sqsjqx="";
		for (AuthorizationrecordEntity authorizationrecordEntity : list) {
			if(authorizationrecordEntity.getSqjg().equals("授权失败")||authorizationrecordEntity.getSqjg().equals("sqjg002")) {
				authorizationrecordEntity.setAt_sqsjqx("");
			}else {
				sqsjqx=authorizationrecordEntity.getAt_sqkssj()+"至"+authorizationrecordEntity.getAt_sqjssj();
				authorizationrecordEntity.setAt_sqsjqx(sqsjqx);
			}
		}
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
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}
	
	/**
	 * 下载授权书
	 * @param request
	 * @param 授权编码id
	 * @return
	 * @throws AjaxException
	 * @throws ParseException
	 */
	@RequestMapping("downloadsReport.html")
	@ResponseBody
	public Object downloadsReport(HttpServletRequest request,String id) throws AjaxException, ParseException{
		Map<String,Object> map = new HashMap<>();
		
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		if(sessionUser == null){
			map.put("success", Boolean.FALSE);
			map.put("message", "session超时，请重新登录");
			return map;
		}		
		
		//设置此id+createid PDFtable正在下载标记*******************************************************************************	
		request.getSession().setAttribute("PDFtable#"+id,"onloading");
		try {
			AuthorizationrecordEntity authorizationrecordEntity=authorizationrecordService.findById(id);
			String htmlstr=loanAgreementService.findByOrgOrLaxyId(authorizationrecordEntity.getOrg_id()).getLaContent();
			String pdfFileName = pdfGenerator.htmlToPdfGenerator(request,id,authorizationrecordEntity,htmlstr);
			map.put("success", Boolean.TRUE);
			map.put("pdfFileName", pdfFileName);
			map.put("message", "下载成功");
		} catch (Exception e) {
			map.put("success", Boolean.FALSE);
			map.put("message", "下载失败，生成pdf过程中发生错误");
			request.getSession().setAttribute("PDFtable#"+id,null);
			map.put("isExistPdf", false);//设置是否下载标记
			return map;
		}
		
		
		return map;
		
	}
	/**
	 *  获取详细信息
	 * @param model
	 * @param request
	 * @param 授权编码id
	 * @return
	 */
	@RequestMapping("tailafterUI.html")
	public String tailafterUI(Model model, HttpServletRequest request, String id) {
		model.addAttribute("id", id);
		return Common.BACKGROUND_PATH + "/authorizationrecord/tailafter";
	}
	
	@ResponseBody
	@RequestMapping("tailafterlist.html")
	public Object tailafterlist(String gridPager,String id) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "at_sqsj DESC");
		List<AuthorizationrecordEntity> list=new ArrayList<>();
		AuthorizationrecordEntity authorizationrecordEntity=authorizationrecordService.findById(id);
		
		list.add(authorizationrecordEntity);
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
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	
}
