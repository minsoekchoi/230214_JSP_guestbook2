package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class ListCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 전체 목록 가져오기  
		//  DAO에 있는리스트를 가져오자 
		List<VO> list =  DAO.getList();
		
		//그리고 저장요청을하자 list를
		request.setAttribute("list", list);
		
		// 클라이언트의 갈 곳
		return "view/list.jsp";
	}
}
