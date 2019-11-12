package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DataAccessObject.memberDAO;
import com.DataObject.userDO;

//자동 임포트는 ctrl + shift + O(영어) 
//not servlet , it's class file

public class LoginService implements com.Interface.Command {

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String u_id = request.getParameter("u_id");
			String u_pw = request.getParameter("u_pw");
			
			memberDAO dao = new memberDAO();
			userDO u_do = dao.Login(u_id, u_pw);
			
			String nextpage = "";
			if(u_do!=null) {
				//if : 검색한 데이터가 하나일 경우	
				//while : 검색한 데이터가 여러개 일경우
							
				//로그인에 성공
				//session영역에 ID값 저장
				HttpSession session = request.getSession();
				//JSP는 내장객체로 session이 존재하지만
				//Servlet은 내장객체가 없음으로 session객체를 생성해줘야함
				session.setAttribute("u_do", u_do);		
				nextpage = "User_main.jsp";
				
			}else {
				System.out.println("로그인실패");
				nextpage = "index.jsp";
			}
			return nextpage;
		}
}