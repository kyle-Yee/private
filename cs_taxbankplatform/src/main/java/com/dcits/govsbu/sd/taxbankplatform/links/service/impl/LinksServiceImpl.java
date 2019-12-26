package com.dcits.govsbu.sd.taxbankplatform.links.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.links.mapper.LinksMapper;
import com.dcits.govsbu.sd.taxbankplatform.links.model.LinksEntity;
import com.dcits.govsbu.sd.taxbankplatform.links.service.LinksService;

@Service("linksService")
public class LinksServiceImpl extends AbstractService<LinksEntity, String> implements LinksService {

	@Autowired
	private LinksMapper linksMapper;
	
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(linksMapper);
	}

	/**
	 * 查找排序，确认唯一
	 */
	@Override
	public int findByOrder(LinksEntity linksEntity) {
		// TODO Auto-generated method stub
		//0:数据库中不存在；1：数据库中存在
		LinksEntity ckOrder = linksMapper.findByOrder(linksEntity);
		if(null != ckOrder){
			String ID = ckOrder.getId();
			if(linksEntity.getId() == ID){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 0;
		}
	}

	/**
	 * 查找名称，确认唯一
	 */
	@Override
	public int findByName(LinksEntity linksEntity) {
		// TODO Auto-generated method stub
		//0:数据库中不存在；1：数据库中存在
		LinksEntity ckName = linksMapper.ckLinksName(linksEntity);
		if(null != ckName){
			String ID = ckName.getId();
			if(linksEntity.getId() == ID){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 0;
		}		
	}

	/**
	 * 检查数据是否存在
	 */
	/*@Override
	public int ckLinksName(LinksEntity linksEntity) {
		// TODO Auto-generated method stub
		LinksEntity linksEntitys = linksMapper.ckLinksName(linksEntity);
		if(linksEntitys == null){
			return 0;
		}else{
			return 1;
		}
	}*/

}
