package com.trms.dao;

import java.sql.SQLException;

import com.trms.beans.Employee;

public interface EmployeeDao {
	
	public Employee getEmployeeByID() throws SQLException;
	public Employee getEmployeeByUsername() throws SQLException;
	public int getEmployeeIDByUsername() throws SQLException;

}
