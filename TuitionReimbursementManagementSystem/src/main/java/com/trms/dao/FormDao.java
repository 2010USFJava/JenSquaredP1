package com.trms.dao;

import java.sql.SQLException;

import com.trms.beans.Form;

public interface FormDao {
	
	public int newForm() throws SQLException;
	public Form getFormByEId() throws SQLException;
	public void updateSupApproval() throws SQLException;
	public void updateDHApproval() throws SQLException;
	public void updateBenCoApproval() throws SQLException;
	public void updateReimbursementAmount() throws SQLException;
	public void deleteForm() throws SQLException;
	

}
