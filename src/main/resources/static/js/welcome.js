window.onload = promptName()//Run when page loads

function promptName() {
	let name = sessionStorage.getItem("name");//If the session has a name, set it to that
	if (!name) { //If name is null at this point, start prompt
		name = prompt("Enter your name:");
		if (name) { //if name now has a value, set up key value pair "name" , name
			sessionStorage.setItem("name", name); // Store in sessionStorage (per tab)

			fetch("/setUser", { //Call the set user method
				method: "POST",
				headers: { "Content-Type": "application/x-www-form-urlencoded" }, //Formats data to key value pairs
				body: "name=" + name
			}).catch(error => console.error("Error setting user:", error));
		} else {

			window.location.reload(); // If the user cancels, reload and ask again
		}
	} else {
		fetch("/setUser", { //Call the set user method
			method: "POST",
			headers: { "Content-Type": "application/x-www-form-urlencoded" }, //Formats data to key value pairs
			body: "name=" + name
		}).catch(error => console.error("Error setting user:", error));
	}
};

function addChannel() {
	let channelName = "";
	channelName = prompt("Enter channel name: ");
	fetch("/addChannel", {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: "channelName=" + channelName
	}).catch(error => console.error("Error Making Channel:", error));
	window.location.reload();//reload to show the new channel
}

