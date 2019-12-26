package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.IndustriesdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.LoansdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.IndustriesstatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.LoansdetailsstatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 谢翠
 * 监管机构用户统计-贷款业务明细查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/loansdetails/")
public class LoansdetailsController {
	@Autowired
	public LoansdetailsstatisticsService loansdetailsstatisticsService;
	@Autowired
	public OrganizationsService organizationsService;
	@Autowired
	private RegionsService regionsService;
	@Autowired
	ProvinceCitiesService provinceCitiesService;
	@Autowired
	public IndustriesstatisticsService industriesstatisticsService;
	/**
	 * 返回贷款业务明细页面
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
				String pcCode = rs.getRegioncode()==null?null:rs.getRegioncode();
				ProvinceCitiesEntity pce = null;
				if(pcCode!=null)
					 pce = provinceCitiesService.findByPccode(pcCode);
				String pcId = pce==null?null:pce.getId();
				//Long pcId = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("pcId", pcId);
				String province =null;
				if(pce!=null)
					province = pce==null?null:pce.getId();
				parameter.put("province", province!=null?province:null);
				List<IndustriesdetailsEntity> searchIndustries = industriesstatisticsService.searchIndustries();
				List<LoansdetailsEntity> loansdetailsstatistics = loansdetailsstatisticsService.loansdetailsstatistics(parameter);
				List<String> hpzt = new ArrayList<String>();
				for (LoansdetailsEntity le : loansdetailsstatistics){
					if (!hpzt.contains(le.getHpzt())){
						hpzt.add(le.getHpzt());
					}
				}
				request.setAttribute("searchIndustries",searchIndustries); 
				request.setAttribute("hpzt",hpzt); 
				
			}
			return Common.BACKGROUND_PATH + "/regulator/loansdetails/formUI";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(HttpServletRequest request) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> pageParameters = null;
		String gridPager = request.getParameter("gridPager");
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		pageParameters = pager.getParameters();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String regionId = sessionUser.getRegionid();
		String province = null;
		String city = null;
		String area = null;
		RegionsEntity rs = regionsService.findById(regionId);
		String pcCode = rs.getRegioncode()==null?null:rs.getRegioncode();
		ProvinceCitiesEntity pce = null;
		if(pcCode!=null)
			 pce = provinceCitiesService.findByPccode(pcCode);
		if(pce!=null)
			province = pce==null?null:pce.getId();
		Iterator<Entry<String, Object>> iter = pageParameters.entrySet().iterator(); 
        while(iter.hasNext()){ 
            Entry<String, Object> entry = iter.next(); 
            String key = entry.getKey(); 
            String value = entry.getValue()==null?null:entry.getValue().toString();
            if("province".equals(key)){
            	if(value!=null)
            		province = value;
            }else if("city".equals(key)){
            	if(value!=null)
            		city = value;
            }else if("area".equals(key)){
            	if(value!=null)
            		area = value;
            }else if("enterprisename".equals(key)){
            	parameters.put("enterprisename", value);
            }else if("starttime".equals(key)){
            	parameters.put("starttime", value);
            }else if("endtime".equals(key)){
            	parameters.put("endtime", value);
            }else if("corporate".equals(key)){
            	parameters.put("corporate", value);
            }else if("approve".equals(key)){
            	parameters.put("approve", value);
            }else if("industries".equals(key)){
            	parameters.put("industries", value);
            }
        }
        if(area != null&&!"0".equals(area)) {
			parameters.put("pcId", area);
		}else if("0".equals(area) && city !=null&&!"0".equals(city)) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
        parameters.put("province", province!=null&&!"0".equals(province)?province:null);
        parameters.put("city", city!=null&&!"0".equals(city)?city:null);
        parameters.put("area", area!=null&&!"0".equals(area)?area:null);
        parameters.put("placeName", "重庆市");
        if(provinceCitiesService.selectPccodeById(parameters)>0){
        	parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
        List<LoansdetailsEntity> loansdetailsstatistics = loansdetailsstatisticsService.loansdetailsstatistics(parameters);
        List<LoansdetailsEntity> rtnLoanDetail = calculate(loansdetailsstatistics,pager);
        for (int i = 0; i < loansdetailsstatistics.size(); i++) {
			loansdetailsstatistics.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount",loansdetailsstatistics.size()%pager.getPageSize()==0?
				loansdetailsstatistics.size()/pager.getPageSize():loansdetailsstatistics.size()/pager.getPageSize()+1);
		parameters.put("recordCount", loansdetailsstatistics.size());
		parameters.put("exhibitDatas", rtnLoanDetail);
		return parameters;
	}
	//分页统计逻辑
	public List<LoansdetailsEntity> calculate(List<LoansdetailsEntity> list,Pager pager){
		if(list==null)return list;
		int listSize = list.size();
		int currentPage = pager.getNowPage();
		int pageSize  = pager.getPageSize();
		int pagerNum = pager.getPageCount();
		List<LoansdetailsEntity> tempList = new ArrayList<LoansdetailsEntity>();
		double applyTotalAmount=0;
		double approvedTotalAmount=0;
		for(int i=0;i<list.size();i++){
			LoansdetailsEntity lde = list.get(i);
			applyTotalAmount = applyTotalAmount+Double.valueOf(lde.getSdje());
			approvedTotalAmount = approvedTotalAmount+Double.valueOf(lde.getSxje());
		}
		if(listSize>=pageSize*currentPage){
			tempList = list.subList((currentPage-1)*pageSize, currentPage*pageSize);
		}else if(listSize<pageSize*currentPage){
			if((currentPage-1)*pageSize<listSize){
				tempList = list.subList((currentPage-1)*pageSize, listSize);
			}else{
				tempList = list;
			}
		}
		if(tempList!=null&&tempList.size()>0){
			tempList.get(0).setApplyTotalAmount(new BigDecimal(Double.valueOf(applyTotalAmount)).setScale(4,BigDecimal.ROUND_HALF_UP)+"");
			tempList.get(0).setApprovedTotalAmount(new BigDecimal(Double.valueOf(approvedTotalAmount)).setScale(4,BigDecimal.ROUND_HALF_UP)+"");
		}
		return tempList;
	}
	/*@RequestMapping(value = "list.html")
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
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String approve = request.getParameter("approve");
		String enterprisename = request.getParameter("enterprisename");
		String corporate = request.getParameter("corporate");
		String industries = request.getParameter("industries");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		parameters.put("approve", approve);
		parameters.put("enterprisename", enterprisename);
		parameters.put("corporate", corporate);
		parameters.put("industries", industries);
		List<LoansdetailsEntity> loansdetailsstatistics = loansdetailsstatisticsService.loansdetailsstatistics(parameters);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loansdetailsstatistics", loansdetailsstatistics);	
		return JSON.toJSONString(map);
	}*/
}
