package com.dcits.govsbu.sd.taxbankplatform.user.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.role.model.RoleEntity;
import com.dcits.govsbu.sd.taxbankplatform.role.service.RoleService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.service.UserService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.EndecryptUtils;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsType;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.RandomUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.Producer;

/**
 * 
 * UserController.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 用户
 */
@Controller
@Scope("prototype")
@RequestMapping("/user/")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private OrganizationsService organizationsService;
	@Autowired
	private Producer captchaProducer;

	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
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
			return Common.BACKGROUND_PATH + "/user/list";
		}catch(Exception e)
		{
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
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		// 根据登录信息获取需要筛选的条件
		parameters.putAll(OrganizationsType.getParameters());
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "region_id,org_id,r_id,u_id DESC");
		List<UserEntity> list = userService.queryListByPage(parameters);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndexNo(i+1);
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


	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		try
		{	
			//根据登录信息获取筛选条件参数
			Map<String, Object> parameters = OrganizationsType.getParameters();
			
			//区域以及组织列表
			List<RegionsEntity> regionList = regionsService.queryListAll(parameters);
			model.addAttribute("regionsList", regionList);
			
				List<OrganizationEntity> orgList = organizationsService.queryListAll(parameters);
				model.addAttribute("orgsList", orgList);				
					//角色列表
				List<RoleEntity> list = roleService.findRoleQuery(parameters);
				model.addAttribute("roleList", list);
			List<Map<String,String>> swlist = userService.getSwjdm();
			model.addAttribute("list", swlist);
			//根据登录信息得到页面判断参数
			model.addAttribute("usertype", (parameters.get("usertype")));
			
			return Common.BACKGROUND_PATH + "/user/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}

	}

	@RequestMapping("add.html")
	@ResponseBody
	public Object add(UserEntity userEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			String password = userEntity.getPassword();
			//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			// 加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
			//设置添加用户的密码和加密盐
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			/*// 对用户分组，方便统计，也是一个表内数据隔离标识
			userEntity.setGroupName(StringUtils.isBlank(sessionUser.getGroupName())?
					userEntity.getAccountName():
						sessionUser.getGroupName());*/
			//设置创建者姓名(姓名可能重复，使用ID区别) 
			userEntity.setCreatorId(sessionUser.getId());
			userEntity.setCreatorName(sessionUser.getUserName());
			userEntity.setCreateTime(new Date());
			//设置锁定状态：未锁定；删除状态：未删除
			userEntity.setLocked(0);
			userEntity.setDeleteStatus(0);
			UserInfoEntity userInfo = new UserInfoEntity();
			userEntity.setUserInfo(userInfo);
			int result = userService.insert(userEntity, password);
		
			if(result == 1)
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
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}


	@RequestMapping("editUI.html")
	public Object editUI(Model model, HttpServletRequest request, String id,HttpServletResponse response) {
		try
		{	
			//根据登录信息获取筛选条件参数
			Map<String, Object> parameters = OrganizationsType.getParameters();
			if(("YH0002017010100000000000").equals(id)){
				   response.setContentType("text/html; charset=UTF-8"); //转码
				    PrintWriter out = response.getWriter();
				    out.flush();
				    out.println("<script>");
				    out.println("layer.msg('系统管理员账户不允许进行编辑操作！');");
				    out.println("history.back();");
				    out.println("</script>");
				    return listUI(model, request);

			}else {
				UserEntity userEntity = userService.findById(id);
				PageUtil page = new PageUtil();
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
				
				//区域以及组织列表
				List<RegionsEntity> regionList = regionsService.queryListAll(parameters);
				List<OrganizationEntity> orgList = organizationsService.queryListAll(parameters);
				model.addAttribute("regionsList", regionList);
				model.addAttribute("orgsList", orgList);
				
				//角色列表
				List<RoleEntity> list = roleService.findRoleQuery(parameters);

				model.addAttribute("page", page);
				model.addAttribute("userEntity", userEntity);
				model.addAttribute("roleList", list);
				List<Map<String,String>> swlist = userService.getSwjdm();
				model.addAttribute("list", swlist);
				return Common.BACKGROUND_PATH + "/user/form";
			}
			
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}

	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(UserEntity userEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			//设置创建者姓名
			userEntity.setCreatorName(sessionUser.getCreatorName());
			int result = userService.update(userEntity);
			if(result == 1)
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
	 * 注意user表中的数据在user_info表中需要有记录一一对应，否则导致编辑信息失败
	 * @param userEntity
	 * @return
	 * @throws AjaxException
	 */
	@RequestMapping("editPersonInfo.html")
	@ResponseBody
	public Object updatePerson(UserEntity userEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			//设置创建者姓名
			userEntity.setCreatorName(sessionUser.getCreatorName());
			userEntity.setCreateTime(new Date());//实际上是更新时间
			int result = userService.updatePerson(userEntity);
			if(result == 1)
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

	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] userIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : userIds) {
				list.add(string);
			}
			int cnt = userService.deleteBatchById(list);
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
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}

	@RequestMapping("resetPassword.html")
	@ResponseBody
	public Object resetPassword(UserEntity userEntity){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			//生成随机密码
			String password = RandomUtil.generateString(6);
			logger.info(userEntity.getAccountName() + " : " + password);

			//加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), password, 2);
			//设置添加用户的密码和加密盐
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			if(userEntity.getId() == null)
			{
				user = null;
				user = userService.findByName(userEntity.getAccountName());
				if(user != null)
				{
					userEntity.setId(user.getId());
					userEntity.setUserName(user.getUserName());
					int cnt = userService.updateOnly(userEntity);
					if(cnt > 0)
					{
						result.put("success", true);
						result.put("data", null);
						result.put("message", "新密码:"+password);
					}else
					{
						result.put("success", false);
						result.put("data", null);
						result.put("message", "密码重置失败");
					}
				}else
				{
					result.put("success", false);
					result.put("data", null);
					result.put("message", "账户不存在");
				}
			}else
			{
				int cnt = userService.updateOnly(userEntity);
				if(cnt > 0)
				{
					result.put("success", true);
					result.put("data", null);
					result.put("message", "新密码:"+password);
				}else
				{
					result.put("success", false);
					result.put("data", null);
					result.put("message", "密码重置失败");
				}
			}

		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}

	@RequestMapping("resetPassWithoutAuthc.html")
	@ResponseBody
	public Object resetPassWithoutAuthc(UserEntity userEntity){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			//生成随机密码
			String password = RandomUtil.generateString(6);
			logger.info(userEntity.getAccountName() + " : " + password);

			//加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), password, 2);
			//设置添加用户的密码和加密盐
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			if(userEntity.getId() == null)
			{
				user = null;
				user = userService.findByName(userEntity.getAccountName());
				if(user != null)
				{
					userEntity.setId(user.getId());
					userEntity.setUserName(user.getUserName());
					int cnt = userService.updateOnly(userEntity);
					if(cnt > 0)
					{
						result.put("success", true);
						result.put("data", null);
						result.put("message", "密码重置成功");
					}else
					{
						result.put("success", false);
						result.put("data", null);
						result.put("message", "密码重置失败");
					}
				}else
				{
					result.put("success", false);
					result.put("data", null);
					result.put("message", "账户不存在");
				}
			}else
			{
				int cnt = userService.updateOnly(userEntity);
				if(cnt > 0)
				{
					result.put("success", true);
					result.put("data", null);
					result.put("message", "密码重置成功");
				}else
				{
					result.put("success", false);
					result.put("data", null);
					result.put("message", "密码重置失败");
				}
			}

		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}


	@RequestMapping("infoUI.html")
	public String infoUI(Model model, String id) {
		try
		{
			UserEntity userEntity = userService.findById(id);
			model.addAttribute("userEntity", userEntity);
			return Common.BACKGROUND_PATH + "/user/info";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}

	@RequestMapping("info.html")
	@ResponseBody
	public Object info(UserEntity userEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			int result = userService.update(userEntity);
			if(result == 1)
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


	@RequestMapping("passwordUI.html")
	public String passwordUI(Model model, UserEntity userEntity,HttpServletRequest req, HttpServletResponse rsp) {
		try
		{
			model.addAttribute("userEntity", userEntity);
			
			return Common.BACKGROUND_PATH + "/user/password";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}

	/**
	 * 检测验证码
	 * @return
	 */
	@RequestMapping("ckYzm.html")
	@ResponseBody
	public Object ckYzm(String yzm, HttpServletRequest request) throws AjaxException{
		try
		{
			if(yzm.equals(request.getSession().getAttribute("verification")))
			{
				//验证码正确
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}

	@RequestMapping(value = "password.html", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	@ResponseBody
	public Object password(UserEntity userEntity, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String currentpassword = request.getParameter("currentpassword");

		String verification = request.getParameter("verification");
		if(!verification.equals(request.getSession().getAttribute("verification"))) {
			result.put("success", Boolean.FALSE);
			result.put("data", "verification");
			result.put("message", "验证码输入错误");
			return result;
		}

		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		String cipherpassword = EndecryptUtils.encrytPassword(sessionUser.getAccountName(),
					currentpassword, sessionUser.getCredentialsSalt(), 2);
		if(!cipherpassword.equals(sessionUser.getPassword())) {
			result.put("success", false);
			result.put("data", null);
			result.put("message", "当前密码错误");
			return result;
		}

		if(!sessionUser.getId().equals(userEntity.getId())) {
			result.put("success", false);
			result.put("data", null);
			result.put("message", "只能修改当前用户密码");
			return result;
		}

		try {
			//加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(sessionUser.getAccountName(), userEntity.getPassword(), 2);
			//设置添加用户的密码和加密盐
			userEntity.setUserName(sessionUser.getUserName());
			userEntity.setAccountName(sessionUser.getAccountName());
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			int cnt = userService.updateOnly(userEntity);
			if(cnt > 0) {
				result.put("success", true);
				result.put("data", null);
				result.put("message", "密码修改成功");
			} else {
				result.put("success", false);
				result.put("data", null);
				result.put("message", "密码修改失败");
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}

		return result;
	}

	/**
	 * 验证码
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(value = "captcha.html", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest req, HttpServletResponse rsp)  throws AjaxException{
		ServletOutputStream out = null;
		try {
	  //      HttpSession session = req.getSession();
	        rsp.setDateHeader("Expires", 0);
	        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        rsp.setHeader("Pragma", "no-cache");
	        rsp.setContentType("image/jpeg");
	
	        String capText = captchaProducer.createText();
	        req.getSession().setAttribute("verification", capText);
	
	        BufferedImage image = captchaProducer.createImage(capText);
	        out = rsp.getOutputStream();
	        ImageIO.write(image, "jpg", out);
	        out.flush();
        }catch(IOException e)
		{
			throw new SystemException(e);
		} finally {
            try {
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				throw new SystemException(e);
			}
        }
    }
	
	@RequestMapping("validateAccountName.html")
	@ResponseBody
	public Object validateAccount(String accountName){
		try
		{
			UserEntity userEntity = userService.findByName(accountName);
			if(userEntity == null)
			{
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("yanzhengma.html")
	@ResponseBody
	public Object yanzhengma(HttpServletRequest request){
		try
		{   	String verification = request.getParameter("verification");

		if(verification.equals(request.getSession().getAttribute("verification"))){
				return 1;
			}else
			{
				return 0;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}

}
