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
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ImgEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ImgService;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/img/")
public class ImgController extends BaseController{

	@Autowired
	private ImgService imgService;
	
	/**
	 * 图片维护页面
	 * @return
	 */
	@RequestMapping(value = "imgUI.html", method = RequestMethod.GET)
	public String imgUI(HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/tbpStatic/img/imgList";
	}
	
	/**
	 * 图片维护列表搜索
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
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), "pi_id ASC");
		
		List<ImgEntity> list = imgService.queryListByPage(parameters);
		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setIndexNo(i+1);
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
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "addUI.html")
	public String addUI(Model model, HttpServletRequest request) {
		List<ImgEntity> imgEntity = imgService.findImgType();
		model.addAttribute("imgType", imgEntity);
		return Common.BACKGROUND_PATH + "/tbpStatic/img/imgForm";
	}
	
	/**
	 * 图片详情信息
	 * @return
	 */
	@RequestMapping(value = "details.html")
	public String details(Model model, HttpServletRequest request, String id) {
		ImgEntity imgEntity = imgService.findById(id);
		model.addAttribute("imgType", imgEntity);
	
		return Common.BACKGROUND_PATH + "/tbpStatic/img/imgDetails";
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
			List<ImgEntity> imgEntityList = imgService.findImgType();
			model.addAttribute("imgType", imgEntityList);
			ImgEntity imgEntity = imgService.findById(id);
			imgEntity.setId(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("imgEntity", imgEntity);
			return Common.BACKGROUND_PATH + "/tbpStatic/img/imgForm";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}	
	}
	
	/**
	 * 数据添加
	 * @param imgEntity
	 * @return
	 */
	@RequestMapping("add.html")
	@ResponseBody
	@Transactional
	public Object add(ImgEntity imgEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			/*//根据webside.properties配置文件中的配置得到上传文件的本地路径
			String path = Showproperties.Getlocal_path();
			//文件保存的本地全路径
			String folderPath = path+"image"+"/"+UploadFileUtil.imageFolder()+"/";
			imgEntity.setImgUrl(folderPath+imgEntity.getImgUrl());*/
			imgEntity.setCityId(Common.getloginUserRegionid());
			imgEntity.setCreatorid(Common.getloginUserId());
			imgEntity.setCreatetime(new Date());
			imgEntity.setUpdatorid(Common.getloginUserId());
			imgEntity.setUpdatetime(new Date());
			//主键
			imgEntity.setId(IDGenerate.getZJID("XH"));
			int result = imgService.insertImg(imgEntity);
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
	 * @param imgEntity
	 * @return
	 */
	@RequestMapping("edit.html")
	@ResponseBody
	@Transactional
	public Object edit(ImgEntity imgEntity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			imgEntity.setUpdatorid(Common.getloginUserId());
			imgEntity.setUpdatetime(new Date());
			int result = imgService.update(imgEntity);
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
	 * order是否已经存在
	 * @return
	 */
	@RequestMapping("ckByOrder.html")
	@ResponseBody
	public Object ckByOrder(Long order,String id, String imgCode) throws AjaxException{
		try
		{
			ImgEntity imgEntity = new ImgEntity();
			imgEntity.setId(id);
			imgEntity.setOrder(order);
			imgEntity.setImgCode(imgCode);
			if(imgCode.equals("LOG") || imgCode.equals("bankImg")){
				//不做检测
				return true;
			}
			int result = imgService.findOrder(imgEntity);
			if(result == 0)
			{
				//数据库中不存在
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
	
	/**
	 * code需要唯一时，检测是否已经存在
	 * @return
	 */
	@RequestMapping("ckByCode.html")
	@ResponseBody
	public Object ckByCode(String imgCode, String id) throws AjaxException{
		try
		{
			if(imgCode.equals("forImg")){
				//不检测
				return true;
			}
			String regionId = Common.getloginUserRegionid();
			int result = imgService.findName(imgCode, id, regionId);
			if(result == 0)
			{
				//数据库中不存在
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
}
