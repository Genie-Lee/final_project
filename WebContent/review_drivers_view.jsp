<%@page import="com.DataObject.reviewDO"%>
<%@page import="com.DataObject.enterpriseDO"%>
<%@page import="com.DataObject.driverDO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DataAccessObject.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title> DeliverySystem</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
		
<style>
.star-rating {
	width: 150px;
}
.star-rating, .star-rating span {
	display: inline-block;
	height: 29px;
	overflow: hidden;
	background: url(images/star.png) no-repeat;
	background-size:150px;
}
.star-rating span {
	background-position: left bottom;
	line-height: 0;
	vertical-align: top;
}
</style>
		
	</head>
<body class="is-preload">
<!--1.DB에 데이터 가져오기-->
<%
	driverDO d_do = (driverDO)session.getAttribute("d_do");	
	enterpriseDO e_do = (enterpriseDO)session.getAttribute("e_do"); 
	String d_id = d_do.getD_id();
	memberDAO dao = new memberDAO();
	ArrayList<reviewDO> r_arr = dao.get_review_driver(d_id);
%> 
<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
			<div id="main">
				<div class="inner">
					<!-- Header -->
						<header id="header">
							<h2>나의 리뷰</h2>
						</header>
							<!-- Section -->
								<section>
									<div class="menu">
										<article>																												
		                                   <table>
		                                   		<%if(r_arr.size()==0){ %>
		                                   		<tr>
		                                   			<td>
			                                   			<div style="border: 2px solid #f56a6a; background-color:transparent; padding:20px; border-radius: 0.375em; text-align:center">
															아직 리뷰가 없어요!
														</div>
			                                   		</td>
		                                   		</tr>
		                                   		<%}else{ %>
			                                   	<%for(int i=0; i<r_arr.size(); i++){%>
			                                   	<tr>
			                                   		<td style="width: 150px;">
			                                   			<div class='star-rating'>
															<span style="width: <%=r_arr.get(i).getStar_rate() %>%"></span>
														</div>
			                                   		</td>
			                                   		<td>
			                                   			<div style="border: 2px solid #f56a6a; background-color:transparent; padding:20px; border-radius: 0.375em; text-align:center">
															<%=r_arr.get(i).getPost() %>
														</div>
			                                   		</td>
			                                   	</tr>
			                                   	<%} %>
											<%} %>
		                                   </table>
										</article>
									</div>
								</section>
	
						</div>
					</div>

				<!-- Sidebar -->
					<div id="sidebar">
						<div class="inner">
							<!-- Menu -->
								<nav id="menu">
									<header class="major">
										<h2>Menu</h2>
									</header>
									<ul>
										<li><a href="DriverMain.jsp">메인</a></li>
										<%if((d_do==null)&&(e_do==null)){ %>
										<li><a href="login.jsp">로그인</a></li>
										<%}else{%>
										<li><a href="logoutCon_driver.do">로그아웃</a></li>
										<%} %>
										<!-- <li>
									</ul>
								</nav>
								
							<!-- Section -->
								<section>
									<header class="major">
										<h2>Get in touch</h2>
									</header>
									<p>문제 발생시 다음 연락처로 연락 바랍니다.</p>
									<ul class="contact">
										<li class="icon solid fa-envelope"><a href="#">genie141930@gmail.com</a></li>
										<li class="icon solid fa-phone">(010) 5091-0984</li>
										<li class="icon solid fa-home">광주광역시 북구 용봉동 77<br />
										전남대학교 생활관 9A동</li>
									</ul>
								</section>

							<!-- Footer -->
								<footer id="footer">
									<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
								</footer>

						</div>
					</div>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
		    <script src="assets/js/util.js"></script>
		    <script src="assets/js/main.js"></script>

	</body>
</html>