window.onload = function() {
	console.log("window");
	login();
}

function payload(){
	var email = document.getElementById("inputEmail").innerHTML;
	var password = document.getElementById("inputPassword").innerHTML;
	var obj = {email,password};
	var log = json.stringify(obj);
}


function login() {

	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhr.readyState == 4 && xhr.status == 200) {
			let e = JSON.parse(xhttp.responseText);
			console.log(e);
			payload();
		}
	}
	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/login.master",true);
	xhr.send(log);
}