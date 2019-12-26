package com.dcits.govsbu.sd.taxbankplatform.count.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.count.model.DataCountEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.service.PandectService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsUtil;

/**
 * 谢翠
 * 数据统计总览
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/pandect/")
public class PandectController extends BaseController {
	
	@Autowired
	public PandectService pandectService;
	@Autowired
	private OrganizationsService organizationsService; //组织表
	@Autowired
	private RegionsService regionsService; //区域表
	@Autowired
	ProvinceCitiesService provinceCitiesService; //省市区
	
	
	/**
	 * 返回总览页面
	 * @return
	 */
	@RequestMapping("formUI.html")
	public String formUI() {
		try
		{
			return Common.BACKGROUND_PATH + "/count/pandect/formUI";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}

	/**
	 * 总览-用户信息统计
	 */
	@ResponseBody
	@RequestMapping("userStatistics.html")
	public Object searchUserStatistis(HttpServletRequest request) throws AjaxException{
		Map<String, Object> map = new HashMap<String, Object>();
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> parameter= OrganizationsUtil.getParameters(organizationList);
		String regionId = parameter.get("regionid").toString();
		Long orgId = (Long) parameter.get("orgid");
		if (regionId != null){
			RegionsEntity rs = regionsService.findById(regionId);
			String pcId = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("pcId", pcId);
			parameters.put("orgId", orgId);
			List<DataCountEntity> searchDetails = pandectService.findDataCount(parameters);
			List<PandectEntity> searchByRegionId = pandectService.findDataCountByMonth(parameters);
			//		model.addAttribute("details",searchDetails);
		    List<String> xMonth = new ArrayList<String>();
			Calendar c = Calendar.getInstance();  
		    String  endtime=new SimpleDateFormat("yyyy-MM").format(c.getTime()).toString();  
		    xMonth.add(endtime);
		    for (int i = 0; i < 5; i++){
		    	c.add(Calendar.MONTH, -1);
		    	xMonth.add(new SimpleDateFormat("yyyy-MM").format(c.getTime()).toString());
		    }
		    Collections.reverse(xMonth);
		    List<PandectEntity> sqyhs1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> sqyhs = new ArrayList<PandectEntity>();
		    List<PandectEntity> sxyhs1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> sxyhs = new ArrayList<PandectEntity>();
		    List<PandectEntity> sqbs1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> sqbs = new ArrayList<PandectEntity>();
		    List<PandectEntity> sqje1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> sqje = new ArrayList<PandectEntity>();
		    List<PandectEntity> sxbs1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> sxbs = new ArrayList<PandectEntity>();
		    List<PandectEntity> sxje1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> sxje = new ArrayList<PandectEntity>();
		    List<PandectEntity> swbgxzs1 = new ArrayList<PandectEntity>();
		    List<PandectEntity> swbgxzs = new ArrayList<PandectEntity>();
		    
		   
		    for (int i = 0; i < searchByRegionId.size(); i++){
		    	PandectEntity pe = searchByRegionId.get(i);
		    	if ("r".equals(pe.getCountName())){
		    		sqyhs1.add(pe);	
		    	}
		    	else if ("la-1".equals(pe.getCountName())){
		    		sxyhs1.add(pe);	
		    	}
		    	else if ("la".equals(pe.getCountName())){
		    		sqbs1.add(pe);	
		    	}
		    	else if ("la_amou".equals(pe.getCountName())){
		    		sqje1.add(pe);	
		    	}
		    	else if ("lar".equals(pe.getCountName())){
		    		sxbs1.add(pe);	
		    	}
		    	else if ("lar_ammou".equals(pe.getCountName())){
		    		sxje1.add(pe);	
		    	}
		    	else if ("report".equals(pe.getCountName())){
		    		swbgxzs1.add(pe);	
		    	}
		    }
	    	for (int i = 0; i < xMonth.size(); i++){
	    		for (int j = 0; j < sqyhs1.size(); j++){
	    			if (xMonth.get(i).equals(sqyhs1.get(j).getMonth())){
	    				if (sqyhs.contains(sqyhs1.get(j))){
	    					sqyhs.remove(i);
	    					sqyhs.add(sqyhs1.get(j));
	    				}else {
	    					sqyhs.add(sqyhs1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(sqyhs.contains(pe1)){
	    					sqyhs.remove(i);
	    					sqyhs.add(pe1);
	    				}else {
	    					sqyhs.add(pe1);
	    				}			
	    			}
	    		}
	    		for (int j = 0; j < sxyhs1.size(); j++){
	    			if (xMonth.get(i).equals(sxyhs1.get(j).getMonth())){
	    				if (sxyhs.contains(sxyhs1.get(j))){
	    					sxyhs.remove(i);
	    					sxyhs.add(sxyhs1.get(j));
	    				}else {
	    					sxyhs.add(sxyhs1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(sxyhs.contains(pe1)){
	    					sxyhs.remove(i);
	    					sxyhs.add(pe1);
	    				}else {
	    					sxyhs.add(pe1);
	    				}			
	    			}
	    		}
	    		for (int j = 0; j < sqbs1.size(); j++){
	    			if (xMonth.get(i).equals(sqbs1.get(j).getMonth())){
	    				if (sqbs.contains(sqbs1.get(j))){
	    					sqbs.remove(i);
	    					sqbs.add(sqbs1.get(j));
	    				}else {
	    					sqbs.add(sqbs1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(sqbs.contains(pe1)){
	    					sqbs.remove(i);
	    					sqbs.add(pe1);
	    				}else {
	    					sqbs.add(pe1);
	    				}			
	    			}
	    		}
	    		for (int j = 0; j < sqje1.size(); j++){
	    			if (xMonth.get(i).equals(sqje1.get(j).getMonth())){
	    				if (sqje.contains(sqje1.get(j))){
	    					sqje.remove(i);
	    					sqje.add(sqje1.get(j));
	    				}else {
	    					sqje.add(sqje1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(sqje.contains(pe1)){
	    					sqje.remove(i);
	    					sqje.add(pe1);
	    				}else {
	    					sqje.add(pe1);
	    				}			
	    			}
	    		}
	    		for (int j = 0; j < sxbs1.size(); j++){
	    			if (xMonth.get(i).equals(sxbs1.get(j).getMonth())){
	    				if (sxbs.contains(sxbs1.get(j))){
	    					sxbs.remove(i);
	    					sxbs.add(sxbs1.get(j));
	    				}else {
	    					sxbs.add(sxbs1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(sxbs.contains(pe1)){
	    					sxbs.remove(i);
	    					sxbs.add(pe1);
	    				}else {
	    					sxbs.add(pe1);
	    				}			
	    			}
	    		}
	    		for (int j = 0; j < sxje1.size(); j++){
	    			if (xMonth.get(i).equals(sxje1.get(j).getMonth())){
	    				if (sxje.contains(sxje1.get(j))){
	    					sxje.remove(i);
	    					sxje.add(sxje1.get(j));
	    				}else {
	    					sxje.add(sxje1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(sxje.contains(pe1)){
	    					sxje.remove(i);
	    					sxje.add(pe1);
	    				}else {
	    					sxje.add(pe1);
	    				}			
	    			}
	    		}
	    		for (int j = 0; j < swbgxzs1.size(); j++){
	    			if (xMonth.get(i).equals(swbgxzs1.get(j).getMonth())){
	    				if (swbgxzs.contains(swbgxzs1.get(j))){
	    					swbgxzs.remove(i);
	    					swbgxzs.add(swbgxzs1.get(j));
	    				}else {
	    					swbgxzs.add(swbgxzs1.get(j));
	    				}
	    				break;
	    			}else {
	    				PandectEntity pe1 = new PandectEntity();
	    				pe1.setCountName("r");
	    				pe1.setMonth(xMonth.get(i));
	    				pe1.setCount(0l);
	    				if(swbgxzs.contains(pe1)){
	    					swbgxzs.remove(i);
	    					swbgxzs.add(pe1);
	    				}else {
	    					swbgxzs.add(pe1);
	    				}			
	    			}
	    		}
	    	}
			map.put("success", Boolean.TRUE);
//			map.put("monthcount", searchByRegionId);	
			map.put("details", searchDetails);
			map.put("sqyhs", sqyhs);
			map.put("sxyhs", sxyhs);
			map.put("sqbs", sqbs);
			map.put("sqje", sqje);
			map.put("sxbs", sxbs);
			map.put("sxje", sxje);
			map.put("swbgxzs", swbgxzs);
		}
		return JSON.toJSONString(map);
		

	}
	
}
