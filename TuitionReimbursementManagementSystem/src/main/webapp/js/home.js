window.onload = function() {
	console.log("window");
	getCookie();
}

function getCookie(){
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.responseText: "+xhr.responseText);
			let user = JSON.parse(xhr.responseText);
			console.log(user);
			document.getElementById("name").innerHTML=user.name;
			document.getElementById("amtavailable").innerHTML=user.available_reimbursement;
		}
	}

	xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getsession.json", true);
	xhr.send();
}

function getUrgentPending(){
	
}

function getNormalPending(){
	
}

function getClosed(){
	
}