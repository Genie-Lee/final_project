package com.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.DataAccessObject.*;
import com.Model.JoinService_driver;
import com.Model.JoinService_enter;
import com.Model.LoginService;
import com.Model.LoginService_driver;
import com.Model.LoginService_enter;
import com.Model.LogoutSurvice_driver;
import com.Model.LogoutSurvice_enter;
import com.Model.ReviewRegistration;
import com.Model.order_session;

/**
 * Servlet implementation class front
 */
@WebServlet("*.do")
public class Front extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("FrontCon 호출");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		System.out.println("path : "+path);

		String front = uri.substring(path.length()+1);
		System.out.println(front);
		
		String nextpage = "";
	
		if (front.equals("login.do")) {
			LoginService login = new LoginService();
			nextpage = login.execute(request,response);	
		}else if (front.equals("login_driver.do")) {
			LoginService_driver login = new LoginService_driver();
			nextpage = login.execute(request,response);	
		}else if (front.equals("login_enter.do")) {
			LoginService_enter login = new LoginService_enter();
			nextpage = login.execute(request,response);	
		}else if(front.equals("Join_driver.do")) {
			JoinService_driver join = new JoinService_driver();
			nextpage = join.execute(request, response);
		}else if(front.equals("Join_enter.do")) {
			JoinService_enter join = new JoinService_enter();
			nextpage = join.execute(request, response);
		}else if(front.equals("LogoutCon_driver.do")) {
			LogoutSurvice_driver logout = new LogoutSurvice_driver();
			nextpage = logout.execute(request, response);
		}else if(front.equals("LogoutCon_enter.do")) {
			LogoutSurvice_enter logout = new LogoutSurvice_enter();
			nextpage = logout.execute(request, response);
		}else if(front.equals("ReviewRegistrationCon.do")) {
			ReviewRegistration review = new ReviewRegistration();
			nextpage = review.execute(request, response);
		}else if(front.equals("random_orderCon.do")) {
			order_session order = new order_session();
			nextpage = order.execute(request, response);
		}

    
		RequestDispatcher dis = request.getRequestDispatcher(nextpage);
		dis.forward(request, response);	
	}

		
}