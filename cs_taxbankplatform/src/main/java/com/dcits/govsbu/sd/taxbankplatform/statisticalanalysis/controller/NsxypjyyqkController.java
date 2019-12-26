package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.NsxypjyyqkEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.ActualissuanceLoansService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.DataRefreshService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.NsxyqjyyqkService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.ReportingService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 
     * Title: ReportingController.java    
     * Description: 局端统计分析——银税互动平台统计分析报表   3.4.3.1 纳税信用评价结果运用情况
     * @author liuc     
     * @created 2017-3-3 上午10:28:44
 */
@Controller
@Scope("prototype")
@RequestMapping("/nsxypjyy/")
public class NsxypjyyqkController {
	@Autowired
	public ReportingService reportingService;
	
	@Autowired
	private RegionsService regionsService;
	
	@Autowired
	public ActualissuanceLoansService actualissuanceLoansService;
	
	@Autowired
	public DataRefreshService dataRefreshService;
	
	//区域码表
	@Autowired
    private ProvinceCitiesService provinceCitiesService; 
	@Autowired
	private NsxyqjyyqkService  nsxyqjyyqkService;
	
	/**
	 * 跳转到纳税信用评价结果运用情况界面
	 * @param model
	 * @param gridPager
	 * @return
	 */
	@RequestMapping(value = "toNsxyjgpj.html")
	public Object toNsxyjgpj(Model model,String gridPager){
		List<Map<String,String>> hyList = nsxyqjyyqkService.findHy();
		model.addAttribute("hyList", hyList);
		return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/toNsxyjgpj";
	}
	/**
	 * 获取纳税信用评价结果运用情况界面 统计数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("toNsxyjgpjlist.html")
	public String totoNsxyjgpjlist(HttpServletRequest request)  {
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");

		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		String regionId = sessionUser.getRegionid();						//用户登录之后  ，获取区域id
	
         
		/****************************统计功能优化**************************************/
		/*modify by xiecui 2018/04/04 begin*/
		// 判断是否包含自定义参数
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		ProvinceCitiesEntity provinceCitiesEntity= provinceCitiesService.findPcID(regionId);
//		String province = "0";
//		if(null != provinceCitiesEntity){
//			String pcId=provinceCitiesEntity.getId();
//			province = pcId;
//		} 
//		long city = 0;
//		long area = 0;
//		if (request.getParameter("province") != null){
//			province = request.getParameter("province");
//		}
//		if (request.getParameter("city") != null){
//			city = Long.parseLong(request.getParameter("city"));
//		}
//		if (request.getParameter("area") != null){
//			area = Long.parseLong(request.getParameter("area"));
//		}	
//		if(area != 0) {
//			parameters.put("currentarea", "area");
//			parameters.put("pcId", area);
//		}else if(area == 0 && city !=0) {
//			parameters.put("currentarea", "city");
//			parameters.put("pcId", city);
//		}else {
//			parameters.put("currentarea", "province");
//			parameters.put("pcId", province);
//		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		String province = null;
		String city = null;
		String area = null;
		if (regionId != null){
			RegionsEntity rs = regionsService.findById(regionId);
			if(rs.getRegioncode()!=null&&!"".equals(rs.getRegioncode()))
				province = provinceCitiesService.findByPccode(rs.getRegioncode()).getId();
		}
		if (request.getParameter("province") != null){
			province = request.getParameter("province");
		}
		if (request.getParameter("city") != null){
			city = request.getParameter("city");
		}
		if (request.getParameter("area") != null){
			area = request.getParameter("area");
		}	
		if(area != null&&!"0".equals(area)) {
			parameters.put("pcId", area);
		}else if("0".equals(area) && city !=null&&!"0".equals(city)) {
			parameters.put("pcId", city);
		}else {
			parameters.put("pcId", province);
		}
		parameters.put("province", province!=null?province:null);
        parameters.put("city", city!=null&!("0").equals(city)?city:null);
        parameters.put("area", area!=null&!("0").equals(area)?area:null);
        parameters.put("placeName", "重庆市");
        if(provinceCitiesService.selectPccodeById(parameters)>0){
        	parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
        String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");

	 
	    if(starttime!=""&&endtime!=""){
	    	 parameters.put("start",starttime);
	 	    parameters.put("end",endtime);
	    }
	    try{
	    NsxypjyyqkEntity  nsxypjyyqkEntity = new NsxypjyyqkEntity();
		parameters.put("orgId",sessionUser.getOrgid());
		String hymd = request.getParameter("hy");
		hymd = hymd ==null  || "".equals(hymd) ? "all":hymd;
		if(!"qt".equals(hymd) && !"all".equals(hymd)){
		List<String> xlhydmxx = nsxyqjyyqkService.findXlHyDm(hymd);
		List<String> zlhydmxx = nsxyqjyyqkService.findZlHyDm(hymd);
		hymd ="";
		if(null != xlhydmxx && xlhydmxx.size() > 0){
			for(String xlhydm :xlhydmxx){
				hymd += "'"+xlhydm+"',";
			}
		}
		if(null != zlhydmxx && zlhydmxx.size() > 0){
			for(String zlhydm :zlhydmxx){
				hymd += "'"+zlhydm+"',";
			}
		}
		hymd = hymd.substring(0, hymd.length()-1);
		}
		parameters.put("hyDm",hymd);
		List<Map<String, String>> sjtsyhsList =   nsxyqjyyqkService.sjtsyhs(parameters);
		List<Map<String, String>> qzysxhsList =  nsxyqjyyqkService.qzysxhs(parameters);
		if(null != sjtsyhsList && sjtsyhsList.size() > 0){
			int qt = 0;
			for(Map<String, String> sjtsyhs : sjtsyhsList){
				String sjtsyhss = String.valueOf(sjtsyhs.get("swjgtsyh"));
				sjtsyhss = !"".equals(sjtsyhss) && sjtsyhss != null && !"null".equals(sjtsyhss)?sjtsyhss:"0";
				if("A".equals(sjtsyhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setSwjgtsyhA(sjtsyhss);
				}else if("B".equals(sjtsyhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setSwjgtsyhB(sjtsyhss);
				}else if("C".equals(sjtsyhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setSwjgtsyhC(sjtsyhss);
				}else if("D".equals(sjtsyhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setSwjgtsyhD(sjtsyhss);
				}else{
					qt = qt + Integer.parseInt(sjtsyhss);
				}
				nsxypjyyqkEntity.setSwjgtsyhW(String.valueOf(qt));
			}
		} 
		
		if(null != qzysxhsList && qzysxhsList.size() > 0){
			int qt = 0;
			for(Map<String, String> qzysxhs : qzysxhsList){
				String qzsxhss = String.valueOf(qzysxhs.get("qzsxhs"));
				qzsxhss = !"".equals(qzsxhss) && qzsxhss != null && !"null".equals(qzsxhss)?qzsxhss:"0";
				if("A".equals(qzysxhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setQzsxhsA(qzsxhss);
					nsxypjyyqkEntity.setQztzxhsA(qzsxhss);
				}else if("B".equals(qzysxhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setQzsxhsB(qzsxhss);
					nsxypjyyqkEntity.setQztzxhsB(qzsxhss);
				}else if("C".equals(qzysxhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setQzsxhsC(qzsxhss);
					nsxypjyyqkEntity.setQztzxhsC(qzsxhss);
				}else if("D".equals(qzysxhs.get("nsryhxx_xydj"))){
					nsxypjyyqkEntity.setQzsxhsD(qzsxhss);
					nsxypjyyqkEntity.setQztzxhsD(qzsxhss);
				}else{
					qt = qt + Integer.parseInt(qzsxhss);
				}
			}
			nsxypjyyqkEntity.setQzsxhsW(String.valueOf(qt));
			nsxypjyyqkEntity.setQztzxhsW(String.valueOf(qt));
		} 
		
		    int qzsxhsHj = Integer.parseInt(nsxypjyyqkEntity.getQzsxhsA()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getQzsxhsB())+
		    		Integer.parseInt(nsxypjyyqkEntity.getQzsxhsC())+
		    		Integer.parseInt(nsxypjyyqkEntity.getQzsxhsD())+
		    		Integer.parseInt(nsxypjyyqkEntity.getQzsxhsW());
		    int qztzxhHj = Integer.parseInt(nsxypjyyqkEntity.getQztzxhsA()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getQztzxhsB()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getQztzxhsC()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getQztzxhsD()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getQztzxhsW()) ;
		    
		    int sjtsyhsH = Integer.parseInt(nsxypjyyqkEntity.getSwjgtsyhA()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getSwjgtsyhB()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getSwjgtsyhC()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getSwjgtsyhD()) +
		    		Integer.parseInt(nsxypjyyqkEntity.getSwjgtsyhW()) ;
		    if(qzsxhsHj != 0){
		    double qyhtzl = (qztzxhHj/qzsxhsHj)*100;//新客户拓展率（%）
		      nsxypjyyqkEntity.setQyhtzl(String.valueOf(qyhtzl));
		     }else{
		    	 nsxypjyyqkEntity.setQyhtzl("0");
		    }
		    if(sjtsyhsH != 0){
		    Double nsxypjjgyyl = (Double.parseDouble(String.valueOf(qzsxhsHj))/Double.parseDouble(String.valueOf(sjtsyhsH)))*100;//纳税信用评价结果运用率（%）
		    DecimalFormat df = new DecimalFormat("#.00");  
		    	nsxypjyyqkEntity.setNsxypjjgyyl(df.format(nsxypjjgyyl));
		    }else{
		    	nsxypjyyqkEntity.setNsxypjjgyyl("0");	
		    }
		    nsxypjyyqkEntity.setSwjgtsyhH(String.valueOf(sjtsyhsH));
		    nsxypjyyqkEntity.setQzsxhsH(String.valueOf(qzsxhsHj));
		    nsxypjyyqkEntity.setQztzxhsH(String.valueOf(qztzxhHj));
		    
		   
		   
		    
		    nsxypjyyqkEntity.setJjjyrs("--");//数据库暂无数据
			parameters.put("exhibitDatas", nsxypjyyqkEntity);
			parameters.put("success", Boolean.TRUE);
			JSONObject json = new JSONObject();
			json.put("exhibitDatas", nsxypjyyqkEntity);
			json.put("success", Boolean.TRUE);
			return json.toString();
	    }catch(Exception e){
			parameters.put("success", Boolean.FALSE);
			e.printStackTrace();
	    }
	    return "";
	}
 
	public static void main(String[] args) {
		 String a = "1,3,45,64,";
		 a=a.substring(0, a.length()-1);
		 System.out.println(a);
	}
	
}
