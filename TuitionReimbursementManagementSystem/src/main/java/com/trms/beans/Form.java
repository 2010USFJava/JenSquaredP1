package com.trms.beans;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.trms.util.LogThis;

public class Form {
	
	private int eid;
	private int event_id;
	private LocalDate submission_date;
	public enum eventType{
		UCOURSE("UCOURSE"),
		SEMINAR("SEMINAR"),
		CERTPREP("CERTPREP"),
		CERT("CERT"),
		TECHTRAINING("TECHTRAINING"),
		OTHER("OTHER");
		private String event;
		private eventType(String etype) {
			this.event=etype;
		}
		@Override
		public String toString() {
			return event;
		}
	}
	public eventType event_type;
	private String event_name;
	private String event_description;
	private LocalDate event_date;
	private String event_time;
	private double time_missed;
	private String event_location;
	private double event_cost;
	
	public enum gradeFormat{
		GRADE("GRADE"),
		PRESENTATION("PRESENTATION");
		private String format;
		private gradeFormat(String gformat) {
			this.format=gformat;
		}
		@Override
		public String toString() {
			return format;
		}
	}
	public gradeFormat grade_format;
	
	private double current_grade;
	private double reimbursement_amount;
	private boolean pre_approval;
	private boolean urgent;
	public enum formStatus{
		PENDING("PENDING"),
		APPROVED("APPROVED"),
		DENIED("DENIED");
		private String status;
		private formStatus(String fstatus) {
			this.status=fstatus;
		}
		@Override
		public String toString() {
			return status;
		}
	}
	public formStatus form_status;
	private boolean file_attachment;
	private boolean supervisor_approval;
	private boolean department_head_approval;
	private boolean benefit_co_approval;
	private String approval_response;
	private String denial_response;
	
	public Form() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Form(int event_id, String event_name, double reimbursement_amount, formStatus form_status) {
		super();
		this.event_id=event_id;
		this.event_name=event_name;
		this.reimbursement_amount=reimbursement_amount;
		this.form_status=form_status;
	}

	public Form(int eid, int event_id, LocalDate submission_date, eventType event_type, String event_name,
			String event_description, LocalDate event_date, String event_time, double time_missed,
			String event_location, double event_cost, gradeFormat grade_format, double current_grade,
			double reimbursement_amount, boolean pre_approval, boolean urgent, formStatus form_status,
			boolean file_attachment, boolean supervisor_approval, boolean department_head_approval,
			boolean benefit_co_approval, String approval_response, String denial_response) {
		super();
		this.eid = eid;
		this.event_id = event_id;
		this.submission_date = submission_date;
		this.event_type = event_type;
		this.event_name = event_name;
		this.event_description = event_description;
		this.event_date = event_date;
		this.event_time = event_time;
		this.time_missed = time_missed;
		this.event_location = event_location;
		this.event_cost = event_cost;
		this.grade_format = grade_format;
		this.current_grade = current_grade;
		this.reimbursement_amount = reimbursement_amount;
		this.pre_approval = pre_approval;
		this.urgent = urgent;
		this.form_status = form_status;
		this.file_attachment = file_attachment;
		this.supervisor_approval = supervisor_approval;
		this.department_head_approval = department_head_approval;
		this.benefit_co_approval = benefit_co_approval;
		this.approval_response = approval_response;
		this.denial_response = denial_response;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public LocalDate getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(LocalDate submission_date) {
		this.submission_date = submission_date;
	}

	public eventType getEvent_type() {
		return event_type;
	}

	public void setEvent_type(eventType event_type) {
		this.event_type = event_type;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public LocalDate getEvent_date() {
		return event_date;
	}

	public void setEvent_date(LocalDate event_date) {
		this.event_date = event_date;
	}

	public String getEvent_time() {
		return event_time;
	}

	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	public double getTime_missed() {
		return time_missed;
	}

	public void setTime_missed(double time_missed) {
		this.time_missed = time_missed;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public double getEvent_cost() {
		return event_cost;
	}

	public void setEvent_cost(double event_cost) {
		this.event_cost = event_cost;
	}

	public gradeFormat getGrade_format() {
		return grade_format;
	}

	public void setGrade_format(gradeFormat grade_format) {
		this.grade_format = grade_format;
	}

	public double getCurrent_grade() {
		return current_grade;
	}

	public void setCurrent_grade(double current_grade) {
		this.current_grade = current_grade;
	}

	public double getReimbursement_amount() {
		return reimbursement_amount;
	}

	public void setReimbursement_amount(double reimbursement_amount) {
		this.reimbursement_amount = reimbursement_amount;
	}

	public boolean isPre_approval() {
		return pre_approval;
	}

	public void setPre_approval(boolean pre_approval) {
		this.pre_approval = pre_approval;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public formStatus getForm_status() {
		return form_status;
	}

	public void setForm_status(formStatus form_status) {
		this.form_status = form_status;
	}

	public boolean isFile_attachment() {
		return file_attachment;
	}

	public void setFile_attachment(boolean file_attachment) {
		this.file_attachment = file_attachment;
	}

	public boolean isSupervisor_approval() {
		return supervisor_approval;
	}

	public void setSupervisor_approval(boolean supervisor_approval) {
		this.supervisor_approval = supervisor_approval;
	}

	public boolean isDepartment_head_approval() {
		return department_head_approval;
	}

	public void setDepartment_head_approval(boolean department_head_approval) {
		this.department_head_approval = department_head_approval;
	}

	public boolean isBenefit_co_approval() {
		return benefit_co_approval;
	}

	public void setBenefit_co_approval(boolean benefit_co_approval) {
		this.benefit_co_approval = benefit_co_approval;
	}

	public String getApproval_response() {
		return approval_response;
	}

	public void setApproval_response(String approval_response) {
		this.approval_response = approval_response;
	}

	public String getDenial_response() {
		return denial_response;
	}

	public void setDenial_response(String denial_response) {
		this.denial_response = denial_response;
	}

	@Override
	public String toString() {
		return "Form [eid=" + eid + ", event_id=" + event_id + ", submission_date=" + submission_date + ", event_type="
				+ event_type + ", event_name=" + event_name + ", event_description=" + event_description
				+ ", event_date=" + event_date + ", event_time=" + event_time + ", time_missed=" + time_missed
				+ ", event_location=" + event_location + ", event_cost=" + event_cost + ", grade_format=" + grade_format
				+ ", current_grade=" + current_grade + ", reimbursement_amount=" + reimbursement_amount
				+ ", pre_approval=" + pre_approval + ", urgent=" + urgent + ", form_status=" + form_status
				+ ", file_attachment=" + file_attachment + ", supervisor_approval=" + supervisor_approval
				+ ", department_head_approval=" + department_head_approval + ", benefit_co_approval="
				+ benefit_co_approval + ", approval_response=" + approval_response + ", denial_response="
				+ denial_response + "]";
	}
	
}
