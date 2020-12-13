package com.trms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.beans.Employee;
import com.trms.beans.Form;
import com.trms.beans.Form.eventType;
import com.trms.beans.Form.formStatus;
import com.trms.beans.Form.gradeFormat;
import com.trms.dao.EmployeeDao;
import com.trms.dao.FormDao;
import com.trms.daoimpl.EmployeeDaoImpl;
import com.trms.daoimpl.FormDaoImpl;

public class FormController {
	static FormDao fdao = new FormDaoImpl();
	static EmployeeDao edao = new EmployeeDaoImpl();

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
			double rcost = Double.valueOf(req.getParameter("reimbursementcost"));

			String gfor = om.writeValueAsString(req.getParameter("gradeformat"));
			gradeFormat gformat = om.readValue(gfor, gradeFormat.class);

			double pgrad = Double.valueOf(req.getParameter("passinggrade"));
			double ramou = 0;
			switch (eventtype) {
			case UCOURSE:
				ramou = (rcost * .80);
				break;
			case SEMINAR:
				ramou = (rcost * .60);
				break;
			case CERTPREP:
				ramou = (rcost * .75);
				break;
			case CERT:
				ramou = (rcost);
				break;
			case TECHTRAINING:
				ramou = (rcost * .90);
				break;
			case OTHER:
				ramou = (rcost * .30);
				break;
			default:
				ramou = 0;
			}
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
			Employee e = (Employee) req.getSession().getAttribute("currentuser");
			edao.updateReimbursement(e);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return HomeController.home(req);
	}

	public static void getUrgentPending(int eid, HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("In getUrgentPending");
		try {
			List<Form> fList = fdao.getUrgentPendingForms(eid);
			res.getWriter().write(new ObjectMapper().writeValueAsString(fList));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getNonUrgentPending(int eid, HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("In getNonUrgentPending");
		try {
			List<Form> fList = fdao.getNonUrgentPendingForms(eid);
			res.getWriter().write(new ObjectMapper().writeValueAsString(fList));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getClosed(int eid, HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("In getClosed");
		try {
			List<Form> fList = fdao.getClosedForms(eid);
			res.getWriter().write(new ObjectMapper().writeValueAsString(fList));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public static List<Form> getUrgSup(int supid, HttpServletRequest req, HttpServletResponse res) throws SQLException {
		List<Integer> eList = null;
		eList = fdao.getEidFromDSup(supid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if (eList != null) {
			for (Integer e : eList) {
				fList = fdao.getUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getNonUrgSup(int supid, HttpServletRequest req, HttpServletResponse res)
			throws SQLException {
		List<Integer> eList = null;
		eList = fdao.getEidFromDSup(supid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if (eList != null) {
			for (Integer e : eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getClosedSup(int supid, HttpServletRequest req, HttpServletResponse res)
			throws SQLException {
		List<Integer> eList = null;
		eList = fdao.getEidFromDSup(supid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if (eList != null) {
			for (Integer e : eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getUrgDH(int dhid, HttpServletRequest req, HttpServletResponse res) throws SQLException {
		List<Integer> eList = null;
		eList = fdao.getEidFromDH(dhid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if (eList != null) {
			for (Integer e : eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	public static List<Form> getNonUrgDH(int dhid, HttpServletRequest req, HttpServletResponse res)
			throws SQLException {
		List<Integer> eList = null;
		eList = fdao.getEidFromDH(dhid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if (eList != null) {
			for (Integer e : eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	@SuppressWarnings("null")
	public static List<Form> getClosedDH(int dhid, HttpServletRequest req, HttpServletResponse res)
			throws SQLException {
		List<Integer> eList = null;
		eList = fdao.getEidFromDH(dhid);
		List<Form> fList = null;
		List<Form> fullList = null;
		if (eList != null) {
			for (Integer e : eList) {
				fList = fdao.getNonUrgentPendingForms(e);
				fullList.add((Form) fList);
			}
		}
		return fullList;
	}

	public static void getAllUrg(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, JsonProcessingException, IOException {
		System.out.println("In getAllUrg");
		List<Form> fList = fdao.getAllUrgentPendingForms();
		res.getWriter().write(new ObjectMapper().writeValueAsString(fList));
	}

	public static void getAllNonUrg(HttpServletRequest req, HttpServletResponse res) throws SQLException, JsonProcessingException, IOException {
		System.out.println("In getAllNonUrg");
		List<Form> fList =  fdao.getAllNonUrgentPendingForms();
		res.getWriter().write(new ObjectMapper().writeValueAsString(fList));
	}

	public static void getAllClosed(HttpServletRequest req, HttpServletResponse res) throws SQLException, JsonProcessingException, IOException {
		System.out.println("In getAllClosed");
		List<Form> fList = fdao.getAllClosedForms();
		res.getWriter().write(new ObjectMapper().writeValueAsString(fList));
	}

	public static Form getFormByEId(int eventid, HttpServletRequest req, HttpServletResponse res) throws SQLException, JsonProcessingException, IOException {
		Form f = fdao.getFormByEventid(eventid);
		res.getWriter().write(new ObjectMapper().writeValueAsString(f));
		return f;
	}

}