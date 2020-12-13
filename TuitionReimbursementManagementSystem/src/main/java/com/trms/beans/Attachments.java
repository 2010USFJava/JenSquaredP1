package com.trms.beans;

import java.io.InputStream;

public class Attachments {
	private int event_id;
	private String file_name;
	private InputStream file_attachment;
	
	public Attachments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachments(int event_id, String file_name, InputStream file_attachment) {
		super();
		this.event_id = event_id;
		this.file_name = file_name;
		this.file_attachment = file_attachment;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public InputStream getFile_attachment() {
		return file_attachment;
	}

	public void setFile_attachment(InputStream file_attachment) {
		this.file_attachment = file_attachment;
	}

	@Override
	public String toString() {
		return "Attachments [event_id=" + event_id + ", file_name=" + file_name + ", file_attachment=" + file_attachment
				+ "]";
	}

	
	
}
