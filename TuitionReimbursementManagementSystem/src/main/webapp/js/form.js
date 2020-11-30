document.getElementById('eventdescription').style.height="100px";
document.getElementById('eventdescription').style.width="400px";

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