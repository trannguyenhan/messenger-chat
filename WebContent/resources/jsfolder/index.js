function refreshMessages(message){
	let username = document.getElementById("tb-info").rows[0].cells.namedItem("td-username").innerHTML;

	document.getElementById("textarea").append("["+d.getDate()+"/"
		+d.getMonth()+"||"+d.getHours()+":"+d.getMinutes()+"] " + username +" : ");
	document.getElementById("textarea").append(message);
	document.getElementById("textarea").append("\n");

	document.getElementById("input-text").value = '';
}

function clicksubmit () {
	// Get value in inputtext and write in textarea1
	let d = new Date();
	let inputVal = document.getElementById("input-text").value;
	let username = document.getElementById("tb-info").rows[0].cells.namedItem("td-username").innerHTML;

	refreshMessages(inputVal);
}

function keysubmit(evt) {
	if(evt.keyCode === 13){
		event.preventDefault();
   		document.getElementById("submit-text").click();
	}
}

/*connect web socket*/
var stompClient = null; 
function connect() {
    var socket = new SockJS('/newMessage');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/newMessage', function(message){
            refreshMessages(JSON.parse(JSON.parse(message.body).content));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}
