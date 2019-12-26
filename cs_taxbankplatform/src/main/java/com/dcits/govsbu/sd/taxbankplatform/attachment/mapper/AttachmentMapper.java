package com.dcits.govsbu.sd.taxbankplatform.attachment.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dcits.govsbu.sd.taxbankplatform.attachment.model.Attachment;
import com.dcits.govsbu.sd.taxbankplatform.base.basemapper.BaseMapper;

@Repository 
public interface AttachmentMapper extends BaseMapper<Attachment, String>{

    int insertSelective(Attachment record);
    
    Attachment findByUrl(String url);

   /**
	* fkTable 关联表
	* fkIdName 关联表ID名称
	* fkId 关联表ID值
	* attachments,删除url in attachments.url的附件；
	* fkIds,删除相关的附件
    * urls,传入urls,删除url not in urls的附件；
    * @param parameter
    * @return
    */
    int deleteBatch(Map<String, Object> parameter);
    
}