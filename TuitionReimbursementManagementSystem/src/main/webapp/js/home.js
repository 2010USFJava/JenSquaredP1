window.onload = function() {
	console.log("window");
	this.document.getElementById("home").addEventListener("click",goHome(),false);
	this.document.getElementById("faq").addEventListener("click",goFAQs(),false);
	this.document.getElementById("form").addEventListener("click",goApp(),false);
	this.document.getElementById("logout").addEventListener("click",goLogout(),false);
}

function goHome() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhr.readyState == 4 && xhr.status == 200) {
			let e = JSON.parse(xhttp.responseText);
			console.log(e);
		}
	}
	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/home.master",true);
	xhr.send();
}

function goFAQs() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhr.readyState == 4 && xhr.status == 200) {
			let e = JSON.parse(xhttp.responseText);
			console.log(e);
		}
	}
	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/faq.master",true);
	xhr.send();
}

function goApp() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhr.readyState == 4 && xhr.status == 200) {
			let e = JSON.parse(xhttp.responseText);
			console.log(e);
		}
	}
	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/form.master",true);
	xhr.send();
}

function goLogout() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhr.readyState == 4 && xhr.status == 200) {
			let e = JSON.parse(xhttp.responseText);
			console.log(e);
		}
	}
	xhr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/logout.master",true);
	xhr.send();
}