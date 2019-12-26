package com.dcits.govsbu.sd.taxbankplatform.dkxx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.mapper.SyptbankApplyMapper;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.model.Syptbankapply;
import com.dcits.govsbu.sd.taxbankplatform.dkxx.service.SyptbankApplyService;

@Service("syptbankApplyService")
public class SyptbankApplyServiceImpl extends AbstractService<Syptbankapply, String> implements SyptbankApplyService{
	
    @Autowired
    private SyptbankApplyMapper syptbankApplyMapper;
    




	public SyptbankApplyMapper getSyptbankApplyMapper() {
		return syptbankApplyMapper;
	}



	public void setSyptbankApplyMapper(SyptbankApplyMapper syptbankApplyMapper) {
		this.syptbankApplyMapper = syptbankApplyMapper;
	}



	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(syptbankApplyMapper);
	}

	
	

	@Override
	public int insert(Syptbankapply syptbankapply) {
		// TODO Auto-generated method stub
		return syptbankApplyMapper.insert(syptbankapply);
	}



	@Override
	public List<Syptbankapply> findBylaid(String ifId, String channelid) {
		// TODO Auto-generated method stub
		return syptbankApplyMapper.findBylaid(ifId, channelid);
	}



	@Override
	public List<Syptbankapply> findByfpid(String bankfpId, String channelid) {
		// TODO Auto-generated method stub
		return syptbankApplyMapper.findByfpid(bankfpId, channelid);
	}



	@Override
	public int changetemp(Syptbankapply syptbankapply) {
		// TODO Auto-generated method stub
		return syptbankApplyMapper.changetemp(syptbankapply);
	}



	@Override
	public List<Syptbankapply> findAll(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return syptbankApplyMapper.findAll(parameter);
	}

}
