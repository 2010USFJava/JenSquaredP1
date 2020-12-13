package com.trms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.beans.Employee;
import com.trms.beans.Form;
import com.trms.dao.EmployeeDao;
import com.trms.dao.FormDao;
import com.trms.daoimpl.EmployeeDaoImpl;
import com.trms.daoimpl.FormDaoImpl;

public class EmployeeController {
	
	static EmployeeDao edao = new EmployeeDaoImpl();
	static FormDao fdao = new FormDaoImpl();

	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("in employee controller");
		Employee e = (Employee) req.getSession().getAttribute("currentuser");
		res.setContentType("text/plain");
		res.getWriter().write(new ObjectMapper().writeValueAsString(e));
	}
	
	public static void updateReimbursement(HttpServletRequest req,boolean approved) throws SQLException {
		Employee e = (Employee) req.getSession().getAttribute("currentuser");
		int fid = 0; //get from ajax somehow
		Form f = fdao.getFormByEventid(fid);
		if(approved) {
			e.setAwarded_reimbursement(e.getAwarded_reimbursement()+f.getReimbursement_amount());
			e.setPending_reimbursement(e.getPending_reimbursement()-f.getReimbursement_amount());
			e.setAvailable_reimbursement(1000-e.getAwarded_reimbursement()-e.getPending_reimbursement());
		}
		boolean denied=false;
		if(approved==false) {
			denied=true;
			e.setPending_reimbursement(e.getPending_reimbursement()-f.getReimbursement_amount());
			e.setAvailable_reimbursement(1000-e.getAwarded_reimbursement()-e.getPending_reimbursement());
		}
		edao.updateReimbursement(e, approved, denied);
	}

}
