package com.dcits.govsbu.sd.taxbankplatform.dkxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.LoanApplyDataSaveMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Dksqxx;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.LoanApplyDataSaveService;

@Service("loanApplyDataSaveService")
public class LoanApplyDataSaveServiceImpl extends AbstractService<Dksqxx, String> implements LoanApplyDataSaveService{
	
	@Autowired
	private LoanApplyDataSaveMapper loanApplyDataSaveMapper;
	


	public LoanApplyDataSaveMapper getLoanApplyDataSaveMapper() {
		return loanApplyDataSaveMapper;
	}



	public void setLoanApplyDataSaveMapper(
			LoanApplyDataSaveMapper loanApplyDataSaveMapper) {
		this.loanApplyDataSaveMapper = loanApplyDataSaveMapper;
	}



	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(loanApplyDataSaveMapper);
	}

	
	@Override
	public List<Dksqxx> queryListAll(Map<String, Object> parameter){
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.queryListAll(parameter);
	}



	@Override
	public List<Dksqxx> findAllbylfId() {
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.findAllbylfId();
	}



	@Override
	public int insert(Syptbankapply syptbankapply) {
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.insert(syptbankapply);
	}



	@Override
	public List<Syptbankapply> findBylaid(String ifId, String channelid) {
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.findBylaid(ifId, channelid);
	}



	@Override
	public int changesqdkxx(String lfId) {
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.changesqdkxx(lfId);
	}



	@Override
	public int changefinal(String lfId) {
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.changefinal(lfId);
	}



	@Override
	public int changeend(String lfId) {
		// TODO Auto-generated method stub
		return loanApplyDataSaveMapper.changeend(lfId);
	}




}
