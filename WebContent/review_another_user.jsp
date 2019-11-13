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
<!--��ü�� ����̹��� ����(�������Ʈ ����� ���� �����ϱ�)�ϱ� ���� ������  ppt 21p

*�����̸��� ()�ȿ� �־��~

�ʿ��� ������: ��ü�� �ִ� ����̹�(����̹� �̸�(D_NAME),����̹� ����(PHOTO))

-->


<!--1.DB�� ������ ��������-->
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
							<h2>�ٸ� ���Ե��� ����</h2>
						</header>
							<!-- Section -->
								<section>
									<div class="menu">
										<article>																		
		                                      <!--2.for�� ���- ���� �ۼ��� ���� �޾ƿ;���-->
		                                   		<a style="font-size:20px;"><%=rv_arr.get(0).getD_name() %>����</a>
		                                   		<%for(int i=0; i<rv_arr.size(); i++){%>
		                                      	<table style="border-collapse: inherit;padding:10px;">
													<tr>
														<%String date[] = rv_arr.get(i).getR_date().split(" "); %>
														<td>��¥ <%=date[0]%></td>
														<td>
															<div class='star-rating'>
																<span style="width: <%=rv_arr.get(i).getStar_rate() %>%"></span>
																<!-- ���� ��ũ��Ʋ������ ���� ���� �޾ƿ� ���� "width:<% %>%"-->
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
										<li><a href="index.jsp">����</a></li>
										<li><a href="login.jsp">�α���</a></li>
										<li><a href="User_usageDetails.jsp">�̿볻��</a></li>
										<li><a href="review_user.jsp">���� �ۼ��� ����</a></li>
										<li><a href="#">ȯ�漳��</a></li>
										<!-- ȯ�漳�� �������� -->
										<!-- <li>
									</ul>
								</nav>
								
							<!-- Section -->
								<section>
									<header class="major">
										<h2>Get in touch</h2>
									</header>
									<p>���� �߻��� ���� ����ó�� ���� �ٶ��ϴ�.</p>
									<ul class="contact">
										<li class="icon solid fa-envelope"><a href="#">genie141930@gmail.com</a></li>
										<li class="icon solid fa-phone">(010) 5091-0984</li>
										<li class="icon solid fa-home">���ֱ����� �ϱ� ����� 77<br />
										�������б� ��Ȱ�� 9A��</li>
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