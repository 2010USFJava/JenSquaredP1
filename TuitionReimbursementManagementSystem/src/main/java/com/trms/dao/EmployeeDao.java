package com.trms.dao;

import java.sql.SQLException;

public interface EmployeeDao {
	
	public String getEmployee() throws SQLException;
	public int getEmployeeID() throws SQLException;

}
