window.onload = function() {
	console.log("window");
	getUser();
	getUrgentPending();
	getNormalPending();
	getClosed();
}

function getUser() {
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("xhr.responseText: " + xhr.responseText);
			let user = JSON.parse(xhr.responseText);
			console.log(user);
			document.getElementById("name").innerHTML = user.name;
			document.getElementById("amtavailable").innerHTML = user.available_reimbursement;
		}
	}

	xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getsession.json", true);
	xhr.send();
}

function generateTableHead(table, data) {
	let thead = table.createTHead();
	let row = thead.insertRow();
	for (let key of data) {
		let th = document.createElement("th");
		let text = document.createTextNode(key);
		th.appendChild(text);
		row.appendChild(th);
	}
}

function generateTable(table, data) {
	for (let element of data) {
		let row = table.insertRow();
		for (key in element) {
			let cell = row.insertCell();
			let text = document.createTextNode(element[key]);
			cell.appendChild(text);
		}
	}
}

function getUrgentPending() {
	let xhrurg = new XMLHttpRequest();
	xhrurg.onreadystatechange = function() {
		console.log("in ORSC urgent");
		if (xhrurg.readyState == 4 && xhrurg.status == 200) {
			try {
				console.log("xhrurg.responseText: " + xhrurg.responseText);
				let urgList = JSON.parse(xhrurg.responseText);
				console.log(urgList);
				let urgtable = document.getElementById("urgentTable");
				generateTable(urgtable, urglist);
				generateTableHead(urgtable, urglist);
			} catch (e) {
				if (e instanceof SyntaxError) {
					document.getElementById("urgentTable").innerHTML = "No Urgent Pending Forms";
				}
			}
		}
	}

	xhrurg.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/geturglist.json", true);
	xhrurg.send();
}

function getNormalPending() {
	let xhrnu = new XMLHttpRequest();
	xhrnu.onreadystatechange = function() {
		console.log("in ORSC non urgent");
		if (xhrnu.readyState == 4 && xhrnu.status == 200) {
			try {
				console.log("xhrnu.responseText: " + xhrnu.responseText);
				let nuList = JSON.parse(xhrnu.responseText);
				console.log(nuList);
				let nutable = document.getElementById("nonurgentTable");
				generateTable(nutable, nuList);
				generateTableHead(nutable, nuList);
			} catch (e) {
				if (e instanceof SyntaxError) {
					document.getElementById("nonurgentTable").innerHTML = "No Non-Urgent Pending Forms";
				}
			}

		}
	}

	xhrnu.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getnonurglist.json", true);
	xhrnu.send();
}

function getClosed() {
	let xhrc = new XMLHttpRequest();
	xhrc.onreadystatechange = function() {
		console.log("in ORSC closed");
		if (xhrc.readyState == 4 && xhrc.status == 200) {
			try {
				console.log("xhrc.responseText: " + xhrc.responseText);
				let cList = JSON.parse(xhrc.responseText);
				console.log(cList);
				let ctable = document.getElementById("closedTable");
				generateTable(ctable, cList);
				generateTableHead(ctable, cList);
			} catch (e) {
				if (e instanceof SyntaxError) {
					document.getElementById("closedTable").innerHTML = "No Closed Forms";
				}
			}

		}
	}

	xhrc.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getclosedlist.json", true);
	xhrc.send();
}