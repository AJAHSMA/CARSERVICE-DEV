package com.ajahsma.carservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author SHARAN A
 */

@Entity(name = "Attachment")
@Table(name = "attachment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AttachmentTO extends AbstractIdDomain {

	private String fileName;
	private String contentType;
	private AttachmentContentTO attachmentContent;
	
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
	public AttachmentContentTO getAttachmentContent() {
		return attachmentContent;
	}
	
	public void setAttachmentContent(AttachmentContentTO attachmentContent) {
		this.attachmentContent = attachmentContent;
	}

	
}
