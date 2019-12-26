package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.NewsMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.NewsEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.NewsService;

@Service
public class NewsServiceImpl extends AbstractService<NewsEntity, String> implements NewsService{

	@Autowired
	private NewsMapper newsMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(newsMapper);
	}

	/**
	 * 新闻动态信息添加
	 */
	@Override
	public int insertNews(NewsEntity newsEntity) {
		//通过code搜索CODE  ID
		NewsEntity searchTypeId = newsMapper.searchByCode(newsEntity);
		if(searchTypeId != null){
			String newId = searchTypeId.getNewId();
			newsEntity.setNewId(newId);
			return newsMapper.insert(newsEntity);
		}
		return 0;
	}

	/**
	 * 根据类型查询下拉列表的初始化
	 */
	@Override
	public List<NewsEntity> findDeptCode(NewsEntity entity) {
		// TODO Auto-generated method stub
		return newsMapper.findDeptCode(entity);
	}
}
