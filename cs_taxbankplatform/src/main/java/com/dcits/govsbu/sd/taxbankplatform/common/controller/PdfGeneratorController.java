package com.dcits.govsbu.sd.taxbankplatform.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.util.PdfGenerator;

/** 
 * <p>Description: </p>
 * <p>版本:1.0 
 * <p>文件名：com.dcits.platframe.common.service.PdfGeneratorService.java 
 * <p>Copyright: Copyright (c) 2002-2015 Digitalchina CO.,LTD.  All rights reserved.</p>
 * <p>Company:神州数码信息系统有限公司</p>
 * <p>作者: dengwei 
 * <p>创建时间: 2015-12-19下午4:32:42 
 * <p>部门:政府SBU  
 */
@Controller
@Scope("prototype")
@RequestMapping("/print/")
public class PdfGeneratorController extends BaseController {

    
    @Autowired
    private PdfGenerator pdfGenerator;

    /**
     * 描述：生成pdf,并返回文件路径
     * 时间：下午4:33:30
     * 作者：dengwei
     * @param request 请求
     * @return 返回
     * @throws Exception 
     */
 /*   @RequestMapping("json2pdf")
	@ResponseBody 
    public Object json2pdf(HttpServletRequest request,Long id,String djxh,Long regionId) throws Exception {
        // 获取请求数据
        //Map params = request.getTBizData(Map.class);
        //String templateIds = (String) request.getParameter("templateIds");
        //JSONObject pdfJson = JSONObject.parseObject(request.getParameter("pdfJsonData"));
        // 生成pdf
        //String pdfFileName = pdfGenerator.json2pdf(templateIds, JSONObject.toJSONString(pdfJson),request);
        //String pdfFileName = pdfGenerator.pdfGenerator(request,id,regionId);
        // 返回文件路径
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pdfFileName", pdfFileName);
        return resultMap;

    }*/
}
