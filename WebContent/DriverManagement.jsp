<%@page import="com.DataObject.enterpriseDO"%>
<%@page import="com.DataObject.driverDO"%>
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
		table, tr, th, td{vertical-align: bottom;}
		/* ul.actions {width: 300px;
        margin-left: auto;
        margin-right: auto;}
		 */
		</style>
		
	</head>
<body class="is-preload">
<!--업체가 드라이버를 관리(사원리스트 보기와 수정 삭제하기)하기 위한 페이지  ppt 21p

*변수이름은 ()안에 있어요~

필요한 데이터: 업체에 있는 드라이버(드라이버 이름(D_NAME),드라이버 사진(PHOTO))

-->


<!--1.DB에 데이터 가져오기-->
<%
	driverDO d_do = (driverDO)session.getAttribute("d_do");
	enterpriseDO e_do = (enterpriseDO)session.getAttribute("e_do"); 
	%> 
<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
			<div id="main">
				<div class="inner">
					<!-- Header -->
						<header id="header">
							<h2>사원 수정 및 삭제</h2>
						</header>
							<!-- Section -->
								<section>
									<div class="menu">
										<article>																		
											
		                                      <!--2.for문 사용하여 데이터 가져오기
		                                              (업체테이블의 사원 필드 갯수 만큼 for문 하기) 
		                                      -->
		                                   	<form action="#" method="post">
		                                      	<table style="border-collapse: inherit;">
													<tr style="background-color: transparent; ">
														<td rowspan="2" width="100" height="120"><!-- 가능하면 사진의 크기와 동일하게 설정-->
															<%//if(profile==null){ %>
															<!-- DB에서 사진을 받아와 표시, null값 일경우 디폴트 프로필 표시 -->
																<span class="image"><img src="images/profile.png" width="100" height="120" alt="프로필"></span>
															<%//}else{%>
																<!--class="image"><img src=""width="100" height="120" alt="프로필"> 기사님사진 받을 곳 -->
															<%//}%>
														</td>
														<td><% %>기사님</td><!-- 기사님 성함을 받아와 표시할 곳 -->
													</tr>
													<tr>
														<td>
															<ul class="actions">
																<li><input type="submit" value="수정" class="button fit"></li>
																<li><a href="#" class="button">삭제</a></li>
															</ul>
														</td>
													</tr>									
												</table>
											</form>								
										</article>
									</div>
								</section>
	
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