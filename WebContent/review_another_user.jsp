<%@page import="com.DataObject.orderDO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.DataAccessObject.memberDAO"%>
<%@page import="com.DataObject.userDO"%>
<%@page import="com.DataObject.review_viewerDO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title> DeliverySystem</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
		
<style>
table, tr, th, td{vertical-align: bottom;}
/* ul.actions {width: 300px;
margin-left: auto;
margin-right: auto;}
 */
.star-rating {
	width:100px;
}
.star-rating, .star-rating span {
	display: inline-block;
	height: 19px;
	overflow: hidden;
	background: url(images/star.png) no-repeat;
	background-size:100px;
}
.star-rating span {
	background-position: left bottom;
	line-height: 0;
	vertical-align: top;
}
</style>
		
	</head>
<body class="is-preload">
<!--업체가 드라이버를 관리(사원리스트 보기와 수정 삭제하기)하기 위한 페이지  ppt 21p

*변수이름은 ()안에 있어요~

필요한 데이터: 업체에 있는 드라이버(드라이버 이름(D_NAME),드라이버 사진(PHOTO))

-->


<!--1.DB에 데이터 가져오기-->
<%
userDO u_do = (userDO)session.getAttribute("u_do");
orderDO o_do = (orderDO)session.getAttribute("o_do");
String u_id = u_do.getU_id();
memberDAO dao = new memberDAO();
ArrayList<review_viewerDO> rv_arr= dao.another_user_review(o_do.getD_id());
 %> 
<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
			<div id="main">
				<div class="inner">
					<!-- Header -->
						<header id="header">
							<h2>다른 고객님들의 리뷰</h2>
						</header>
							<!-- Section -->
								<section>
									<div class="menu">
										<article>																		
		                                      <!--2.for문 사용- 내가 작성한 리뷰 받아와야함-->
		                                   		<a style="font-size:20px;"><%=rv_arr.get(0).getD_name() %>기사님</a>
		                                   		<%for(int i=0; i<rv_arr.size(); i++){%>
		                                      	<table style="border-collapse: inherit;padding:10px;">
													<tr>
														<%String date[] = rv_arr.get(i).getR_date().split(" "); %>
														<td>날짜 <%=date[0]%></td>
														<td>
															<div class='star-rating'>
																<span style="width: <%=rv_arr.get(i).getStar_rate() %>%"></span>
																<!-- 추후 스크립틀릿으로 별점 정보 받아올 예정 "width:<% %>%"-->
															</div>
														</td>
													</tr>								
												</table>
												<div style="border: 2px solid #f56a6a; background-color:transparent; padding:20px; border-radius: 0.375em; text-align:center">
													<%=rv_arr.get(i).getPost() %>
												</div>
												<%} %>	
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
										<li><a href="index.jsp">메인</a></li>
										<li><a href="login.jsp">로그인</a></li>
										<li><a href="User_usageDetails.jsp">이용내역</a></li>
										<li><a href="review_user.jsp">내가 작성한 리뷰</a></li>
										<li><a href="#">환경설정</a></li>
										<!-- 환경설정 구현예정 -->
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