package com.shopping.www;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//dao
//member 테이블 (select,insert,update,delete)
@Repository("userselect")
public class user_dao {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	public String search_id(String mname, String memail) { //1명의 id 찾기
		Map<String, String> keycode=new HashMap<>();
		keycode.put("part", "1");
		keycode.put("mname", mname);
		keycode.put("memail", memail);
		return sst.selectOne("ShoppingMall.search",keycode);
	}
	
}
