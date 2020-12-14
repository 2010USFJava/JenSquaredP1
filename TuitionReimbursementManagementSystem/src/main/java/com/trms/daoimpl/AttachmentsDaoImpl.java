package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trms.beans.Attachments;
import com.trms.dao.AttachmentsDao;
import com.trms.util.ConnFactory;
import com.trms.util.LogThis;

public class AttachmentsDaoImpl implements AttachmentsDao{
	public static ConnFactory cf = ConnFactory.getInstance();


	@Override
	public void newAttachment(Attachments a) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into attachments values(event_id,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, a.getFile_name());
		ps.setBlob(2, a.getFile_attachment());
		ps.executeUpdate();
		LogThis.LogIt("info", "Attachment has been added.");
	}

	@Override
	public Attachments getFilesByEventId(int event_id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from attatchments where event_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, event_id);
		ResultSet rs = ps.executeQuery();
		Attachments a = null;
		if(rs!=null) {
			while (rs.next()) {
				a = new Attachments(rs.getInt(1), rs.getString(2), rs.getBinaryStream(3));
			}
		}
		return a;
	}


}
