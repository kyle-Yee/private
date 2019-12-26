package com.dcits.govsbu.sd.taxbankplatform.dkxx.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.model.AuthorizationrecordEntity;
import com.dcits.govsbu.sd.taxbankplatform.authorizationrecord.service.AuthorizationrecordService;
import com.dcits.govsbu.sd.taxbankplatform.base.basecontroller.BaseController;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.DhDhrmxxMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhDkjsxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhFailure;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.DhSxjgxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.RegisterEntity;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.BankfinalService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.BankfinalendService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.LoanApplyDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.RegisterService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.SyptbankApplyService;
import com.dcits.govsbu.sd.taxbankplatform.financialProduct.service.FinancialProductService;
import com.dcits.govsbu.sd.taxbankplatform.gxgsService.service.gxgsService;
import com.dcits.govsbu.sd.taxbankplatform.loanapplyquery.model.LoanApproveRecEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEndEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApplyFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveQueryService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveService;
import com.dcits.govsbu.sd.taxbankplatform.organization.model.OrganizationEntity;
import com.dcits.govsbu.sd.taxbankplatform.organization.service.OrganizationsService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ParametersService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;
import com.dcits.govsbu.sd.taxbankplatform.user.service.UserService;
import com.dcits.govsbu.sd.taxbankplatform.util.CreateChartUtil;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;
import com.dcits.govsbu.sd.taxbankplatform.util.WebToolUtils;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
/**
 * 
     * Title: GLLoanBankApproveController.java    
     * Description: 任务调度录入申请表单 ，审批记录，审批结果，贷款完成表
     * @author yaofang       
     * @created 2017-2-27 下午3:13:52
 */
