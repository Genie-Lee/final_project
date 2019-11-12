package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DataAccessObject.memberDAO;
import com.DataObject.reviewDO;
import com.Interface.Command;

public class ReviewRegistration implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String order_num = request.getParameter("order_num");
		String star_rate = request.getParameter("star-input");
		String post = request.getParameter("post");
		
		reviewDO r_do = new reviewDO(null, order_num, star_rate, post);		
		memberDAO dao = new memberDAO();
		
		
		int cnt = dao.reviewInsert(r_do);
		
		String nextpage = "";
		
		if(cnt>0) {
			request.setAttribute("r_do", r_do);
			nextpage="User_usageDetails.jsp";
			
			
		//	response.sendRedirect("main.jsp");
		}else {
			System.out.println("입력실패");
		}
		return nextpage;
	}

}
