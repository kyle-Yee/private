package com.dcits.govsbu.sd.taxbankplatform.datastorage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;
import com.dcits.govsbu.sd.taxbankplatform.datastorage.mapper.TaxDataSaveMapper;
import com.dcits.govsbu.sd.taxbankplatform.datastorage.service.TaxDataSaveService;
import com.dcits.govsbu.sd.taxbankplatform.exception.AjaxException;
import com.dcits.govsbu.sd.taxbankplatform.exception.ServiceException;
import com.dcits.govsbu.sd.taxbankplatform.util.IDGenerate;

/**
 * 接口数据存储实现
 * @author Administrator
 */
@Service("taxDataSaveService")
public class TaxDataSaveServiceImpl extends AbstractService<Map<String, String>, String> implements TaxDataSaveService {
	//数据存储结果0失败, 1成功
	public int result = 0;
	
	@Autowired
	private TaxDataSaveMapper taxDataSaveMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(taxDataSaveMapper);
	}
	
	
	/**保存企业基础信息*/
	@Override
	public int insertNsrjcxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxNsrjcxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业基础信息扩展*/
	@Override
	public int insertNsrxxKz(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				taxDataSaveMapper.insertTaxNsrxxKz(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业批准机构信息*/
	@Override
	public int insertPzjgxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				taxDataSaveMapper.insertTaxPzjgxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业变更信息*/
	@Override
	public int insertBgdjmx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxBgdjmx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业投资方信息*/
	@Override
	public int insertTzfxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxTzfxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业分支机构信息*/
	@Override
	public int insertFzjgxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxFzjgxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业总分机构信息*/
	@Override
	public int insertZzjgxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxZzjgxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业违法违章信息*/
	@Override
	public int insertSswfxwdj(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxSswfxwdj(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业稽查案件信息*/
	@Override
	public int insertAjxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxAjxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业申报信息*/
	@Override
	public int insertSbxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxSbxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业征收信息*/
	@Override
	public int insertYjsf(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxYjsf(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业资产负债表(小企业会计制度)*/
	@Override
	public int insertXqyzcfzb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxXqyzcfzb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业利润表(小企业会计制度)*/
	@Override
	public int insertXqylrb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxXqylrb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业发票开具*/
	@Override
	public int insertFpkj(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxFpkj(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业认证发票*/
	@Override
	public int insertRzfp(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxRzfp(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存一般纳税人申报明细主表*/
	@Override
	public int insertYnbsr(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxYnbsr(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存小规模纳税人申报明细主表*/
	@Override
	public int insertXgmnsr(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxXgmnsr(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存纳税人资格信息结果表*/
	@Override
	public int insertNsrzgxxjgb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxNsrzgxxjgb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存稽查案源信息*/
	@Override
	public int insertAyxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxAyxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存欠税信息*/
	@Override
	public int insertQsclxx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxQsclxx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存财报申报主表*/
	@Override
	public int insertZlbscjb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxZlbscjb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业资产负债表(企业会计制度)*/
	@Override
	public int insertQyzcfzb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxQyzcfzb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业利润表(企业会计制度)*/
	@Override
	public int insertQylrb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxQylrb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业资产负债表(一般企业会计)*/
	@Override
	public int insertYbqyzcfzb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxYbqyzcfzb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存企业利润表(一般企业会计制度)*/
	@Override
	public int insertYbqylrb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxYbqylrb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存利润表(小企业会计制度)_年报*/
	@Override
	public int insertXqylrbnb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxXqylrbnb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存编码有效期*/
	@Override
	public int insertLpbmcx(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxLpbmcx(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	/**保存纳税信用级别*/
	@Override
	public int insertNsxyjb(List<Map<String, String>> dataList) {
		try {
			for(int i = 0; i < dataList.size(); i++){
				String id = IDGenerate.getZJID("XH");	//生成主键
				dataList.get(i).put("id", id);	//放入主键
				result = taxDataSaveMapper.insertTaxNsxyjb(dataList.get(i));
			}
		} catch (ServiceException e) {
			throw new AjaxException(e);
		}
		return result;
	}
	//调用接口成功后修改纳税人基础信息表里的接口调用是否成功
	@Override
	public int updataNsryhxx(String id){
		return taxDataSaveMapper.updataNsryhxx(id);
	}
}
