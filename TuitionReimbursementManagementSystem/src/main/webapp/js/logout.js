/**
 * TODO: end session
 * 		 forward to login
 */

//adds security to finalize logout
function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};