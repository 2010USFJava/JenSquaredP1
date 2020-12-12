window.onload = function() {
	console.log("window");
	getUser();
}

function getUser(){
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

function generateTableHead(table,data){
	let thead=table.createTHead();
	let row = thead.insertRow();
	for (let key of data){
		let th = document.createElement("th");
		let text = document.createTextNode(key);
		th.appendChild(text);
		row.appendChild(th);
	}
}

function generateTable(table, data){
	for(let element of data){
		let row = table.insertRow();
		for(key in element){
			let cell = row.insertCell();
			let text = document.createTextNode(element[key]);
			cell.appendChild(text);
		}
	}
}

function getUrgentPending(){
	let xhrurg = new XMLHttpRequest();
	xhrurg.onreadystatechange = function() {
		console.log("in ORSC urgent");
		if (xhrurg.readyState == 4 && xhr.status == 200) {
			console.log("xhrurg.responseText: "+xhrurg.responseText);
			let urgList = JSON.parse(xhr.responseText);
			console.log(urgList);
			let urgtable = document.getElementById("urgentTable");
			generateTable(urgtable,urglist);
			generateTableHead(urgtable,urglist);
		}
	}

	xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/geturglist.json", true);
	xhr.send();
}

function getNormalPending(){
	
}

function getClosed(){
	
}