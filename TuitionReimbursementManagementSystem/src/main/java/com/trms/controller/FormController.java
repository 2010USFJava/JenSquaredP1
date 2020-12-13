package com.trms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

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
			String eventtime = req.getParameter("eventtime");
			double tmissed = Double.valueOf(req.getParameter("timemissed"));
			String eloc = req.getParameter("eventlocation");
			double rcost = Double.valueOf(req.getParameter("reimbursmentcost"));

			String gfor = om.writeValueAsString(req.getParameter("gradeformat"));
			gradeFormat gformat = om.readValue(gfor, gradeFormat.class);

			double pgrad = Double.valueOf(req.getParameter("passinggrade"));
			double ramou = Double.valueOf(req.getParameter("reimbursmentamount"));
			Boolean preapp = Boolean.valueOf(req.getParameter("preapproval"));
			Period intervalPeriod = Period.between(submissionDate, eventdate);
			Boolean urgent = false;
			if (intervalPeriod.getDays() <= 14) {
				urgent = true;
			}
			Boolean attf = Boolean.valueOf(req.getParameter("attachedfile"));

			Form f = new Form(0, 0, submissionDate, eventtype, eventname, desc, eventdate, eventtime, tmissed, eloc,
					rcost, gformat, pgrad, ramou, preapp, urgent, formStatus.PENDING, attf, false, false, false, null,
					null);

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

	public static void getUrgentPending(int eid) {
		System.out.println("In getUrgentPending");
		try {
			fdao.getUrgentPendingForms(eid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getNonUrgentPending(int eid) {
		System.out.println("In getNonUrgentPending");
		try {
			fdao.getNonUrgentPendingForms(eid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getClosed(int eid) {
		System.out.println("In getClosed");
		try {
			fdao.getClosedForms(eid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public static List<Form> getUrgSup(int supid) throws SQLException{
		List<Integer> eList = null;
		eList = fdao.getEidFromDSup(supid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if(eList!=null) {
			for(Integer e:eList) {
				fList = fdao.getUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getNonUrgSup(int supid) throws SQLException{
		List<Integer> eList = null;
		eList = fdao.getEidFromDSup(supid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if(eList!=null) {
			for(Integer e:eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getClosedSup(int supid) throws SQLException{
		List<Integer> eList = null;
		eList = fdao.getEidFromDSup(supid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if(eList!=null) {
			for(Integer e:eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getUrgDH(int dhid) throws SQLException{
		List<Integer> eList = null;
		eList = fdao.getEidFromDH(dhid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if(eList!=null) {
			for(Integer e:eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	public static List<Form> getNonUrgDH(int dhid) throws SQLException{
		List<Integer> eList = null;
		eList = fdao.getEidFromDH(dhid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if(eList!=null) {
			for(Integer e:eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}
	
	@SuppressWarnings("null")
	public static List<Form> getClosedDH(int dhid) throws SQLException{
		List<Integer> eList = null;
		eList = fdao.getEidFromDH(dhid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if(eList!=null) {
			for(Integer e:eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}
	
	public static List<Form> getAllUrg() throws SQLException{
		System.out.println("In getAllUrg");
		List<Form> fList = null;
		fList = fdao.getAllUrgentPendingForms();
		return fList;
	}
	
	public static List<Form> getAllNonUrg() throws SQLException{
		System.out.println("In getAllNonUrg");
		List<Form> fList = null;
		fList = fdao.getAllNonUrgentPendingForms();
		return fList;
	}
	
	public static List<Form> getAllClosed() throws SQLException{
		System.out.println("In getAllClosed");
		List<Form> fList = null;
		fList = fdao.getAllClosedForms();
		return fList;
	}
	
	public static Form getFormById(int eventid) throws SQLException{
		Form f = fdao.getFormByEventId(eventid);
		return f;
	}

}