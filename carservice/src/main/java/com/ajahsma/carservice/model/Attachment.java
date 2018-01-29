package com.ajahsma.carservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author SHARAN A
 */

@Entity
@Table(name = "attachment")
public class Attachment extends AbstractIdDomain {

	private String fileName;
	private String contentType;
	private AttachmentContent attachmentContent;
	
	@Column(name="filename", nullable=false)
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name="contenttype", nullable=false)
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="attachmentcontent_id", nullable=false)
	public AttachmentContent getAttachmentContent() {
		return attachmentContent;
	}
	
	public void setAttachmentContent(AttachmentContent attachmentContent) {
		this.attachmentContent = attachmentContent;
	}

	
}
