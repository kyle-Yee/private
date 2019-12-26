/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.loanagreement.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.model.AgreementkvEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.model.LoanAgreementEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.service.LoanAgreementService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author 胡宝龙2016-8-18 下午9:19:18
 * 贷款协议
 */
@Controller
@Scope("prototype")
@RequestMapping("/loanagreement/")
public class LoanAgreementController extends BaseController {
	@Autowired
	private LoanAgreementService loanAgreementService; //贷款协议
	@Autowired
	private RegionsService regionsService; //城市
	@Autowired
	private OrganizationsService organizationsService; //组织表
	/**
	 * 某银行所有贷款协议列表页跳转
	 */
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
			Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
			Boolean readOnly = (Boolean) map.get("readOnly");
			Boolean regionShow = (Boolean) map.get("regionShow");
			Boolean orgShow = (Boolean) map.get("orgShow");
			model.addAttribute("readOnly", readOnly);
			model.addAttribute("regionShow", regionShow);
			model.addAttribute("orgShow", orgShow);
			return Common.BACKGROUND_PATH + "/loanagreement/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 列出某银行所有贷款协议
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager) throws Exception{
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId =  (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		String otid = (String) map.get("otid");
		Map<String, Object> parameters = null;
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		parameters = pager.getParameters();
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "updatetime DESC");
		if(("ZZLX002").equals(otid)){//如果登陆用户属于银行组织则只显示此银行的产品
			parameters.put("orgId", orgId);//所属银行
		}
		parameters.put("regionId", regionId);//所在城市
		List<LoanAgreementEntity> list = loanAgreementService.queryListByPage(parameters);
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setIndexNo(i+1);
			}
		}
		for (LoanAgreementEntity loanAgreementEntity : list) {
			String la_regionId =loanAgreementEntity.getRegionId();
			String la_orgId =loanAgreementEntity.getOrgId();
			if(null !=la_regionId){
				RegionsEntity regionEntity = regionsService.findById(la_regionId);
				if(null !=regionEntity){
					String regionName = regionEntity.getRegionname();
					loanAgreementEntity.setRegionName(regionName);
				}
			}
			if(null !=la_orgId){
				OrganizationEntity organizationEntity =organizationsService.findById(la_orgId);
				if(null !=organizationEntity){
					String orgName = organizationEntity.getOrgname();
					loanAgreementEntity.setOrgName(orgName);
				}
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
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	/**
	 * 产品详情和贷款申请单 弹出层
	 */
	@RequestMapping(value = "detail.html", method = RequestMethod.GET)
	public String detail(Model model,String id) {
		try
		{
	        LoanAgreementEntity loanAgreementEntity =  loanAgreementService.findById(id);
	        model.addAttribute("loanAgreementEntity", loanAgreementEntity);//贷款协议
			return Common.BACKGROUND_PATH + "/loanagreement/detail";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	/**
	 * 添加贷款协议跳转页
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId =  (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		OrganizationEntity organizationEntity=organizationsService.findById(orgId);
		String rzcid=organizationEntity.getRzcid();
		String sqcid=organizationEntity.getSqcid();
		ArrayList<AgreementkvEntity> list= new ArrayList<AgreementkvEntity>();
		
		if(rzcid!=null&&!("").equals(rzcid)){
			list.add(new AgreementkvEntity(rzcid,"认证协议"));
		}
		if(sqcid!=null&&!("").equals(sqcid)){
			list.add(new AgreementkvEntity(sqcid, "税务授信协议"));
		}
		model.addAttribute("regionId", regionId);
		model.addAttribute("orgId", orgId);
		model.addAttribute("list", list);
		return Common.BACKGROUND_PATH + "/loanagreement/form";
	}

	/**
	 * 添加协议
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(LoanAgreementEntity loanagreement,Model model, HttpServletRequest request, HttpServletResponse response)
	{
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		//生成 tb_loan_agreement 主键ID
		String laId = IDGenerate.getZJID("CPXY");
		try
		{
			loanagreement.setId(laId);
			loanagreement.setRegionId(regionId); //所属城市ID
			loanagreement.setOrgId(orgId); //所属组织ID
			String laName = loanagreement.getLaName();//判断此协议名字是否存在
			Map<String, Object> map2 = new HashMap<>();
			map2.put("laName", laName);
			map2.put("regionId", regionId);
			map2.put("orgId", orgId);
			String xy=loanagreement.getLaxyid();
			int result =0;
			Long newlfId =null;
            List<LoanAgreementEntity> loanagreements = loanAgreementService.checkName(map2);
			
			if(loanagreements.size()>0){
				System.out.println("名称为:"+laName+"的产品已存在!");
			}
			else  if(!("").equals(xy)){
				map2.put("rzcxyid",loanagreement.getLaxyid());
				List<LoanAgreementEntity> loanagreements2 = loanAgreementService.queryListByrzxy(map2);
				if(loanagreements2.size()>0){
					result=2;
					System.out.println("产品已存在认证/税务授信协议!");
				}else{
					this.createModel(loanagreement);
					if(loanAgreementService. insert(loanagreement)>0){
						result=1;//认证协议/授权协议添加成功
					}else {
						result=0;
					}
				}
			}
			
			else{
			
				this.createModel(loanagreement);
				if(loanAgreementService.insert(loanagreement)>0){
					result=1;//普通产品协议添加成功
				}else {
					result=0;
				}
				//保存贷款协议
			}
			if(result == 1)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("newlfId", newlfId);
				map.put("message", "添加成功");
			}else if(result==2){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("newlfId",null);
				map.put("message", "产品已存在认证/税务授信协议!");
			}else{
				map.put("success", Boolean.FALSE);
				map.put("newlfId", null);
				map.put("data", null);
				map.put("message", "添加失败");
			}
	        /*if(loanagreement.getLaxyid()!=null&&!("").equals(loanagreement.getLaxyid())){
	        	if(("rzxy").equals(loanagreement.getLaxyid().substring(0,4))){
	        		map2.put("rzcxyid",loanagreement.getLaxyid());
	        		List<LoanAgreementEntity> loanagreements = loanAgreementService.queryListByrzxy(map2);
	        		if(loanagreements!=null||loanagreements.size()!=0){
	        		    response.setContentType("text/html; charset=UTF-8"); //转码
	   				    PrintWriter out = response.getWriter();
	   				    out.flush();
	   				    out.println("<script>");
	   				    out.println("layer.msg('认证协议已存在，其选择其他协议！');");
	   				    out.println("history.back();");
	   				    out.println("</script>");
	   				    return listUI(model, request);
	        		}
	        	}else if (("sqxy").equals(loanagreement.getLaxyid().substring(0,4))) {
	        		map2.put("sqcxyid",loanagreement.getLaxyid());
	        		List<LoanAgreementEntity> loanagreements = loanAgreementService.queryListBysqxy(map2);
	        		if(loanagreements!=null||loanagreements.size()!=0){
	        		    response.setContentType("text/html; charset=UTF-8"); //转码
	   				    PrintWriter out = response.getWriter();
	   				    out.flush();
	   				    out.println("<script>");
	   				    out.println("layer.msg('税务协议已存在，请选择其他协议！');");
	   				    out.println("history.back();");
	   				    out.println("</script>");
	   				    return listUI(model, request);
	        		}
				}
	        }*/
			
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	/**
	 * 银行贷款协议修改页跳转
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		LoanAgreementEntity loanAgreement;
		try
		{
			loanAgreement = loanAgreementService.findById(id);
			model.addAttribute("loanAgreement", loanAgreement);
			List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
			Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
			String orgId = (String) map.get("orgid");
			OrganizationEntity organizationEntity=organizationsService.findById(orgId);
			String rzcid=organizationEntity.getRzcid();
			String sqcid=organizationEntity.getSqcid();
			ArrayList<AgreementkvEntity> list= new ArrayList<AgreementkvEntity>();
			
			if(rzcid!=null&&!("").equals(rzcid)){
				list.add(new AgreementkvEntity(rzcid,"认证协议"));
			}
			if(sqcid!=null&&!("").equals(sqcid)){
				list.add(new AgreementkvEntity(sqcid, "税务授信协议"));
			}
			model.addAttribute("list",list);
			return Common.BACKGROUND_PATH + "/loanagreement/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 更新银行贷款协议
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object update(LoanAgreementEntity loanAgreementEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			this.updateModel(loanAgreementEntity);
			int result = loanAgreementService.update(loanAgreementEntity);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	/**
	 * 贷款协议批量删除
	 */
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	@Transactional
	public Object deleteBatch(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		int cnt=0;
		try
		{
			String[] financialProductIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : financialProductIds) {
				list.add(string);
				LoanAgreementEntity loanAgreementEntity=loanAgreementService.findById(string);
				this.updateModel(loanAgreementEntity); //更新操作人和修改时间
				loanAgreementEntity.setEnabled("N");
				int i=loanAgreementService.update(loanAgreementEntity);
				if(i>0){
					cnt+=1;
				}
			}
			if(cnt == list.size())
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "删除成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "删除失败");
			}
		}catch(Exception e){
			throw new AjaxException(e);
		}
		return result;
	}
	/**
	 * 判断协议名称是否唯一
	 */
	@RequestMapping("checkLaName.html")
	public void checkFpName(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding( "utf-8" );
		//((ServletRequest) response).setCharacterEncoding( "utf-8" );
		response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
		response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
		String laName = request.getParameter("laName");
		String orgIdStr = request.getParameter("orgId");
		String regionIdStr = request.getParameter("regionId");
		Map<String, Object> map2 = new HashMap<>();
		if(null !=orgIdStr && orgIdStr.length()>0){
			map2.put("orgId",orgIdStr);
		}
		if(null !=regionIdStr && regionIdStr.length()>0){
			map2.put("regionId",regionIdStr);
		}
		map2.put("laName", laName);
		List<LoanAgreementEntity> loanAgreementEntities = loanAgreementService.checkName(map2);
		Boolean notExist = true;
		if(loanAgreementEntities.size()>0){
			notExist =false;
		}
		PrintWriter out = response.getWriter();
		out.print(notExist);
		out.flush();
		out.close();
	}
}
