
// This is for registration

document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("registerForm").addEventListener("submit", async function (event) {

        event.preventDefault(); // Prevent default form submission

        console.log("Form submission triggered");

        alert("Submitting form...");

        // Get form values
        const name = document.getElementById("name").value;
        const age = document.getElementById("age").value;
        const address = document.getElementById("address").value;
        const gender = document.getElementById("gender").value;
        const mobileNo = document.getElementById("mobile").value;
        const emailId = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        console.log("Collected Data:", { name, age, address, gender, mobileNo, emailId });

        

        // Prepare request body
        const requestBody = {
            name,
            age: parseInt(age, 10), // Ensure age is a number
            address,
            gender,
            mobileNo,
            emailId,
            password,
         
        };

        try {
            console.log("Sending data to server...");
            const response = await fetch("http://localhost:8099/user/addNew", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestBody)
                
            });
            console.log(JSON.stringify(requestBody))

          ;
            alert("User registered successfully!");
            window.location.href = "login.html";
            console.log("Response:", responseData);
            document.getElementById("registerForm").reset(); 
        } catch (error) {
            
        }

    });

});






