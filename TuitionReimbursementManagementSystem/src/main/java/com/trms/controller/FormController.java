package com.trms.controller;

import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import com.trms.beans.Form;
import com.trms.dao.FormDao;
import com.trms.daoimpl.FormDaoImpl;

public class FormController {
	static FormDao fdao = new FormDaoImpl();

	public static String newForm(HttpServletRequest req) throws SQLException {
		if (!req.getMethod().equals("POST")) {
			return "html/form.html";
		}
		String name = req.getParameter("empfirstname, emplastname");
		String LocalDate = req.getParameter("todaysdate");
		String eventtype =  req.getParameter("eventtype");
		String eventname = req.getParameter("eventname");
		String desc = req.getParameter("eventdescription");
		String edate = req.getParameter("eventdate");
		String LocalTime =  req.getParameter("time");
		String tmissed = req.getParameter("timemissed");
		String eloc = req.getParameter("eventlocation");
		int rcost = Integer.valueOf(req.getParameter("reimbursmentcost"));
		String gformat = req.getParameter("gradeformat");
		String pgrad = req.getParameter("passinggrade");
		int ramou = Integer.valueOf(req.getParameter("reimbursmentamount"));
		String preapp = req.getParameter("preapproval");
		String attf =  req.getParameter("attachedfile");
		String attf1 =  req.getParameter("attachedfile");
		Form f = new Form(name, LocalDate, eventtype, eventname, desc, edate, LocalTime, 
				tmissed, eloc, rcost, gformat, pgrad, ramou, preapp, attf, attf1);
		
		fdao.newForm(f);
		return "form.master";
		
	}
}