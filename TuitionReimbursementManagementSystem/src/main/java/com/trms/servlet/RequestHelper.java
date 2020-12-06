package com.trms.servlet;

import javax.servlet.http.HttpServletRequest;

import com.trms.controller.FAQsController;
import com.trms.controller.FormPageController;
import com.trms.controller.LoginController;
import com.trms.controller.LogoutController;

public class RequestHelper {

	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/TuitionReimbursementManagementSystem/login.master":
			return LoginController.login(req);
		case "/TuitionReimbursementManagementSystem/home.master":
			System.out.println("home.master test");
			return null;
		case "/TuitionReimbursementManagementSystem/faq.master":
			return FAQsController.faqs(req);
		case "/TuitionReimbursementManagementSystem/form.master":
			return FormPageController.formPage(req);
		case "/TuitionReimbursementManagementSystem/finalsubmit.master":
			//TODO
			return null;
		case "/TuitionReimbursementManagementSystem/logout.master":
			return LogoutController.logout(req);
		default:
			System.out.println("Everything went wrong.");
			return null;
		}
	}

}
