$(document).ready(function () {

    //Validate name
    function validateName(){
        var name = $("#username").val();

        if(name.trim() === ""){
            $(".username_err").text("The username is not null.");
            return false;
        }
        else if(name.length < 2){
            $(".username_err").text("The username must be at least 2 characters long.");
            return false;
        }
        else {
            $(".username_err").text("")
            return true;
        }
    }

    //Validate email
    function validateEmail(){
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

    //Validate rePassword
    function validateRepassword(){
        var password = $("#password").val();
        var rePassword = $("#repassword").val();

        if(rePassword.trim() === ""){
            $(".repassword_err").text("The re-password is not null.");
            return false;
        }
        else if(password != rePassword){
            $(".repassword_err").text("Password do not match.");
            return false;
        }
        else {
            $(".repassword_err").text("");
            return true;
        }
    }

    // Event listeners for input changes
    $("#username").on("input", validateName);
    $("#email").on("input", validateEmail);
    $("#password").on("input", validatePassword);
    $("#repassword").on("input", validateRepassword);

    //Event listener for form submit
    $("#registerForm").submit(function (event){
        //Prevent default form submission
        event.preventDefault();

        // Validate all fields
        var validName = validateName();
        // alert(validName);
        var validEmail = validateEmail();
        var validPassword = validatePassword();
        var validRepassword = validateRepassword();

        // Check if any validation fails
        if (!validName || !validEmail || !validPassword || !validRepassword) {
            return false; // Prevent form submission if validation fails
        }

        //Create data object to be sent to the server
        var formData = {
            userName : $("#username").val(),
            email : $("#email").val(),
            password : $("#password").val(),
            rePassword : $("#repassword").val(),
        };

        //Send an Ajax request to server
        $.ajax({
            url: "/v1/auth/register",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (result) {
                toastr.success("Successfully registered account.", "", {timeout: 2000});
                setTimeout(function () {
                    window.location.href = "/login";
                }, 2000);
            },
            error: function (error) {
                toastr.error("Account registration failed");
            }
        });
    });

});