package com.dcits.govsbu.sd.taxbankplatform.links.mapper;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.links.model.LinksEntity;

@Repository
public interface LinksMapper extends BaseMapper<LinksEntity, String>{

	public LinksEntity findByOrder(LinksEntity linksEntity);

	public LinksEntity ckLinksName(LinksEntity linksEntity);

}
