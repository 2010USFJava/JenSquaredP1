/**
 * TODO: end session - happens automatically?
 * 		 forward to login
 * 		 delete cookie
 */

//adds security to finalize logout
function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};