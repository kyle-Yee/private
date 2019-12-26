package com.dcits.govsbu.sd.taxbankplatform.dkxx.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.common.StringHelper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Bankfinal;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Dksqxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.RegisterEntity;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.BankfinalService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.BankfinalendService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.LoanApplyDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.RegisterService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.SyptbankApplyService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.model.FinancialProduct;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEndEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveQueryService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.service.UserService;


/**
 * 
     * Title: LoanBankApproveController.java    
     * Description: 任务调度录入申请表单 ，审批记录，审批结果，贷款完成表
     * @author yaofang       
     * @created 2017-2-27 下午3:13:52
 */
@Controller
@Scope("prototype")
public class LoanBankApproveController extends BaseController {
     
	//查询(初审/终审)信息列表/详细

@Autowired
private LoanApplyDataSaveService loanApplyDataSaveService;
@Autowired
private BankfinalendService bankfinalendService;
@Autowired 
private BankfinalService bankfinalService;
@Autowired
private LoanApproveQueryService loanApproveQueryService;
//录入审批(记录/结果)
	@Autowired
private LoanApproveService loanApproveService;
@Autowired
private SyptbankApplyService syptbankApplyService;
@Autowired
private RegisterService registerService;
@Autowired
private OrganizationsService organizationsService;
@Autowired
private UserService userService;
@Autowired
private FinancialProductService financialProductService;

public  void  invoking(){
	try {
		
		//automaticFinalrec();
		//automaticEnd();
		//changeStatues();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
}
/**
 * 
     * @discription 任务调度录入申请表单 ，审批记录，审批结果，贷款完成表
     * @author yaofang       
     * @created 2017-2-27 下午3:15:51
 */

public void automaticFinalrec() 
{
	Map<String, Object> map = new HashMap<String, Object>();
	int results=0;
	try
	{   	

    	
    	List<Dksqxx> list=loanApplyDataSaveService.findAllbylfId();
/*	    List<Bankfinalend> bankfinalendList=bankfinalendService.queryListAll(parameters);
	    List<Bankfinal> bankfinalList=bankfinalService.queryListAll(parameters)*/;
	    if(list != null && list.size()>0){
	    	for (Dksqxx dksqxx : list) {
	    		if(dksqxx.getNsryhxxNsrsbh()!=null && dksqxx.getNsryhxxNsrsbh()!= ""){
	    			Map<String, Object> map1 = new HashMap<String, Object>();
	    			if(("Y").equals(dksqxx.getIsnsrsbh())){
	    			 map1.put("isy", "ynsrsbh");
	    			}else if(("N").equals(dksqxx.getIsnsrsbh())) {
	    		     map1.put("isy", "nnsrsbh");	
					}
	    			map1.put("nsrsbh", dksqxx.getNsryhxxNsrsbh());
	    			List<RegisterEntity> registerEntities=registerService.UserList(map1);
	    			
	    			if(registerEntities != null && registerEntities.size()>0){
	    				RegisterEntity registerEntity=registerEntities.get(0);
	    		//		double s=Math.ceil(Double.parseDouble(dksqxx.getBankfinal().getLarCreditQuota()));
			    	  //System.out.println( Math.ceil(Double.parseDouble(dksqxx.getBankfinal().getLarCreditQuota())));    
			    		String lfId=dksqxx.getLfId();
			    		String bankfpId=dksqxx.getFpId();
			    		String channelid=dksqxx.getChannelid();
			    		if((lfId!=null || !("").equals(lfId) )&&( channelid!=null || !("").equals(channelid))){
			    		Map<String, Object> parameter = new HashMap<String, Object>();
			    		if(("LB").equals(channelid)){
			    			parameter.put("orgcode", "SZRCB");
			    					
			    		}
			    		
			    		else {
			    			parameter.put("orgcode", channelid.toUpperCase());
						}
		    			
		    			List<OrganizationEntity> organizationEntities=organizationsService.queryListAll(parameter);
		    			if(organizationEntities != null && organizationEntities.size()>0){
		    				
		    		
		    			Map<String, Object> para = new HashMap<String, Object>();
		    			para.put("orgid", organizationEntities.get(0).getId());
		    			if(userService.queryListByPage(para) != null && userService.queryListByPage(para).size() > 0){
		    				UserEntity userEntity=   userService.queryListByPage(para).get(0);
		    			if(userEntity!=null){
		    				
		    			
		    			String Manager=userEntity.getId();
			    		List<Syptbankapply> syptbankapplies=syptbankApplyService.findBylaid(lfId,channelid);
			    		List<Syptbankapply> syptbankapplies1=syptbankApplyService.findByfpid(bankfpId,channelid);
			    		if(syptbankapplies1.size()!=0&&syptbankapplies1.get(0).getSyptfpId()!=null){
			    		FinancialProduct financialProduct=  financialProductService.findById(syptbankapplies1.get(0).getSyptfpId());
			    		if(financialProduct!=null){
			    			String fpid=financialProduct.getId().toString();
			    		
			    		
			    		//对照表里存在数据
			    		if( syptbankapplies !=null && syptbankapplies.size()>0){
			    			if( syptbankapplies.get(0).getTempfinalId()==null||("").equals(syptbankapplies.get(0).getTempfinalId())){
			    				if( ("N").equals(dksqxx.getBankfinal().getEnabled())&&dksqxx.getBankfinal().getNsrsbh()!=null){

				    				   if(("00").equals(dksqxx.getBankfinal().getLarPdjg())){
					    				 /*  //构造申请信息记录
					    				   LoanApproveRecEntity loanApproveRecEntity=new LoanApproveRecEntity();
					    				   loanApproveRecEntity.setRegion_id(Long.parseLong(registerEntity.getRegionId()));
					    				   loanApproveRecEntity.setFp_id(fpid);
					    				   loanApproveRecEntity.setLa_id( Integer.parseInt( String.valueOf(syptbankapplies.get(0).getLaId())));
					    				   loanApproveRecEntity.setLas_id(1);
					    				   loanApproveRecEntity.setLac_id(0);
					    				   loanApproveRecEntity.setLar_credit_quota(0);
					    				   loanApproveRecEntity.setLar_loan_deadline(0);
					    				   loanApproveRecEntity.setLar_begin(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity.setLar_end(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity.setLar_rate("0");
					    				   loanApproveRecEntity.setLar_isoverlay_area(0);
					    				   loanApproveRecEntity.setLar_opinion("企业已提交贷款申请，请银行尽快受理。");
					    				   loanApproveRecEntity.setCreatorid(Long.parseLong(registerEntity.getUserId()));
					    				   loanApproveRecEntity.setCreatetime(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity.setUpdatorid(Long.parseLong(registerEntity.getUserId()));
					    				   loanApproveRecEntity.setUpdatetime(dksqxx.getLaApplyTime());
					    				   loanApproveQueryService.insertRec3(loanApproveRecEntity);*/
					    				   //构造受理信息
					    				   LoanApproveRecEntity loanApproveRecEntity2=new LoanApproveRecEntity();
					    				   loanApproveRecEntity2.setRegion_id(registerEntity.getRegionId());
					    				   loanApproveRecEntity2.setFp_id(fpid);
					    				   loanApproveRecEntity2.setLa_id(String.valueOf(syptbankapplies.get(0).getLaId()));
					    				   loanApproveRecEntity2.setLas_id(String.valueOf(2));
					    				   loanApproveRecEntity2.setLac_id(String.valueOf(2));
					    				   loanApproveRecEntity2.setLar_credit_quota("0");
					    				   loanApproveRecEntity2.setLar_loan_deadline(0);
					    				   loanApproveRecEntity2.setLar_begin(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity2.setLar_end(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity2.setLar_rate("0");
					    				   loanApproveRecEntity2.setLar_isoverlay_area(0);
					    				   loanApproveRecEntity2.setLar_opinion("同意受理");
					    				   loanApproveRecEntity2.setCreatorid(Manager);
					    				   loanApproveRecEntity2.setCreatetime(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity2.setUpdatorid(Manager);
					    				   loanApproveRecEntity2.setUpdatetime(dksqxx.getLaApplyTime());
					    				   loanApproveQueryService.insertRec3(loanApproveRecEntity2);
					    				   //构造授信数据
					    				   LoanApproveFinalEntity loanApproveFinalEntity=new LoanApproveFinalEntity();
					    				   loanApproveFinalEntity.setFp_id(String.valueOf(fpid));
					    				   loanApproveFinalEntity.setRegion_id(registerEntity.getRegionId());
					    				   loanApproveFinalEntity.setLa_id(String.valueOf(syptbankapplies.get(0).getLaId()));
					    				   loanApproveFinalEntity.setLac_id(String.valueOf(3));
					    				   loanApproveFinalEntity.setLar_opinion("同意");
					    				   loanApproveFinalEntity.setCreatorid(Manager);
					    				   loanApproveFinalEntity.setCreatetime(dksqxx.getLaApplyTime());
					    				   loanApproveFinalEntity.setUpdatorid(Manager);
					    				   loanApproveFinalEntity.setUpdatetime(dksqxx.getLaApplyTime());
					    				   loanApproveService.insertFinalchannelid(loanApproveFinalEntity);
					    				   String lafid=loanApproveFinalEntity.getId();
					    				   //构造授信记录信息
					    				   LoanApproveRecEntity loanApproveRecEntity3=new LoanApproveRecEntity();
					    				   loanApproveRecEntity3.setRegion_id(registerEntity.getRegionId());
					    				   loanApproveRecEntity3.setFp_id(fpid);
					    				   loanApproveRecEntity3.setLa_id( String.valueOf(syptbankapplies.get(0).getLaId()));
					    				   loanApproveRecEntity3.setLas_id(String.valueOf(3));
					    				   loanApproveRecEntity3.setLac_id(String.valueOf(3));
					    				   if(("LB").equals(dksqxx.getChannelid())){
					    				   loanApproveRecEntity3.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinal().getLarCreditQuota())/10000)));
					    				   }else {
					    				   loanApproveRecEntity3.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinal().getLarCreditQuota()))));
										   }
					    				   loanApproveRecEntity3.setLar_loan_deadline(Integer.parseInt(dksqxx.getBankfinal() .getLarLoanDeadline()));
					    				   loanApproveRecEntity3.setLar_begin(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity3.setLar_end(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity3.setLar_rate(dksqxx.getBankfinal().getLarRate().toString());
					    				   loanApproveRecEntity3.setLar_isoverlay_area(0);
					    				   loanApproveRecEntity3.setLar_opinion("同意授信");
					    				   loanApproveRecEntity3.setCreatorid(Manager);
					    				   loanApproveRecEntity3.setCreatetime(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity3.setUpdatorid(Manager);
					    				   loanApproveRecEntity3.setUpdatetime(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity3.setRw_id(dksqxx.getBankfinal().getRwId());
					    			
					    				   loanApproveQueryService.insertRec3(loanApproveRecEntity3);
					    				   //修改对照表中授信临时表ID   
					    
					    				   Syptbankapply syptbankapply2=new Syptbankapply();
					    				   syptbankapply2.setTempfinalId(String.valueOf(dksqxx.getBankfinal().getFinalid()));
					    				   syptbankapply2.setLfId(lfId);
					    				   syptbankApplyService.changetemp(syptbankapply2);
					    				 //多条申请单数据进行处理
						    			   loanApplyDataSaveService.changesqdkxx(lfId);
					    				   //多条同申请单ID授信数据置N
					    				   loanApplyDataSaveService.changefinal(lfId);
					    				   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
					    				   loanApproveQueryEntity.setId(syptbankapplies.get(0).getLaId());
					    				   loanApproveQueryEntity.setUpdatorid(registerEntity.getUserId());
					    				   loanApproveQueryEntity.setUpdatetime(dksqxx.getLaApplyTime());
					    			       loanApproveQueryService.updatelastauts3(loanApproveQueryEntity);
					    			
					    				   
					    				   
					    				   
					    				   
					    			   }
				    				   else{

										
					    				   //构造受理未通过信息
					    				   LoanApproveRecEntity loanApproveRecEntity6=new LoanApproveRecEntity();
					    				   loanApproveRecEntity6.setRegion_id(registerEntity.getRegionId());
					    				   loanApproveRecEntity6.setFp_id(fpid);
					    				   loanApproveRecEntity6.setLa_id(String.valueOf(syptbankapplies.get(0).getLaId()));
					    				   loanApproveRecEntity6.setLas_id(String.valueOf(6));
					    				   loanApproveRecEntity6.setLac_id(String.valueOf(6));
					    				   loanApproveRecEntity6.setLar_credit_quota("0");
					    				   loanApproveRecEntity6.setLar_loan_deadline(0);
					    				   loanApproveRecEntity6.setLar_begin(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity6.setLar_end(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity6.setLar_rate("0");
					    				   loanApproveRecEntity6.setLar_isoverlay_area(0);
					    				   loanApproveRecEntity6.setLar_opinion("受理不通过");
					    				   loanApproveRecEntity6.setCreatorid(Manager);
					    				   loanApproveRecEntity6.setCreatetime(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity6.setUpdatorid(Manager);
					    				   loanApproveRecEntity6.setUpdatetime(dksqxx.getLaApplyTime());
					    				   loanApproveQueryService.insertRec3(loanApproveRecEntity6);
					    				   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
					    				   loanApproveQueryEntity.setId(syptbankapplies.get(0).getLaId());
					    				   loanApproveQueryEntity.setUpdatorid(registerEntity.getUserId());				
					    				   loanApproveQueryService.updatelastauts6(loanApproveQueryEntity);
					    				   //修改对照表中授信临时表ID   
										    
					    				   Syptbankapply syptbankapply4=new Syptbankapply();
					    				   syptbankapply4.setTempfinalId(String.valueOf(dksqxx.getBankfinal().getFinalid()));
					    				   syptbankapply4.setTempendId("N");
					    				   syptbankapply4.setLfId(lfId);
					    				   syptbankApplyService.changetemp(syptbankapply4);
					    				 //多条申请单数据进行处理
						    			   loanApplyDataSaveService.changesqdkxx(lfId);
					    				   //多条同申请单ID授信数据置N
					    				   loanApplyDataSaveService.changefinal(lfId);
					    				   
									   
				    				   }
				    			
			    				}
			    			}
			    		   if(syptbankapplies.get(0).getTempendId()==null||("").equals(syptbankapplies.get(0).getTempendId())) {
			    		   		if( ("N").equals(dksqxx.getBankfinalend().getEnabled())&&dksqxx.getBankfinalend().getNsrsbh()!=null){
			    		   			Map<String, Object> paMap = new HashMap<String, Object>();
			    		   			paMap.put("laId",syptbankapplies.get(0).getLaId());
			    		   			   List<LoanApplyFinalEntity> entities=loanApproveQueryService.findByEx(paMap);
			    		   			   
			    					   //构造授信终止
			    		   		    	LoanApplyFinalEntity loanApproveFinalEntity=entities.get(0);
			    					   LoanApplyFinalEndEntity applyFinalEndEntity=new LoanApplyFinalEndEntity();
			    					   applyFinalEndEntity.setLaf_id(loanApproveFinalEntity.getId().toString());
			    					   if(("00").equals(dksqxx.getBankfinalend().getLaeZzbz())){
			    						   applyFinalEndEntity.setLafs_id("2");//贷款按期完成
			    					   }else if (("10").equals(dksqxx.getBankfinalend().getLaeZzbz())) {
			    						   applyFinalEndEntity.setLafs_id("1");//贷款手动终止
			    						   
									   } 
			    					   applyFinalEndEntity.setBankloan_type(dksqxx.getBankfinalend().getBankloanType().toString());
			    					   if(("LB").equals(dksqxx.getChannelid())){
			    						   applyFinalEndEntity.setLae_credit_quota(String.valueOf(Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota())/10000)));
			    					   }else {
			    						   applyFinalEndEntity.setLae_credit_quota(dksqxx.getBankfinalend().getLaeCreditQuota());
									   }
			    					 
			    					   applyFinalEndEntity.setLae_overdue_count(dksqxx.getBankfinalend().getLaeOverdueCount());
			    					   applyFinalEndEntity.setCreatorid(Manager);
			    					   applyFinalEndEntity.setNsrmc(dksqxx.getNsryhxxQymc());
			    					   applyFinalEndEntity.setNsrbh(dksqxx.getNsryhxxNsrsbh());
			    					   applyFinalEndEntity.setLae_endDate(dksqxx.getBankfinalend().getLaeEbddate());
			    					   applyFinalEndEntity.setCreatetime(dksqxx.getBankfinalend().getLaeEbddate());
			    					   applyFinalEndEntity.setUpdatorid(Manager);
			    					   applyFinalEndEntity.setUpdatetime(dksqxx.getBankfinalend().getLaeEbddate());
			    					   loanApproveQueryService.insertItem(applyFinalEndEntity);
			    					   
			    					   LoanApproveRecEntity loanApproveRecEntity4=new LoanApproveRecEntity();
				    				   loanApproveRecEntity4.setRegion_id(registerEntity.getRegionId());
				    				   loanApproveRecEntity4.setFp_id(fpid);
				    				   loanApproveRecEntity4.setLa_id(String.valueOf(syptbankapplies.get(0).getLaId()));
				    				   loanApproveRecEntity4.setLas_id(String.valueOf(7));
				    				   loanApproveRecEntity4.setLac_id(String.valueOf(3));
				    				   if(("LB").equals(dksqxx.getChannelid())){
				    				   loanApproveRecEntity4.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota())/10000)));
				    				   }else {
				    					   loanApproveRecEntity4.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota()))));
								   	   }
				    				   loanApproveRecEntity4.setLar_loan_deadline(Integer.parseInt(dksqxx.getBankfinal().getLarLoanDeadline()));
				    				   loanApproveRecEntity4.setLar_begin(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity4.setLar_end(dksqxx.getBankfinalend().getLaeEbddate());
				    				   loanApproveRecEntity4.setLar_rate(dksqxx.getBankfinal().getLarRate().toString());
				    				   loanApproveRecEntity4.setLar_isoverlay_area(0);
				    				   if(("00").equals(dksqxx.getBankfinalend().getLaeZzbz())){
				    					   loanApproveRecEntity4.setLafs_id(String.valueOf(2));//贷款按期完成
			    					   }else if (("10").equals(dksqxx.getBankfinalend().getLaeZzbz())) {
			    						   loanApproveRecEntity4.setLafs_id(String.valueOf(1));//贷款手动终止
			    						   
									   }
				    				   loanApproveRecEntity4.setLar_opinion("");
				    				   loanApproveRecEntity4.setCreatorid(Manager);
				    				   loanApproveRecEntity4.setUpdatorid(Manager);
				    				   loanApproveRecEntity4.setUpdatetime(dksqxx.getBankfinalend().getLaeEbddate());
				    				   loanApproveRecEntity4.setCreatetime(dksqxx.getBankfinalend().getLaeEbddate());
				    				   loanApproveQueryService.insertRec7(loanApproveRecEntity4);
			    					   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
			    					   loanApproveQueryEntity.setId(syptbankapplies.get(0).getLaId());
				    				   loanApproveQueryEntity.setUpdatorid(registerEntity.getUserId());	
				    				   loanApproveQueryService.updatela(loanApproveQueryEntity);
				    				   LoanApproveFinalEntity applyFinalEntity=new LoanApproveFinalEntity();
				    				   if(("00").equals(dksqxx.getBankfinalend().getLaeZzbz())){
				    					   applyFinalEntity.setLafs_id("2");//贷款按期完成
			    					   }else if (("10").equals(dksqxx.getBankfinalend().getLaeZzbz())) {
			    						   applyFinalEntity.setLafs_id("1");//贷款手动终止
			    						   
									   }
				    				   applyFinalEntity.setId(loanApproveFinalEntity.getId());
				    				   applyFinalEntity.setUpdatetime(dksqxx.getBankfinalend().getLaeEbddate());
				    				   loanApproveQueryService.updatelaf2(applyFinalEntity);
				    				   //修改对照表中授信临时表ID   
									    
				    				   Syptbankapply syptbankapply3=new Syptbankapply();
				    				   syptbankapply3.setTempendId( String.valueOf(dksqxx.getBankfinalend().getEndssid()));
				    				   syptbankapply3.setLfId(lfId);
				    				   
				    				   syptbankApplyService.changetemp(syptbankapply3);
				    					//多条申请单数据进行处理
					    			   loanApplyDataSaveService.changesqdkxx(lfId);
					    			   //多条同申请单ID授信数据置N
				    				   loanApplyDataSaveService.changefinal(lfId);
				    				   //多条同申请单ID终止数据置N
				    				   loanApplyDataSaveService.changeend(lfId);
				    				   
			    				   }
							}
			    		     if(syptbankapplies.get(0).getTempfinalId()!=null&&syptbankapplies.get(0).getTempendId()!=null) {
			    		    	//多条申请单数据进行处理
				    			   loanApplyDataSaveService.changesqdkxx(lfId);
				    			   //多条同申请单ID授信数据置N
			    				   loanApplyDataSaveService.changefinal(lfId);
			    				 //多条同申请单ID终止数据置N
			    				   loanApplyDataSaveService.changeend(lfId);
							}
			    			
			    		}//对照表中不存在数据
			    		else{

				    		   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
				    		   loanApproveQueryEntity.setFp_id(fpid);
				    		   loanApproveQueryEntity.setRegion_id(registerEntity.getRegionId());
				    		   String[] rwId=financialProduct.getRwIds().split("#");
				    		   String rwIds=rwId[0];

				    		   loanApproveQueryEntity.setRw_id(
				    				   rwIds
				    				   );
				    			   
	
				    		   loanApproveQueryEntity.setNsrsbh(dksqxx.getNsryhxxNsrsbh());
				    		   loanApproveQueryEntity.setLa_serial_number(StringHelper.getnuber());
				    		   if(("LB").equals(dksqxx.getChannelid())){
				    		   BigDecimal b1 = dksqxx.getLaAmount(); 
				    		   BigDecimal b2 = new BigDecimal("10000"); 
				    		   BigDecimal laAmount=     b1.divide(b2,BigDecimal.ROUND_HALF_UP);  
				    		   loanApproveQueryEntity.setLa_amount((int)Math.ceil( Double.parseDouble( laAmount.toString())));
				    		   }else {
				    			   loanApproveQueryEntity.setLa_amount((int)Math.ceil( Double.parseDouble( dksqxx.getLaAmount().toString())));
							   }
				    		   loanApproveQueryEntity.setLa_repay_loan_deadline(Integer.parseInt(financialProduct.getDeadlineEntity().getCode()));//还款期限月数   无
				    		   loanApproveQueryEntity.setLa_apply_time(dksqxx.getLaApplyTime());
				    		   if(dksqxx.getBankfinalend()==null||dksqxx.getBankfinalend().getNsrsbh()==null){
				    			   loanApproveQueryEntity.setLa_end_time(dksqxx.getLaApplyTime());
				    		   }else {
				    			   loanApproveQueryEntity.setLa_end_time(dksqxx.getBankfinalend().getLaeEbddate());
							   }
				    		  
				    		   loanApproveQueryEntity.setLa_status(String.valueOf(1));
				    		   loanApproveQueryEntity.setCreatorid(registerEntity.getUserId());
				    		   loanApproveQueryEntity.setCreatetime(dksqxx.getLaApplyTime());
				    		   loanApproveQueryEntity.setUpdatorid(Manager);
				    		   if(dksqxx.getBankfinalend()==null ||dksqxx.getBankfinalend().getNsrsbh()==null){
				    		   loanApproveQueryEntity.setUpdatetime(dksqxx.getLaApplyTime());
				    		   }else {
				    			   loanApproveQueryEntity.setUpdatetime(dksqxx.getBankfinalend().getLaeEbddate());
							   }
				    		   int result=loanApproveQueryService.insertApply(loanApproveQueryEntity);
				    		   String laid=loanApproveQueryEntity.getId();
				    		   if(result>0){
				    			   //申请单构造成功 新增对照表申请单id 
				    			   Bankfinal bankfinal=dksqxx.getBankfinal();
				    			   loanApproveQueryEntity.setId(laid);
				    			   //新增税银平台银行申请单关联记录
				    			   Syptbankapply syptbankapply=new Syptbankapply();
				    			   syptbankapply.setLaId(loanApproveQueryEntity.getId());
				    			   syptbankapply.setLfId(dksqxx.getLfId());
				    			   syptbankapply.setTemplaId(String.valueOf(dksqxx.getDksqdId()));
				    			   syptbankapply.setChannelid(dksqxx.getChannelid());
				    			   syptbankApplyService.insert(syptbankapply);
				    			   //多条申请单数据进行处理
				    			   loanApplyDataSaveService.changesqdkxx(lfId);
				    			   //申请 插入记录表
				    			   LoanApproveRecEntity loanApproveRecEntity=new LoanApproveRecEntity();
			    				   loanApproveRecEntity.setRegion_id(registerEntity.getRegionId());
			    				   loanApproveRecEntity.setFp_id(fpid);
			    				   loanApproveRecEntity.setLa_id(loanApproveQueryEntity.getId());
			    				   loanApproveRecEntity.setLas_id(String.valueOf(1));
			    				   loanApproveRecEntity.setLac_id(String.valueOf(0));
			    				   loanApproveRecEntity.setLar_credit_quota("0");
			    				   loanApproveRecEntity.setLar_loan_deadline(0);
			    				   loanApproveRecEntity.setLar_begin(dksqxx.getLaApplyTime());
			    				   loanApproveRecEntity.setLar_end(dksqxx.getLaApplyTime());
			    				   loanApproveRecEntity.setLar_rate("0");
			    				   loanApproveRecEntity.setLar_isoverlay_area(0);
			    				   loanApproveRecEntity.setLar_opinion("企业已提交贷款申请，请银行尽快受理。");
			    				   loanApproveRecEntity.setCreatorid(registerEntity.getUserId());
			    				   loanApproveRecEntity.setCreatetime(dksqxx.getLaApplyTime());
			    				   loanApproveRecEntity.setUpdatorid(registerEntity.getUserId());
			    				   loanApproveRecEntity.setUpdatetime(dksqxx.getLaApplyTime());
			    				   loanApproveQueryService.insertRec3(loanApproveRecEntity);
				    			   //审批通过
				    			   if(("00").equals(dksqxx.getBankfinal().getLarPdjg())){
				    				   //构造申请信息记录
				    				
				    				   //构造受理信息
				    				   LoanApproveRecEntity loanApproveRecEntity2=new LoanApproveRecEntity();
				    				   loanApproveRecEntity2.setRegion_id(registerEntity.getRegionId());
				    				   loanApproveRecEntity2.setFp_id(fpid);
				    				   loanApproveRecEntity2.setLa_id(loanApproveQueryEntity.getId());
				    				   loanApproveRecEntity2.setLas_id(String.valueOf(2));
				    				   loanApproveRecEntity2.setLac_id(String.valueOf(2));
				    				   loanApproveRecEntity2.setLar_credit_quota("0");
				    				   loanApproveRecEntity2.setLar_loan_deadline(0);
				    				   loanApproveRecEntity2.setLar_begin(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity2.setLar_end(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity2.setLar_rate("0");
				    				   loanApproveRecEntity2.setLar_isoverlay_area(0);
				    				   loanApproveRecEntity2.setLar_opinion("同意受理");
				    				   loanApproveRecEntity2.setCreatorid(Manager);
				    				   loanApproveRecEntity2.setCreatetime(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity2.setUpdatorid(Manager);
				    				   loanApproveRecEntity2.setUpdatetime(dksqxx.getLaApplyTime());
				    				   loanApproveQueryService.insertRec3(loanApproveRecEntity2);
				    				   //构造授信数据
				    				   LoanApproveFinalEntity loanApproveFinalEntity=new LoanApproveFinalEntity();
				    				   loanApproveFinalEntity.setFp_id(String.valueOf(fpid));
				    				   loanApproveFinalEntity.setRegion_id(registerEntity.getRegionId());
				    				   loanApproveFinalEntity.setLa_id(loanApproveQueryEntity.getId().toString());
				    				   loanApproveFinalEntity.setLac_id(String.valueOf(3));
				    				   loanApproveFinalEntity.setLar_opinion("同意");
				    				   loanApproveFinalEntity.setCreatorid(Manager);
				    				   loanApproveFinalEntity.setCreatetime(dksqxx.getLaApplyTime());
				    				   loanApproveFinalEntity.setUpdatorid(Manager);
				    				   loanApproveFinalEntity.setUpdatetime(dksqxx.getLaApplyTime());
				    				   loanApproveService.insertFinalchannelid(loanApproveFinalEntity);
				    				   String lafid=loanApproveFinalEntity.getId();
				    				   //构造授信记录信息
				    				   LoanApproveRecEntity loanApproveRecEntity3=new LoanApproveRecEntity();
				    				   loanApproveRecEntity3.setRegion_id(registerEntity.getRegionId());
				    				   loanApproveRecEntity3.setFp_id(fpid);
				    				   loanApproveRecEntity3.setLa_id(loanApproveQueryEntity.getId());
				    				   loanApproveRecEntity3.setLas_id(String.valueOf(3));
				    				   loanApproveRecEntity3.setLac_id(String.valueOf(3));
				    				   loanApproveRecEntity3.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinal().getLarCreditQuota())/10000)));//--
				    				   loanApproveRecEntity3.setLar_loan_deadline(Integer.parseInt(bankfinal.getLarLoanDeadline()));
				    				   loanApproveRecEntity3.setLar_begin(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity3.setLar_end(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity3.setLar_rate(bankfinal.getLarRate().toString());
				    				   loanApproveRecEntity3.setLar_isoverlay_area(0);
				    				   loanApproveRecEntity3.setLar_opinion("同意授信");
				    				   loanApproveRecEntity3.setCreatorid(Manager);
				    				   loanApproveRecEntity3.setCreatetime(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity3.setUpdatorid(Manager);
				    				   loanApproveRecEntity3.setUpdatetime(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity3.setRw_id(bankfinal.getRwId());
				    				   loanApproveQueryService.insertRec3(loanApproveRecEntity3);
				    				   //修改对照表中授信临时表ID   				
				    				   Syptbankapply syptbankapply2=new Syptbankapply();
				    				   syptbankapply2.setTempfinalId(String.valueOf(dksqxx.getBankfinal().getFinalid()));
				    				   syptbankapply2.setLfId(lfId);
				    				   syptbankApplyService.changetemp(syptbankapply2);
				    				   //多条同申请单ID授信数据置N
				    				   loanApplyDataSaveService.changefinal(lfId);
				    				   if(dksqxx.getBankfinalend()!=null&dksqxx.getBankfinalend().getNsrsbh()!=null){
				    					   //构造授信终止
				    					   loanApproveFinalEntity.setId(lafid);
				    					   LoanApplyFinalEndEntity applyFinalEndEntity=new LoanApplyFinalEndEntity();
				    					   applyFinalEndEntity.setLaf_id(loanApproveFinalEntity.getId());
				    					   if(("00").equals(dksqxx.getBankfinalend().getLaeZzbz())){
				    						   applyFinalEndEntity.setLafs_id("2");//贷款按期完成
				    					   }else if (("10").equals(dksqxx.getBankfinalend().getLaeZzbz())) {
				    						   applyFinalEndEntity.setLafs_id("1");//贷款手动终止
				    						   
										   } 
				    					   applyFinalEndEntity.setBankloan_type(dksqxx.getBankfinalend().getBankloanType().toString());
				    					   if(("LB").equals(dksqxx.getChannelid())){
				    					   applyFinalEndEntity.setLae_credit_quota(String.valueOf(Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota())/10000)));
				    					   }else {
				    						   applyFinalEndEntity.setLae_credit_quota(String.valueOf(Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota()))));
										   }
				    					   applyFinalEndEntity.setLae_overdue_count(dksqxx.getBankfinalend().getLaeOverdueCount());
				    					   applyFinalEndEntity.setCreatorid(Manager);
				    					   applyFinalEndEntity.setNsrmc(dksqxx.getNsryhxxQymc());
				    					   applyFinalEndEntity.setNsrbh(dksqxx.getNsryhxxNsrsbh());
				    					   applyFinalEndEntity.setLae_endDate(dksqxx.getBankfinalend().getLaeEbddate());
				    					   applyFinalEndEntity.setCreatetime(dksqxx.getBankfinalend().getLaeEbddate());
				    					   applyFinalEndEntity.setUpdatorid(Manager);
				    					   applyFinalEndEntity.setUpdatetime(dksqxx.getBankfinalend().getLaeEbddate());
				    					   loanApproveQueryService.insertItem(applyFinalEndEntity);
				    					   
				    					   LoanApproveRecEntity loanApproveRecEntity4=new LoanApproveRecEntity();
					    				   loanApproveRecEntity4.setRegion_id(registerEntity.getRegionId());
					    				   loanApproveRecEntity4.setFp_id(fpid);
					    				   loanApproveRecEntity4.setLa_id(loanApproveQueryEntity.getId());
					    				   loanApproveRecEntity4.setLas_id(String.valueOf(7));
					    				   loanApproveRecEntity4.setLac_id(String.valueOf(3));
					    				   if(("LB").equals(dksqxx.getChannelid())){
					    				   loanApproveRecEntity4.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota())/10000)));
					    				   }else {
					    				   loanApproveRecEntity4.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dksqxx.getBankfinalend().getLaeCreditQuota()))));
										   }
					    				   loanApproveRecEntity4.setLar_loan_deadline(Integer.parseInt(bankfinal.getLarLoanDeadline()));
					    				   loanApproveRecEntity4.setLar_begin(dksqxx.getLaApplyTime());
					    				   loanApproveRecEntity4.setLar_end(dksqxx.getBankfinalend().getLaeEbddate());
					    				   loanApproveRecEntity4.setLar_rate(bankfinal.getLarRate().toString());
					    				   loanApproveRecEntity4.setLar_isoverlay_area(0);
					    				   if(("00").equals(dksqxx.getBankfinalend().getLaeZzbz())){
					    					   loanApproveRecEntity4.setLafs_id(String.valueOf(2));//贷款按期完成
				    					   }else if (("10").equals(dksqxx.getBankfinalend().getLaeZzbz())) {
				    						   loanApproveRecEntity4.setLafs_id(String.valueOf(1));//贷款手动终止
				    						   
										   }
					    				   loanApproveRecEntity4.setLar_opinion("");
					    				   loanApproveRecEntity4.setCreatorid(Manager);
					    				   loanApproveRecEntity4.setUpdatorid(Manager);
					    				   loanApproveRecEntity4.setUpdatetime(dksqxx.getBankfinalend().getLaeEbddate());
					    				   loanApproveRecEntity4.setCreatetime(dksqxx.getBankfinalend().getLaeEbddate());
					    				   loanApproveQueryService.insertRec7(loanApproveRecEntity4);
				    					   
					    				   loanApproveQueryService.updatela(loanApproveQueryEntity);
					    				   if(("00").equals(dksqxx.getBankfinalend().getLaeZzbz())){
					    					   loanApproveFinalEntity.setLafs_id("2");//贷款按期完成
				    					   }else if (("10").equals(dksqxx.getBankfinalend().getLaeZzbz())) {
				    						   loanApproveFinalEntity.setLafs_id("1");//贷款手动终止
				    						   
										   }
					    				   loanApproveQueryService.updatelaf2(loanApproveFinalEntity);
					    				   //修改对照表中授信临时表ID   
										    
					    				   Syptbankapply syptbankapply3=new Syptbankapply();
					    				   syptbankapply3.setTempendId(String.valueOf(dksqxx.getBankfinalend().getEndssid()));
					    				   syptbankapply3.setLfId(lfId);
					    				   syptbankApplyService.changetemp(syptbankapply3);
					    				   //多条同申请单ID终止数据置N
					    				   loanApplyDataSaveService.changeend(lfId);
					    				   
				    				   }else{
				    					   loanApproveQueryService.updatelastauts3(loanApproveQueryEntity);
				    				   }
				    				   
				    				   
				    				   
				    				   
				    			   }else if(("10").equals(dksqxx.getBankfinal().getLarPdjg())){
										//受理不通过
			    					 /*  //构造申请信息记录
				    				   LoanApproveRecEntity loanApproveRecEntity5=new LoanApproveRecEntity();
				    				   loanApproveRecEntity5.setRegion_id(Long.parseLong(registerEntity.getRegionId()));
				    				   loanApproveRecEntity5.setFp_id(fpid);
				    				   loanApproveRecEntity5.setLa_id(Integer.parseInt((loanApproveQueryEntity.getId()).toString()));
				    				   loanApproveRecEntity5.setLas_id(1);
				    				   loanApproveRecEntity5.setLac_id(0);
				    				   loanApproveRecEntity5.setLar_credit_quota(0);
				    				   loanApproveRecEntity5.setLar_loan_deadline(0);
				    				   loanApproveRecEntity5.setLar_begin(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity5.setLar_end(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity5.setLar_rate("0");
				    				   loanApproveRecEntity5.setLar_isoverlay_area(0);
				    				   loanApproveRecEntity5.setLar_opinion("企业已提交贷款申请，请银行尽快受理。");
				    	 			   loanApproveRecEntity5.setCreatorid(Long.parseLong(registerEntity.getUserId()));
				    				   loanApproveRecEntity5.setCreatetime(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity5.setUpdatorid(Long.parseLong(registerEntity.getUserId()));
				    				   loanApproveRecEntity5.setUpdatetime(dksqxx.getLaApplyTime());
				    				   loanApproveQueryService.insertRec3(loanApproveRecEntity5);*/
				    				   //构造受理未通过信息
				    				   LoanApproveRecEntity loanApproveRecEntity6=new LoanApproveRecEntity();
				    				   loanApproveRecEntity6.setRegion_id(registerEntity.getRegionId());
				    				   loanApproveRecEntity6.setFp_id(fpid);
				    				   loanApproveRecEntity6.setLa_id(loanApproveQueryEntity.getId());
				    				   loanApproveRecEntity6.setLas_id(String.valueOf(6));
				    				   loanApproveRecEntity6.setLac_id(String.valueOf(6));
				    				   loanApproveRecEntity6.setLar_credit_quota("0");
				    				   loanApproveRecEntity6.setLar_loan_deadline(0);
				    				   loanApproveRecEntity6.setLar_begin(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity6.setLar_end(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity6.setLar_rate("0");
				    				   loanApproveRecEntity6.setLar_isoverlay_area(0);
				    				   loanApproveRecEntity6.setLar_opinion("受理不通过");
				    				   loanApproveRecEntity6.setCreatorid(Manager);
				    				   loanApproveRecEntity6.setCreatetime(dksqxx.getLaApplyTime());
				    				   loanApproveRecEntity6.setUpdatorid(Manager);
				    				   loanApproveRecEntity6.setUpdatetime(dksqxx.getLaApplyTime());
				    				   loanApproveQueryService.insertRec3(loanApproveRecEntity6);
				    				   loanApproveQueryService.updatelastauts6(loanApproveQueryEntity);
				    				   //修改对照表中授信临时表ID   
									    
				    				   Syptbankapply syptbankapply4=new Syptbankapply();
				    				   syptbankapply4.setTempfinalId(String.valueOf(dksqxx.getBankfinal().getFinalid()));
				    				   syptbankapply4.setTempendId("N");
				    				   syptbankapply4.setLfId(lfId);
				    				   syptbankApplyService.changetemp(syptbankapply4);
				    				   //多条同申请单ID授信数据置N
				    				   loanApplyDataSaveService.changefinal(lfId);
				    				   
								   }
				    			   else {
									
								}
				    		   }
				    		   }
				    		   
			    		}
			    		}
			    		}
		    			}
		    			}
		    			
	    			}
	    			}
	    		}
	    	}
	    
	    
	    
	    
	    }
	 /*   for (Bankfinalend bankfinalend : bankfinalendList) {
			
		}
	    for (Bankfinal bankfinal : bankfinalList) {
			
		}*/

		
	}catch(ServiceException e)
	{
		throw new AjaxException(e);
	}
	
}
/**
 * 自动终止
 * @param
 * @throws ParseException 
 * @throws Exception
 */
