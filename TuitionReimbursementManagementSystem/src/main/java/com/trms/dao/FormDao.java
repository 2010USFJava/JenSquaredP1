package com.trms.dao;

import java.sql.SQLException;
import java.util.List;

import com.trms.beans.Form;

public interface FormDao {
	
	public void newForm(Form f) throws SQLException;
	public int getFormByEid(int eid) throws SQLException;
	public void updateForm(Form f) throws SQLException;
	List<Form> getUrgentPendingForms() throws SQLException;
	List<Form> getNonUrgentPendingForms(int eid) throws SQLException;
	List<Form> getClosedForms(int eid) throws SQLException;
	
	
	
//	public void updateSupApproval() throws SQLException;
//	public void updateDHApproval() throws SQLException;
//	public void updateBenCoApproval() throws SQLException;
//	public void updateReimbursementAmount() throws SQLException;
//	public void deleteForm() throws SQLException;
	

}