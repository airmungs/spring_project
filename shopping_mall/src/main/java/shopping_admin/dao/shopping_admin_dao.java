package shopping_admin.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_admin.model.shopping_admin_dto;

@Repository("shoppingDao")
public class shopping_admin_dao {
	@Resource(name="template2")
	private SqlSessionTemplate sst;
	
	public int shoppingInsert(shopping_admin_dto admin_dto) {
		int result=sst.insert("sailmallDB.admin_insert",admin_dto);
		return result;
	}
}
