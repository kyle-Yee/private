/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.message.controller;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.message.model.MessageEntity;
import com.dcits.govsbu.sd.taxbankplatform.message.service.MessageService;
import com.dcits.govsbu.sd.taxbankplatform.messageType.model.MessageType;
import com.dcits.govsbu.sd.taxbankplatform.messageType.service.MessageTypeService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.service.UserService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author 胡宝龙2016-8-22 下午5:03:06
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/message/")
public class MessageController extends BaseController {
	@Autowired
	private MessageTypeService messageTypeService; 
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	HttpSession session = request.getSession();
	UserEntity sessionUser = (UserEntity)session.getAttribute("userSession");
	
	/**
	 * 所有消息列表页跳转
	 */
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			String typeIdStr = request.getParameter("typeId");
			String typeId =null;
			if(null !=typeIdStr && typeIdStr.length()>0){
				typeId = typeIdStr;
			}
			MessageType messageType = messageTypeService.findById(typeId);
			model.addAttribute("messageType", messageType);
			return Common.BACKGROUND_PATH + "/message/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	/**
	 * 列出所有消息
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager, HttpServletRequest request) throws Exception{
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		Map<String, Object> parameters = null;
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		parameters = pager.getParameters();
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "createtime DESC");
		String typeIdStr = request.getParameter("typeId");
		if( null !=typeIdStr && typeIdStr.length()>0 && !typeIdStr.equals("0")){
			parameters.put("messageType", typeIdStr);
		}else{
			parameters.put("messageType", new MessageEntity());
		}
		parameters.put("userId", sessionUser.getId());
		List<MessageEntity> list = messageService.queryListByPage(parameters);
		
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
	 * 根据用户ID查询用户名
	 */
	@RequestMapping("getUserName.html")
	public void getUserName(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding( "utf-8" );
		response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
		response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
		String userIdStr = request.getParameter("userId");
		String userName = null;
		if(null !=userIdStr && userIdStr.length()>0){
			userName = userService.findById(userIdStr).getUserName();
		}
		PrintWriter out = response.getWriter();
		out.print(userName);
		out.flush();
		out.close();
	}
	
	/**
	 * 首页计算未读消息条数
	 */
	@RequestMapping("getMessageCount.html")
	public void getMessageCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding( "utf-8" );
		response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
		response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
		//((ServletRequest) response).setCharacterEncoding( "utf-8" );
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		parameters.put("userId", sessionUser.getId());
		parameters.put("unread", "Y");
		parameters.put("messageType", "XX01");
		List<MessageEntity> sysList = messageService.queryListByPage(parameters);
		int sysCount = sysList.size();
		
		parameters.clear();
		parameters.put("userId", sessionUser.getId());
		parameters.put("unread", "Y");
		parameters.put("messageType", "XX02");
		List<MessageEntity> managerList = messageService.queryListByPage(parameters);
		int managerCount = managerList.size();
		
		parameters.clear();
		parameters.put("userId", sessionUser.getId());
		parameters.put("unread", "Y");
		parameters.put("messageType", "XX03");
		List<MessageEntity> otherList = messageService.queryListByPage(parameters);
		int otherCount = otherList.size();
		
		map.put("sysCount", sysCount);
		map.put("managerCount", managerCount);
		map.put("otherCount", otherCount);
		
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(map));
		out.flush();
		out.close();
	}
	
	/**
	 * 消息详情弹出层
	 */
	@RequestMapping(value = "detail.html", method = RequestMethod.GET)
	public String detail(Model model,String id) {
		try
		{
			MessageEntity messageEntity = messageService.findById(id);
			String userId = messageEntity.getCreatorid();
			Date createTime = messageEntity.getCreatetime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyyyyy-MM-dd HH(hh):mm:ss");
			String createTimeStr = sdf.format(createTime);
			String userName = userService.findById(userId).getUserName();
			MessageType type =messageEntity.getMessageType();
			String typeName = type.getTypeName();
			//更新已读状态
			messageEntity.setUnread("N");
			messageService.update(messageEntity);
			
			model.addAttribute("message", messageEntity);
			model.addAttribute("userName", userName);
			model.addAttribute("createTime", createTimeStr);
			model.addAttribute("typeName", typeName);
			return Common.BACKGROUND_PATH + "/message/detail";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
}
