<%@page import="com.DataObject.enterpriseDO"%>
<%@page import="com.DataObject.driverDO"%>
<%@page import="com.DataObject.userDO"%>
<%@page import="com.DataAccessObject.memberDAO"%>
<%@page import="java.sql.*" %>

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
</style>

</head>

<body class="is-preload" onload='startWorker()'>

<% 

String u_id = "1"; //아이디를 받아왔다고 가정 
int b_num = 1;   //교촌치킨을 받아왔다고 가정

//String bname =memberDAO.getBusiness(b_num); 

String gps = request.getParameter("gps"); 
System.out.println(gps);

driverDO d_do = (driverDO)session.getAttribute("d_do");






   %>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">

					<a class="logo"><strong>배달위치 : 광주 북구 설죽로 214번길 100-4 </strong></a>
					<!-- 점표명 받아서 표시할 부분 -->
					</header>

				<!-- Banner -->
				<section id="banner">
					<div class="content">
						<header>
							<table style="border-collapse: inherit;">
								<tr style="background-color: transparent; font-size: 20px; padding: 5em 0 1em 0;">

								</tr>
	
							</table>
						</header>
					</div>

					<div class="image object">

						<div id="map" style="width: 100%; height:500px"> </div> 

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2b069a55b436aecc3dc43b2467f6bfa2&libraries=services"></script>

<script>

//지도

var arr = new Array(); 

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 

mapOption = {
    center: new kakao.maps.LatLng(0, 0), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//배달위치 
//주소로 좌표를 검색합니다
  
navigator.geolocation.watchPosition(function(pos) {

	
	 var coords = new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude);

	    arr[0] = pos.coords.latitude;
		arr[1] = pos.coords.longitude; 

	 setTimeout(function() {
	 	
	 	location.reload(); 
     
	 	var form = document.createElement('form');
	  form.setAttribute("charset", "UTF-8");
       form.setAttribute("method", "GET");  //Post 방식
       form.setAttribute("action", "Driver_navi.jsp"); //요청 보낼 주소
	 	
       var hiddenField = document.createElement("input");

       hiddenField.setAttribute("type", "hidden");

       hiddenField.setAttribute("name", "gps");

       hiddenField.setAttribute("value", arr[0] + ',' + arr[1]);

       form.appendChild(hiddenField);

       document.body.appendChild(form);

       form.submit();
       
	 }
	 	
	 	, 10000); 
		
	 var marker = new kakao.maps.Marker({
	        map: map,
	        position: coords
	    });
	    
	 
	
	// 인포윈도우로 장소에 대한 설명을 표시합니다
    
	var infowindow = new kakao.maps.InfoWindow({
   
    	content: '<div style="width:150px;text-align:center;padding:6px 0;">배달중</div>'
    });
	
	infowindow.open(map, marker);
    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);

});  


var geocoder = new kakao.maps.services.Geocoder();

//도착지
//주소로 좌표를 검색합니다
geocoder.addressSearch('광주 북구 설죽로 214번길 100-4', function(result, status) {
// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {
	
    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });
    // 인포윈도우로 장소에 대한 설명을 표시합니다
    
	 arr[2] = result[0].y;  
	 arr[3] = result[0].x;
	 
	 
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">도착지</div>'
    });
    infowindow.open(map, marker);
    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
   // map.setCenter(coords);

 }



 getC(arr); 
 

}); 


function getC(arr) {
//좌표 거리 계산 
c = Math.sqrt( (arr[1]-arr[3])*(arr[1]-arr[3]) + (arr[0]-arr[2])*(arr[0] -arr[2]));
c = c*100000; 
c = c.toFixed(0); 
document.getElementById('getMeter').innerHTML = '남은 거리:' + c + 'm'; 


}




</script>
					
					
					</div>
				</section>
				<!-- Section -->
			
			
		
		
		<!-- Sidebar -->
		<div id="sidebar">
			<div class="inner">

				<!-- Search 검색기능 일단 무시하기로 합니다-->

				<!-- <section id="search" class="alt">
									<form method="post" action="#">
										<input type="text" name="query" id="query" placeholder="Search" />
									</form>
								</section> -->
				<!-- Menu -->
				<nav id="menu">
					<header class="major">
						<h2>Menu</h2>
					</header>
					<ul>

						<li><a href="index.jsp">메인</a></li>
						<li><a href="Driver_navi.jsp">위치 확인</a></li>
						<li><a href="review_user.jsp">나의 리뷰</a></li>
						
						
						<!-- 환경설정 구현 안함-->

						<!-- <li>
											<span class="opener">Submenu</span>
											<ul>
												<li><a href="#">Lorem Dolor</a></li>
												<li><a href="#">Ipsum Adipiscing</a></li>
												<li><a href="#">Tempus Magna</a></li>
												<li><a href="#">Feugiat Veroeros</a></li>

											</ul> 참고용으로 남겨둠-->

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
						<li class="icon solid fa-home">광주광역시 북구 용봉동 77<br /> 전남대학교
							생활관 9A동

						</li>
					</ul>
				</section>
				<!-- Footer -->
				<footer id="footer">
					<p class="copyright">
						&copy; Untitled. All rights reserved. Demo Images: <a
							href="https://unsplash.com">Unsplash</a>. Design: <a
							href="https://html5up.net">HTML5 UP</a>.
					</p>
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