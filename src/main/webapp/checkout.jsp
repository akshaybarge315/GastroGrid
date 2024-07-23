<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.food.model.User" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">

            <title>Checkout Page</title>

            <!--    Favicons-->
            <link rel="icon" type="image/png " sizes="32x32" href="assets/images/favicons/favicon-32x32.png">
            <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicons/favicon-16x16.png">
            <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicons/favicon.ico">
            <link rel="manifest" href="assets/images/favicons/manifest.json">
            <meta name="msapplication-TileImage" content="assets/images/favicons/mstile-150x150.png">

            <!-- Font Awesome -->
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">


            <!-- Bootstrap Scripts -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
                integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
                crossorigin="anonymous">

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
                integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
                crossorigin="anonymous"></script>

            <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" type="text/css"
                rel="stylesheet">


            <!--    Stylesheets-->
            <link
                href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200;300;400;600;700;900&amp;display=swap"
                rel="stylesheet">

            <link href="assets/css/checkout.css" rel="stylesheet">
            <link href="assets/css/theme.css" rel="stylesheet" />

        </head>

        <body>

            <main class="main" id="top">
                <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top"
                    data-navbar-on-scroll="data-navbar-on-scroll">
                    <div class="container"><a class="navbar-brand d-inline-flex" href="#"><img class="d-inline-block"
                                src="assets/images/gallery/logo.svg" alt="logo" /><span
                                class="text-1000 fs-3 fw-bold ms-2 text-gradient">GastroGrid</span></a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon">
                            </span></button>
                        <form class="d-flex mt-4 mt-lg-0 ms-lg-auto ms-xl-0">
                            <a href="#"><button class="btn btn-white text-warning" type="button"><i
                                        class="fa-solid fa-house me-2"></i>Home</button></a>
                            <a href="cart.jsp"><button class="btn btn-white text-warning" type="button"><i
                                        class="fa-solid fa-cart-shopping me-2"></i>Cart</button></a>
                            <% 
                            	if (session !=null) { 
                            		
                            		User user=(User) session.getAttribute("user"); 
                            		if(user !=null){ 
                            %>
                                <div class="dropdown">
                                    <button class="btn btn-white text-warning text-uppercase" type="button"
                                        data-toggle="dropdown">
                                        <i class="fas fa-user me-2"></i>
                                        <%= user.getUsername() %>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Edit Profile</a></li>
                                        <li><a class="dropdown-item" href="#">View Orders</a></li>
                                        <li><a class="dropdown-item"
                                                href="<%= request.getContextPath() %>/logout">Logout</a></li>
                                    </ul>
                                </div>
                                <% 
                                	}else{ 
                                %>
                                    <a href="login.jsp"><button class="btn btn-white text-warning" type="button"> <i
                                                class="fas fa-user me-2"></i>Login</button></a>
                               <% 	} 
                            	} else { 
                            	%>
                                        <a href="login.jsp"><button class="btn btn-white text-warning" type="button"> <i
                                                    class="fas fa-user me-2"></i>Login</button></a>
                                <% } %>
                        </form>
                    </div>
                </nav>
            </main>

            <!-- CheckOut Section -->
            <section>
                <div class="container">
                    <div class="checkout-container">
                        <h1>Checkout</h1>
                        <form action="checkout" method="post">
                            <div class="form-group">
                                <label for="address">Delivery Address:</label>
                                <textarea id="address" name="address" rows="4" cols="37" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="paymode">Payment Mode:</label>
                                <select id="paymode" name="paymentMode" required>
                                    <option value="UPI">UPI</option>
                                    <option value="Cash">Cash</option>
                                    <option value="Credit Card">Credit Card</option>
                                    <option value="Net Banking">Net Banking</option>
                                </select>
                            </div>
                            <div class="button-group">
                                <a href="cart.jsp"><button type="button" class="btnn btn-sm back-btn">Back to
                                        Cart</button></a>
                                <input type="submit" value="Place Order" class="btnn btn-sm confirm-btn">
                            </div>
                        </form>
                    </div>
                </div>
            </section>



        </body>

        </html>