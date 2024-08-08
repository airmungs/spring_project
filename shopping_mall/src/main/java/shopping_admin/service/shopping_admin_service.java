package shopping_admin.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping_admin.dao.shopping_admin_dao;
import shopping_admin.dto.shopping_admin_dto;
import shopping_admin.dto.shopping_cate_dto;
import shopping_admin.dto.shopping_product_dto;
import shopping_admin.dto.shopping_siteinfo_dto;

@Service
public class shopping_admin_service {
	 @Autowired
	    private shopping_admin_dao adminDao;
	 	
	 	//상품등록하기
	 	public boolean saveProduct(shopping_product_dto productDTO) {
	 		return adminDao.saveProduct(productDTO)>0;
	 	}
	 	
	 	//상품등록 페이지의 대메뉴코드
	 	public List<shopping_cate_dto> lgMenuCode(){
	 		return adminDao.lgMenuCode();
	 	}
	 	
	 	//카테고리 리스트
	 	public List<shopping_cate_dto> cateList(){
	 		return adminDao.cateList();
	 	}
	 
	 	//카레고리 생성
	 	public boolean createCate(shopping_cate_dto cateDTO) {
	 		return adminDao.createCate(cateDTO)>0;
	 	}
	 	
	 	//쇼핑몰 설정 리스트
	 	public List<shopping_siteinfo_dto> siteinfoList(){
	 		return adminDao.siteinfoList();
	 	}
	 
	 	//쇼핑몰 기본설정 저장
	 	public boolean saveSiteinfo(shopping_siteinfo_dto siteDTO) {
	 		return adminDao.saveSiteinfo(siteDTO) > 0;
	 	}
	 
	 	//관리자 로그인 승인
	    public boolean updateAdminStatus(String adid, String status) {
	        return adminDao.updateAdminStatus(adid, status) > 0;
	    }
	 
	 	//관리자 리스트
	    public List<shopping_admin_dto> adminList() {
	        return adminDao.adminList(); // DAO 메서드 호출
	    }
	 
	 	//관리자 로그인
	    public shopping_admin_dto adminLogin(String adid, String adpass) throws Exception {
	        String hashedPassword = hashPassword(adpass);
	        return adminDao.adminLogin(adid, hashedPassword);
	    }
	 
	 	//admin 등록신청
		public boolean registerAdmin(shopping_admin_dto adminDTO) throws Exception {
		        String hashedPassword = hashPassword(adminDTO.getAdpass());
		        adminDTO.setAdpass(hashedPassword);
		        int result = adminDao.insertAdmin(adminDTO);
		        return result > 0;
		}
		
		//admin 등록 신청 id중복체크
	    public boolean isIdAvailable(String adid) {
	        shopping_admin_dto admin = adminDao.selectAdminById(adid);
	        return admin == null;
	    }
	    
	    //password 암호화
	    private String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest digest = MessageDigest.getInstance("SHA3-256"); // SHA3-256을 사용합니다.
	        byte[] hashedBytes = digest.digest(password.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashedBytes) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    }
}
