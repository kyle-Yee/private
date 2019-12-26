package com.dcits.govsbu.sd.taxbankplatform.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.common.CommonConstant;
import com.dcits.govsbu.sd.taxbankplatform.datastorage.service.TaxDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.DeleteService;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportRecServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyAttachEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyTailafterEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.service.LoanApplyQueryService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.ApplyStatus;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEndEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanBankloanTypeEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanExportExcel;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanExportSWSJ;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.SxsjxEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveQueryService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl.DownCQPdf;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl.DownHNPdf;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.pdf.service.CqPdfService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.model.RepaymentWayEntity;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.service.RepaymentWayService;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.SystemService;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.model.TaxAuthorityEntity;
import com.dcits.govsbu.sd.taxbankplatform.taxauthority.service.TaxAuthorityService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfNsqdService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.PdfGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@Scope("prototype")
@RequestMapping("/test/")
public class TestController {
	@Autowired
	private TaxDataSaveService taxDataSaveService;
	@Autowired 
	private TaxPdfNsqdService taxPdfNsqdService;
	//查询(初审/终审)信息列表/详细

	//还款方式列表
	@Autowired
	private RepaymentWayService repaymentWayService;
	//区域码表
	@Autowired
	private ProvinceCitiesService provinceCitiesService; 
	@Autowired
	private TaxAuthorityService taxAuthorityService;
	@Autowired
	private DownloadsReportRecServiceImpl downloadsReportRecService;
	@Autowired
	private DownloadsReportServiceImpl downloadsReportService;
	@Autowired
	private OrganizationsService organizationsService; //组织表
	//下载PDF
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private LoanApproveQueryService loanApproveQueryService;
	//查询跟踪数据
	@Autowired
	private LoanApplyQueryService loanApplyQueryService;
	//录入审批(记录/结果)
	@Autowired
	private LoanApproveService loanApproveService;
	//清空接口数据表
	@Autowired
	private DeleteService deleteService;
	//获取数据校验的开关
	@Autowired
	private SystemService systemService;
	//获取数据失败时记录日志
	@Autowired
	private InvokeRecodeService invokeRecodeService;
	
	//重庆贷款报告下载
	@Autowired
	private DownCQPdf downCQPdf;
	
	//重庆贷款报告下载
	@Autowired
	private DownHNPdf downHNPdf;
	//重庆地区贷款报告下载获取税务数据
	@Autowired
	private CqPdfService cqPdfService;
	
	@RequestMapping("indexUI.html")
	public String indexUI(Model model, HttpServletRequest request){
		return Common.BACKGROUND_PATH + "/test/index";
		
	}
	
	
	/**
	 * 加载贷款申请(待初审/待终审)信息列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("loanapprove.html")
	public String listUI(Model model, HttpServletRequest request) {
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		try{
			PageUtil page = new PageUtil();
			ApplyStatus status = new ApplyStatus();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			status.setStatus(request.getParameter("status"));
			//表头统计，添加org_id限制
			String orgid = sessionUser.getOrgid();
			TotalDataEntity totalDataEntity = loanApproveQueryService.queryTotalData(orgid);
			model.addAttribute("totalDataEntity",totalDataEntity);
			model.addAttribute("page", page);
			model.addAttribute("status", status);
			return Common.BACKGROUND_PATH + "/test/loanapprove";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * ajax分页动态加载模式
	 * @param gridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager,Model model) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		Map<String, Object> parameters = null;
		// 映射Pager, ApplyStatus对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		//parameters.put("status", request.getParameter("status"));
		parameters.put("orgId", sessionUser.getOrgid().toString());
		//parameters.put("orgId", "23");
		
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "");
		List<LoanApproveQueryEntity> list = loanApproveQueryService.queryListByPage(parameters);
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
	 * ajax分页动态加载模式
	 * @param gridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/test.html", method = RequestMethod.POST)
	@ResponseBody
	public Object test(String gridPager,Model model) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		// 映射Pager, ApplyStatus对象
		// 判断是否包含自定义参数
		parameters.put("orgId", sessionUser.getOrgid().toString());
		//parameters.put("status", request.getParameter("status"));
		
		List<LoanApproveQueryEntity> list = loanApproveQueryService.queryListByPage(parameters);

		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	
}
