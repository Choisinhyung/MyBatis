package com.conn.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.data.vo.EmpVO;

public class DBConn {

	public static SqlSession getSqlSession() { 
		SqlSession sess = null;
		
		String config = "resources/mybatis-config.xml"; // mybatis-config 경로 설정하기
		try {
			// session factory builder로 build(inputStream)해서 session factory를 만든다
			InputStream is = Resources.getResourceAsStream(config);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is, "development"); 
			// 위에서 만든 session factory를 가지고 session을 만들었다..!  위에서 sqlsession을 만들었으니 그대로 변수 사용하면 됨.
			sess = ssf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sess;
	}
	
	public static void main(String[] args) {
		SqlSession session = DBConn.getSqlSession();
		// selectOne, selectList, selectMap, insert, delete, update
//		String result = session.selectOne("testMapper.test");
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("id1", 100);
		data.put("id2", 110);
		List<EmpVO> result = session.selectList("testMapper.employee", data);
		System.out.println(result);
//		EmpVO result = session.selectOne("testMapper.employee");

		for(EmpVO d: result) {
			System.out.println(d.getEmployee_id() + ", " + d.getFirst_name());
		}
		
//		System.out.println(result.size()); //갯수
//		System.out.println(result.get(0).getEmployee_id() + ", " + result.get(0).getFirst_name());
	}

}
