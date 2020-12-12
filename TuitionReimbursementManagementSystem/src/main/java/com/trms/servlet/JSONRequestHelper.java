package com.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trms.beans.Employee;
import com.trms.controller.AttachmentsController;
import com.trms.controller.EmployeeController;
import com.trms.controller.FormController;
import com.trms.daoimpl.FormDaoImpl;

public class JSONRequestHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, SQLException{
		Employee e = (Employee) req.getSession().getAttribute("currentuser");
		switch(req.getRequestURI()) {
		case "/TuitionReimbursementManagementSystem/getsession.json":
			EmployeeController.getSessionUser(req, res);
			break;
		case "/TuitionReimbursementManagementSystem/form.json":
			FormController.newForm(req);
			break;
		case "/TuitionReimbursementManagementSystem/attachments.json":
			AttachmentsController.newAttachment(req);
			break;
		case "/TuitionReimbursementManagementSystem/geturglist.json":
			if(e.isIs_benefit_co()) {
				FormController.getAllUrg();
			}else if(e.isIs_department_head()) {
				FormController.getUrgDH(e.getEid());
			}else if(e.isIs_supervisor()) {
				FormController.getUrgSup(e.getEid());
			}else {
				FormController.getUrgentPending(e.getEid());
			}
			break;
		case "/TuitionReimbursementManagementSystem/getnonurglist.json":
			if(e.isIs_benefit_co()) {
				FormController.getAllNonUrg();
			}else if(e.isIs_department_head()) {
				FormController.getNonUrgDH(e.getEid());
			}else if(e.isIs_supervisor()) {
				FormController.getNonUrgSup(e.getEid());
			}else {
				FormController.getNonUrgentPending(e.getEid());
			}
			break;
		case "/TuitionReimbursementManagementSystem/getclosedlist.json":
			if(e.isIs_benefit_co()) {
				FormController.getAllClosed();
			}else if(e.isIs_department_head()) {
				FormController.getClosedDH(e.getEid());
			}else if(e.isIs_supervisor()) {
				FormController.getClosedSup(e.getEid());
			}else {
				FormController.getClosed(e.getEid());
			}
			break;
		default:
			System.out.println("Everything went wrong JSON.");
		}
	}

}
