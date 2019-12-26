package com.dcits.govsbu.sd.taxbankplatform.count.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.common.CommonConstant;
import com.dcits.govsbu.sd.taxbankplatform.count.model.BusinessdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.service.BusinessdetailsService;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportRecServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 谢翠
 * 银行端-业务明细查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/businessdetails/")
public class BusinessdetailsController extends BaseController {
	@Autowired
	public BusinessdetailsService querybyproductService;
	@Autowired
	public OrganizationsService organizationsService;
	@Autowired
	public FinancialProductService financialProductService;
	@Autowired
	private DownloadsReportRecServiceImpl downloadsReportRecService;
	/**
	 * 返回业务明细页面
	 * @return
	 */
	@RequestMapping("listUI.html")
	public String listUI(HttpServletRequest request) {
		try
		{		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		        Map<String, Object> parameter = OrganizationsUtil.getParameters(organizationList);
		        Long orgId = (Long)parameter.get("orgid");
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("orgId", orgId);
			List<FinancialProduct> loanproduct = financialProductService.selectAllName(parameters);
			request.setAttribute("loanproduct", loanproduct);
			return Common.BACKGROUND_PATH + "/count/businessdetails/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(String gridPager) throws IOException {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> parameter = OrganizationsUtil.getParameters(organizationList);
		Long orgId = (Long)parameter.get("orgid");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("orgId", orgId);
		Map<String, Object> pageParameters = null;
		// 映射Pager对象
		//Map<String,Object> searchParameters=new HashMap<String,Object>();
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		pageParameters = pager.getParameters();
		long province = 0;
		long city = 0;
		long area = 0;
		Iterator<Entry<String, Object>> iter = pageParameters.entrySet().iterator(); 
        while(iter.hasNext()){ 
            Entry<String, Object> entry = iter.next(); 
            String key = entry.getKey(); 
            String value = entry.getValue()==null?null:entry.getValue().toString();
            if("province".equals(key)){
            	if(value!=null)
            		province = Long.parseLong(value);
            }else if("city".equals(key)){
            	if(value!=null)
            		city = Long.parseLong(value);
            }else if("area".equals(key)){
            	if(value!=null)
            		area = Long.parseLong(value);
            }else if("enterprisename".equals(key)){
            	parameters.put("enterprisename", value);
            }else if("starttime".equals(key)){
            	parameters.put("starttime", value);
            }else if("endtime".equals(key)){
            	parameters.put("endtime", value);
            }else if("loanproduct".equals(key)){
            	parameters.put("loanproduct", value);
            }else if("subbranch".equals(key)){
            	parameters.put("subbranch", value);
            }
        }
        if(area != 0) {
			parameters.put("pcId", area);
		}else if(area == 0 && city !=0) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
        Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "la_apply_time DESC");
		List<BusinessdetailsEntity> searchDetails = querybyproductService.searchDetails(parameters);
//		model.addAttribute("details",searchDetails);
		for (int i = 0; i < searchDetails.size(); i++) {
			searchDetails.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", searchDetails);
		return parameters;
	}
	
	/*@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(HttpServletRequest request) throws IOException {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> parameter = OrganizationsUtil.getParameters(organizationList);
		Long orgId = (Long)parameter.get("orgid");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("orgId", orgId);
		long province = 0;
		long city = 0;
		long area = 0;
		if (request.getParameter("province") != null && !"".equals(request.getParameter("province")) ){
			province = Long.parseLong(request.getParameter("province"));
		}
		if (request.getParameter("city") != null && !"".equals(request.getParameter("city"))){
			city = Long.parseLong(request.getParameter("city") );
		}
		if (request.getParameter("area") != null && !"".equals(request.getParameter("area"))){
			area = Long.parseLong(request.getParameter("area"));
		}	
		if(area != 0) {
			parameters.put("pcId", area);
		}else if(area == 0 && city !=0) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String loanproduct = request.getParameter("loanproduct");
		String subbranch = request.getParameter("subbranch");
		
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		parameters.put("loanproduct", loanproduct);
		parameters.put("subbranch", subbranch);
		List<BusinessdetailsEntity> searchDetails = querybyproductService.searchDetails(parameters);
//		model.addAttribute("details",searchDetails);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("details", searchDetails);	
		return JSON.toJSONString(map);
	}*/

	@RequestMapping("downloadReport.html")
	@ResponseBody
	public Object downloadReport(HttpServletRequest request,String id) throws AjaxException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			id = request.getParameter("id");
			DownloadsReportRecEntity drre = downloadsReportRecService.findBylaid2(id);
			String pdfFileName = drre.getReportUrl();
			String v_path = BusinessdetailsController.class.getResource(CommonConstant.pdfPath).toString(); 
			String v_pdfFileName = v_path.substring(6, (v_path.length() - 4))+"attached/pdf/"+pdfFileName;
			File file =new File(v_pdfFileName);
	        if(! file.exists()){       
	        	map.put("message1", "文件不存在");
	        } else {
	        	map.put("success", Boolean.TRUE);
				map.put("pdfFileName", pdfFileName);
				map.put("message", "下载成功");
	        }	
		}catch (Exception e) {
			e.printStackTrace();
			map.put("message1", "下载失败");
		}
		
		return map;
	}
}
