package com.trms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.beans.Employee;

public class EmployeeController {

	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("in employee controller");
		Employee e = (Employee) req.getSession().getAttribute("currentuser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(e));
//		Cookie ck = new Cookie("name",e);
//		res.addCookie(ck);
//		System.out.println("Cookie: "+ck);
//		res.setContentType("text/html");
//		PrintWriter out = res.getWriter();
//		out.print("Hello "+ck.getValue());
//		out.close();
	}

}
