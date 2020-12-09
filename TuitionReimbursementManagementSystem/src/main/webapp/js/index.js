window.onload = function() {
	console.log("window");
	login();
}

function togglePassword() {
  var password = document.getElementById("inputPassword");
  if (password.type === "password") {
    password.type = "text";
  } else {
    password.type = "password";
  }
} 

function payload(){
	console.log("in payload fcn");
	var email = document.getElementById("inputEmail").innerHTML;
	togglePassword();
	console.log(password.type);
	password.innerHTML;
	console.log(password);
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
	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/home",true);
	xhr.send(log);
}

