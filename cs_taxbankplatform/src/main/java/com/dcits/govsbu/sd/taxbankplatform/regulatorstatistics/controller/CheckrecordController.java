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
 * yaofang
 * 监管机构用户统计-企业注册明细查询
 * @author 16420
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/checkrecord/")
public class CheckrecordController {
	@Autowired
	public EnterprisestatisticsService enterprisestatisticsService;
	/**
	 * 返回企业认证记录详情页面
	 * @return
	 */
	@Autowired
	private RegionsService regionsService;
	
	@Autowired
	ProvinceCitiesService provinceCitiesService;
	
	@RequestMapping("listUI.html")
	public Object listUI(HttpServletRequest request) {
		try
		{

			return Common.BACKGROUND_PATH + "/regulator/enterprisestatistics/list";
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
		String otId=sessionUser.getOrganizationEntity().getOtid();
		/*if(("ZZLX004").equals(otId)){
		}else{
			parameters.put("isSuccess", Boolean.TRUE);
			parameters.put("nowPage", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
			parameters.put("pageCount", 0);
			parameters.put("recordCount", 0);
			parameters.put("startRecord", 0);
			return parameters;
		}*/
	
			String province = null;
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
	            if("searchParam".equals(key)){
	            	parameters.put("searchParam", value);
	            }
	           
	        }
	        parameters.put("pcId", province!=null?province:null);
	        parameters.put("placeName", "重庆市");
	        List<ProvinceCitiesEntity> provinceList=provinceCitiesService.findAll(province);
	        if(provinceCitiesService.selectPccodeById(parameters)>0){
	        	parameters.put("isChongQing", "yes");
	        	if(provinceList.size()==1){
	        		parameters.put("districtType","city");
	        	}else if(("0").equals(provinceList.get(0).getPcpid())){
	        		parameters.put("districtType","province");
				}
	        	
			}else{
				parameters.put("isChongQing", "no");
				if(provinceList.size()==1){
					parameters.put("districtType","area");
				}else{
					if(("0").equals(provinceList.get(0).getPcpid())){
						parameters.put("districtType","province");
					}else {
						parameters.put("districtType","city");
					}
				}
			}
	        Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
	        List<EnterpriseInfoEntity> enterprisestatistics = enterprisestatisticsService.findcheckrecord(parameters);
//			model.addAttribute("details",searchDetails);
	        if(enterprisestatistics.size()!=0){
	        	for (int i = 0; i < enterprisestatistics.size(); i++) {
	    			enterprisestatistics.get(i).setIndexNo(i+1);
	    		}
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
	
}
