function validateuser() {
	event.preventDefault();
	console.log("hi")
	const name = document.getElementById("name").value;
	const email = document.getElementById("email").value;
	const requestdata = { name, email }
	fetch("http://localhost:8080/billing/createcustomer", {
		method: "POST",
		headers: {
			"content-type": "application/Json"
		},
		body: JSON.stringify(requestdata)
	})
		.then(response => response.json())
		.then(data => {
			if (data.valid) {
				console.log("i am created")
				console.log("ok done")
				if (confirm("Your account  is Created")) {

					window.location.href = "login.html";
				} 

			}

		})

}
function bill() {
	event.preventDefault();
	console.log("cam to bill")
	const id = document.getElementById("id").value;
	const requestdata = { id: Number(id) };
	fetch(`http://localhost:8080/billing/createBill/${id}`, {
		method: "POST",
		headers: {
			"Content-type": "application/Json"
		},
		body: JSON.stringify(requestdata)
	})
		.then(response => response.json())
		.then(data => {
			if (data.valid) {
				console.log("ok valid bill")
				window.location.href = "transaction.html";
			}
		})
}
function transaction() {
	event.preventDefault();
	const id = document.getElementById("id").value;
	const amount = document.getElementById("amount").value;
	const type = document.getElementById("type").value;
	fetch(`http://localhost:8080/billing/transaction?accountid=${Number(id)}&amount=${Number(amount)}&type=${encodeURIComponent(type)}`, {
		method: "POST",
		headers: {
			"content-type": "application/Json"
		},

	})
		.then(response => response.json())
		.then(data => {
			if(data.valid)
				{
			window.location.href = "balance.html";
			}
		})

}
function balance() {
	event.preventDefault();
	console.log("cam to balance")
	const id = document.getElementById("id").value;
	fetch(`http://localhost:8080/billing/balance/${id}`, {
		method: "GET",

	})
		.then(response => response.json())
		.then(data => {
			document.getElementById("bal").style.display = "block"
			if (data == 0) {

				document.getElementById("bal").value = "Account Not Found";
			}
			else {
				document.getElementById("bal").value = data;
			}
			console.log(data);

		})
}

function pay()
{
	event.preventDefault()
	const accountid = document.getElementById("accountid").value.trim();
	        
	        if (!accountid) {
	            alert("Please enter an account ID");
	            return;
	        }

	        fetch(`http://localhost:8080/billing/payslips?account_id=${Number(accountid)}`, { 
	            method: "GET",
	            headers: {
	                "Content-Type": "application/json"
	            }
	        })
	        .then(response => {
	            if (!response.ok) {
	                throw new Error(`HTTP error! Status: ${response.status}`);
	            }
	            return response.json();
	        })
			.then(data => {
			            console.log("Received Data:", data); // Debugging

			            // Select the table body
			            let tableBody = document.getElementById("tableBody");

			            document.getElementById("payslipTable").style.display="block";
			            tableBody.innerHTML = "";

			            // Loop through data and add rows
			            data.forEach(item => {
			                let row = `<tr>
			                    
			                    <td>${item.account_id}</td>
			                    <td>${item.amount}</td>
			                    <td>${item.type}</td>
								<td>${item.dateTime}</td>
			                </tr>`;
			                tableBody.innerHTML += row;
			            });
			        })

	        .catch(error => console.error("Error fetching payslips:", error));
	    
}



function login(event) {
    event.preventDefault(); // Prevent page refresh
    console.log("Login function triggered"); // Debugging

    const name = document.getElementById("names");
    const email = document.getElementById("emails");

    // Ensure input elements exist
    if (!name || !email) {
        console.error("Input fields not found!");
        return;
    }

    const requestData = { name: name.value, email: email.value };
    console.log("Request Data:", requestData); // Debugging

    fetch("http://localhost:8080/billing/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestData)
    })
    .then(response => {
        console.log("Response received:", response);
        return response.json();
    })

	.then(data => {
	    console.log("Parsed Data:", data);

	    if (data) {
			console.log("login is done")
	        const user = data[0]; // Get the first object from the array
			document.getElementById("acc").style.display="block";
			document.getElementById("login").style.display="none";
			document.getElementById("hello").innerHTML="Hello "+data.name
			document.getElementById("accno").innerHTML=data.accountNo
		
	        }
			
	})
}

function Exit()
{
	event.preventDefault();
	window.location.href="home.html"
}
function customernav()
{
	event.preventDefault();
	window.location.href="customer.html"
}
function transactionnav()
{
	event.preventDefault();
	window.location.href="transaction.html"
}
function balancenav()
{
	event.preventDefault();
	window.location.href="balance.html"
}
function payslipsnav()
{
	event.preventDefault();
	window.location.href="payslips.html"
}

function loginnav()
{
	event.preventDefault();
	window.location.href="login.html"
}