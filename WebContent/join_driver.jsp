<%@page import="com.DataObject.enterpriseDO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.DataAccessObject.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title> DeliverySystem</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		
		<style>
		.filebox input[type="file"] {
  		  position: absolute;
   		 width: 1px;
   		 height: 1px;
  		  padding: 0;
  		  margin: -1px;
 		   overflow: hidden;
		    clip:rect(0,0,0,0);
		    border: 0;
		}

		.filebox label {
    	 display: inline-block;
   		 padding: .9em 1em;
   		 color: #f56a6a;
    	 font-size: inherit;
 		 line-height: normal;
  		 vertical-align: middle;
   		 background-color: #fdfdfd;
  		  cursor: pointer;
  		  border: 1px solid #ebebeb;
  		  border-bottom-color: #e2e2e2;
  		  border-radius: .25em;
  		  margin: 0;
		}

		/* named upload */
		.filebox .upload-name {
  		 display: inline-block;
   		 font-size: inherit;
	     font-family: inherit;
	     line-height: normal;
 		 vertical-align: middle;
  		 background-color: #ffffff;
  		 border: solid 1px rgba(210, 215, 217, 0.75);
  	   	 border-bottom-color: #e2e2e2;
 		 border-radius: 0.375em;
 		 -webkit-appearance: none;
		}

		.filebox.bs3-primary label {
		 -webkit-appearance: none;
   		 transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
   		 background-color: transparent;
   		 border-radius: 0.375em;
	    border: 0;
	    box-shadow: inset 0 0 0 2px #f56a6a;
	    color: #f56a6a !important;
	    cursor: pointer;
	    display: inline-block;
	    font-family: "Roboto Slab", serif;
	    font-size: 0.8em;
	    font-weight: 700;
	    height: 3.5em;
	    letter-spacing: 0.075em;
	    line-height: 3.5em;
	    padding: 0 2.25em;
	    text-align: center;
	    text-decoration: none;
	    text-transform: uppercase;
	    white-space: nowrap;
		}
		.filebox.bs3-primary label:hover {
			background-color: rgba(245, 106, 106, 0.05);
		}
		.filebox.bs3-primary label:active {
			background-color: rgba(245, 106, 106, 0.15);
		}
		
		</style>
		
	</head>
<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">
							<!-- Header -->
								<header id="header">
									<h2>드라이버용 회원가입</h2>
								</header>
									<!-- Section -->
										<section>
											<div class="menu">
												<article>																		
													<form action="Join_driver.do" method="post" enctype="multipart/form-data"><!--파일을 보낼 때는 바이너리 코드로 보내야하기 때문에 enc-type속성 지정 -->
														<ul class="actions">
															<li><input type="text" name="d_id" placeholder="ID를 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="password" name="d_pw" placeholder="PW를 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="text" name="d_name" placeholder="이름을 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions">
															<li><input type="text" name="d_num" placeholder="전화번호를 입력하세요" style="text-align:center; width:313px; height:50px;"></li>
														</ul>
														<ul class="actions" style="padding: 0 1em;">
															<select name="e_id" style="text-align:center; width:313px; height:50px;">
															  <option value='' selected>업체선택</option>
															  <%
															  	memberDAO dao = new memberDAO();
															  	ArrayList<enterpriseDO> en_arr = dao.getEnter();
															  	
															  	for(int i=0; i<en_arr.size(); i++){
															  %>
															  <option value=<%=en_arr.get(i).getE_id() %>><%=en_arr.get(i).getE_name() %></option>
															  <%} %>
															</select>	
														</ul>
														<ul class="filebox bs3-primary" style="padding-left: 0em;">
															<input class="upload-name" value="사진선택" disabled="disabled" style="color:#9fa3a6; text-align:center; width:210px; height:50px;">
                           									<label for="ex_filename" style="text-align:center; width:100px; height:50px; line-height: 50px;">UPLOAD</label> 
                         									<input type="file" id="ex_filename" class="upload-hidden" name="photo">
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