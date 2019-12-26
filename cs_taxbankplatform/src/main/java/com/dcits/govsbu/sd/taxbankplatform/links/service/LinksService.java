package com.dcits.govsbu.sd.taxbankplatform.links.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.links.model.LinksEntity;

public interface LinksService {

	public int insert(LinksEntity linksEntity);

	public List<LinksEntity> queryListByPage(Map<String, Object> parameters);

	public int update(LinksEntity linksEntity);

	public LinksEntity findById(String id);

	public int findByName(LinksEntity linksEntity);

	public int findByOrder(LinksEntity linksEntity);

}
