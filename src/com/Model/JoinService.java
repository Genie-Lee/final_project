package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DataAccessObject.memberDAO;
import com.DataObject.userDO;
import com.Interface.Command;

public class JoinService implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String u_id = request.getParameter("u_id");
		String u_pw = request.getParameter("u_pw");
		String u_name = request.getParameter("u_name");
		int u_num = Integer.parseInt(request.getParameter("u_num"));
		
		userDO u_do = new userDO(u_id, u_pw, u_name, u_num);
		
		memberDAO dao = new memberDAO();
		
		
		int cnt = dao.Join(u_do);
		
		String nextpage = "";
		
		if(cnt>0) {
			request.setAttribute("u_do", u_do);
			nextpage="login.jsp";
			
			
		//	response.sendRedirect("main.jsp");
		}else {
			System.out.println("가입실패");
		}
		return nextpage;
	}

}
