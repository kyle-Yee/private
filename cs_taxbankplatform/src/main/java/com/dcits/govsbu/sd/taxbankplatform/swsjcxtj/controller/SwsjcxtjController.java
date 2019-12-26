package com.dcits.govsbu.sd.taxbankplatform.swsjcxtj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BMListModel;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BankOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.service.BankManagerService;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.portmager.model.PortmagerEntity;
import com.dcits.govsbu.sd.taxbankplatform.portmager.service.PortmagerService;
import com.dcits.govsbu.sd.taxbankplatform.regions.model.RegionsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.role.model.RoleEntity;
import com.dcits.govsbu.sd.taxbankplatform.role.service.RoleService;
import com.dcits.govsbu.sd.taxbankplatform.swsjcxtj.mapper.SwsjcxtjMapper;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.OrganizationsType;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * RoleController.java
 * 
 * @author liuc
 * @date 2018年6月29日
 * @caption 涉税数据提供情况统计
 */
@Controller
@Scope("prototype")
@RequestMapping("/swsjcxtj/")
public class SwsjcxtjController extends BaseController {
	@Autowired
	public BankManagerService bankManagerService;
	

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private OrganizationsService organizationsService;
	
	@Autowired
	public PortmagerService portMangerService; 
	
	@Autowired 
	private SwsjcxtjMapper  swsjcxtjMapper;

	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil();
			if (request.getParameterMap().containsKey("page")) {
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			Map<String,Object> paramaters = new HashMap<String,Object>();
			UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
			String regionId = sessionUser.getRegionid();
			//if(regionId!=null){
				paramaters.put("regionId", regionId);//regionId
			//}
			List<PortmagerEntity> bankList=portMangerService.queryBank();
			model.addAttribute("bankList", bankList);
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/swsjcxtj/list";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@ResponseBody
	@RequestMapping("list.html")
	public Object list(String gridPager , HttpServletRequest request) throws Exception {
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		// 根据登录信息获取需要筛选的条件
		parameters.putAll(OrganizationsType.getParameters());
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),
				pager.getPageSize(), "t.createtime DESC");
		UserEntity sessionUser = (UserEntity)request.getSession().getAttribute("userSession");
		parameters.put("nsrzgswj", sessionUser.getSsswjDm());
		List<Map<String,Object>>  list = swsjcxtjMapper.queryListByPage(parameters);
		if(null != list && list.size() > 0 ){
			for(int i = 0 ;i < list.size(); i ++ ){
				Map<String,Object> map = list.get(i);
				String qynd = String.valueOf(map.get("nd"));
				String[] sjjh = qynd.split(",");
				map.put("indexNo", i+1);
				if(null != sjjh && sjjh.length > 0){
					map.put("sssqq", sjjh[0]);
				}
				if(null != sjjh && sjjh.length > 1){
					map.put("sssqz", sjjh[1]);
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
		// 列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;

	}

 

	@RequestMapping("cxsjx.html")
	@ResponseBody
	public Object cxsjx(HttpServletRequest request) {
		Map<String,String> dataMap =  new HashMap<String, String>();
		try {
			String sqxh = request.getParameter("sqxh");
			Map<String,String> mapdate = new HashMap<String, String>();
			mapdate.put("sqxh", sqxh);
			List<Map<String, Object>> list = swsjcxtjMapper.getDataRecord(mapdate);
			if(null != list && list.size() > 0 ){
				for(Map<String, Object> map:list){
					dataMap.put(String.valueOf(map.get("gsbdm")), String.valueOf(map.get("gsbmc")));
				}
			}
			 
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return dataMap;
	}
	
	
	@RequestMapping("cxbxx.html")
	@ResponseBody
	public Object cxbxx(HttpServletRequest request) {
		Map<String,Object> dataMap =  new HashMap<String, Object>();
		try {
			Map<String,String> bzd =new  HashMap<String, String>();
			String sqxh = request.getParameter("sqxh");
			String bdm = request.getParameter("ckbdm");
			Map<String,String> mapdate = new HashMap<String, String>();
			mapdate.put("sqxh", sqxh);
			mapdate.put("bdm", bdm);
			if(null != bdm && !"".equals(bdm)){
			String sql = "select ";
			List<Map<String, Object>> list = swsjcxtjMapper.getDataRecord(mapdate);
			if(null != list && list.size() > 0 ){
				for(Map<String, Object> map:list){
					String zd = String.valueOf( map.get("bzddm"));
					if(!"".equals(zd) && !"null".equals(zd)){
						sql += zd+",";
						bzd.put(zd, String.valueOf(map.get("bzdmc")));
					}
				}
				 
			}
			 sql = sql.substring(0, sql.length()-1);
			 sql += " from "+bdm.toLowerCase()+" where sqxh='"+sqxh+"'";
			 
			 List<Map<String, Object>> listData = swsjcxtjMapper.getBData(sql);
			 dataMap.put("dataxx", listData);
			 dataMap.put("bzd", bzd);
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return dataMap;
	}
}
