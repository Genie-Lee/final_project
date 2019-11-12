package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DataAccessObject.memberDAO;
import com.DataObject.enterpriseDO;
import com.Interface.Command;

public class JoinService_enter implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String e_id = request.getParameter("e_id");
		String e_pw = request.getParameter("e_pw");
		String e_name = request.getParameter("e_name");
		
		enterpriseDO e_do = new enterpriseDO(e_id, e_pw, e_name);
		
		memberDAO dao = new memberDAO();
		
		
		int cnt = dao.Join_enter(e_do);
		
		String nextpage = "";
		
		if(cnt>0) {
			request.setAttribute("e_do", e_do);
			nextpage="index.jsp";
			
			
		//	response.sendRedirect("main.jsp");
		}else {
			System.out.println("가입실패");
		}
		return nextpage;
	}

}
