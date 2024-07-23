<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Login</title>

        <!-- ===============================================-->
        <!--    Favicons-->
        <!-- ===============================================-->
        <link rel="icon" type="image/png " sizes="32x32" href="assets/images/favicons/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicons/favicon-16x16.png">
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicons/favicon.ico">
        <link rel="manifest" href="assets/images/favicons/manifest.json">
        <meta name="msapplication-TileImage" content="assets/images/favicons/mstile-150x150.png">

        <!-- Stylesheet -->

        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet" />

        <link rel="stylesheet" href="assets/css/login.css" />
    </head>

    <body>
        <div class="login">
            <video id="video1" autoplay muted loop>
                <source src=".//assets/video/video2.mp4" type="video/mp4" />
            </video>
            <form onsubmit="return validate()" action="<%= request.getContextPath() %>/login" method="POST" class="form">
                <h1 class="head">Login</h1>

                <div class="content">
                    <div class="box">
                        <i class="ri-user-3-line login__icon"></i>

                        <div class="box-input">
                            <input type="text" name="username" class="input" id="login-username"
                                placeholder=" " />
                            <label for="login-username" class="login__label">Username <span>*</span></label>
                        </div>
                    </div>
                    <span id="usernameError" class="error"></span>

                    <div class="box">
                        <i class="ri-lock-2-line login__icon"></i>

                        <div class="box-input">
                            <input type="password" name="password" class="input" id="login-pass"
                                placeholder=" " />
                            <label for="login-pass" class="login__label">Password <span>*</span></label>
                            <i class="ri-eye-off-line login__eye" id="login-eye"></i>
                        </div>
                    </div>
                    <span id="passwordError" class="error"></span>
                </div>
                <%
	                String errormsg = request.getParameter("error");
	                if ("true".equals(errormsg)) {
                %>
                    <span id="userPassError" class="error">Invalid Username and password</span>
				<%
                    }
				%>
                <div class="tick">
                    <div class="tick-group">
                        <input type="checkbox" class="tick-input" id="login-check" />
                        <label for="login-check" class="tick-label">Remember me</label>
                    </div>

                    <a href="#" class="forgot">Forgot Password?</a>
                </div>

                <button type="submit" class="button">Login</button>

                <p class="register">Don't have an account? <a href="signUp.jsp">Register</a></p>
            </form>
        </div>

        <script src="assets/js/login.js"></script>
        <script src="assets/js/loginValidation.js"></script>

    </body>

    </html>