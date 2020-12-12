package com.trms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.beans.Form;
import com.trms.beans.Form.eventType;
import com.trms.beans.Form.formStatus;
import com.trms.beans.Form.gradeFormat;
import com.trms.dao.FormDao;
import com.trms.daoimpl.FormDaoImpl;

public class FormController {
	static FormDao fdao = new FormDaoImpl();
	static DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_TIME;
	String eventtime1 = "hh:mm";
	

	public static String newForm(HttpServletRequest req) throws SQLException, JsonProcessingException {
		if (!req.getMethod().equals("POST")) {
			return "html/form.html";
		}
		ObjectMapper om = new ObjectMapper();
		
		LocalDate submissionDate = LocalDate.parse(req.getParameter("todaysdate"));
		try {
		String etype = om.writeValueAsString(req.getParameter("eventtype"));
		eventType eventtype = om.readValue(etype, eventType.class);
		
		String eventname = req.getParameter("eventname");
		String desc = req.getParameter("eventdescription");
		LocalDate eventdate = LocalDate.parse(req.getParameter("eventdate"));
//		String etime = om.writeValueAsString(req.getParameter("eventtime"));
		String eventtime = req.getParameter("eventtime");
//		LocalTime eventtime = LocalTime.parse(req.getParameter("eventtime"));
		double tmissed = Double.valueOf(req.getParameter("timemissed"));
		String eloc = req.getParameter("eventlocation");
		double rcost = Double.valueOf(req.getParameter("reimbursementcost"));
		
		String gfor = om.writeValueAsString(req.getParameter("gradeformat"));
		gradeFormat gformat = om.readValue(gfor, gradeFormat.class); 
		
		double pgrad = Double.valueOf(req.getParameter("passinggrade"));
		double ramou = Double.valueOf(req.getParameter("reimbursementamount"));
		Boolean preapp = Boolean.valueOf(req.getParameter("preapproval"));
		Period intervalPeriod = Period.between(submissionDate, eventdate);
		Boolean urgent =false;
		if(intervalPeriod.getDays() <= 14) {
			 urgent=true;
		}
		Boolean attf = Boolean.valueOf(req.getParameter("attachedfile"));
		
		Form f = new Form(0,0,submissionDate, eventtype, eventname, desc, eventdate, eventtime, tmissed, eloc, rcost, gformat,
				pgrad, ramou, preapp, urgent, formStatus.PENDING, attf, false, false, false, null, null);

		fdao.newForm(f);
	
		
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "form.master";
	}
}