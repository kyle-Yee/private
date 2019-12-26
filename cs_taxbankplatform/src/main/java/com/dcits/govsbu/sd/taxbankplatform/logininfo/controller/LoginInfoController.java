package com.dcits.govsbu.sd.taxbankplatform.logininfo.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.exception.SystemException;
import com.dcits.govsbu.sd.taxbankplatform.logininfo.model.LoginInfoEntity;
import com.dcits.govsbu.sd.taxbankplatform.logininfo.service.LoginInfoService;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.util.ExportUtils;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;

/**
 * 
 * LoginInfoController.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 登陆信息
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/loginInfo/")
public class LoginInfoController extends BaseController {

	@Autowired
	private LoginInfoService loginInfoService;

	@RequestMapping("listUI.html")
	public String listUI() {
		try
		{
			return Common.BACKGROUND_PATH + "/logininfo/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}

	/**
	 * ajax分页动态加载模式
	 * 
	 * @param gridPager Pager对象
	 * @param response  导出文件请求直接返回文件流，需要HttpServletResponse
	 * @throws Exception
	 * 
	 * modify by zhangmz 2016-07-11 处理导出文件请求
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	// public Object list(String gridPager) throws Exception{
	public Object list(String gridPager, HttpServletResponse response) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("accountName", null);
		}
		
		// add by zhangmz 2016-07-11 处理导出文件请求 begin
		// 判断是否是导出操作
		if(pager.getIsExport()){
			if(pager.getExportAllData()){
				// 导出全部数据
				List<LoginInfoEntity> list = loginInfoService.queryListByPage(parameters);
				ExportUtils.exportAll(response, pager, list);
			}else{
				// 导出当前页数据
				ExportUtils.export(response, pager);
			}
			
			// 导出请求结束，返回null
			return null;
		}			
		// add by zhangmz 2016-07-11 处理导出文件请求 end
		
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "l_id DESC");
		List<LoginInfoEntity> list = loginInfoService.queryListByPage(parameters);
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
		parameters.put("exhibitDatas", list);
		return parameters;		
	}

}
