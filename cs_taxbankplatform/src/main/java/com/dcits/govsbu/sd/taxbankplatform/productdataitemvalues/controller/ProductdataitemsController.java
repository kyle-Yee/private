package com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dcits.govsbu.sd.taxbankplatform.dtgrid.model.Pager;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.HtmlTag;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitems;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.model.Productdataitemvalues;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.HtmlTagService;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.ProductdataitemsService;
import com.dcits.govsbu.sd.taxbankplatform.productdataitemvalues.service.ProductdataitemvaluesService;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.service.ProvinceCitiesService;
import com.dcits.govsbu.sd.taxbankplatform.regions.service.RegionsService;
import com.dcits.govsbu.sd.taxbankplatform.util.ChineseToEnglish;
import com.dcits.govsbu.sd.taxbankplatform.util.Common;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@Scope("prototype")
@RequestMapping("/productdataitems/")
public class ProductdataitemsController {
	@Autowired
	private ProductdataitemsService productdataitemsService;

	@Autowired
	private ProductdataitemvaluesService productdataitemvaluesService;
	
	@Autowired
	private HtmlTagService htmlTagService;
	@Autowired
	private RegionsService regionsService; //城市
	@Autowired
	private ProvinceCitiesService provinceCitiesService; //区域码表
	@Autowired
	private OrganizationsService organizationsService; //组织表
	/**
	 * 
	 * @param model
	 * @param 显示查询所有数据项列表预操作
	 * @return
	 */
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil();
			Map<String, Object> map= Permission.getParameters();
			Boolean readOnly = (Boolean) map.get("readOnly");
			Boolean regionShow = (Boolean) map.get("regionShow");
			Boolean orgShow = (Boolean) map.get("orgShow");
			model.addAttribute("readOnly", readOnly);
			model.addAttribute("regionShow", regionShow);
			model.addAttribute("orgShow", orgShow);
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			
			model.addAttribute("page", page);
			List<HtmlTag> htmlTags=htmlTagService.select();
			model.addAttribute("htmlTagList",htmlTags);
			return Common.BACKGROUND_PATH + "/productdataitems/list";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * ajax分页动态加载模式
	 * @param 显示查询所有数据项列表
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
		// add by zhangmz 2016-06-30 必须的条件
        if(("KTQY0002017010100000000000").equals(Common.getloginUserRegionid())&&("ZZ0002017010100000000000").equals(Common.getloginUserOrgid())){
        	
			
		}else if (Common.getloginUserRegionid()!=null&&!("").equals(Common.getloginUserRegionid())) {
			 parameters.put("regionid",Common.getloginUserRegionid());
		}
		else{
        parameters.put("regionid",Common.getloginUserRegionid());
        parameters.put("orgid",Common.getloginUserOrgid());
		}

     	String ot=Common.getloginUserOrgotid();
     		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		ArrayList<Productdataitems> list=(ArrayList<Productdataitems>) productdataitemsService.queryListByPage(parameters);
		for(int i = 0;i < list.size();i++){
	
			list.get(i).setOt(ot);
		}

