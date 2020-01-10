<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>首页</title>
<link rel="shortcut icon" href="assets/img/favicon.ico"
	type="image/x-icon" />
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,400i,500,600,700"
	rel="stylesheet">
<link href="assets/css/vendor/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/vendor/font-awesome.css" rel="stylesheet">
<link href="assets/css/plugins.css" rel="stylesheet">
<link href="assets/css/helper.min.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<script src="assets/js/vendor/modernizr-2.8.3.min.js"></script>
</head>

<body>

	<!--== Start Header Section ==-->
	<header id="header-area">
		<!-- Start PreHeader Area -->
		<div class="preheader-area">
			<div class="container">
				<div class="row">
					<div class="col-md-5 col-lg-6">
						<div
							class="preheader-contact-info d-flex align-items-center justify-content-between justify-content-md-start">
							<div class="single-contact-info">
								<strong class="contact-text">用户名/游客</strong>
							</div>
							<div class="single-contact-info">
								<strong class="contact-text">欢迎来到xxx</strong>
							</div>
						</div>
					</div>

					<div class="col-md-7 col-lg-6 mt-xs-10">
						<div class="site-setting-menu">
							<ul class="nav justify-content-center justify-content-md-end">
								<li><a href="my-account.html">个人中心</a></li>
								<li><a href="wishlist.html">写文章</a></li>
								<li><a href="pages/login-register.html">登出</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End PreHeader Area -->

		<!-- Start Header Middle Area -->
		<div class="header-middle-area">
			<div class="container">
				<div class="row">
					<!-- Logo Area Start -->
					<div
						class="col-4 col-md-2 col-xl-3 m-auto text-center text-lg-left">
						<a href="index.jsp" class="logo-wrap d-block"> <img
							src="assets/img/logo.png" alt="Logo" class="img-fluid" />
						</a>
					</div>
					<!-- Logo Area End -->

					<!-- Search Box Area Start -->
					<div class="col-8 col-md-7 col- m-auto ">
						<div class="search-box-wrap">
							<form class="search-form d-flex" action="#" method="get">
								<input type="search" name="search" placeholder="输入要搜索的关键字" />
								<button class="btn btn-search">
									<img src="assets/img/icons/icon-search.png" alt="搜索" />
								</button>
							</form>
						</div>
					</div>
					<!-- Search Box Area End -->
				</div>
			</div>
		</div>
		<!-- End Header Middle Area -->

		<!-- Start Main Menu Area -->
		<div class="navigation-area" id="fixheader">
			<div class="container">
				<div class="row">
					<!-- 个人中心 -->
					<div class="col-10 col-lg-3">
						<div class="categories-list-wrap">
							<button class="btn btn-category d-none d-lg-inline-block">
								<!-- <i class="fa fa-bars"></i> --> 个人中心
							</button>
						</div>
					</div>

					<!-- Main Menu Start -->
					<div class="col-2 col-lg-9 d-none d-lg-block">
						<div class="main-menu-wrap">
							<nav class="mainmenu">
								<ul class="main-navbar clearfix">
									<li class="dropdown-show"><a href="index.html"
										class="arrow-toggle">我的文章</a>
										<ul class="dropdown-nav sub-dropdown">
											<li><a href="#">标签 1</a></li>
											<li><a href="#">标签 2</a></li>
											<li><a href="#">标签 3</a></li>
											<li><a href="#">标签 4</a></li>
										</ul></li>
									<li><a href="#">我关注的</a></li>
									<li class="dropdown-show"><a href="#" class="arrow-toggle">我订阅的</a>
										<ul class="dropdown-nav">
											<li><a href="#">标签 1</a></li>
											<li><a href="#">标签 2</a></li>
											<li><a href="#">标签</a></li>
											<li><a href="#">标签</a></li>
										</ul></li>
									<li class="dropdown-show"><a href="#">热门</a></li>
									<li class="dropdown-show"><a href="#" class="arrow-toggle">猜你喜欢</a></li>
									
									<li class="dropdown-show"><a href="#" class="arrow-toggle">？？？</a>
										<ul class="dropdown-nav">
											<li><a href="blog.html">Blog Left Sidebar</a></li>
											<li><a href="blog-right-sidebar.html">Blog Right
													Sidebar</a></li>
											<li><a href="blog-grid.html">Blog Grid Layout</a></li>
											<li><a href="single-blog.html">Single Blog</a></li>
											<li><a href="single-blog-right-sidebar.html">Single
													Blog Right Sidebar</a></li>
										</ul></li>
									<li><a href="contact.html">？？？</a></li>
								</ul>
							</nav>
						</div>
					</div>
					<!-- Main Menu End -->
				</div>
			</div>
		</div>
		<!-- End Main Menu Area -->
	</header>
	<!--== End Header Section ==-->

	<!--== Start Page Breadcrumb ==-->
	<!-- <div class="page-breadcrumb-wrap">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="page-breadcrumb">
						<ul class="nav">
							<li><a href="index.html">Home</a></li>
							<li><a href="shop.html" class="active">Dashboard</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!--== End Page Breadcrumb ==-->

	<!--== Page Content Wrapper Start ==-->
	<div id="page-content-wrapper">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- My Account Page Start -->
					<div class="myaccount-page-wrapper">
						<!-- My Account Tab Menu Start -->
						<div class="row">
							<div class="col-lg-3">
								<div class="myaccount-tab-menu nav" role="tablist">
									<a href="#dashboad" class="active" data-toggle="tab"><i
										class="fa fa-dashboard"></i> Dashboard</a> <a href="#orders"
										data-toggle="tab"><i class="fa fa-cart-arrow-down"></i>
										Orders</a> <a href="#download" data-toggle="tab"><i
										class="fa fa-cloud-download"></i> Download</a> <a
										href="#payment-method" data-toggle="tab"><i
										class="fa fa-credit-card"></i> Payment Method</a> <a
										href="#address-edit" data-toggle="tab"><i
										class="fa fa-map-marker"></i> address</a> <a href="#account-info"
										data-toggle="tab"><i class="fa fa-user"></i> Account
										Details</a> <a href="login-register.html"><i
										class="fa fa-sign-out"></i> Logout</a>
								</div>
							</div>
							<!-- My Account Tab Menu End -->

							<!-- My Account Tab Content Start -->
							<div class="col-lg-9 mt-15 mt-lg-0">
								<div class="tab-content" id="myaccountContent">
									<!-- Single Tab Content Start -->
									<div class="tab-pane fade show active" id="dashboad"
										role="tabpanel">
										<div class="myaccount-content">
											<h3>Dashboard</h3>

											<div class="welcome">
												<p>
													Hello, <strong>Alex Tuntuni</strong> (If Not <strong>Tuntuni
														!</strong><a href="login-register.html" class="logout"> Logout</a>)
												</p>
											</div>

											<p class="mb-0">From your account dashboard. you can
												easily check & view your recent orders, manage your shipping
												and billing addresses and edit your password and account
												details.</p>
										</div>
									</div>
									<!-- Single Tab Content End -->

									<!-- Single Tab Content Start -->
									<div class="tab-pane fade" id="orders" role="tabpanel">
										<div class="myaccount-content">
											<h3>Orders</h3>

											<div class="myaccount-table table-responsive text-center">
												<table class="table table-bordered">
													<thead class="thead-light">
														<tr>
															<th>Order</th>
															<th>Date</th>
															<th>Status</th>
															<th>Total</th>
															<th>Action</th>
														</tr>
													</thead>

													<tbody>
														<tr>
															<td>1</td>
															<td>Aug 22, 2018</td>
															<td>Pending</td>
															<td>$3000</td>
															<td><a href="cart.html" class="btn">View</a></td>
														</tr>
														<tr>
															<td>2</td>
															<td>July 22, 2018</td>
															<td>Approved</td>
															<td>$200</td>
															<td><a href="cart.html" class="btn">View</a></td>
														</tr>
														<tr>
															<td>3</td>
															<td>June 12, 2017</td>
															<td>On Hold</td>
															<td>$990</td>
															<td><a href="cart.html" class="btn">View</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!-- Single Tab Content End -->

									<!-- Single Tab Content Start -->
									<div class="tab-pane fade" id="download" role="tabpanel">
										<div class="myaccount-content">
											<h3>Downloads</h3>

											<div class="myaccount-table table-responsive text-center">
												<table class="table table-bordered">
													<thead class="thead-light">
														<tr>
															<th>Product</th>
															<th>Date</th>
															<th>Expire</th>
															<th>Download</th>
														</tr>
													</thead>

													<tbody>
														<tr>
															<td>Haven - Free Real Estate PSD Template</td>
															<td>Aug 22, 2018</td>
															<td>Yes</td>
															<td><a href="#" class="btn">Download File</a></td>
														</tr>
														<tr>
															<td>HasTech - Profolio Business Template</td>
															<td>Sep 12, 2018</td>
															<td>Never</td>
															<td><a href="#" class="btn">Download File</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!-- Single Tab Content End -->

									<!-- Single Tab Content Start -->
									<div class="tab-pane fade" id="payment-method" role="tabpanel">
										<div class="myaccount-content">
											<h3>Payment Method</h3>

											<p class="saved-message">You Can't Saved Your Payment
												Method yet.</p>
										</div>
									</div>
									<!-- Single Tab Content End -->

									<!-- Single Tab Content Start -->
									<div class="tab-pane fade" id="address-edit" role="tabpanel">
										<div class="myaccount-content">
											<h3>Billing Address</h3>

											<address>
												<p>
													<strong>Alex Tuntuni</strong>
												</p>
												<p>
													1355 Market St, Suite 900 <br> San Francisco, CA 94103
												</p>
												<p>Mobile: (123) 456-7890</p>
											</address>

											<a href="#" class="btn d-inline-block"><i
												class="fa fa-edit"></i> Edit Address</a>
										</div>
									</div>
									<!-- Single Tab Content End -->

									<!-- Single Tab Content Start -->
									<div class="tab-pane fade" id="account-info" role="tabpanel">
										<div class="myaccount-content">
											<h3>Account Details</h3>

											<div class="account-details-form">
												<form action="#">
													<div class="row">
														<div class="col-lg-6">
															<div class="single-input-item">
																<label for="first-name" class="required">First
																	Name</label> <input type="text" id="first-name"
																	placeholder="First Name" />
															</div>
														</div>

														<div class="col-lg-6">
															<div class="single-input-item">
																<label for="last-name" class="required">Last
																	Name</label> <input type="text" id="last-name"
																	placeholder="Last Name" />
															</div>
														</div>
													</div>

													<div class="single-input-item">
														<label for="display-name" class="required">Display
															Name</label> <input type="text" id="display-name"
															placeholder="Display Name" />
													</div>

													<div class="single-input-item">
														<label for="email" class="required">Email Addres</label> <input
															type="email" id="email" placeholder="Email Address" />
													</div>

													<fieldset>
														<legend>Password change</legend>
														<div class="single-input-item">
															<label for="current-pwd" class="required">Current
																Password</label> <input type="password" id="current-pwd"
																placeholder="Current Password" />
														</div>

														<div class="row">
															<div class="col-lg-6">
																<div class="single-input-item">
																	<label for="new-pwd" class="required">New
																		Password</label> <input type="password" id="new-pwd"
																		placeholder="New Password" />
																</div>
															</div>

															<div class="col-lg-6">
																<div class="single-input-item">
																	<label for="confirm-pwd" class="required">Confirm
																		Password</label> <input type="password" id="confirm-pwd"
																		placeholder="Confirm Password" />
																</div>
															</div>
														</div>
													</fieldset>

													<div class="single-input-item">
														<button class="btn">Save Changes</button>
													</div>
												</form>
											</div>
										</div>
									</div>
									<!-- Single Tab Content End -->
								</div>
							</div>
							<!-- My Account Tab Content End -->
						</div>
					</div>
					<!-- My Account Page End -->
				</div>
			</div>
		</div>
	</div>
	<!--== Page Content Wrapper End ==-->

	<!--== Start Newsletter Area ==-->
	<div class="newsletter-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-9 m-auto">
					<!-- Newsletter Content Start -->
					<div
						class="newsletter-content-wrap text-center text-lg-left d-lg-flex">
						<h2>
							<i class="fa fa-envelope-square"></i> Sign up for Newsletter
						</h2>
						<div class="newsletter-form-wrap">
							<form id="subscribe-form" action="assets/php/subscribe.php"
								method="post">
								<input type="email" name="newsletter_email" id="address"
									placeholder="Enter Your Email Address" required />
								<button class="btn" type="submit">Subscribe</button>
							</form>
							<!-- Show Error & Success Message -->
							<div id="subscribeResult"></div>
						</div>
					</div>
					<!-- Newsletter Content End -->
				</div>

				<div class="col-lg-3 m-auto text-center text-lg-right">
					<!-- Social Icons Area Start -->
					<div class="social-icons">
						<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
							class="fa fa-twitter"></i></a> <a href="#"><i
							class="fa fa-linkedin"></i></a> <a href="#"><i
							class="fa fa-youtube"></i></a>
					</div>
					<!-- Social Icons Area End -->
				</div>
			</div>
		</div>
	</div>
	<!--== End Newsletter Area ==-->

	<!--== Start Footer Area ==-->
	<footer id="footer-area">
		<!-- Footer Widget Area Start -->
		<div class="footer-widget-area">
			<div class="container">
				<div class="footer-widget-content">
					<div class="row">
						<!-- Single Widget-item Start -->
						<div class="col-lg-3 col-sm-6">
							<div class="single-widget-item">
								<h3 class="widget-title">About Market</h3>
								<div class="widget-body">
									<ul class="footer-list">
										<li><a href="#">My Account</a></li>
										<li><a href="#">Order History</a></li>
										<li><a href="#">Returns</a></li>
										<li><a href="#">Specials</a></li>
										<li><a href="#">Site Map</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- Single Widget-item End -->

						<!-- Single Widget-item Start -->
						<div class="col-lg-3 col-sm-6">
							<div class="single-widget-item">
								<h3 class="widget-title">Customer Service</h3>
								<div class="widget-body">
									<ul class="footer-list">
										<li><a href="#">Returns</a></li>
										<li><a href="#">Order History</a></li>
										<li><a href="#">My Account</a></li>
										<li><a href="#">Site Map</a></li>
										<li><a href="#">Specials</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- Single Widget-item End -->

						<!-- Single Widget-item Start -->
						<div class="col-lg-3 col-sm-6">
							<div class="single-widget-item">
								<h3 class="widget-title">Information</h3>
								<div class="widget-body">
									<ul class="footer-list">
										<li><a href="#">Order History</a></li>
										<li><a href="#">Site Map</a></li>
										<li><a href="#">Returns</a></li>
										<li><a href="#">Specials</a></li>
										<li><a href="#">My Account</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- Single Widget-item End -->

						<!-- Single Widget-item Start -->
						<div class="col-lg-3 col-sm-6">
							<div class="single-widget-item">
								<h3 class="widget-title">Contact Us</h3>
								<div class="widget-body">
									<div class="contact-info">
										<div class="single-contact-info">
											<span class="contact-icon"> <i class="fa fa-home"></i>
											</span> <a href="mailto:your@example.com" class="contact-text">
												<strong>Address:</strong> 15/C, Uttara, Dhaka
											</a>
										</div>
										<div class="single-contact-info">
											<span class="contact-icon"> <i class="fa fa-phone"></i>
											</span> <a href="mailto:your@example.com" class="contact-text">
												<strong>Phone:</strong> (+1) 866-540-3229
											</a>
										</div>
										<div class="single-contact-info">
											<span class="contact-icon"> <i class="fa fa-envelope"></i>
											</span> <a href="mailto:your@example.com" class="contact-text">
												<strong>Email:</strong> your@email.here
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Single Widget-item End -->
					</div>
				</div>
			</div>
		</div>
		<!-- Footer Widget Area End -->

		<!-- Footer Bottom Area -->
		<div class="footer-bttom-area">
			<div class="container">
				<div class="row">
					<!-- Copyright Text Area -->
					<div
						class="col-md-6 m-auto text-center text-md-left order-last order-md-first">
						<p>
							Copyright &copy; 2018.Company name All rights reserved.<a
								target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
						</p>
					</div>
					<!-- Copyright Text Area -->

					<!-- Payment Method Area -->
					<div class="col-md-6 m-auto text-center text-md-right">
						<div class="payment-support">
							<img src="assets/img/payment.png" alt="Payment"
								class="d-inline-block" />
						</div>
					</div>
					<!-- Payment Method Area -->
				</div>
			</div>
		</div>
		<!-- Footer Bottom Area -->
	</footer>
	<!--== End Footer Area ==-->

	<!-- Scroll to Top Start -->
	<a href="#" class="scrolltotop"><i class="fa fa-angle-up"></i></a>
	<!-- Scroll to Top End -->

	<!--== Product Quick View Modal Area Wrap ==-->
	<div class="modal" id="quickView" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true"><img
						src="assets/img/icons/cancel.png" alt="Close" class="img-fluid" /></span>
				</button>
				<div class="modal-body">
					<div class="quick-view-content single-product-page-content">
						<div class="row">
							<!-- Product Thumbnail Start -->
							<div class="col-lg-5 col-md-6">
								<div class="product-thumbnail-wrap">
									<div class="product-thumb-carousel owl-carousel">
										<div class="single-thumb-item">
											<a href="single-product.html"><img class="img-fluid"
												src="assets/img/single-pro-1.jpg" alt="Product" /></a>
										</div>

										<div class="single-thumb-item">
											<a href="single-product.html"><img class="img-fluid"
												src="assets/img/single-pro-2.jpg" alt="Product" /></a>
										</div>

										<div class="single-thumb-item">
											<a href="single-product.html"><img class="img-fluid"
												src="assets/img/single-pro-3.jpg" alt="Product" /></a>
										</div>

										<div class="single-thumb-item">
											<a href="single-product.html"><img class="img-fluid"
												src="assets/img/single-pro-4.jpg" alt="Product" /></a>
										</div>
									</div>
								</div>
							</div>
							<!-- Product Thumbnail End -->

							<!-- Product Details Start -->
							<div class="col-lg-7 col-md-6 mt-5 mt-md-0">
								<div class="product-details">
									<h2>
										<a href="single-product.html">Crown Summit Backpack</a>
									</h2>

									<div class="rating">
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star-half"></i> <i
											class="fa fa-star-o"></i>
									</div>

									<span class="price">$52.00</span>

									<div class="product-info-stock-sku">
										<span class="product-stock-status text-success">In
											Stock</span> <span class="product-sku-status ml-5"><strong>SKU</strong>
											MH03</span>
									</div>

									<p class="products-desc">Ideal for cold-weathered training
										worked lorem ipsum outdoors, the Chaz Hoodie promises superior
										warmth with every wear. Thick material blocks out the wind as
										ribbed cuffs and bottom band seal in body heat Lorem ipsum
										dolor sit amet, consectetur adipisicing elit. Enim,
										reprehenderit.</p>
									<div class="shopping-option-item">
										<h4>Color</h4>
										<ul class="color-option-select d-flex">
											<li class="color-item black">
												<div class="color-hvr">
													<span class="color-fill"></span> <span class="color-name">Black</span>
												</div>
											</li>

											<li class="color-item green">
												<div class="color-hvr">
													<span class="color-fill"></span> <span class="color-name">green</span>
												</div>
											</li>

											<li class="color-item orange">
												<div class="color-hvr">
													<span class="color-fill"></span> <span class="color-name">Orange</span>
												</div>
											</li>
										</ul>
									</div>

									<div class="product-quantity d-flex align-items-center">
										<div class="quantity-field">
											<label for="qty">Qty</label> <input type="number" id="qty"
												min="1" max="100" value="1" />
										</div>

										<a href="cart.html" class="btn">Add to Cart</a>
									</div>
								</div>
							</div>
							<!-- Product Details End -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--== Product Quick View Modal Area End ==-->


	<!--=======================Javascript============================-->
	<!--=== Jquery Min Js ===-->
	<script src="assets/js/vendor/jquery-3.3.1.min.js"></script>
	<!--=== Jquery Migrate Min Js ===-->
	<script src="assets/js/vendor/jquery-migrate-1.4.1.min.js"></script>
	<!--=== Popper Min Js ===-->
	<script src="assets/js/vendor/popper.min.js"></script>
	<!--=== Bootstrap Min Js ===-->
	<script src="assets/js/vendor/bootstrap.min.js"></script>
	<!--=== Ajax Mail Js ===-->
	<script src="assets/js/ajax-mail.js"></script>
	<!--=== Plugins Min Js ===-->
	<script src="assets/js/plugins.js"></script>

	<!--=== Active Js ===-->
	<script src="assets/js/active.js"></script>
</body>
</html>
