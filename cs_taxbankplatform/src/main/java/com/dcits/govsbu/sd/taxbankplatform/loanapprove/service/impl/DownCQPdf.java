/**
 * <p>Description: </p>
 * <p>versions:1.0 
 * <p>file name：DownCQPdf.java
 * <p>Company:dfwyBank</p>
 * <p>@author: Zhongyj 
 * <p>Created: 2017-5-4下午上午9:51:502:57:43 
 * <p>department:深圳IT部门  
 * <p>Copyright Copyright (c) dfwy. All rights reserved.</p>
 */
package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportRecServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.PdfGenerator;

/**
 * @versions:1.0 
 * @filename：DownCQPdf.java
 * @Company:dfwyBank
 * @Created: 2017-5-4下午上午9:51:502:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName DownCQPdf
 */
@Service("downCQPdf")
public class DownCQPdf {
	private static final Logger logger = LoggerFactory.getLogger(DownCQPdf.class);
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private DownloadsReportRecServiceImpl downloadsReportRecService;
	@Autowired
	private DownloadsReportServiceImpl downloadsReportService;
	
	/**
	 * @Author Zhongyj
	 * @date 2017-5-4 上午10:54:43
	 * @param request
	 * @param id
	 * @param statue
	 * @param creatorid
	 * @param lasid
	 * @param qymc
	 * @return
	 */
	public Map<String,Object> downCQPdf (HttpServletRequest request,String id,String statue,String creatorid,String lasid,String qymc){
		logger.info("downCQPdf start !");
		Map<String, Object> map = new HashMap<String, Object>();
		//**********************判定此id+creatorid的pDF是否正在下载********************************
		String pdfSession = (String) request.getSession().getAttribute("PDFtable#"+id+"#"+creatorid);
		if(pdfSession != null){
			map.put("isExistPdf", true);
			return map;
		}
		//设置此id+createid PDFtable正在下载标记*******************************************************************************	
		request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,"onloading");
		Map<String, Object> parameters =new HashMap<String, Object>();
		//根据前台传回来的id 查询贷款申请表 获取需要的数据
		//根据id查询 需要校验的数据
		UserEntity userEntity = (UserEntity)request.getSession().getAttribute("userSession");
		String user = null;
		String regionId = null;
		String orgId = null;
		/*
		 * 组织编码
		 */
		String org_code = null;
		/*
		 * 区域编码
		 */
		String region_code = null;
		List<DownloadsReportEntity> downloadsReportEntity = null;
		if(userEntity != null){
			user = userEntity.getUserName();
			orgId = userEntity.getOrgid();
			regionId = userEntity.getRegionid();
			region_code = userEntity.getRegionsEntity().getRegioncode();
			org_code = userEntity.getOrganizationEntity().getOrgcode();
			
		}
		DownloadsReportRecEntity  downloadsReportRecEntity = null;
		String pdfFileName;
		try {
			pdfFileName = pdfGenerator.pdfGenerator(request,id,regionId,region_code,org_code);
			downloadsReportRecEntity = new DownloadsReportRecEntity();
			downloadsReportRecEntity.setEnterpriseName(qymc);
			downloadsReportRecEntity.setDownName(user);
			downloadsReportRecEntity.setLaid(id);
			downloadsReportRecEntity.setDownStatus(statue);
			downloadsReportRecEntity.setOrgId(orgId);
			downloadsReportRecEntity.setLasid(lasid);
			downloadsReportRecEntity.setRegionId(regionId);
			downloadsReportRecEntity.setReportUrl(pdfFileName);
			parameters.put("ids",id);
			parameters.put("statue",statue);
			downloadsReportEntity = downloadsReportService.findByLaId(parameters);
			DownloadsReportEntity downloadsReportEntity2 ;
			String drid = IDGenerate.getZJID("XH");
			if(downloadsReportEntity.size()==0){								
				downloadsReportEntity2 = new DownloadsReportEntity();
				downloadsReportEntity2.setDownStatus(statue);
				downloadsReportEntity2.setLaid(id);
				downloadsReportEntity2.setOrgId(orgId);
				downloadsReportEntity2.setRegionId(regionId);
				downloadsReportEntity2.setEnterpriseName(qymc);
				downloadsReportEntity2.setDownName(user);
				downloadsReportEntity2.setTotaldowns(new Long(1));
				downloadsReportEntity2.setFilename(pdfFileName);
				
				downloadsReportEntity2.setId(drid);
				downloadsReportService.insert(downloadsReportEntity2);
				downloadsReportRecEntity.setId(drid);
				downloadsReportRecService.insert(downloadsReportRecEntity);
			}else{
				//downloadsReportEntity.setDownStatus(statue);
				for (DownloadsReportEntity downloadsReportEntity3 : downloadsReportEntity) {
					downloadsReportEntity3.setLaid(id);
					String orgid = downloadsReportEntity3.getOrgId();
					int days = downloadsReportEntity3.getDays();
					if(orgid.equals(orgId) && days>30){
						Long total = downloadsReportEntity3.getTotaldowns();
						total = total + 1;
						downloadsReportEntity3.setTotaldowns(total);
						downloadsReportService.update(downloadsReportEntity3);
					}
					downloadsReportRecEntity.setId(drid);
					downloadsReportRecService.insert(downloadsReportRecEntity);
				}
			}
			map.put("success", Boolean.TRUE);
			map.put("pdfFileName", pdfFileName);
			map.put("message", "下载成功");
		} catch (IOException e) {
			map.put("success", Boolean.FALSE);
			map.put("message", "下载失败，请联系管理员");
			request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
			map.put("isExistPdf", false);
			e.printStackTrace();
		} catch (Exception e) {
			map.put("success", Boolean.FALSE);
			map.put("message", "下载失败，请联系管理员");
			request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
			map.put("isExistPdf", false);
			e.printStackTrace();
		}		
		request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
		map.put("isExistPdf", false);
		logger.info("downCQPdf end !");
		return map;	
	}
}
