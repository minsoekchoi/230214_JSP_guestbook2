package com.ict.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	// 실제 사용하는 클래스는 SqlSession이래
	private static SqlSession ss;

	// 싱글턴 패턴으로 만들어보자(동기화 처리 해야하니까)
	// 어제 동기화와 비동기화에 대해서 배웠지 복습하고
	// 한번 만들어진 객체를 재사용하게 하는 방식이래

	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// DB 처리하는 메서드들

	// list
	public static List<VO> getList() {
		List<VO> list = getSession().selectList("guestbook2.list");
		return list;
	}

	// 삽입
	public static int getInsert(VO vo) {
		int result = getSession().insert("guestbook2.insert", vo);
		ss.commit();
		return result;
	}

	// 상세보기
	public static VO getOneList(String idx) {
		VO vo = getSession().selectOne("guestbook2.onelist", idx);
		return vo;
	}

	// 삭제
	public static int getDelete(String idx) {
		int result = getSession().delete("guestbook2.delete", idx);
		ss.commit();
		return result;
	}
	
	// update
	public static int getUpdate(VO vo) {
		int result = getSession().update("guestbook2.update", vo);
		ss.commit();
		return result;
	}
}
