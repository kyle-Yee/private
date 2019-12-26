package com.dcits.govsbu.sd.taxbankplatform.loanapprove.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardDetailEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanCardQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.TotalDataEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveCardService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * LoanApproveCardController.java
 * @date 2017年6月7日09:32:21
 * @caption 信用卡申请(初审/终审查看)
 */
@Controller
@Scope("prototype")
@RequestMapping("/loancard/")
public class LoanApproveCardController extends BaseController {
	
	@Autowired
	private LoanApproveCardService loanApproveCardService;
	
	/**
	 * 跳转到信用卡信贷审批界面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		try{
			//是否有返回标记
			String returnFlag = request.getParameter("returnFlag");
			if(returnFlag != null && returnFlag.equals("returnPage")){
				request.getSession().setAttribute("returnFlag", Boolean.TRUE);
			}
			
			//表头统计
			String orgid = sessionUser.getOrgid();
			Map<String,Object> parameters = new HashMap<String, Object>();
			parameters.put("orgid", orgid);
			parameters.put("pt_dm", 2);
			TotalDataEntity totalDataEntity = loanApproveCardService.queryTotalData(parameters);
			model.addAttribute("totalDataEntity",totalDataEntity);
			return Common.BACKGROUND_PATH + "/loancard/cardlist";
		}catch(Exception e){
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * 信用卡业务审批界面表单数据分页获取
	 * @param gridPager
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(Model model) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		String gridPager;
		Object o = request.getSession().getAttribute("returnFlag");
		if(o != null){
			Boolean returnFlag = (Boolean)o;
			if(returnFlag){
				gridPager = (String) request.getSession().getAttribute("returnGridPager"); //点击返回
				request.getSession().setAttribute("returnFlag", Boolean.FALSE);
			}else{
				gridPager = request.getParameter("gridPager");;//点击菜单、搜索
			}
		}else{
			gridPager = request.getParameter("gridPager");;//点击菜单、搜索
		}
			
		Map<String, Object> parameters = null;
		// 映射Pager, ApplyStatus对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("orgId", sessionUser.getOrgid().toString());
		parameters.put("pt_dm", 2);//信用卡
		// 设置分页，page里面包含了分页信息
		
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "");
		List<LoanCardQueryEntity> list = loanApproveCardService.queryListByPage(parameters);
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
	 * 跳转到初审详细信息页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toChuShenDetail.html")
	public ModelAndView toChuShenDetail(String gridPager,ModelAndView mv,HttpServletRequest request,Long id){
		mv.setViewName(Common.BACKGROUND_PATH+"/loancard/shouLiDetail");
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("la_id",id);
		LoanCardDetailEntity cardDetail = loanApproveCardService.loanCardDetail(parameters);
		
		request.getSession().setAttribute("returnGridPager", gridPager);
		mv.addObject("cardDetail", cardDetail);//详情
		return mv;
	}
	
	/**
	 * 跳转到终审详情页
	 * @param mv
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("toZhongShenDetail.html")
	public ModelAndView toZhongShenDetail(String gridPager,ModelAndView mv,HttpServletRequest request,Long id){
		mv.setViewName(Common.BACKGROUND_PATH+"/loancard/shouXinDetail");
		
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("la_id",id);
		LoanCardDetailEntity cardDetail = loanApproveCardService.loanCardDetail(parameters);
		if(cardDetail == null){
			return mv;
		}
		
		/*
		 * 取出授信记录单
		 * 修改为从信用卡申请表获取数据
		 * */
//		List<LoanApproveRecEntity> recList = cardDetail.getRecList();
//		if(recList != null && recList.size() > 0){
//			for (LoanApproveRecEntity loanApproveRecEntity : recList) {
//				Integer las_id = loanApproveRecEntity.getLas_id();
//				if(las_id != null && las_id == 3){//授信通过
//					loanApproveRecEntity.setPassFlag("Y");
//					mv.addObject("recDetail", loanApproveRecEntity);
//					break;
//				}else if(las_id != null && las_id == 4){//授信不通过
//					loanApproveRecEntity.setPassFlag("N");
//					mv.addObject("recDetail", loanApproveRecEntity);
//					break;
//				}
//			}
//		}
		request.getSession().setAttribute("returnGridPager", gridPager);
		mv.addObject("cardDetail", cardDetail);//详情
		return mv;
	}
	
	
	
	
	
	
	
	
}
