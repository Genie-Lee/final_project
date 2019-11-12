package com.Model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DataAccessObject.memberDAO;
import com.Interface.Command;

public class UserReviewServise implements Command {
	

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO dao = new memberDAO();
		ArrayList<reviewDO> arr = dao.userMyReview();
		
		request.setAttribute("arr", arr);
		return "#.jsp";
		//업체 메인 페이지 만들어야함
	}


}