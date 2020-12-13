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

import com.trms.beans.Form;
import com.trms.beans.Form.eventType;
import com.trms.beans.Form.formStatus;
import com.trms.beans.Form.gradeFormat;
import com.trms.dao.EmployeeDao;
import com.trms.dao.FormDao;
import com.trms.util.ConnFactory;
import com.trms.util.LogThis;

public class FormDaoImpl implements FormDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	LocalDateTime localDateTime = LocalDateTime.now();
	LocalDate localDate = localDateTime.toLocalDate();
	LocalTime localTime = localDateTime.toLocalTime();
	Date date = Date.valueOf(localDate);
	
	@Override
	public int newForm(Form f) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into form values(?,DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		ps.setDouble(13, f.getReimbursement_amount());
		ps.setBoolean(14, f.isPre_approval());
		ps.setBoolean(15, f.isUrgent());
		ps.setString(16, f.getForm_status().toString());
		ps.setBoolean(17, f.isFile_attachment());
		ps.setBoolean(18, f.isSupervisor_approval());
		ps.setBoolean(19, f.isDepartment_head_approval());
		ps.setBoolean(20, f.isBenefit_co_approval());
		ps.setString(21, f.getApproval_response());
		ps.setString(22, f.getDenial_response());
		int event_id = 0;
		int affectedrows = ps.executeUpdate();
		if (affectedrows > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				event_id = rs.getInt(2);
			}
		}
		LogThis.LogIt("info", event_id + ",has been submitted");
		EmployeeDao edao = new EmployeeDaoImpl();
		edao.updateReimbursement(e, approved, denied);
		return event_id;
	}

	@Override
	public Form getFormByEventid(int event_id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from form where eventid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, event_id);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		if (rs != null) {
			while (rs.next()) {
				f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)),
						rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8),
						rs.getDouble(9), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)),
						rs.getDouble(13), rs.getDouble(14), rs.getBoolean(15), rs.getBoolean(16),
						formStatus.valueOf(rs.getString(17)), rs.getBoolean(18), rs.getBoolean(19), rs.getBoolean(20),
						rs.getBoolean(21), rs.getString(22), rs.getString(23));
			}
		}
		return f;
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
		LogThis.LogIt("info", "Form has been updated.");
	}

	@Override
	public List<Form> getUrgentPendingForms(int eid) throws SQLException {
		System.out.println("in fdao method");
		List<Form> penForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select event_id, event_name, reimbursement_amount, form_status from form where urgent=true and form_status='pending' and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getString(2),rs.getDouble(3),formStatus.valueOf(rs.getString(4)));
			penForms.add(f);
		}
		return penForms;
	}

	@Override
	public List<Form> getNonUrgentPendingForms(int eid) throws SQLException {
		System.out.println("in fdao method");
		List<Form> nonUForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select event_id, event_name, reimbursement_amount, form_status from form where urgent=false and form_status='pending' and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getString(2),rs.getDouble(3),formStatus.valueOf(rs.getString(4)));
			nonUForms.add(f);
		}
		return nonUForms;
	}

	@Override
	public List<Form> getClosedForms(int eid) throws SQLException {
		System.out.println("in fdao method");
		List<Form> cForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select event_id, event_name, reimbursement_amount, form_status from form where form_status='approved' OR form_status='denied' and eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getString(2),rs.getDouble(3),formStatus.valueOf(rs.getString(4)));
			cForms.add(f);
		}
		return cForms;
	}
	
	@Override
	public List<Integer> getEidFromDSup(int supeid) throws SQLException{
		System.out.println("in fdao method");
		List<Integer> eList = new ArrayList<Integer>();
		Connection conn = cf.getConnection();
		String sql = "select eid from employee e inner join employee m on m.eid = e.supervisor_id where m.eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, supeid);
		ResultSet rs = ps.executeQuery();
		Integer e=null;
		while (rs.next()) {
			e = rs.getInt(1);
			eList.add(e);
		}
		return eList;
	}
	
	@Override
	public List<Integer> getEidFromDH(int dheid) throws SQLException{
		System.out.println("in fdao method");
		List<Integer> eList = new ArrayList<Integer>();
		Connection conn = cf.getConnection();
		String sql = "select eid from employee e inner join employee m on m.eid = e.department_head_id where m.eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, dheid);
		ResultSet rs = ps.executeQuery();
		Integer e=null;
		while (rs.next()) {
			e = rs.getInt(1);
			eList.add(e);
		}
		return eList;
	}
	
	@Override
	public List<Form> getAllUrgentPendingForms() throws SQLException {
		System.out.println("in fdao method");
		List<Form> penForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select event_id, event_name, reimbursement_amount, form_status from form where urgent=true and form_status='pending'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getString(2),rs.getDouble(3),formStatus.valueOf(rs.getString(4)));
			penForms.add(f);
		}
		return penForms;
	}
	
	@Override
	public List<Form> getAllNonUrgentPendingForms() throws SQLException {
		System.out.println("in fdao method");
		List<Form> nonUForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select event_id, event_name, reimbursement_amount, form_status from form where urgent=false and form_status='pending'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getString(2),rs.getDouble(3),formStatus.valueOf(rs.getString(4)));
			nonUForms.add(f);
		}
		return nonUForms;
	}
	
	@Override
	public List<Form> getAllClosedForms() throws SQLException{
		System.out.println("in fdao method");
		List<Form> cForms = new ArrayList<Form>();
		Connection conn = cf.getConnection();
		String sql = "select event_id, event_name, reimbursement_amount, form_status from form where form_status='approved' OR form_status='denied'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while (rs.next()) {
			f = new Form(rs.getInt(1), rs.getString(2),rs.getDouble(3),formStatus.valueOf(rs.getString(4)));
			cForms.add(f);
		}
		return cForms;
	}
	
	@Override
	public Form getFormByEventId(int eid) throws SQLException{
		System.out.println("in fdao method");
		Connection conn = cf.getConnection();
		String sql = "select * from form where event_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, eid);
		ResultSet rs = ps.executeQuery();
		Form f = null;
		while(rs.next()) {
			f = new Form(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), eventType.valueOf(rs.getString(4)),
					rs.getString(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getString(8),
					rs.getDouble(9), rs.getString(10), rs.getDouble(11), gradeFormat.valueOf(rs.getString(12)),
					rs.getDouble(13), rs.getDouble(14), rs.getBoolean(15), rs.getBoolean(16),
					formStatus.valueOf(rs.getString(17)), rs.getBoolean(18), rs.getBoolean(19), rs.getBoolean(20),
					rs.getBoolean(21), rs.getString(22), rs.getString(23));
		}
		return f;
	}
}