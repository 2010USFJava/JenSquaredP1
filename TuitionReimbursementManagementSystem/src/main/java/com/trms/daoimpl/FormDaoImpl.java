package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.trms.beans.Employee;
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
//	Time time = Time.valueOf(localTime);

	@Override
	public int newForm(Form f) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into form values(?,DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, f.getEid());
		ps.setDate(2, date);
		ps.setString(3, f.getEvent_type().toString());
		ps.setString(4, f.getEvent_name());
		ps.setString(5, f.getEvent_description());
		ps.setDate(6, date);
		ps.setString(7, f.getEvent_time());
		ps.setDouble(8, f.getTime_missed());
		ps.setString(9, f.getEvent_location());
		ps.setDouble(10, f.getEvent_cost());
		ps.setString(11, f.getGrade_format().toString());
		ps.setDouble(12, f.getCurrent_grade());
		ps.setBoolean(13, f.isPre_approval());
		ps.setBoolean(14, f.isUrgent());
		ps.setString(15, f.getForm_status().toString());
		ps.setBoolean(16, f.isFile_attachment());
		ps.setBoolean(17, f.isSupervisor_approval());
		ps.setBoolean(18, f.isDepartment_head_approval());
		ps.setBoolean(19, f.isBenefit_co_approval());
		ps.setString(20, f.getApproval_response());
		ps.setString(21, f.getDenial_response());
		int event_id = 0;
		int affectedrows = ps.executeUpdate();
		if (affectedrows > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				event_id = rs.getInt(2);
			}
		}
		return event_id;
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
	public void updateForm(Form f, int event_id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update form set reimbursement_amount=? AND form_status=? AND supervisor_approval=? AND department_head_approval=? "
				+ "AND benefit_co_approval=? AND approval_response=? AND denial_response=? where event_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, f.getReimbursement_amount());
		ps.setString(2, f.getForm_status().toString());
		ps.setBoolean(3, f.isSupervisor_approval());
		ps.setBoolean(4, f.isDepartment_head_approval());
		ps.setBoolean(5, f.isBenefit_co_approval());
		ps.setString(6, f.getApproval_response());
		ps.setString(7, f.getDenial_response());
		ps.setInt(8, event_id);
		ps.executeUpdate();
	}

	@Override
	public List<Form> getUrgentPendingForms() throws SQLException {
		List<Form> penForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select * from form where urgent=true and form_status=pending and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)),
					rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8),
					rs.getDouble(9), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)),
					rs.getDouble(13), rs.getBoolean(14), rs.getBoolean(15),
					formStatus.valueOf(rs.getString(16)), rs.getBoolean(17), rs.getBoolean(18), rs.getBoolean(19),
					rs.getBoolean(20), rs.getString(21), rs.getString(22));
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
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)),
					rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8),
					rs.getDouble(9), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)),
					rs.getDouble(13), rs.getBoolean(14), rs.getBoolean(15),
					formStatus.valueOf(rs.getString(16)), rs.getBoolean(17), rs.getBoolean(18), rs.getBoolean(19),
					rs.getBoolean(20), rs.getString(21), rs.getString(22));
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
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)),
					rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8),
					rs.getDouble(9), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)),
					rs.getDouble(13), rs.getBoolean(14), rs.getBoolean(15),
					formStatus.valueOf(rs.getString(16)), rs.getBoolean(17), rs.getBoolean(18), rs.getBoolean(19),
					rs.getBoolean(20), rs.getString(21), rs.getString(22));
			cForms.add(f);
		}
		return cForms;

	}

//	@Override
//	public Form updateReimbursement(Employee e, Form f, int eid) throws SQLException {
//		Connection conn = cf.getConnection();
//		double awarded = e.getAwarded_reimbursement();
//		double available = e.getAvailable_reimbursement();
//		double pending = e.getPending_reimbursement();
//		available = 1000 - pending - awarded; 
//				
//				String sql = "update form set awarded_reimbursement=? and pending_reimbursment=?"
//						+ "and available_reimbursment=? where eid=?";
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ps.setDouble(1, );
//				ps.setDouble(2, );
//				ps.setDouble(3, );
//				ps.setInt(4, eid);  //available_balance=$1000-pending_reimbursement-awarded_reimbursement
//				ps.executeUpdate();
//			
//			return f;
		
//	}
}
