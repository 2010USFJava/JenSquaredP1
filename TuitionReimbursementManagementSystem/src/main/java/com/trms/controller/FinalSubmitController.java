package com.trms.controller;

import javax.servlet.http.HttpServletRequest;

public class FinalSubmitController {
	
	public static String submitpage(HttpServletRequest req) {
		return "html/finalsubmit.html";
	}
	
	public static String approveordenypage(HttpServletRequest req) {
		return "html/approveordeny.html";
	}

}
