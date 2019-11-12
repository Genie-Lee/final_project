<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title> DeliverySystem</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		
	</head>
<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">
							<!-- Header -->
								<header id="header">
									<h2>고객용 회원가입</h2>
								</header>
									<!-- Section -->
										<section>
											<div class="menu">
												<article>																		
													<form action="Join.do" method="post">
														<ul class="actions">
															<li><input type="text" name="u_id" placeholder="ID를 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="password" name="u_pw" placeholder="PW를 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="text" name="u_name" placeholder="이름을 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="text" name="u_num" placeholder="전화번호를 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="submit" value="join us" class="button fit" style="text-align:center; width:150px; height:50px;"></li>
															<li><a href="javascript:history.back()" class="button"  style="text-align:center; line-height: 50px; width:150px; height:50px;">CANCEL</a></li> <!-- 뒤로가기 -->
														</ul>									
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