@Controller
@Scope("prototype")
public class GLLoanBankApproveController extends BaseController {
	//打印日志
	private static Logger log = Logger.getLogger(GLLoanBankApproveController.class);
     
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
@Autowired
private DhDhrmxxMapper dhDhrmxxMapper;
@Autowired
private ParametersService  parametersService;
@Autowired
private AuthorizationrecordService authorizationrecordService;
@Autowired
private gxgsService gxgsService;
public  void  invoking(){
		try {
			//String flag = parametersService.QueryValueByCode("getdhgzsj");
			
			String flag = "10.10.10.222";
			String ipString = WebToolUtils.getLocalIP();
			log.info("---------------------当前服务器IP为"+ipString+"----------------");
			if (null != flag) {
				flag = flag.trim();
				if (flag.equals(ipString)) {
					log.info("---------------------当前服务器IP为"+ipString+"开启----------------");
					automaticFinalrec();
					
				}else {
					log.info("---------------------当前服务器"+ipString+"定时任务不开启----------------");
				}
			}else {
				log.info("---------------------当前服务器"+ipString+"定时任务不开启----------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("---------------------当前服务器定时任务出现错误----------------");
		}
}
/**
 * 
     * @discription 任务调度录入审批记录，审批结果，贷款完成表
     * @author yaofang       
     * @throws ParseException 
     * @created 2017-10-10 下午3:15:51
 */

/*public void automaticFinalrec() throws ParseException 
{
	  	
		Map<String, Object> parameter = new HashMap<String, Object>();
    	List<Syptbankapply> list=syptbankApplyService.findAll(parameter);
	    if(list != null && list.size()>0){
	    	for (Syptbankapply syptbankapply : list) {
	    		//manager为0时 未找到该管理员
				String Manager="0";
				String RegionId="0";
				String fpid="0";
				parameter.put("orgcode", syptbankapply.getChannelid().toUpperCase());
				List<OrganizationEntity> organizationEntities=organizationsService.queryListAll(parameter);
    			if(organizationEntities != null && organizationEntities.size()>0){
    			Map<String, Object> para = new HashMap<String, Object>();
    			para.put("orgid", organizationEntities.get(0).getId());
    			if(userService.queryListByPage(para) != null && userService.queryListByPage(para).size() > 0){
    				UserEntity userEntity=  userService.queryListByPage(para).get(0);
    			if(userEntity!=null){
    			       Manager=userEntity.getId();
    			   }
    			 }
    			}
    			String laid=syptbankapply.getLaId();
    			String bankid=syptbankapply.getLfId();
    			String userid=syptbankapply.getSyptUserId();
    			if(userid!=null&&!("".equals(userid))){
    				Map<String, Object> mapuserid = new HashMap<String, Object>();
    				mapuserid.put("userid", userid);
	    			List<RegisterEntity> registerEntities=registerService.UserList(mapuserid);

	    			if(registerEntities != null && registerEntities.size()>0&&laid!=null&&!("").equals(laid)){
	    				RegisterEntity registerEntity=registerEntities.get(0);
	    				if(("N").equals(registerEntity.getEnabled())){
	    					//构造受理不通过数据
	    					if(laid!=null&&!("").equals(laid)&&bankid!=null&&!("").equals(bankid)&&!("0").equals(Manager)){
	    						LoanApproveQueryEntity loanApproveQueryEntities=loanApproveQueryService.findlaById(syptbankapply.getLaId());
    		    				if(loanApproveQueryEntities!=null){
    		    					RegionId=loanApproveQueryEntities.getRegion_id();
    		    					fpid=loanApproveQueryEntities.getFp_id();
    		    				}

	    						//构造授信不通过
	    				           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    						   Date failApproeday =new Date();
	    						   //构造受理未通过信息
	    						   LoanApproveRecEntity loanApproveRecEntity6=new LoanApproveRecEntity();
	    						   String recid6=IDGenerate.getZJID("DKJL");
	    						   loanApproveRecEntity6.setId(recid6);
	    						   loanApproveRecEntity6.setRegion_id(RegionId);
	    						   loanApproveRecEntity6.setFp_id(fpid);
	    						   loanApproveRecEntity6.setLa_id(syptbankapply.getLaId());
	    						   loanApproveRecEntity6.setLas_id("DKZT06");
	    						   loanApproveRecEntity6.setLac_id("YDKZT06");
	    						   loanApproveRecEntity6.setLar_credit_quota("0");
	    						   loanApproveRecEntity6.setLar_loan_deadline(0);
	    						   loanApproveRecEntity6.setLar_begin(failApproeday);
	    						   loanApproveRecEntity6.setLar_end(failApproeday);
	    						   loanApproveRecEntity6.setLar_rate("0");
	    						   loanApproveRecEntity6.setLar_isoverlay_area(0);
	    						   loanApproveRecEntity6.setLar_opinion("受理不通过");
	    						   loanApproveRecEntity6.setCreatorid(Manager);
	    						   loanApproveRecEntity6.setCreatetime(new Date());
	    						   loanApproveRecEntity6.setUpdatorid(Manager);
	    						   loanApproveRecEntity6.setUpdatetime(new Date());
	    						   try {
	    							   loanApproveQueryService.insertRec3(loanApproveRecEntity6);
	    						   } catch (Exception e) {
	    							// TODO: handle exception
	    							   logger.info("=================受理不通过插入贷款记录表失败==================");
	    							   e.printStackTrace();
	    						   }
	    						   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
	    						   loanApproveQueryEntity.setId(syptbankapply.getLaId());
	    						   loanApproveQueryEntity.setUpdatorid(Manager);				
	    						   loanApproveQueryService.updatelastauts6(loanApproveQueryEntity);
	    						   //修改对照表中授信临时表ID   
	    						   Syptbankapply syptbankapply4=new Syptbankapply();
	    						   //授信失败存N+businessid
	    						   syptbankapply4.setTempfinalId("N");
	    						   syptbankapply4.setTempendId("N");
	    						   syptbankapply4.setLfId(syptbankapply.getLfId());
	    						   syptbankApplyService.changetemp(syptbankapply4);
	    					}
	    				}else if(("Y").equals(registerEntity.getEnabled())){
	    					if(laid!=null&&!("").equals(laid)&&bankid!=null&&!("").equals(bankid)&&!("0").equals(Manager)){
	    						LoanApproveQueryEntity loanApproveQueryEntities=loanApproveQueryService.findlaById(syptbankapply.getLaId());
	    		    				if(loanApproveQueryEntities!=null){
	    		    					RegionId=loanApproveQueryEntities.getRegion_id();
	    		    					fpid=loanApproveQueryEntities.getFp_id();
	    		    				}

	    		    				if(syptbankapply.getTempfinalId()==null||("").equals(syptbankapply.getTempfinalId())){
	    		    					//查询该id在授信失败中是否存在
	    		    					Map<String, Object> dhFailuresmap = new HashMap<String, Object>();
	    		    					dhFailuresmap.put("businessid",syptbankapply.getLfId());
	    		    					List<DhFailure> dhFailures=dhDhrmxxMapper.queryfailureListAll(dhFailuresmap);
	    		    					if(dhFailures.size()>0){
	    		    						//构造授信不通过
	    		    				           DhFailure dhFailure=dhFailures.get(0);
	    		    				           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    		    						       Date failApproeday = sdf.parse(dhFailure.getApprovedate());
	    		    						   //构造受理未通过信息
	    		    						   LoanApproveRecEntity loanApproveRecEntity6=new LoanApproveRecEntity();
	    		    						   String recid6=IDGenerate.getZJID("DKJL");
	    		    						   loanApproveRecEntity6.setId(recid6);
	    		    						   loanApproveRecEntity6.setRegion_id(RegionId);
	    		    						   loanApproveRecEntity6.setFp_id(fpid);
	    		    						   loanApproveRecEntity6.setLa_id(syptbankapply.getLaId());
	    		    						   loanApproveRecEntity6.setLas_id("DKZT06");
	    		    						   loanApproveRecEntity6.setLac_id("YDKZT06");
	    		    						   loanApproveRecEntity6.setLar_credit_quota("0");
	    		    						   loanApproveRecEntity6.setLar_loan_deadline(0);
	    		    						   loanApproveRecEntity6.setLar_begin(failApproeday);
	    		    						   loanApproveRecEntity6.setLar_end(failApproeday);
	    		    						   loanApproveRecEntity6.setLar_rate("0");
	    		    						   loanApproveRecEntity6.setLar_isoverlay_area(0);
	    		    						   loanApproveRecEntity6.setLar_opinion("受理不通过");
	    		    						   loanApproveRecEntity6.setCreatorid(Manager);
	    		    						   loanApproveRecEntity6.setCreatetime(new Date());
	    		    						   loanApproveRecEntity6.setUpdatorid(Manager);
	    		    						   loanApproveRecEntity6.setUpdatetime(new Date());
	    		    						   try {
	    		    							   loanApproveQueryService.insertRec3(loanApproveRecEntity6);
	    		    						   } catch (Exception e) {
	    		    							// TODO: handle exception
	    		    							   logger.info("=================受理不通过插入贷款记录表失败==================");
	    		    							   e.printStackTrace();
	    		    						   }
	    		    						   
	    		    						   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
	    		    						   loanApproveQueryEntity.setId(syptbankapply.getLaId());
	    		    						   loanApproveQueryEntity.setUpdatorid(Manager);				
	    		    						   loanApproveQueryService.updatelastauts6(loanApproveQueryEntity);
	    		    						   //修改对照表中授信临时表ID   
	    		    						   Syptbankapply syptbankapply4=new Syptbankapply();
	    		    						   //授信失败存N+businessid
	    		    						   syptbankapply4.setTempfinalId("N"+dhFailure.getBusinessid());
	    		    						   syptbankapply4.setTempendId("N");
	    		    						   syptbankapply4.setLfId(syptbankapply.getLfId());
	    		    						   syptbankApplyService.changetemp(syptbankapply4);
	    		    					}else{
	    		    						//查询授信申请临时表 查找是否授信
	    		    						Map<String, Object> map1 = new HashMap<String, Object>();
	    		    						//暂定通过纳税人识别号 或者社会统一信用代码查询
	    		    						//修改为通过证件号
	    		    						//map1.put("nsrsbh", syptbankapply.getNsrsbh());
	    		    						//map1.put("businessid", syptbankapply.getLfId());
	    		    						Map<String, Object> parameters=new HashMap<String, Object>();					
	    		    						parameters.put("at_nsrsbh", syptbankapply.getNsrsbh());	
	    		    						parameters.put("isdkxx","yes");	
	    		    						List<AuthorizationrecordEntity> lists = authorizationrecordService.querySqByPage(parameters);
	    		    						if(lists.size()>0){
	    		    							map1.put("zjhm",lists.get(0).getAt_frsfz());
	    		    						}else{
	    		    							map1.put("zjhm","");
	    		    						}
	    		    						map1.put("isdesc","yes");
	    		    						List<DhSxjgxx> dhSxjgxxs=dhDhrmxxMapper.querysxjgxxListAll(map1);
	    		    						//查询是否推送了授信数据 如果推送了 授信是否有授信利率   
	    		    						//如果存在授信数据
	    		    						if(dhSxjgxxs.size()>0){
	    		    							//判定该授信是通过还是不通过 0为授信 1 受理不通过   受理不通过 从小微平台取数
	    		    							DhSxjgxx dhSxjgxx=dhSxjgxxs.get(0);
	    		    							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    		    						    Date Approvedate = sdf.parse(dhSxjgxx.getApprovedate());
	    		    							//通过
	    		    							if(("0").equals(dhSxjgxx.getAdmissionresult())){
	    		    								//有授信利率
	    		    								if(dhSxjgxx.getContractrate()!=null&&!("").equals(dhSxjgxx.getContractrate())){
	    		    									//构造授信数据
	    		    				    				   //构造受理信息
	    		    				    				   LoanApproveRecEntity loanApproveRecEntity2=new LoanApproveRecEntity();
	    		    				    				   String recid2=IDGenerate.getZJID("DKJL");
	    		    				    				   loanApproveRecEntity2.setId(recid2);
	    		    				    				   loanApproveRecEntity2.setRegion_id(RegionId);
	    		    				    				   loanApproveRecEntity2.setFp_id(fpid);
	    		    				    				   loanApproveRecEntity2.setLa_id(syptbankapply.getLaId());
	    		    				    				   loanApproveRecEntity2.setLas_id("DKZT02");
	    		    				    				   loanApproveRecEntity2.setLac_id("YDKZT02");
	    		    				    				   loanApproveRecEntity2.setLar_credit_quota("0");
	    		    				    				   loanApproveRecEntity2.setLar_loan_deadline(0);
	    		    				    				   loanApproveRecEntity2.setLar_begin(Approvedate);
	    		    				    				   loanApproveRecEntity2.setLar_end(Approvedate);
	    		    				    				   loanApproveRecEntity2.setLar_rate("0");
	    		    				    				   loanApproveRecEntity2.setLar_isoverlay_area(0);
	    		    				    				   loanApproveRecEntity2.setLar_opinion("同意受理");
	    		    				    				   loanApproveRecEntity2.setCreatorid(Manager);
	    		    				    				   loanApproveRecEntity2.setCreatetime(new Date());
	    		    				    				   loanApproveRecEntity2.setUpdatorid(Manager);
	    		    				    				   loanApproveRecEntity2.setUpdatetime(new Date());
	    		    				    				   try {
	    		    				    					   loanApproveQueryService.insertRec3(loanApproveRecEntity2);
	    		    									   } catch (Exception e) {
	    		    										// TODO: handle exception
	    		    										   logger.info("==================受理通过插入授信记录表失败=====================");
	    		    										   e.printStackTrace();
	    		    									   }
	    		    				    				   //构造授信数据
	    		    				    				   LoanApproveFinalEntity loanApproveFinalEntity=new LoanApproveFinalEntity();
	    		    				    				   String lfid2=IDGenerate.getZJID("DKWC");
	    		    				    				   loanApproveFinalEntity.setId(lfid2);
	    		    				    				   loanApproveFinalEntity.setFp_id(fpid);
	    		    				    				   loanApproveFinalEntity.setRegion_id(RegionId);
	    		    				    				   loanApproveFinalEntity.setLa_id(syptbankapply.getLaId());
	    		    				    				   loanApproveFinalEntity.setLac_id("YDKZT03");
	    		    				    				   loanApproveFinalEntity.setLar_opinion("同意");
	    		    				    				   loanApproveFinalEntity.setCreatorid(Manager);
	    		    				    				   loanApproveFinalEntity.setCreatetime(new Date());
	    		    				    				   loanApproveFinalEntity.setUpdatorid(Manager);
	    		    				    				   loanApproveFinalEntity.setUpdatetime(new Date());
	    		    				    				   try {
	    		    				    					   loanApproveService.insertFinalchannelid(loanApproveFinalEntity);
	    		    									   } catch (Exception e) {
	    		    										// TODO: handle exception
	    		    										   logger.info("=================插入产品申贷成功记录表失败==================");
	    		    										   e.printStackTrace();
	    		    									   } 
	    		    				    				   //构造授信记录信息
	    		    				    				   LoanApproveRecEntity loanApproveRecEntity3=new LoanApproveRecEntity();
	    		    				    				   String recid3=IDGenerate.getZJID("DKJL");
	    		    				    				   loanApproveRecEntity3.setId(recid3);
	    		    				    				   loanApproveRecEntity3.setRegion_id(RegionId);
	    		    				    				   loanApproveRecEntity3.setFp_id(fpid);
	    		    				    				   loanApproveRecEntity3.setLa_id( syptbankapply.getLaId());
	    		    				    				   loanApproveRecEntity3.setLas_id("DKZT03");
	    		    				    				   loanApproveRecEntity3.setLac_id("YDKZT03");
	    		    				    				   loanApproveRecEntity3.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(dhSxjgxx.getContractamt())/10000)));
	    		    				    				   loanApproveRecEntity3.setLar_loan_deadline(Integer.parseInt(dhSxjgxx.getContractterm()));
	    		    				    				   loanApproveRecEntity3.setLar_begin(Approvedate);
	    		    				    				   loanApproveRecEntity3.setLar_end(Approvedate);
	    		    				    				   loanApproveRecEntity3.setLar_rate(dhSxjgxx.getContractrate());
	    		    				    				   loanApproveRecEntity3.setLar_isoverlay_area(0);
	    		    				    				   loanApproveRecEntity3.setLar_opinion("同意授信");
	    		    				    				   loanApproveRecEntity3.setCreatorid(Manager);
	    		    				    				   loanApproveRecEntity3.setCreatetime(new Date());
	    		    				    				   loanApproveRecEntity3.setUpdatorid(Manager);
	    		    				    				   loanApproveRecEntity3.setUpdatetime(new Date());
	    		    				    				   if(("RM03").equals(dhSxjgxx.getPaybacktype())){
	    		    				    					   loanApproveRecEntity3.setRw_id("HKFS05");  
	    		    				    				   }else if(("FB06").equals(dhSxjgxx.getPaybacktype())){
	    		    				    					   loanApproveRecEntity3.setRw_id("HKFS15");  
	    		    				    				   }else if(("FB07").equals(dhSxjgxx.getPaybacktype())){
	    		    				    					   //修改还款方式
	    		    				    					   loanApproveRecEntity3.setRw_id("HKFS01");  
	    		    				    				   }else if(("FB08").equals(dhSxjgxx.getPaybacktype())){
	    		    				    					   loanApproveRecEntity3.setRw_id("HKFS17");  
	    		    				    				   }else if(("RM01").equals(dhSxjgxx.getPaybacktype())){
	    		    				    					   loanApproveRecEntity3.setRw_id("HKFS18");  
	    		    				    				   }else if(("FB05").equals(dhSxjgxx.getPaybacktype())){
	    		    				    					   loanApproveRecEntity3.setRw_id("HKFS19");  
	    		    				    				   }
	    		    				                       try {
	    		    				                    	   loanApproveQueryService.insertRec3(loanApproveRecEntity3);
	    		    									   } catch (Exception e) {
	    		    										// TODO: handle exception
	    		    										   logger.info("====================授信通过插入授信记录表失败=======================");
	    		    										   e.printStackTrace();
	    		    									    } 
	    		    				    				   //修改对照表中授信临时表ID   
	    		    				    				   Syptbankapply syptbankapply2=new Syptbankapply();
	    		    				    				   //授信成功存合同号
	    		    				    				   syptbankapply2.setTempfinalId(dhSxjgxx.getHth());
	    		    				    				   syptbankapply2.setLaId(laid);
	    		    				    				   syptbankApplyService.changetemp(syptbankapply2);
	    		    				    				   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
	    		    				    				   loanApproveQueryEntity.setId(syptbankapply.getLaId());
	    		    				    				   loanApproveQueryEntity.setUpdatorid(Manager);
	    		    				    				   loanApproveQueryEntity.setUpdatetime(new Date());
	    		    				    			       loanApproveQueryService.updatelastauts3(loanApproveQueryEntity);

	    		    								}
	    		    								
	    		    							}
	    		    						}
	    		    					}

	    		    				}
	    		    				//再次查询 该数据fianlid是否为空
	    		    				Map<String, Object> cxparameter = new HashMap<String, Object>();
	    		    				cxparameter.put("laId",syptbankapply.getLaId());
	    		    				List<Syptbankapply> cxlist=syptbankApplyService.findAll(cxparameter);
	    		    				if(cxlist.size()>0){
	    		    					Syptbankapply syptbankapply2=cxlist.get(0);
	    		    					String fianlid=syptbankapply2.getTempfinalId();
	    		    					String endid=syptbankapply2.getTempendId();
	    		    					if(fianlid!=null&&!("").equals(fianlid)&&!("N").equals(endid)&&(("").equals(endid)||endid==null)){
	    		    						//根据合同号查询终止数据
	    		    						Map<String, Object> htmap = new HashMap<String, Object>();
	    		    						htmap.put("businessid",syptbankapply2.getTempfinalId());
	    		    						htmap.put("isdesc","yes");
	    		    						List<DhDkjsxx> dhDkjsxxs=dhDhrmxxMapper.querydkjsxxListAll(htmap);
	    		    						if(dhDkjsxxs.size()>0){
	    		    							DhDkjsxx dhDkjsxx=dhDkjsxxs.get(0);
	    		    							if(("300").equals(dhDkjsxx.getHtzt()) &&("0").equals(dhDkjsxx.getEndbalance())){
	    		    								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    		    				    		    Date enddate = sdf.parse(dhDkjsxx.getEnddate());
	    		    								//构造贷款结束数据
	    		    								Map<String, Object> paMap = new HashMap<String, Object>();
	    		    					   			paMap.put("laId",syptbankapply2.getLaId());
	    		    					   		    List<LoanApplyFinalEntity> entities=loanApproveQueryService.findByEx(paMap);
	    		    								   //构造授信终止
	    		    					   		    	LoanApplyFinalEntity loanApproveFinalEntity=entities.get(0);
	    		    								    LoanApplyFinalEndEntity applyFinalEndEntity=new LoanApplyFinalEndEntity();
	    		    								    applyFinalEndEntity.setLaf_id(loanApproveFinalEntity.getId().toString());
	    		    								   if(("00").equals(dhDkjsxx.getEndtype())){
	    		    									   applyFinalEndEntity.setLafs_id("DHZT02");//贷款按期完成
	    		    								   }else if (("01").equals(dhDkjsxx.getEndtype())) {
	    		    									   applyFinalEndEntity.setLafs_id("DHZT01");//贷款手动终止
	    		    									   
	    		    								   }else if(("11").equals(dhDkjsxx.getEndtype())) {
	    		    									   applyFinalEndEntity.setLafs_id("DHZT03");//其他非正常终止
	    		    									   
	    		    								   } 
	    		    								   applyFinalEndEntity.setBankloan_type("DHPJ0"+dhDkjsxx.getEndclass());
	    		    								   applyFinalEndEntity.setLae_credit_quota(String.valueOf(loanApproveFinalEntity.getLoanApproveRecList().getLar_credit_quota()));
	    		    								   applyFinalEndEntity.setLae_overdue_count(Integer.parseInt(dhDkjsxx.getOverduecount()));
	    		    								   applyFinalEndEntity.setCreatorid(Manager);
	    		    								   applyFinalEndEntity.setNsrmc(dhDkjsxx.getEntname());
	    		    								   applyFinalEndEntity.setNsrbh(dhDkjsxx.getEnttaxid());
	    		    								   applyFinalEndEntity.setLae_endDate(enddate);
	    		    								   applyFinalEndEntity.setCreatetime(new Date());
	    		    								   applyFinalEndEntity.setUpdatorid(Manager);
	    		    								   applyFinalEndEntity.setUpdatetime(new Date());
	    		    								   loanApproveQueryService.insertItem(applyFinalEndEntity);
	    		    								   LoanApproveRecEntity loanApproveRecEntity4=new LoanApproveRecEntity();
	    		    								   loanApproveRecEntity4.setRegion_id(RegionId);
	    		    								   loanApproveRecEntity4.setFp_id(fpid);
	    		    								   loanApproveRecEntity4.setLa_id(syptbankapply.getLaId());
	    		    								   loanApproveRecEntity4.setLas_id("DKZT07");
	    		    								   loanApproveRecEntity4.setLac_id("YDKZT03");
	    		    								   loanApproveRecEntity4.setLar_credit_quota( String.valueOf((int)Double.parseDouble(loanApproveFinalEntity.getLoanApproveRecList().getLar_credit_quota())));
	    		    								   loanApproveRecEntity4.setLar_loan_deadline(loanApproveFinalEntity.getLoanApproveRecList().getLar_loan_deadline());
	    		    								   loanApproveRecEntity4.setLar_begin(loanApproveFinalEntity.getLoanApproveQueryEntity().getLa_apply_time());
	    		    								   loanApproveRecEntity4.setLar_end(enddate);
	    		    								   loanApproveRecEntity4.setLar_rate(loanApproveFinalEntity.getLoanApproveRecList().getLar_rate());
	    		    								   loanApproveRecEntity4.setLar_isoverlay_area(0);
	    		    								   if(("00").equals(dhDkjsxx.getEndtype())){
	    		    									   loanApproveRecEntity4.setLafs_id("DHZT02");//贷款按期完成
	    		    								   }else if (("01").equals(dhDkjsxx.getEndtype())) {
	    		    									   loanApproveRecEntity4.setLafs_id("DHZT01");//贷款手动终止	    		    									   
	    		    								   }else if(("11").equals(dhDkjsxx.getEndtype())) {
	    		    									   applyFinalEndEntity.setLafs_id("DHZT03");//其他非正常终止	    		    									   
	    		    								   }
	    		    								   loanApproveRecEntity4.setLar_opinion("完成");
	    		    								   loanApproveRecEntity4.setCreatorid(Manager);
	    		    								   loanApproveRecEntity4.setUpdatorid(Manager);
	    		    								   loanApproveRecEntity4.setUpdatetime(new Date());
	    		    								   loanApproveRecEntity4.setCreatetime(new Date());
	    		    								   loanApproveQueryService.insertRec7(loanApproveRecEntity4);
	    		    								   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
	    		    								   loanApproveQueryEntity.setId(syptbankapply2.getLaId());
	    		    								   loanApproveQueryEntity.setUpdatorid(Manager);	
	    		    								   loanApproveQueryService.updatela(loanApproveQueryEntity);
	    		    								   LoanApproveFinalEntity applyFinalEntity=new LoanApproveFinalEntity();
	    		    								   if(("00").equals(dhDkjsxx.getEndtype())){
	    		    									   applyFinalEntity.setLafs_id("DHZT02");//贷款按期完成
	    		    								   }else if(("01").equals(dhDkjsxx.getEndtype())) {
	    		    									   applyFinalEntity.setLafs_id("DHZT01");//贷款手动终止	    		    									   
	    		    								   }else if(("11").equals(dhDkjsxx.getEndtype())) {
	    		    									   applyFinalEntity.setLafs_id("DHZT03");//其他非正常终止    		    									   
	    		    								   }
	    		    								   applyFinalEntity.setId(loanApproveFinalEntity.getId());
	    		    								   loanApproveQueryService.updatelaf2(applyFinalEntity);
	    		    								   //修改对照表中授信临时表ID   
	    		    								    
	    		    								   Syptbankapply syptbankapply3=new Syptbankapply();
	    		    								   //结束存bussinessid  也是合同号
	    		    								   syptbankapply3.setTempendId(dhDkjsxx.getBusinessid());
	    		    								   syptbankapply3.setLaId(laid);
	    		    								   syptbankApplyService.changetemp(syptbankapply3);
	    		    								   //更新纳税人用户合同号状态
	    		    								   //根据贷款id查询用户编号
	    		    								   LoanApproveQueryEntity loanQueryEntity = loanApproveQueryService.findlaById(laid);
	    		    								   if(loanQueryEntity!=null&&!("").equals(loanQueryEntity)){
	    		    									   String creatorid=loanQueryEntity.getCreatorid();
	    		    									   if(!("").equals(creatorid)){
	    		    										 //更新纳税人用户合同号状态
	    		    										   try {
	    		    											   Map<String, Object> uphtztMap = new HashMap<String, Object>();
	    		    											   uphtztMap.put("yxs","N");
	    		    											   uphtztMap.put("nsryhid", creatorid);
	    		    											   registerService.updatehtzt(uphtztMap);
	    		    											    
	    		    										   } catch (Exception e) {
	    		    											   logger.info("=====================更新纳税人合同状态失败=================");
	    		    											   e.printStackTrace();
	    		    										   }
	    		    									   }else{
	    		    										   logger.info("=====================纳税人用户不存在=================");
	    		    									   }
	    		    								   }

	    		    							}
	    		    							
	    		    						}
	    		    						
	    		    					}
	    		    				  }
	    		    				
	    		    			}else{
	    		    				logger.info("对照数据中的银税平台申请单id或银行申请单id不为空");
	    		    			}
	    		    			
	    				}
	    			}
    			}else{
    				if((laid!=null&&!("").equals(laid))&&(userid==null||("").equals(userid))){
    					logger.info("编号为"+userid+"用户不存在");
    				}else{
    					logger.info("对照数据中的银税平台申请单id或银行申请单id不为空");
    				}
    				
    			}
    			
	    		
	    	}

	    
	    }
	
}*/
	/**
	 *1.认证成功后构造一条申请单数据（tb_loan_apply），并将申请单id放到对照表中（sypt_bank_apply），认证不成功的不构造申请单数据
	 * 2.构造授信通过数据
	 * 2.1查找授信成功的 数据根据身份证号码过滤最新一条授权记录表（tb_authorization_record）中申请单id，通过这个申请单id 找到对照表（sypt_bank_apply）的贷款申请表中的id
	 * 将这条记录构造成授信成功的数据，在rec表（tb_loan_approve_rec）中新增一条记录，在final表（tb_loan_apply_final）中新增一条记录
	 * 2.2根据贷款合同号去查找对应表（sypt_bank_apply）中申请单id，将这条贷款申请tb_loan_apply）数据改为贷款结清状态，并在rec表（tb_loan_approve_rec）中新增一条记录，
	 * 同时在end表（tb_loan_applyend）中新增一条记录
	 * @Author Zhongyj
	 * @date 2018-4-19 下午3:19:28
	 * @throws ParseException
	 */
	public void automaticFinalrec() throws ParseException {
		/***构建授信成功的数据***/
		createFinalTableData();
		/***构造结清信息****/
		createEndTableData();
	}

    /**
     * 
     * @Author Zhongyj
     * @date 2018-4-16 下午7:46:46
     */
	private void  createFinalTableData() {
		String indcertid = null;//授信日期
		String approvedate = null;// 授信额度
		String contractamt = null;//授信利率
		String contractrate = null;//授信期限
		String contractterm = null;//还款方式
		String paybacktype = null;//还款方式
		String hth = null;//授信时间
	    Date Approvedate =null; //产品id
		String fpId = null;//申请单id
		String laid = null;//税银平台用户id
		String syptId = null;//用户id
		String regionId = null;//区域id
		String temp_finalId = null;//合同编号
		String sqdid = null;//银行业务流水编号 也是申请单id
        try {
		/*****获取最新的授信数据 获取当前的时间减去一天 然后匹配tb_dh_sxjgxx表中的 APPROVEDATE 获取增量的数据*****/
		String date = getData();
		List<Map<String, Object>> finalLists = gxgsService.getFinalTableData(date);
		if (finalLists!=null && finalLists.size()>0) {
		   LoanApproveRecEntity loanApproveRecEntity2=new LoanApproveRecEntity();
		   LoanApproveFinalEntity loanApproveFinalEntity=new LoanApproveFinalEntity();
		   LoanApproveRecEntity loanApproveRecEntity3=new LoanApproveRecEntity();
		   Syptbankapply syptbankapply2=new Syptbankapply();
		   LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			for (int i = 0; i < finalLists.size(); i++) {
				Map<String, Object> finalMap = finalLists.get(i);
				if (finalMap != null) {
					indcertid = String.valueOf(finalMap.get("INDCERTID"));
					approvedate = String.valueOf(finalMap.get("APPROVEDATE"));
					contractamt = String.valueOf(finalMap.get("CONTRACTAMT"));
					contractrate = String.valueOf(finalMap.get("CONTRACTRATE"));
					contractterm = String.valueOf(finalMap.get("CONTRACTTERM"));
					paybacktype = String.valueOf(finalMap.get("PAYBACKTYPE"));
					hth = String.valueOf(finalMap.get("HTH"));
					sqdid = String.valueOf(finalMap.get("BUSINESSID"));
					Approvedate = sdf.parse(approvedate);
				}
				//根据合同编号找到对应申请单id并将此条申请构造成授信成功的，构造授信成功的数据完成后根据身份证号码查找到这个用户的其他申请单信息，并且构造为授信失败的数据
				//Map<String, Object> bankApplyMap = gxgsService.getBankApplyByHth(hth);
				Map<String, Object> bankApplyMap = gxgsService.getBankApplyBySqdid(sqdid);
				if (bankApplyMap!=null) {
					/****************增加次判断是避免String.valueOf（）方法 在值为null时返回字符串“null”*********************************/
					if (bankApplyMap.get("temp_finalId")!=null) {
						temp_finalId = String.valueOf(bankApplyMap.get("temp_finalId"));
					}else {
						temp_finalId = null;
					}
					/*****************如果 temp_finalId不为空 说明此条记录已经构造过了*****************************************/
					if (temp_finalId==null||"".equals(temp_finalId)) {
						fpId = String.valueOf(bankApplyMap.get("sypt_fpid"));
						laid = String.valueOf(bankApplyMap.get("sypt_laId"));
						syptId = String.valueOf(bankApplyMap.get("sypt_userId"));
						regionId = String.valueOf(bankApplyMap.get("regionid"));
						
						String recid2=IDGenerate.getZJID("DKJL");
						loanApproveRecEntity2.setId(recid2);
						loanApproveRecEntity2.setRegion_id(regionId);
						loanApproveRecEntity2.setFp_id(fpId);
						loanApproveRecEntity2.setLa_id(laid);
						loanApproveRecEntity2.setLas_id("DKZT02");
						loanApproveRecEntity2.setLac_id("YDKZT02");
						loanApproveRecEntity2.setLar_credit_quota("0");
						loanApproveRecEntity2.setLar_loan_deadline(0);
						loanApproveRecEntity2.setLar_begin(Approvedate);
						loanApproveRecEntity2.setLar_end(Approvedate);
						loanApproveRecEntity2.setLar_rate("0");
						loanApproveRecEntity2.setLar_isoverlay_area(0);
						loanApproveRecEntity2.setLar_opinion("同意受理");
						loanApproveRecEntity2.setCreatorid(syptId);
						loanApproveRecEntity2.setCreatetime(new Date());
						loanApproveRecEntity2.setUpdatorid(syptId);
						loanApproveRecEntity2.setUpdatetime(new Date());
						/*******************将受理成功的信息插入贷款记录表中****************************/
						int resultInsertRec3 = loanApproveQueryService.insertRec3(loanApproveRecEntity2);
						/**********构造授信数据*****************/
						String lfid2=IDGenerate.getZJID("DKWC");
						loanApproveFinalEntity.setId(lfid2);
						loanApproveFinalEntity.setFp_id(fpId);
						loanApproveFinalEntity.setRegion_id(regionId);
						loanApproveFinalEntity.setLa_id(laid);
						loanApproveFinalEntity.setLac_id("YDKZT03");
						loanApproveFinalEntity.setLar_opinion("同意");
						loanApproveFinalEntity.setCreatorid(syptId);
						loanApproveFinalEntity.setCreatetime(new Date());
						loanApproveFinalEntity.setUpdatorid(syptId);
						loanApproveFinalEntity.setUpdatetime(new Date());
						/********************构造受理成后将信息存入授信表 *****************************/
						int resultInsertFinalchannelid = loanApproveService.insertFinalchannelid(loanApproveFinalEntity);
						/*********构造授信记录信息**********************/
						String recid3=IDGenerate.getZJID("DKJL");
						loanApproveRecEntity3.setId(recid3);
						loanApproveRecEntity3.setRegion_id(regionId);
						loanApproveRecEntity3.setFp_id(fpId);
						loanApproveRecEntity3.setLa_id(laid);
						loanApproveRecEntity3.setLas_id("DKZT03");
						loanApproveRecEntity3.setLac_id("YDKZT03");
						
						loanApproveRecEntity3.setLar_credit_quota(String.valueOf((int) Math.ceil(Double.parseDouble(contractamt)/10000)));
						loanApproveRecEntity3.setLar_loan_deadline(Integer.parseInt(contractterm));
						loanApproveRecEntity3.setLar_begin(Approvedate);
						loanApproveRecEntity3.setLar_end(Approvedate);
						loanApproveRecEntity3.setLar_rate(contractrate);
						loanApproveRecEntity3.setLar_isoverlay_area(0);
						loanApproveRecEntity3.setLar_opinion("同意授信");
						loanApproveRecEntity3.setCreatorid(syptId);
						loanApproveRecEntity3.setCreatetime(new Date());
						loanApproveRecEntity3.setUpdatorid(syptId);
						loanApproveRecEntity3.setUpdatetime(new Date());
						/***修改还款方式****/
						if(("RM03").equals(paybacktype)){
							loanApproveRecEntity3.setRw_id("HKFS05");  
						}else if(("FB06").equals(paybacktype)){
							loanApproveRecEntity3.setRw_id("HKFS15");  
						}else if(("FB07").equals(paybacktype)){
							loanApproveRecEntity3.setRw_id("HKFS01");  
						}else if(("FB08").equals(paybacktype)){
							loanApproveRecEntity3.setRw_id("HKFS17");  
						}else if(("RM01").equals(paybacktype)){
							loanApproveRecEntity3.setRw_id("HKFS18");  
						}else if(("FB05").equals(paybacktype)){
							loanApproveRecEntity3.setRw_id("HKFS19");  
						}
						/******************将授信成功的记录信息存入贷款记录表**************************/
						int resultSxInsertRec3 = loanApproveQueryService.insertRec3(loanApproveRecEntity3);
						loanApproveQueryEntity.setId(laid);
						loanApproveQueryEntity.setUpdatorid(syptId);
						loanApproveQueryEntity.setUpdatetime(new Date());
						/*****************授信成功后 将授权成功的状态更新到贷款申请表中***********************************/
						int resultUpdatelastauts3 = loanApproveQueryService.updatelastauts3(loanApproveQueryEntity);
						syptbankapply2.setTempfinalId(hth);
						syptbankapply2.setLaId(laid);
						int selultChangetemp = 0;
						/****************授权成功后将合同号保存到对造表中************************/
						if (resultInsertFinalchannelid > 0 && resultUpdatelastauts3 > 0) {
							
							selultChangetemp = syptbankApplyService.changetemp(syptbankapply2);
						}
						
						/**根据身份证号码找需要构造成授信失败的申请单id列表过滤规则对照表sypt_bank_apply中平台申请单id不为空并且合同编号不为空 **/
						List<Map<String, Object>> bankApplyList = gxgsService.getBankApplyData(indcertid);
						if (bankApplyList !=null && bankApplyList.size()>0) {
							/***************构造授信失败的数据*********************/
							if (bankApplyList.size() >0) {
								createFailData(bankApplyList);
							}
						}
						if (resultInsertRec3 < 1) {
							logger.info("**********************构造授信成功接口 插入受理成功的记录方法出错*************************");
						} else if( resultInsertFinalchannelid <1) {
							logger.info("**********************构造授信成功接口 构造受理成后将信息存入授信表方法出错*****************");
						} else if (resultSxInsertRec3 <1) {
							logger.info("**********************构造授信成功接口 将授信成功的记录信息存入贷款记录表方法出错************");
						}else if (selultChangetemp <1) {
							logger.info("********************构造授信成功接口 授权成功后将合同号保存到对造表中方法出错****************");
						}else if (resultUpdatelastauts3 <1) {
							logger.info("********************构造授信成功接口 授信成功后 将授权成功的状态更新到贷款申请表中方法出错*****");
						}
						
					}
					
				}
			}
		}
	 } catch (Exception e) {
		   logger.info("**********************授信通过构造数据失败*************************");
		   e.printStackTrace();
	 } 
	}
	
	/**
	 * 构造贷款结清信息
	 * @Author Zhongyj
	 * @date 2018-4-19 下午5:30:37
	 */
	private void createEndTableData() {
		String endType = null;
		String endClass = null;
		//String businessid = null;
		String overduecount = null;
		String entname = null;
		String enttaxid = null;
		String hth = null;
		String fpId = null;
		String laId = null;
		String syptId = null;
		String regionId = null;
		Map<String, Object> dhDkjsxxMap =null;
		String temp_endId = null;//合同编号
		String sqdid = null;
		try {
		/***获取最新的贷款结清信息获取当前的时间减去一天 然后匹配tb_dh_dkjsxx 表中的ENDDATE 获取增量的数据****/
		List<Map<String, Object>> endLists = gxgsService.getEndTableData(getData());
		if (endLists != null && endLists.size()>0) {
			//Map<String, Object> dhDkjsxxMap=endLists.get(0);
			LoanApproveRecEntity loanApproveRecEntity4=new LoanApproveRecEntity();
			LoanApproveFinalEntity applyFinalEntity=new LoanApproveFinalEntity();
			LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
			for (int i = 0; i < endLists.size(); i++) {
				dhDkjsxxMap=endLists.get(i);
				hth = String.valueOf(dhDkjsxxMap.get("BH"));
				endType= String.valueOf(dhDkjsxxMap.get("ENDTYPE"));
				endClass = String.valueOf(dhDkjsxxMap.get("ENDCLASS"));
				//businessid = String.valueOf(dhDkjsxxMap.get("BUSINESSID"));
				overduecount = String.valueOf(dhDkjsxxMap.get("OVERDUECOUNT"));
				entname = String.valueOf(dhDkjsxxMap.get("ENTNAME"));
				enttaxid = String.valueOf(dhDkjsxxMap.get("ENTTAXID"));
				sqdid = String.valueOf(dhDkjsxxMap.get("BUSINESSID"));
				/****根据合同号码找到对应的申请单id*****/
				//Map<String, Object> bankApplyMap = gxgsService.getBankApplyDataByHth(hth);
				Map<String, Object> bankApplyMap = gxgsService.getBankApplyBySqdid(sqdid);
				if (bankApplyMap != null) {
					laId = String.valueOf(bankApplyMap.get("sypt_laId"));
					fpId = String.valueOf(bankApplyMap.get("sypt_fpid"));
					regionId = String.valueOf(bankApplyMap.get("regionid"));
					syptId = String.valueOf(bankApplyMap.get("sypt_userId"));
					/****************增加此判断是避免String.valueOf()方法在返回值为null时返回 "null"字符串********************************/
					if (bankApplyMap.get("temp_endId")!=null) {
						temp_endId = String.valueOf(bankApplyMap.get("temp_endId"));
					}else {
						temp_endId = null;
					}
				}
				/******************* 如果 temp_endId 合同编号为空说明此条数据没有构造结清信息******************/
				if (temp_endId==null ||"".equals(temp_endId)) {
					if(("300").equals(dhDkjsxxMap.get("HTZT")) &&("0").equals(dhDkjsxxMap.get("ENDBALANCE"))){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
						Date enddate = null;;
						try {
							enddate = sdf.parse(String.valueOf(dhDkjsxxMap.get("ENDDATE")));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						/******构造贷款结束数据****/
						Map<String, Object> paMap = new HashMap<String, Object>();
						paMap.put("laId",laId);
						List<LoanApplyFinalEntity> entities=loanApproveQueryService.findByEx(paMap);
						/*****构造授信终止*****/
						LoanApplyFinalEntity loanApproveFinalEntity=entities.get(0);
						LoanApplyFinalEndEntity applyFinalEndEntity=new LoanApplyFinalEndEntity();
						applyFinalEndEntity.setLaf_id(loanApproveFinalEntity.getId().toString());
						
						if(("00").equals(endType)){
							loanApproveRecEntity4.setLafs_id("DHZT02");//贷款按期完成
							applyFinalEntity.setLafs_id("DHZT02");//贷款按期完成
							applyFinalEndEntity.setLafs_id("DHZT02");//贷款按期完成
						}else if (("01").equals(endType)) {
							loanApproveRecEntity4.setLafs_id("DHZT01");//贷款手动终止	  
							applyFinalEntity.setLafs_id("DHZT01");//贷款手动终止	 
							applyFinalEndEntity.setLafs_id("DHZT01");//贷款手动终止
						}else if(("11").equals(endType)) {
							applyFinalEndEntity.setLafs_id("DHZT03");//其他非正常终止	  
							applyFinalEntity.setLafs_id("DHZT03");//其他非正常终止   
						}
						applyFinalEndEntity.setBankloan_type("DHPJ0"+endClass);
						applyFinalEndEntity.setLae_credit_quota(String.valueOf(loanApproveFinalEntity.getLoanApproveRecList().getLar_credit_quota()));
						applyFinalEndEntity.setLae_overdue_count(Integer.parseInt(overduecount));
						applyFinalEndEntity.setCreatorid(syptId);
						applyFinalEndEntity.setNsrmc(entname);
						applyFinalEndEntity.setNsrbh(enttaxid);
						applyFinalEndEntity.setLae_endDate(enddate);
						applyFinalEndEntity.setCreatetime(new Date());
						applyFinalEndEntity.setUpdatorid(syptId);
						applyFinalEndEntity.setUpdatetime(new Date());
						/*************将结清信息插入tb_loan_applyend表****************/
						int resultInsertItem = loanApproveQueryService.insertItem(applyFinalEndEntity);
						loanApproveRecEntity4.setRegion_id(regionId);
						loanApproveRecEntity4.setFp_id(fpId);
						loanApproveRecEntity4.setLa_id(laId);
						loanApproveRecEntity4.setLas_id("DKZT07");
						loanApproveRecEntity4.setLac_id("YDKZT03");
						loanApproveRecEntity4.setLar_credit_quota( String.valueOf((int)Double.parseDouble(loanApproveFinalEntity.getLoanApproveRecList().getLar_credit_quota())));
						loanApproveRecEntity4.setLar_loan_deadline(loanApproveFinalEntity.getLoanApproveRecList().getLar_loan_deadline());
						loanApproveRecEntity4.setLar_begin(loanApproveFinalEntity.getLoanApproveQueryEntity().getLa_apply_time());
						loanApproveRecEntity4.setLar_end(enddate);
						loanApproveRecEntity4.setLar_rate(loanApproveFinalEntity.getLoanApproveRecList().getLar_rate());
						loanApproveRecEntity4.setLar_isoverlay_area(0);
						loanApproveRecEntity4.setLar_opinion("完成");
						loanApproveRecEntity4.setCreatorid(syptId);
						loanApproveRecEntity4.setUpdatorid(syptId);
						loanApproveRecEntity4.setUpdatetime(new Date());
						loanApproveRecEntity4.setCreatetime(new Date());
						/*****************贷款结清信息插入到 tb_loan_approve_rec记录表中************************/
						int resultInsertRec7 = loanApproveQueryService.insertRec7(loanApproveRecEntity4);
						loanApproveQueryEntity.setId(laId);
						loanApproveQueryEntity.setUpdatorid(syptId);
						/*******************更新tb_loan_apply表中la_status字段为DKZT07****************************/
						int resultUpdatela = loanApproveQueryService.updatela(loanApproveQueryEntity);
						applyFinalEntity.setId(loanApproveFinalEntity.getId());
						/******************更新tb_loan_apply_final中的lafs_id字段值******************************/
						int resultUpdatelaf2 = loanApproveQueryService.updatelaf2(applyFinalEntity);
						Syptbankapply syptbankapply3=new Syptbankapply();
						syptbankapply3.setTempendId(hth);
						syptbankapply3.setLaId(laId);
						/******************贷款结清构造完成后将合同号插入到sypt_bank_apply中TempendId字段**********/
						int resultChangetemp = syptbankApplyService.changetemp(syptbankapply3);
						
						Map<String, Object> uphtztMap = new HashMap<String, Object>();
						uphtztMap.put("yxs","N");
						uphtztMap.put("nsryhid", syptId);
						/****************贷款结清后更新纳税人基础信息表中的合同状态号*************************/
						int resultUpdateHztStaus = registerService.updatehtzt(uphtztMap);
						if (resultUpdateHztStaus<1) {
							logger.info("**************贷款结清后更新纳税人基础信息表中的合同状态号 方法错误********");
						}else if (resultChangetemp<1) {
							logger.info("******贷款结清构造完成后将合同号插入到sypt_bank_apply中TempendId字段 方法错误*****");
						}else if (resultUpdatelaf2<1) {
							logger.info("*************更新tb_loan_apply_final中的lafs_id字段值方法错误********");
						}else if (resultUpdatela<1) {
							logger.info("*************更新tb_loan_apply表中la_status字段为DKZT07方法错误********");
						}else if (resultInsertRec7<1) {
							logger.info("*************贷款结清信息插入到 tb_loan_approve_rec记录表中 方法错误********");
						}else if (resultInsertItem<1) {
							logger.info("*************将结清信息插入tb_loan_applyend表 方法错误*********************");
						}
					}
				}
			}
		}
	} catch (Exception e) {
		logger.info("**********************构造贷款结清信息失败*********************************");
		e.printStackTrace();
	}
		
	}
	
	/**
	 * 构造授信失败的数据
	 * @Author Zhongyj
	 * @date 2018-4-17 上午9:47:09
	 * @param lists
	 */
	private void createFailData(List<Map<String, Object>> lists){
		String laId = null;
		String syptId = null;
		String fpid = null;
		String regionId = null;
		try {
			LoanApproveRecEntity loanApproveRecEntity=new LoanApproveRecEntity();
			LoanApproveQueryEntity loanApproveQueryEntity=new LoanApproveQueryEntity();
			/**********获取列表中的第一条数据 拿到一些固定值***********************/
			Map<String, Object> map = lists.get(0);
			if (map != null) {
				fpid = String.valueOf(map.get("sypt_fpid"));
				regionId = String.valueOf(map.get("regionid"));
				/*************将固定不便的值先放进实体 避免循环取数***************/
				loanApproveRecEntity.setRegion_id(regionId);
				loanApproveRecEntity.setFp_id(fpid);
				loanApproveRecEntity.setLas_id("DKZT06");
				loanApproveRecEntity.setLac_id("YDKZT06");
				loanApproveRecEntity.setLar_credit_quota("0");
				loanApproveRecEntity.setLar_loan_deadline(0);
				loanApproveRecEntity.setLar_rate("0");
				loanApproveRecEntity.setLar_isoverlay_area(0);
				loanApproveRecEntity.setLar_opinion("受理不通过");
			}
			for (int i = 0; i < lists.size(); i++) {
				/************获取到需要构造授信失败的数据的id，并且找到申请单id***********/
				map = lists.get(i);
				if (map != null) {
					laId = String.valueOf(map.get("sypt_laId"));
					syptId = String.valueOf(map.get("sypt_userId"));
					/*******构造受理未通过信息*************************/
					String recid6=IDGenerate.getZJID("DKJL");
					loanApproveRecEntity.setId(recid6);
					loanApproveRecEntity.setLa_id(laId);
					loanApproveRecEntity.setLar_begin(new Date());
					loanApproveRecEntity.setLar_end(new Date());
					loanApproveRecEntity.setCreatorid(syptId);
					loanApproveRecEntity.setCreatetime(new Date());
					loanApproveRecEntity.setUpdatorid(syptId);
					loanApproveRecEntity.setUpdatetime(new Date());
				}
				/***********插入贷款记录表tb_loan_approve_rec*************/
				int resultInsertRec = loanApproveQueryService.insertRec(loanApproveRecEntity);
				loanApproveQueryEntity.setId(laId);
				loanApproveQueryEntity.setUpdatorid(syptId);
				/**************更新tb_loan_apply表中的贷款状态************/
				int resultUpdatelastauts6 = loanApproveQueryService.updatelastauts6(loanApproveQueryEntity);
				
				if (resultInsertRec < 1) {
					logger.info("******构造授权失败方法  插入贷款记录表tb_loan_approve_rec 失败******");
				}else if (resultUpdatelastauts6 < 1) {
					logger.info("******构造授权失败方法  更新tb_loan_apply表中的贷款状态 失败**********");
				}
			}
		} catch (Exception e) {
			logger.info("****************构造授权失败的数据失败************************");
			e.printStackTrace();
		}
	}
	/**
	 * 获取前一天的时间
	 * @Author Zhongyj
	 * @date 2018-4-19 下午3:55:10
	 * @return
	 */
	private String getData() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Date date=new Date();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return sdf.format(date);
	}

}
