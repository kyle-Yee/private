package com.dcits.govsbu.sd.taxbankplatform.provincecities.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.provincecities.model.ProvinceCitiesEntity;


/**
 * 
 * @author liwangxiong
 *
 */
public interface ProvinceCitiesMapper extends BaseMapper<ProvinceCitiesEntity, String > {
	 public ProvinceCitiesEntity findByPccode(@Param("pccode")String pccode);
	 public ProvinceCitiesEntity findPcID(String regionid);

	 //	 通过pccode前两位判断传入的pcid是否属于指定的省市区
	 public int selectPccodeById(Map<String,Object> map);
	 public List<ProvinceCitiesEntity> findAll(String id);
}
