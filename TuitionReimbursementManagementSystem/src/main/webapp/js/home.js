window.onload = function() {
	console.log("window");
	getCookie();
}

function getCookie(){
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			let user = JSON.parse(xhr.responseText);
			user.name=document.getElementById("name").innerHTML;
		}
	}

	xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getsession.master", true);
	//console.log(ck);
	xhr.send();
}

function getUrgentPending(){
	
}

function getNormalPending(){
	
}

function getClosed(){
	
}