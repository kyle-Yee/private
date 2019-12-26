package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.datastorage.service.TaxDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.model.DownloadsReportRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportRecServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.downreport.service.impl.DownloadsReportServiceImpl;
import com.dcits.govsbu.sd.taxbankplatform.invokeRecord.service.InvokeRecodeService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.NsryhxxEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.model.SystemEntity;
import com.dcits.govsbu.sd.taxbankplatform.systemconfiguration.service.SystemService;
import com.dcits.govsbu.sd.taxbankplatform.taxpdf.service.TaxPdfNsqdService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.util.PdfGenerator;
/**
 * 
 * @versions:1.0 
 * @filename：DownHNPdf.java
 * @Company:dfwyBank
 * @Created: 2017-5-3下午下午5:48:542:57:43 
 * @department:深圳IT部门  
 * @Copyright Copyright (c) dfwy. All rights reserved.
 * @author Zhongyj
 * @ClassName DownHNPdf
 */
@Service("downHNPdf")
public class DownHNPdf {
	
	private static final Logger logger = LoggerFactory.getLogger(DownHNPdf.class);
	@Autowired
	private LoanApproveService loanApproveService;
	@Autowired
	private DownloadsReportRecServiceImpl downloadsReportRecService;
	@Autowired
	private DownloadsReportServiceImpl downloadsReportService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TaxDataSaveService taxDataSaveService;
	@Autowired
	private InvokeRecodeService invokeRecodeService;
	@Autowired
	private TaxPdfNsqdService taxPdfNsqdService;
	@Autowired
	private PdfGenerator pdfGenerator;
	
	public Map<String, Object> downHNPdf(HttpServletRequest request,String id,String statue,String creatorid,String lasid,String qymc) throws ParseException{
		logger.info("downHNPdf start !");
		Map<String, Object> map = new HashMap<String, Object>();
		
		//**********************判定此id+creatorid的pDF是否正在下载********************************
		String pdfSession = (String) request.getSession().getAttribute("PDFtable#"+id+"#"+creatorid);
		if(pdfSession != null){
			map.put("isExistPdf", true);
			return map;
		}
		//设置此id+createid PDFtable正在下载标记*******************************************************************************	
		request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,"onloading");
		logger.info("downloadsReport start !");
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
			NsryhxxEntity nsryhxxEntity = loanApproveService.findNsryhxx(id);
			String sqxh = loanApproveService.findHNSqxh();
			HandleProceduce handleProceduce = new HandleProceduce();
			HNDataCheckOut checkOut = new HNDataCheckOut();
			SystemEntity SystemEntity = systemService.queryFlagByXtcs("10001");
			String enable = SystemEntity.getEnabled();
			String sqxhhn=loanApproveService.findHNSqxhbynsrsbh(nsryhxxEntity.getNsrsbh());
			boolean checkOutFlag = false ;
			boolean result = false ;
				GetDateFromHN getDateFromHN = new GetDateFromHN();
				String hnDate = null;
				if(sqxhhn!=null){
					//设置时间格式
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
			        Date date = new Date();
					
			        //获取2个月前的最后一天
			        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);	//获取上一个月时间
			        calendar.set(Calendar.DAY_OF_MONTH, 0);		//可取到上个月最后一天时间
			        String lastDay = dateFormat.format(calendar.getTime());	//得到2个月前的最后一天日期
			        Date lastDate = dateFormat.parse(lastDay);	//转成日期格式
			        
			        //获取明天的日期
			        calendar.setTime(date);
			        calendar.add(Calendar.DATE,1);	//把日期往后增加一天.整数往后推,负数往前移动
			        date=calendar.getTime(); 		//这个时间就是日期往后推一天的结果 
			        String dateString = dateFormat.format(date);	//获取明天的日期
			        Date mtDate = dateFormat.parse(dateString);		//转成日期格式
			        
			        //获取数据中的日期
			        String nf = sqxhhn.substring(2, 6);	//获取数据中的年份
					String yf = sqxhhn.substring(6, 8);	//获取数据中的月份
					String rq = sqxhhn.substring(8, 10);	//获取数据中的日				
					String ny = nf + "-" + yf + "-" + rq;			//数据中的年月日
					Date nyDate = dateFormat.parse(ny);	//转成日期格式
					
