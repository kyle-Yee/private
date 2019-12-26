package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ImgEntity;

@Repository
public interface ImgMapper extends BaseMapper<ImgEntity, String>{

	public List<ImgEntity> findImgType();

	public ImgEntity findIdByCode(ImgEntity imgEntity);

	public ImgEntity findName(ImgEntity imgs);

	public ImgEntity findOrder(ImgEntity imgEntity);

}
