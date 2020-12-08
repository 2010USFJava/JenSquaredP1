package com.trms.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		switch(req.getRequestURI()) {
//		case "/HelloFrontController/getsession.json":
//			VillainController.getSessionVillain(req, res);
//			break;
//		default:
//			SuperVillain vill = new SuperVillain("?","?",0);
//			res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		}
	}

}
