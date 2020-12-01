package com.trms.beans;

public class Employee {
	
	private int eid;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String username;
	private String password;
	private double available_reimbursement;
	private double pending_reimbursement;
	private double awarded_reimbursement;
	private int supervisor_id;
	private int department_head_id;
	private int benefit_co_id;
	private boolean is_supervisor;
	private boolean is_department_head;
	private boolean is_benefit_co;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int eid, String name, String address, String phone, String email, String username, String password,
			double available_reimbursement, double pending_reimbursement, double awarded_reimbursement,
			int supervisor_id, int department_head_id, int benefit_co_id, boolean is_supervisor,
			boolean is_department_head, boolean is_benefit_co) {
		super();
		this.eid = eid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.available_reimbursement = available_reimbursement;
		this.pending_reimbursement = pending_reimbursement;
		this.awarded_reimbursement = awarded_reimbursement;
		this.supervisor_id = supervisor_id;
		this.department_head_id = department_head_id;
		this.benefit_co_id = benefit_co_id;
		this.is_supervisor = is_supervisor;
		this.is_department_head = is_department_head;
		this.is_benefit_co = is_benefit_co;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAvailable_reimbursement() {
		return available_reimbursement;
	}
	public void setAvailable_reimbursement(double available_reimbursement) {
		this.available_reimbursement = available_reimbursement;
	}
	public double getPending_reimbursement() {
		return pending_reimbursement;
	}
	public void setPending_reimbursement(double pending_reimbursement) {
		this.pending_reimbursement = pending_reimbursement;
	}
	public double getAwarded_reimbursement() {
		return awarded_reimbursement;
	}
	public void setAwarded_reimbursement(double awarded_reimbursement) {
		this.awarded_reimbursement = awarded_reimbursement;
	}
	public int getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}
	public int getDepartment_head_id() {
		return department_head_id;
	}
	public void setDepartment_head_id(int department_head_id) {
		this.department_head_id = department_head_id;
	}
	public int getBenefit_co_id() {
		return benefit_co_id;
	}
	public void setBenefit_co_id(int benefit_co_id) {
		this.benefit_co_id = benefit_co_id;
	}
	public boolean isIs_supervisor() {
		return is_supervisor;
	}
	public void setIs_supervisor(boolean is_supervisor) {
		this.is_supervisor = is_supervisor;
	}
	public boolean isIs_department_head() {
		return is_department_head;
	}
	public void setIs_department_head(boolean is_department_head) {
		this.is_department_head = is_department_head;
	}
	public boolean isIs_benefit_co() {
		return is_benefit_co;
	}
	public void setIs_benefit_co(boolean is_benefit_co) {
		this.is_benefit_co = is_benefit_co;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
				+ email + ", username=" + username + ", password=" + password + ", available_reimbursement="
				+ available_reimbursement + ", pending_reimbursement=" + pending_reimbursement
				+ ", awarded_reimbursement=" + awarded_reimbursement + ", supervisor_id=" + supervisor_id
				+ ", department_head_id=" + department_head_id + ", benefit_co_id=" + benefit_co_id + ", is_supervisor="
				+ is_supervisor + ", is_department_head=" + is_department_head + ", is_benefit_co=" + is_benefit_co
				+ "]";
	}

}
