package com.dcits.govsbu.sd.taxbankplatform.count.model;

import com.dcits.govsbu.sd.taxbankplatform.base.basemodel.BaseEntity;

public class PandectEntity extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String countName;
	private String month;
	private Long count;

	
	public String getCountName() {
		return countName;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PandectEntity other = (PandectEntity) obj;
		if (this.getMonth().equals(other.getMonth())) {
			return true;
		}else {
			return false;	
		}
//		if (!(obj instanceof PandectEntity)){
//			return false;
//		}
//		PandectEntity p = (PandectEntity) obj;
//		return this.getMonth().equals(p.getMonth());
		
	}
	
   
}
