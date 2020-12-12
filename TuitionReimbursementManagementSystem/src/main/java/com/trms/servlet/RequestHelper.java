package com.trms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trms.controller.EmployeeController;
import com.trms.controller.FAQsController;
import com.trms.controller.FormController;
import com.trms.controller.FormPageController;
import com.trms.controller.HomeController;
import com.trms.controller.LoginController;
import com.trms.controller.LogoutController;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, SQLException{
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/TuitionReimbursementManagementSystem/login.master":
			return LoginController.login(req, res);
		case "/TuitionReimbursementManagementSystem/home.master":
			return HomeController.home(req);
		case "/TuitionReimbursementManagementSystem/faq.master":
			return FAQsController.faqs(req);
		case "/TuitionReimbursementManagementSystem/formpage.master":
			return FormPageController.formPage(req);
		case "/TuitionReimbursementManagementSystem/finalsubmit.master":
			//TODO
			return null;
		case "/TuitionReimbursementManagementSystem/logout.master":
			return LogoutController.logout(req, res);
		default:
			System.out.println("Everything went wrong.");
			return null;
		}
	}

}
