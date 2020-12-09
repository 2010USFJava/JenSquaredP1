document.getElementById('eventdescription').style.height = "100px";
document.getElementById('eventdescription').style.width = "400px";

var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; // January is 0
var yyyy = today.getFullYear();

//calculate submission date restriction

function alert() {
	document.getElementById("reimbursementcost").value = parseFloat(
		document.getElementById("reimbursementcost").value).toFixed(2);
	var amount = document.getElementById("reimbursementcost").value;

	var event = document.getElementById("eventtype").value;

	document.getElementById('reimbursementcost').addEventListener('change', alert);
	document.getElementById('eventtype').addEventListener('change', alert);
	document.getElementById("todaysdate").value = today;

	switch (event) {
		case "200":
			document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .80)).toFixed(2);
			document.getElementById("estReimbAmt").value = parseFloat(
				(amount * .80)).toFixed(2);
			;
			break;
		case "201":
			document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .60)).toFixed(2);
			document.getElementById("estReimbAmt").value = parseFloat(
				(amount * .60)).toFixed(2);
			break;
		case "202":
			document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .75)).toFixed(2);
			document.getElementById("estReimbAmt").value = parseFloat(
				(amount * .75)).toFixed(2);
			break;
		case "203":
			document.getElementById("reimbursementamount").value = parseFloat(
				amount).toFixed(2);
			document.getElementById("estReimbAmt").value = parseFloat(amount)
				.toFixed(2);
			break;
		case "204":
			document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .90)).toFixed(2);
			document.getElementById("estReimbAmt").value = parseFloat(
				(amount * .90)).toFixed(2);
			break;
		case "205":
			document.getElementById("reimbursementamount").value = parseFloat(
				(amount * .30)).toFixed(2);
			document.getElementById("estReimbAmt").value = parseFloat(
				(amount * .30)).toFixed(2);
			break;
		default:
			console.log("Error caught calculating reimbursement amount in form.js");
	}

}

function payload(){
	var todaysdate = document.getElementById("todaysdate").innerHTML;	
	var eventtype = document.getElementById("eventtype").innerHTML;
	var eventname = document.getElementById("eventname").innerHTML;	
	var eventdescription = document.getElementById("eventdescription").innerHTML;	
	var eventdate = document.getElementById("eventdate").innerHTML;
	var eventtime = document.getElementById("time").innerHTML;	
	var timemissed = document.getElementById("timemissed").innerHTML;
	var eventlocation = document.getElementById("eventlocation").innerHTML;
	var reimbursementcost = document.getElementById("reimbursementcost").innerHTML;	
	var gradeformat = document.getElementById("gradeformat").innerHTML;		
	var passinggrade = document.getElementById("passinggrade").innerHTML;
	var reimbursementamount = document.getElementById("reimbursementamount").innerHTML;	
	var preapproval = document.getElementById("preapproval").innerHTML;	
	var attachedfile = document.getElementById("attachedfile").innerHTML;	
	
	var obj = {todaysdate,eventtype,eventname,eventdescription,eventdate,eventtime,timemissed,eventlocation,
	reimbursementcost,gradeformat,passinggrade,reimbursementamount,preapproval,attachedfile};
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
	document.getElementById("formsubmit").addEventListener("click", getForm, false);
}