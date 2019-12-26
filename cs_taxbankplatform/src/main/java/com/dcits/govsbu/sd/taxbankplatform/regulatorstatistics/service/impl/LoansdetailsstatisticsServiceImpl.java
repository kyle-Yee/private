package com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.mapper.LoansdetailsstatisticsMapper;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.model.LoansdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.regulatorstatistics.service.LoansdetailsstatisticsService;

@Service("loansdetailsstatisticsService")
public class LoansdetailsstatisticsServiceImpl  extends AbstractService<LoansdetailsEntity, String> implements LoansdetailsstatisticsService {

	@Autowired
	private LoansdetailsstatisticsMapper loansdetailsstatisticsMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(loansdetailsstatisticsMapper);		
	}

	@Override
	public List<LoansdetailsEntity> loansdetailsstatistics(
			Map<String, Object> parameters) {
		List<LoansdetailsEntity> list = loansdetailsstatisticsMapper.loansdetailsstatistics(parameters);
		/****************************统计功能优化**************************************/
		/*delete by xiecui 2018/04/04 begin*/
		/*for (LoansdetailsEntity le : list){
			if (le.getQymc() == null){
				le.setQymc("");
			}
			if (le.getSdsj() == null){
				le.setSdsj("");
			}
			if (le.getSdyh() == null){
				le.setSdyh("");
			}
			if (le.getSdje() == null){
				le.setSdje("");
			}
			if (le.getHpzt() == null){
				le.setHpzt("");
			}
			if (le.getSpsj() == null){
				le.setSpsj("");
			}
			if (le.getSxje() == null){
				le.setSxje("0");
			}
			if (le.getSxll() == null){
				le.setSxll("");
			}
		}*/
		/*delete by xiecui 2018/04/04 end*/
		return list;
	}

}
