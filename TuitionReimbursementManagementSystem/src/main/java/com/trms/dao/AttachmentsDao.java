package com.trms.dao;

import java.sql.SQLException;

import com.trms.beans.Attachments;

public interface AttachmentsDao {
	
	public void newAttachment(Attachments a) throws SQLException;
	public Attachments getFilesByEventId(int event_id) throws SQLException;
	

}
