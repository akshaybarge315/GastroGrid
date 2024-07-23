function validate(){
	document.getElementById('usernameError').textContent = '';
    document.getElementById('passwordError').textContent = '';
    document.getElementById('userPassError').textContent = '';
        
    var userValidate = document.getElementById("login-username").value
    var passwordValidate = document.getElementById("login-pass").value
    
    // for username 
    if(userValidate==""){
        document.getElementById("usernameError").innerHTML="please enter username"
        return false
    }
    
    //for password 
    if(passwordValidate==""){
        document.getElementById("passwordError").innerHTML="please enter password"
        return false
    }
    
}