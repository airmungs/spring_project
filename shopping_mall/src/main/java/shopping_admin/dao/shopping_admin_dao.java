package shopping_admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_admin.dto.shopping_admin_dto;
import shopping_admin.dto.shopping_cate_dto;
import shopping_admin.dto.shopping_product_dto;
import shopping_admin.dto.shopping_siteinfo_dto;

@Repository("shoppingDao")
public class shopping_admin_dao {
	@Resource(name="template2")
	private SqlSessionTemplate sqlTemplate;
	
	//상품 리스트 페이징
	public int countTotalProducts(String searchType, String searchKeyword) {
        Map<String, Object> params = Map.of("searchType", searchType, "searchKeyword", searchKeyword);
        return sqlTemplate.selectOne("sailmallDB.count_total_products", params);
    }
	//상품검색
    public List<shopping_product_dto> searchProducts(int offset, int pageSize, String searchType, String searchKeyword) {
        Map<String, Object> params = Map.of("offset", offset, "pageSize", pageSize, "searchType", searchType, "searchKeyword", searchKeyword);
        return sqlTemplate.selectList("sailmallDB.search_products",params);
    }
	
	//상품 리스트 출력
    public List<shopping_product_dto> getAllProducts() {
        return sqlTemplate.selectList("sailmallDB.get_all_products");
    }
	
	//상품등록하기
	public int saveProduct(shopping_product_dto productDTO) {
		return sqlTemplate.insert("sailmallDB.save_product",productDTO);
	}
	
	//상품등록페이지의 대메뉴 코드
	public List<shopping_cate_dto> lgMenuCode () {
		return sqlTemplate.selectList("sailmallDB.lg_menu_code");
	}
	
	//카테고리 검색
	public List<shopping_cate_dto> searchCategories(String searchType, String searchKeyword){
		Map<String, Object> params = new HashMap<>();
	    params.put("searchType", searchType);
	    params.put("searchKeyword", searchKeyword);
		return sqlTemplate.selectList("sailmallDB.search_category",params);
	}
	
	//카테고리 리스트
	public List<shopping_cate_dto> cateList () {
		return sqlTemplate.selectList("sailmallDB.cate_list");
	}
	
	//카테고리 생성
	public int createCate (shopping_cate_dto cateDTO) {
		return sqlTemplate.insert("sailmallDB.create_cate",cateDTO);
	}
	
	public List<shopping_siteinfo_dto> siteinfoList(){
		return sqlTemplate.selectList("sailmallDB.saved_siteinfo");
	}
	
	//쇼핑몰 기본설정
	public int saveSiteinfo(shopping_siteinfo_dto siteDTO) {
		return sqlTemplate.insert("sailmallDB.save_siteinfo",siteDTO);
	}
	
	//관리자 승인
	public int updateAdminStatus(String adid, String status) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("adid", adid);
	    params.put("status", status);
	    return sqlTemplate.update("sailmallDB.admin_login_status", params);
	}
	
	//관리자 리스트
	public List<shopping_admin_dto> adminList() {
        return sqlTemplate.selectList("sailmallDB.admin_list");
    }
	
	//관리자 로그인
	public shopping_admin_dto adminLogin(String adid, String hashedPassword) {
		Map<String, String> params = new HashMap<>();
        params.put("adid", adid);
        params.put("adpass", hashedPassword);
        return sqlTemplate.selectOne("sailmallDB.admin_login", params);
	}
	
	// 관리자 등록
    public int insertAdmin(shopping_admin_dto adminDTO) {
        return sqlTemplate.insert("sailmallDB.admin_insert", adminDTO);
    }
    // 아이디 중복 체크
    public shopping_admin_dto selectAdminById(String adid) {
        return sqlTemplate.selectOne("sailmallDB.admin_idcheck", adid);
    }
}
