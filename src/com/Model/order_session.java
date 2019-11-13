package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DataAccessObject.memberDAO;
import com.DataObject.orderDO;
import com.Interface.Command;

public class order_session implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String u_id = request.getParameter("u_id");
		
		memberDAO dao = new memberDAO();
		orderDO o_do = dao.get_order(u_id);
		
		String nextpage = "";
		if(o_do!=null) {
			//if : 검색한 데이터가 하나일 경우	
			//while : 검색한 데이터가 여러개 일경우
						
			//로그인에 성공
			//session영역에 ID값 저장
			HttpSession session = request.getSession();
			//JSP는 내장객체로 session이 존재하지만
			//Servlet은 내장객체가 없음으로 session객체를 생성해줘야함
			session.setAttribute("o_do", o_do);		
			nextpage = "User_main.jsp";
		}else {
			System.out.println("실패");
		}
		return nextpage;
	}

}
