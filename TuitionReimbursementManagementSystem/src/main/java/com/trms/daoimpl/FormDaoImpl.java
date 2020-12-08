package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.trms.beans.Form;
import com.trms.beans.Form.eventType;
import com.trms.beans.Form.formStatus;
import com.trms.beans.Form.gradeFormat;
import com.trms.dao.FormDao;
import com.trms.util.ConnFactory;

public class FormDaoImpl implements FormDao {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	LocalDateTime localDateTime = LocalDateTime.now();
	LocalDate localDate = localDateTime.toLocalDate();
	LocalTime localTime = localDateTime.toLocalTime();
	Date date = Date.valueOf(localDate);
	Time time = Time.valueOf(localTime);

	@Override
	public void newForm(Form f) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into form values(eid,DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		Date date = Date.valueOf(LocalDate.now());
//		Time time = Time.valueOf(LocalTime.parse(sql));
//		LocalDate localDate = LocalDate.now();
//		LocalDate localDate1 = LocalDate.parse(sql);
//		LocalTime localTime = LocalTime.parse(sql);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDate(1, date);
		ps.setString(2, f.getEvent_type().toString());
		ps.setString(3, f.getEvent_name());
		ps.setString(4, f.getEvent_description());
		ps.setDate(5, date);
		ps.setObject(6, time);
		ps.setObject(7, time);
		ps.setString(8, f.getEvent_location());
		ps.setDouble(9, f.getEvent_cost());
		ps.setString(10, f.getGrade_format().toString());
		ps.setDouble(11, f.getCurrent_grade());
		ps.setDouble(12, f.getReimbursement_amount());
		ps.setBoolean(13, f.isPre_approval());
		ps.setBoolean(14, f.isUrgent());
		ps.setString(15, f.getForm_status().toString());
		ps.setBoolean(16, f.isFile_attachment());
		ps.setBoolean(17, f.isSupervisor_approval());
		ps.setBoolean(18, f.isDepartment_head_approval());
		ps.setBoolean(19, f.isBenefit_co_approval());
		ps.setString(20, f.getApproval_response());
		ps.setString(21, f.getDenial_response());
		ps.executeUpdate();
	}

	@Override
	public int getFormByEid(int eid) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from form where eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		int event_id = 0;
		if (rs != null) {
			while (rs.next()) {
				event_id = rs.getInt(2);
				return event_id;
			}
		}
		return 0;
	}

	@Override
	public void updateForm(Form f) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update form values(eid,DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setObject(1, f.getSubmission_date().now());
//		ps.setString(2, f.getEvent_type().toString());
//		ps.setString(3, f.getEvent_name());
//		ps.setString(4, f.getEvent_description());
//		ps.setObject(5, f.getEvent_date().toString());
//		ps.setObject(6, f.getEvent_time().toString());
//		ps.setObject(7, f.getTime_missed().toString());
//		ps.setString(8, f.getEvent_location());
//		ps.setDouble(9, f.getEvent_cost());
//		ps.setString(10, f.getGrade_format().toString());
//		ps.setDouble(11, f.getCurrent_grade());
//		ps.setDouble(12, f.getReimbursement_amount());
//		ps.setBoolean(13, f.isPre_approval());
//		ps.setBoolean(14, f.isUrgent());
//		ps.setString(15, f.getForm_status().toString());
//		ps.setBoolean(16, f.isFile_attachment());
//		ps.setBoolean(17, f.isSupervisor_approval());
//		ps.setBoolean(18, f.isDepartment_head_approval());
//		ps.setBoolean(19, f.isBenefit_co_approval());
//		ps.setString(20, f.getApproval_response());
//		ps.setString(21, f.getDenial_response());
//		ps.executeUpdate();
	}

	@Override
	public List<Form> getUrgentPendingForms() throws SQLException {
		List<Form> penForms = new ArrayList<Form>();
		Connection conn= cf.getConnection();
		String sql= "select * from form where urgent=true and form_status=pending and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while(rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), LocalDate.parse(rs.getString(3)), eventType.valueOf(rs.getString(4)), rs.getString(5),rs.getString(6), rs.getDate(7).toLocalDate(), 
				rs.getTime(8).toLocalTime(), rs.getTime(9).toLocalTime(), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)), rs.getDouble(13), 
				rs.getDouble(14), rs.getBoolean(15), rs.getBoolean(16), formStatus.valueOf(rs.getString(17)), rs.getBoolean(18),
				rs.getBoolean(19), rs.getBoolean(20), rs.getBoolean(21), rs.getString(22), rs.getString(23));
			penForms.add(f);
		}
		return penForms;
	}

	@Override
	public List<Form> getNonUrgentPendingForms(int eid) throws SQLException {
		List<Form> nonUForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select * from form where urgent=false and form_status=pending and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while(rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)), rs.getString(5),rs.getString(6), rs.getDate(7).toLocalDate(), 
				rs.getTime(8).toLocalTime(), rs.getTime(9).toLocalTime(), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)), rs.getDouble(13), 
				rs.getDouble(14), rs.getBoolean(15), rs.getBoolean(16), formStatus.valueOf(rs.getString(17)), rs.getBoolean(18),
				rs.getBoolean(19), rs.getBoolean(20), rs.getBoolean(21), rs.getString(22), rs.getString(23));
			nonUForms.add(f);
		}
		return nonUForms;
	}

	@Override
	public List<Form> getClosedForms(int eid) throws SQLException {
		List<Form> cForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select * from form where form_status=approved OR form_status=denied and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while(rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)), rs.getString(5),rs.getString(6), rs.getDate(7).toLocalDate(), 
				rs.getTime(8).toLocalTime(), rs.getTime(9).toLocalTime(), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)), rs.getDouble(13), 
				rs.getDouble(14), rs.getBoolean(15), rs.getBoolean(16), formStatus.valueOf(rs.getString(17)), rs.getBoolean(18),
				rs.getBoolean(19), rs.getBoolean(20), rs.getBoolean(21), rs.getString(22), rs.getString(23));
			cForms.add(f);
		}
		return cForms;
	
	}
}