package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.NewsEntity;

@Repository
public interface NewsMapper extends BaseMapper<NewsEntity, String>{

	public NewsEntity searchByCode(NewsEntity newsEntity);

	public List<NewsEntity> findDeptCode(NewsEntity entity);

}
