package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.NewsEntity;

public interface NewsService {

	public NewsEntity findById(String id);

	public List<NewsEntity> queryListByPage(Map<String, Object> parameters);

	public int update(NewsEntity newsEntity);

	public int insertNews(NewsEntity newsEntity);

	public List<NewsEntity> findDeptCode(NewsEntity entity);

}
