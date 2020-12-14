window.onload = function() {
	console.log("approveordeny page");
	getForm(fid);
	document.getElementById("approvebutton").addEventListener("click", approved(fid), true);
	document.getElementById("denybutton").addEventListener("click", denied(fid), true);
}

var fid = localstorage.getItem("option");

function getForm(fid) {
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log("in ORSC");
		if (xhr.readyState == 4 && xhr.status == 200) {
			try {
				console.log("xhr.responseText: " + xhr.responseText);
				let form = JSON.parse(xhr.responseText);
				console.log(form);
				if (form.length > 0) {
					let formtable = document.getElementById("formshow");
					generateTable(formtable, form);
					let data = Object.keys(form[0]);
					generateTableHead(formtable, data);
				} else { document.getElementById("formshow").innerHTML = "Cannot Display Application" }
			} catch (e) {
				if (e instanceof SyntaxError) {
					document.getElementById("formshow").innerHTML = "Cannot Display Application";
				}
			}

			xhr.open("GET", "http://localhost:8080/TuitionReimbursementManagementSystem/getform.json", true);
			xhr.send();
		}

		function approved(fid) {
			let xhrapp = new XMLHttpRequest();

			xhrapp.onreadystatechange = function() {
				console.log("in ORSC");
				if (xhrapp.readyState == 4 && xhrapp.status == 200) {
					console.log("xhrapp.responseText: " + xhrapp.responseText);
					let app = JSON.parse(xhrapp.responseText);
					console.log(app);
				}
			}

			xhrapp.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/approved.json", true);
			xhrapp.send(fid);
		}

		function denied(fid) {
			let xhrden = new XMLHttpRequest();

			xhrden.onreadystatechange = function() {
				console.log("in ORSC");
				if (xhrden.readyState == 4 && xhrden.status == 200) {
					console.log("xhrden.responseText: " + xhrden.responseText);
					let den = JSON.parse(xhrden.responseText);
					console.log(den);
				}
			}

			xhxhrdenr.open("POST", "http://localhost:8080/TuitionReimbursementManagementSystem/approved.json", true);
			xhrden.send(fid);
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
	}
}