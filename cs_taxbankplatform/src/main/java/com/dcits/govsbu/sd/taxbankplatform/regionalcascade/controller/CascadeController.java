package com.dcits.govsbu.sd.taxbankplatform.regionalcascade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.sun.xml.bind.v2.model.core.RegistryInfo;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.model.CascadeEntity;
import com.dcits.govsbu.sd.taxbankplatform.regionalcascade.service.CascadeService;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;


@Controller
@Scope("prototype")
@RequestMapping("/cascade/")
public class CascadeController extends BaseController {
    @Autowired
    public CascadeService cascadeService;
    @Autowired
    private RegionsService regionsService; //区域表
    @Autowired
    private ProvinceCitiesService provinceCitiesService; //省市区


    @RequestMapping("findProvince.html")
    @ResponseBody
    public Object findProvince(HttpServletRequest request) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
        String userId = sessionUser.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        List<CascadeEntity> userArea = cascadeService.queryUserArea(userId);
        JSONArray province = new JSONArray();
        JSONArray city = new JSONArray();
        JSONArray area = new JSONArray();
        JSONObject prdetails = new JSONObject();
        JSONObject ctdetails = new JSONObject();
        JSONObject ardetails = new JSONObject();
        for (CascadeEntity ua : userArea) {
            if (Integer.valueOf(ua.getFfpcId()) == 0) {
                if (Integer.valueOf(ua.getFpcId()) == 0) {
                    prdetails.put("id", ua.getPcId());
                    prdetails.put("topid", ua.getPcpId());
                    prdetails.put("name", ua.getName());
//					prdetails.put("pccode", ua.getPcCode());
                    province.add(prdetails);
                    String pcId = ua.getPcId();
                    city = cascadeService.querycityListAll(pcId);
                    area = cascadeService.queryareaBycity(pcId);
                } else {
                    prdetails.put("id", ua.getFpcId());
                    prdetails.put("topid", ua.getFpcpId());
                    prdetails.put("name", ua.getFname());
//					prdetails.put("pccode", ua.getFpcCode());
                    province.add(prdetails);
                    ctdetails.put("id", ua.getPcId());
                    ctdetails.put("topid", ua.getPcpId());
                    ctdetails.put("name", ua.getName());
//					ctdetails.put("pccode", ua.getPcCode());
                    city.add(ctdetails);
                    String pcId = ua.getPcId();
                    area = cascadeService.queryareaListAll(pcId);
                }
            } else {
                prdetails.put("id", ua.getFfpcId());
                prdetails.put("topid", ua.getFfpcpId());
                prdetails.put("name", ua.getFfname());
//				prdetails.put("pccode", ua.getFfpcCode());
                province.add(prdetails);
                ctdetails.put("id", ua.getFpcId());
                ctdetails.put("topid", ua.getFpcpId());
                ctdetails.put("name", ua.getFname());
//				ctdetails.put("pccode", ua.getFpcCode());
                city.add(ctdetails);
                ardetails.put("id", ua.getPcId());
                ardetails.put("topid", ua.getPcpId());
                ardetails.put("name", ua.getName());
//				ardetails.put("pccode", ua.getPcCode());
                area.add(ardetails);

            }
        }
        map.put("success", Boolean.TRUE);
        map.put("province", province);
        map.put("city", city);
        map.put("area", area);
        return map;
    }

    @RequestMapping("findProvince_new.html")
    @ResponseBody
    public Object findProvinceNew(HttpServletRequest request) {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
        String regionid = sessionUser.getRegionid();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParamMap.put("region_id", regionid);
        List<RegionsEntity> regionsEntities = regionsService.queryListAll(queryParamMap);
        String regioncode = regionsEntities.get(0).getRegioncode();

        //返回值map对象，通过@ResponseBody注解解析为json
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //省、市/州、区/县 全部数据
        List<ProvinceCitiesEntity> provinceCitiesEntities = provinceCitiesService.queryListAll(null);

        //省、市/州、区/县 级联数据
        List<Map<String, Object>> provinces = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> citys = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> areas = new ArrayList<Map<String, Object>>();

        //省行政区划前缀
        String provincePrefix = regioncode.substring(0, 2);
        //省级行政区划代码
        String provinceCode = provincePrefix + "0000";

        String pcCode = null;
        //当前登陆人区域代码级别
        if (regioncode.endsWith("0000")) {
            //当前登陆人是 省级
            for (ProvinceCitiesEntity provincecitie : provinceCitiesEntities) {
                pcCode = provincecitie.getPccode();
                if (StringUtils.isBlank(pcCode)) {
                    continue;
                }
                if (provinceCode.equals(pcCode)) {
                    provinces.add(convertToCascadeVo(provincecitie));
                }
                //以00结尾，且不是省级行政区划的，为 市/州 级数据
                if (pcCode.endsWith("00") && !pcCode.endsWith("0000")) {
                    citys.add(convertToCascadeVo(provincecitie));
                }
                //不以0结尾，视为 区/县 级数据
                if (!pcCode.endsWith("0")) {
                    areas.add(convertToCascadeVo(provincecitie));
                }
            }
        } else if (regioncode.endsWith("00")) {
            //当前登陆人是 市/州 级
            for (ProvinceCitiesEntity provincecitie : provinceCitiesEntities) {
                pcCode = provincecitie.getPccode();
                if (StringUtils.isBlank(pcCode)) {
                    continue;
                }
                if (provinceCode.equals(pcCode)) {
                    provinces.add(convertToCascadeVo(provincecitie));
                }
                if (regioncode.equals(pcCode)) {
                    citys.add(convertToCascadeVo(provincecitie));
                }
                if (pcCode.startsWith(regioncode.substring(0, 4)) && !pcCode.endsWith("0")) {
                    areas.add(convertToCascadeVo(provincecitie));
                }
            }
        } else {
            //当前登陆人是 区/县 级
            for (ProvinceCitiesEntity provincecitie : provinceCitiesEntities) {
                pcCode = provincecitie.getPccode();
                if (StringUtils.isBlank(pcCode)) {
                    continue;
                }
                if (provinceCode.equals(pcCode)) {
                    provinces.add(convertToCascadeVo(provincecitie));
                }
                if ((regioncode.substring(0, 4) + "00").equals(pcCode)) {
                    citys.add(convertToCascadeVo(provincecitie));
                }
                if (regioncode.equals(pcCode)) {
                    areas.add(convertToCascadeVo(provincecitie));
                }
            }
        }

        resultMap.put("province", provinces);
        resultMap.put("city", citys);
        resultMap.put("area", areas);

        return resultMap;
    }

    private Map<String, Object> convertToCascadeVo(ProvinceCitiesEntity provincecitie) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", provincecitie.getId());
        map.put("topid", provincecitie.getPcpid());
        map.put("name", provincecitie.getPcname());

        return map;
    }
}
