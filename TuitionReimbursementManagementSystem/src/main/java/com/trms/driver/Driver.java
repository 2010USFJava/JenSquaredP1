package com.trms.driver;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.trms.beans.Form;
import com.trms.beans.Form.eventType;
import com.trms.beans.Form.formStatus;
import com.trms.beans.Form.gradeFormat;
import com.trms.daoimpl.FormDaoImpl;

public class Driver {

	public static void main(String[] args) throws SQLException {
		Form form = new Form(0,1,LocalDate.now(), eventType.CERT, "Blah", "blah blah blah", LocalDate.parse("2020-12-10"), LocalTime.now(), 2345, 
				"blah ave", 500, gradeFormat.GRADE, 80, 0, false, false, formStatus.PENDING, false, false, false, false, "", "");
		List<Form> fList = new ArrayList<Form>();
		fList.add(form);
		insertForm(fList);
	}
	
	public static void insertForm(List<Form> fList) throws SQLException {
		FormDaoImpl fdao = new FormDaoImpl();
		for (int i = 0; i < fList.size(); i++) {
			fdao.newForm(fList.get(i));
		}
		
		System.out.println(fdao.getFormByEid(0));
	}

}
