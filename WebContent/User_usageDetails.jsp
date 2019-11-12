<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.DataAccessObject.memberDAO"%>
<%@page import="com.DataObject.UserUsageDO"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
      <title> DeliverySystem</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
      <link rel="stylesheet" href="assets/css/main.css" />
      
      <style>
      .star-rating { width:205px; }
      .star-rating,.star-rating span { display:inline-block; height:39px; overflow:hidden; background:url(star.png)no-repeat; }
      .star-rating span{ background-position:left bottom; line-height:0; vertical-align:top; }
      /* ul.actions {width: 300px;
        margin-left: auto;
        margin-right: auto;}
       */
      </style>
      
   </head>
<body class="is-preload">

<%
   
 String u_id = request.getParameter("u_id");  // 확인
 
 
 %> 
      <!-- Wrapper -->
         <div id="wrapper">

            <!-- Main -->
               <div id="main">
                  <div class="inner">
                     <!-- Header -->
                        <header id="header">
                           <h2>이용내역</h2>
                        </header>
                           <!-- Section -->
                              <section>
                                 <div class="menu">
                                    
                                 <article>                                                      
                                 
                                   <%
                                   
							memberDAO dao = new memberDAO();
							ArrayList<UserUsageDO> usage_arr = dao.getUserUsage(u_id);
							
							for(int i=0; i<usage_arr.size(); i++){
						
								String date = usage_arr.get(i).getDate(); 
								String date_arr[] = date.split(" "); 
						%>
							
							
                                 
                                       <form action="#" method="post">
                                          <ul class="actions">
                                             <li> 배달 일자 : <%=date_arr[0] %> </li>
                                          </ul>
                                          
                                          <ul class="actions">
                                             <li>기사님 : <%= usage_arr.get(i).getD_name()%> </li>
                                          </ul>
                                          
                                          <ul class="actions">
                                             
                                            
                                          
                                             <li>배달 업소 : <%= usage_arr.get(i).getB_name()%> </li>
                                          </ul>
                                          
                                          <ul class="actions">
                                             <li>주문번호 : <%= usage_arr.get(i).getOrder_num()%> </li>
                                          </ul>
                                          
                           
                               <ul class="actions">
                                          <!--3.href로 리뷰작성 페이지로 연결하기-->
                                             <li><a href="review.jsp?d_name = <%=usage_arr.get(i).getD_name() %>" class="button">리뷰작성</a></li>
                                            <!--  드라이버 이름으로 확인--> 
                                          
                                          </ul>  
                                       </form>   
                        </article>
                                       <% }; %>
                                                                     
                                     </article>
                                       
                                          
                                          
                                 
                                     
                                 </div>
                              </section>
                        
                              <section>
                                 
                           
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
                              <li><a href="User_main.jsp">메인</a></li>
                              <li><a href="User_usageDetails.jsp">이용내역</a></li>
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