					//判断数据是否是上一个月到现在的数据
			        if(nyDate.before(mtDate) && nyDate.after(lastDate)){
			        	result = false;	//可用数据
			        }else{
			        	result = true;	//不可用数据
			        }
					
				}
				if(result==true){
				  try {
					//获取湖南接口的数据
					hnDate = getDateFromHN.getDateFromHNJK(id,taxDataSaveService,loanApproveService,invokeRecodeService);
				     } catch (Exception e) {
					   map.put("success", Boolean.FALSE);
					   map.put("message", "下载失败 请联系管理员");
					   e.printStackTrace();
					   logger.error("getDateFromHNJK  获取湖南 接口数据出错 ！");
					   request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
					   map.put("isExistPdf", false);//设置是否下载标记
					   return map;
				     }
				}else {
					hnDate ="success";
				}
				
				
				//获取数据成功后往下执行校验操作
				if("success".equals(hnDate)){
					if("Y".equals(enable)){
						checkOutFlag = checkOut.hNDataCheckOut(id,creatorid,loanApproveService);
					}else {
						checkOutFlag = true;
					}
					if(checkOutFlag){
						//调用存储过程 生成pdf需要的数据项
						try {
							handleProceduce.callProceduc(nsryhxxEntity, taxPdfNsqdService,sqxh);
							//djxh = downloadsReportService.findDjxhById(id);
							String pdfFileName = pdfGenerator.pdfGenerator(request,id,regionId,region_code,org_code);
							handleProceduce.deleteProcedure(nsryhxxEntity, taxPdfNsqdService);
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
								downloadsReportService.insert(downloadsReportEntity2);
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
									downloadsReportRecService.insert(downloadsReportRecEntity);
								}
							}
							map.put("success", Boolean.TRUE);
							map.put("pdfFileName", pdfFileName);
							map.put("message", "下载成功");
						}catch (SQLException e1) {
							e1.printStackTrace();
							logger.error("调用存储过程发生错误");
							try {
								handleProceduce.deleteProcedure(nsryhxxEntity, taxPdfNsqdService);
							} catch (SQLException e2) {
								e2.printStackTrace();
								map.put("success", Boolean.FALSE);
								map.put("message", "下载失败，调用存储过程发生错误");
								logger.error("调用存储过程 后清除数据出错");
								request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
								map.put("isExistPdf", false);//设置是否下载标记
								return map;
							}
							request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
							map.put("isExistPdf", false);//设置是否下载标记
							return map;
						} catch (Exception e) {	
							try {
								handleProceduce.deleteProcedure(nsryhxxEntity, taxPdfNsqdService);
							} catch (SQLException e1) {
								e1.printStackTrace();
								map.put("success", Boolean.FALSE);
								map.put("message", "下载失败，生成pdf过程中发生错误");
								logger.error("调用存储过程 后清除数据出错");
								request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
								map.put("isExistPdf", false);//设置是否下载标记
								return map;
							}
							e.printStackTrace();
							logger.error("生成pdf过程中发生错误");
							request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
							map.put("isExistPdf", false);//设置是否下载标记
							return map;
						}
					} else {
						map.put("success", Boolean.FALSE);
						map.put("message", "下载失败,数据校验失败");
					}
					
				}else{
					//授权编码已经过期
					map.put("success", Boolean.FALSE);
					map.put("message", "下载失败,授权编码已经过期!");
				}
			//删除pdf表格数据
			try {
				handleProceduce.deleteProcedure(nsryhxxEntity, taxPdfNsqdService);
			} catch (SQLException e) {
				e.printStackTrace();
				map.put("success", Boolean.FALSE);
				map.put("message", "下载失败，调用存储过程后清除数据出错");
				logger.error("调用存储过程 后清除数据出错");
				request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
				map.put("isExistPdf", false);//设置是否下载标记
				return map;
			}

		logger.info("downloadsReport end !");
		//清除此id+createid PDFtable正在下载标记
		request.getSession().setAttribute("PDFtable#"+id+"#"+creatorid,null);
		map.put("isExistPdf", false);//设置是否下载标记
		logger.info("downHNPdf end !");
		return map;
	
	}
}
