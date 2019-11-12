package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DataAccessObject.memberDAO;
import com.DataObject.driverDO;

//자동 임포트는 ctrl + shift + O(영어) 
//not servlet , it's class file

public class LoginService_driver implements com.Interface.Command {

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String d_id = request.getParameter("d_id");
			String d_pw = request.getParameter("d_pw");
			
			memberDAO dao = new memberDAO();
			driverDO d_do = dao.Login_driver(d_id,d_pw);
			
			String nextpage = "";

			if(d_do!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("d_do", d_do);		
				
				//로그인 알림
				System.out.println("로그인 완료"); 
				
				nextpage = "DriverMain.jsp";
				
			}else {
		
				nextpage = "index.jsp";
			}
			return nextpage;
		}
}