function fetchMessages() {
	fetch(window.location.pathname + "/messages") //Get request this path
		.then(response => response.json())//convert the response from the server into JSON
		.then(messages => {
			const messagesBox = document.getElementById("messages");//Find HTML element with the ID:messages
			messagesBox.innerHTML = messages.map(msg => `<div style='margin 2px 0;'>${msg}</div>`).join("");//combine the messages into a div in this format
		})
		.catch(error => console.error('Error fetching messages:', error));
}

function sendMessage() {
	const messageInput = document.getElementById("messageInput");//Find element labeled messageInput
	const message = messageInput.value; //find the value of that input, that is the message.
	let name = sessionStorage.getItem("name");//find the name from session storage to add to the message

	if (!message.trim()) return; // Prevent sending empty messages

	fetch(window.location.pathname + "/send", { //Send this data to the POST mapping send
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: "name=" + name + "&message=" + message
	})
		.then(() => {//then
			messageInput.value = "";//Clear the text area & Send the message
			fetchMessages();//re-populate the message box
		})
		.catch(error => console.error('Error sending message:', error));
}

function handleKeyPress(event) {
	if (event.key === 'Enter' && !event.shiftKey) {
		event.preventDefault();
		sendMessage();
	}//Trying to get mine to function like his. Needed to make it so enter sends the message
}

setInterval(fetchMessages, 500);//refresh the message box every 500 ms