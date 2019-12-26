package com.dcits.govsbu.sd.taxbankplatform.loanapprove.controller;

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
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
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
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
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
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.PdfGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * LoanApproveController.java
 * @author 严添麟
 * @date 2016年8月15日
 * @caption 贷款申请(初审/终审)
 */
@Controller
@Scope("prototype")
@RequestMapping("/loanapprove/")
public class LoanApproveController extends BaseController {
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
	/**
	 * 加载贷款申请(待初审/待终审)信息列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("listUI.html")
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
			return Common.BACKGROUND_PATH + "/loanapprove/list";
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
	
	
	/**
	 * 功能: 查看附件信息
	 * @param model
	 * @param request
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "showAttachment.html")
	public String showAttachment(Model model, HttpServletRequest request, String path) {
		model.addAttribute("imgPath", "/"+path);
		return Common.BACKGROUND_PATH + "/loanapprove/showAttachment";
	}
	
	
	/**
	 * 功能: 查看企业涉税信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("showEnterpriseTax.html")
	public String showEnterpriseTax(Model model, HttpServletRequest request){
		String url = null;
		switch (request.getParameter("optype")) {
		case CommonConstant.optype_jcxx:
			//企业基本信息
			
			break;
		case CommonConstant.optype_swjcxx:
			//企业税务稽查信息
			
			break;
		case CommonConstant.optype_xydjxx:
			//企业近五年信用等级评定信息
			
			break;
		case CommonConstant.optype_nssbxx:
			//企业纳税申报信息
			
			break;
		case CommonConstant.optype_cwbbxx:
			//财务报表信息
			
			break;
		case CommonConstant.optype_wfwzxx:
			//企业违法违章信息
			url = "/showViolation";
			System.out.println("=====================纳税人识别号:"+request.getParameter("nsrsbh"));
			break;

		default:
			break;
		}
		return Common.BACKGROUND_PATH + "/loanapprove"+url;
	}
	
	
	/**
	 * 获取(贷款/纳税人)等详细信息
	 * @param model
	 * @param request
	 * @param 贷款申请id
	 * @return
	 */
	@RequestMapping("detailsUI.html")
	public String detailsUI(Model model, HttpServletRequest request, String id,String flag) {
		try{
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id);
			//获取产品以及纳税人信息
			LoanApproveQueryEntity loanApproveQueryEntity = loanApproveQueryService.findById(id);
			//获取还款方式信息
			List<RepaymentWayEntity> repaymentWayList = repaymentWayService.queryListByPage(parameters);
			//获取贷款申请数据项信息
			List<LoanApplyAttachEntity> loanApplyAttachList = loanApproveQueryService.queryListAttach(parameters).getLoanApplyAttachList();
			//获取还款方式rw_id
			String hkfs = loanApproveQueryService.getHkfsById(id);
			String[] hkfsArray = hkfs.split("#");
			model.addAttribute("hkfsArray", hkfsArray);
			//分页参数
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			NsryhxxEntity nsryhxxEntity = loanApproveService.findSXSJxById(id);
			List<SxsjxEntity> list = null;
			if(nsryhxxEntity != null){
				list = nsryhxxEntity.getSxsjxMapList();
			}
			//覆盖区域拆分/注入
	        List<ProvinceCitiesEntity> cityEntities = new ArrayList<ProvinceCitiesEntity>();
	        String fpOverlayPcIds = loanApproveQueryEntity.getFinancialProduct().getFpOverlayPcIds();
	        String[] fpOverlayPcIdsArray = fpOverlayPcIds.split("#");
	        for (int i = 0; i < fpOverlayPcIdsArray.length; i++) {
	            String fpOverlayPcIdStr = fpOverlayPcIdsArray[i];
	            if(null !=fpOverlayPcIdStr && fpOverlayPcIdStr.length()>0){
	            	String fpOverlayPcId = fpOverlayPcIdStr;
	            	ProvinceCitiesEntity cityEntity = provinceCitiesService.findById(fpOverlayPcId);
	            	cityEntities.add(cityEntity);
	            }
	        }
	        StringBuilder htmlStr=new StringBuilder();
	        if(list==null){
	        	htmlStr.append( "<tr><td>"+"暂无数据"+ "</td></tr>");
	        }else{
	        	if(list.size()==0){
	        		htmlStr.append( "<tr><td>"+"暂无数据"+ "</td></tr>");
	        	}else{
	        		for(int i=0;i<list.size();i++){
	        			String name = list.get(i).getName();
	        			String value = list.get(i).getValues().replace("#"," ");
	        			if(value==null){
	        				value = "用户没有填写";
	        			}
	        			htmlStr.append( "<tr><td colspan=1 >"+name+"</td>"+"<td colspan=3>"+value+"</td></tr>");
	        		}
	        	}
	        }
			//返回参数
	        model.addAttribute("tbody", htmlStr);
	        model.addAttribute("cityEntities", cityEntities);
	        model.addAttribute("loanApplyAttachList", loanApplyAttachList);
			model.addAttribute("repaymentWayList", repaymentWayList);
			model.addAttribute("page", page);
			model.addAttribute("loanApproveQueryEntity", loanApproveQueryEntity);
			int length = loanApproveQueryEntity.getLoanApproveRecList().size();
			if(length > 0){
				model.addAttribute("approverecAmount", Integer.parseInt(loanApproveQueryEntity.getFinancialProduct().getAmountEntity().getCode()));
				model.addAttribute("approverecdeadline", Integer.parseInt(loanApproveQueryEntity.getFinancialProduct().getDeadlineEntity().getCode()));
				if(length == 1){
					model.addAttribute("approverec", loanApproveQueryEntity.getLoanApproveRecList().get(0));
				}else{
					model.addAttribute("approverec", loanApproveQueryEntity.getLoanApproveRecList().get(1));
				}
				
			}
			String tatus = flag;
			if("1".equals(tatus)){//受理可以编辑
				return Common.BACKGROUND_PATH + "/loanapprove/approve";
			}else{//受理不可以编辑
				return Common.BACKGROUND_PATH + "/loanapprove/noReceive";
			}
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	
	/***
	 * 贷款申请(初审/终审)记录/结果信息录入
	 * @param loanApproveRecEntity
	 * @return
	 * @throws AjaxException
	 */
	@RequestMapping("loanApprove.html")
	@ResponseBody
	public Object loanApprove(LoanApproveFinalEntity loanApproveFinalEntity) throws AjaxException{
		//获取登录用户
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		String phonenumber = request.getParameter("frsjh");
		String qymc = request.getParameter("qymc");
		String dksqsj = request.getParameter("dksqsj");
		String sqyh = request.getParameter("sqyh");
		String sqcp = request.getParameter("sqcp");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			/*
			 * 在终审的时候 如果授信期限起为空的时候在此赋予当前系统时间
			 */
			String lar_time = null;
			if(loanApproveFinalEntity != null ){
				lar_time = loanApproveFinalEntity.getLar_begin();
				if(lar_time == null||lar_time == ""){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					lar_time = df.format(new Date());
					loanApproveFinalEntity.setLar_begin(lar_time);
				}
				String rw_id = loanApproveFinalEntity.getRw_id();
				if(rw_id == null||rw_id == ""){
					loanApproveFinalEntity.setRw_id("0");
				}
			}
			loanApproveFinalEntity.setCreatorid(sessionUser.getId());
			loanApproveFinalEntity.setRegion_id(sessionUser.getRegionid().toString());
			int result = loanApproveService.insert(loanApproveFinalEntity);
			if(result == 1){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "审批完成");
				map.put("phonenumber", phonenumber);
				map.put("qymc", qymc);
				map.put("dksqsj", dksqsj);
				map.put("sqyh", sqyh);
				map.put("sqcp", sqcp);
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "审批失败");
			}
		}catch(ServiceException e){
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "审批失败");
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 进入管理看板首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("kanbansystemlistUI.html")
	public String kanBanSystemListUI(Model model, HttpServletRequest request) {
			return Common.BACKGROUND_PATH + "/loanapprove/kanbansystemlist";
	}
	
	@ResponseBody
	@RequestMapping("kanbansystemlist.html")
	public Object kanBanSystemList(String gridPager) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "tax_id DESC");
	
		List<TaxAuthorityEntity> list = taxAuthorityService.queryListByPage(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}
	/**
	 *  获取受理信息
	 * @param model
	 * @param request
	 * @param 贷款申请id
	 * @return
	 */
	@RequestMapping("receiveUI.html")
	public String receiveUI(Model model, HttpServletRequest request, Long id) {
			return Common.BACKGROUND_PATH + "/loanapprove/receive";
	}
	/**
	 *  获取授信信息
	 * @param model
	 * @param request
	 * @param 贷款申请id
	 * @return
	 */
	@RequestMapping("creditextensionUI.html")
	public String creditextensionUI(Model model, HttpServletRequest request, String id, String flag) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id.toString());
		//分页参数
		PageUtil page = new PageUtil();
		page.setPageNum(Integer.valueOf(request.getParameter("page")));
		page.setPageSize(Integer.valueOf(request.getParameter("rows")));
		page.setOrderByColumn(request.getParameter("sidx"));
		page.setOrderByType(request.getParameter("sord"));
		model.addAttribute("page", page);
		//获取产品以及纳税人信息
		LoanApproveQueryEntity loanApproveQueryEntity = loanApproveQueryService.findById(id);
		List<LoanApproveQueryEntity> loanApproveQueryEntities=loanApproveQueryService.querylaIdListByPage(parameters);
		//获取附件
		//获取贷款申请数据项信息
		List<LoanApplyAttachEntity> loanApplyAttachList = loanApproveQueryService.queryListAttach(parameters).getLoanApplyAttachList();
		//获取授信所需要的数据项
		NsryhxxEntity nsryhxxEntity = loanApproveService.findSXSJxById(id);
		List<SxsjxEntity> list = null;
		if(nsryhxxEntity != null){
			list = nsryhxxEntity.getSxsjxMapList();
		}
		//获取还款方式rw_id
		String hkfs = loanApproveQueryService.getHkfsById(id);
		String[] hkfsArray = hkfs.split("#");
		model.addAttribute("hkfsArray", hkfsArray);
		//获取银行设定的还款方式
		String hkfsYh = loanApproveQueryService.getHkfsYhById(id);
		String[] hkfsYhArray = hkfsYh.split("#");
		model.addAttribute("hkfsYhArray", hkfsYhArray);
        StringBuilder htmlStr=new StringBuilder();
        if(list==null){
        	htmlStr.append( "<tr><td>"+"暂无数据"+ "</td></tr>");
        }else{
        	if(list.size()==0){
        		htmlStr.append( "<tr><td>"+"暂无数据"+ "</td></tr>");
        	}else{
        		for(int i=0;i<list.size();i++){
        			String name = list.get(i).getName();
        			String value = list.get(i).getValues().replace("#"," ");
        			if(value==null){
        				value = "用户没有填写";
        			}
        			htmlStr.append( "<tr><td>"+name+"</td>"+"<td>"+value+"</td></tr>");
        		}
        	}
        }
		model.addAttribute("tbody",htmlStr);
		//获取还款方式信息
		List<RepaymentWayEntity> repaymentWayList = repaymentWayService.queryListByPage(parameters);
		model.addAttribute("loanApproveQueryEntity",loanApproveQueryEntity);
		model.addAttribute("repaymentWayList",repaymentWayList);
		int length = loanApproveQueryEntity.getLoanApproveRecList().size();
		if(length > 0){
			model.addAttribute("approverecAmount", Integer.parseInt(loanApproveQueryEntity.getFinancialProduct().getAmountEntity().getCode()));
			model.addAttribute("approverecdeadline", Integer.parseInt(loanApproveQueryEntity.getFinancialProduct().getDeadlineEntity().getCode()));
			if(length == 1){
				model.addAttribute("approverec", loanApproveQueryEntity.getLoanApproveRecList().get(0));
			}
			if(length > 1){
				model.addAttribute("approverec", loanApproveQueryEntity.getLoanApproveRecList().get(1));
				String lacid = loanApproveQueryEntity.getLoanApproveRecList().get(length-1).getLac_id();
				if(lacid.equals("DKZT07")){
					model.addAttribute("approverecSX", loanApproveQueryEntity.getLoanApproveRecList().get(1));
				}else {
					model.addAttribute("approverecSX", loanApproveQueryEntity.getLoanApproveRecList().get(length-1));
				}
			}
		}
		   model.addAttribute("loanApplyAttachList", loanApplyAttachList);
		String status = flag;
		if("3".equals(status)){//授信可以编辑
			return Common.BACKGROUND_PATH + "/loanapprove/creditextension";
		}else{//授信不可以编辑
			if ("4".equals(status)) {
				LoanApproveQueryEntity loanApproveQueryEntity3 =loanApproveQueryEntities.get(0);
				
				model.addAttribute("approverec2", loanApproveQueryEntity3.getLoanApproveRecList().get(2));
			}else if ("2".equals(status)) {
				LoanApproveQueryEntity loanApproveQueryEntity3 =loanApproveQueryEntities.get(0);
				
				model.addAttribute("approverec2", loanApproveQueryEntity3.getLoanApproveRecList().get(2));
			}
			//从产品申请记录表（状态为3/4，已授信/授信未获得授信）获取银行要求的客户还款方式（唯一）
			model.addAttribute("finalHkfs", loanApproveQueryService.getFinalHkfsById(id));
			
			return Common.BACKGROUND_PATH + "/loanapprove/noCreditExtension";
		}
	}
	/**
	 *  获取跟踪信息
	 * @param model
	 * @param request
	 * @param 贷款申请id
	 * @return
	 */
	@RequestMapping("tailafterUI.html")
	public String tailafterUI(Model model, HttpServletRequest request, String id) {
		model.addAttribute("id", id);
		return Common.BACKGROUND_PATH + "/loanapprove/tailafter";
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "la.la_id DESC");
		LoanApplyQueryEntity loanApplyQueryEntity =loanApplyQueryService.findById(id);
		LoanApplyTailafterEntity tailafterEntity = loanApplyQueryService.findDataById(id);
		List<DownloadsReportRecEntity>  recEntityList= downloadsReportRecService.findDownloadsReportRecById(id);
		LoanApproveRecEntity loanApproveRecEntity = null;
		List<LoanApproveRecEntity> list = new ArrayList<LoanApproveRecEntity>();
