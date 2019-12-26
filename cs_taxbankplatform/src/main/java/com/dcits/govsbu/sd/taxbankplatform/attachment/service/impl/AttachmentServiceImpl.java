/**
 * 
 */
package com.dcits.govsbu.sd.taxbankplatform.attachment.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.govsbu.sd.taxbankplatform.attachment.mapper.AttachmentMapper;
import com.dcits.govsbu.sd.taxbankplatform.attachment.model.Attachment;
import com.dcits.govsbu.sd.taxbankplatform.attachment.service.AttachmentService;
import com.dcits.govsbu.sd.taxbankplatform.base.baseservice.impl.AbstractService;

/**
 * 上传附件管理
 * @author xudan
 *
 */
@Service("attachmentService")
public class AttachmentServiceImpl extends AbstractService<Attachment, String> implements AttachmentService{

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	//这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为attachmentMapper
	@Autowired
	public void setBaseMapper() {
		super.setBaseMapper(attachmentMapper);
	}


	@Override
	public Attachment findByUrl(String url) {
		return attachmentMapper.findByUrl(url);
	}
	
	/**
	 * fkTable 关联表
	 * fkIdName 关联表ID名称
	 * fkIds 关联表ID值，传入fkIds，删除相关的附件数据和实体
	 */
	@Override
	public int deleteBatchByFkId(Map<String, Object> mapParam) {
		int i = 0;
		try{
			//删除文件
			List<Attachment> attachments = attachmentMapper.queryListAll(mapParam);
			if(attachments != null && attachments.size()>0){
				for(Attachment attachment:attachments){
					File f = new File(attachment.getPath()); // 输入要删除的文件位置
					if(f.exists())
						f.delete();
				}
			}
			return attachmentMapper.deleteBatch(mapParam);
		}catch(Exception e){
			i = -1;
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * fkTable 关联表
	 * fkIdName 关联表ID名称
	 * fkId 关联表ID值
	 * urls(或attachments，二选一)  
	 * 传入attachments，删除url in attachments.url的附件；
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int deleteBatchByUrl(Map<String,Object> mapParam) {
		int i = 0;
		try{
			List<Attachment> attachments = (List<Attachment>) mapParam.get("attachments");
			//删除文件
			if(attachments != null && attachments.size()>0){
				for(Attachment attachment:attachments){
					File f = new File(attachment.getPath()); // 输入要删除的文件位置
					if(f.exists())
						f.delete();
				}
			}
			return attachmentMapper.deleteBatch(mapParam);
		}catch(Exception e){
			i = -1;
			e.printStackTrace();
		}
		return i;
	}
	
	@Override
	public int deleteBatchByUrl(List<Attachment> attachments) {
		int i = 0;
		try{
			//删除文件
			if(attachments != null && attachments.size()>0){
				for(Attachment attachment:attachments){
					File f = new File(attachment.getPath()); // 输入要删除的文件位置
					if(f.exists())
						f.delete();
				}
			}
		}catch(Exception e){
			i = -1;
			e.printStackTrace();
		}
		return i;
	}
}
