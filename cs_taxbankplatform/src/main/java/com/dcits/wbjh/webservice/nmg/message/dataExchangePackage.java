package com.dcits.wbjh.webservice.nmg.message;

import com.dcits.wbjh.webservice.nmg.message.contentControl.*;
import com.dcits.wbjh.webservice.nmg.message.packageInfo.*;
import com.dcits.wbjh.webservice.nmg.message.transferInfo.*;
import com.dcits.wbjh.webservice.nmg.message.envelopeInfo.*;

public class dataExchangePackage {
	private envelopeInfo envelopeInfo;
	private packageInfo packageInfo;
	private transferInfo transferInfo;	
	private contentControl contentControl;
	public envelopeInfo getEnvelopeInfo() {
		return envelopeInfo;
	}

	public void setEnvelopeInfo(envelopeInfo envelopeInfo) {
		this.envelopeInfo = envelopeInfo;
	}

	public packageInfo getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(packageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	public transferInfo getTransferInfo() {
		return transferInfo;
	}

	public void setTransferInfo(transferInfo transferInfo) {
		this.transferInfo = transferInfo;
	}

	public contentControl getContentControl() {
		return contentControl;
	}

	public void setContentControl(contentControl contentControl) {
		this.contentControl = contentControl;
	}

	
	
	
}
