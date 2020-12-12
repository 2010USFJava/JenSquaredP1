package com.trms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Employee;
import com.trms.util.TRMSService;

public class LoginController {
	
static TRMSService serv = new TRMSService();
	
	public static String login(HttpServletRequest req, HttpServletResponse res) {
		if(!req.getMethod().equals("POST")) {
			return "html/index.html";
		}
		String email = req.getParameter("inputEmail");
		String password = req.getParameter("inputPassword");
		Employee e = serv.loginGetEmployee(email, password);
		if(e==null) { //this block is why the forwarding works
			return serv.wrongCreds();
		}else {
			req.getSession().setAttribute("currentuser", e);
			
//			Cookie ckName = new Cookie("user",e.getName());
//			ckName.setMaxAge(3600);
//			res.addCookie(ckName);
//			
//			String isSup = null;
//			if(e.isIs_supervisor()) {isSup="true";} else {isSup="false";}
//			Cookie ckIsDS = new Cookie("Sup",isSup);
//			ckIsDS.setMaxAge(3600);
//			res.addCookie(ckIsDS);
//			
//			String isHead = null;
//			if(e.isIs_department_head()) {isHead="true";} else {isHead="false";}
//			Cookie isDH = new Cookie("DH",isHead);
//			isDH.setMaxAge(3600);
//			res.addCookie(isDH);
//			
//			String isBen = null;
//			if(e.isIs_supervisor()) {isBen="true";} else {isBen="false";}
//			Cookie ckIsBenCo = new Cookie("BenCo",isBen);
//			ckIsDS.setMaxAge(3600);
//			res.addCookie(ckIsBenCo);
//			
			return "home.master";
		}
	}
}
