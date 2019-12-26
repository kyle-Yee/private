package com.dcits.govsbu.sd.taxbankplatform.financialProduct.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.ActionEnter;
import com.dcits.govsbu.sd.taxbankplatform.amount.model.AmountEntity;
import com.dcits.govsbu.sd.taxbankplatform.amount.service.AmountService;
import com.dcits.govsbu.sd.taxbankplatform.attachment.model.Attachment;
import com.dcits.govsbu.sd.taxbankplatform.attachment.service.AttachmentService;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.deadline.model.DeadlineEntity;
import com.dcits.govsbu.sd.taxbankplatform.deadline.service.DeadlineService;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;
import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.model.GuaranteeStyleEntity;
import com.dcits.govsbu.sd.taxbankplatform.guaranteestyle.service.GuaranteeStyleService;
import com.dcits.govsbu.sd.taxbankplatform.loanForms.model.LoanForms;
import com.dcits.govsbu.sd.taxbankplatform.loanForms.service.LoanFormsService;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.model.LoanAgreementEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanagreement.service.LoanAgreementService;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.model.LoanFormsAttach;
import com.dcits.govsbu.sd.taxbankplatform.loanformsattach.service.LoanFormsAttachService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.HtmlTagService;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.ProductdataitemsService;
import com.dcits.govsbu.sd.taxbankplatform.productfaq.model.ProductFaqEntity;
import com.dcits.govsbu.sd.taxbankplatform.productfaq.service.ProductFaqService;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.model.ProductStatusEntity;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.service.ProductStatusService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.rates.model.RatesEntity;
import com.dcits.govsbu.sd.taxbankplatform.rates.service.RatesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.model.RepaymentWayEntity;
import com.dcits.govsbu.sd.taxbankplatform.repaymentway.service.RepaymentWayService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.PermissionOrgcode;
import com.dcits.govsbu.sd.taxbankplatform.util.QueryChildorg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author baolong.hu
 * 理财产品模块
 *financialProduct
 */
