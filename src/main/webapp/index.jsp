<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.food.model.Restaurant" %>
        <%@ page import="com.food.model.Menu" %>
        <%@ page import="com.food.model.User" %>
            <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="utf-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1">

                    <title>GastroGrid</title>

                    <!--    Favicons-->
                    <link rel="icon" type="image/png " sizes="32x32" href="assets/images/favicons/favicon-32x32.png">
                    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicons/favicon-16x16.png">
                    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicons/favicon.ico">
                    <link rel="manifest" href="assets/images/favicons/manifest.json">
                    <meta name="msapplication-TileImage" content="assets/images/favicons/mstile-150x150.png">

                    <!-- Font Awesome -->
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
                    
                    <!-- Bootstrap Scripts -->
					  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
					
					  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
					  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
					  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
                    

                    <!--    Stylesheets-->
                    <link
                        href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200;300;400;600;700;900&amp;display=swap"
                        rel="stylesheet">

                    <link href="assets/css/theme.css" rel="stylesheet" />
                    <link href="assets/css/menu.css" rel="stylesheet" />

                </head>


                <body>

                    <!-- ===============================================-->
                    <!--    Main Content-->
                    <!-- ===============================================-->
                    <main class="main" id="top">
                        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top"
                            data-navbar-on-scroll="data-navbar-on-scroll">
                            <div class="container"><a class="navbar-brand d-inline-flex"
                                    href="<%= request.getContextPath() %>/index"><img class="d-inline-block"
                                        src="assets/images/gallery/logo.svg" alt="logo" /><span
                                        class="text-1000 fs-3 fw-bold ms-2 text-gradient">GastroGrid</span></a>
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                    aria-expanded="false" aria-label="Toggle navigation"><span
                                        class="navbar-toggler-icon">
                                    </span></button>
                                <form class="d-flex mt-4 mt-lg-0 ms-lg-auto ms-xl-0">
                                    <div class="input-group-icon pe-2"><i
                                            class="fas fa-search input-box-icon text-primary"></i>
                                        <input class="form-control border-0 input-box bg-100" type="search"
                                            placeholder="Search Food" aria-label="Search" />
                                    </div>
                                    <a href="<%= request.getContextPath() %>/index"><button class="btn btn-white text-warning"
		                                            type="button"><i class="fa-solid fa-house me-2"></i>Home</button></a>
                                    <a href="cart.jsp"><button class="btn btn-white text-warning"
		                                            type="button"><i class="fa-solid fa-cart-shopping me-2"></i>Cart</button></a>
                                    <%	
								        if (session != null) {
								            User user = (User) session.getAttribute("user");
								            if(user != null){
								   	%>
								   		<div class="dropdown">
								   			<button class="btn btn-white text-warning text-uppercase"
                                            type="button" data-toggle="dropdown"> 
                                            <i class="fas fa-user me-2"></i> <%= user.getUsername() %> </button>
                                            <ul class="dropdown-menu">
											   <li><a class="dropdown-item" href="#">Edit Profile</a></li>
											   <li><a class="dropdown-item" href="#">View Orders</a></li>
											   <li><a class="dropdown-item" href="<%= request.getContextPath() %>/logout">Logout</a></li>
											</ul>
										</div>
                                   	<%	 	
                                   		}else{
                                   	%>
                                   		<a href="login.jsp"><button class="btn btn-white text-warning"
		                                            type="button"> <i class="fas fa-user me-2"></i>Login</button></a>
                                   	<%
                                   	}
								        } else {
								     %>
		                                    <a href="login.jsp"><button class="btn btn-white text-warning"
		                                            type="button"> <i class="fas fa-user me-2"></i>Login</button></a>
								    <%
								        }
								    %>
                                </form>
                            </div>
                        </nav>
                        <section class="py-5 overflow-hidden bg-primary" id="home">
                            <div class="container">
                                <div class="row flex-center">
                                    <div class="col-md-5 col-lg-6 order-0 order-md-1 mt-8 mt-md-0"><a
                                            class="img-landing-banner" href="#!"><img class="img-fluid"
                                                src="assets/images/gallery/hero-header.png" alt="hero-header" /></a>
                                    </div>
                                    <div class="col-md-7 col-lg-6 py-8 text-md-start text-center">
                                        <h1 class="display-1 fs-md-5 fs-lg-6 fs-xl-8 text-light">Are you starving?</h1>
                                        <h1 class="text-800 mb-5 fs-4">Within a few clicks, find meals that<br
                                                class="d-none d-xxl-block" />are accessible near you</h1>
                                    </div>
                                </div>
                            </div>
                        </section>


                        <!-- ============================================-->
                        <!-- <section> begin ============================-->
                        <section class="py-0 bg-primary-gradient">

                            <div class="container">
                                <div class="row justify-content-center g-0">
                                    <div class="col-xl-9">
                                        <div class="col-lg-6 text-center mx-auto mb-3 mb-md-5 mt-4">
                                            <h5 class="fw-bold text-danger fs-3 fs-lg-5 lh-sm my-6">How does it work
                                            </h5>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6 col-md-3 mb-6">
                                                <div class="text-center"><img class="shadow-icon"
                                                        src="assets/images/gallery/order.png" height="112" alt="..." />
                                                    <h5 class="mt-4 fw-bold">Choose order</h5>
                                                    <p class="mb-md-0">Check over hundreds of menus to pick your
                                                        favorite food</p>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3 mb-6">
                                                <div class="text-center"><img class="shadow-icon"
                                                        src="assets/images/gallery/location.png" height="112"
                                                        alt="..." />
                                                    <h5 class="mt-4 fw-bold">Address</h5>
                                                    <p class="mb-md-0">Enter the address where your food will be
                                                        delivered.</p>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3 mb-6">
                                                <div class="text-center"><img class="shadow-icon"
                                                        src="assets/images/gallery/pay.png" height="112" alt="..." />
                                                    <h5 class="mt-4 fw-bold">Payment</h5>
                                                    <p class="mb-md-0">It's quick, safe, and simple. Select several
                                                        methods of
                                                        payment</p>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 col-md-3 mb-6">
                                                <div class="text-center"><img class="shadow-icon"
                                                        src="assets/images/gallery/meals.png" height="112" alt="..." />
                                                    <h5 class="mt-4 fw-bold">Enjoy meals</h5>
                                                    <p class="mb-md-0">Food is made and delivered directly to your home.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end of .container-->

                        </section>
                        <!-- <section> close ============================-->
                        <!-- ============================================-->




                        	<!-- ============================================-->
                        <!-- <section> begin ============================-->
                        <section class="py-4 overflow-hidden">

                            <div class="container">
                                <div class="row h-100">
                                    <div class="col-lg-7 mx-auto text-center mt-7 mb-5">
                                        <h5 class="fw-bold fs-3 fs-lg-5 lh-sm">Popular items</h5>
                                    </div>
                                    <div class="col-12">
                                        <div class="carousel slide" id="carouselPopularItems" data-bs-touch="false"
                                            data-bs-interval="false">
                                            <div class="carousel-inner">
                                                <div class="carousel-item active" data-bs-interval="10000">
                                                    <div class="row gx-3 h-100 align-items-center">
                                                    	<%
												            List<Menu> allMenu = (List<Menu>) request.getAttribute("allMenus");
											            	int count0 = 0;
												            for (Menu Menus : allMenu) {
												            	if (count0 >= 3) {
					                                            	break;
					                                            }
					                                            count0++;
												        %>
                                                        <div class="col-sm-6 col-md-4 mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-3"><img
                                                                    class="img-fluid rounded-3 h-100"
                                                                    src="assets/images/gallery/menus/<%= Menus.getImagePath() %>"
                                                                    alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5 class="fw-bold text-1000 text-truncate mb-1">
                                                                        <%= Menus.getItemName() %>
                                                                    </h5>
                                                                    <div><span><p><%= Menus.getDescription() %></p></span>
                                                                    <span class="fw-bold text-1000">₹<%= Menus.getPrice() %></span>
                                                                    </div>
                                                                </div>
                                                                <form action="cart" method="post">
	                                                                	<input type="hidden" name="itemId" value="<%= Menus.getMenuId() %>">
	                                                                	<input type="number" name="quantity" id="quantity" value="1" min="1" class="quantity-input">
	                                                                	<input type="submit" value="Add to Cart" class="add-to-cart-btn btn btn-danger">
	                                                                	<input type="hidden" name="action" value="add">
	                                                            </form>
                                                            </div>
                                                        </div>
												        <%
												            }
												        %>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end of .container-->

                        </section>
                        <!-- <section> close ============================-->
                        <!-- ============================================-->


                        <section id="testimonial">
                            <div class="container">
                                <div class="row h-100">
                                    <div class="col-lg-7 mx-auto text-center mb-6">
                                        <h5 class="fw-bold fs-3 fs-lg-5 lh-sm mb-3">Featured Restaurants</h5>
                                    </div>
                                </div>
                                <div class="row gx-2">
                                    <% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
                                            int count = 0;
                                            for (Restaurant restaurant : restaurants) {
                                            if (count >= 8) {
                                            	break;
                                            }
                                            count++;
                                     %>
                                            <div class="col-sm-6 col-md-4 col-lg-3 h-100 mb-5">
                                                <div class="card card-span h-100 text-white rounded-3"><img
                                                        class="img-fluid rounded-3 h-100"
                                                        src="assets/images/gallery/restaurants/<%= restaurant.getImagePath() %>"
                                                        alt="..." />
                                                    <div class="card-body ps-0">
                                                        <div class="d-flex align-items-center mb-3">
                                                            <div class="flex-1 ms-3">
                                                                <h5 class="mb-1 fw-bold text-1000">
                                                                    <%= restaurant.getName() %>
                                                                </h5><span class="text-success fs--1 me-1">
                                                                    <i
                                                                        class="fa-solid fa-star-half-stroke"></i></span><span
                                                                    class="text-1000">
                                                                    <%= restaurant.getRating() %>
                                                                </span>
                                                                <span class="fw-bold text-1000"><i
                                                                        class="fa fa-clock-o"></i> • <%=
                                                                        restaurant.getDeliveryTime() %>mins</span>
                                                                <div><span class="text-1000"><i
                                                                            class="fa fa-cutlery"></i>
                                                                        <%= restaurant.getCuisineType() %>
                                                                    </span></div>
                                                                <span class="text-1000"><i
                                                                        class=" fa-solid fa-location-dot"></i>
                                                                    <%= restaurant.getAddress() %>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <% if (restaurant.isActive()==true) { %>
                                                            <a href="<%= request.getContextPath() %>/menu?restaurantID=<%= restaurant.getRestaurantId() %>" class="badge bg-soft-danger p-2"><span
                                                                    class="fw-bold fs-1 text-success">Open
                                                                    Now</span></a>
                                                        <% } else { %>
                                                                <span class="badge bg-soft-danger p-2">
                                                                    <span class="fw-bold fs-1 text-danger">Opens
                                                                        Tomorrow</span>
                                                                </span>
                                                         <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <% } %>

                                                <div class="col-12 d-flex justify-content-center mt-5"> <a
                                                        class="btn btn-lg btn-primary"
                                                        href="<%= request.getContextPath() %>/restaurant">View All <i
                                                            class="fas fa-chevron-right ms-2"> </i></a></div>
                                </div>
                            </div>
                        </section>


                        <!-- ============================================-->
                        <!-- <section> begin ============================-->
                        <section class="py-8 overflow-hidden">

                            <div class="container">
                                <div class="row flex-center mb-6">
                                    <div class="col-lg-11">
                                        <h5 class="fw-bold fs-3 fs-lg-5 lh-sm text-center text-lg-start">Search by Food
                                        </h5>
                                    </div>
                                </div>
                                <div class="row flex-center">
                                    <div class="col-12">
                                        <div class="carousel slide" id="carouselSearchByFood" data-bs-touch="false"
                                            data-bs-interval="false">
                                            <div class="carousel-inner">
                                                <div class="carousel-item active" data-bs-interval="10000">
                                                    <div class="row h-100 align-items-center">
                                                        <div class="col-sm-6 col-md-4 col-xl mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-circle"><img
                                                                    class="img-fluid rounded-circle h-100"
                                                                    src="assets/images/gallery/search-pizza.png"
                                                                    alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5
                                                                        class="text-center fw-bold text-1000 text-truncate mb-2">
                                                                        pizza</h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6 col-md-4 col-xl mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-circle"><img
                                                                    class="img-fluid rounded-circle h-100"
                                                                    src="assets/images/gallery/burger.png" alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5
                                                                        class="text-center fw-bold text-1000 text-truncate mb-2">
                                                                        Burger</h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6 col-md-4 col-xl mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-circle"><img
                                                                    class="img-fluid rounded-circle h-100"
                                                                    src="assets/images/gallery/noodles.png" alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5
                                                                        class="text-center fw-bold text-1000 text-truncate mb-2">
                                                                        Noodles</h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6 col-md-4 col-xl mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-circle"><img
                                                                    class="img-fluid rounded-circle h-100"
                                                                    src="assets/images/gallery/sub-sandwich.png"
                                                                    alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5
                                                                        class="text-center fw-bold text-1000 text-truncate mb-2">
                                                                        Sub-sandwiches</h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6 col-md-4 col-xl mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-circle"><img
                                                                    class="img-fluid rounded-circle h-100"
                                                                    src="assets/images/gallery/chowmein.png"
                                                                    alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5
                                                                        class="text-center fw-bold text-1000 text-truncate mb-2">
                                                                        Chowmein</h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-6 col-md-4 col-xl mb-5 h-100">
                                                            <div class="card card-span h-100 rounded-circle"><img
                                                                    class="img-fluid rounded-circle h-100"
                                                                    src="assets/images/gallery/steak.png" alt="..." />
                                                                <div class="card-body ps-0">
                                                                    <h5
                                                                        class="text-center fw-bold text-1000 text-truncate mb-2">
                                                                        Steak</h5>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- end of .container-->

                        </section>
                        <!-- <section> close ============================-->
                        <!-- ============================================-->


                        <section class="py-0">
                            <div class="bg-holder"
                                style="background-image:url(assets/images/gallery/cta-two-bg.png);background-position:center;background-size:cover;">
                            </div>
                            <!--/.bg-holder-->

                            <div class="container">
                                <div class="row flex-center">
                                    <div class="col-xxl-9 py-7 text-center">
                                        <h1 class="fw-bold mb-4 text-white fs-6">Are you ready to order <br />with the
                                            best deals?
                                        </h1><a class="btn btn-danger"
                                            href="<%= request.getContextPath() %>/restaurant"> PROCEED TO ORDER<i
                                                class="fas fa-chevron-right ms-2"></i></a>
                                    </div>
                                </div>
                            </div>
                        </section>


                        <!-- ============================================-->
                        <!-- <section> begin ============================-->
                        <section class="py-0 pt-7 bg-1000">

                            <div class="container">
                                <div class="row justify-content-lg-between">
                                    <h5 class="lh-lg fw-bold text-white">OUR TOP CITIES</h5>
                                    <div class="col-6 col-md-4 col-lg-auto mb-3">
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Mumbai</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Delhi</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Banglore</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Gao</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Nasik</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-6 col-md-4 col-lg-auto mb-3">
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Amravati</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Rajstan</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Navi
                                                    Mumbai</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Punjab</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Hydrabad</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-6 col-md-4 col-lg-auto mb-3">
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Pune</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Satara</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Kolhapur</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Baramati</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Sangli</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-6 col-md-4 col-lg-auto mb-3">
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Chennai</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Laknaow</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">West
                                                    Gao</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Lahor</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Nagpur</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-6 col-md-4 col-lg-auto mb-3">
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Sambhaji Nagar</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Solapur</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Akola</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Thane</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Ratnagiri</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <hr class="text-900" />
                                <div class="row">
                                    <div class="col-6 col-md-4 col-lg-4 col-xxl-2 mb-3">
                                        <h5 class="lh-lg fw-bold text-white">COMPANY</h5>
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">About
                                                    Us</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Team</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">Careers</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none"
                                                    href="#!">blog</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-6 col-md-4 col-lg-4 col-xxl-2 col-lg-3 mb-3">
                                        <h5 class="lh-lg fw-bold text-white">CONTACT</h5>
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Help
                                                    &amp;
                                                    Support</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Partner
                                                    with us
                                                </a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Ride
                                                    with us</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-6 col-md-4 col-lg-4 col-xxl-2 mb-3">
                                        <h5 class="lh-lg fw-bold text-white">LEGAL</h5>
                                        <ul class="list-unstyled mb-md-4 mb-lg-0">
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Terms
                                                    &amp;
                                                    Conditions</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Refund
                                                    &amp;
                                                    Cancellation</a></li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Privacy
                                                    Policy</a>
                                            </li>
                                            <li class="lh-lg"><a class="text-200 text-decoration-none" href="#!">Cookie
                                                    Policy</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-12 col-md-8 col-lg-6 col-xxl-4">
                                        <h3 class="text-500 my-4">Receive exclusive offers and <br />discounts in your
                                            mailbox</h3>
                                        <form action="https://sheetdb.io/api/v1/k0ynnxw0yqul2" method="POST"
                                            id="sheetdb-form">
                                            <div class="row input-group-icon mb-5">
                                                <div class="col-auto"><i
                                                        class="fas fa-envelope input-box-icon text-500 ms-3"></i>
                                                    <input class="form-control input-box bg-800 border-0" type="email"
                                                        placeholder="Enter Email" aria-label="email"
                                                        name="data[Email]" />
                                                </div>
                                                <div class="col-auto">
                                                    <button class="btn btn-primary" type="submit">Subscribe</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row flex-center">
                                        <div class="text-center my-3">
                                            <a href="#"><i class="fa-brands fa-facebook fa-lg"
                                                    style="color: #74C0FC; padding:8px;"></i></a>
                                            <a href="#"><i class="fa-brands fa-twitter fa-lg"
                                                    style="color: #74C0FC; padding:8px;"></i></a>
                                            <a href="https://www.instagram.com/_akshaybarge_/" target="_blank"><i
                                                    class="social-icon fab fa-instagram fa-lg"
                                                    style="color: #74C0FC; padding:8px;"></i></a>
                                            <a href="https://www.linkedin.com/in/akshaybarge315/"><i
                                                    class="social-icon fab fa-linkedin-in fa-lg"
                                                    style="color: #74C0FC; padding:8px;"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <hr class="border border-800" />
                                <div class="row flex-center pb-3">
                                    <div class="col-md-6 order-0">
                                        <p class="text-200 text-center text-md-center">All rights Reserved &copy;
                                            GastroGrid, 2024
                                        </p>
                                    </div>
                                </div>
                            </div><!-- end of .container-->

                        </section>
                        <!-- <section> close ============================-->
                        <!-- ============================================-->


                    </main>
                    <!-- ===============================================-->
                    <!--    End of Main Content-->
                    <!-- ===============================================-->

                    <script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>

                    <script src="assets/js/emailSubscription.js"></script>

                </body>

                </html>