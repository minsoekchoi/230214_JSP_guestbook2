package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class OneListCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//  idx 가 프라이머리키기 떄문에 그기준으로 한다 .
		String idx = request.getParameter("idx");
		//리스트를 상세보기 idx 기본키!
		VO vo = DAO.getOneList(idx);
		
		//저장요청한다 vo 값을 
		request.setAttribute("vo", vo);
		
		// vo  값이 있으면  view onelist.jsp 로 가라! 
		// 아니면 error.jsp 로 넘어가자!
		if(vo != null) {
			return "view/onelist.jsp";
		}else {
			return "view/error.jsp";
		}
		
	}
}