package com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.ActualissuanceLoansEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.DataRefreshEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.model.ReportingEntity;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.ActualissuanceLoansService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.DataRefreshService;
import com.dcits.govsbu.sd.taxbankplatform.statisticalanalysis.service.ReportingService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;

/**
 * 
     * Title: ReportingController.java    
     * Description: 局端统计分析——银税互动平台统计分析报表
     * @author xiecui       
     * @created 2017-3-3 上午10:28:44
 */
@Controller
@Scope("prototype")
@RequestMapping("/reporting/")
public class ReportingController {
	@Autowired
	public ReportingService reportingService;
	
	@Autowired
	public ActualissuanceLoansService actualissuanceLoansService;
	@Autowired
	private RegionsService regionsService;
	@Autowired
	public DataRefreshService dataRefreshService;
	
	//区域码表
	@Autowired
    private ProvinceCitiesService provinceCitiesService; 
	/**
	 * 
	     * @discription 返回银税互动推进情况列表
	     * @author xiecui       
	     * @created 2017-3-3 上午10:30:34     
	     * @param request
	     * @return
	 */
	@RequestMapping("listUI.html")
	public Object formUI(Model model,HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/listUI";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception{
		Map<String, Object> pageParameters = null;
		// 映射Pager对象
		Map<String,Object> searchParameters=new HashMap<String,Object>();
		String gridPager = request.getParameter("gridPager");
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		pageParameters = pager.getParameters();
		//设置分页，page里面包含了分页信息
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
            }else if("starttime".equals(key)){
            	searchParameters.put("starttime", value);
            }else if("endtime".equals(key)){
            	searchParameters.put("endtime", value);
            }
        }
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
	    if(starttime!=""&&endtime!=""){
	    	searchParameters.put("starttime",starttime);
	    	searchParameters.put("endtime",endtime);
	    }
        searchParameters.put("province", province!= null&&!("0".equals(province)||"".equals(province))?province:null);
        searchParameters.put("city", city!=null&&!("0".equals(city)||"".equals(city))?city:null);
        searchParameters.put("area", area!=null&&!("0".equals(area)||"".equals(area))?area:null);
		//Page<Object> page = PageHelper.startPage(0,10000, "taxid DESC");
		List<ReportingEntity> list = reportingService.taxBankAnaDetails(searchParameters);
		List<ReportingEntity> rtnlist = calculate(list, pager);
		//calculate(list, pager);
		for (int i = 0; i < rtnlist.size(); i++) {
			rtnlist.get(i).setIndexNo(i+1);
		}
		pageParameters.clear();
		pageParameters.put("isSuccess", Boolean.TRUE);
		pageParameters.put("nowPage", pager.getNowPage());
		pageParameters.put("pageSize", pager.getPageSize());
		pageParameters.put("pageCount",list.size()%pager.getPageSize()==0?list.size()/pager.getPageSize():list.size()/pager.getPageSize()+1);
		pageParameters.put("recordCount", list.size()-1);
		//pageParameters.put("startRecord", page.getStartRow());
		//列表展示数据
		pageParameters.put("exhibitDatas", rtnlist);
		return pageParameters;
		
	}
	public List<ReportingEntity> calculate(List<ReportingEntity> list,Pager pager){
		if(list==null)return list;
		int listSize = list.size();
		int currentPage = pager.getNowPage();
		int pageSize  = pager.getPageSize();
		int pagerNum = pager.getPageCount();
		List<ReportingEntity> tempList = new ArrayList<ReportingEntity>();
		if(listSize>=pageSize*currentPage){
			tempList = list.subList((currentPage-1)*pageSize, currentPage*pageSize);
		}else if(listSize<pageSize*currentPage){
			if((currentPage-1)*pageSize<listSize){
				tempList = list.subList((currentPage-1)*pageSize, listSize);
			}else{
				tempList = list;
			}
		}
		int regUserTotal = 0;
		for(int i=0;i<listSize;i++){
			ReportingEntity tempRe = list.get(i);
			regUserTotal=regUserTotal+Integer.parseInt(tempRe.getZcyh());
		}
		int pageRegUserNum = 0;
		double pageRegUserRate=0;
		int pageUserAppNum=0;
		int pageAuthNum=0;
		double pageLoanAmount=0;
		for (int i=0;i<tempList.size();i++){
			ReportingEntity subRe =tempList.get(i);
			String rate ="0";
			if(regUserTotal!=0)
				rate = String.valueOf(Double.valueOf(subRe.getZcyh())/regUserTotal*100);
			pageRegUserNum = pageRegUserNum+Integer.parseInt(subRe.getZcyh());
			pageRegUserRate = pageRegUserRate+Double.valueOf(rate);
			pageUserAppNum =pageUserAppNum+Integer.parseInt(subRe.getRztgs());
			pageAuthNum = pageAuthNum+Integer.parseInt(subRe.getSxbs());
			pageLoanAmount = pageLoanAmount+Double.valueOf(subRe.getSxze());
			tempList.get(i).setSxze(new BigDecimal(Double.valueOf(tempList.get(i).getSxze())).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
			tempList.get(i).setZqyzsbl(new BigDecimal(rate).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
		}
		ReportingEntity lastCalRe = new ReportingEntity();
		lastCalRe.setSwjg("合计");
		lastCalRe.setZcyh(String.valueOf(pageRegUserNum));
		lastCalRe.setZqyzsbl(String.valueOf(new BigDecimal(pageRegUserRate).setScale(2,BigDecimal.ROUND_HALF_UP))+"%");
		lastCalRe.setRztgs(String.valueOf(pageUserAppNum));
		lastCalRe.setSxbs(String.valueOf(pageAuthNum));
		lastCalRe.setSxze(String.valueOf(new BigDecimal(String.valueOf(pageLoanAmount)).setScale(2,BigDecimal.ROUND_HALF_UP)));
		tempList.add(lastCalRe);
		return tempList;
	}
	/**
	public Object list(HttpServletRequest request) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		long province = 0;
		long city = 0;
		long area = 0;
		// 映射Pager对象
		Pager pager = JSON.parseObject("{size:4}", Pager.class);
		// 判断是否包含自定义参数
		//parameters = pager.getParameters();
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		if (request.getParameter("province") != null){
			province = Long.parseLong(request.getParameter("province"));
		}
		if (request.getParameter("city") != null){
			city = Long.parseLong(request.getParameter("city"));
		}
		if (request.getParameter("area") != null){
			area = Long.parseLong(request.getParameter("area"));
		}
		
		parameters.put("placeName", "重庆市");
		if(area != 0) {
			parameters.put("currentarea", "area");
			parameters.put("pcId", area);
		}else if(area == 0 && city !=0) {
			parameters.put("currentarea", "city");
			parameters.put("pcId", city);
			
			if(provinceCitiesService.selectPccodeById(parameters)>0){//重庆市特殊处理，定位区 时需要截取4位字符串
				parameters.put("currentarea", "area");
			}
		}else {
			parameters.put("currentarea", "province");
			parameters.put("pcId", province);
		}
		
		//是否是重庆
		if(provinceCitiesService.selectPccodeById(parameters)>0){
			parameters.put("isChongQing", "yes");
		}else{
			parameters.put("isChongQing", "no");
		}
		
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		List<ReportingEntity> reportingDetails = new ArrayList<>();
		ReportingEntity rg = new ReportingEntity();
		rg.setSwjg("32");
		reportingDetails.add(rg);
				//reportingService.reportingDetails(parameters);
//		ReportingEntity unauthorizedDetails = reportingService.unauthorizedDetails(parameters);
//		reportingDetails.add(unauthorizedDetails);
		
		ReportingEntity totalEnterprises = reportingService.totalEnterprises(parameters);
//		ReportingEntity re = new ReportingEntity();
//		Long zcyh = 0l;
//		Long rztgs = 0l;
//		Long sxbs = 0l;
//		Long sxze = 0l;
//		if (reportingDetails != null){
//			for (ReportingEntity rd : reportingDet ails){
//				zcyh += Long.parseLong(rd.getZcyh());
//				rztgs += Long.parseLong(rd.getRztgs());
//				sxbs += Long.parseLong(rd.getSxbs());
//				sxze += Long.parseLong(rd.getSxze());
//			}
//		}
//		re.setArea("合计");
//		re.setZcyh(zcyh.toString());
//		re.setRztgs(rztgs.toString());
//		re.setSxbs(sxbs.toString());
//		re.setSxze(sxze.toString());
//		reportingDetails.add(re);
		//Collections.reverse(reportingDetails);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportingDetails", reportingDetails);	
//		map.put("unauthorizedDetails", unauthorizedDetails);
		map.put("totalEnterprises", totalEnterprises);
		return JSON.toJSONString(map);
	}**/
	/*public Object list(String gridPager) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
	
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
//		String regioncode = sessionUser.getRegionsEntity().getRegioncode();
		Long province = Long.parseLong((String)parameters.get("province"));
		Long city = Long.parseLong((String)parameters.get("city"));
		Long area = Long.parseLong((String)parameters.get("area"));
//		long province = 0;
//		long city = 0;
//		long area = 0;
//		if (request.getParameter("province") != null){
//			province = Long.parseLong(request.getParameter("province"));
//		}
//		if (request.getParameter("city") != null){
//			city = Long.parseLong(request.getParameter("city"));
//		}
//		if (request.getParameter("area") != null){
//			area = Long.parseLong(request.getParameter("area"));
//		}	
		if(area != 0) {
			parameters.put("currentarea", "area");
			parameters.put("pcId", area);
		}else if(area == 0 && city !=0) {
			parameters.put("currentarea", "city");
			parameters.put("pcId", city);
		}else {
			parameters.put("currentarea", "province");
			parameters.put("pcId", province);
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		parameters.put("starttime", starttime);
		parameters.put("endtime", endtime);
		List<ReportingEntity> list = reportingService.reportingDetails(parameters);
		ReportingEntity unauthorizedDetails = reportingService.unauthorizedDetails(parameters);
		list.add(unauthorizedDetails);
		
		ReportingEntity totalEnterprises = reportingService.totalEnterprises(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
		
	}*/
	
	
	@RequestMapping(value = "details.html")
	@ResponseBody
	public Object details(HttpServletRequest request) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value = "detailsUI.html")
	public String detailsUI(Model model,HttpServletRequest request){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<>();
//		String data = request.getParameter("data"); 
//		JSONObject jsonObject = JSONObject.fromObject(data); 
//		String taxid = jsonObject.getString("taxid");
		String taxid = (String)request.getParameter("taxid");
		String starttime = (String)request.getParameter("starttime");
		String endtime = (String)request.getParameter("endtime");
		String province = (String)request.getParameter("province");
		String city = (String)request.getParameter("city");
		String area = (String)request.getParameter("area");
//		if(pccode == -1){
//			//未认证企业，无省市区，获取session中的region_id
//			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
//			Long regionId = sessionUser.getRegionid();
//			map.put("region_id",regionId);
//		}else {
//			map.put("pccode", pccode);
//			if(pccode%10000 == 0){//省，匹配两位
//				map.put("pro_area_city", 2);
//			}else if(pccode%100 == 0){//区，匹配四位
//				map.put("pro_area_city", 4);
//			}else{//市，匹配六位
//				map.put("pro_area_city", 6);
//			}
//		}
		map.put("taxid", taxid);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("province", province!= null&&!("0".equals(province)||"".equals(province))?province:null);
		map.put("city", city!=null&&!("0".equals(city)||"".equals(city))?city:null);
		map.put("area", area!=null&&!("0".equals(area)||"".equals(area))?area:null);
		list = reportingService.detailUI(map);
		model.addAttribute("province",province );
		model.addAttribute("city", city);
		model.addAttribute("area", area);
		model.addAttribute("nsrList", list);
		return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/detailUI";
 	}
	
	@RequestMapping(value = "detailsUIrztg.html")
	public String detailsUIrxtg(Model model,HttpServletRequest request){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<>();
		
//		String data = request.getParameter("data"); 
//		JSONObject jsonObject = JSONObject.fromObject(data); 
		String taxid = (String)request.getParameter("taxid");
		String starttime = (String)request.getParameter("starttime");
		String endtime = (String)request.getParameter("endtime");
		String province = (String)request.getParameter("province");
		String city = (String)request.getParameter("city");
		String area = (String)request.getParameter("area");
//		if(pccode == -1){
//			//未认证企业，无省市区，获取session中的region_id
//			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
//			Long regionId = sessionUser.getRegionid();
//			map.put("region_id",regionId);
//		}else {
//			map.put("pccode", pccode);
//			if(pccode%10000 == 0){//省，匹配两位
//				map.put("pro_area_city", 2);
//			}else if(pccode%100 == 0){//区，匹配四位
//				map.put("pro_area_city", 4);
//			}else{//市，匹配六位
//				map.put("pro_area_city", 6);
//			}
//		}
		map.put("taxid", taxid);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("province", province!= null&&!("0".equals(province)||"".equals(province))?province:null);
		map.put("city", city!=null&&!("0".equals(city)||"".equals(city))?city:null);
		map.put("area", area!=null&&!("0".equals(area)||"".equals(area))?area:null);
		list = reportingService.detailUIrztg(map);
		model.addAttribute("nsrList", list);
		return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/detailUIrztg";
 	}
	
	@RequestMapping(value = "detailsUIsxbs.html")
	public String detailsUIsxbs(Model model,HttpServletRequest request){
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<>();
		
//		String data = request.getParameter("data"); 
//		JSONObject jsonObject = JSONObject.fromObject(data); 
		String taxid = (String)request.getParameter("taxid");
		String starttime = (String)request.getParameter("starttime");
		String endtime = (String)request.getParameter("endtime");
		String province = (String)request.getParameter("province");
		String city = (String)request.getParameter("city");
		String area = (String)request.getParameter("area");
//		if(pccode == -1){
//			//未认证企业，无省市区，获取session中的region_id
//			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
//			Long regionId = sessionUser.getRegionid();
//			map.put("region_id",regionId);
//		}else {
//			map.put("pccode", pccode);
//			if(pccode%10000 == 0){//省，匹配两位
//				map.put("pro_area_city", 2);
//			}else if(pccode%100 == 0){//区，匹配四位
//				map.put("pro_area_city", 4);
//			}else{//市，匹配六位
//				map.put("pro_area_city", 6);
//			}
//		}
		map.put("taxid", taxid);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		map.put("province", province!= null&&!("0".equals(province)||"".equals(province))?province:null);
		map.put("city", city!=null&&!("0".equals(city)||"".equals(city))?city:null);
		map.put("area", area!=null&&!("0".equals(area)||"".equals(area))?area:null);
		list = reportingService.detailUIsxbs(map);
		model.addAttribute("nsrList", list);
		return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/detailUIsxbs";
 	}
	
	/**
	 * 跳转到实际发放贷款情况界面
	 * @param model
	 * @param gridPager
	 * @return
	 */
	@RequestMapping(value = "toReport.html")
	public Object toReport(Model model,String gridPager){
		return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/toReporting";
	}
	
	
	/**
	 * 获取实际发放贷款情况界面 统计数据(静态数据)
	 * @param request
	 * @return
	 */
	@RequestMapping("toReportlistStatic.html")
	@ResponseBody
	public Object toReportlistStatic(HttpServletRequest request){
		
		Map<String, Object> parameters = new HashMap<String, Object>();
//		//省市区定位
//		long province = 0;
//		long city = 0;
//		long area = 0;
//		if (request.getParameter("province") != null && request.getParameter("province").length()>0){
//			province = Long.parseLong(request.getParameter("province"));
//		}
//		if (request.getParameter("city") != null && request.getParameter("city").length()>0){
//			city = Long.parseLong(request.getParameter("city"));
//		}
//		if (request.getParameter("area") != null && request.getParameter("area").length()>0){
//			area = Long.parseLong(request.getParameter("area"));
//		}	
//		if(area != 0) {
//			parameters.put("pc_id", area);
//		}else if(area == 0 && city !=0) {
//			parameters.put("pc_id", city);
//		}else {
//			parameters.put("pc_id", province);
//		}
		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		String regionId = sessionUser.getRegionid();	
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
        parameters.put("province", province!= null&&!("0".equals(province)||"".equals(province))?province:null);
        parameters.put("city", city!=null&&!("0".equals(city)||"".equals(city))?city:null);
        parameters.put("area", area!=null&&!("0".equals(area)||"".equals(area))?area:null);
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMM");
		Calendar now = Calendar.getInstance();  
	    int begin=(now.get(Calendar.YEAR));
//	    String data_monthStart = String.valueOf(begin)+"01";//年份+01，取当年到现在的资料更新数据
//	    String data_monthEnd = dateFormat.format(new Date());//年份+当前月份，取当年到现在的资料更新数据
	    String data_start=String.valueOf(begin)+"0101";//取从年初到当前授信完成的贷款
//	    parameters.put("data_monthStart",data_monthStart);
//	    parameters.put("data_monthEnd",data_monthEnd);
	    parameters.put("data_start",data_start);
	    String last_month = dateFormat.format(getLastDate(new Date()));
	    parameters.put("last_month",last_month);//获取最新的资料更新数据(年度计划值)

		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
	    if(starttime!=""&&endtime!=""){
	    	parameters.put("starttime",starttime);
	 	    parameters.put("endtime",endtime);
	    }
	    
	    ActualissuanceLoansEntity actualissuanceLoansEntity = actualissuanceLoansService.getStaticData(parameters);
	    parameters.clear();
	    if(actualissuanceLoansEntity != null){
	    	parameters.put("actEntity", actualissuanceLoansEntity);
	    	parameters.put("success", Boolean.TRUE);
	    }else{
	    	parameters.put("success", Boolean.FALSE);
	    }
		return parameters;
	}
	
	
	
	/**
	 * 获取实际发放贷款情况界面 统计数据(动态数据)
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("toReportlist.html")
	public Object toReportlist(HttpServletRequest request) throws Exception{
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");

//		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		
		// 判断是否包含自定义参数
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		long province = 0;
//		long city = 0;
//		long area = 0;
//		if (request.getParameter("province") != null && request.getParameter("province").length()>0){
//			province = Long.parseLong(request.getParameter("province"));
//		}
//		if (request.getParameter("city") != null && request.getParameter("city").length()>0){
//			city = Long.parseLong(request.getParameter("city"));
//		}
//		if (request.getParameter("area") != null && request.getParameter("area").length()>0){
//			area = Long.parseLong(request.getParameter("area"));
//		}	
//		if(area != 0) {
////			parameters.put("currentarea", "area");
//			parameters.put("pc_id", area);
//		}else if(area == 0 && city !=0) {
////			parameters.put("currentarea", "city");
//			parameters.put("pc_id", city);
//		}else {
////			parameters.put("currentarea", "province");
//			parameters.put("pc_id", province);
//		}
//		
//		//获取所有下辖的区的pc_ic
////		List<Integer> areaList = actualissuanceLoansService.getUnderArea(parameters);
//		
////		parameters.clear();
////		parameters.put("areaList", areaList);
		
		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		String regionId = sessionUser.getRegionid();	
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
        parameters.put("province", province!= null&&!("0".equals(province)||"".equals(province))?province:null);
        parameters.put("city", city!=null&&!("0".equals(city)||"".equals(city))?city:null);
        parameters.put("area", area!=null&&!("0".equals(area)||"".equals(area))?area:null);
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
	    if(starttime!=""&&endtime!=""){
	    	parameters.put("starttime",starttime);
	 	    parameters.put("endtime",endtime);
	    }
		ActualissuanceLoansEntity act =actualissuanceLoansService.getDynamicData(parameters);
       
		parameters.clear();
		if(act != null){
			parameters.put("exhibitDatas", act);
			parameters.put("success", Boolean.TRUE);
		}else{
			parameters.put("success", Boolean.FALSE);
		}
		return parameters;
	}
	
	/**
	 * 跳转到银税互动资料更新界面
	 * @return
	 */
	@RequestMapping("toRefresh.html")
	public Object toRefresh(){
		return Common.BACKGROUND_PATH + "/statisticalanalysis/reporting/toRefresh";
	}
	
	/**
	 * 获取银税互动资料更新界面 数据
	 * @return
	 */
	@RequestMapping("getData.html")
	@ResponseBody
	public Object getData(){
		Map<String,Object> map = new HashMap<>();
		
		//获取组织id
		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		String orgid = sessionUser.getOrgid();
		
		//获取当前月份的上个月份
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String data_month = dateFormat.format(getLastDate(date));
		
		//获取资料更新实体
		map.put("org_id", orgid);
		map.put("data_month", data_month);
		DataRefreshEntity drEntity = dataRefreshService.getDataRefresh(map);
		
		if(drEntity == null){
			drEntity = new DataRefreshEntity();
			drEntity.setHasUpdate(Boolean.FALSE);//本月未更新
//			drEntity.setBad_num("0");
//			drEntity.setBad_sum("0");
//			drEntity.setBad_rate("0");
//			drEntity.setYearly_plan_micro_sum("0");
//			drEntity.setYearly_plan_sum("0");
		}else{
			drEntity.setHasUpdate(Boolean.TRUE);//本月已有更新
		}
		map.clear();
		map.put("drEntity", drEntity);
		return map;
	}
	
	/**
	 * 获取上一个月份
	 * 格式：yyyyMM
	 * @param date
	 * @return
	 */
	 private static Date getLastDate(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, -1);
	        return cal.getTime();
	    }
	
	
	/**
	 * 银税互动资料更新界面 数据更新或插入
	 * @return
	 */
	@RequestMapping("updateData.html")
	@ResponseBody
	public Object updateData(DataRefreshEntity dataRefreshEntity){
		Map<String,Object> map = new HashMap<>();
		int result = 0;
		//获取登陆者信息
		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		
		//构造“资料更新”实体
		dataRefreshEntity.setUpdatetime(new Date());
		dataRefreshEntity.setUpdatorid(sessionUser.getId());
		
		if(dataRefreshEntity.isHasUpdate()){//本月有更新过，作update
			result = dataRefreshService.updateDataRefresh(dataRefreshEntity);
		}else{//本月没更新过，作insert
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMM");
			Date date = new Date();
			//上个月月份
			String data_month = dateFormat.format(getLastDate(date));
			dataRefreshEntity.setId(IDGenerate.getZJID("XH"));
			dataRefreshEntity.setCreatetime(new Date());
			dataRefreshEntity.setCreatorid(sessionUser.getId());
			dataRefreshEntity.setData_month(data_month);
			dataRefreshEntity.setHasUpdate(Boolean.TRUE);
			dataRefreshEntity.setOrg_id(sessionUser.getOrgid());
			dataRefreshEntity.setRegion_id(sessionUser.getRegionid());
			
			result = dataRefreshService.insertDataRefresh(dataRefreshEntity);
		}
		
		if(result == 1){
			map.put("success", Boolean.TRUE);
			map.put("updatetime", dataRefreshEntity.getUpdatetime());
			map.put("hasUpdate", dataRefreshEntity.isHasUpdate());
			map.put("dr_id", dataRefreshEntity.getDr_id());
		}else{
			map.put("success", Boolean.FALSE);
		}
		
		return map;
	}
	
	@RequestMapping(value = "downloadExport.html")
	public void downloadExport(HttpServletRequest request,HttpServletResponse response){
		String data = request.getParameter("exportData");
		if(data==null||"".equals(data))return;
		JSONArray list = JSONArray.fromObject(data);
		String path = 	request.getServletContext().getRealPath("/")+File.separator+"resources"+File.separator+"batchExcel"+File.separator+"BankTaxStatistics.xls";
		InputStream in ;
		HSSFWorkbook wb = null;
		try {
			File file = new File(path);
			in = new FileInputStream(file);
			wb = new HSSFWorkbook(new POIFSFileSystem(in));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowIndex=1;
		for(int i=0;i<list.size();i++){
		     JSONObject jobj =  (JSONObject) list.get(i);
		     Row row=sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(jobj.getString("swjg"));
				row.createCell(1).setCellValue(jobj.getString("zcyh"));
				row.createCell(2).setCellValue(jobj.getString("zqyzsbl"));
				row.createCell(3).setCellValue(jobj.getString("rztgs"));
				row.createCell(4).setCellValue(jobj.getString("sxbs"));
				row.createCell(5).setCellValue(jobj.getString("sxze"));
		}
		//设置列宽
		for(int i = 0,n = sheet.getRow(0).getLastCellNum();i <= n; i++){
			sheet.setColumnWidth(i,256*16+184);
		}
		
		//设置单元格样式
		HSSFCellStyle cellStyle = wb.createCellStyle(); 
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
//		cellStyle.setWrapText(true);//自动换行
		for(int i = 1,n = sheet.getLastRowNum(); i <= n; i++){
			for(int j = 0,m = sheet.getRow(0).getLastCellNum();j <= m;j++){
				Row row = sheet.getRow(i);
				if(row!= null){
					Cell cell = row.getCell(j);
					if(cell != null){
						cell.setCellStyle(cellStyle);
					}
				}
			}
		}
		
		try {
			export(response,wb,"BankTaxStatistics.xls");//导出表格
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception{
		String fileNames[] = fileName.split("\\.");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer name = new StringBuffer(fileNames[0]).append(sdf.format(new Date())).append(".").append(fileNames[1]);
		response.setHeader("Content-Disposition", "attachment;filename="+new String(name.toString().getBytes("utf-8"),"iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}
	
}
