package com.trms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.servlet.RequestHelper;

public class MasterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in master doGet");
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in master doPost");
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req, res);
	}

}
