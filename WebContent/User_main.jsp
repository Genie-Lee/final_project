<%@page import="com.DataObject.orderDO"%>
<%@page import="com.DataObject.enterpriseDO"%>
<%@page import="com.DataObject.driverDO"%>
<%@page import="com.DataObject.userDO"%>
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


.layer{position:absolute;top:0;left:0;width:100%;height:100%;text-align:center;top: 100px;}
.layer .content{display:inline-block;vertical-align:middle}
.layer .blank{display:inline-block;width:0;height:100%;vertical-align:middle}

</style>

</head>

<body class="is-preload" onload='recall()'>

<% 

userDO u_do = (userDO)session.getAttribute("u_do");
String u_id = u_do.getU_id();
orderDO o_do = (orderDO)session.getAttribute("o_do");

if(o_do==null){ %>
<div class="layer">
	<span class="content">
		<img src="images/main_user.png" width="auto" height="200px" alt="메인사진">
	<span class="blank"></span>
	</span>
</div>
<div class="layer">
	<span class="content">
		<a href="random_orderCon.do?u_id=<%=u_id %>" class="button primary"  style="text-align:center; line-height: 100px; width:300px; height:100px; font-family:none; font-size: 2em;">주문하기</a>
	</span>
	<span class="blank"></span>
</div>
<%}else{
int b_num = o_do.getB_num();
String bname =memberDAO.getBusiness(b_num); 

driverDO d_do = memberDAO.getDriver(u_id , b_num); 


/* int star_avg = memberDAO.getStar(d_do.getD_id());   */
/*
System.out.println(arr[0]);
System.out.println(arr[1]);
System.out.println(arr[2]);
 */

%>

	<!-- Wrapper -->
	<div id="wrapper">
	
		<!-- Main -->
		<div id="main">
			<div class="inner">
				
				<!-- Header -->
				<header id="header">
					
					<a class="logo"><strong>점포명 : <%=bname%></strong></a>
					
				</header>

				<!-- Banner -->
				<section id="banner">
					<div class="content">
					
						<header>
							<table style="border-collapse: inherit;">
								<tr style="background-color: transparent; font-size: 20px; padding: 5em 0 1em 0;">

									<td><input type='button' value='위치확인하기' onclick ='recall()'></td>
									<td id = "getMeter"></td>
								</tr>
	
							</table>
						</header>
					</div>

					<div class="image object">

						<div id="map" style="width: 100%; height:250px"> </div> 

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2b069a55b436aecc3dc43b2467f6bfa2&libraries=services"></script>

<script>

//지도


var check = 1 ; 
	
function recall() { 

var startX = 0;
var startY = 0; 
var endX = 0; 
var endY = 0; 

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(0, 0), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//도착지 
//주소로 좌표를 검색합니다
geocoder.addressSearch('광주 북구 설죽로 214번길 100-5', function(result, status) {
// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {
	 
    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });
    
    endY = result[0].y; 
	endX = result[0].x; 
    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">도착지</div>'
    });
    infowindow.open(map, marker);
    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 
});    


//출발지 
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
     startY = result[0].y; 
	 startX = result[0].x; 
    
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">출발지</div>'
    });
    infowindow.open(map, marker);
    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
   // map.setCenter(coords);
}

//좌표 거리 계산 
c = Math.sqrt( (endY-startY)*(endY-startY) + (endX -startX)*(endX -startX));
c = c*100000; 
c = c.toFixed(0); 
document.getElementById('getMeter').innerHTML = '남은 거리:' + c + 'm'; 

//c는 출발지와 도착지 사이의 직선거리 //200m 이하이면 알림 보내기 


var Notification = window.Notification ||  window.mozNotification || window.webkitNotification;

Notification.requestPermission(function (permission) {
	
	if(permission == 'granted') {
		 
		 if(c<=200 && check == 1) {
			 
			 check = 0 ;
		
		 }
	
	}
	
});

var title = 'Simple Title';
var options = {
  body: 'Simple piece of body text.\nSecond line of body text :)'
};
registration.showNotification(title, options);


});    

}


</script>
					</div>
				</section>
				<!-- Section -->
				<section>
					<header class="major">

						<a href="review_another_user.jsp" style="color:black; font-size:20px">
							
							<%=d_do.getD_name()%> 기사님
						</a>
						<!-- 기사님 성함을 받아와 표시할 곳 -->
					</header>
					<div class="post">
						<article>
							<table style="border-collapse: inherit; color:black;">
								<tr style="background-color: transparent; ">
									<td rowspan="2" width="100" height="120"><!-- 가능하면 사진의 크기와 동일하게 설정-->
										<%if(d_do.getPhoto()==null){ %>
										<!-- DB에서 사진을 받아와 표시, null값 일경우 디폴트 프로필 표시 -->
										<span class="image"><img src="images/profile.png" width="100" height="120" alt="프로필"></span>
										<%}else{%>
										<span class="image"><img src="images/<%=d_do.getPhoto() %>"width="100" height="120" alt="프로필"></span>
										<%}%>
									</td>
									<td>소속 : <%=d_do.getE_id()%></td><!-- DO에 이름값이 없지만 아이디와 같이 String이기 때문에 입력함 -->
								</tr>
								<tr>
									<td>연락처 : <%=d_do.getD_num() %></td>
								</tr>
								<tr style="background-color: transparent; ">
									<td colspan="2">
										<div class="wrap-star" style="color:black;">
											평균점수
											<br>
											<div class='star-rating'>
											<%// System.out.println(star_avg); %>
												<span style="width: <%//star_avg%>%"></span>
												<!-- 추후 스크립틀릿으로 별점 정보 받아올 예정 "width:%"-->
											</div>
										</div>
									</td>
								</tr>								
							</table>							

						</article>
					</div>
				</section>
			</div>
		</div>
<%} %>
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
						<li><a href="User_main.jsp" >메인</a></li>
						<li><a href="User_usageDetails.jsp" >이용내역</a></li>
						<li><a href="review_user.jsp">내가 작성한 리뷰</a></li>

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