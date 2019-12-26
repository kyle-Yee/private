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
import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.service.GuaranteeStyleService;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.GuaranteeStyleEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.ProductstatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 谢翠
 * 监管机构用户统计-按产品查询
 *
 * @author 16420
 */
@Controller
@Scope("prototype")
@RequestMapping("/productstatistics/")
public class ProductstatisticsController {
    @Autowired
    public ProductstatisticsService productstatisticsService;
    public GuaranteeStyleService guaranteeStyleService;
    @Autowired
    private OrganizationsService organizationsService; //组织表
    @Autowired
    private RegionsService regionsService;
    @Autowired
    ProvinceCitiesService provinceCitiesService;

    /**
     * 返回按产品查询页面
     *
     * @return
     */
    @RequestMapping("formUI.html")
    public Object formUI(HttpServletRequest request) {
        try {
//			String province = request.getParameter("province");
//			String city = request.getParameter("city");
//			String area = request.getParameter("area");
//			List<DataCountEntity> userstatistics = pandectstatisticsService.userstatistics(parameter);
//			model.addAttribute("province", province);
//			model.addAttribute("city", city);
//			model.addAttribute("area", "1111");
            return Common.BACKGROUND_PATH + "/regulator/productstatistics/formUI";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping(value = "list.html")
    @ResponseBody
    public Object list(HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
        String regionId = sessionUser.getRegionid();
        if (regionId != null) {
            RegionsEntity rs = regionsService.findById(regionId);
            String pcId = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("pcId", pcId);
            parameters.put("starttime", request.getParameter("starttime"));
            parameters.put("endtime", request.getParameter("endtime"));
            List<BanklistInfoEntity> productstatistics = productstatisticsService.productstatistics(parameters);
            for (int i = 0; i < productstatistics.size(); i++) {
                String dbfs = productstatistics.get(i).getDbfs();
                String[] split = dbfs.split("#");
                String gs = "";
                String gsname = "";
                for (int j = 0; j < split.length; j++) {
                    String gsId = String.valueOf(split[j]);
                    List<GuaranteeStyleEntity> findgsById = productstatisticsService.findgsById(gsId);
                    for (int k = 0; k < findgsById.size(); k++) {
                        gsname = findgsById.get(k).getGsname();
                    }
                    gs += " " + gsname + " ,";
                }
                gs = gs.substring(0, gs.length() - 1);
                productstatistics.get(i).setDbfs(gs);
            }

            map.put("productstatistics", productstatistics);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "list_new.html")
    @ResponseBody
    public Object listNew(HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
        String regionId = sessionUser.getRegionid();
        if (regionId != null) {
            RegionsEntity rs = regionsService.findById(regionId);
            String pcId = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("pcId", pcId);
            parameters.put("starttime", request.getParameter("starttime"));
            parameters.put("endtime", request.getParameter("endtime"));

            List<Map<String, Object>> productstatistics = productstatisticsService.productstatistics_new(parameters);

            map.put("productstatistics", productstatistics);
        }
        return map;
    }
}
