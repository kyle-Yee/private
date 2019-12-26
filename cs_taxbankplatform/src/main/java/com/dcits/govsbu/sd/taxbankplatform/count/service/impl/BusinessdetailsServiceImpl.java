package com.dcits.govsbu.sd.taxbankplatform.count.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.count.mapper.BusinessdetailsMapper;
import com.dcits.govsbu.sd.taxbankplatform.count.model.BusinessdetailsEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.service.BusinessdetailsService;

@Service("querybyproductService")
public class BusinessdetailsServiceImpl  extends AbstractService<BusinessdetailsEntity, String> implements BusinessdetailsService {
	@Autowired
	private BusinessdetailsMapper querybyproductMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(querybyproductMapper);		
	}
	@Override
	public List<BusinessdetailsEntity> searchDetails(Map<String,Object> parameters) {
		List<BusinessdetailsEntity> searchDetails = querybyproductMapper.searchDetails(parameters);
		for (BusinessdetailsEntity detail : searchDetails) {
			if(detail.getLarrate()!=null){
				detail.setLarrate(detail.getLarrate()+" %");
			}
			//设置为万元统计单位
			if(detail.getLaamount()!=null){
				detail.setLaamount(new BigDecimal(Double.valueOf(detail.getLaamount())/10000).setScale(2, BigDecimal.ROUND_HALF_UP)+"");;
			}
			if(detail.getCreditquota()!=null){
				detail.setCreditquota(new BigDecimal(Double.valueOf(detail.getCreditquota())/10000).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
			}
			if (detail.getReport() == null){
				detail.setReport("无");
			}else{
				detail.setReport("有");
			}
			if (detail.getLascode().equals("DM-DSL") || detail.getLascode().equals("DM-WSL") 
					/*|| detail.getLascode().equals("DM-SLBTG")*/){
				detail.setBankname("--");
				detail.setLarrate("");
				detail.setSxsj("");
				detail.setCreditquota("");
				
			}
			if (detail.getLascode().equals("DM-DSX") || detail.getLascode().equals("DM-WHDSX")){
				detail.setLarrate("");
				detail.setSxsj("");
				detail.setCreditquota("");
			}
		}
		
		return searchDetails;
	}
}
