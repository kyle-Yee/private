package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service;

import java.util.List;
import java.util.Map;

import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ImgEntity;

public interface ImgService {

	public List<ImgEntity> queryListByPage(Map<String, Object> parameters);

	public ImgEntity findById(String id);

	public int insertImg(ImgEntity imgEntity);

	public int update(ImgEntity imgEntity);

	public List<ImgEntity> findImgType();

	public int findName(String imgCode, String id, String regionId);

	public int findOrder(ImgEntity imgEntity);

}
