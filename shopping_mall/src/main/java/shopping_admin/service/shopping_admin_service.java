package shopping_admin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shopping_admin.dao.shopping_admin_dao;
import shopping_admin.dto.shopping_admin_dto;
import shopping_admin.dto.shopping_cate_dto;
import shopping_admin.dto.shopping_notice_dto;
import shopping_admin.dto.shopping_product_dto;
import shopping_admin.dto.shopping_siteinfo_dto;
import shopping_client.dto.shopping_clients_dto;

@Service
public class shopping_admin_service {
	
	private String uploadDir;
	
	@Autowired
	private shopping_admin_dao adminDao;
		
	    // 공지사항 리스트 페이징 처리
		public List<shopping_notice_dto> getNoticesWithPaging(int offset, int pageSize) {
		    return adminDao.getNoticesWithPaging(offset, pageSize);
		}
	
	    // 총 공지사항 수 계산
	    public int countTotalNotices() {
	        return adminDao.countTotalNotices();
	    }
	
	    // 공지사항 등록
	    public boolean insertNotice(Map<String, Object> noticeData) {
	        return adminDao.insertNotice(noticeData) > 0;
	    }
	
		//공지사항 등록
		public boolean insertNotice(boolean notify, String title, String writer, String content) {
		    Map<String, Object> noticeData = new HashMap<>();
		    noticeData.put("notify", notify ? 1 : 0);
		    noticeData.put("noticeTitle", title);
		    noticeData.put("noticeWriter", writer);
		    noticeData.put("noticeText", content);
	
		    return adminDao.insertNotice(noticeData) > 0;
		}
		
		//이용약관
		public boolean useAgree(String useAgree) {
			return adminDao.useAgree(useAgree)>0;
		}
		public String selectUseAgree() {
			return adminDao.selectUseAgree();
		}
		//고객정보 이용약관
		public boolean infoAgree(String infoAgree) {
			return adminDao.infoAgree(infoAgree)>0;
		}
		public String selectInfoAgree() {
			return adminDao.selectInfoAgree();
		}
	
		//클라이언트 정지 휴면 업데이트
		public boolean updateLoginStatus(String userId, String status) {
		    return adminDao.updateLoginStatus(userId, status) > 0;
		}
		//클라이언트 리스트
		public List<shopping_clients_dto> showClients(){
			return adminDao.showClients();
		}
		
		//상품리스트 업데이트
		public Map<String, Object> getProductsByPage(int page, int pageSize, String searchType, String searchKeyword) {
		    Map<String, Object> result = new HashMap<>();

		    int offset = (page - 1) * pageSize;
		    
		    List<shopping_product_dto> productList = adminDao.searchProducts(offset, pageSize, searchType, searchKeyword);
		    int totalProducts = adminDao.countTotalProducts(searchType, searchKeyword);
		    
		    int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

		    result.put("productList", productList);
		    result.put("currentPage", page);
		    result.put("totalPages", totalPages);
		    result.put("totalProducts", totalProducts);

		    return result;
		}
		
		//상품코드 중복체크
	    public boolean checkProductCodeExists(String productCode) {
	        return adminDao.checkProductCodeExists(productCode)>0;
	    }
	    
		//상품등록 첨부파일 저장 경로
	    public shopping_admin_service(ServletContext servletContext) {
	        this.uploadDir = servletContext.getRealPath("/upload/");
	    }
	    // 상품 등록 //파일 저장
	    public boolean saveProduct(shopping_product_dto productDTO, MultipartFile mainImage, MultipartFile additionalImage1, MultipartFile additionalImage2) throws IOException {
	        // 1. 파일 저장 처리
	        List<String> mainImageData = saveFile(mainImage);
	        List<String> additionalImage1Data = saveFile(additionalImage1);
	        List<String> additionalImage2Data = saveFile(additionalImage2);
	        // DTO에 파일 이름 , 경로 저장
	        productDTO.setMainImage(mainImageData.get(0));
	        productDTO.setMainImagePath(mainImageData.get(1));
	        if (additionalImage1Data != null) {
	            productDTO.setAdditionalImage1(additionalImage1Data.get(0));
	            productDTO.setAdditionalImage1Path(additionalImage1Data.get(1));
	        }

	        if (additionalImage2Data != null) {
	            productDTO.setAdditionalImage2(additionalImage2Data.get(0));
	            productDTO.setAdditionalImage2Path(additionalImage2Data.get(1));
	        }
	        // 2. 상품 정보를 DB에 저장
	        return adminDao.saveProduct(productDTO) > 0;
	    }

	    // 파일 저장 처리 method
	    private List<String> saveFile(MultipartFile file) throws IOException {
	        if (file != null && !file.isEmpty()) {
	            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	            Path path = Paths.get(this.uploadDir, fileName);
	            Files.createDirectories(path.getParent()); // 디렉토리가 존재하지 않으면 생성
	            Files.copy(file.getInputStream(), path);
	            
	            //파일저장 두가지 정보(이름, 경로)
	            String webPath = "/upload/" + fileName;
	            List<String> fileData = new ArrayList<>();
	            fileData.add(fileName); // 파일 이름
	            fileData.add(webPath);  // 웹 경로
	            return fileData;
	        }
	        return null;
	    }
	 	
	 	//상품등록 페이지 대메뉴코드 출력
	 	public List<shopping_cate_dto> lgMenuCode(){
	 		return adminDao.lgMenuCode();
	 	}
	 	
	 	//카테고리 검색
	 	public List<shopping_cate_dto> searchCategories(String searchType, String searchKeyword){
	 		return adminDao.searchCategories(searchType,searchKeyword);
	 	}
	 	
	 	//카테고리 리스트
	 	public List<shopping_cate_dto> cateList(){
	 		return adminDao.cateList();
	 	}
	 
	 	//카레고리 생성
	 	public boolean createCate(shopping_cate_dto cateDTO) {
	 		return adminDao.createCate(cateDTO)>0;
	 	}
	 	
	 	//카테고리 분류코드 중복확인
	 	public boolean checkCategoryCodeExists(String categoryCode) {
	 	    return adminDao.checkCategoryCodeExists(categoryCode) > 0;
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
