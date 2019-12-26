package com.dcits.govsbu.sd.taxbankplatform.bankmanager.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.govsbu.sd.taxbankplatform.bankmanager.mapper.BankManagerMapper;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BMListModel;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BankOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.BusinessRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.DataUpdateRecord;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.FPOrg;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxOptionInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxOptionRecord;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.model.TaxRegInfo;
import com.dcits.govsbu.sd.taxbankplatform.bankmanager.service.BankManagerService;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.statistics.model.CommonEntity;

@Service("bankManagerService")
public class BankManagerServiceImpl extends AbstractService<BMListModel, String> implements BankManagerService{
	public Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BankManagerMapper bankManagerMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(bankManagerMapper);
	}
	
	
	@Override
	public List<BankOrg> queryBankOrg(Map<String,Object> paramaters) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryBankOrg(paramaters);
	}



	@Override
	public List<BMListModel> queryBMList(Map<String,Object> paramaters) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryBMList(paramaters);
	}

	@Override
	public int insert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		//BMListModel bmListModel = new BMListModel();
		//BusinessRegInfo businessRegInfo = new BusinessRegInfo();
		//TaxRegInfo taxRegInfo = new TaxRegInfo();
		//Iterator<Entry<String, Object>> iter = map.entrySet().iterator(); 
       /* while(iter.hasNext()){ 
            Entry<String, Object> entry = iter.next(); 
            String key = entry.getKey(); 
            String value = entry.getValue()==null?null:entry.getValue().toString();
        }*/
		int result=0;
		/*int a = bankManagerMapper.insertBMListModel(bmListModel);
		int b = bankManagerMapper.insertBusinessRegInfo(businessRegInfo);
		int c = bankManagerMapper.insertTaxRegInfo(taxRegInfo);
		if(a>1&&b>1&&c>1)
			result =1;*/ 
		return result;
	}

	@Override
	public int insert(BusinessRegInfo businessRegInfo) {
		// TODO Auto-generated method stub
		return bankManagerMapper.insertBusinessRegInfo(businessRegInfo);
	}

	@Override
	public int insert(TaxRegInfo taxRegInfo) {
		// TODO Auto-generated method stub
		return bankManagerMapper.insertTaxRegInfo(taxRegInfo);
	}

	@Override
	public int insert(BMListModel bmListModel) {
		// TODO Auto-generated method stub
		return bankManagerMapper.insertBMListModel(bmListModel);
	}


	@Override
	public List<BusinessRegInfo> queryBusinessRegInfo(String fpId) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryBusinessRegInfo(fpId);
	}


	@Override
	public TaxRegInfo queryTaxRegInfo(String bankCode) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryTaxRegInfo(bankCode);
	}


	@Override
	public List<TaxOptionInfo> queryTaxOption(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryTaxOption(map);
	}


	@Override
	public int insertTaxOptList(List<TaxOptionRecord> recList) {
		// TODO Auto-generated method stub
		if(recList!=null&&recList.size()>0)
			return bankManagerMapper.insertTaxOptList(recList);
		else return 0;
	}


	@Override
	public void updateBankStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		bankManagerMapper.updateBankStatus(map);
	}


	@Override
	public BMListModel queryBmModel(String fpId,String bankCode) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryBmModel(fpId,bankCode);
	}


	@Override
	public int updateBMModel(BMListModel bmListMode) {
		// TODO Auto-generated method stub
		return bankManagerMapper.updateBMModel(bmListMode);
	}

	@Transactional
	@Override
	public int delAndinsertTaxOptList(List<TaxOptionRecord> recList) {
		// TODO Auto-generated method stub
		if(recList==null||recList.size()<1) return 0;
		String fpId = recList.get(0).getFpId();
		String bankcode = recList.get(0).getBankcode();
		bankManagerMapper.deleteTaxOptionByBKCode(fpId,bankcode);
		int reuslt =bankManagerMapper.insertTaxOptList(recList);
		return reuslt;
	}


	@Override
	public List<TaxOptionInfo> queryTaxOptionTe(Map<?, ?> map) {
		return bankManagerMapper.queryTaxOptionTe(map);
	}

	@Override
	public List<FPOrg> findFPsByBankId(String bankId) {
		List<FPOrg> list = bankManagerMapper.findFPsByBankId(bankId);
		logger.info("*********************添加数据项银行change时的bankId："+bankId+",查询出的产品fpList为："+list);
		return list;
	}

	@Override
	public int recordDataUpdate(DataUpdateRecord dataUpdateRecord) {
		String fpId = dataUpdateRecord.getFpId();
		String bankcode = dataUpdateRecord.getBankcode();
		//查询之前记录的数据项
		List<TaxOptionInfo> fpList = bankManagerMapper.findDataByFpId(fpId,bankcode);
		//将ids转换成字符串
//        List<String> dataIds = fpList.stream().map(TaxOptionInfo::getPkey).collect(Collectors.toList());
		List<String> dataIds = new ArrayList<String>();
		for (TaxOptionInfo taxOptionInfo : fpList) {
			dataIds.add(taxOptionInfo.getPkey());
		}
        String dataIdsStr = org.apache.commons.lang3.StringUtils.join(dataIds, ",");
        dataUpdateRecord.setDataItems(dataIdsStr);
        BMListModel queryBmModel = bankManagerMapper.queryBmModel(fpId,bankcode);
        dataUpdateRecord.setStarttime(queryBmModel.getStarttime());
        dataUpdateRecord.setEndtime(queryBmModel.getEndtime());
        dataUpdateRecord.setOpentime(queryBmModel.getOpentime());
        //记录
		return bankManagerMapper.recordDataUpdate(dataUpdateRecord);
	}


	@Override
	public List<BankOrg> queryBankList(Map<String, Object> paramaters) {
		// TODO Auto-generated method stub
		return bankManagerMapper.queryBankList(paramaters);
	}


	@Override
	public List<FPOrg> findFPList(String bankId) {
		// TODO Auto-generated method stub
		return bankManagerMapper.findFPList(bankId);
	}
	@Override
	public List<FPOrg> findFpList(String bankId) {
		// TODO Auto-generated method stub
		return bankManagerMapper.findFpList(bankId);
	}
}
