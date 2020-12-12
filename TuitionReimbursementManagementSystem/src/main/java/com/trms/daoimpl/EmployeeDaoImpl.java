package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trms.beans.Employee;
import com.trms.beans.Form;
import com.trms.dao.EmployeeDao;
import com.trms.util.ConnFactory;

public class EmployeeDaoImpl implements EmployeeDao{
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public Employee getEmployeeByID(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from employee where eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		Employee e = null;
		if(rs!=null) {
			while(rs.next()) {
				e = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),
						rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getBoolean(14),rs.getBoolean(15),
						rs.getBoolean(16));
			}
		}
		return e;
	}

	@Override
	public Employee getEmployeeByEmail(String email) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from employee where email=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		Employee e = null;
		if(rs!=null) {
			while(rs.next()) {
				e = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),
						rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getBoolean(14),rs.getBoolean(15),
						rs.getBoolean(16));
			}
		}
		return e;
	}

	@Override
	public int getEmployeeIDByUsername(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select eid from employee where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Employee e = null;
		if(rs!=null) {
			while(rs.next()) {
				e = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),
						rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getBoolean(14),rs.getBoolean(15),
						rs.getBoolean(16));
			}
		}
		return e.getEid();
	}

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		Connection conn = cf.getConnection();
		List<Employee> elist = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement("select * from employee");
		ResultSet rs = ps.executeQuery();
		Employee e = null;
		while(rs.next()) {
			e = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
					rs.getString(6),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),
					rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getBoolean(14),rs.getBoolean(15),
					rs.getBoolean(16));
			elist.add(e);
		}
		System.out.println("in getAllEmployees");
		System.out.println(elist.toString());
		return elist;
	}

	@Override
	public Employee updateReimbursement(Employee e, int eid, Boolean approved, Boolean denied) throws SQLException {
		Connection conn = cf.getConnection();
		double awarded = e.getAwarded_reimbursement();
		double available = e.getAvailable_reimbursement();
		double pending = e.getPending_reimbursement();
		double available1 = 1000;
		if(approved =true) {
			available1 = 1000 - (pending + awarded);
			e.setAvailable_reimbursement(available1);
		}else if (denied = true){
			available1 = 1000 + (awarded - pending); 
			e.setAvailable_reimbursement(available1);
		}else {
			
		}String sql = "update employee set awarded_reimbursement=? and pending_reimbursement=?"
				+ "and available_reimbursement=? where eid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, awarded);
		ps.setDouble(2, pending);  //available_balance=$1000-pending_reimbursement-awarded_reimbursement
		ps.setDouble(3, available1);
		ps.setInt(4, eid);
		ps.executeUpdate();
		return e;
	}


}
