package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class DeleteCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 일처리 : idx를 받아서 비밀번호 가져오기  onelist  가져오고 요청하자 파라미터값을 idx
		VO vo = DAO.getOneList(request.getParameter("idx"));
		
		// idx 와 비밀번호 저장  비밀번호가 있어야 삭제를 하니까!
		request.setAttribute("vo", vo);
		// delete.jsp 로 넘어가자!
		return "view/delete.jsp";
	}
}