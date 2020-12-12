package com.trms.dao;

import java.sql.SQLException;
import java.util.List;

import com.trms.beans.Employee;
import com.trms.beans.Form;

public interface FormDao {
	
	public int newForm(Form f) throws SQLException;
	public int getFormByEid(int eid) throws SQLException;
	public void updateForm(Form f, int event_id) throws SQLException;
	List<Form> getUrgentPendingForms() throws SQLException;
	List<Form> getNonUrgentPendingForms(int eid) throws SQLException;
	List<Form> getClosedForms(int eid) throws SQLException;
//	public Form updateReimbursement(Employee e, Form f, int eid) throws SQLException;
	

}
