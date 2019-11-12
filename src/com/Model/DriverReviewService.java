package com.Model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DataAccessObject.memberDAO;
import com.DataObject.reviewDO;
import com.Interface.Command;

public class DriverReviewService implements Command {
	

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDAO dao = new memberDAO();
		ArrayList<reviewDO> arr = dao.driverMyReview();
		
		request.setAttribute("arr", arr);
		return ".jsp";
		//드라이버 리뷰 출력 페이지 만들어야함
	}


}
