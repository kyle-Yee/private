package com.dcits.govsbu.sd.taxbankplatform.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.resource.mapper.ResourceMapper;
import com.dcits.govsbu.sd.taxbankplatform.resource.model.ResourceEntity;
import com.dcits.govsbu.sd.taxbankplatform.resource.service.ResourceService;

/**
 * 
 * ResourceServiceImpl.java
 * @author 张孟志
 * @date 2016年7月1日
 * @caption 资源
 */
@Service("resourceService")
public class ResourceServiceImpl extends AbstractService<ResourceEntity, Long> implements ResourceService{

	@Autowired
	private ResourceMapper resourceMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(resourceMapper);
	}
	
	@Override
	public List<ResourceEntity> findResourcesByUserId(String userId) {
		return resourceMapper.findResourcesByUserId(userId);
	}

	@Override
	public List<ResourceEntity> queryResourceList(Map<String, Object> parameter) {
		return resourceMapper.queryResourceList(parameter);
	}

	@Override
	public int deleteBatchBysid(List<Long> sid) {
		// TODO Auto-generated method stub
		if(resourceMapper.deleteBatchBysid(sid)>0){
			return 1;
		}else{
		return 0;
		}
	}



  



}
