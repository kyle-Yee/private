package com.dcits.govsbu.sd.taxbankplatform.financialProduct.model;

import java.util.Date;
import java.util.List;

import com.dcits.govsbu.sd.taxbankplatform.acceptdeadline.model.AcceptDeadLineEntity;
import com.dcits.govsbu.sd.taxbankplatform.amount.model.AmountEntity;
import com.dcits.govsbu.sd.taxbankplatform.attachment.model.Attachment;
import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;
import com.dcits.govsbu.sd.taxbankplatform.deadline.model.DeadlineEntity;
import com.dcits.govsbu.sd.taxbankplatform.rates.model.RatesEntity;
import com.dcits.govsbu.sd.taxbankplatform.productstatus.model.ProductStatusEntity;

public class FinancialProduct extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**表名*/
	private static final String tableName = "tb_financial_product";
	/**表ID名*/
	private static final String idName = "fp_id";
	
	private String fpName = "";	//金融产品名称
    private String orgId;	//所属银行id
    private String regionId;	//所属城市
    private Date fpIssueTime;	//发布时间
    private Date fpRemoveTime; //下架时间
    private AmountEntity amountEntity; //最高贷款额度  
    private DeadlineEntity deadlineEntity; //最长贷款期限  
    private RatesEntity ratesEntity; //贷款利率   
    private String fpmanyCredit;  //是否多重授信
    private String pstatusId;			//产品状态id
    private ProductStatusEntity productStatusEntity; //产品状态
    private String fpIshot; //是否热门(Y/N)
    private String fpNeedInfo; //申请所需资料
    private String fpDetails; //产品详情
    private String fpPrdImgUrl; //产品图片地址
    private String fpRemark; //备注
    private String enabled;		//有效标志(Y/N)--逻辑删除状态

    private String rwIds; //还款方式  (多个)
    private String gsIds; //担保方式  (多个)
    private String fpOverlayPcIds; //发布组织即覆盖区域 (多个) 关联tb_organizations表-----老版本是关联tb_region表
    private int ptDm;//产品类型代码
    private String ptname;//产品类型名称 
    public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}

	public int getPtDm() {
		return ptDm;
	}

	public void setPtDm(int ptDm) {
		this.ptDm = ptDm;
	}

	private List<Attachment> attachments;
    
    private String orgName;	//所属银行
    private String regionName;	//所属城市
    private AcceptDeadLineEntity acceptDeadLineEntity;
    
	public AcceptDeadLineEntity getAcceptDeadLineEntity() {
		return acceptDeadLineEntity;
	}

	public void setAcceptDeadLineEntity(AcceptDeadLineEntity acceptDeadLineEntity) {
		this.acceptDeadLineEntity = acceptDeadLineEntity;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getFpOverlayPcIds() {
		return fpOverlayPcIds;
	}

	public void setFpOverlayPcIds(String fpOverlayPcIds) {
		this.fpOverlayPcIds = fpOverlayPcIds;
	}

	public String getRwIds() {
		return rwIds;
	}

	public void setRwIds(String rwIds) {
		this.rwIds = rwIds;
	}

	public String getGsIds() {
		return gsIds;
	}

	public void setGsIds(String gsIds) {
		this.gsIds = gsIds;
	}

	public RatesEntity getRatesEntity() {
		return ratesEntity;
	}

	public void setRatesEntity(RatesEntity ratesEntity) {
		this.ratesEntity = ratesEntity;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public AmountEntity getAmountEntity() {
		return amountEntity;
	}

	public void setAmountEntity(AmountEntity amountEntity) {
		this.amountEntity = amountEntity;
	}

	public DeadlineEntity getDeadlineEntity() {
		return deadlineEntity;
	}

	public void setDeadlineEntity(DeadlineEntity deadlineEntity) {
		this.deadlineEntity = deadlineEntity;
	}

	public ProductStatusEntity getProductStatusEntity() {
		return productStatusEntity;
	}

	public void setProductStatusEntity(ProductStatusEntity productStatusEntity) {
		this.productStatusEntity = productStatusEntity;
	}
	
	public String getFpName() {
        return fpName;
    }

    public void setFpName(String fpName) {
        this.fpName = fpName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public Date getFpIssueTime() {
        return fpIssueTime;
    }

    public void setFpIssueTime(Date fpIssueTime) {
        this.fpIssueTime = fpIssueTime;
    }

    public Date getFpRemoveTime() {
        return fpRemoveTime;
    }

    public void setFpRemoveTime(Date fpRemoveTime) {
        this.fpRemoveTime = fpRemoveTime;
    }

	public String getFpIshot() {
        return fpIshot;
    }

    public void setFpIshot(String fpIshot) {
        this.fpIshot = fpIshot;
    }

    public String getFpNeedInfo() {
        return fpNeedInfo;
    }

    public void setFpNeedInfo(String fpNeedInfo) {
        this.fpNeedInfo = fpNeedInfo;
    }

    public String getFpPrdImgUrl() {
        return fpPrdImgUrl;
    }

    public void setFpPrdImgUrl(String fpPrdImgUrl) {
        this.fpPrdImgUrl = fpPrdImgUrl;
    }
    public String getFpRemark() {
        return fpRemark;
    }

    public void setFpRemark(String fpRemark) {
        this.fpRemark = fpRemark;
    }

    public String getFpDetails() {
        return fpDetails;
    }

    public void setFpDetails(String fpDetails) {
        this.fpDetails = fpDetails;
    }

    public static String getTablename() {
		return tableName;
	}
	
	public static String getIdname() {
		return idName;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public String getFpmanyCredit() {
		return fpmanyCredit;
	}

	public void setFpmanyCredit(String fpmanyCredit) {
		this.fpmanyCredit = fpmanyCredit;
	}

	public String getPstatusId() {
		return pstatusId;
	}

	public void setPstatusId(String pstatusId) {
		this.pstatusId = pstatusId;
	}
	
}