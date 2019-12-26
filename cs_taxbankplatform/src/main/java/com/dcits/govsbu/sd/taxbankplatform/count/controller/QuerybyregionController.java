package com.dcits.govsbu.sd.taxbankplatform.count.controller;

import java.io.IOException;
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

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.count.model.QuerybyregionEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.service.QuerybyregionService;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.service.LoanApplyQueryService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;

/**
 * 谢翠
 * 数据统计 区域业务查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/querybyregion/")
public class QuerybyregionController extends BaseController {
	
	@Autowired
	public QuerybyregionService querybyregionService;
	
	@Autowired
	public FinancialProductService financialProductService;
	
	@Autowired
	public OrganizationsService organizationsService;
	
	@Autowired
	public LoanApplyQueryService loanApplyQueryService;
	/**
	 * 返回区域业务查询页面
	 * @return
	 */
	@RequestMapping("formUI.html")
	public String formUI(Model model, HttpServletRequest request) {
//		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
//		Map<String, Object> parameters = OrganizationsUtil.getParameters(organizationList);
//		Long regionId = (Long)parameters.get("regionid");
//		Long orgId = (Long)parameters.get("orgid");
		try
		{
//			return Common.BACKGROUND_PATH + "/count/querybyregion/formUI";
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			List<FinancialProduct> loanproduct = financialProductService.selectAllName(null);
			request.setAttribute("loanproduct", loanproduct);
//			Map<String, Object> parameter = new HashMap<String, Object>();
//			parameter.put("regionId", regionId);//所在城市
//			parameter.put("orgId", orgId);
//			List<FinancialProduct> financialProduct = financialProductService.selectAllName(parameter);
			model.addAttribute("page", page);
//			model.addAttribute("financialProduct",financialProduct);
			return Common.BACKGROUND_PATH + "/count/querybyregion/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}	 	
	/**
	 * @throws Exception
	 */
	@RequestMapping(value = "list.html")
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
		if (request.getParameter("province") != null && !"".equals(request.getParameter("province"))){
			province = Long.parseLong(request.getParameter("province"));
		}
		if (request.getParameter("city") != null && !"".equals(request.getParameter("city"))){
			String parameter2 = request.getParameter("city");
			city = Long.parseLong(request.getParameter("city"));
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
		String subbranch = request.getParameter("subbranch");
		
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		parameters.put("subbranch", subbranch);
		List<QuerybyregionEntity> searchDetails = querybyregionService.queryListByPage(parameters);
//		model.addAttribute("details",searchDetails);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> bankList = new ArrayList<String>();
		List<String> creditnumber = new ArrayList<String>();
		List<String> creditline = new ArrayList<String>();
		for(int i = 0; i < searchDetails.size(); i++){
			bankList.add(searchDetails.get(i).getBanklist());
			creditnumber.add(searchDetails.get(i).getCreditnumber());
			creditline.add(searchDetails.get(i).getCreditline());
		}
		map.put("success", Boolean.TRUE);
		map.put("details", searchDetails);	
		map.put("bankList", bankList);
		map.put("creditnumber", creditnumber);
		map.put("creditline", creditline);
		return JSON.toJSONString(map);
	}

}
