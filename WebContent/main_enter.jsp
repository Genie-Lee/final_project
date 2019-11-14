<%@page import="com.DataObject.enterpriseDO"%>
<%@page import="com.DataObject.driverDO"%>
<%@page import="com.DataObject.enter_main_viewerDO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DataAccessObject.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>DeliverySystem</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

<style>
table, tr, th, td{vertical-align: bottom;}
.star-rating {
	width: 205px;
}
.star-rating, .star-rating span {
	display: inline-block;
	height: 39px;
	overflow: hidden;
	background: url(images/star.png) no-repeat;
}
.star-rating span {
	background-position: left bottom;
	line-height: 0;
	vertical-align: top;
}

		table, tr, th, td{vertical-align: bottom; }
		table td { padding: 0.20em 0.75em;}
		/* ul.actions {width: 300px;
        margin-left: auto;
        margin-right: auto;}
		 */
		 
.star-rating_s {
	width:100px;
}
.star-rating_s, .star-rating_s span {
	display: inline-block;
	height: 19px;
	overflow: hidden;
	background: url(images/star.png) no-repeat;
	background-size:100px;
}
.star-rating_s span {
	background-position: left bottom;
	line-height: 0;
	vertical-align: top;
}

</style>

</head>

<body class="is-preload" onload='recall()'>
	<%
	driverDO d_do = (driverDO)session.getAttribute("d_do");
	enterpriseDO e_do = (enterpriseDO)session.getAttribute("e_do");
	String e_id = e_do.getE_id();
	memberDAO dao = new memberDAO();
	ArrayList<enter_main_viewerDO> emv_arr = dao.enter_main(e_id);
	int star = dao.star_avg(e_id);
	%>
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<a class="logo"><strong><%=e_do.getE_name() %></strong></a><!-- 업체명표시 -->
				</header>

				<!-- Banner -->
				<section id="banner">
					<div class="content">
						<div style=" background-color:transparent; padding:5px; border-bottom: 2px solid #f56a6a;;">
							<b style="font-size:40px;">평균별점&nbsp&nbsp</b>
							<div class='star-rating'>
								<span style="width: <%=star %>%"></span>
							</div>										
						</div>
					</div>
				</section>
				
				<section style="border-top:none;">
					<div class="menu">
						<article>																		
		                	<form action="#" method="post">
		                    	<table style="border-collapse: inherit;">
			                	<%for(int i=0; i<emv_arr.size(); i++){%>
									<tr style="background-color: transparent; ">
										<td rowspan="3" width="100" height="120"><!-- 가능하면 사진의 크기와 동일하게 설정-->
											<%if(emv_arr.get(i).getPhoto()==null){ %>
											<!-- DB에서 사진을 받아와 표시, null값 일경우 디폴트 프로필 표시 -->
											<span class="image"><img src="images/profile.png" width="100" height="120" alt="프로필"></span>
											<%}else{%>
											<span class="image"><img src="images/<%=emv_arr.get(i).getPhoto() %>"width="100" height="120" alt="프로필"></span>
											<%}%>
										</td>
										<td><%=emv_arr.get(i).getD_name() %>&nbsp기사님</td><!-- 기사님 성함을 받아와 표시할 곳 -->
										<td><input type="submit" value="수정" class="button fit" style="width:inherit;"></td>
									</tr>
									<tr>
										<td>
											번호&nbsp <%=emv_arr.get(i).getD_num() %>
										</td>
										<td><a href="#" class="button">삭제</a></td>
									</tr>
									<tr style="background-color: transparent; ">
										<td>
											별점&nbsp
											<%if(emv_arr.get(i).getStar_rate()==null){ %>
											아직 평가가 없어요!
											<%}else{ %>
											<div class='star-rating_s'>
												<span style="width: <%=emv_arr.get(i).getStar_rate() %>%"></span>
											</div>
											<%} %>
										</td>
										<td>
											<a href="javascript:doDisplay(<%=i %>);" class="button primary">Touch</a><br/><br/>
										</td>
									</tr>
									<tr>
										<td colspan="3">
											<div id="myDIV<%=i %>" style="display: none;">
												<div style="border: 2px solid #f56a6a; background-color:transparent; padding:20px; border-radius: 0.375em; text-align:center">
													<%if(emv_arr.get(i).getTxt_m()==null){ %>
														아직 평가가 없어요!
													<%}else{ %>
														<%=emv_arr.get(i).getTxt_m() %>
													<%} %>
												</div>
											</div>
										</td>
									</tr>
								<%} %>	
								</table>
							</form>
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
										<li><a href="main_enter.jsp">메인</a></li>
										<%if((d_do==null)&&(e_do==null)){ %>
										<li><a href="index.jsp">로그인</a></li>
										<%}else{%>
										<li><a href="logoutCon_enter.do">로그아웃</a></li>
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
		    <script src="assets/js/doDisplay.js"></script>

	</body>
</html>