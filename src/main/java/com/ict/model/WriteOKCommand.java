package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteOKCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 실제 저장  되는 위치 
			String path = request.getServletContext().getRealPath("upload");
			
			
			MultipartRequest mr = 
					//  ↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
					// (HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
					// 아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
				new MultipartRequest(request, path, 100*1024*1024, 
						"utf-8", new DefaultFileRenamePolicy());
			//Vo 안에 저장 되어 있는 값들을 가져오자
			VO vo = new VO();
			vo.setName(mr.getParameter("name")); //이름 파라미터 값으로 가져오자
			vo.setSubject(mr.getParameter("subject"));//제목 파라미터 값으로 가져오자
			vo.setContent(mr.getParameter("content"));//내용 파라미터 값으로 가져오자
			vo.setEmail(mr.getParameter("email"));//이메일 파라미터 값으로 가져오자
			vo.setPwd(mr.getParameter("pwd"));// 비밀번호 파라미터값으로 가져오자 
			
			// 첨부파일이 있을 때와 없을 때를 구분하자.
			// 첨부파일 이 있을경우 
			if(mr.getFile("f_name") != null) {
			// vo. set F_name을 mr 겟 파일시스템이름을 가져오자 f_name 으로
				vo.setF_name(mr.getFilesystemName("f_name"));
			}else {
				//아닐경우  없음.
				vo.setF_name("");
			}
			// result 값이 0보다 크면  insert 를 하고 컨트롤러 cmd list 로 가겠다 
			int result = DAO.getInsert(vo);
			if(result>0) {
				return "MyController?cmd=list";
			}
			// 아닐경우 에러 처리 
			return "view/error.jsp";
		} catch (Exception e) {
			return "view/error.jsp";
		}
	}
}