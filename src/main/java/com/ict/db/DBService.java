package com.ict.db;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBService {
	static private SqlSessionFactory factory;
	static String resource = "com/ict/db/config.xml";

	// 초기화를 시켜보자
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
		}

	}

	// DAO 에서 factory를 호출할 수 있도록 만든 메서드
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