//		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String orgName = null;
		if(tailafterEntity != null){
			//total = tailafterEntity.getTotal_days();
			orgName = tailafterEntity.getOrg_name();
		}
		String qymc = null;
		if(loanApplyQueryEntity.getNsryhxxEntity()!=null){
			NsryhxxEntity nsryhxxEntity = loanApplyQueryEntity.getNsryhxxEntity();
			qymc = nsryhxxEntity.getQymc();
		}
		
		if(loanApplyQueryEntity.getLoanApproveRecEntity()!=null){
			for(int i = 0;i<loanApplyQueryEntity.getLoanApproveRecEntity().size();i++){
				loanApproveRecEntity = new LoanApproveRecEntity();
				LoanApproveRecEntity loanApproveRec= loanApplyQueryEntity.getLoanApproveRecEntity().get(i);
				String lasid = loanApproveRec.getLas_id();
				String lafsid = loanApproveRec.getLafs_id();
				Date createdate = loanApproveRec.getCreatetime();
				String s = df.format(createdate);
				/********************提交申请**************************/
				if(lasid.equals("DKZT01")){
					
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(orgName);
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion("企业已提交贷款申请，请银行尽快受理");
					
				}
				/*******************授信完成***********************/
				if(lasid.equals("DKZT03")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(loanApproveRec.getUserEntity().getUserName());
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(orgName+"用户已于"+s+"批准贷款。");
				}
				/************************未受理状态显示的处理信息***************************/
				if(lasid.equals("DKZT05")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					//loanApproveRecEntity.setUserName(loanApproveRec.getUserEntity().getUserName());
					loanApproveRecEntity.setUserName("系统管理员");
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion("已超过受理时间，此笔贷款自动结束。");
					
				}
				/************************待授信状态显示的处理信息***************************/
				if(lasid.equals("DKZT02")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(loanApproveRec.getUserEntity().getUserName());
					loanApproveRecEntity.setLas_id(lasid);
					//loanApproveRecEntity.setLar_opinion("您的贷款在"+total+"个工作日内无银行受理，如果有需要请重新申请，建议调整您的贷款诉求。");
					loanApproveRecEntity.setLar_opinion(orgName+"用户已于"+s+"受理通过，请反馈授信结果。");
					
				}
				/************************未获得授信状态显示的处理信息***************************/
				if(lasid.equals("DKZT04")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(loanApproveRec.getUserEntity().getUserName());
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(orgName +"已于"+s+"拒绝您的贷款申请。");
					
				}
				/************************受理不通过状态显示的处理信息***************************/
				if(lasid.equals("DKZT06") ){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(loanApproveRec.getUserEntity().getUserName());
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(orgName+"用户已于"+s+"拒绝受理。");
					
				}
				/************************已完成状态显示的处理信息***************************/
				//if(loanApproveRecEntity.getLas_id() != 0 && loanApproveRecEntity.getCreatetime() != null)
				if(lasid.equals("DKZT07")){
					/*******************贷款完成 手动终止***********************/
					if(lafsid.equals("DHZT01")){
						loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
						loanApproveRecEntity.setUserName(loanApproveRec.getUserEntity().getUserName());
						loanApproveRecEntity.setLas_id(lasid);
						loanApproveRecEntity.setLar_opinion(orgName+"已于"+s+"确认您的此笔贷款完成。");
						
					}
					/*******************贷款完成自动终止***********************/
					if(lafsid.equals("ZD")){
						loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
						loanApproveRecEntity.setUserName("系统管理员");
						loanApproveRecEntity.setLas_id(lasid);
						loanApproveRecEntity.setLar_opinion("此笔贷款已于"+s+"完成。");
					}
				}
				/************************待批准撤销***************************/
				if(lasid.equals("DKZT08")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(qymc);
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(qymc+"已撤销贷款申请，请银行尽快受理。");
				}
				/************************已批准撤销***************************/
				if(lasid.equals("DKZT09")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(orgName);
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(orgName+"用户已于"+s+"批准撤销。");
				}
				/************************已退单***************************/
				if(lasid.equals("DKZT10")){
					loanApproveRecEntity.setCreatetime(loanApproveRec.getCreatetime());
					loanApproveRecEntity.setUserName(orgName);
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(orgName+"用户已于"+s+"进行退单，此笔贷款结束。");
				}
				list.add(loanApproveRecEntity);
			}
			/***************************下载报告的信息*****************************/
			DownloadsReportRecEntity downloadsReportRecEntity = null;
		    if(recEntityList!=null){
		    	for(int j=0;j<recEntityList.size();j++){
		    		loanApproveRecEntity = new LoanApproveRecEntity();
		    		downloadsReportRecEntity = recEntityList.get(j);
		    		Date createdate = downloadsReportRecEntity.getDownTime();
		    		String lasid = downloadsReportRecEntity.getLasid();
		    		String s = df.format(createdate);
					loanApproveRecEntity.setCreatetime(downloadsReportRecEntity.getDownTime());
					loanApproveRecEntity.setUserName(downloadsReportRecEntity.getDownName());
					if(lasid.equals("DKZT07")){
						lasid = "DKZT11";
					}
					loanApproveRecEntity.setLas_id(lasid);
					loanApproveRecEntity.setLar_opinion(orgName+"用户已于"+s+"下载报告。");
					list.add(loanApproveRecEntity);
		    	}
		    }
		}
		//按createTime时间排序
		if(list.size()>1)
			Collections.sort(list); 
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		//parameters.put("exhibitDatas", loanApplyQueryEntity);
		return parameters;
	}
	
	
	/*
	 * 下载报告逻辑处理
	 * 1.根据条件查询下载报告校验所需要的数据
	 * 1.1 校验未通过直接给出提示 请联系管理员
	 * 1.2 校验通过
	 * 1.2.1 查询下载报告所需要的全部数据生成报告
	 * 1.2.2 报告下载完成时向数据库中插入信息
	 * 1.2.2.1 判断当前用户的下载是否在30日周期计算内
	 * 1.2.2.1.1如果在30日周期内则在下载报告记录日志表中插入操作信息
	 * 1.2.2.1.2如果不在30日周期 如果是首次下载则同时在下载报告记录表 和下载报告记录日志表中都加入操作信息
	 * 否则 在下载报告记录表中将下载此次数增加一次 同时在报告记录表日志表中插入操作信息
	 * 
	 */
	@RequestMapping("downloadsReport.html")
	@ResponseBody
	public Object downloadsReport(HttpServletRequest request,String id,String statue,String creatorid,String lasid,String qymc) throws AjaxException, ParseException{
		Map<String,Object> map = new HashMap<>();
		
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		if(sessionUser == null){
			map.put("success", Boolean.FALSE);
			map.put("message", "session超时，请重新登录");
			return map;
		}
		
		String regionCode = sessionUser.getRegionsEntity().getRegioncode();
		if(regionCode == null || regionCode.length()< 2){
			map.put("success", Boolean.FALSE);
			map.put("message", "对不起，获取区域id异常");
			return map;
		}
		if(regionCode.substring(0, 2).equals("43")){//湖南
			return downHNPdf.downHNPdf(request, id, statue, creatorid, lasid, qymc);
		}else if(regionCode.substring(0, 2).equals("50") || regionCode.substring(0, 2).equals("45")){
			// 50 表示重庆  45 表示广西
			/*
			 * 1.先根据纳税人识别号去查询税务数据表是否有数据
			 * 1.1如果有税务数据直接进入获取税务数据生成pdf报告
			 * 1.2如果没有税务数据调用接口取获取数据
			 * 1.2.1每隔5秒去查询一下数据库如果没有数据在等5秒，如果等待30秒任然没有数据退出循环返回页面
			 
     	 	Calendar now = Calendar.getInstance();
    		int currentMonth = now.get(Calendar.MONTH);
    		String month = String.valueOf(currentMonth+1);
    		if(currentMonth<10){
    			month = "0"+month;
    		}
    		String current = String.valueOf(now.get(Calendar.YEAR));
    		int currentdate = now.get(Calendar.DATE);
    		String nsrsbh = null;
    		nsrsbh = cqPdfService.findNsrsbhById(id);
    		
			now.add(Calendar.YEAR,-1);
			String first = String.valueOf(now.get(Calendar.YEAR));//去年
			now.add(Calendar.YEAR,-1);
			String second = String.valueOf(now.get(Calendar.YEAR));//前一年
			String sqxh = null;
			//获取最新的批次号
		    sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
			if(nsrsbh!=null){
				//获取最新的批次号
				sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
			}
			
			 * 工商登记信息
			 * 
			 
			CqPdfEntity cqPdfEntity = null;
			if(sqxh != null){
				cqPdfEntity = cqPdfService.findCqQyjcxxBySqxh(sqxh);
			}
			
			 *纳税人信用等级 
			 * 
			 
			List<CqSwupjxxEntity> cqSwupjxxList = null;
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("sqxh", sqxh);
			parameters.put("starttime", second);
			parameters.put("endtime", current);
			cqSwupjxxList = cqPdfService.findCqSwpjxx(parameters);
			if(cqPdfEntity != null && cqSwupjxxList != null){
				return downCQPdf.downCQPdf(request, id, statue, creatorid, lasid, qymc);
			}else{
				sqxh = IDGenerate.generateMessageID();
				Map<String,Object> paremterMap = new HashMap<String, Object>();
				paremterMap.put("NSRSBH", nsrsbh);
				paremterMap.put("NSRMC", qymc);
				paremterMap.put("CZYH", "");
				//如果请求来自平台页面，需要调税务接口(此处为异步，不影响流程)
				SendCqXdsq.sendGetTaxRequestToTax(paremterMap, sqxh);
				boolean status = false;
				for(int i=0;i<6;i++){
					try {
						Thread.sleep(5000);
						//String sqxh = null;
						if(nsrsbh!=null){
							//获取最新的批次号
							sqxh = cqPdfService.findSqxhByNsrsbh(nsrsbh);
						}
						
						 * 工商登记信息
						 * 
						 
						if(sqxh != null){
							cqPdfEntity = cqPdfService.findCqQyjcxxBySqxh(sqxh);
						}
						
						 *纳税人信用等级 
						 * 
						 
						Map<String,Object> parameter = new HashMap<String,Object>();
						parameters.put("sqxh", sqxh);
						parameters.put("starttime", second);
						parameters.put("endtime", current);
						cqSwupjxxList = cqPdfService.findCqSwpjxx(parameter);
						if(cqPdfEntity != null && cqSwupjxxList != null){
							status= true;
							break;
						}else{
							continue;
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
						map.put("success", Boolean.FALSE);
						map.put("message", "获取税务数据异常");
						return map;
					}
					
				}
				if(status){
					return downCQPdf.downCQPdf(request, id, statue, creatorid, lasid, qymc);
				}else{
					map.put("success", Boolean.FALSE);
					map.put("message", "尚未获取到税务数据");
					return map;
				}
			}*/
			return downCQPdf.downCQPdf(request, id, statue, creatorid, lasid, qymc);
		}else{
			map.put("success", Boolean.FALSE);
			map.put("message", "对不起，该区域下载报告服务尚未开通");
			return map;
		}
		
	}
	
	
	/**
	 *  获取跟踪信息
	 * @param model
	 * @param request
	 * @param 贷款申请id
	 * @return
	 */
	@RequestMapping("postloanUI.html")
	public String postloanUI(Model model, HttpServletRequest request) {

		return Common.BACKGROUND_PATH + "/loanapprove/finallist";
	}
	
	@ResponseBody
	@RequestMapping("postloanlist.html")
	public Object postloanlist(String gridPager) throws Exception{
		 SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
		 /**** 2017/07/13 zhongyj 注释掉下面的语句是没有用到的***/
		/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);*/
		 HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession"); 
		String regionId = sessionUser.getRegionid();						//用户登录之后  ，获取区域id
		Map<String, Object> parameters = null;
 
	
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		
		// 判断是否包含自定义参数
	
		parameters = pager.getParameters();
		if(parameters.get("pcId")==null||parameters.get("pcId").equals(" ")){
			if(regionId.equals("KTQY0002017010100000000000") || regionId==null){
				parameters.put("regionId", regionId);
			}else {
				ProvinceCitiesEntity provinceCitiesEntity= provinceCitiesService.findPcID(regionId);
				String pcId=provinceCitiesEntity.getId();
					parameters.put("pcId", pcId);
				
			}
			
		
		}
		
	
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "laf.laf_id desc");
		parameters.put("orgId",sessionUser.getOrgid());
		List<LoanApplyFinalEntity> list =loanApproveQueryService.findByEx (parameters);
	    for (LoanApplyFinalEntity loanApplyFinalEntity : list) {
	    	  String laId=loanApplyFinalEntity.getLa_id();
		      String date= 	downloadsReportRecService.findBylaid(laId);
			  loanApplyFinalEntity.setLoan_down_date(date);
			  /**** 2017/07/13 zhongyj 注释掉下面的语句是没有用到的***/
			  //LoanApproveRecEntity approveRecEntity=loanApplyFinalEntity.getLoanApproveRecList();
			  loanApplyFinalEntity.setBegin(dateFormat.format(loanApplyFinalEntity.getLoanApproveRecList().getLar_begin()));
			  
		/*	  approveRecEntity.setLar_begin(dateFormat.parse(loanApplyFinalEntity.getLoanApproveRecList().getLar_begin().toString()));
			  loanApplyFinalEntity.setLoanApproveRecList(approveRecEntity)   ;*/
		
		}

		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	
	/**
	 *  授信终止UI
	 * @param model
	 * @param request
	 * @param 最终贷款id
	 * @return
	 */
	@RequestMapping("crediToTerminateUI.html")
	public String crediToTerminateUI(Model model, HttpServletRequest request, String id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("lafId", id);
	    parameters.put("orgId",Common.getloginUserOrgid());
		List<LoanApplyFinalEntity> loanApplyFinalEntity = loanApproveQueryService.findByEx(parameters);
	    for (LoanApplyFinalEntity loanApplyFinalEntity2 : loanApplyFinalEntity) {
	    	 model.addAttribute("loanApplyFinalEntity",loanApplyFinalEntity2);
	    	 String a=loanApplyFinalEntity2.getLaname();
	    	 String regEx="[^0-9]";   
		     Pattern p = Pattern.compile(regEx);   
   		     Matcher laName = p.matcher(a);   
   		     //System.out.println( laName.replaceAll("").trim());
   		     model.addAttribute("laName",laName.replaceAll("").trim());
		}
	    List<LoanBankloanTypeEntity> loanBankloanTypeEntities=loanApproveQueryService.bankloanTypeList();
	    model.addAttribute("loanBankloanTypeEntities",loanBankloanTypeEntities);
	    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String date=dateFormat.format(new Date());
	    model.addAttribute("time",date);
	   
		return Common.BACKGROUND_PATH + "/loanapprove/editList";
	}
	
	/**
	 * 手动终止
	 * @param
	 * @throws Exception
	 */
	@RequestMapping("addEnd.html")
	@Transactional
	@ResponseBody
	public Object addEnd(LoanApplyFinalEndEntity loanApplyFinalEndEntity,Model model, String bankloan_type,String tthNsrmc,String tthNsrsbh,
			
			String lae_credit_quota,int lae_overdue_count,String laId
			,String la_id,String lacId,String laf_id) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int results=0;
		try
		{   	

	       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	       HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
	    	Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("lafId", laf_id);
		    parameters.put("orgId",sessionUser.getOrgid());
			List<LoanApplyFinalEntity> loanApplyFinalEntity = loanApproveQueryService.findByEx(parameters);
	    	StringBuffer sb = new StringBuffer();
	        loanApplyFinalEndEntity.setNsrmc(tthNsrmc);
	        loanApplyFinalEndEntity.setNsrbh(tthNsrsbh);
	        loanApplyFinalEndEntity.setLae_endDate(new Date());
	        loanApplyFinalEndEntity.setLae_credit_quota(lae_credit_quota);
	        loanApplyFinalEndEntity.setLae_overdue_count(lae_overdue_count);
	        loanApplyFinalEndEntity.setBankloan_type(bankloan_type);
	        loanApplyFinalEndEntity.setLaf_id(laf_id);
	        loanApplyFinalEndEntity.setLafs_id("2");
	        loanApplyFinalEndEntity.setCreatorid(sessionUser.getId());
	        loanApplyFinalEndEntity.setCreatetime(new Date());
	        loanApplyFinalEndEntity.setUpdatorid(sessionUser.getId());
	        loanApplyFinalEndEntity.setUpdatetime(new Date());	
	        
	        if(  loanApproveQueryService.insertItem(loanApplyFinalEndEntity)>0){
	        	LoanApproveRecEntity loanApproveRecEntity=new LoanApproveRecEntity();
	        	loanApproveRecEntity.setLa_id(la_id);
	        	loanApproveRecEntity.setLar_credit_quota(lae_credit_quota);
	        	loanApproveRecEntity.setLafs_id("DHZT01");
	        	loanApproveRecEntity.setCreatorid(sessionUser.getId());
	        	loanApproveRecEntity.setCreatetime(new Date());
	        	loanApproveRecEntity.setUpdatorid(sessionUser.getId());
	        	loanApproveRecEntity.setUpdatetime(new Date());
	        	  for (LoanApplyFinalEntity loanApplyFinalEntity2 : loanApplyFinalEntity) {
	  		    	model.addAttribute("loanApplyFinalEntity",loanApplyFinalEntity2);
	  		    	loanApproveRecEntity.setLar_bank_account(loanApplyFinalEntity2.getLoanApproveRecList().getLar_bank_account());
	  		    	loanApproveRecEntity.setLar_bank_name(loanApplyFinalEntity2.getLoanApproveRecList().getLar_bank_name());
	  		    	loanApproveRecEntity.setRegion_id(loanApplyFinalEntity2.getLoanApproveRecList().getRegion_id());
	  		    	loanApproveRecEntity.setLas_id("DKZT07");
	  		    	loanApproveRecEntity.setFp_id(loanApplyFinalEntity2.getLoanApproveRecList().getFp_id());
	  		    	loanApproveRecEntity.setLac_id("YDKZT03");
	  		    	loanApproveRecEntity.setLar_loan_deadline(loanApplyFinalEntity2.getLoanApproveRecList().getLar_loan_deadline());
	  		    	loanApproveRecEntity.setLar_begin(loanApplyFinalEntity2.getLoanApproveRecList().getLar_begin());
	  		    	loanApproveRecEntity.setLar_end(loanApplyFinalEntity2.getLoanApproveRecList().getLar_end());
	  		    	loanApproveRecEntity.setLar_rate(loanApplyFinalEntity2.getLoanApproveRecList().getLar_rate());
	  		    	loanApproveRecEntity.setLar_isoverlay_area(loanApplyFinalEntity2.getLoanApproveRecList().getLar_isoverlay_area());
	  		    	loanApproveRecEntity.setRw_id(loanApplyFinalEntity2.getLoanApproveRecList().getRw_id());
	  		    	loanApproveRecEntity.setLar_contract(loanApplyFinalEntity2.getLoanApproveRecList().getLar_contract());
	  		    	loanApproveRecEntity.setLar_opinion(loanApplyFinalEntity2.getLoanApproveRecList().getLar_opinion());
	  		    	int res=loanApproveQueryService.insertRec(loanApproveRecEntity);
	  			}
	        	  String lafid=laf_id;
	        	  LoanApproveQueryEntity loanApproveQueryEntity2=new LoanApproveQueryEntity();
	        	  loanApproveQueryEntity2.setId(laId);  
	        	  loanApproveQueryEntity2.setUpdatorid(sessionUser.getId());
	        	  loanApproveQueryService.updatela(loanApproveQueryEntity2);
	        	  LoanApplyFinalEntity loanApplyFinalEntity2=new LoanApplyFinalEntity();
	        	  loanApplyFinalEntity2.setId(lafid);
	        	  loanApplyFinalEntity2.setUpdatorid(sessionUser.getId());
	        	  loanApplyFinalEntity2.setLafs_id("1");
	        	   results=  loanApproveQueryService.updatelaf(loanApplyFinalEntity2);
	        }
			if(results==1)
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
	 * 自动终止
	 * @param
	 * @throws ParseException 
	 * @throws Exception
	 */
	/*public void automaticEnd() throws AjaxException, ParseException
	{  
		int results=0;
 		LoanApplyFinalEndEntity loanApplyFinalEndEntity=new LoanApplyFinalEndEntity();
		try
		{   	
	
	       SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间格式
           Date nowTime=new Date();//获取当前时间
           String   tmf= matter1.format(nowTime);
           Date now=matter1.parse(tmf);
	       SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       Map<String, Object> parameters = new HashMap<String, Object>();
		   List<LoanApplyFinalEntity> loanApplyFinalEntity = loanApproveQueryService.findByEx(parameters);
		   for (LoanApplyFinalEntity loanApplyFinalEntity2 : loanApplyFinalEntity) {
		    	  Date end=matter1.parse(simpleDateFormat.format(loanApplyFinalEntity2.getLoanApproveRecList().getLar_end()));//atime是数据库中事前存的时间
                  long cha=(now.getTime()-end.getTime())/ (1000 * 60 * 60 * 24);//计算时间差
				if(cha>=0&&loanApplyFinalEntity2.getLoanApproveQueryEntity().getLa_status()!=7){
					loanApplyFinalEndEntity.setNsrmc(loanApplyFinalEntity2.getNsryhxxEntity().getQymc());
			        loanApplyFinalEndEntity.setNsrbh(loanApplyFinalEntity2.getNsryhxxEntity().getNsrsbh());
			        loanApplyFinalEndEntity.setLae_endDate(new Date());
			        loanApplyFinalEndEntity.setLae_credit_quota(String.valueOf(loanApplyFinalEntity2.getLoanApproveRecList().getLar_credit_quota()));
			        loanApplyFinalEndEntity.setLae_overdue_count(0);
			        loanApplyFinalEndEntity.setBankloan_type(0);
			        loanApplyFinalEndEntity.setLaf_id(Integer.parseInt(loanApplyFinalEntity2.getId().toString()));
			        loanApplyFinalEndEntity.setLafs_id(2);
			        loanApplyFinalEndEntity.setCreatorid(loanApplyFinalEntity2.getCreatorid());
			        loanApplyFinalEndEntity.setCreatetime(new Date());
			        loanApplyFinalEndEntity.setUpdatorid(loanApplyFinalEntity2.getCreatorid());
			        loanApplyFinalEndEntity.setUpdatetime(new Date());  
			        if(loanApproveQueryService.insertItem(loanApplyFinalEndEntity)>0){
			        	LoanApproveRecEntity loanApproveRecEntity=new LoanApproveRecEntity();
			        	loanApproveRecEntity.setLa_id(loanApplyFinalEntity2.getLa_id());
			        	loanApproveRecEntity.setLar_credit_quota(loanApplyFinalEntity2.getLoanApproveRecList().getLar_credit_quota());
			        	loanApproveRecEntity.setLafs_id(2);
			        	loanApproveRecEntity.setCreatorid(loanApplyFinalEntity2.getCreatorid());
			        	loanApproveRecEntity.setCreatetime(new Date());
			        	loanApproveRecEntity.setUpdatorid(loanApplyFinalEntity2.getCreatorid());
			        	loanApproveRecEntity.setUpdatetime(new Date());
		  		    	loanApproveRecEntity.setLar_bank_account(loanApplyFinalEntity2.getLoanApproveRecList().getLar_bank_account());
		  		    	loanApproveRecEntity.setLar_bank_name(loanApplyFinalEntity2.getLoanApproveRecList().getLar_bank_name());
		  		    	loanApproveRecEntity.setRegion_id(loanApplyFinalEntity2.getLoanApproveRecList().getRegion_id());
		  		    	loanApproveRecEntity.setLas_id(7);
		  		    	loanApproveRecEntity.setFp_id(loanApplyFinalEntity2.getLoanApproveRecList().getFp_id());
		  		    	loanApproveRecEntity.setLac_id(3);
		  		    	loanApproveRecEntity.setLar_loan_deadline(loanApplyFinalEntity2.getLoanApproveRecList().getLar_loan_deadline());
		  		    	loanApproveRecEntity.setLar_begin(loanApplyFinalEntity2.getLoanApproveRecList().getLar_begin());
		  		    	loanApproveRecEntity.setLar_end(loanApplyFinalEntity2.getLoanApproveRecList().getLar_end());
		  		    	loanApproveRecEntity.setLar_rate(loanApplyFinalEntity2.getLoanApproveRecList().getLar_rate());
		  		    	loanApproveRecEntity.setLar_isoverlay_area(loanApplyFinalEntity2.getLoanApproveRecList().getLar_isoverlay_area());
		  		    	loanApproveRecEntity.setRw_id(loanApplyFinalEntity2.getLoanApproveRecList().getRw_id());
		  		    	loanApproveRecEntity.setLar_contract(loanApplyFinalEntity2.getLoanApproveRecList().getLar_contract());
		  		    	loanApproveRecEntity.setLar_opinion(loanApplyFinalEntity2.getLoanApproveRecList().getLar_opinion());
		  		    	int res=loanApproveQueryService.insertRec(loanApproveRecEntity);    
						LoanApproveQueryEntity loanApproveQueryEntity2=new LoanApproveQueryEntity();
						loanApproveQueryEntity2.setId(Long.parseLong(String.valueOf(loanApplyFinalEntity2.getLa_id())));
						loanApproveQueryEntity2.setLa_status(7);
						loanApproveQueryEntity2.setUpdatorid(loanApplyFinalEntity2.getCreatorid());	 
						loanApproveQueryService.updatela(loanApproveQueryEntity2);
						LoanApplyFinalEntity loanApplyFinalEntity3=new LoanApplyFinalEntity();
						loanApplyFinalEntity3.setLafs_id("2");
						loanApplyFinalEntity3.setId(loanApplyFinalEntity2.getId());
						loanApplyFinalEntity3.setUpdatorid(loanApplyFinalEntity2.getCreatorid());			        	 
			        	try {
			        		  results=  loanApproveQueryService.updatelaf(loanApplyFinalEntity3);
						} catch (Exception e) {
							return;
						}
			        }
				}  
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
	}
	public void changeStatues() throws Exception{
		  SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间格式
          Date nowTime=new Date();//获取当前时间
          String   tmf= matter1.format(nowTime);
          Date now=matter1.parse(tmf);
		List<LoanApproveQueryEntity> approveQueryEntities=loanApproveQueryService.findBystatues();
		for (LoanApproveQueryEntity loanApproveQueryEntity : approveQueryEntities) {
			if(loanApproveQueryEntity.getFinancialProduct().getAcceptDeadLineEntity().getTlTotalDays()!=null&&loanApproveQueryEntity.getFinancialProduct().getAcceptDeadLineEntity().getTlTotalDays()!=0){
			Calendar calen = Calendar.getInstance();
			 //日历对象默认的日期为当前日期，调用setTime设置该日历对象的日期为程序中指定的日期
			 calen.setTime(loanApproveQueryEntity.getLa_apply_time());
		     //将日历的"天"增加5
			calen.add(Calendar.DAY_OF_YEAR, Integer.parseInt(loanApproveQueryEntity.getFinancialProduct().getAcceptDeadLineEntity().getTlTotalDays().toString()));
             //获取日历对象的时间，并赋给日期对象c
			 Date newDealLine=calen.getTime();
			//用f格式化c并输出
		    System.out.println(matter1.format(newDealLine));
		    long cha=(now.getTime()-newDealLine.getTime())/ (1000 * 60 * 60 * 24);//计算时间差
		       if(cha>=0){
		    	   LoanApproveQueryEntity loanApproveQueryEntity2=new LoanApproveQueryEntity();
		    	   loanApproveQueryEntity2.setId(loanApproveQueryEntity.getId());
		    	   loanApproveQueryService.updatelabydealline(loanApproveQueryEntity2);
		        }else {
				
				}
			}else {
				
			}
		}
	
	}
	*/
	
	/**
	 * @category 导出表格
	 * @param list
	 */
	@RequestMapping(value = "downloadExport.html")
	public void downloadExport(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<>();
		//获取参数
		String apply_star = request.getParameter("A_apply_star");
		String apply_end = request.getParameter("A_apply_end");
		String nsrmc = request.getParameter("A_nsrmc");
		String status = request.getParameter("A_status");
		
		if(apply_star!=null && !"".equals(apply_star) && apply_end != null && !"".equals(apply_end)){
			map.put("apply_star", apply_star);
			map.put("apply_end", apply_end);
		}
		map.put("nsrmc",nsrmc );
		map.put("status", status);
		
		
		//查询参数
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		map.put("orgId", sessionUser.getOrgid().toString());
		map.put("nsrsbh","");
		
		//获取对应数据
		List<LoanExportExcel> list = loanApproveQueryService.exportExcel(map);
		
		String path = 	request.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"batchExcel"+File.separator+"LoanExportExcel.xls";
		InputStream in;
		HSSFWorkbook wb = null;
		try {
			File file = new File(path);
			in = new FileInputStream(file);
			wb = new HSSFWorkbook(new POIFSFileSystem(in));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowIndex=1;
//		int seriesCount = 1;//税务数据的份数
		for (LoanExportExcel loanExportExcel: list) {
			Row row=sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(loanExportExcel.getLaId());//改为显示税银平台的申请id
			row.createCell(1).setCellValue(loanExportExcel.getLaApplyTime().substring(0, 19));
			row.createCell(2).setCellValue(loanExportExcel.getFpName());
			row.createCell(3).setCellValue(loanExportExcel.getLasBankStatus());
			row.createCell(4).setCellValue(loanExportExcel.getLarOpinion());
			row.createCell(5).setCellValue(loanExportExcel.getNsryhxxQymc());
			row.createCell(6).setCellValue(loanExportExcel.getLaAmount());
			row.createCell(7).setCellValue(loanExportExcel.getLarCreditQuota());
			row.createCell(8).setCellValue(loanExportExcel.getLaRepayLoanDeadline());
			
			//还款方式，码值转换
			List<Map<String,Object>> rwList = loanApproveQueryService.getAllRepayWay();
			String rw_id = loanExportExcel.getRwId();
			List<String> finalString = new ArrayList<>();
			if(rw_id != null && rw_id != ""){
				String rw_ids[] = rw_id.split("#");
				for (int i = 0; i < rw_ids.length; i++) {
					if (rwList.size()>0) {
						for (Map<String, Object> map2 : rwList) {
							if (map2.get("id") != null) {
								if(rw_ids[i]!="" && (((String)map2.get("id")).equals( rw_ids[i]))){
									finalString.add((String) map2.get("way"));
									break;
								}
							}
						}
					}
				}
				row.createCell(9).setCellValue(StringUtils.join(finalString, "|"));
			}
			
			row.createCell(10).setCellValue(loanExportExcel.getFrsjh());
			row.createCell(11).setCellValue(loanExportExcel.getNsryhxxQymc());
			row.createCell(12).setCellValue(loanExportExcel.getNsrsbh());
			row.createCell(13).setCellValue(loanExportExcel.getZzjgDm());
			row.createCell(14).setCellValue(loanExportExcel.getZcdz());
			row.createCell(15).setCellValue(loanExportExcel.getZcdlxdh());
			row.createCell(16).setCellValue(loanExportExcel.getFddbrmc());
			
			row.createCell(17).setCellValue("正常户");//纳税人管理状态
			
			List<LoanExportSWSJ> listSwsj = loanExportExcel.getSwsjList();
			//添加税务数据(列表表头根据税务数据份数动态添加)
			/*if(listSwsj != null && listSwsj.size() > seriesCount){
				int need = listSwsj.size() - seriesCount;//份数不够，表头添加n份税务数据列
				Row headRow = sheet.getRow(0);//获取表头
				for(int i = 1;i <= need;i++){
					for (int j = 0; j < 8; j++) {
						Cell cellNew = headRow.createCell(18+i*8+j);
						Cell cellOld = headRow.getCell(18+i*8-8+j);
						cellNew.setCellValue(cellOld.getStringCellValue());
						cellNew.setCellStyle(cellOld.getCellStyle());
					}
					seriesCount++;
				}
				
			}*/
			int columnNo = 18;
			//只取两条
			for (int i = 0,size = listSwsj.size(); i < size && i < 2; i++) {
				LoanExportSWSJ loanExportSWSJ = listSwsj.get(i);
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getSkjnssq());//税款缴纳所属期
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getSdssjnse());//所得税实际纳税额
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getSdsjmtse());//所得税减免退税额
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getSdsynsehj());//所得税应纳税额合计
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getZzssjnse());//增值税实际税额
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getZzsjmtse());//增值税减免退税额
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getZzsynsehj());//增值税应纳税额合计
				row.createCell(columnNo++).setCellValue(loanExportSWSJ.getHj());//合计
			}
			
			
		}
		
		//设置列宽
		for(int i = 0,n = sheet.getRow(0).getLastCellNum();i <= n; i++){
			sheet.setColumnWidth(i,256*16+184);
		}
		
		//设置单元格样式
		HSSFCellStyle cellStyle = wb.createCellStyle(); 
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);   
//		cellStyle.setWrapText(true);//自动换行
		for(int i = 1,n = sheet.getLastRowNum(); i <= n; i++){
			for(int j = 0,m = sheet.getRow(0).getLastCellNum();j <= m;j++){
				Row row = sheet.getRow(i);
				if(row!= null){
					Cell cell = row.getCell(j);
					if(cell != null){
						cell.setCellStyle(cellStyle);
					}
				}
			}
		}
		
		try {
			export(response,wb,"YSPT_checkedForm.xls");//导出表格
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//导出工具
	private static void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
		String fileNames[] = fileName.split("\\.");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer name = new StringBuffer(fileNames[0]).append(sdf.format(new Date())).append(".").append(fileNames[1]);
		response.setHeader("Content-Disposition", "attachment;filename="+new String(name.toString().getBytes("utf-8"),"iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}
	
	
}
