package com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.mapper.LoanApproveBatchMapper;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.BatchCheckEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveFinalEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.model.LoanApproveQueryEntity;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveBatchService;
import com.dcits.govsbu.sd.taxbankplatform.loanapprove.service.LoanApproveQueryService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;


@Service("loanApproveBatchService")
public class LoanApproveBatchServiceImpl extends AbstractService<LoanApproveFinalEntity, String> implements LoanApproveBatchService{

	@Autowired
	private LoanApproveBatchMapper loanApproveBatchMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApproveBatchMapper);
	}
	
	@Autowired
	LoanApproveQueryService loanApproveQueryService;
	
	//创建一个审核实体
	@Override
	public LoanApproveFinalEntity getSHEntity(BatchCheckEntity batchCheckEntity,UserEntity sessionUser){
		//根据银行申请序号，获取申请id
		String yhsqxh = batchCheckEntity.getYhsqxh();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("yhsqxh",yhsqxh);
		map.put("org_id", sessionUser.getOrgid());//限定本组织下的申请，A组织不可以审批B组织的申请
		Map<String,Object> resultMap = loanApproveBatchMapper.getLaIdByNsrsbh(map);
		//该识别号没有可授信的申请
		if(resultMap == null)
			return null;
		String laid = (String)resultMap.get("la_id");
		Long type = (long)resultMap.get("la_status");
		
		//调用接口获取数据，通过申请id
		LoanApproveQueryEntity query = loanApproveQueryService.findById(laid);
		//申请审批实体
		LoanApproveFinalEntity loanApproveFinalEntity = new LoanApproveFinalEntity();
		
		//获取审批实体信息
		 String region_id = sessionUser.getRegionid();	//区域id
		 String create_id = sessionUser.getId();  		//create_id
		 String fp_id = query.getFp_id();					//产品id
		 String la_id = laid;					//贷款申请id
		 String las_id = query.getLa_status();			//申请状态id
		 
		 String lac_id;								//审批意见代码id，授信（终审）
		 String lar_opinion;							//审批意见
		 if(batchCheckEntity.getResult().equals("002")){//002通过/003不通过
			 lac_id = "YDKZT03";//终审通过
			 lar_opinion = "同意授信（来自批量审批）";	
			 //五个不通过的非必填项
			 loanApproveFinalEntity.setRw_id(batchCheckEntity.getHkfs());//还款方式
			 loanApproveFinalEntity.setLar_credit_quota(Integer.parseInt(batchCheckEntity.getSxje()));//授信额度(万)
			 loanApproveFinalEntity.setLar_loan_deadline(Integer.parseInt(batchCheckEntity.getSxzq()));//贷款期限(月)
			 loanApproveFinalEntity.setLar_begin(batchCheckEntity.getDkqxStart());//贷款期限起
			 loanApproveFinalEntity.setLar_end(batchCheckEntity.getDkqxEnd());//贷款期限止
			 loanApproveFinalEntity.setLar_rate(batchCheckEntity.getSxll());//贷款利率
		 }else{
			 lac_id = "YDKZT04";//终审不通过
			 DateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			 loanApproveFinalEntity.setLar_begin(sdf.format(new Date()));//贷款期限起(数据库不为null)
			 loanApproveFinalEntity.setLar_rate("0");//贷款利率(数据库不为null)
			 lar_opinion = "不同意授信（来自批量审批）";		
		 }
		 
		 
//		 int lar_isoverlay_area;	//是否在产品覆盖区域
		 String lar_bank_name = query.getLoanApproveRecList().get(0).getLar_bank_name();		
		 String lar_bank_account = query.getLoanApproveRecList().get(0).getLar_bank_account();	
		 String lar_contract = query.getLoanApproveRecList().get(0).getLar_contract();		
		 
		 //填充实体
		 loanApproveFinalEntity.setCreatorid(create_id);
		 loanApproveFinalEntity.setRegion_id(String.valueOf(region_id));
		 loanApproveFinalEntity.setFp_id(String.valueOf(fp_id));//产品id
		 loanApproveFinalEntity.setLa_id(String.valueOf(la_id));//申请id
		 loanApproveFinalEntity.setLas_id(String.valueOf(las_id));//审批状态id
		 loanApproveFinalEntity.setLac_id(String.valueOf(lac_id));//审批意见id
		 loanApproveFinalEntity.setLar_bank_name(lar_bank_name);//借款方开户银行
		 loanApproveFinalEntity.setLar_bank_account(lar_bank_account);//借款方银行账号
		 loanApproveFinalEntity.setLar_contract(lar_contract);//贷款合同号
		 loanApproveFinalEntity.setLar_remark("来自批量审批");//备注
		 loanApproveFinalEntity.setLar_opinion(lar_opinion);//审批意见
		 loanApproveFinalEntity.setBatchCheckType(type.intValue());//批量审批前的状态（1，2）
		
		 DateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		 try {
			 if(batchCheckEntity.getSprq() != null){
				 loanApproveFinalEntity.setCreatetime(sdf.parse(batchCheckEntity.getSprq()));//创建日期就是审批日期
			 }else{
				 loanApproveFinalEntity.setCreatetime(new Date());
			 }
		 } catch (ParseException e) {
			e.printStackTrace();
		 }
		 loanApproveFinalEntity.setUpdatetime(new Date());
		 loanApproveFinalEntity.setUpdatorid(create_id);
		 
		 
		return loanApproveFinalEntity;
	}

	//获取所有的还款方式
	@Override
	public List<Map<String,Object>> getHkfsList(){
		return loanApproveBatchMapper.getHkfsList();
	}

	@Override
	public List<Map<String, Object>> getTbpRwId(Map<String, Object> map) {
		return loanApproveBatchMapper.getTbpRwId(map);
	}

	
	
	
}
