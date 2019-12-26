package com.dcits.govsbu.sd.taxbankplatform.count.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.count.mapper.PandectMapper;
import com.dcits.govsbu.sd.taxbankplatform.count.model.DataCountEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.model.PandectEntity;
import com.dcits.govsbu.sd.taxbankplatform.count.service.PandectService;

@Service("pandectService")
public class PandectServiceImpl  extends AbstractService<PandectEntity, String> implements PandectService {

	
	@Autowired
	private PandectMapper pandectMapper;
	
	@Autowired
	public void setBaseMapper() {		
		super.setBaseMapper(pandectMapper);		
	}
	/**
	 * 查询注册用户人数
	 */
	@Override
	public List<PandectEntity> searchByRegionId() {
		// TODO Auto-generated method stub
		return pandectMapper.searchByRegionId();
	}
	/**
	 * 查询用户统计数据
	 */
	@Override
	public List<DataCountEntity> findDataCount(Map<String, Object> parameters) {
		List<DataCountEntity> dataCount = pandectMapper.findDataCount(parameters);
		DataCountEntity data=null;
		for(int i=0;i<dataCount.size();i++){
			data = dataCount.get(i);
			if("r".equals(data.getDataName())){
				data.setDataName("申请用户数");
				data.setUnit("户");
			}else if("la-1".equals(data.getDataName())){
				data.setDataName("授信用户数");
				data.setUnit("户");
			}else if("la".equals(data.getDataName())){
				data.setDataName("申请笔数");
				data.setUnit("笔");
			}else if("la_amou".equals(data.getDataName())){
				data.setDataName("申请金额");
				data.setUnit("万元");
			}else if("lar".equals(data.getDataName())){
				data.setDataName("授信笔数");
				data.setUnit("笔");
			}else if("lar_amou".equals(data.getDataName())){
				data.setDataName("授信金额");
				data.setUnit("万元");
			}else if("report".equals(data.getDataName())){
				data.setDataName("税务报告下载次数");
				data.setUnit("次");
			}
		}
		return dataCount;
	}

	@Override
	public List<PandectEntity> findDataCountByMonth(Map<String, Object> parameters) {
		List<PandectEntity> countByMonth = pandectMapper.findDataCountByMonth(parameters);
//		PandectEntity pe1 = new PandectEntity() ;
//		for (int i=0;i<countByMonth.size();i++){
//			
//			if (!"report".equals(countByMonth.get(i).getCountName())){
//				pe1.setCountName("report");
//				pe1.setMonth("0000-00-00");
//				pe1.setCount(0l);
//				countByMonth.add(pe1);
//				break;
//			}
//		}
		
		return countByMonth;
	}


}
