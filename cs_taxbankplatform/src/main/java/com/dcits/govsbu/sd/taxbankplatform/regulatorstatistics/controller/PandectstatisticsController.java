package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.BanklistInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.PandectstatisticsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 谢翠
 * 监管机构用户统计-总览
 *
 * @author 16420
 */
@Controller
@Scope("prototype")
@RequestMapping("/pandectstatistics/")
public class PandectstatisticsController {
    @Autowired
    public PandectstatisticsService pandectstatisticsService;
    /**
     * 返回总览页面
     *
     * @return
     */
    @Autowired
    private RegionsService regionsService;
    @Autowired
    private ProvinceCitiesService provinceCitiesService;

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
            return Common.BACKGROUND_PATH + "/regulator/pandectstatistics/formUI";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * @throws Exception
     */
    @RequestMapping(value = "list.html")
    @ResponseBody
    public Object list(HttpServletRequest request) throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String province = null;
        String city = null;
        String area = null;
        UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
        String regionId = sessionUser.getRegionid();
        if (regionId != null) {
            RegionsEntity rs = regionsService.findById(regionId);
            if (rs.getRegioncode() != null && !"".equals(rs.getRegioncode()))
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
        if (area != null && !"0".equals(area)) {
            parameters.put("pcId", area);
        } else if ("0".equals(area) && city != null && !"0".equals(city)) {
            parameters.put("pcId", city);
        } else {
            parameters.put("pcId", province);
        }
        parameters.put("province", province != null ? province : null);
        parameters.put("city", city != null & !("0").equals(city) ? city : null);
        parameters.put("area", area != null & !("0").equals(area) ? area : null);
        parameters.put("placeName", "重庆市");
        if (provinceCitiesService.selectPccodeById(parameters) > 0) {
            parameters.put("isChongQing", "yes");
        } else {
            parameters.put("isChongQing", "no");
        }


        /****************************统计功能优化**************************************/
        /*modify by xiecui 2018/04/04 begin*/
        
		/*List<DataCountEntity> userstatistics = pandectstatisticsService.userstatistics(parameters);
		List<Long> count1 = new ArrayList<Long>();
		List<Long> count2=new ArrayList<Long>();
		List<Long> count3=new ArrayList<Long>();
		for(DataCountEntity data:userstatistics){
			if("bqhzyh".equals(data.getDataName()) || "yshdjrcp".equals(data.getDataName())){
				count1.add(data.getDataCount());
			}else if("zcyh".equals(data.getDataName()) ||  "rzyh".equals(data.getDataName()) ||
					"sqdkyh".equals(data.getDataName())  || "sxyh".equals(data.getDataName())){
				count2.add(data.getDataCount());
			}else{
				count3.add(data.getDataCount());
			}
		}
		List<PandectEntity> monthstatistics = pandectstatisticsService.monthstatistics(parameters);
		List<BanklistInfoEntity> banklistinfo = pandectstatisticsService.banklistinfo(parameters);
		List<String> xMonth = new ArrayList<String>();
		Calendar c = Calendar.getInstance();  
	    String  endtime=new SimpleDateFormat("yyyy-MM").format(c.getTime()).toString();  
	    xMonth.add(endtime);
	    for (int i = 0; i < 11; i++){
	    	c.add(Calendar.MONTH, -1);
	    	xMonth.add(new SimpleDateFormat("yyyy-MM").format(c.getTime()).toString());
	    }
	    Collections.reverse(xMonth);
	    List<PandectEntity> zcyh1 = new ArrayList<PandectEntity>();
	    List<PandectEntity> zcyh = new ArrayList<PandectEntity>();
	    List<PandectEntity> rzyh1 = new ArrayList<PandectEntity>();
	    List<PandectEntity> rzyh = new ArrayList<PandectEntity>();
	    List<PandectEntity> sqdkbs1 = new ArrayList<PandectEntity>();
	    List<PandectEntity> sqdkbs = new ArrayList<PandectEntity>();
	    List<PandectEntity> sxbs1 = new ArrayList<PandectEntity>();
	    List<PandectEntity> sxbs = new ArrayList<PandectEntity>();
	    List<PandectEntity> sdze1 = new ArrayList<PandectEntity>();
	    List<PandectEntity> sdze = new ArrayList<PandectEntity>();
	    List<PandectEntity> sxze1 = new ArrayList<PandectEntity>();
	    List<PandectEntity> sxze = new ArrayList<PandectEntity>();
		for (int i = 0; i < monthstatistics.size(); i++){
	    	PandectEntity pe = monthstatistics.get(i);
	    	if ("zcyh".equals(pe.getCountName())){
	    		zcyh1.add(pe);	
	    	}
	    	else if ("rzyh".equals(pe.getCountName())){
	    		rzyh1.add(pe);	
	    	}
	    	else if ("sqdkbs".equals(pe.getCountName())){
	    		sqdkbs1.add(pe);	
	    	}
	    	else if ("sxbs".equals(pe.getCountName())){
	    		sxbs1.add(pe);	
	    	}
	    	else if ("sdze".equals(pe.getCountName())){
	    		sdze1.add(pe);	
	    	}
	    	else if ("sxze".equals(pe.getCountName())){
	    		sxze1.add(pe);	
	    	}
	    }
    	for (int i = 0; i < xMonth.size(); i++){
    		for (int j = 0; j < zcyh1.size(); j++){
    			if (xMonth.get(i).equals(zcyh1.get(j).getMonth())){
    				if (zcyh.contains(zcyh1.get(j))){
    					zcyh.remove(i);
    					zcyh.add(zcyh1.get(j));
    				}else {
    					zcyh.add(zcyh1.get(j));
    				}
    				break;
    			}else {
    				PandectEntity pe1 = new PandectEntity();
    				pe1.setCountName("zcyh");
    				pe1.setMonth(xMonth.get(i));
    				pe1.setCount(0l);
    				if(zcyh.contains(pe1)){
    					zcyh.remove(i);
    					zcyh.add(pe1);
    				}else {
    					zcyh.add(pe1);
    				}			
    			}
    		}
    		for (int j = 0; j < rzyh1.size(); j++){
    			if (xMonth.get(i).equals(rzyh1.get(j).getMonth())){
    				if (rzyh.contains(rzyh1.get(j))){
    					rzyh.remove(i);
    					rzyh.add(rzyh1.get(j));
    				}else {
    					rzyh.add(rzyh1.get(j));
    				}
    				break;
    			}else {
    				PandectEntity pe1 = new PandectEntity();
    				pe1.setCountName("rzyh");
    				pe1.setMonth(xMonth.get(i));
    				pe1.setCount(0l);
    				if(rzyh.contains(pe1)){
    					rzyh.remove(i);
    					rzyh.add(pe1);
    				}else {
    					rzyh.add(pe1);
    				}			
    			}
    		}
    		for (int j = 0; j < sqdkbs1.size(); j++){
    			if (xMonth.get(i).equals(sqdkbs1.get(j).getMonth())){
    				if (sqdkbs.contains(sqdkbs1.get(j))){
    					sqdkbs.remove(i);
    					sqdkbs.add(sqdkbs1.get(j));
    				}else {
    					sqdkbs.add(sqdkbs1.get(j));
    				}
    				break;
    			}else {
    				PandectEntity pe1 = new PandectEntity();
    				pe1.setCountName("sqdkbs");
    				pe1.setMonth(xMonth.get(i));
    				pe1.setCount(0l);
    				if(sqdkbs.contains(pe1)){
    					sqdkbs.remove(i);
    					sqdkbs.add(pe1);
    				}else {
    					sqdkbs.add(pe1);
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
    				pe1.setCountName("sxbs");
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
    		for (int j = 0; j < sdze1.size(); j++){
				if (xMonth.get(i).equals(sdze1.get(j).getMonth())){
					if (sdze.contains(sdze1.get(j))){
						sdze.remove(i);
						sdze.add(sdze1.get(j));
					}else {
						sdze.add(sdze1.get(j));
					}
					break;
				}else {
					PandectEntity pe1 = new PandectEntity();
					pe1.setCountName("sdze");
					pe1.setMonth(xMonth.get(i));
					pe1.setCount(0l);
					if(sdze.contains(pe1)){
						sdze.remove(i);
						sdze.add(pe1);
					}else {
						sdze.add(pe1);
					}			
				}
			}
    		for (int j = 0; j < sxze1.size(); j++){
    			if (xMonth.get(i).equals(sxze1.get(j).getMonth())){
    				if (sxze.contains(sxze1.get(j))){
    					sxze.remove(i);
    					sxze.add(sxze1.get(j));
    				}else {
    					sxze.add(sxze1.get(j));
    				}
    				break;
    			}else {
    				PandectEntity pe1 = new PandectEntity();
    				pe1.setCountName("sxze");
    				pe1.setMonth(xMonth.get(i));
    				pe1.setCount(0l);
    				if(sxze.contains(pe1)){
    					sxze.remove(i);
    					sxze.add(pe1);
    				}else {
    					sxze.add(pe1);
    				}			
    			}
    		}		
    	}*/

        Map<String, Object> userstatistics = pandectstatisticsService.userstatistics(parameters);
        Map<String, Object> loanstatistics = pandectstatisticsService.loanstatistics(parameters);
        Map<String, Object> bankstatistics = pandectstatisticsService.bankstatistics(parameters);
        List<Long> count1 = new ArrayList<Long>();
        List<Long> count2 = new ArrayList<Long>();
        List<Long> count3 = new ArrayList<Long>();
        if (bankstatistics != null) {
            count1.add(Long.parseLong(bankstatistics.get("bqhzyh") == null ? "0" : String.valueOf(bankstatistics.get("bqhzyh"))));
            count1.add(Long.parseLong(bankstatistics.get("yshdjrcp") == null ? "0" : String.valueOf(bankstatistics.get("yshdjrcp"))));
        }

        if (userstatistics != null) {
            count2.add(Long.parseLong(userstatistics.get("zcyh") == null ? "0" : String.valueOf(userstatistics.get("zcyh"))));
            count2.add(Long.parseLong(userstatistics.get("rzyh") == null ? "0" : String.valueOf(userstatistics.get("rzyh"))));
        }

        if (loanstatistics != null) {
            count2.add(Long.parseLong(loanstatistics.get("sqyh") == null ? "0" : String.valueOf(loanstatistics.get("sqyh"))));
            count2.add(Long.parseLong(loanstatistics.get("sxyh") == null ? "0" : String.valueOf(loanstatistics.get("sxyh"))));
            count3.add(Long.parseLong(loanstatistics.get("sqdkbs") == null ? "0" : String.valueOf(loanstatistics.get("sqdkbs"))));
            count3.add(Long.parseLong(loanstatistics.get("sdze") == null ? "0" : String.valueOf(loanstatistics.get("sdze"))));
            count3.add(Long.parseLong(loanstatistics.get("sxbs") == null ? "0" : String.valueOf(loanstatistics.get("sxbs"))));
            count3.add(Long.parseLong(loanstatistics.get("sxze") == null ? "0" : String.valueOf(loanstatistics.get("sxze"))));
        }

        List<Map<String, Object>> usermonthstatistics = pandectstatisticsService.usermonthstatistics(parameters);
        List<Map<String, Object>> loanmonthstatistics = pandectstatisticsService.loanmonthstatistics(parameters);
        List<BanklistInfoEntity> banklistinfo = pandectstatisticsService.banklistinfo(parameters);
        List<String> xMonth = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        String endtime = new SimpleDateFormat("yyyy-MM").format(c.getTime()).toString();
        xMonth.add(endtime);
        for (int i = 0; i < 11; i++) {
            c.add(Calendar.MONTH, -1);
            xMonth.add(new SimpleDateFormat("yyyy-MM").format(c.getTime()).toString());
        }
        Collections.reverse(xMonth);
        List<PandectEntity> zcyh1 = new ArrayList<PandectEntity>();
        List<PandectEntity> zcyh = new ArrayList<PandectEntity>();
        List<PandectEntity> rzyh1 = new ArrayList<PandectEntity>();
        List<PandectEntity> rzyh = new ArrayList<PandectEntity>();
        List<PandectEntity> sqdkbs1 = new ArrayList<PandectEntity>();
        List<PandectEntity> sqdkbs = new ArrayList<PandectEntity>();
        List<PandectEntity> sxbs1 = new ArrayList<PandectEntity>();
        List<PandectEntity> sxbs = new ArrayList<PandectEntity>();
        List<PandectEntity> sdze1 = new ArrayList<PandectEntity>();
        List<PandectEntity> sdze = new ArrayList<PandectEntity>();
        List<PandectEntity> sxze1 = new ArrayList<PandectEntity>();
        List<PandectEntity> sxze = new ArrayList<PandectEntity>();
        if (usermonthstatistics != null && usermonthstatistics.size() > 0) {
            for (Map<String, Object> users : usermonthstatistics) {
                PandectEntity pe1 = new PandectEntity();
                pe1.setCountName("zcyh");
                pe1.setCount(Long.parseLong(users.get("zcyh") == null ? "0" : String.valueOf(users.get("zcyh"))));
                pe1.setMonth(String.valueOf(users.get("zcrq")));

                PandectEntity pe2 = new PandectEntity();
                pe2.setCountName("rzyh");
                pe2.setCount(Long.parseLong(users.get("rzyh") == null ? "0" : String.valueOf(users.get("rzyh"))));
                pe2.setMonth(String.valueOf(users.get("zcrq")));

                zcyh1.add(pe1);
                rzyh1.add(pe2);
            }
        }

        if (loanmonthstatistics != null && loanmonthstatistics.size() > 0) {
            for (Map<String, Object> loans : loanmonthstatistics) {
                PandectEntity pe1 = new PandectEntity();
                pe1.setCountName("sqdkbs");
                pe1.setCount(Long.parseLong(loans.get("sqdkbs") == null ? "0" : String.valueOf(loans.get("sqdkbs"))));
                pe1.setMonth(String.valueOf(loans.get("sdrq")));

                PandectEntity pe2 = new PandectEntity();
                pe2.setCountName("sxbs");
                pe2.setCount(Long.parseLong(loans.get("sxbs") == null ? "0" : String.valueOf(loans.get("sxbs"))));
                pe2.setMonth(String.valueOf(loans.get("sdrq")));

                PandectEntity pe3 = new PandectEntity();
                pe3.setCountName("sdze");
                pe3.setCount(Long.parseLong(loans.get("sdze") == null ? "0" : String.valueOf(loans.get("sdze"))));
                pe3.setMonth(String.valueOf(loans.get("sdrq")));

                PandectEntity pe4 = new PandectEntity();
                pe4.setCountName("sxze");
                pe4.setCount(Long.parseLong(loans.get("sxze") == null ? "0" : String.valueOf(loans.get("sxze"))));
                pe4.setMonth(String.valueOf(loans.get("sdrq")));

                sqdkbs1.add(pe1);
                sxbs1.add(pe2);
                sdze1.add(pe3);
                sxze1.add(pe4);
            }
        }

        for (int i = 0; i < xMonth.size(); i++) {
            for (int j = 0; j < zcyh1.size(); j++) {
                if (xMonth.get(i).equals(zcyh1.get(j).getMonth())) {
                    if (zcyh.contains(zcyh1.get(j))) {
                        zcyh.remove(i);
                        zcyh.add(zcyh1.get(j));
                    } else {
                        zcyh.add(zcyh1.get(j));
                    }
                    break;
                } else {
                    PandectEntity pe1 = new PandectEntity();
                    pe1.setCountName("zcyh");
                    pe1.setMonth(xMonth.get(i));
                    pe1.setCount(0l);
                    if (zcyh.contains(pe1)) {
                        zcyh.remove(i);
                        zcyh.add(pe1);
                    } else {
                        zcyh.add(pe1);
                    }
                }
            }
            for (int j = 0; j < rzyh1.size(); j++) {
                if (xMonth.get(i).equals(rzyh1.get(j).getMonth())) {
                    if (rzyh.contains(rzyh1.get(j))) {
                        rzyh.remove(i);
                        rzyh.add(rzyh1.get(j));
                    } else {
                        rzyh.add(rzyh1.get(j));
                    }
                    break;
                } else {
                    PandectEntity pe1 = new PandectEntity();
                    pe1.setCountName("rzyh");
                    pe1.setMonth(xMonth.get(i));
                    pe1.setCount(0l);
                    if (rzyh.contains(pe1)) {
                        rzyh.remove(i);
                        rzyh.add(pe1);
                    } else {
                        rzyh.add(pe1);
                    }
                }
            }
            for (int j = 0; j < sqdkbs1.size(); j++) {
                if (xMonth.get(i).equals(sqdkbs1.get(j).getMonth())) {
                    if (sqdkbs.contains(sqdkbs1.get(j))) {
                        sqdkbs.remove(i);
                        sqdkbs.add(sqdkbs1.get(j));
                    } else {
                        sqdkbs.add(sqdkbs1.get(j));
                    }
                    break;
                } else {
                    PandectEntity pe1 = new PandectEntity();
                    pe1.setCountName("sqdkbs");
                    pe1.setMonth(xMonth.get(i));
                    pe1.setCount(0l);
                    if (sqdkbs.contains(pe1)) {
                        sqdkbs.remove(i);
                        sqdkbs.add(pe1);
                    } else {
                        sqdkbs.add(pe1);
                    }
                }
            }
            for (int j = 0; j < sxbs1.size(); j++) {
                if (xMonth.get(i).equals(sxbs1.get(j).getMonth())) {
                    if (sxbs.contains(sxbs1.get(j))) {
                        sxbs.remove(i);
                        sxbs.add(sxbs1.get(j));
                    } else {
                        sxbs.add(sxbs1.get(j));
                    }
                    break;
                } else {
                    PandectEntity pe1 = new PandectEntity();
                    pe1.setCountName("sxbs");
                    pe1.setMonth(xMonth.get(i));
                    pe1.setCount(0l);
                    if (sxbs.contains(pe1)) {
                        sxbs.remove(i);
                        sxbs.add(pe1);
                    } else {
                        sxbs.add(pe1);
                    }
                }
            }
            for (int j = 0; j < sdze1.size(); j++) {
                if (xMonth.get(i).equals(sdze1.get(j).getMonth())) {
                    if (sdze.contains(sdze1.get(j))) {
                        sdze.remove(i);
                        sdze.add(sdze1.get(j));
                    } else {
                        sdze.add(sdze1.get(j));
                    }
                    break;
                } else {
                    PandectEntity pe1 = new PandectEntity();
                    pe1.setCountName("sdze");
                    pe1.setMonth(xMonth.get(i));
                    pe1.setCount(0l);
                    if (sdze.contains(pe1)) {
                        sdze.remove(i);
                        sdze.add(pe1);
                    } else {
                        sdze.add(pe1);
                    }
                }
            }
            for (int j = 0; j < sxze1.size(); j++) {
                if (xMonth.get(i).equals(sxze1.get(j).getMonth())) {
                    if (sxze.contains(sxze1.get(j))) {
                        sxze.remove(i);
                        sxze.add(sxze1.get(j));
                    } else {
                        sxze.add(sxze1.get(j));
                    }
                    break;
                } else {
                    PandectEntity pe1 = new PandectEntity();
                    pe1.setCountName("sxze");
                    pe1.setMonth(xMonth.get(i));
                    pe1.setCount(0l);
                    if (sxze.contains(pe1)) {
                        sxze.remove(i);
                        sxze.add(pe1);
                    } else {
                        sxze.add(pe1);
                    }
                }
            }
        }

        /*modify by xiecui 2018/04/04 end*/

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userstatistics", userstatistics);
        map.put("count1", count1);
        map.put("count2", count2);
        map.put("count3", count3);
        map.put("banklistinfo", banklistinfo);
        map.put("zcyh", zcyh);
        map.put("rzyh", rzyh);
        map.put("sqdkbs", sqdkbs);
        map.put("sxbs", sxbs);
        map.put("sdze", sdze);
        map.put("sxze", sxze);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "list_new.html")
    @ResponseBody
    public Object listNew(HttpServletRequest request) throws IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();

        String province = request.getParameter("province");
        if (StringUtils.isBlank(province)) {
            UserEntity sessionUser = (UserEntity) request.getSession().getAttribute("userSession");
            String regionId = sessionUser.getRegionid();
            if (regionId != null) {
                RegionsEntity rs = regionsService.findById(regionId);
                String regioncode = rs.getRegioncode();
                if (StringUtils.isNotBlank(regioncode)) {
                    province = provinceCitiesService.findByPccode(regioncode).getId();
                }
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

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statisticsinfo", pandectstatisticsService.statisticsinfo(parameters));
        map.put("loanamountstatisticsinfo", pandectstatisticsService.loanamountstatisticsinfo(parameters));
        map.put("loancountstatisticsinfo", pandectstatisticsService.loancountstatisticsinfo(parameters));
        map.put("bankproduckstatisticsinfo", pandectstatisticsService.bankproduckstatisticsinfo(parameters));

        return map;
    }
}
