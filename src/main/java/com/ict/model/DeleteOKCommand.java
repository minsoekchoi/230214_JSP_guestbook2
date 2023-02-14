package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;

public class DeleteOKCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		int result = DAO.getDelete(idx);
		
		if(result>0) {
			// 이미지 삭제를 하기 (숙제)
			return "MyController?cmd=list";
		}else {
			return "view/error.jsp";
		}
	}
}
