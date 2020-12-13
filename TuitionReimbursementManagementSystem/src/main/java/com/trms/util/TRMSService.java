package com.trms.util;

import java.sql.SQLException;
import java.util.List;

import com.trms.beans.Employee;
import com.trms.dao.EmployeeDao;
import com.trms.daoimpl.EmployeeDaoImpl;

public class TRMSService {

	EmployeeDao edao = new EmployeeDaoImpl();

	// login verify method--postgres
	public boolean loginVerify(String email, String password) {
		List<Employee> eList = null;
		try {
			eList = edao.getAllEmployees();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		boolean verify = false;
		System.out.println("right before enhanced for loop");
		for(int i=0;i<eList.size();i++) {
			Employee e=eList.get(i);
			if(e.getEmail().equals(email)&&e.getPassword().equals(password)) {
				verify = true;
				System.out.println(verify);
				return verify;
			}else {
				i++;
			}
		}
		System.out.println("login verify failed");
		return verify;
	}

	// get user name on login
	public Employee loginGetEmployee(String email, String password) {
		if (loginVerify(email, password)) {
			try {
				return edao.getEmployeeByEmail(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// other security methods?
	public String wrongCreds() {
		return "html/unsuccessfullogin.html";
	}

	public TRMSService() {

	}

}