@Controller
@Scope("prototype")
@RequestMapping("/financialProduct/")
public class FinancialProductController extends BaseController {
	@Autowired
	private FinancialProductService financialProductService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ActionEnter actionEnter;
	@Autowired
	private AmountService amountService;
	@Autowired
	private DeadlineService deadlineService;
	@Autowired
	private RatesService ratesService;
	@Autowired
	private ProductStatusService productStatusService;
	@Autowired
	private ProductFaqService productFaqService;
	@Autowired
	private RepaymentWayService repaymentWayService;
	@Autowired
	private GuaranteeStyleService guaranteeStyleService;
	@Autowired
	private LoanFormsService loanFormsService; //贷款单基础表
	@Autowired
	private LoanFormsAttachService LoanFormsAttachService; //贷款单附表
	@Autowired
	private ProductdataitemsService productdataitemsService; //数据项
	@Autowired
	private LoanAgreementService loanAgreementService; //贷款协议
	@Autowired
	private RegionsService regionsService; //城市
	@Autowired
	private ProvinceCitiesService provinceCitiesService; //区域码表
	@Autowired
	private HtmlTagService htmlTagService; //贷款数据项中间表
	@Autowired
	private OrganizationsService organizationsService; //组织表
	@Autowired
	private ParametersService parametersService;//参数表
	/**
	 * 所有产品列表页跳转
	 */
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
			String regioncode  = sessionUser.getRegionsEntity().getRegioncode();
			ProvinceCitiesEntity citiesEntity = provinceCitiesService.findByPccode(regioncode);
			List<OrganizationEntity> listorg = organizationsService.queryListAll(null);
			List<ProvinceCitiesEntity> listpccity = provinceCitiesService.queryListAll(null);
			Map<String, Object> map = OrganizationsUtil.getParament(listorg, listpccity, sessionUser, citiesEntity);
			Map<String, Object> mapptdm = PermissionOrgcode.getParameters();
			//List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
			//Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
			Boolean readOnly = (Boolean) map.get("readOnly");
			Boolean regionShow = (Boolean) map.get("regionShow");
			Boolean orgShow = (Boolean) map.get("orgShow");
			Boolean ptdm = (Boolean) mapptdm.get("ptdm");
			model.addAttribute("readOnly", readOnly);
			model.addAttribute("regionShow", regionShow);
			model.addAttribute("orgShow", orgShow);
			model.addAttribute("ptdm", ptdm);
			return Common.BACKGROUND_PATH + "/financialProduct/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 列出所有产品
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager) throws Exception{
		//List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		//Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		String regioncode  = sessionUser.getRegionsEntity().getRegioncode();
		Map<String, Object> parameters = null;
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		parameters = pager.getParameters();
		if (regioncode.length()>0 && !"KTQY0002017010100000000000".equals(sessionUser.getRegionid())) {
			ProvinceCitiesEntity citiesEntity = provinceCitiesService.findByPccode(regioncode);
			List<OrganizationEntity> listorg = organizationsService.queryListAll(null);
			List<ProvinceCitiesEntity> listpccity = provinceCitiesService.queryListAll(null);
			Map<String, Object> map = OrganizationsUtil.getParament(listorg, listpccity, sessionUser, citiesEntity);
			//Long regionId = (Long) map.get("regionid");
			//Long orgId = (Long) map.get("orgid");
			String otid =  (String) map.get("otid");
			if (("ZZLX002").equals(otid)) {//如果登陆用户属于银行组织则只显示此银行的产品
				String orglist = (String) map.get("orgidlist");
				parameters.put("orglist", orglist);
			}else {
				String regionlist = (String) map.get("pccodes");
				List<RegionsEntity> regionlists = regionsService.findByRegioncode(regionlist);
				StringBuffer sb = new StringBuffer();
				String regionid = "";
				for (RegionsEntity regionsEntity : regionlists) {
					if (null != regionsEntity.getId()) {
						regionid += String.valueOf(regionsEntity.getId())+",";
					}
				}
				sb.append(regionid);
				if (','==sb.charAt(sb.length()-1)) {
					sb = sb.deleteCharAt(sb.length() - 1); 
				}
				String regid = sb.toString();
				parameters.put("regionlist", regid);
			}
			
		}
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "createtime DESC");
		/*parameters.put("regionId", regionId);//所在城市
		if(otid ==2){//如果登陆用户属于银行组织则只显示此银行的产品
			parameters.put("orgId", orgId);//所属银行
		}*/
		List<FinancialProduct> list = financialProductService.queryListByPage(parameters);
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setIndexNo(i+1);
			}
		}
		for (FinancialProduct financialProduct : list) {
			String fp_regionId=financialProduct.getRegionId();
			String fp_orgId=financialProduct.getOrgId();
			if(null !=fp_regionId){
				RegionsEntity regionEntity = regionsService.findById(fp_regionId);
				if(null !=regionEntity){
					String regionName = regionEntity.getRegionname();
					financialProduct.setRegionName(regionName);
				}
			}
			if(null !=fp_orgId){
				OrganizationEntity organizationEntity =organizationsService.findById(fp_orgId);
				if(null !=organizationEntity){
					String orgName = organizationEntity.getOrgname();
					financialProduct.setOrgName(orgName);
				}
			}
			//rwIds 还款方式  (多个) 
			String rwIds = financialProduct.getRwIds();
			String rwNames ="";
			if(rwIds !=null && rwIds.length()>0){
				String[] rwIdsArray = rwIds.split("#");
				for (int i = 0; i < rwIdsArray.length; i++) {
					String rwIdStr=rwIdsArray[i];
					if(null !=rwIdStr && rwIdStr.length()>0){
						String rwId = rwIdStr;
						RepaymentWayEntity repaymentWayEntity = repaymentWayService.findById(rwId);
						if(repaymentWayEntity !=null){
							rwNames += repaymentWayEntity.getRwname()+"<span>&nbsp;&nbsp;&nbsp;&nbsp;<span>";
						}
					}
				}
			}
	        financialProduct.setRwIds(rwNames);
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
	 * 未发布产品列表页跳转
	 */
	@RequestMapping("listPublishUI.html")
	public String listPublishUI(Model model, HttpServletRequest request) {
		try
		{
			HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
			String regioncode  = sessionUser.getRegionsEntity().getRegioncode();
			ProvinceCitiesEntity citiesEntity = provinceCitiesService.findByPccode(regioncode);
			List<OrganizationEntity> listorg = organizationsService.queryListAll(null);
			List<ProvinceCitiesEntity> listpccity = provinceCitiesService.queryListAll(null);
			Map<String, Object> map = OrganizationsUtil.getParament(listorg, listpccity, sessionUser, citiesEntity);
		//	List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		//	Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
			Boolean readOnly = (Boolean) map.get("readOnly");
			Boolean faqReadOnly = (Boolean) map.get("faqReadOnly");
			Boolean regionShow = (Boolean) map.get("regionShow");
			Boolean orgShow = (Boolean) map.get("orgShow");
			model.addAttribute("readOnly", readOnly);
			model.addAttribute("faqReadOnly", faqReadOnly);
			model.addAttribute("regionShow", regionShow);
			model.addAttribute("orgShow", orgShow);
			return Common.BACKGROUND_PATH + "/financialProduct/listPublish";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 列出所有未发布的产品
	 */
	@ResponseBody
	@RequestMapping("listPublish.html")
	public Object listPublish(String gridPager) throws Exception{
		HttpServletRequest requests = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)requests.getSession().getAttribute("userSession");
		String regioncode  = sessionUser.getRegionsEntity().getRegioncode();
		Map<String, Object> parameters = null;
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		parameters = pager.getParameters();
		if (regioncode.length()>0) {
			ProvinceCitiesEntity citiesEntity = provinceCitiesService.findByPccode(regioncode);
			List<OrganizationEntity> listorg = organizationsService.queryListAll(null);
			List<ProvinceCitiesEntity> listpccity = provinceCitiesService.queryListAll(null);
			Map<String, Object> map = OrganizationsUtil.getParament(listorg, listpccity, sessionUser, citiesEntity);
			String otid =  (String) map.get("otid");
			if (("ZZLX002").equals(otid)) {//如果登陆用户属于银行组织则只显示此银行的产品
				String orglist = (String) map.get("orgidlist");
				parameters.put("orglist", orglist);
			} else {
				String regionlist = (String) map.get("pccodes");
				List<RegionsEntity> regionlists = regionsService.findByRegioncode(regionlist);
				StringBuffer sb = new StringBuffer();
				String regionid = "";
				for (RegionsEntity regionsEntity : regionlists) {
					if (null != regionsEntity.getId()) {
						regionid += String.valueOf(regionsEntity.getId())+",";
					}
				}
				sb.append(regionid);
				if (','==sb.charAt(sb.length()-1)) {
					sb = sb.deleteCharAt(sb.length() - 1); 
				}
				String regid = sb.toString();
				parameters.put("regionlist", regid);
			}
			
		}
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "createtime DESC");
		parameters.put("ps_id", "CPZT01");//未发布
		//parameters.put("regionId", regionId);//所在城市
		/*if(otid ==2){//如果登陆用户属于银行组织则只显示此银行的产品
			Long orgId = (Long) map.get("orgid");
			parameters.put("orgId", orgId);//所属银行
		}*/
		List<FinancialProduct> list = financialProductService.queryListByPage(parameters);
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setIndexNo(i+1);
			}
		}
		for (FinancialProduct financialProduct : list) {
			
			String fp_regionId=financialProduct.getRegionId();
			String fp_orgId=financialProduct.getOrgId();
			if(null !=fp_regionId){
				RegionsEntity regionEntity = regionsService.findById(fp_regionId);
				if(null !=regionEntity){
					String regionName = regionEntity.getRegionname();
					financialProduct.setRegionName(regionName);
				}
			}
			if(null !=fp_orgId){
				OrganizationEntity organizationEntity =organizationsService.findById(fp_orgId);
				if(null !=organizationEntity){
					String orgName = organizationEntity.getOrgname();
					financialProduct.setOrgName(orgName);
				}
			}
			
			//AmountEntity 最高贷款额度  
			String amountId = financialProduct.getAmountEntity().getId();
			AmountEntity amountEntity = null;
			if(amountId !=null){
				amountEntity = amountService.findById(amountId);
			}
			amountEntity.setId(amountId);
			financialProduct.setAmountEntity(amountEntity);
			//DeadlineEntity 最长贷款期限  
			String deadlineEntityId = financialProduct.getDeadlineEntity().getId();
			DeadlineEntity deadlineEntity = null;
			if(deadlineEntityId !=null){
				deadlineEntity = deadlineService.findById(deadlineEntityId);
			}
			deadlineEntity.setId(deadlineEntityId);
			financialProduct.setDeadlineEntity(deadlineEntity);
			//ratesEntity //贷款利率  
			String ratesEntityId = financialProduct.getRatesEntity().getId();
			RatesEntity ratesEntity = null;
			if(ratesEntityId !=null){
				ratesEntity = ratesService.findById(ratesEntityId);
			}
			ratesEntity.setId(ratesEntityId);
			financialProduct.setRatesEntity(ratesEntity);
			
			//rwIds 还款方式  (多个) 
			String rwIds = financialProduct.getRwIds();
			String rwNames ="";
			if(rwIds !=null && rwIds.length()>0){
				String[] rwIdsArray = rwIds.split("#");
				for (int i = 0; i < rwIdsArray.length; i++) {
					String rwIdStr=rwIdsArray[i];
					if(null !=rwIdStr && rwIdStr.length()>0){
						String rwId =rwIdStr;
						RepaymentWayEntity repaymentWayEntity = repaymentWayService.findById(rwId);
						if(repaymentWayEntity !=null){
							rwNames += repaymentWayEntity.getRwname()+"<span>&nbsp;&nbsp;&nbsp;&nbsp;<span>";
						}
					}
				}
			}
	        financialProduct.setRwIds(rwNames);
		}

		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	/**
	 * 添加产品跳转页
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model,int ptdm) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");							//用户登录之后  ，获取区域id
		String orgId = (String) map.get("orgid");								//用户登录之后  ，获取组织id
		
		Map<String,Object> parameter1 = new HashMap<String,Object>();
		//根据用户所在组织查找到下级的所有组织
		List <OrganizationEntity> childOrganizationList = new ArrayList<OrganizationEntity>();
		String childOrgIds = QueryChildorg.getorglistIdquery(organizationList, orgId);
		
		String[] childOrgIdsArray = childOrgIds.split(",");
        for (int i = 0; i < childOrgIdsArray.length; i++) {
            String childOrgIdStr=childOrgIdsArray[i];
            if(null !=childOrgIdStr && childOrgIdStr.length()>0){
//            	Long childOrgId = Long.valueOf(childOrgIdStr);
            	OrganizationEntity childOrganizationEntity = organizationsService.findById(childOrgIdStr);
            	childOrganizationList.add(childOrganizationEntity);
            }
        }
		
		List<AmountEntity> amountEntityList =amountService.queryListByPage(null);
		List<DeadlineEntity> deadlineEntityList =deadlineService.queryListByPage(null);
		List<RatesEntity> ratesEntityList =ratesService.queryListByPage(null);
		List<ProductStatusEntity> productStatusEntityList =productStatusService.queryListByPage(null);
		List<RepaymentWayEntity> repaymentWayEntityList =repaymentWayService.queryListByPage(null);
		List<GuaranteeStyleEntity> guaranteeStyleEntityList =guaranteeStyleService.queryListByPage(null);
		
		model.addAttribute("cityList", childOrganizationList);
		model.addAttribute("amountEntityList", amountEntityList);
		model.addAttribute("deadlineEntityList", deadlineEntityList);
		model.addAttribute("ratesEntityList", ratesEntityList);
		model.addAttribute("productStatusEntityList", productStatusEntityList);
		model.addAttribute("repaymentWayEntityList", repaymentWayEntityList);
		model.addAttribute("guaranteeStyleEntityList", guaranteeStyleEntityList);
		
		parameter1.clear();
		parameter1.put("regionId", regionId);
		parameter1.put("orgId", orgId);
		parameter1.put("laxy","laxy");
		List<LoanAgreementEntity> loanAgreementList = loanAgreementService.queryListByPage(parameter1);
		model.addAttribute("loanAgreementList", loanAgreementList);
		model.addAttribute("regionId", regionId);
		model.addAttribute("orgId", orgId);
	    if(ptdm==2){//2为信用卡产品添加页面
	    	return Common.BACKGROUND_PATH + "/financialProduct/formLoan";
	    }else {
	    	return Common.BACKGROUND_PATH + "/financialProduct/form";
		}
		
	}
	/**
	 * 选择贷款协议弹出层
	 */
	@RequestMapping(value = "showCheckDkxy.html", method = RequestMethod.GET)
	public String CheckDkxy(Model model) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		
		try
		{
			Map<String,Object> parameter1 = new HashMap<String,Object>();
			parameter1.put("regionId", regionId);
			parameter1.put("orgId", orgId);
			List<LoanAgreementEntity> loanAgreementList = loanAgreementService.queryListByPage(parameter1);
			model.addAttribute("loanAgreementList", loanAgreementList);
			return Common.BACKGROUND_PATH + "/financialProduct/showCheckDkxy";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 添加产品
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(FinancialProduct financialProduct,int ptdm)
	{
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");	
		String orgcode=sessionUser.getOrganizationEntity().getOrgcode();
		try
		{
			financialProduct.setRegionId(regionId); //所属城市ID
			financialProduct.setOrgId(orgId); //所属组织ID
            
			String attachmenturls = request.getParameter("attachmenturls");
			
			//判断此产品名字是否存在
			String fpName = financialProduct.getFpName();
			Map<String, Object> map2 = new HashMap<>();
			map2.put("fpName", fpName);
			map2.put("regionId", regionId);
			map2.put("orgId", orgId);
			List<FinancialProduct> financialProducts = financialProductService.checkName(map2);
			int result =0;
			int resultInsertlf=0;
			String newlfId = "";
			if(financialProducts.size()>0){
				System.out.println("名称为:"+fpName+"的产品已存在!");
			}else{
				this.createModel(financialProduct);
				if(ptdm==2){//为2时信用卡产品添加
					String rateCode;
					String deadlineCode;
					String amountCode;
					if(("ZGGDYH").equals(orgcode)){
						rateCode="cq_ZGGDYH_rate";
						deadlineCode="cq_ZGGDYH_deadline";
						amountCode="cq_ZGGDYH_amount";
						String rateVaule=parametersService.QueryValueByCode(rateCode);
						String deadlineValue=parametersService.QueryValueByCode(deadlineCode);
						String amountValue=parametersService.QueryValueByCode(amountCode);
						AmountEntity amountEntity=new AmountEntity();
						if(amountValue!=null&&!(" ").equals(amountValue)){
							amountEntity.setId(amountValue);
						}else{
							amountEntity.setId("0");//为空时取0
							logger.error("*****************==========光大银行信用卡-额度 tb_parameters未配置 =========***********************");
						}
						
						financialProduct.setAmountEntity(amountEntity);
						DeadlineEntity deadlineEntity=new DeadlineEntity();
						if(deadlineValue!=null&&!(" ").equals(deadlineValue)){
							deadlineEntity.setId(deadlineValue);
						}else{
							deadlineEntity.setId("0");
							logger.error("*****************==========光大银行信用卡-期限 tb_parameters未配置 =========***********************");
						}
					
						financialProduct.setDeadlineEntity(deadlineEntity);
						RatesEntity ratesEntity=new RatesEntity();
						if(rateVaule!=null&&!(" ").equals(rateVaule)){
							ratesEntity.setId(rateVaule);
						}else{
							ratesEntity.setId("0");
							logger.error("*****************==========光大银行信用卡-利率 tb_parameters未配置 =========***********************");
						}
						
					financialProduct.setRatesEntity(ratesEntity);
					financialProduct.setGsIds("DBFS01");
					financialProduct.setRwIds("HKFS01");
					}
				}
				financialProduct.setPtDm(ptdm);
				result = financialProductService.insert(financialProduct);//保存理财产品信息

				String la_idStr = request.getParameter("sxxyss");

				String fp_id = financialProduct.getId();
				//新建申请单 1.申请单主表中新增数据
				LoanForms loanForms = new LoanForms();
				loanForms.setFp_id(fp_id);
				loanForms.setOrgId(orgId);
				loanForms.setRegionId(regionId);
//				Long la_id = null;
				if(null !=la_idStr && la_idStr.length()>0){
//					la_id = Long.parseLong(la_idStr);
					loanForms.setLa_id(la_idStr);//授权协议ID
				}else{
					loanForms.setLa_id(null);//授权协议ID
				}
				this.createModel(loanForms);
				// TODO Auto-generated method stub
				String lfId = IDGenerate.getZJID("SQD");
				loanForms.setId(lfId);
				resultInsertlf = loanFormsService.insert(loanForms);
				newlfId =lfId;
				}
			
			
			//判断是否上传了附件

			if(null !=attachmenturls && attachmenturls.length()>0 ){
				boolean f = this.saveAttachments(session,financialProduct.getId(),attachmenturls);//保存附件
			
				if(result > 0 && resultInsertlf>0 && f)
				{
					map.put("success", Boolean.TRUE);
					map.put("data", null);
					map.put("newlfId", newlfId);
					map.put("message", "添加成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("newlfId", null);
					map.put("data", null);
					map.put("message", "添加失败");
				}
			}else{
				if(result > 0 && resultInsertlf>0)
				{
					map.put("success", Boolean.TRUE);
					map.put("data", null);
					map.put("newlfId", newlfId);
					map.put("message", "添加成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("newlfId", null);
					map.put("data", null);
					map.put("message", "添加失败");
				}
			}
			
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	/**
	 * 申请单附表中新增数据
	 */
	@RequestMapping("addLoanFormsAttach.html")
	@ResponseBody
	@Transactional
	public Object addLoanFormsAttach(HttpServletRequest request) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String newlfIdStr = request.getParameter("newlfId");
		String la_idStr = request.getParameter("sxxyss");
		String newlfId =null;
		if(null !=newlfIdStr && newlfIdStr.length()>0){
			newlfId = newlfIdStr;
		}
		LoanFormsAttach loanFormsAttach = new LoanFormsAttach();
		loanFormsAttach.setLf_id(newlfId); //主表ID
		loanFormsAttach.setId(IDGenerate.getZJID("SQDF"));
		String pdi_idStr = request.getParameter("pdi_id");
		String pdi_id=null;
		if(null !=pdi_idStr &&pdi_idStr.length()>0 ){
			pdi_id = pdi_idStr;
		}
		loanFormsAttach.setPdi_id(pdi_id);
		String pdi_code = request.getParameter("pdi_code");
		if(null !=pdi_code && pdi_code.length()>0){
			loanFormsAttach.setPdi_code(pdi_code);
		}
		String pdi_name = request.getParameter("pdi_name");
		if(null !=pdi_name && pdi_name.length()>0){
			loanFormsAttach.setPdi_name(pdi_name);
		}
		String ht_type = request.getParameter("ht_type");
		if(null !=ht_type && ht_type.length()>0){
			loanFormsAttach.setHt_type(ht_type);
		}
		String pdi_values = request.getParameter("productditvalueslist");
		if(null !=pdi_values && pdi_values.length()>0){
			loanFormsAttach.setPdi_values(pdi_values);
		}
		this.createModel(loanFormsAttach);
	  /*  if(!("sqxyss").equals(pdi_code)){*/
	    	int resultloanFormsAttach = LoanFormsAttachService.insert(loanFormsAttach);
	    	if(resultloanFormsAttach>0){
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
	  /*  }*/
		
		return map;
	}
	/**
	 * 根据pdi_code判断哪些数据项已不需要
	 * 更新申请单附表中的不需要数据项enabled为N
	 */
	@RequestMapping("updateLoanFormsAttachEnabled.html")
	@ResponseBody
	@Transactional
	public Object updateLoanFormsAttachEnabled(HttpServletRequest request) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String lfIdStr = request.getParameter("lfId");
		String pdi_codes = request.getParameter("pdi_codes");
		String lfId =null;
		if(null!=lfIdStr && lfIdStr.length()>0){
			lfId = lfIdStr;
		}
        String[] pdi_codesArray_new = pdi_codes.split(","); //新数据项数组
        int resultloanFormsAttach =0;
        map.put("lf_id", lfId);
        List<LoanFormsAttach> loanFormsAttachList = LoanFormsAttachService.queryListByLfId(map);
        
        if(loanFormsAttachList.size()>0){
        	String[] pdi_codesArray_old = new String[loanFormsAttachList.size()]; //旧数据项数组
        	for (int i = 0; i < pdi_codesArray_old.length; i++) {
        		pdi_codesArray_old[i]= loanFormsAttachList.get(i).getPdi_code();
			}
        	
        	boolean isInclude = false;
        	for (int j = 0; j < pdi_codesArray_old.length; j++) {
        		for (int z = 0; z < pdi_codesArray_new.length; z++) {
        			if (pdi_codesArray_old[j].equalsIgnoreCase(pdi_codesArray_new[z])){//需要的code
        				isInclude = true;
        				break;
        			}
        		}
        		if (!isInclude){//不需要的code
        			if(null !=pdi_codesArray_old[j] && pdi_codesArray_old[j].length()>0){
        				map.clear();
        				map.put("lf_id", lfId);
        				map.put("pdi_code", pdi_codesArray_old[j]);
        				LoanFormsAttach loanFormsAttach = LoanFormsAttachService.queryExist(map);
        				if(null !=loanFormsAttach){
        					loanFormsAttach.setEnabled("N");
        					this.updateModel(loanFormsAttach);
        					resultloanFormsAttach += LoanFormsAttachService.update(loanFormsAttach);
        				}
        			}
        		}
        		isInclude = false;
        	}
        }
		map.put("newSize", pdi_codesArray_new.length);
		map.put("oldSize", loanFormsAttachList.size());
		map.put("result", resultloanFormsAttach);
		return map;
	}
	
	/**
	 * 更新申请单附表中的数据
	 */
	@RequestMapping("updateLoanFormsAttach.html")
	@ResponseBody
	@Transactional
	public Object updateLoanFormsAttach(HttpServletRequest request) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String newlfIdStr = request.getParameter("newlfId");
		String newlfId =null;
		if(null !=newlfIdStr && newlfIdStr.length()>0){
			newlfId = newlfIdStr;
		}
		LoanFormsAttach loanFormsAttach = null;
		
		String pdi_idStr = request.getParameter("pdi_id");
		String pdi_id=null;
		if(null !=pdi_idStr &&pdi_idStr .length()>0 ){
			pdi_id = pdi_idStr;
		}
		String pdi_code = request.getParameter("pdi_code");
		String pdi_name = request.getParameter("pdi_name");
		String ht_type = request.getParameter("ht_type");
		String pdi_values = request.getParameter("productditvalueslist");
		
		int resultloanFormsAttach=0;
		if(null !=newlfId && null !=pdi_code && pdi_code.length()>0){
			map.put("lf_id", newlfId);
			map.put("pdi_code", pdi_code);
			loanFormsAttach =LoanFormsAttachService.queryExist(map);//查询此条记录是否存在(不管是否enabled)
			if(null !=loanFormsAttach){ //存在则更新
				loanFormsAttach.setLf_id(newlfId); //主表ID
				loanFormsAttach.setPdi_id(pdi_id);
				if(null !=pdi_code && pdi_code.length()>0){
					loanFormsAttach.setPdi_code(pdi_code);
				}
				if(null !=pdi_name && pdi_name.length()>0){
					loanFormsAttach.setPdi_name(pdi_name);
				}
				if(null !=ht_type && ht_type.length()>0){
					loanFormsAttach.setHt_type(ht_type);
				}
				if(null !=pdi_values && pdi_values.length()>0){
					loanFormsAttach.setPdi_values(pdi_values);
				}
				loanFormsAttach.setEnabled("Y");
				this.updateModel(loanFormsAttach);
				resultloanFormsAttach = LoanFormsAttachService.update(loanFormsAttach);
			}else{ //不存在则插入
				loanFormsAttach = new LoanFormsAttach();
				loanFormsAttach.setLf_id(newlfId); //主表ID
				loanFormsAttach.setPdi_id(pdi_id);
				if(null !=pdi_code && pdi_code.length()>0){
					loanFormsAttach.setPdi_code(pdi_code);
				}
				if(null !=pdi_name && pdi_name.length()>0){
					loanFormsAttach.setPdi_name(pdi_name);
				}
				if(null !=ht_type && ht_type.length()>0){
					loanFormsAttach.setHt_type(ht_type);
				}
				if(null !=pdi_values && pdi_values.length()>0){
					loanFormsAttach.setPdi_values(pdi_values);
				}
				this.createModel(loanFormsAttach);
				resultloanFormsAttach = LoanFormsAttachService.insert(loanFormsAttach);
			}
		}
		map.clear();
		if(resultloanFormsAttach>0){
			map.put("success", Boolean.TRUE);
			map.put("data", null);
			map.put("message", "添加成功");
		}else{
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("addFaq.html")
	@ResponseBody
	@Transactional
	public Object addFaq(ProductFaqEntity faqEntity)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			String attachmenturls = request.getParameter("attachmenturls");
			String financialProductId = request.getParameter("financialProductId");
			if(financialProductId==null||"".equals(financialProductId)){
//				Long fpId=faqEntity.getFpId();
			}else {
//				Long fpId = Long.valueOf(financialProductId); 
				faqEntity.setFpId(financialProductId);//对应产品的ID
			}
			
			
			//保存数据
			this.createModel(faqEntity);
			faqEntity.setId(IDGenerate.getZJID("XH"));
			int result = productFaqService.insert(faqEntity);
			
			//判断是否上传了附件
			if(null!=attachmenturls && attachmenturls.length()>0 ){
				boolean f = this.saveAttachments(session,faqEntity.getId(),attachmenturls);//保存附件
				if(result > 0 && f)
				{
					map.put("success", Boolean.TRUE);
					map.put("data", null);
					map.put("message", "添加成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("data", null);
					map.put("message", "添加失败");
				}
			}else{
				if(result > 0)
				{
					map.put("success", Boolean.TRUE);
					map.put("data", null);
					map.put("message", "添加成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("data", null);
					map.put("message", "添加失败");
				}
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 银行产品修改页跳转
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request,int ptdm, String id) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");

		FinancialProduct financialProduct;
		try                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		{
			financialProduct = financialProductService.findById(id);
			Map<String,Object> parameter = new HashMap<String,Object>();

			Map<String,Object> parameter1 = new HashMap<String,Object>();
			//根据用户所在组织查找到下级的所有组织
			List <OrganizationEntity> childOrganizationList = new ArrayList<OrganizationEntity>();
			String childOrgIds = QueryChildorg.getorglistIdquery(organizationList, orgId);
			
			String[] childOrgIdsArray = childOrgIds.split(",");
	        for (int i = 0; i < childOrgIdsArray.length; i++) {
	            String childOrgIdStr=childOrgIdsArray[i];
	            if(null !=childOrgIdStr && childOrgIdStr.length()>0){
//	            	Long childOrgId = Long.valueOf(childOrgIdStr);
	            	OrganizationEntity childOrganizationEntity = organizationsService.findById(childOrgIdStr);
	            	childOrganizationList.add(childOrganizationEntity);
	            }
	        }
			
			List<AmountEntity> amountEntityList =amountService.queryListByPage(parameter);
			List<DeadlineEntity> deadlineEntityList =deadlineService.queryListByPage(parameter);
			List<RatesEntity> ratesEntityList =ratesService.queryListByPage(parameter);
			List<ProductStatusEntity> productStatusEntityList =productStatusService.queryListByPage(parameter);
			List<RepaymentWayEntity> repaymentWayEntityList =repaymentWayService.queryListByPage(parameter);
			List<GuaranteeStyleEntity> guaranteeStyleEntityList =guaranteeStyleService.queryListByPage(parameter);
			
			//循环遍历还款方式  (多个) 和 担保方式  (多个) 进行拆分并注入
			List<RepaymentWayEntity> repaymentWayEntityEntities = new ArrayList<RepaymentWayEntity>();
			String rwIds = financialProduct.getRwIds();
	        String[] rwIdsArray = rwIds.split("#");
	        for (int i = 0; i < rwIdsArray.length; i++) {
	            String rwIdStr=rwIdsArray[i];
	            if(null !=rwIdStr && rwIdStr.length()>0){
	            	String rwId = rwIdStr;
	            	RepaymentWayEntity repaymentWayEntity = repaymentWayService.findById(rwId);
	            	repaymentWayEntityEntities.add(repaymentWayEntity);
	            }
	        }
	        
	        List<GuaranteeStyleEntity> guaranteeStyleEntityEntities = new ArrayList<GuaranteeStyleEntity>();;
	        String gsIds = financialProduct.getGsIds();
	        String[] gsIdsArray = gsIds.split("#");
	        for (int i = 0; i < gsIdsArray.length; i++) {
	            String gsIdStr=gsIdsArray[i];
	            if(null !=gsIdStr && gsIdStr.length()>0){
	            	GuaranteeStyleEntity guaranteeStyleEntity = guaranteeStyleService.findById(gsIdStr);
	            	guaranteeStyleEntityEntities.add(guaranteeStyleEntity);
	            }
	        }
	        //覆盖区域拆分/注入
	        List<OrganizationEntity> cityEntities = new ArrayList<OrganizationEntity>();
	        String fpOverlayPcIds = financialProduct.getFpOverlayPcIds();
	        String[] fpOverlayPcIdsArray = fpOverlayPcIds.split("#");
	        for (int i = 0; i < fpOverlayPcIdsArray.length; i++) {
	            String fpOverlayPcIdStr=fpOverlayPcIdsArray[i];
	            if(null !=fpOverlayPcIdStr && fpOverlayPcIdStr.length()>0){
//	            	Long fpOverlayPcId = Long.valueOf(fpOverlayPcIdStr);
	            	OrganizationEntity cityEntity = organizationsService.findById(fpOverlayPcIdStr);
	            	cityEntities.add(cityEntity);
	            }
	        }
	        model.addAttribute("repaymentWayEntityEntities", repaymentWayEntityEntities);
	        model.addAttribute("guaranteeStyleEntityEntities", guaranteeStyleEntityEntities);
	        model.addAttribute("cityEntities", cityEntities);
	        
			model.addAttribute("cityList", childOrganizationList);
			model.addAttribute("amountEntityList", amountEntityList);
			model.addAttribute("deadlineEntityList", deadlineEntityList);
			model.addAttribute("ratesEntityList", ratesEntityList);
			model.addAttribute("productStatusEntityList", productStatusEntityList);
			
			model.addAttribute("repaymentWayEntityList", repaymentWayEntityList);
			model.addAttribute("guaranteeStyleEntityList", guaranteeStyleEntityList);
			
			model.addAttribute("financialProduct", financialProduct);
			
			//申请表附表
			LoanForms loanForms = null;
	        map.put("fp_id", id);
	        List<LoanForms> loanFormsList = loanFormsService.queryListByPage(map);
	        if(loanFormsList.size()>0){
	        	loanForms = loanFormsList.get(0);
	        }
	        String lf_id = null;
	        if(loanForms !=null){
	        	lf_id = loanForms.getId();
	        }
	        parameter.clear();
	        parameter.put("lf_id", lf_id);
	        List<LoanFormsAttach> loanFormsAttachList = LoanFormsAttachService.queryListByPage(parameter);
	        model.addAttribute("loanFormsAttachList", loanFormsAttachList);
	        String loanAgreementIdss = null;
	        for (LoanFormsAttach loanFormsAttach : loanFormsAttachList) {
	        	String pdi_code =loanFormsAttach.getPdi_code();
	        	if(pdi_code == "sqxyss" || pdi_code.equalsIgnoreCase("sqxyss") ){
	        		String loanAgreementIdStr = loanFormsAttach.getPdi_values();
	        		if(null !=loanAgreementIdStr && loanAgreementIdStr.length()>0){
	        			loanAgreementIdss = loanAgreementIdStr;
	        		}
	        	}
			}
	        //  根据产品附件表中的授权协议Id获取 授信协议对象
	        LoanAgreementEntity loanAgreementssEntity = loanAgreementService.findById(loanAgreementIdss);
	        
	        model.addAttribute("loanAgreementssEntity", loanAgreementssEntity);//授信协议
	        
	        parameter1.clear();
			parameter1.put("regionId", regionId);
			parameter1.put("orgId", orgId);
			parameter1.put("laxy","laxy");
			List<LoanAgreementEntity> loanAgreementList = loanAgreementService.queryListByPage(parameter1);
			model.addAttribute("loanAgreementList", loanAgreementList);
	        if(ptdm==1){
	        	return Common.BACKGROUND_PATH + "/financialProduct/form";
	        }else {
	        	return Common.BACKGROUND_PATH + "/financialProduct/formLoan";
			}
		    
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editFAQUI.html")
	public String editFAQUI(Model model, HttpServletRequest request, String id) {
		FinancialProduct financialProduct;
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		try
		{
			financialProduct = financialProductService.findById(id);
			map.put("fpId", id);
			List<ProductFaqEntity> faqList = productFaqService.queryListByPage(map);
			ProductFaqEntity productFaqEntity = null;
			if(faqList.size()>0){
				productFaqEntity = faqList.get(0);
			}
			model.addAttribute("productFaqEntity", productFaqEntity);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("regionId", regionId);//所在城市
			parameters.put("orgId", orgId);
			List<FinancialProduct> productName=financialProductService.selectAllName(parameters);
			model.addAttribute("productName",productName);
			Boolean faqReadOnly = (Boolean) map.get("faqReadOnly");
			model.addAttribute("faqReadOnly", faqReadOnly);
			model.addAttribute("financialProduct", financialProduct);
			return Common.BACKGROUND_PATH + "/financialProduct/editFaq";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editFaq.html")
	@ResponseBody
	@Transactional
	public Object editFaq(ProductFaqEntity productFaqEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			this.updateModel(productFaqEntity);
			String attachmenturls = request.getParameter("attachmenturls");
			String pfId = request.getParameter("pfId");
			if(productFaqEntity ==null){
				productFaqEntity =new ProductFaqEntity();
			}
			if(null !=pfId && pfId.length()>0){
				productFaqEntity.setId(pfId);
			}else{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
			int result = productFaqService.update(productFaqEntity);//更新Faq
			if(null !=attachmenturls && attachmenturls.length()>0 ){
				boolean f = this.saveAttachments(session,productFaqEntity.getId(),attachmenturls);
				if(result > 0 && f)
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
			}else{
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
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 * 更新金融产品
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object update(FinancialProduct financialProduct,int ptdm)
	{
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		try
		{
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			this.updateModel(financialProduct);
			String attachmenturls = request.getParameter("attachmenturls");
			int result = financialProductService.update(financialProduct);
			
			//修改申请单 1.申请单主表中新增数据
			
			LoanForms loanForms = loanFormsService.findByFpId(financialProduct.getId());
			loanForms.setFp_id(financialProduct.getId());
			loanForms.setOrgId(orgId);
			loanForms.setRegionId(regionId);
			String la_idStr = request.getParameter("sxxyss");
			if(null !=la_idStr && la_idStr.length()>0){
				loanForms.setLa_id(la_idStr);//授权协议ID
			}else{
				loanForms.setLa_id("0");//授权协议ID
			}
			this.updateModel(loanForms);
			int resultUptlf = loanFormsService.update(loanForms);
			String lfId = loanForms.getId();
			map.clear();
			if(null !=attachmenturls && attachmenturls.length()>0 ){
				boolean f = this.saveAttachments(session,financialProduct.getId(),attachmenturls);
				if(resultUptlf > 0 && result > 0 && f)
				{
					map.put("success", Boolean.TRUE);
					map.put("lfId", lfId);
					map.put("data", null);
					map.put("message", "编辑成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("data", null);
					map.put("message", "编辑失败");
				}
			}else{
				if(resultUptlf > 0 && result > 0)
				{
					map.put("success", Boolean.TRUE);
					map.put("lfId", lfId);
					map.put("data", null);
					map.put("message", "编辑成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("data", null);
					map.put("message", "编辑失败");
				}
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	private boolean saveAttachments(HttpSession session,String fkId,String attachmenturls){
		boolean f = true;
		try{
			UserEntity sessionUser = (UserEntity)session.getAttribute("userSession");
			@SuppressWarnings("unchecked")
			List<Attachment> allattachments = (List<Attachment>) session.getAttribute("allattachments");
			//用户添加的附件
			List<String> attachmenturlArr = Arrays.asList(attachmenturls.split(","));//url数组
			//用户最后保存的附件
			List<Attachment> savedAttachments = new ArrayList<Attachment>();
			if(allattachments != null && allattachments.size()>0){
				for(Attachment attachment :allattachments){
					if(attachmenturlArr.contains(attachment.getUrl())){
						//生成 tb_attachment 主键ID
						String aId = IDGenerate.getZJID("XH");
						attachment.setId(aId);
						attachment.setFkId(fkId);//设置关联表ID
						attachment.setCreatorId(sessionUser.getId());
						attachment.setCreatorName(sessionUser.getUserName());
						attachment.setStatus("1");//设置有效状态
						savedAttachments.add(attachment);
					}
				}
				//添加附件信息
				if(savedAttachments != null && savedAttachments.size()>0){
					attachmentService.insertBatch(savedAttachments);
				}
				//删除无效附件[编辑过程中产生的无效附件]
				allattachments.removeAll(savedAttachments);
				if(allattachments != null && allattachments.size()>0){
					attachmentService.deleteBatchByUrl(allattachments);
				}
			}
			//删除无效附件[编辑过程中删除的原始附件]
//			if(attachmentsOrig != null && attachmentsOrig.size()>0){
//				for(Attachment attachment :attachmentsOrig){
//					if(attachmenturlArr.contains(attachment.getUrl())){
//						attachment.setFkId(fkId);//设置关联表ID
//						attachment.setCreatorId(sessionUser.getId().intValue());
//						attachment.setCreatorName(sessionUser.getUserName());
//						attachment.setStatus("1");//设置有效状态
//						savedAttachments.add(attachment);
//					}
//				}
//				attachmentsOrig.removeAll(savedAttachments);
//				System.out.println(attachmentsOrig+"--------------------attachmentsOrig");
//				if(attachmentsOrig !=null && attachmentsOrig.size()>0){
//					Map<String,Object> mapParam = new HashMap<String,Object>();
//					mapParam.put("fkId", fkId);
//					mapParam.put("fkTable", FinancialProduct.getTablename());
//					mapParam.put("fkIdName", FinancialProduct.getIdname());
//					mapParam.put("attachments",attachmentsOrig);
//					attachmentService.deleteBatchByUrl(mapParam);
//				}
//			}
			session.removeAttribute("allattachments");//清除缓存
		}catch(Exception e){
			e.printStackTrace();
			f = false;
		}
		return f;
	}
	
	/**
	 * 产品下架
	 */
	@RequestMapping("fpRemove.html")
	@ResponseBody
	@Transactional
	public Object fpRemove(String ids) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		ids = request.getParameter("ids");
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			FinancialProduct financialProduct = financialProductService.findById(ids);
			this.updateModel(financialProduct);
			financialProduct.setFpRemoveTime(new Date());
			String ps_id = "CPZT03"; //设定下架状态为3
			ProductStatusEntity productStatusEntity = productStatusService.findById(ps_id);
			financialProduct.setProductStatusEntity(productStatusEntity);
			int cnt = financialProductService.update(financialProduct);
			if(cnt != 0)
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "下架成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "下架失败");
			}
		}catch(Exception e){
			throw new AjaxException(e);
		}
		return result;
	}
	/**
	 * 扩大金融产品的覆盖区域
	 */
	@RequestMapping("updateFpOverlayPcIds.html")
	@ResponseBody
	@Transactional
	public Object updateFpOverlayPcIds() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ids = request.getParameter("ids");
		String fp_overlay_pc_ids = request.getParameter("fp_overlay_pc_ids");
		String id=null;
		if(null !=ids && ids.length()>0){
			id = ids;
		}
		FinancialProduct financialProduct = financialProductService.findById(id);
		financialProduct.setFpOverlayPcIds(fp_overlay_pc_ids);
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			this.updateModel(financialProduct);
			int cnt = financialProductService.update(financialProduct);
			if(cnt != 0)
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "产品覆盖成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "产品覆盖失败");
			}
		}catch(Exception e){
			throw new AjaxException(e);
		}
		return result;
	}
	
	/**
	 * 产品批量发布
	 */
	@RequestMapping("updateStatus.html")
	@ResponseBody
	@Transactional
	public Object updateStatus(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] financialProductIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			int cnt=0;
			for (String string : financialProductIds) {
				if(null !=string && string.length()>0){
					list.add(string);
					FinancialProduct financialProduct=financialProductService.findById(string);
					this.updateModel(financialProduct); //更新操作人和修改时间
					financialProduct.setFpIssueTime(new Date()); //更新发布时间
					String ps_id = "CPZT02";
					ProductStatusEntity productStatusEntity = productStatusService.findById(ps_id);//更新发布状态
					financialProduct.setProductStatusEntity(productStatusEntity);
					int i=financialProductService.update(financialProduct);
					if(i>0){
						cnt+=1;
					}
				}
			}
			if(cnt == list.size())
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "发布成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "发布失败");
			}
		}catch(Exception e){
			throw new AjaxException(e);
		}
		return result;
	}
	
	/**
	 * 产品批量删除
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
				FinancialProduct financialProduct=financialProductService.findById(string);
				this.updateModel(financialProduct); //更新操作人和修改时间
				financialProduct.setFpIssueTime(new Date()); //更新发布时间
				financialProduct.setEnabled("N");
				int i=financialProductService.update(financialProduct);
				if(i>0){
					cnt+=1;
				}
			}
			boolean f = deleteBatchAttachments(list);
			if(cnt == list.size() && f)
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
	
	private boolean deleteBatchAttachments(List<String> fkIds){
		boolean f = true;
		try{
			Map<String,Object> mapParam = new HashMap<String,Object>();
			mapParam.put("fkIds", fkIds);
			mapParam.put("fkTable", FinancialProduct.getTablename());
			mapParam.put("fkIdName", FinancialProduct.getIdname());
			attachmentService.deleteBatchByFkId(mapParam);
		}catch(Exception e){
			e.printStackTrace();
			f = false;
		}
		return f;
	}
	
	/**
	 * 显示扩大产品覆盖区域的弹出层
	 */
	@RequestMapping(value = "showFpOverlayPcIds.html", method = RequestMethod.GET)
	public String showFpOverlayPcIds(Model model,String id) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String orgId = (String) map.get("orgid");
		try
		{
			FinancialProduct financialProduct = financialProductService.findById(id);
			String fpOverlayPcIds = financialProduct.getFpOverlayPcIds();
			List<OrganizationEntity> cityEntities = new ArrayList<OrganizationEntity>(); //产品已有的区域
			
			//根据用户所在组织查找到下级的所有组织
			List <OrganizationEntity> childOrganizationList = new ArrayList<OrganizationEntity>();
			String childOrgIds = QueryChildorg.getorglistIdquery(organizationList, orgId);
			
			String[] childOrgIdsArray = childOrgIds.split(",");
	        for (int i = 0; i < childOrgIdsArray.length; i++) {
	            String childOrgIdStr=childOrgIdsArray[i];
	            if(null !=childOrgIdStr && childOrgIdStr.length()>0){
//	            	Long childOrgId = Long.valueOf(childOrgIdStr);
	            	OrganizationEntity childOrganizationEntity = organizationsService.findById(childOrgIdStr);
	            	childOrganizationList.add(childOrganizationEntity);
	            }
	        }
			
	        String[] cityIdsArray = fpOverlayPcIds.split("#");
	        for (int i = 0; i < cityIdsArray.length; i++) {
	            String cityIdStr=cityIdsArray[i];
	            if(null !=cityIdStr && cityIdStr.length()>0){
//	            	Long city2Id = Long.valueOf(cityIdStr);
	            	OrganizationEntity city2 = organizationsService.findById(cityIdStr);
	            	cityEntities.add(city2);
	            }
	        }
			model.addAttribute("cityList", childOrganizationList);//此城市下的所有区域
			model.addAttribute("cityEntities", cityEntities);//此城市下已覆盖产品的区域
			model.addAttribute("financialProduct", financialProduct);
			
			return Common.BACKGROUND_PATH + "/financialProduct/showFpOverlayPcIds";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 显示添加数据项弹出层
	 */
	@RequestMapping("listDataItemsUI.html")
	public String listDataItemsUI(Model model, HttpServletRequest request) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId =  (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		
		try
		{
			String pdiName =request.getParameter("searchKey");
			//漏洞检测-过滤 searchKey
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("regionid", regionId);//所在城市
			parameters.put("orgid", orgId);//所属银行
			if(null !=pdiName && pdiName.length()>0 ){
				parameters.put("pdiName", pdiName);
			}
			List<Productdataitems> itemsList = new ArrayList<Productdataitems>();
			itemsList = productdataitemsService.queryListByPage(parameters);
			model.addAttribute("itemsList", itemsList);
			return Common.BACKGROUND_PATH + "/financialProduct/listDataItems";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 数据项弹出层的模糊查询
	 */
	@RequestMapping("listDataItems.html")
	public  void listDataItems( HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
		response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId =  (String) map.get("regionid");
		String orgId =  (String) map.get("orgid");
		
		PrintWriter out = response.getWriter();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("regionid", regionId);//所在城市
		parameters.put("orgid", orgId);//所属银行
		String pdiName =request.getParameter("searchKey");
		if(pdiName !=null && pdiName.length()>0 ){
			parameters.put("pdiName", pdiName);
		}
		List<Productdataitems> itemsList = new ArrayList<Productdataitems>();
		itemsList = productdataitemsService.queryListByPage(parameters);
		out.print(JSON.toJSONString(itemsList));
		out.flush();
		out.close();
		try
		{
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 产品详情和贷款申请单 弹出层
	 */
	@RequestMapping(value = "detail.html", method = RequestMethod.GET)
	public String detail(Model model,String id) {
		try
		{
			FinancialProduct financialProduct = financialProductService.findById(id);
			model.addAttribute("financialProduct", financialProduct);
			
			String amountEntityId =financialProduct.getAmountEntity().getId();
			AmountEntity amountEntity = amountService.findById(amountEntityId);
			model.addAttribute("amountName", amountEntity.getAmountName());
			
			String deadlineEntityId =financialProduct.getDeadlineEntity().getId();
			DeadlineEntity deadlineEntity = deadlineService.findById(deadlineEntityId);
			model.addAttribute("deadlineName", deadlineEntity.getDeadlineName());
			
			String ratesEntityId =financialProduct.getRatesEntity().getId();
			RatesEntity ratesEntity = ratesService.findById(ratesEntityId);
			model.addAttribute("ratesName", ratesEntity.getRatesName());
			
			//循环遍历还款方式  (多个) 和 担保方式  (多个) 进行拆分并注入
			List<RepaymentWayEntity> repaymentWayEntityEntities = new ArrayList<RepaymentWayEntity>();
			String rwIds = financialProduct.getRwIds();
	        String[] rwIdsArray = rwIds.split("#");
	        for (int i = 0; i < rwIdsArray.length; i++) {
	            String rwIdStr=rwIdsArray[i];
	            if(null !=rwIdStr && rwIdStr.length()>0){
	            	String rwId = rwIdStr;
	            	RepaymentWayEntity repaymentWayEntity = repaymentWayService.findById(rwId);
	            	repaymentWayEntityEntities.add(repaymentWayEntity);
	            }
	        }
	        
	        List<GuaranteeStyleEntity> guaranteeStyleEntityEntities = new ArrayList<GuaranteeStyleEntity>();;
	        String gsIds = financialProduct.getGsIds();
	        String[] gsIdsArray = gsIds.split("#");
	        for (int i = 0; i < gsIdsArray.length; i++) {
	            String gsIdStr=gsIdsArray[i];
	            if(null !=gsIdStr && gsIdStr.length()>0){
	            	GuaranteeStyleEntity guaranteeStyleEntity = guaranteeStyleService.findById(gsIdStr);
	            	guaranteeStyleEntityEntities.add(guaranteeStyleEntity);
	            }
	        }
	        //覆盖区域拆分/注入
	        List<OrganizationEntity> cityEntities = new ArrayList<OrganizationEntity>();;
	        String fpOverlayPcIds = financialProduct.getFpOverlayPcIds();
	        String[] fpOverlayPcIdsArray = fpOverlayPcIds.split("#");
	        for (int i = 0; i < fpOverlayPcIdsArray.length; i++) {
	            String fpOverlayPcIdStr=fpOverlayPcIdsArray[i];
	            if(null !=fpOverlayPcIdStr && fpOverlayPcIdStr.length()>0){
//	            	Long fpOverlayPcId = Long.valueOf(fpOverlayPcIdStr);
	            	OrganizationEntity city = organizationsService.findById(fpOverlayPcIdStr);
	            	cityEntities.add(city);
	            }
	        }
	        model.addAttribute("repaymentWayEntityEntities", repaymentWayEntityEntities);
	        model.addAttribute("guaranteeStyleEntityEntities", guaranteeStyleEntityEntities);
	        model.addAttribute("cityEntities", cityEntities);
			
	        LoanForms loanForms = null;
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("fp_id", id);
	        List<LoanForms> loanFormsList = loanFormsService.queryListByPage(map);
	        if(loanFormsList.size()>0){
	        	loanForms = loanFormsList.get(0);
	        }
	        String lf_id = null;
	        if(loanForms !=null){
	        	lf_id = loanForms.getId();
	        }
	        Map<String, Object> parameter = new HashMap<String, Object>();
	        parameter.put("lf_id", lf_id);
	        
	        List<LoanFormsAttach> loanFormsAttachList = LoanFormsAttachService.queryListByPage(parameter);
	        
	        String loanAgreementIdss = null;
	        for (LoanFormsAttach loanFormsAttach : loanFormsAttachList) {
	        	String pdi_code =loanFormsAttach.getPdi_code();
	        	if(pdi_code == "sqxyss" || pdi_code.equalsIgnoreCase("sqxyss") ){
	        		String loanAgreementIdStr = loanFormsAttach.getPdi_values();
	        		if(null !=loanAgreementIdStr && loanAgreementIdStr.length()>0){
	        			loanAgreementIdss = loanAgreementIdStr;
	        		}
	        	}
			}
	        //  根据产品附件表中的授信协议Id获取 授信协议对象
	        LoanAgreementEntity loanAgreementssEntity = loanAgreementService.findById(loanAgreementIdss);
	        
	        model.addAttribute("loanAgreementssEntity", loanAgreementssEntity);//授信协议涉税
	        
	        model.addAttribute("loanFormsAttachList", loanFormsAttachList);
			return Common.BACKGROUND_PATH + "/financialProduct/detail";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("ueditor.html")
    public void ueditor(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.setCharacterEncoding( "utf-8" );
        response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
		response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        actionEnter.initActionEnter(request, rootPath);
        String exec = actionEnter.exec();
        response.getWriter().write(exec);
    }
	/**
	 * 判断产品名称是否唯一
	 */
	@RequestMapping("checkFpName.html")
	public void checkFpName(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding( "utf-8" );
		response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
		response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
		//((ServletRequest) response).setCharacterEncoding( "utf-8" );
		
		String fpName = request.getParameter("fpName");
		String orgIdStr = request.getParameter("orgId");
		String regionIdStr = request.getParameter("regionId");
		Map<String, Object> map2 = new HashMap<>();
		if(null !=orgIdStr && orgIdStr.length()>0){
			map2.put("orgId",orgIdStr);
		}
		if(null !=regionIdStr && regionIdStr.length()>0){
			map2.put("regionId",regionIdStr);
		}
		map2.put("fpName", fpName);
		List<FinancialProduct> financialProducts = financialProductService.checkName(map2);
		Boolean notExist = true;
		if(financialProducts.size()>0){
			notExist =false;
		}
		PrintWriter out = response.getWriter();
		out.print(notExist);
		out.flush();
		out.close();
	}
	@RequestMapping("addEditUI.html")
	public String addEditUI(Model model) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		String orgId = (String) map.get("orgid");
		
		Map<String, Object> parameters =new HashMap<String, Object>();
		parameters.put("regionId", regionId);//所在城市
		parameters.put("orgId", orgId);
		List<FinancialProduct> productName=financialProductService.selectAllName(parameters);
		model.addAttribute("productName",productName);
		Boolean faqReadOnly = (Boolean) map.get("faqReadOnly");
		model.addAttribute("faqReadOnly", faqReadOnly);
		return Common.BACKGROUND_PATH + "/financialProduct/editFaq";
	}
	@RequestMapping("findEditUIById.html")
	public String findEditUIById(Model model,String id) {
		List <OrganizationEntity> organizationList = organizationsService.queryListAll(null);
		Map<String, Object> map= OrganizationsUtil.getParameters(organizationList);
		String regionId = (String) map.get("regionid");
		System.out.println(regionId);
		String orgId = (String) map.get("orgid");
		System.out.println(orgId);
		FinancialProduct financialProduct=new FinancialProduct();
		financialProduct.setId(id);
		FinancialProduct  findByProductId=financialProductService.selectfaqByfpId(financialProduct);
		Boolean faqReadOnly = (Boolean) map.get("faqReadOnly");
		model.addAttribute("faqReadOnly", faqReadOnly);
		model.addAttribute("findByProductId",findByProductId);
		return Common.BACKGROUND_PATH + "/financialProduct/editFaq";
	}
	
	
	/**
	 * 根据id查询覆盖区域，返回list集合
	 * @param request
	 * @return
	 */
	@RequestMapping("findFGcityByorgid.html")
	public void findFGcityByorgid(HttpServletRequest request,HttpServletResponse response){
		try {
			String orgids = request.getParameter("orgids");
			List<OrganizationEntity> listorgs = new ArrayList<OrganizationEntity>();
			String orgid[] = orgids.split(",");
			for (int i = 0; i < orgid.length; i++) {
//				Long id = Long.valueOf(orgid[i]);
				OrganizationEntity org = organizationsService.findById(orgid[i]);
				listorgs.add(org);
			}
			String json = JSON.toJSONString(listorgs);
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据授信协议id查询对象
	 * @param request
	 */
	@RequestMapping("findSxxyById.html")
	public void findSxxyById(HttpServletRequest request,HttpServletResponse response){
		try {
			String sxxyId = request.getParameter("sxxy"); 
			String json = "";
			if (sxxyId.length()>0) {
				LoanAgreementEntity agreementEntity = loanAgreementService.findById(sxxyId);
				json = JSON.toJSONString(agreementEntity);
			}
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}