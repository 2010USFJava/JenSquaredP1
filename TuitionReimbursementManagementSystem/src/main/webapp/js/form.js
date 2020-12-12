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
	/*var preapproval = null;
	var preapprovalyes = document.getElementById("preapprovalyes").innerHTML;
	var preapprovalno = document.getElementById("preapprovalno").innerHTML;
	if(preapprovalyes="Yes"){preapproval=true;}else if(preapprovalno="No"){preapproval=false;}else{preapproval=false;}
	var attachedfile = document.getElementById("attachedfile").innerHTML;	
	if(attachedfile != null){
		var filebool = true;
	}*/
	
	var obj = {todaysdate,eventtype,eventname,eventdescription,eventdate,eventtime,timemissed,eventlocation,
	reimbursementcost,gradeformat,passinggrade,reimbursementamount/*,preapproval,filebool*/};
	var sendData = json.stringify(obj);	
	console.log(sendData);
	return sendData;
}

function payload2(){
	
}

function sendForm() {
	
	var formElements = document.querySelector("form");
	console.log(formElements);
	
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let form = JSON.parse(xhr.responseText);
			console.log(form);
		}
	}

	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagemenetSystem/form.json", true);
	
	xhr.send(new FormData(formElements));
}

function sendAttachment(eventId, file){
	let xhr2=new XMLHttpRequest();
	xhr2.onreadystatechange-function(){
		console.log("in ORSC 2");
		if(xhr2.readyState==4&&xhr2.status==200){
			console.log(xhr2.responseText);
			let attachment = JSON.parse(xhr2.responseText);
			console.log(attachment);
			payload2();
		}
	}
	xhr2.open("POST","http://localhost:8080/TuitionReimbursementManagemenetSystem/attachments.json", true);
	xhr2.send(sendFile);
}

window.onload = function() {
	console.log("in onload");
	document.getElementById("formsubmit").addEventListener("click", sendForm, false);
}