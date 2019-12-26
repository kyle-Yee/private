package com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyAttachEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApplyQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.service.LoanApplyQueryService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;

/**
 * LoanApplyQueryController.java
 * @author 严添麟
 * @date 2016年8月10日
 * @caption 贷款申请查询
 */
@Controller
@Scope("prototype")
@RequestMapping("/loanapplyquery/")
public class LoanApplyQueryController extends BaseController {

	@Autowired
	private LoanApplyQueryService loanApplyQueryService;
	//区域码表
	@Autowired
	private ProvinceCitiesService provinceCitiesService; 
	
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/loanapplyquery/list";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * ajax分页动态加载模式
	 * @param gridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("orgId", sessionUser.getOrgid().toString());
		
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "la_id DESC");
		List<LoanApplyQueryEntity> list = loanApplyQueryService.queryListByPage(parameters);
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
	 * 功能: 查看附件信息
	 * @param model
	 * @param request
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "showAttachment.html")
	public String showAttachment(Model model, HttpServletRequest request, String path) {
		model.addAttribute("imgPath", path);
		return Common.BACKGROUND_PATH + "/loanapplyquery/showAttachment";
	}
	
	
	/***
	 * 根据申请id获取贷款申请详细新
	 * @param model 返回对象
	 * @param request 请求
	 * @param id 申请id
	 * @return
	 */
	@RequestMapping("applydetails.html")
	public String detailsUI(Model model, HttpServletRequest request, String id) {
		try{
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("id", id.toString());
			//获取产品以及纳税人信息
			LoanApplyQueryEntity loanApplyQueryEntity = loanApplyQueryService.findById(id);
			//获取贷款申请数据项信息
			List<LoanApplyAttachEntity> loanApplyAttachList = loanApplyQueryService.queryListAttach(parameters).getLoanApplyAttachList();
			
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			
			//覆盖区域拆分/注入
	        List<ProvinceCitiesEntity> cityEntities = new ArrayList<ProvinceCitiesEntity>();
	        String fpOverlayPcIds = loanApplyQueryEntity.getFinancialProduct().getFpOverlayPcIds();
	        String[] fpOverlayPcIdsArray = fpOverlayPcIds.split("#");
	        for (int i = 0; i < fpOverlayPcIdsArray.length; i++) {
	            String fpOverlayPcIdStr = fpOverlayPcIdsArray[i];
	            if(null !=fpOverlayPcIdStr && fpOverlayPcIdStr.length()>0){
	            	String fpOverlayPcId = fpOverlayPcIdStr;
	            	ProvinceCitiesEntity cityEntity = provinceCitiesService.findById(fpOverlayPcId);
	            	cityEntities.add(cityEntity);
	            }
	        }
	        
	        model.addAttribute("loanApplyAttachList", loanApplyAttachList);
	        model.addAttribute("cityEntities", cityEntities);
			model.addAttribute("loanApplyQueryEntity", loanApplyQueryEntity);
			model.addAttribute("loanrecord", loanApplyQueryEntity.getLoanApproveRecEntity());
			return Common.BACKGROUND_PATH + "/loanapplyquery/applydetails";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
}