		if(list.size() > 0){
			for(int i = 0;i < list.size();i++){
				list.get(i).setIndexNo(i+1);
			}
		}
		for (Productdataitems productdataitems : list) {
			String fp_regionId=productdataitems.getRegionid();
			String fp_orgId=productdataitems.getOrgid();
			String regionName = regionsService.findById(fp_regionId).getRegionname();
			OrganizationEntity org = organizationsService.findById(fp_orgId);
			String orgName = org.getOrgname();
			productdataitems.setRegionName(regionName);
			productdataitems.setOrgName(orgName);
		}
		
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas",list);
	
		
		return parameters;
	}
	

	/**
	 * 
	 * @param 新增数据项及数据项值预操作
	 * @throws Exception
	 */
	
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		try
		{	
			return Common.BACKGROUND_PATH + "/productdataitems/list";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	/**
	 * 
	 * @param 新增数据项及数据项值
	 * @throws Exception
	 */
	@RequestMapping("add.html")
	@Transactional
	@ResponseBody
	public Object add(Productdataitems productdataitems) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int results=0;
		try
		{   	
	       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    	String pdiName=request.getParameter("pdiName");
	    	String[] pdivName=request.getParameterValues("pdivName");
	    	String htmlTag=request.getParameter("htmlTag.id");
	    	 StringBuffer sb = new StringBuffer();
	         for(int i=0; i<pdivName.length; i++) {
	             if("".equals(pdivName[i])) {
	                 continue;
	             }
	             sb.append(pdivName[i]);
	            
	         }
			//生成 tb_product_data_items 主键ID
			String pdiIdNew = IDGenerate.getZJID("SJXM");
			productdataitems.setId(pdiIdNew);
			if(sb.length()!=0){
				 //值/注入
				//生成主键

		        productdataitems.setPdiName(pdiName);
		        productdataitems.setCreatorid(Common.getloginUserId());
				productdataitems.setCreatetime(new Date());
				productdataitems.setOrgid(Common.getloginUserOrgid());
				productdataitems.setEnabled("Y");
				productdataitems.setUpdatetime(new Date());
				productdataitems.setUpdatorid(Common.getloginUserId());
				productdataitems.setRegionid(Common.getloginUserRegionid());
				productdataitems.setProductditvaluesList(null);
				if(("3").equals(htmlTag)){
				productdataitems.setPdiCode(ChineseToEnglish.getFullSpell(pdiName)+"_cb");
				}else {
					productdataitems.setPdiCode(ChineseToEnglish.getFullSpell(pdiName));
				}
      		    results=productdataitemsService.insertItem(productdataitems);
			    Productdataitemvalues productdataitemvalues=new Productdataitemvalues();
			    
		        for (int i = 0; i < pdivName.length; i++) {
		            if(pdivName[i]==""||("").equals(pdivName)){
		            	continue;
		            
		            }else{
	            	//生成 tb_product_data_item_values 主键ID
				    String pdivId = IDGenerate.getZJID("SJXZ");
				    productdataitemvalues.setId(pdivId);
		            productdataitems.setId(pdiIdNew);
		            productdataitemvalues.setProductdataitems(productdataitems);
		            productdataitemvalues.setCreatorid(Common.getloginUserId());
		            productdataitemvalues.setCreatetime(new Date());
		            productdataitemvalues.setUpdatorid(Common.getloginUserId());
		            productdataitemvalues.setUpdatetime(new Date());	       
		            productdataitemvalues.setPdivSeq(i+1);
		            productdataitemvalues.setPdivName(pdivName[i]);
		            results=productdataitemsService.insertValues(productdataitemvalues);
		            }
		        }
			}else { 
				productdataitems.setPdiName(pdiName);
				productdataitems.setCreatorid(Common.getloginUserId());
				productdataitems.setCreatetime(new Date());
				productdataitems.setOrgid(Common.getloginUserOrgid());
				productdataitems.setEnabled("Y");
				productdataitems.setUpdatetime(new Date());
				productdataitems.setUpdatorid(Common.getloginUserId());
				productdataitems.setRegionid(Common.getloginUserRegionid());
				productdataitems.setProductditvaluesList(null);
				productdataitems.setPdiCode(ChineseToEnglish.getFullSpell(pdiName));
				results=productdataitemsService.insertItem(productdataitems);
			}
	     
			if(results==1)
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
	/**
	 * 
	 * @param 新增数据项值
	 * @throws Exception
	 */
	@RequestMapping(value ="addValue.html")
	@Transactional
	@ResponseBody
	public Object addValue(Productdataitemvalues productdataitemvalues,String pdiId) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		int results=0;
		try
		{   	
	       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	       Map<String, Object> map2 = new HashMap<>();
			map2.put("pdiId",pdiId);
			List<Productdataitemvalues> productdataitemvalues2 = productdataitemvaluesService.selectPdivSeqByPdiId(map2);      
      		        String  pdivName=request.getParameter("pdivName");
      		        Productdataitems productdataitems=new Productdataitems();
      		        productdataitems.setId(pdiId);
      		        //生成 tb_product_data_item_values 主键ID
				    String pdivId = IDGenerate.getZJID("SJXZ");
				    productdataitemvalues.setId(pdivId);
		            productdataitemvalues.setProductdataitems(productdataitems);
		            productdataitemvalues.setCreatorid(Common.getloginUserId());
		            productdataitemvalues.setCreatetime(new Date());
		            productdataitemvalues.setUpdatorid(Common.getloginUserId());
		            productdataitemvalues.setUpdatetime(new Date());
		            productdataitemvalues.setPdivSeq(productdataitemvalues2.get(0).getPdivSeq()+1);
		            productdataitemvalues.setPdivName(pdivName);
		            results=productdataitemsService.insertValues(productdataitemvalues);
	     
			if(results==1)
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
	
	@RequestMapping("addPdiUI.html")
	public String addPdiUI(Model model, HttpServletRequest request) {
		return Common.BACKGROUND_PATH + "/productdataitems/editList";
	}
	
	/**
	 * 
	 * @param 修改数据项及数据项值预操作
	 * @throws Exception
	 */
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try		{ 
			List<Productdataitems> pdivNameList = productdataitemvaluesService.searchPdivNameList(id);
			model.addAttribute("pdivNameList", pdivNameList);
			Productdataitems productdataitems=productdataitemsService.queryListById(id);
			productdataitems.setId(id);
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			model.addAttribute("productDataitems", productdataitems);
			model.addAttribute("id",id);
			return Common.BACKGROUND_PATH + "/productdataitems/editList";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	/**
	 * 
	 * @param 查询需要修改的数据项及数据项值
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "editList.html", method = RequestMethod.POST)
	public Object editList(String gridPager,String id) throws Exception{
		Map<String, Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("pdiId",id);
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize());
		List<Productdataitemvalues> list = productdataitemvaluesService.selectPdivByPdiId(parameters);
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
	 * 
	 * @param 逻辑删除数据项
	 * @throws Exception
	 */
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			int cnt=productdataitemsService.updateenableByID(id);
			if(cnt == 1)
			{
				result.put("success", Boolean.TRUE);
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
	/**
	 * 
	 * @param 删除数据项值
	 * @throws Exception
	 */
	@RequestMapping("deleteValue.html")
	@ResponseBody
	public Object deleteValue(String pdivId){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{   Productdataitemvalues productdataitemvalues=new Productdataitemvalues();
		    productdataitemvalues.setId(pdivId);
			int cnt=productdataitemsService.deleteByPdivId(pdivId);
			if(cnt == 1)
			{
				result.put("success", Boolean.TRUE);
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
	
	/**
	 * 
	 * @param 修改数据项值预操作
	 * @throws Exception
	 */
	@RequestMapping("editValueUI.html")
	public String editValueUI(Model model, HttpServletRequest request, String id) {
		try		{ 
		    Productdataitemvalues productdataitemvalues=productdataitemsService.selectPdivByPdivId(id);
		    model.addAttribute("productdataitemvalues",productdataitemvalues);
			return Common.BACKGROUND_PATH + "/productdataitems/editList";
	
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	/**
	 * 
	 * @param 新增数据项预操作
	 * @throws Exception
	 */
	@RequestMapping("editItemUI.html")
	public String editItemUI(Model model, HttpServletRequest request,HttpServletResponse response ,String pdiId) {
		try		{ 
		     Productdataitems productdataitems= productdataitemsService.selectPdiByPdiId(pdiId);
		     model.addAttribute("productdataitemsEdit", productdataitems);
		    
 		     return  "redirect:/productdataitems/editfrom";
	
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	/**
	 * 
	 * @param 修改数据项值
	 * @throws Exception
	 */
	@RequestMapping("editValue.html")
	@ResponseBody
	public Object editValue(String pdiId,String pdivId,HttpServletResponse response) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		try
		{   		
			//获取登录用户
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			int result;
			
		
				if(pdivId!=null&&!("").equals(pdivId)){
					String pdivName=request.getParameter("pdivName");
					Productdataitemvalues productdataitemvalues=new Productdataitemvalues();
					
					Productdataitems productdataitems=new Productdataitems();
					productdataitems.setUpdatorid(Common.getloginUserId());
					productdataitems.setId(pdiId);
					productdataitemvalues.setPdivName(pdivName);
					productdataitemvalues.setUpdatorid(Common.getloginUserId());
					productdataitemvalues.setId(pdivId);
				    result=productdataitemsService.updatePdivByPdivID(productdataitemvalues);
					}
					else {
						String pdiName=request.getParameter("pdiName");
						Productdataitems productdataitems=new Productdataitems();
						productdataitems.setId(pdiId);
						productdataitems.setPdiName(pdiName);
						productdataitems.setPdiCode(ChineseToEnglish.getFullSpell(pdiName));
						productdataitems.setUpdatorid(Common.getloginUserId());
					    result=productdataitemsService.updatePdiByPdiID(productdataitems);
					}
					
					if(result>0)
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
	 * 判断数据项是否唯一
	 */
	@RequestMapping("checkpdiName.html")
	public void checkpdiName(HttpServletRequest request,HttpServletResponse response,String pdiName) throws Exception {
		try {
			request.setCharacterEncoding( "utf-8" );
			//((ServletRequest) response).setCharacterEncoding( "utf-8" );
			response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
			response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
			Map<String, Object> map2 = new HashMap<>();
			map2.put("pdiName", pdiName);
			map2.put("orgid",Common.getloginUserOrgid());
			map2.put("regionid",Common.getloginUserRegionid());
			List<Productdataitems> productdataitems = productdataitemsService.queryListByPage(map2);
			Boolean notExist = true;
			if(productdataitems.size()>0){
				notExist =false;	
			}
			PrintWriter out = response.getWriter();
			out.print(notExist);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new AjaxException(e);
		}

	}

	
	/**
	 * 判断数据项值是否唯一
	 */
	@RequestMapping("checkpdivName.html")
	public void checkpdivName(HttpServletRequest request,HttpServletResponse response,String pdiId,String pdivName) throws Exception {
		try {
			request.setCharacterEncoding( "utf-8" );
			//((ServletRequest) response).setCharacterEncoding( "utf-8" );
			response.setCharacterEncoding("UTF-8");//设置响应流的编码方式
			response.setHeader("ContentType","text/html;charset=UTF-8");//设置浏览器的解码方式
			Map<String, Object> map2 = new HashMap<>();
			map2.put("pdivName", pdivName);
			map2.put("pdiId",pdiId);
			List<Productdataitemvalues> productdataitems = productdataitemvaluesService.selectPdivByPdiId(map2);
			Boolean notExist = true;
			if(productdataitems.size()>0){
				notExist =false;
				/*map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "数据已经存在,请重新输入");*/
			}/*else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "数据不存在");
			}*/
			PrintWriter out = response.getWriter();
			out.print(notExist);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new AjaxException(e);
		}

	}

}
