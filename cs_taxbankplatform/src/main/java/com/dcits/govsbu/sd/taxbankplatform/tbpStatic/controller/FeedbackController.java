package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.DialogEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.FeedbackEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.FeedbackService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/feedback/")
public class FeedbackController extends BaseController {

	
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 反馈建议页面
	 * @return
	 */
	@RequestMapping(value = "feedbackUI.html", method = RequestMethod.GET)
	public String feedbackUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/feedback/feedbackList";
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "addUI.html", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/feedback/feedbackForm";
	}
	
	/**
	 * 编辑页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			FeedbackEntity feedbackEntity = feedbackService.findById("1");
			feedbackEntity.setId(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("feedbackEntity", feedbackEntity);
			
			//判断是否可以回复
			if(feedbackEntity.getStatus()== 2){//已回答，尚未追问
				model.addAttribute("canAnswer", 'N');
			}else{//已追问
				model.addAttribute("canAnswer", 'Y');
			}
			//对话列表
			List<DialogEntity> dialogList = feedbackService.dialogList(id);
			for (DialogEntity dialogEntity : dialogList) {
				String id2 = dialogEntity.getCreatorid();
				String createName;
				if(dialogEntity.getType() == 1){//回答
					createName = feedbackService.getNameByAdminId(id2);
				}else{//追问
					createName = feedbackService.getNameByUserId(id2);
				}
				dialogEntity.setCreateName(createName);
			}
			
			model.addAttribute("dialogList", dialogList);
			
			return Common.BACKGROUND_PATH + "/tbpStatic/feedback/feedbackForm";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 反馈建议列表搜索
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager){
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("cityId", Common.getloginUserRegionid());
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "f_id DESC");
		
		List<FeedbackEntity> list = feedbackService.queryListByPage(parameters);
		for (int j = 0; j < list.size(); j++) {
			list.get(j).setIndexNo(j+1);
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
	 * 查看详情
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("details.html")
	public String details(Model model, HttpServletRequest request, String id) throws AjaxException {
		try
		{
			FeedbackEntity feedbackEntity = feedbackService.findById(id);
//			List<DialogEntity> dialogList = feedbackService.dialogList(id.intValue());
			model.addAttribute("feedbackEntity", feedbackEntity);
//			model.addAttribute("dialogList", dialogList);
			return Common.BACKGROUND_PATH + "/tbpStatic/feedback/feedbackDetails";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 数据添加
	 * @param feedbackEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(FeedbackEntity feedbackEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			String fId = IDGenerate.getZJID("XH");
			feedbackEntity.setId(fId);
			feedbackEntity.setCityId(Common.getloginUserRegionid());
			feedbackEntity.setCreatorid(Common.getloginUserId());
			feedbackEntity.setCreatetime(new Date());
			feedbackEntity.setUpdatorid(Common.getloginUserId());
			feedbackEntity.setUpdatetime(new Date());
			int result = feedbackService.insert(feedbackEntity);
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
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	/**
	 *数据编辑
	 * @param feedbackEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(FeedbackEntity feedbackEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			feedbackEntity.setUpdatorid(Common.getloginUserId());
			feedbackEntity.setUpdatetime(new Date());
			feedbackEntity.setLasttime(new Date());//最后回复时间
			feedbackEntity.setStatus(2);//已回复
//			int result = feedbackService.update(feedbackEntity);
			
			int result = feedbackService.replyFeedback(feedbackEntity);
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
	
	
	
	
}
