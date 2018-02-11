package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class AttachmentDTO extends AbstractIdDomain {

	private String fileName;
	private String contentType;
	private AttachmentContentDTO attachmentContent;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public AttachmentContentDTO getAttachmentContent() {
		return attachmentContent;
	}

	public void setAttachmentContent(AttachmentContentDTO attachmentContent) {
		this.attachmentContent = attachmentContent;
	}

}
