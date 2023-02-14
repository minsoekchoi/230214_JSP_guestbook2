package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class UpdateCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 일처리 
		VO vo = DAO.getOneList(request.getParameter("idx"));
		
		request.setAttribute("vo", vo);
		
		return "view/update.jsp";
	}
}
