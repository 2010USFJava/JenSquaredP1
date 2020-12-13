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
				if (urgList.length > 0) {
					let urgtable = document.getElementById("urgentTable");
					generateTable(urgtable, urgList);
					let data = Object.keys(urgList[0]);
					generateTableHead(urgtable, data);
					displayOptions(urgList, '#urgentOption');
				} else {
					document.getElementById("urgentTable").innerHTML = "No Urgent Pending Forms";
				}
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
				if (nuList.length > 0) {
					let nutable = document.getElementById("nonurgentTable");
					generateTable(nutable, nuList);
					let data = Object.keys(nuList[0]);
					generateTableHead(nutable, data);
					displayOptions(nuList, "#nonurgentOption");
				} else {
					document.getElementById("nonurgentTable").innerHTML = "No Non-Urgent Pending Forms";
				}
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
				if (cList.length > 0) {
					let ctable = document.getElementById("closedTable");
					generateTable(ctable, cList);
					let data = Object.keys(cList[0]);
					generateTableHead(ctable, data);
					displayOptions(cList, '#closedOption');
				} else {
					document.getElementById("closedTable").innerHTML = "No Closed Forms";
				}
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

function displayOptions(reqList, eleid) {  //pass in already parsed json
	console.log("I was called!");
	var obj = reqList;//this is kind of useless but obj is shorter lol
	console.log(obj); //print to  check!
	$.each(obj, function(key, value) { //for each object in reqList:(index, json )
		console.log(key);
		console.log(value.requestId); //json.parameter, will pass in the value associated with the parameter
		optionText = value.requestId; //stores the seen text in option
		optionValue = value.requestId; //stores a value that is passed in when this option is chosen
		$(`${eleid}`).append(`<option value="${optionValue}">${optionText}</option>`);
		//^^^
	});
}

function sendOption(optionValue) {
	if (typeof (Storage) !== "undefined") {
		localStorage.setItem("formOption", optionValue);
	}
}