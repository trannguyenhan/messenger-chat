function clicksubmit () {
	// Get value in inputtext and write in textarea1
	var d = new Date();
	var inputVal = document.getElementById("input-text").value;
	document.getElementById("textarea").append("["+d.getDate()+"/"+d.getMonth()+"||"+d.getHours()+":"+d.getMinutes()+"] Admin : ");
	document.getElementById("textarea").append(inputVal);
	document.getElementById("textarea").append("\n");

	document.getElementById("input-text").value = '';
}

function keysubmit(evt) {
	if(evt.keyCode === 13){
		clicksubmit();
	} 
}