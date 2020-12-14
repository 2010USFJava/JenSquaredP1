package com.trms.dao;

import java.sql.SQLException;
import java.util.List;

import com.trms.beans.Form;

public interface FormDao {
	
	public int newForm(Form f) throws SQLException;
	public Form getFormByEventid(int event_id) throws SQLException;
	public void updateForm(Form f, int event_id) throws SQLException;
	public void updateFormStatus(Form f, int event_id) throws SQLException;
	List<Form> getUrgentPendingForms(int eid) throws SQLException;
	List<Form> getNonUrgentPendingForms(int eid) throws SQLException;
	List<Form> getClosedForms(int eid) throws SQLException;
	public List<Integer> getEidFromDSup(int supeid) throws SQLException;
	public List<Integer> getEidFromDH(int dheid) throws SQLException;
	public List<Form> getAllUrgentPendingForms() throws SQLException;
	public List<Form> getAllNonUrgentPendingForms() throws SQLException;
	public List<Form> getAllClosedForms() throws SQLException;
	public List<Form> getFormByEId(int eid) throws SQLException;
}
