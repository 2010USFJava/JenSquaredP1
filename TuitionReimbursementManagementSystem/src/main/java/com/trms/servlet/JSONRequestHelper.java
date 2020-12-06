package com.trms.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trms.controller.EmployeeController;

public class JSONRequestHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		switch(req.getRequestURI()) {
		case "/TuitionReimbursementManagementSystem/getsession/json":
			EmployeeController.getSessionUser(req, res);
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		default:
			System.out.println("Everything went wrong.");
		}
	}

}
