$(document).ready(function () {

    //Validate email
    function validateEmail(){
        debugger;
        var email = $("#email").val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if(email.trim() === ""){
            $(".email_err").text("The email is not null.");
            return false;
        }
        else if(!emailRegex.test(email)){
            $(".email_err").text("Email is not valid");
            return false;
        }
        else {
            $(".email_err").text("");
            return true;
        }
    }

    //Validate password
    function validatePassword(){
        debugger;
        var password = $("#password").val();
        var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        if(password.trim() === ""){
            $(".password_err").text("The password is not null.");
            return false;
        }
        else if(password.length < 8){
            $(".password_err").text("Minimum length is 8.");
            return false;
        }
        else if(!passwordRegex.test(password)){
            $(".password_err").text("Password must contain at least 1 lowercase letter, 1 uppercase letter, 1 digit, 1 special character.")
            return false;
        }
        else {
            $(".password_err").text("");
            return true;
        }
    }

    // Event listeners for input changes
    $("#email").on("input", validateEmail);
    $("#password").on("input", validatePassword);

    //Event listener for form submit
    $("#loginForm").submit(function (event){
        debugger;
        //Prevent default form submission
        event.preventDefault();

        // Validate all fields
        var validEmail = validateEmail();
        var validPassword = validatePassword();

        // Check if any validation fails
        if (!validEmail || !validPassword) {
            return false; // Prevent form submission if validation fails
        }

        //Create data object to be sent to the server
        var formData = {
            email : $("#email").val(),
            password : $("#password").val(),
        };

        //Send an Ajax request to server
        $.ajax({
            url: "/v1/auth/login",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (result) {
                toastr.success("Logged in successfully.", "", {timeout: 2000});
                setTimeout(function () {
                    window.location.href = "/";
                }, 2000);
            },
            error: function (error) {
                toastr.error("Login failed.");
            }
        });
    });

});