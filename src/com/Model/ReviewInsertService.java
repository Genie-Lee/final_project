package com.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DataAccessObject.memberDAO;
import com.DataObject.reviewDO;
import com.Interface.Command;

public class ReviewInsertService implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		
		
		String u_id = request.getParameter("u_id");
		int order_num = (int) request.getAttribute("order_num");
		int r_date = (int) request.getAttribute("r_date");
		float star_rate = (float) request.getAttribute("star_rate");
		String post = request.getParameter("post");

		memberDAO dao = new memberDAO();
		
		reviewDO vo = new reviewDO(u_id, order_num, r_date, star_rate, post);
		// post_num 일련번호 자동 추가로 변경
		
		int cnt = dao.reviewInsert(vo);
		
		
		if(cnt>0) {
			nextPage = ".jsp";
			// 리뷰 출력 페이지
		}
		return nextPage;
	}

}
