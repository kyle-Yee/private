package com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.mapper.ImgMapper;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.model.ImgEntity;
import com.dcits.govsbu.sd.taxbankplatform.tbpStatic.service.ImgService;
import com.dcits.govsbu.sd.taxbankplatform.user.model.UserEntity;

@Service
public class ImgServiceImpl extends AbstractService<ImgEntity, String> implements ImgService {

	
	@Autowired
	private ImgMapper imgMapper;

	// 这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(imgMapper);
	}

	@Override
	public List<ImgEntity> findImgType() {
		return imgMapper.findImgType();
	}

	/**
	 * 插入数据 
	 */
	@Override
	public int insertImg(ImgEntity imgEntity) throws AjaxException {
		//找到此图片类型的ID
		ImgEntity codeId = imgMapper.findIdByCode(imgEntity);
		try {
			if(!("").equals(codeId)){
				imgEntity.setPitId(codeId.getPitId());
				//插入数据
				return imgMapper.insert(imgEntity);
			}
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 排序顺序检测唯一
	 */
	@Override
	public int findOrder(ImgEntity imgEntity) {
		//0:数据库中不存在；1：数据库中存在
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		UserEntity userEntity = (UserEntity)request.getSession().getAttribute("userSession");
		String regionId = userEntity.getRegionid();
		imgEntity.setCityId(regionId);
		ImgEntity ckOrder = imgMapper.findOrder(imgEntity);
		if(null != ckOrder){
			String ID = ckOrder.getId();
			if(imgEntity.getId().equals(ID)){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 0;
		}
	}

	/**
	 * 检测类型的唯一性
	 */
	@Override
	public int findName(String imgCode, String id, String regionId) {
		//0:数据库中不存在；1：数据库中存在
		ImgEntity imgs = new ImgEntity();
		imgs.setCityId(regionId);
		imgs.setImgCode(imgCode);
		ImgEntity ckCode = imgMapper.findName(imgs);
		if(null != ckCode){
			if(ckCode.getId().equals(String.valueOf(id))){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 0;
		}
	}
}
