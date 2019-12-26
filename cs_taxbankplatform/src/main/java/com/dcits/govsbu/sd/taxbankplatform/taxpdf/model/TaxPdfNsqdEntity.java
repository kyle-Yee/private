package com.dcits.govsbu.sd.taxbankplatform.taxpdf.model;

import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
/**
 * 
 * @author Administrator
 *
 */
public class TaxPdfNsqdEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   
	private List<NsqdEntity> nsqdEntity;//纳税清单实体
	
	private List<ZzsxsefxEntity> zzsxsefxEntity;//增值税销售额分析
	
	private List<SdsyyesrfxEntity> sdsyyesrfxEntity;//所得税营业额收入分析实体

	private List<SdsnsqkEntity> sdsnsqkEntity;//所得税月季纳税情况实体
	
	private List<ZzsnsEntity> zzsnsEntity;//增值税纳税情况（增值税一般纳税人)实体
	
	private List<JtnsqdEntity> jtnsqdEntity;//获取具体税种的综合清单
	
	public List<NsqdEntity> getNsqdEntity() {
		return nsqdEntity;
	}

	public void setNsqdEntity(List<NsqdEntity> nsqdEntity) {
		this.nsqdEntity = nsqdEntity;
	}

	public List<ZzsxsefxEntity> getZzsxsefxEntity() {
		return zzsxsefxEntity;
	}

	public void setZzsxsefxEntity(List<ZzsxsefxEntity> zzsxsefxEntity) {
		this.zzsxsefxEntity = zzsxsefxEntity;
	}

	public List<SdsyyesrfxEntity> getSdsyyesrfxEntity() {
		return sdsyyesrfxEntity;
	}

	public void setSdsyyesrfxEntity(List<SdsyyesrfxEntity> sdsyyesrfxEntity) {
		this.sdsyyesrfxEntity = sdsyyesrfxEntity;
	}

	public List<SdsnsqkEntity> getSdsnsqkEntity() {
		return sdsnsqkEntity;
	}

	public void setSdsnsqkEntity(List<SdsnsqkEntity> sdsnsqkEntity) {
		this.sdsnsqkEntity = sdsnsqkEntity;
	}

	public List<ZzsnsEntity> getZzsnsEntity() {
		return zzsnsEntity;
	}

	public void setZzsnsEntity(List<ZzsnsEntity> zzsnsEntity) {
		this.zzsnsEntity = zzsnsEntity;
	}

	public List<JtnsqdEntity> getJtnsqdEntity() {
		return jtnsqdEntity;
	}

	public void setJtnsqdEntity(List<JtnsqdEntity> jtnsqdEntity) {
		this.jtnsqdEntity = jtnsqdEntity;
	}

}
