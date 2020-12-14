package com.trms.dao;

import java.sql.SQLException;
import java.util.List;

import com.trms.beans.Employee;

public interface EmployeeDao {
	
	public Employee getEmployeeByID(int id) throws SQLException;
	public Employee getEmployeeByEmail(String email) throws SQLException;
	public int getEmployeeIDByUsername(String username) throws SQLException;
	public List<Employee> getAllEmployees()throws SQLException;
	public Employee updateReimbursement(Employee e) throws SQLException;

}
