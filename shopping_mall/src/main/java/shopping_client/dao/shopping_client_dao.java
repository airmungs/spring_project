package shopping_client.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_client.dto.shopping_clients_dto;

@Repository("clientDao")
public class shopping_client_dao {
	@Resource(name="template")
	private SqlSessionTemplate sqlTemplate;
	
	// 쇼핑몰 회원가입
    public int insertClient(shopping_clients_dto clientDTO) {
        return sqlTemplate.insert("ShoppingMall.joinOk", clientDTO);
    }
    // 아이디 중복 체크
    public shopping_clients_dto selectClientById(String userId) {
        return sqlTemplate.selectOne("ShoppingMall.clientIdcheck", userId);
    }
}
