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
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.AreastatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

import net.sf.ehcache.search.expression.And;

/**
 * 谢翠
 * 监管机构用户统计-按地区查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/areastatistics/")
public class AreastatisticsController {
	@Autowired
	public AreastatisticsService areastatisticsService;
	
	@Autowired
	public ProvinceCitiesService provinceCitiesService;
	
	/**
	 * 返回按地区查询页面
	 * @return
	 */
	@RequestMapping("formUI.html")
	public Object formUI(HttpServletRequest request) {
		try
		{
//			String province = request.getParameter("province");
//			String city = request.getParameter("city");
//			String area = request.getParameter("area");
//			List<DataCountEntity> userstatistics = pandectstatisticsService.userstatistics(parameter);
//			model.addAttribute("province", province);
//			model.addAttribute("city", city);
//			model.addAttribute("area", "1111");
			return Common.BACKGROUND_PATH + "/regulator/areastatistics/formUI";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping(value = "list1.html")
	@ResponseBody
	public Object list1(HttpServletRequest request) throws IOException {
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
		if(province!=0)parameters.put("province", province);
        if(city!=0)parameters.put("city", city);
        if(area!=0)parameters.put("area", area);
		parameters.put("placeName", "重庆市");
		if(area != 0) {
			parameters.put("currentarea", "area");
			parameters.put("pcId", area);
		}else if(area == 0 && city !=0) {
			parameters.put("currentarea", "city");
			parameters.put("pcId", city);
			
			if(provinceCitiesService.selectPccodeById(parameters) >0){//重庆市特殊处理，定位区 时需要截取4位字符串
				parameters.put("currentarea", "area");
			}
		}else {
			parameters.put("currentarea", "province");
			parameters.put("pcId", province);
		}
		//是否是重庆
		if(provinceCitiesService.selectPccodeById(parameters) >0){
			parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		List<BanklistInfoEntity> areastatistics = areastatisticsService.areastatistics(parameters);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areastatistics", areastatistics);	
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(HttpServletRequest request) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String xzqh = "";
		int level;
		System.out.println(request.getParameter("province")+"这是省");
		if (request.getParameter("province") != null && !request.getParameter("province").equals("") && !request.getParameter("province").equals("0")){
			xzqh = request.getParameter("province");
			level = 1;
		}else {
			System.out.println(xzqh);
			xzqh="430000";
			level=1;
		}
		if (request.getParameter("city") != null && !request.getParameter("city").equals("") && !request.getParameter("city").equals("0")){
			xzqh = request.getParameter("city");
			level = 2;
			System.out.println(request.getParameter("city")+"这是城市");
		}
		if (request.getParameter("area") != null && !request.getParameter("area").equals("") && !request.getParameter("area").equals("0")){
			xzqh = request.getParameter("area");
			level = 3;
			System.out.println(request.getParameter("area")+"这是区");
		}
		System.out.println("行政编码为"+xzqh);
		System.out.println("level为"+level);
		if(Integer.parseInt(xzqh)!=0 && level!=0) {
			parameters.put("xzqh", xzqh);
			parameters.put("level", level);
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		List<BanklistInfoEntity> areastatistics = areastatisticsService.areastatistics1(parameters);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areastatistics", areastatistics);	
		
		String jsonStr = JSON.toJSONString(map);
		return JSON.toJSONString(map);
	}
}
