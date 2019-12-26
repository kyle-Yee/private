package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.BankliststatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 谢翠
 * 监管机构用户统计-按银行查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/bankliststatistics/")
public class BankliststatisticsController {
	@Autowired
	public BankliststatisticsService bankliststatisticsService;
	@Autowired
	public OrganizationsService organizationsService;
	@Autowired
	private RegionsService regionsService;
	@Autowired
	ProvinceCitiesService provinceCitiesService;
	/**
	 * 返回按银行查询页面
	 * @return
	 */
	@RequestMapping("formUI.html")
	public Object formUI(HttpServletRequest request) {
		try
		{
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
			String regionId = sessionUser.getRegionid();
			if (regionId != null){
				RegionsEntity rs = regionsService.findById(regionId);
				String pcId = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("pcId", pcId);
				List<BanklistInfoEntity> bankliststatistics = bankliststatisticsService.bankliststatistics(parameter);
				request.setAttribute("bankliststatistics",bankliststatistics); 
				
			}
			return Common.BACKGROUND_PATH + "/regulator/bankliststatistics/formUI";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(HttpServletRequest request) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		long province = 0;
		long city = 0;
		long area = 0;
		if (request.getParameter("province") != null){
			province = Long.parseLong(request.getParameter("province"));
		}
		if (request.getParameter("city") != null){
			city = Long.parseLong(request.getParameter("city"));
		}
		if (request.getParameter("area") != null){
			area = Long.parseLong(request.getParameter("area"));
		}	
		if(area != 0) {
			parameters.put("pcId", area);
		}else if(area == 0 && city !=0) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
		if(province!=0)parameters.put("province", province);
        if(city!=0)parameters.put("city", city);
        if(area!=0)parameters.put("area", area);
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String bank = request.getParameter("bank");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		parameters.put("bank", bank);
		parameters.put("placeName", "重庆市");
	    if(provinceCitiesService.selectPccodeById(parameters)>0){
	        parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
		List<BanklistInfoEntity> bankliststatistics = bankliststatisticsService.bankliststatistics(parameters);
		request.setAttribute("bankliststatistics",bankliststatistics); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bankliststatistics", bankliststatistics);	
		return JSON.toJSONString(map);
	}
}
