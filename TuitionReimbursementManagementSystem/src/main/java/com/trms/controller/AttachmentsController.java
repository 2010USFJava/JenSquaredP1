package com.trms.controller;

import javax.servlet.http.HttpServletRequest;

public class AttachmentsController {

	public static String newAttachment(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "html/form.html";
		}
		
		
		
		return "form.master";
	}

}
