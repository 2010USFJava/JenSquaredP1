document.getElementById('eventdescription').style.height = "100px";
document.getElementById('eventdescription').style.width = "400px";

var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; // January is 0
var yyyy = today.getFullYear();

//calculate submission date restriction

function getUser() {
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.responseText: " + xhr.responseText);
			let user = JSON.parse(xhr.responseText);
			console.log(user);
			document.getElementById("empname").innerHTML = user.name;
		}
	}

	xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getsession.json", true);
	xhr.send();
}

function payload(){
	document.getElementById("reimbursementcost").value = parseFloat(
		document.getElementById("reimbursementcost").value).toFixed(2);
	var amount = document.getElementById("reimbursementcost").value;

	var event = document.getElementById("eventtype").value;

	document.getElementById('reimbursementcost').addEventListener('change', alert);
	document.getElementById('eventtype').addEventListener('change', alert);
	document.getElementById("todaysdate").value = today;

	var todaysdate = document.getElementById("todaysdate").innerHTML;	
	var eventtype = document.getElementById("eventtype").innerHTML;
	var eventname = document.getElementById("eventname").innerHTML;	
	var eventdescription = document.getElementById("eventdescription").innerHTML;	
	var eventdate = document.getElementById("eventdate").innerHTML;
	var eventtime = document.getElementById("time").innerHTML;	
	var timemissed = document.getElementById("timemissed").innerHTML;
	var eventlocation = document.getElementById("eventlocation").innerHTML;
	var gradeformat = document.getElementById("gradeformat").innerHTML;		
	var passinggrade = document.getElementById("passinggrade").innerHTML;
	var preapproval = document.getElementById("preapproval").innerHTML;	
	var attachedfile = document.getElementById("attachedfile").innerHTML;	
	
	var obj = {todaysdate,eventtype,eventname,eventdescription,eventdate,eventtime,timemissed,eventlocation,
	reimbursementcost,gradeformat,passinggrade,preapproval,attachedfile};
	var sendData = json.stringify(obj);	
}

function sendForm() {

	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let form = JSON.parse(xhr.responseText);
			console.log(form);
			payload();
		}
	}

	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagemenetSystem/form.master", true);
	
	xhr.send(sendData);
}

window.onload = function() {
	console.log("in onload");
	getUser();
	document.getElementById("formsubmit").addEventListener("click", sendForm, false);
}