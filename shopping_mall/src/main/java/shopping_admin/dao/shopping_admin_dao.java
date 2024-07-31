package shopping_admin.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_admin.model.shopping_admin_dto;

@Repository("shoppingDao")
public class shopping_admin_dao {
	@Resource(name="template2")
	private SqlSessionTemplate sqlTemplate;
	
	// 관리자 등록
    public int insert_admin(shopping_admin_dto adminDTO) {
        return sqlTemplate.insert("sailmallDB.admin_insert", adminDTO);
    }

    // 아이디 중복 체크
    public shopping_admin_dto selectAdminById(String adid) {
        return sqlTemplate.selectOne("sailmallDB.admin_idcheck", adid);
    }
}
