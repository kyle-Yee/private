package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.StaticMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.StaticEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.StaticService;

@Service
public class StaticServiceImpl extends AbstractService<StaticEntity, String> implements StaticService{

	
	@Autowired
	private StaticMapper staticMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(staticMapper);
	}

	/**
	 * 新增静态信息
	 */
	@Override
	public int insertStatic(StaticEntity staticEntity) {
		StaticEntity searchTypeId = staticMapper.searchByCode(staticEntity);
		if(!("").equals(searchTypeId)){
			String codeId = searchTypeId.getCodeId();
			staticEntity.setCodeId(codeId);
			return staticMapper.insert(staticEntity);
		}
		return 0;
	}

	/***
	 * 搜索数据是否存在
	 */
	@Override
	public int searchListByRegionId(StaticEntity staticEntity) {
		StaticEntity staticEntitys = staticMapper.searchListByRegionId(staticEntity);
		if(staticEntitys == null){
			return 0;
		}else if(staticEntitys.getId()==staticEntity.getId()){
			return 0;
		}
		else{
			return 1;
		}
	}

}
