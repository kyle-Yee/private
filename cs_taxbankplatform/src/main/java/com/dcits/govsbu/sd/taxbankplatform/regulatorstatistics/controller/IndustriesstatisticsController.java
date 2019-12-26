package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.IndustriesdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.IndustriesstatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 谢翠
 * 监管机构用户统计-按行业查询
 *
 * @author 16420
 */
@Controller
@Scope("prototype")
@RequestMapping("/industriesstatistics/")
public class IndustriesstatisticsController {
    @Autowired
    public IndustriesstatisticsService industriesstatisticsService;
    @Autowired
    public OrganizationsService organizationsService;
    @Autowired
    private RegionsService regionsService;
    @Autowired
    ProvinceCitiesService provinceCitiesService;

    /**
     * 返回按行业查询页面
     *
     * @return
     */
    @RequestMapping("formUI.html")
    public Object formUI(HttpServletRequest request) {
        try {
            UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
            String regionId = sessionUser.getRegionid();
            if (regionId != null) {
                RegionsEntity rs = regionsService.findById(regionId);
                String pcId = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter.put("pcId", pcId);
               
//				for (int i = 0;i < searchIndustries.size();i++){
//					if ("Y".equals(searchIndustries.get(i).getMlbz())){
//						break;
//					}else {
//						String sjhydm1 = searchIndustries.get(i).getSjhydm();
//						IndustriesdetailsEntity hyxx1 = industriesstatisticsService.queryByHydm(sjhydm1);
//						if ("Y".equals(hyxx1.getMlbz())){
//							searchIndustries.remove(i);
//							searchIndustries.add(i, hyxx1);
//						}else {
//							String sjhydm2 = hyxx1.getSjhydm();
//							IndustriesdetailsEntity hyxx2 = industriesstatisticsService.queryByHydm(sjhydm2);
//							if ("Y".equals(hyxx2.getMlbz())){
//								searchIndustries.remove(i);
//								searchIndustries.add(i, hyxx2);
//							}else {
//								String sjhydm3 = hyxx2.getSjhydm();
//								IndustriesdetailsEntity hyxx3 = industriesstatisticsService.queryByHydm(sjhydm3);
//								searchIndustries.remove(i);
//								searchIndustries.add(i, hyxx3);
//							}
//						}	
//					}
//				}
                
            }
            return Common.BACKGROUND_PATH + "/regulator/industriesstatistics/formUI";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
    
    @RequestMapping(value = "listIndustries.html")
    @ResponseBody
    public Object listIndustries(HttpServletRequest request) throws IOException {
    	 List<IndustriesdetailsEntity> searchIndustries = industriesstatisticsService.searchIndustries();
         
         request.setAttribute("searchIndustries", searchIndustries);
         
    	return searchIndustries;
    }
    
    @RequestMapping(value = "list.html")
    @ResponseBody
    public Object list(HttpServletRequest request) throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
//		String province = request.getParameter("province");
//		String city = request.getParameter("city");
//		String area = request.getParameter("area");
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String hydm = request.getParameter("industries");
        IndustriesdetailsEntity queryByHydm = industriesstatisticsService.queryByHydm(hydm);
        String hymc = null;
        String sjhymc = null;
        if (!"".equals(hydm) && !"00".equals(hydm)) {
            hymc = queryByHydm.getHymc();
            IndustriesdetailsEntity queryByHydm2 = industriesstatisticsService.queryByHydm(queryByHydm.getSjhydm());
            sjhymc = queryByHydm2.getHymc();
        }
        if ("00".equals(hydm)) {
            hymc = null;
        }


        /************************省市区处理*******************************/
        String province = null;
        String city = null;
        String area = null;
        UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
        String regionId = sessionUser.getRegionid();
        if (regionId != null) {
            RegionsEntity rs = regionsService.findById(regionId);
            province = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
        }
        if (request.getParameter("province") != null) {
            province = request.getParameter("province");
        }
        if (request.getParameter("city") != null) {
            city = request.getParameter("city");
        }
        if (request.getParameter("area") != null) {
            area = request.getParameter("area");
        }

        parameters.put("placeName", "重庆市");
        if (area != null && !"0".equals(area)) {
            parameters.put("pcId", area);
        } else if ("0".equals(area) && city != null && !"0".equals(city)) {
            parameters.put("pcId", city);
        } else {
            parameters.put("pcId", province);
        }
//		if(area != null) {
//			parameters.put("currentarea", "area");
//			parameters.put("pcId", Long.parseLong(area));
//		}else if(area == null && city !=null) {
//			parameters.put("currentarea", "city");
//			parameters.put("pcId", Long.parseLong(city));
//		}else if(area == null && city ==null && province != null){
//			parameters.put("currentarea", "province");
//			parameters.put("pcId", Long.parseLong(province));
//		}else {
//			parameters.put("pcId", 0);
//		}
        parameters.put("starttime", starttime);
        parameters.put("endtime", endtime);
        parameters.put("hymc", hymc);
        parameters.put("hydm", hydm);
        parameters.put("sjhymc", sjhymc);
        parameters.put("province", province != null && !"0".equals(province) ? province : null);
        parameters.put("city", city != null && !"0".equals(city) ? city : null);
        parameters.put("area", area != null && !"0".equals(area) ? area : null);
        if (provinceCitiesService.selectPccodeById(parameters) > 0) {
            parameters.put("isChongQing", "yes");
        } else {
            parameters.put("isChongQing", "no");
        }
        List<IndustriesdetailsEntity> industriesstatistics = industriesstatisticsService.Industriesstatistics(parameters);
        request.setAttribute("industriesstatistics", industriesstatistics);
        List<IndustriesdetailsEntity> industriesstatisticsByMl = industriesstatisticsService.IndustriesstatisticsByMl(parameters);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("industriesstatistics", industriesstatistics);
        map.put("industriesstatisticsByMl", industriesstatisticsByMl);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "list_new.html")
    @ResponseBody
    public Object listNew(HttpServletRequest request) throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String hydm = request.getParameter("industries");
        String hydmml = request.getParameter("mlindustries");
        String hydmdl = request.getParameter("dlindustries");
        String hydmzl = request.getParameter("zlindustries");
        IndustriesdetailsEntity queryByHydm = industriesstatisticsService.queryByHydm(hydm);
        String hymc = null;
        String sjhymc = null;
        if (!"".equals(hydm) && !"00".equals(hydm)&&hydm!=null) {
            hymc = queryByHydm.getHymc();
            IndustriesdetailsEntity queryByHydm2 = industriesstatisticsService.queryByHydm(queryByHydm.getSjhydm());
            sjhymc = queryByHydm2.getHymc();
        }
        if ("00".equals(hydm)) {
            hymc = null;
        }

        String province = request.getParameter("province");
        if (StringUtils.isBlank(province)) {
            UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
            String regionId = sessionUser.getRegionid();
            if (regionId != null) {
                RegionsEntity rs = regionsService.findById(regionId);
                province = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
            }
        }
        String city = request.getParameter("city");
        String area = request.getParameter("area");

        if (StringUtils.isNotBlank(area) && !"0".equals(area)) {
            parameters.put("pcId", StringUtils.replacePattern(area, "0{0,4}$", ""));
        } else if (("0".equals(area) || StringUtils.isBlank(area)) && (StringUtils.isNotBlank(city) && !"0".equals(city))) {
            parameters.put("pcId", StringUtils.replacePattern(city, "0{0,4}$", ""));
        } else {
            parameters.put("pcId", StringUtils.replacePattern(province, "0{0,4}$", ""));
        }

        parameters.put("starttime", starttime);
        parameters.put("endtime", endtime);
        parameters.put("hymc", hymc);
        parameters.put("hydm", hydm);
        parameters.put("sjhymc", sjhymc);
        parameters.put("hydmml", hydmml);
        parameters.put("hydmdl", hydmdl);
        parameters.put("hydmzl", hydmzl);
        List<Map<String, Object>> industriesstatistics = industriesstatisticsService.industriesstatistics(parameters);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("industriesstatistics", industriesstatistics);

        return map;
    }
}
