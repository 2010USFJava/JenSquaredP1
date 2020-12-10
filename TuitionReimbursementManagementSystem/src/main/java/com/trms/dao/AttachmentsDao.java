package com.trms.dao;

import java.sql.SQLException;

import com.trms.beans.Attachments;

public interface AttachmentsDao {
	
	public void newAttachment() throws SQLException;
	public Attachments getFilesByEId() throws SQLException;
	

}
