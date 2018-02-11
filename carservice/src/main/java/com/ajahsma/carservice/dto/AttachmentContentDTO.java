package com.ajahsma.carservice.dto;

/**
 * @author DEVU I
 */

public class AttachmentContentDTO extends AbstractIdDomain {

	private byte[] contentBytes;

	public byte[] getContentBytes() {
		return contentBytes;
	}

	public void setContentBytes(byte[] contentBytes) {
		this.contentBytes = contentBytes;
	}

}
