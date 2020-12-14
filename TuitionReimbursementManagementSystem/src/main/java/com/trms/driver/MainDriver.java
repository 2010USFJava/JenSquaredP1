package com.trms.driver;

import java.sql.SQLException;
import java.util.List;

import com.trms.beans.Form;
import com.trms.dao.FormDao;
import com.trms.daoimpl.FormDaoImpl;

public class MainDriver {
	
	public static void main(String[] args) {
		FormDao fdao = new FormDaoImpl();
		List<Form> testList = null;
		try {
			testList = fdao.getUrgentPendingForms(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(testList);
	}
	
}
