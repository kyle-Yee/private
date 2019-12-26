package com.dcits.govsbu.sd.taxbankplatform.bankmanager.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BMListModel;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BankOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BusinessRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.DataUpdateRecord;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.FPOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxOptionInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxOptionRecord;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.service.BankManagerService;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/bankManager/")
public class bankManagerController extends BaseController{
	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	public BankManagerService bankManagerService;
	@RequestMapping("bankList.html")
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
			return Common.BACKGROUND_PATH + "/bankManager/bankList";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping(value = "list.html")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception{
		Map<String, Object> parameters = new HashMap<String, Object>();
		// 映射Pager对象
		String gridPager = request.getParameter("gridPager");
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		Map<String, Object> searchMap =null;
		searchMap = pager.getParameters();
		String searchKey =null;
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		String regionId = sessionUser.getRegionid();
		System.out.println(regionId);
		Map<String,Object> paramaters = new HashMap<String,Object>();
		if(regionId!=null){
			paramaters.put("regionId", regionId);
		}
		if(searchMap.containsKey("searchKey")){
			searchKey=(String) searchMap.get("searchKey");
			paramaters.put("searchKey", searchKey);
		}
        Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "bk.updatetime DESC");
		List<BMListModel> bankManagerList = bankManagerService.queryBMList(paramaters);
		for (int i = 0; i < bankManagerList.size(); i++) {
			bankManagerList.get(i).setIndexNo(i+1);
		}
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", bankManagerList);
		return parameters;
	}
	
	@RequestMapping("showBankCfgDtl.html")
	public String showBankCfgDtl(HttpServletRequest request) {		
		try
		{	
			String fpId = request.getParameter("fpId");
			List<BusinessRegInfo> businessRegInfo = bankManagerService.queryBusinessRegInfo(fpId);
			request.setAttribute("businessRegInfo", businessRegInfo);
			return Common.BACKGROUND_PATH + "/bankManager/bankTaxDataCfg";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}		
	}
	
	@RequestMapping("addBankConf.html")
	public String addUI(HttpServletRequest request) {		
		try
		{	
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = sessionUser.getRegionid();
			Map<String,Object> paramaters = new HashMap<String,Object>();
			if(regionId!=null){
				paramaters.put("regionId", regionId);
			}
			List<BankOrg> bankList = bankManagerService.queryBankOrg(paramaters);
			request.setAttribute("bankList", bankList);
			
			setRequestTaxOption(request);
			return Common.BACKGROUND_PATH + "/bankManager/addBankConf";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}		
	}
	
	@RequestMapping("editBankConf.html")
	public String editBankConf(HttpServletRequest request) {		
		try
		{	
			//获取登录用户
			//UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String fpId = request.getParameter("fpId");
			String bankCode = request.getParameter("bankCode");
			BMListModel bmModel = bankManagerService.queryBmModel(fpId,bankCode);
			setRequestTaxOption(request);
			request.setAttribute("BMListModel", bmModel);
			return Common.BACKGROUND_PATH + "/bankManager/editBankConf";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}		
	}

	@RequestMapping("updatsBankStatus.html")
	public String updatsBankStatus(HttpServletRequest request) {		
		try
		{	
			String fpIdBankCodeStr = request.getParameter("fpIdBankCodeStr");
			List<String> fpIdBankCodeList =  java.util.Arrays.asList(fpIdBankCodeStr.split(","));; 
			String status = request.getParameter("status");
			Map<String,Object> paramaters = new HashMap<String,Object>();
			paramaters.put("fpIdBankCodeList", fpIdBankCodeSeparate(fpIdBankCodeList));
			paramaters.put("status", status);
			if(fpIdBankCodeList.size()>0&&("1".equals(status)||"2".equals(status)))
				bankManagerService.updateBankStatus(paramaters);
			return Common.BACKGROUND_PATH + "/bankManager/bankList";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}		
	}
	/**
	 * 将“fpId:bankCode”分离
	 * @author Sigua.Huang
	 * @date 2018年6月29日
	 */
	private List<BMListModel> fpIdBankCodeSeparate(List<String> list) {
		List<BMListModel> finalList = new ArrayList<BMListModel>();
		for (String fpIdBankCode : list) {
			BMListModel bmListModel = new BMListModel();
			String[] fpIdBankCodeArr = fpIdBankCode.split(":");
			bmListModel.setFpId(fpIdBankCodeArr[0]);
			bmListModel.setBankCode(fpIdBankCodeArr[1]);
			finalList.add(bmListModel);
		}
		return finalList;
	}
	
	@RequestMapping("addAction.html")
	@ResponseBody
	public Object add(BMListModel bmListModel) throws AjaxException
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Enumeration<?> enu=request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		List<TaxOptionRecord> recList = new ArrayList<TaxOptionRecord>();
		while(enu.hasMoreElements()){
			String paraName=(String)enu.nextElement();
			if(paraName.startsWith("DM"))
			{
				TaxOptionRecord tempRecord = new TaxOptionRecord();
				tempRecord.setPkey(IDGenerate.getZJID("DM"));
				tempRecord.setBankcode(bmListModel.getBankCode());
				tempRecord.setFpId(bmListModel.getFpId());
				tempRecord.setFpName(bmListModel.getFpName());
				tempRecord.setTaxOptionId(paraName);
				recList.add(tempRecord);
			}
		}
		try{	
			//插入银行管理服务配置
			bmListModel.setStatus("0");//默认待开通
			int resultBmModel =bankManagerService.insert(bmListModel);
			//插入国税数据项记录
			int result = bankManagerService.insertTaxOptList(recList);
			if(resultBmModel==1&&result==recList.size()){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(ServiceException e){
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping("editAction.html")
	@ResponseBody
	public Object edit(BMListModel bmListModel) throws AjaxException{
		//获取登录用户
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		//记录修改前的数据项所需参数的实体
		DataUpdateRecord dataUpdateRecord = new DataUpdateRecord();
		dataUpdateRecord.setCreateId(sessionUser.getId());
		dataUpdateRecord.setBankid(bmListModel.getBankId());
		dataUpdateRecord.setBankcode(bmListModel.getBankCode());
		dataUpdateRecord.setFpId(bmListModel.getFpId());
		Enumeration<?> enu=request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		List<TaxOptionRecord> recList = new ArrayList<TaxOptionRecord>();
		while(enu.hasMoreElements()){
			String paraName=(String)enu.nextElement();
			if(paraName.startsWith("DM"))
			{
				TaxOptionRecord tempRecord = new TaxOptionRecord();
				tempRecord.setPkey(IDGenerate.getZJID("DM"));
				tempRecord.setBankcode(bmListModel.getBankCode());
				tempRecord.setFpId(bmListModel.getFpId());
				tempRecord.setFpName(bmListModel.getFpName());
				tempRecord.setTaxOptionId(paraName);
				recList.add(tempRecord);
			}
		}
		try{
			int recordSign = bankManagerService.recordDataUpdate(dataUpdateRecord);//对数据项的记录
			int resultBmModel =bankManagerService.updateBMModel(bmListModel);
			int result = bankManagerService.delAndinsertTaxOptList(recList);
			if(recordSign==1&&resultBmModel==1&&result==recList.size()){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "修改成功");
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "修改失败");
			}
		}catch(ServiceException e){
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 添加选择银行时，异步查询该银行下的产品
	 * @author Sigua.Huang
	 * @date 2018年6月21日
	 */
	@RequestMapping("findFPsByBankId.html")
	@ResponseBody
	public Object findFPsByBankId(String bankId) throws AjaxException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<FPOrg> fpList = bankManagerService.findFPsByBankId(bankId);
		if(fpList==null||fpList.size()<1){
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "未获取到产品");
		}else{
			map.put("success", Boolean.TRUE);
			map.put("data", fpList);
			map.put("message", "");
		}
		return map;
	}
	
	/**
	 * 添加选择银行时，异步查询该银行下的产品
	 * @author Sigua.Huang
	 * @date 2018年6月21日
	 */
	@RequestMapping("findFPList.html")
	@ResponseBody
	public Object findFPList(String bankId) throws AjaxException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<FPOrg> fpList = bankManagerService.findFpList(bankId);
		logger.info("*********************添加数据项银行change时的bankId："+bankId+",查询出的产品fpList为："+fpList);
		if(fpList==null||fpList.size()<1){
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "未获取到产品");
		}else{
			map.put("success", Boolean.TRUE);
			map.put("data", fpList);
			map.put("message", "");
		}
		return map;
	}

	private void setRequestTaxOption(HttpServletRequest request){
		//根据银行产品Id拿到该税局数据项
		String fpId = request.getParameter("fpId");
		String bankCode = request.getParameter("bankCode");
		Map<String,String> paramMap = new  HashMap<String,String>();
		paramMap.put("fpId", fpId);
		paramMap.put("bankCode", bankCode);
		List<TaxOptionInfo> optList = bankManagerService.queryTaxOptionTe(paramMap);
		//以表名>标识归类数据项
		LinkedHashMap<String,LinkedHashMap<String,List<TaxOptionInfo>>> finalMap = new LinkedHashMap<String,LinkedHashMap<String,List<TaxOptionInfo>>>();
		//循环所有数据项，以表名>标识归类
		int subSign = 0;
		for(TaxOptionInfo taxOptionInfo : optList){
			String dataHeader = taxOptionInfo.getDataHeader();
			//a.如果finalMap包含key为tableName，根据表名>标识归类
			if(finalMap.containsKey(dataHeader)){
				finalMap.get(dataHeader).get(getRankMapKey(taxOptionInfo,subSign)).add(taxOptionInfo);
				subSign++;
			}else{//b.否则重新new一个集合
				subSign = 0;
				LinkedHashMap<String,List<TaxOptionInfo>> rankMap = new LinkedHashMap<String,List<TaxOptionInfo>>();
				rankMap.put("sub1", new ArrayList<TaxOptionInfo>());//初始化实质性内容的集合1
				rankMap.put("sub2", new ArrayList<TaxOptionInfo>());//初始化实质性内容的集合2
				rankMap.put("esub", new ArrayList<TaxOptionInfo>());//初始化非实质性内容的集合
//				rankMap.put("other", new ArrayList<TaxOptionInfo>());//初始其他内容的集合
				rankMap.get(getRankMapKey(taxOptionInfo,subSign)).add(taxOptionInfo);
				finalMap.put(dataHeader, rankMap);
				subSign++;
			}
		}
		request.setAttribute("finalMap", finalMap);
	}
	private String getRankMapKey(TaxOptionInfo taxOptionInfo,int subSign){
		String sign = taxOptionInfo.getSign();
		String rankMapKey;//归类标识
		if("1".equals(sign)){
			rankMapKey = subSign%2==0 ? "sub1":"sub2";
		}else{
//			rankMapKey = "0".equals(sign) ? "esub":"other";
			rankMapKey = "esub";
		}
		return rankMapKey;
	}
}