public void automaticEnd() throws AjaxException, ParseException
{  
	int results=0;
	LoanApplyFinalEndEntity loanApplyFinalEndEntity=new LoanApplyFinalEndEntity();
	try
	{   	

       SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间格式
       Date nowTime=new Date();//获取当前时间
       String   tmf= matter1.format(nowTime);
       Date now=matter1.parse(tmf);
       SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Map<String, Object> parameters = new HashMap<String, Object>();
	   List<LoanApplyFinalEntity> loanApplyFinalEntity = loanApproveQueryService.findByEx(parameters);
	   for (LoanApplyFinalEntity loanApplyFinalEntity2 : loanApplyFinalEntity) {
	    	  Date end=matter1.parse(simpleDateFormat.format(loanApplyFinalEntity2.getLoanApproveRecList().getLar_end()));//atime是数据库中事前存的时间
              long cha=(now.getTime()-end.getTime())/ (1000 * 60 * 60 * 24);//计算时间差
			if(cha>=0&&!("7").equals(loanApplyFinalEntity2.getLoanApproveQueryEntity().getLa_status())){
				loanApplyFinalEndEntity.setNsrmc(loanApplyFinalEntity2.getNsryhxxEntity().getQymc());
		        loanApplyFinalEndEntity.setNsrbh(loanApplyFinalEntity2.getNsryhxxEntity().getNsrsbh());
		        loanApplyFinalEndEntity.setLae_endDate(new Date());
		        loanApplyFinalEndEntity.setLae_credit_quota(String.valueOf(loanApplyFinalEntity2.getLoanApproveRecList().getLar_credit_quota()));
		        loanApplyFinalEndEntity.setLae_overdue_count(0);
		        loanApplyFinalEndEntity.setBankloan_type("0");
		        loanApplyFinalEndEntity.setLaf_id(loanApplyFinalEntity2.getId());
		        loanApplyFinalEndEntity.setLafs_id("2");
		        loanApplyFinalEndEntity.setCreatorid(loanApplyFinalEntity2.getCreatorid());
		        loanApplyFinalEndEntity.setCreatetime(new Date());
		        loanApplyFinalEndEntity.setUpdatorid(loanApplyFinalEntity2.getCreatorid());
		        loanApplyFinalEndEntity.setUpdatetime(new Date());  
		        if(loanApproveQueryService.insertItem(loanApplyFinalEndEntity)>0){
		        	LoanApproveRecEntity loanApproveRecEntity=new LoanApproveRecEntity();
		        	loanApproveRecEntity.setLa_id(loanApplyFinalEntity2.getLa_id());
		        	loanApproveRecEntity.setLar_credit_quota(loanApplyFinalEntity2.getLoanApproveRecList().getLar_credit_quota());
		        	loanApproveRecEntity.setLafs_id(String.valueOf(2));
		        	loanApproveRecEntity.setCreatorid(loanApplyFinalEntity2.getCreatorid());
		        	loanApproveRecEntity.setCreatetime(new Date());
		        	loanApproveRecEntity.setUpdatorid(loanApplyFinalEntity2.getCreatorid());
		        	loanApproveRecEntity.setUpdatetime(new Date());
	  		    	loanApproveRecEntity.setLar_bank_account(loanApplyFinalEntity2.getLoanApproveRecList().getLar_bank_account());
	  		    	loanApproveRecEntity.setLar_bank_name(loanApplyFinalEntity2.getLoanApproveRecList().getLar_bank_name());
	  		    	loanApproveRecEntity.setRegion_id(loanApplyFinalEntity2.getLoanApproveRecList().getRegion_id());
	  		    	loanApproveRecEntity.setLas_id(String.valueOf(7));
	  		    	loanApproveRecEntity.setFp_id(loanApplyFinalEntity2.getLoanApproveRecList().getFp_id());
	  		    	loanApproveRecEntity.setLac_id(String.valueOf(3));
	  		    	loanApproveRecEntity.setLar_loan_deadline(loanApplyFinalEntity2.getLoanApproveRecList().getLar_loan_deadline());
	  		    	loanApproveRecEntity.setLar_begin(loanApplyFinalEntity2.getLoanApproveRecList().getLar_begin());
	  		    	loanApproveRecEntity.setLar_end(loanApplyFinalEntity2.getLoanApproveRecList().getLar_end());
	  		    	loanApproveRecEntity.setLar_rate(loanApplyFinalEntity2.getLoanApproveRecList().getLar_rate());
	  		    	loanApproveRecEntity.setLar_isoverlay_area(loanApplyFinalEntity2.getLoanApproveRecList().getLar_isoverlay_area());
	  		    	loanApproveRecEntity.setRw_id(loanApplyFinalEntity2.getLoanApproveRecList().getRw_id());
	  		    	loanApproveRecEntity.setLar_contract(loanApplyFinalEntity2.getLoanApproveRecList().getLar_contract());
	  		    	loanApproveRecEntity.setLar_opinion(loanApplyFinalEntity2.getLoanApproveRecList().getLar_opinion());
	  		    	int res=loanApproveQueryService.insertRec(loanApproveRecEntity);    
					LoanApproveQueryEntity loanApproveQueryEntity2=new LoanApproveQueryEntity();
					loanApproveQueryEntity2.setId(String.valueOf(loanApplyFinalEntity2.getLa_id()));
					loanApproveQueryEntity2.setLa_status(String.valueOf(7));
					loanApproveQueryEntity2.setUpdatorid(loanApplyFinalEntity2.getCreatorid());	 
					loanApproveQueryService.updatela(loanApproveQueryEntity2);
					LoanApplyFinalEntity loanApplyFinalEntity3=new LoanApplyFinalEntity();
					loanApplyFinalEntity3.setLafs_id("2");
					loanApplyFinalEntity3.setId(loanApplyFinalEntity2.getId());
					loanApplyFinalEntity3.setUpdatorid(loanApplyFinalEntity2.getCreatorid());			        	 
		        	try {
		        		  results=  loanApproveQueryService.updatelaf(loanApplyFinalEntity3);
					} catch (Exception e) {
						return;
					}
		        }
			}  
		}
	}catch(ServiceException e)
	{
		throw new AjaxException(e);
	}
}
public void changeStatues() throws Exception{
	  SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间格式
      Date nowTime=new Date();//获取当前时间
      
      String   tmf= matter1.format(nowTime);
      ParsePosition pos = new ParsePosition(0);
      Date now=matter1.parse(tmf,pos);
	  List<LoanApproveQueryEntity> approveQueryEntities=loanApproveQueryService.findBystatues();
	  for (LoanApproveQueryEntity loanApproveQueryEntity : approveQueryEntities) {
		if(loanApproveQueryEntity.getFinancialProduct().getAcceptDeadLineEntity().getTlTotalDays()!=null&&loanApproveQueryEntity.getFinancialProduct().getAcceptDeadLineEntity().getTlTotalDays()!=0){
		 Calendar calen = Calendar.getInstance();
		 //日历对象默认的日期为当前日期，调用setTime设置该日历对象的日期为程序中指定的日期
		 calen.setTime(loanApproveQueryEntity.getLa_apply_time());
	     //将日历的"天"增加5
		 calen.add(Calendar.DAY_OF_YEAR, Integer.parseInt(loanApproveQueryEntity.getFinancialProduct().getAcceptDeadLineEntity().getTlTotalDays().toString()));
         //获取日历对象的时间，并赋给日期对象c
		 Date newDealLine=calen.getTime();
		 //用f格式化c并输出
		    System.out.println(matter1.format(newDealLine));
		    String  aa=matter1.format(newDealLine);
		    
		    long cha=now.compareTo(newDealLine);
		       if(cha>=0){
		    	   LoanApproveQueryEntity loanApproveQueryEntity2=new LoanApproveQueryEntity();
		    	   loanApproveQueryEntity2.setId(loanApproveQueryEntity.getId());
		    	   loanApproveQueryService.updatelabydealline(loanApproveQueryEntity2);
		        }else {
				
				}
			}else {
				
			}
		}
	
	}
	
    
}
