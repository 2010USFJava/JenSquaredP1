window.onload = function() {
	console.log("window");
	getCookie();
}

function loadName(){
	document.getElementById("name").innerHTML=user.name;
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
		}
	}

	xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getsession.json", true);
	//console.log(ck);
	xhr.send();
}

function getUrgentPending(){
	
}

function getNormalPending(){
	
}

function getClosed(){
	
}