package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.controller;

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
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.EnterpriseInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.EnterprisestatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 谢翠
 * 监管机构用户统计-企业注册明细查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/enterprisestatistics/")
public class EnterprisestatisticsController {
	@Autowired
	public EnterprisestatisticsService enterprisestatisticsService;
	/**
	 * 返回企业注册明细页面
	 * @return
	 */
	@Autowired
	private RegionsService regionsService;
	
	@Autowired
	ProvinceCitiesService provinceCitiesService;
	
	@RequestMapping("formUI.html")
	public Object formUI(HttpServletRequest request) {
		try
		{
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			String province = request.getParameter("province");
//			String city = request.getParameter("city");
//			String area = request.getParameter("area");
//			parameters.put("area", area);
//			List<BanklistInfoEntity> bankliststatistics = bankliststatisticsService.bankliststatistics(parameters);
//			request.setAttribute("bankliststatistics",bankliststatistics); 
			return Common.BACKGROUND_PATH + "/regulator/enterprisestatistics/formUI";
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
		// 判断是否包含自定义参数
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
            	parameters.put("qymc", value);
            }else if("starttime".equals(key)){
            	parameters.put("starttime", value);
            }else if("endtime".equals(key)){
            	parameters.put("endtime", value);
            }else if("corporate".equals(key)){
            	parameters.put("frxm", value);
            }else if("phonenumber".equals(key)){
            	parameters.put("frsjh", value);
            }
            /*switch (key){
            case "province":{
            	if(value!=null)
            		province = Long.parseLong(value);
            };
            case "city":{
            	if(value!=null)
            		city = Long.parseLong(value);
            };
            case "area":{
            	if(value!=null)
            		area = Long.parseLong(value);
            };
            case "enterprisename":{
            	parameters.put("enterprisename", value);
            }
            case "starttime":{
            	parameters.put("starttime", value);
            };
            case "loanproduct":{
            	parameters.put("endtime", value);
            };
            case "corporate":{
            	parameters.put("frxm", value);
            };
            case "phonenumber":{
            	parameters.put("frsjh", value);
            };
            }*/
        }
        if(area != null) {
			parameters.put("pcId", area);
		}else if(area == null && city !=null) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
        parameters.put("province", province!= null&&!"0".equals(province)?province:null);
        parameters.put("city", city!=null&&!"0".equals(city)?city:null);
        parameters.put("area", area!=null&&!"0".equals(area)?area:null);
        parameters.put("placeName", "重庆市");
        parameters.put("isAuth", null);
        if(provinceCitiesService.selectPccodeById(parameters)>0){
        	parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
        Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(),"nx.createtime DESC");
        List<EnterpriseInfoEntity> enterprisestatistics = enterprisestatisticsService.enterprisestatistics(parameters);
//		model.addAttribute("details",searchDetails);
		for (int i = 0; i < enterprisestatistics.size(); i++) {
			enterprisestatistics.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", enterprisestatistics);
		return parameters;
	}
	
	@RequestMapping("authFormUI.html")
	public Object authFormUI(HttpServletRequest request) {
		try
		{
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			String province = request.getParameter("province");
//			String city = request.getParameter("city");
//			String area = request.getParameter("area");
//			parameters.put("area", area);
//			List<BanklistInfoEntity> bankliststatistics = bankliststatisticsService.bankliststatistics(parameters);
//			request.setAttribute("bankliststatistics",bankliststatistics); 
			return Common.BACKGROUND_PATH + "/regulator/enterprisestatistics/authFormUI";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	@RequestMapping(value = "authUserlist.html")
	@ResponseBody
	public Object authUserlist(HttpServletRequest request) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> pageParameters = null;
		String gridPager = request.getParameter("gridPager");
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
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
            	parameters.put("qymc", value);
            }else if("starttime".equals(key)){
            	parameters.put("starttime", value);
            }else if("endtime".equals(key)){
            	parameters.put("endtime", value);
            }else if("corporate".equals(key)){
            	parameters.put("frxm", value);
            }else if("phonenumber".equals(key)){
            	parameters.put("frsjh", value);
            }
            /*switch (key){
            case "province":{
            	if(value!=null)
            		province = Long.parseLong(value);
            };
            case "city":{
            	if(value!=null)
            		city = Long.parseLong(value);
            };
            case "area":{
            	if(value!=null)
            		area = Long.parseLong(value);
            };
            case "enterprisename":{
            	parameters.put("enterprisename", value);
            }
            case "starttime":{
            	parameters.put("starttime", value);
            };
            case "loanproduct":{
            	parameters.put("endtime", value);
            };
            case "corporate":{
            	parameters.put("frxm", value);
            };
            case "phonenumber":{
            	parameters.put("frsjh", value);
            };
            }*/
        }
        if(area != null&&!"0".equals(area)) {
			parameters.put("pcId", area);
		}else if("0".equals(area) && city !=null&&!"0".equals(city)) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
        parameters.put("province", province!= null&&!"0".equals(province)?province:null);
        parameters.put("city", city!=null&&!"0".equals(city)?city:null);
        parameters.put("area", area!=null&&!"0".equals(area)?area:null);
        parameters.put("placeName", "重庆市");
        parameters.put("isAuth", 1);
        if(provinceCitiesService.selectPccodeById(parameters)>0){
        	parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
        Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(),"nx.createtime DESC");
        List<EnterpriseInfoEntity> enterprisestatistics = enterprisestatisticsService.enterprisestatistics(parameters);
//		model.addAttribute("details",searchDetails);
		for (int i = 0; i < enterprisestatistics.size(); i++) {
			enterprisestatistics.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", enterprisestatistics);
		return parameters;
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
		String qymc = request.getParameter("enterprisename");
		String frxm = request.getParameter("corporate");
		String frsjh = request.getParameter("phonenumber");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		parameters.put("qymc", qymc);
		parameters.put("frxm", frxm);
		parameters.put("frsjh", frsjh);
		List<EnterpriseInfoEntity> enterprisestatistics = enterprisestatisticsService.enterprisestatistics(parameters);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enterprisestatistics", enterprisestatistics);	
		return JSON.toJSONString(map);
	}*/
}
