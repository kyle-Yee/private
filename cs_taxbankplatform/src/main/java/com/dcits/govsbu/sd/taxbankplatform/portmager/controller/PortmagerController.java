package com.dcits.govsbu.sd.taxbankplatform.portmager.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.XlztEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.service.PortmagerService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * @versions:1.0 
 * @filename：PortmagerController.java
 * @Company:dfwyBank
 * @Created: 2017-8-8下午下午2:42:002:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName PortmagerController
 */
@Controller
@Scope("prototype")
@RequestMapping("/portManager/")
public class PortmagerController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(PortmagerController.class);
	
	@Autowired
	private PortmagerService portmagerService;
	/**
	 * @Author Zhongyj
	 * @date 2017-8-8 下午2:42:21
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request){
		logger.info("PortmagerController listUI start !");
		return Common.BACKGROUND_PATH + "/portManager/list";
		
	}
	/**
	 * 
	 * @Author Zhongyj
	 * @date 2017-8-8 下午2:42:33
	 * @param gridPager
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("list.html")
	public Object list(HttpServletRequest request) throws Exception{
		logger.info("PortmagerController list start !");
		
		
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		// 映射Pager对象
		String gridPager = request.getParameter("gridPager");
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		Map<String, Object> searchMap =null;
		searchMap = pager.getParameters();
		String searchKey =null;
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		String regionId = sessionUser.getRegionid();
		Map<String,Object> paramaters = new HashMap<String,Object>();
		if(regionId!=null){
			paramaters.put("regionId", regionId);
		}
		if(searchMap.containsKey("searchKey")){
			searchKey=(String) searchMap.get("searchKey");
			paramaters.put("searchKey", searchKey);
		}
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "id DESC");
	
		List<PortmagerEntity> list = portmagerService.queryListByPage(paramaters);
		
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
		
		logger.info("PortmagerController list end !");
		
		return parameters;
		
	}
	/**
	 * @Author Zhongyj
	 * @date 2017-8-8 下午2:42:38
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		
		logger.info("PortmagerController addUI start !");
		
		try
		{	
			List <OrganizationEntity> bankNameList = new ArrayList<OrganizationEntity>();
			
			//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			
			String regionId = sessionUser.getRegionid();
			bankNameList = portmagerService.findOrgNameList(regionId);
			
			//PortManagerEntity portManagerEntity = portmagerService.findById(id);
			
			model.addAttribute("bankNameList", bankNameList);
			
			logger.info("PortmagerController addUI end !");
			
			return Common.BACKGROUND_PATH + "/portManager/form";
			
		}catch(Exception e)
		
		{
			throw new AjaxException(e);
		}		
	}
	
	/**
	 * @Author Zhongyj
	 * @date 2017-8-8 下午2:42:46
	 * @param regionclassEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	public Object add(PortmagerEntity portmagerEntity) {
		
		logger.info("PortmagerController add start !");
		
		//获取登录用户
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		
		String userName = sessionUser.getUserName();
		
		String regionId = sessionUser.getRegionid();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if("KTQY0002017010100000000000".equals(regionId)){
			
			map.put("success", Boolean.TRUE);
			
			map.put("data", null);
			
			map.put("message", "添加失败，您没有该操作的权限");
			
		} else{
			
			portmagerEntity.setId(IDGenerate.getZJID("XH"));
			
			portmagerEntity.setCjr(userName);
			
			int result;
			
			try {
				
				if(portmagerEntity!=null){
					
					String yhmc = portmagerEntity.getYhmc();
					
					/***根据银行名称去查询数据库tb_bank_port_manage 表中是否有开通的专线 如果有取最新的一条（一般只有一条数据 如果同时存在两条已经开通的数据是有错误数据的 但是为了防止错误数据的存在而报异常 这里用list接收取最新的一条判断）***/
					List<PortmagerEntity> list= portmagerService.findByYhmc(yhmc);
					
					if(list!=null && list.size()>0){
						
						/*PortmagerEntity entity = list.get(0);
					
					if(entity != null){
						
						String enable = entity.getXlzt();
						
						if("xlzt02".equals(enable)){
							
							map.put("success", Boolean.TRUE);
							
							map.put("data", null);
							
							map.put("message", "银行已有开通的端口");
							
						}else{
							
							result = portmagerService.insert(portmagerEntity);
							
							if(result == 1)
								
							{
								map.put("success", Boolean.TRUE);
								
								map.put("data", null);
								
								map.put("message", "添加成功");
								
							}else {
								map.put("success", Boolean.FALSE);
								
								map.put("data", null);
								
								map.put("message", "添加失败");
								
							}
							
						}
						
					}else{
						
						result = portmagerService.insert(portmagerEntity);
						
						if(result == 1)
							
						{
							map.put("success", Boolean.TRUE);
							
							map.put("data", null);
							
							map.put("message", "添加成功");
							
						}else {
							map.put("success", Boolean.FALSE);
							
							map.put("data", null);
							
							map.put("message", "添加失败");
							
						}
						
					}*/
						
						map.put("success", Boolean.TRUE);
						
						map.put("data", null);
						
						map.put("message", "添加失败，该银行已经分配了专线和接口");
						
					} else {
						
						result = portmagerService.insert(portmagerEntity);
						
						if(result == 1)
							
						{
							map.put("success", Boolean.TRUE);
							
							map.put("data", null);
							
							map.put("message", "添加成功");
							
						}else {
							map.put("success", Boolean.FALSE);
							
							map.put("data", null);
							
							map.put("message", "添加失败");
							
						}
						
					}
					
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				map.put("success", Boolean.FALSE);
				
				map.put("data", null);
				
				map.put("message", "添加失败");
				
				logger.info("PortmagerController add end !");
				
				return map;
		}
		
		}
		
		logger.info("PortmagerController add end !");
		
		return map;
	}
	/**
	 * @Author Zhongyj
	 * @date 2017-8-8 下午2:42:54
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		
		logger.info("PortmagerController editUI start !");
		
		try
		{	
			List <OrganizationEntity> bankNameList = new ArrayList<OrganizationEntity>();
			
			//获取登录用户
			
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			
			String regionId = sessionUser.getRegionid();
			
			bankNameList = portmagerService.findOrgNameList(regionId);
			
			PortmagerEntity portManagerEntity = portmagerService.findById(id);
			
			List<XlztEntity> xlztList = portmagerService.findXlztList();
			
			PageUtil page = new PageUtil();
			
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			
			page.setOrderByColumn(request.getParameter("sidx"));
			
			page.setOrderByType(request.getParameter("sord"));
			
			model.addAttribute("page", page);
			
			model.addAttribute("portManagerEntity", portManagerEntity);
			
			model.addAttribute("xlztList", xlztList);
			
			model.addAttribute("bankNameList", bankNameList);
			
			logger.info("PortmagerController editUI end !");
			
			return Common.BACKGROUND_PATH + "/portManager/form";
			
		}catch(Exception e)
		
		{
			throw new AjaxException(e);
		}
		
	}
	/**
	 * 
	 * @Author Zhongyj
	 * @date 2017-8-18 下午1:01:12
	 * @param regionclassEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("edit.html")
	public Object update(PortmagerEntity portmagerEntity) {
		Map<String, Object> map = new HashMap<String, Object>();
		//数据更新
		int result;
		try {
			
			//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			
			String userName = sessionUser.getUserName();
			
			portmagerEntity.setCjr(userName);
			
			result = portmagerService.updateRecord(portmagerEntity);
			
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
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "编辑失败");
			return map;
		}
		return map;
	}
	
	/**
	 * @Author Zhongyj
	 * @date 2017-8-8 下午2:43:26
	 * @param request
	 * @return
	 */
	@RequestMapping("update.html")
	public String updatsBankPortStatus(HttpServletRequest request) {
		
		logger.info("PortmagerController updatsBankPortStatus start !");
		
		try
		
		{	
			
			String xlzt = request.getParameter("xlzt");
			
			String bankCode = request.getParameter("bankCode");
			
			String bankCodeArr[]=bankCode.split(",");
			
			List<String> bankCodelist = Arrays.asList(bankCodeArr); 
			
			
			Map<String,Object> paramaters = new HashMap<String,Object>();
			
			paramaters.put("bankCodelist", bankCodelist);
			
			paramaters.put("xlzt", xlzt);
			
			portmagerService.update(paramaters);
			
			logger.info("PortmagerController updatsBankPortStatus end !");
			
			return Common.BACKGROUND_PATH + "/portManager/list";
			
		}catch(Exception e)
		
		{
			throw new AjaxException(e);
		}		
	}
	
	
	/**
	 * 
	 * @Author Zhongyj
	 * @date 2017-8-3 上午10:20:50
	 * @param regionclassEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findOrgCode.html")
	public Object findOrgCode(HttpServletRequest request) {
		
		logger.info("PortmagerController findOrgCode start !");
		
		String orgName = request.getParameter("orgName");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			String orgCode = portmagerService.findOrgCodeByOrgName(orgName);
			
			map.put("success", Boolean.TRUE);
			
			map.put("data", orgCode);
			
			map.put("message", "获取组织编码成功");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			map.put("success", Boolean.FALSE);
			
			map.put("data", null);
			
			map.put("message", "获取组织编码失败");
			
			logger.info("PortmagerController findOrgCode end !");
			
			return map;
		}
		
		logger.info("PortmagerController findOrgCode end !");
		
		return map;
	}
	
	
	
	/**
	 * 
	 * @Author Zhongyj
	 * @date 2017-8-3 上午10:20:50
	 * @param regionclassEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("testPort.html")
	public Object testPort(HttpServletRequest request) {
		
		logger.info("PortmagerController testPort start !");
		
		String id = request.getParameter("id");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			PortmagerEntity portmagerEntity = portmagerService.findById(id);
			
			boolean flag = false;
			
			if(portmagerEntity!=null){
				
				String xlzt = portmagerEntity.getXlzt();
				
				if("xlzt02".equals(xlzt)){
					
					String ip = portmagerEntity.getYhfwqipdz();
					
					String port = portmagerEntity.getYhdkh();
					
					flag =	isHostConnectable(ip,Integer.valueOf(port).intValue());
				}
			}
		    if(flag){
		    	
		    	map.put("success", Boolean.TRUE);
		    	
		    	map.put("data", flag);
		    	
		    	map.put("message", "端口测试成功");
		    	
		    }else{
		    	
		    	map.put("success", Boolean.TRUE);
		    	
		    	map.put("data", flag);
		    	
		    	map.put("message", "端口测试失败");
		    	
		    }
		} catch (Exception e) {
			
			e.printStackTrace();
			
			map.put("success", Boolean.FALSE);
			
			map.put("data", null);
			
			map.put("message", "端口测试失败");
			
			logger.info("PortmagerController testPort end !");
			
			return map;
		}
		
		logger.info("PortmagerController testPort end !");
		
		return map;
	}
	
	/**
	 * 更具输入的端口和ip 测试目标主机是否连通
	 * @Author Zhongyj
	 * @date 2017-8-3 下午4:00:13
	 * @param host
	 * @param port
	 * @return
	 */
	public static boolean isHostConnectable(String host, int port) {
		
		logger.info("PortmagerController isHostConnectable start !");
		
        Socket socket = new Socket();
        
        try {
        	
            socket.connect(new InetSocketAddress(host, port));
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            
            return false;
            
        } finally {
        	
            try {
            	
                socket.close();
                
            } catch (IOException e) {
            	
                e.printStackTrace();
            }
        }
        
        logger.info("PortmagerController isHostConnectable end !");
        
        return true;
    }
	
	
}
