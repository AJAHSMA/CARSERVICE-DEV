package com.ajahsma.carservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author SHARAN A
 */

@Entity(name = "AttachmentContent")
@Table(name = "attachmentcontent")
public class AttachmentContentTO extends AbstractIdDomain {

	private byte[] contentBytes;

	@Column(name="contentblob", nullable=false, length=Integer.MAX_VALUE)
	@Lob()
	public byte[] getContentBytes() {
		return contentBytes;
	}

	public void setContentBytes(byte[] contentBytes) {
		this.contentBytes = contentBytes;
	}

}
