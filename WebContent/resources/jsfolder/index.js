function clicksubmit () {
	// Get value in inputtext and write in textarea1
	let d = new Date();
	let inputVal = document.getElementById("input-text").value;
	let username = document.getElementById("tb-info").rows[0].cells.namedItem("td-username").innerHTML;

	document.getElementById("textarea").append("["+d.getDate()+"/"
		+d.getMonth()+"||"+d.getHours()+":"+d.getMinutes()+"] " + username +" : ");
	document.getElementById("textarea").append(inputVal);
	document.getElementById("textarea").append("\n");

	document.getElementById("input-text").value = '';
}

function keysubmit(evt) {
	if(evt.keyCode === 13){
		event.preventDefault();
   		document.getElementById("submit-text").click();
	}
}
