package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateOKCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 혹시 모르니까 try catch 를 해주자
		try {
			
			//↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
			// (HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
			// 아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)		
			String path = request.getServletContext().getRealPath("upload");
			MultipartRequest mr = 
					new MultipartRequest(request, path, 100*1024*1024,
							"utf-8", new DefaultFileRenamePolicy());
			// vo에 저장되어있는 값들을 파라미터값으로 가져오자!
			VO vo = new VO();
			vo.setIdx(mr.getParameter("idx"));
			vo.setName(mr.getParameter("name"));
			vo.setSubject(mr.getParameter("subject"));
			vo.setContent(mr.getParameter("content"));
			vo.setEmail(mr.getParameter("email"));
			vo.setPwd(mr.getParameter("pwd"));
			
			//이전파일 !
			String old_f_name = mr.getParameter("old_f_name");
			
			// 첨부파일이 null 경우  이전파일로 대치하자 
			if(mr.getFile("f_name") == null) {
				vo.setF_name(old_f_name);
				//아닐경우 f_name 을 가져오자 mr 에서 
			}else {
				vo.setF_name(mr.getFilesystemName("f_name"));
			}
			//result 값이 0 보다크면 업데이트 하자 그러고 상세보기로 돌아가고
			//getldx  해서 추가해주자 아닐경우 에러문으로 가자 
			
			int result = DAO.getUpdate(vo);
			
			if(result>0) {
				return "MyController?cmd=onelist&idx="+vo.getIdx();
			}else {
				return "view/error.jsp";
			}
		} catch (Exception e) {
			return "view/error.jsp";
		}
		
	}
}
