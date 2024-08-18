package shopping_admin.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import shopping_admin.dto.shopping_admin_dto;
import shopping_admin.dto.shopping_cate_dto;
import shopping_admin.dto.shopping_product_dto;
import shopping_admin.dto.shopping_siteinfo_dto;
import shopping_admin.service.shopping_admin_service;

@Controller
public class shopping_admin_controller {
	
	@Autowired
	private shopping_admin_service adminService;
	
	@GetMapping("/notice_list.do")
	public String notice_list(){
		return "notice_list";
	}
	//카테고리리스트 검색
	@PostMapping("/search_category")
	public String searchCategory(@RequestParam("searchType") String searchType,
	                            @RequestParam("searchKeyword") String searchKeyword,
	                            Model model) {
	    List<shopping_cate_dto> cateList = adminService.searchCategories(searchType, searchKeyword);
	    model.addAttribute("cate_list", cateList);
	    return "cate_list";
	}
	
	//카테고리 생성
	@PostMapping("/create_cate")
	public ResponseEntity<Map<String, Object>> create_cate(@RequestBody shopping_cate_dto cateDTO){
		Map<String, Object> response=new HashMap<>();
		try {
            boolean result=adminService.createCate(cateDTO);
            if (result) {
	            response.put("success", true);
	        } else {
	            response.put("success", false);
	            response.put("message", "카테고리 생성 실패");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "서버 오류 발생: " + e.getMessage());
	    }
	    return ResponseEntity.ok(response);
	}
	
	//카테고리 분류코드 중복확인
	@PostMapping("/checkCategoryCode")
	@ResponseBody
	public Map<String, Object> checkCategoryCode(@RequestBody Map<String, String> requestBody) {
	    String categoryCode = requestBody.get("categoryCode");
	    boolean exists = adminService.checkCategoryCodeExists(categoryCode);

	    Map<String, Object> response = new HashMap<>();
	    response.put("exists", exists);
	    return response;
	}
	
	//쇼핑몰 상품관리 - 카테고리등록 페이지
	@GetMapping("/cate_write.do")
	public String cate_write(){
		return "cate_write";
	}

	//쇼핑몰 상품관리 - 카테고리 리스트
	@GetMapping("/cate_list.do")
	public String cate_list(Model model){
		List<shopping_cate_dto> cate_list=adminService.cateList();
		model.addAttribute("cate_list",cate_list);
		return "cate_list";
	}
	
    // 페이지 이동
    @PostMapping("/get_products_by_page")
    @ResponseBody
    public String getProductsByPage(
            @RequestParam("page") int page,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            Model model) {
    	int pageSize = 5;
        Map<String, Object> productData = adminService.getProductsByPage(page, pageSize, searchType, searchKeyword);

        model.addAttribute("productList", productData.get("productList"));
        model.addAttribute("currentPage", productData.get("currentPage"));
        model.addAttribute("totalPages", productData.get("totalPages"));
        model.addAttribute("totalProducts",productData.get("totalProducts"));
    	
        return "product_list";
    }
	
	
    // 상품 리스트 페이징
    @GetMapping("/product_list.do")
    public String showProductList(@RequestParam(defaultValue = "1") int page, 
                                  @RequestParam(defaultValue = "") String searchType, 
                                  @RequestParam(defaultValue = "") String searchKeyword, 
                                  HttpSession session,
                                  Model model) {
        int pageSize = 5; // 한 페이지에 보여줄 항목 수
        
        Map<String, Object> productData = adminService.getProductsByPage(page, pageSize, searchType, searchKeyword);

        model.addAttribute("productList", productData.get("productList"));
        model.addAttribute("currentPage", productData.get("currentPage"));
        model.addAttribute("totalPages", productData.get("totalPages"));
        model.addAttribute("totalProducts",productData.get("totalProducts"));

        return "product_list";
    }
	
	//상품 검색
    @PostMapping("/search_product")
    public String searchProduct(@RequestParam(defaultValue = "1") int page, 
														     @RequestParam(defaultValue = "") String searchType, 
														     @RequestParam(defaultValue = "") String searchKeyword,
														     Model model,
														     HttpSession session) {
        int pageSize = 5; // 한 페이지에 보여줄 항목 수
        Map<String, Object> productData = adminService.getProductsByPage(page, pageSize, searchType, searchKeyword);
        
        model.addAttribute("productList", productData.get("productList"));
        model.addAttribute("currentPage", productData.get("currentPage"));
        model.addAttribute("totalPages", productData.get("totalPages"));
        model.addAttribute("totalProducts",productData.get("totalProducts"));
        session.setAttribute("searchType", searchType);
        session.setAttribute("searchKeyword", searchKeyword);
        return "product_list";
    }
	
	//쇼핑몰 상품관리 - 신규상품등록
	@GetMapping("/product_write.do")
	public String product_write(Model model){
		List<shopping_cate_dto> codes=adminService.lgMenuCode();
		model.addAttribute("codes",codes);
		return "product_write";
	}
	
    @PostMapping("/checkProductCode")
    @ResponseBody
    public Map<String, Object> checkProductCode(@RequestParam String productCode) {
        boolean exists = adminService.checkProductCodeExists(productCode);

        Map<String, Object> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
	
	//신규상품등록하기
	@PostMapping("/save_product")
	public ResponseEntity<Map<String, Object>> save_product(@RequestParam("productName") String productName, @RequestParam("productCode") String productCode, @RequestParam("productDescription") String productDescription, @RequestParam("productDetails") String productDetails, @RequestParam("discountRate") int discountRate, @RequestParam("salePrice") BigDecimal salePrice, @RequestParam("discountedPrice") BigDecimal discountedPrice, @RequestParam("saleStatus") String saleStatus, @RequestParam("stockQuantity") int stockQuantity, @RequestParam("mainCategory") String mainCategory, @RequestParam("earlySoldOut") String earlySoldOut, @RequestParam("mainImage") MultipartFile mainImage, @RequestParam("additionalImage1") MultipartFile additionalImage1, @RequestParam("additionalImage2") MultipartFile additionalImage2) {
	    Map<String, Object> response = new HashMap<>();
	    shopping_product_dto productDTO = new shopping_product_dto();
	    productDTO.setProductName(productName); productDTO.setProductCode(productCode); productDTO.setProductDescription(productDescription); productDTO.setProductDetails(productDetails); productDTO.setDiscountRate(discountRate); productDTO.setSalePrice(salePrice); productDTO.setDiscountedPrice(discountedPrice); productDTO.setSaleStatus(saleStatus); productDTO.setStockQuantity(stockQuantity); productDTO.setMainCategory(mainCategory); productDTO.setEarlySoldOut(earlySoldOut);
        try {
            boolean result=adminService.saveProduct(productDTO,mainImage,additionalImage1,additionalImage2);
            if (result) {
	            response.put("success", true);
	        } else {
	            response.put("success", false);
	            response.put("message", "상품 등록 실패");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "서버 오류 발생: " + e.getMessage());
	    }
	    return ResponseEntity.ok(response);
    }
	
	
	//쇼핑몰 기본설정
	@GetMapping("/admin_siteinfo.do")
	public String admin_siteinfo(Model model){
		 List<shopping_siteinfo_dto> siteinfo_list = adminService.siteinfoList();
		    if (siteinfo_list != null && !siteinfo_list.isEmpty()) {
		        shopping_siteinfo_dto siteinfo = siteinfo_list.get(0);
		        model.addAttribute("siteinfo", siteinfo);
		    }
		return "admin_siteinfo";
	}
	
    @PostMapping("/save_siteinfo")
    public ResponseEntity<Map<String, Object>> save_siteinfo(@RequestBody shopping_siteinfo_dto siteDTO) {
	    Map<String, Object> response = new HashMap<>();
        try {
            boolean result=adminService.saveSiteinfo(siteDTO);
            if (result) {
	            response.put("success", true);
	        } else {
	            response.put("success", false);
	            response.put("message", "설정 저장 실패");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "서버 오류 발생: " + e.getMessage());
	    }
	    return ResponseEntity.ok(response);
    }
	
	//쇼핑몰 회원관리
	@GetMapping("/shop_member_list.do")
	public String shop_member_list(){
		return "shop_member_list";
	}
	
	
	//관리자 로그인 승인
	@GetMapping("/update_admin_status.do")
	public ResponseEntity<Map<String, Object>> update_admin_status(
	        @RequestParam("adid") String adid,
	        @RequestParam("status") String status) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        boolean result = adminService.updateAdminStatus(adid, status);
	        if (result) {
	            response.put("success", true);
	        } else {
	            response.put("success", false);
	            response.put("message", "상태 업데이트 실패");
	        }
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "서버 오류 발생: " + e.getMessage());
	    }
	    return ResponseEntity.ok(response);
	}
	
	//admin로그인 index 페이지
    @GetMapping("/")
    public String admin_login() {
        return "index";
    }
    
    //admin 등록 승인 페이지
    @GetMapping("/admin_list.do")
    public String admin_list(Model m) {
    	m.addAttribute("adminList", adminService.adminList());
    	return "admin_list";
    }
    
    //admin등록 신청 페이지
    @GetMapping("/add_master.do")
    public String add_master() {
    	return "add_master";
    }
    
    //admin 로그아웃
    @GetMapping("/admin_logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/"; // 로그인 페이지로 리다이렉트
    }
    
    //admin 로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> admin_loginok (@RequestBody Map<String, String> loginData, HttpServletRequest request){
    	Map<String,Object> response=new HashMap<>();
    	String adid = loginData.get("adid");
        String adpass = loginData.get("adpass");
        try {
            shopping_admin_dto admin = adminService.adminLogin(adid, adpass);
            if (admin != null && admin.getLogin().equals("Y")) {
            	request.getSession().setAttribute("admin", admin);
                response.put("success", true);
                response.put("admin", admin);
            }else if(admin != null && admin.getLogin().equals("N")) {
            	response.put("success", false);
                response.put("message", "로그인 실패: 승인되지 않은 관리자 입니다");
            }else {
                response.put("success", false);
                response.put("message", "로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "서버 오류 발생: " + e.getMessage());
        }
    	return ResponseEntity.ok(response);
    }
    
  //admin등록 insert
    @PostMapping("/add_masterok")
    public ResponseEntity<Map<String,Object>> admin_join(@RequestBody shopping_admin_dto adminDTO){
    	Map<String,Object> response=new HashMap<>();
    	try {
	    	boolean result=adminService.registerAdmin(adminDTO);
	    	if(result) {
	    	response.put("success", true);
	    	}else {
	    	response.put("success", false);	
	    	response.put("message", "등록 실패");  	
	    	}
    	}catch (Exception e) {
            response.put("success", false);
            response.put("message", "서버 오류 발생");

        }
    	return ResponseEntity.ok(response);
    }
  //admin등록신청 id check
    @GetMapping("/idcheck")
    public ResponseEntity<String> check_id(@RequestParam("adid") String adid) {
        try {
            boolean isAvailable = adminService.isIdAvailable(adid);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.set("Content-Type", "text/plain; charset=UTF-8");

            if (isAvailable) {
                return new ResponseEntity<>("사용 가능한 아이디입니다.", headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("이미 사용 중인 아이디입니다.", headers, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpHeaders errorHeaders = new HttpHeaders();
            errorHeaders.setContentType(MediaType.TEXT_PLAIN);
            errorHeaders.set("Content-Type", "text/plain; charset=UTF-8");
            return new ResponseEntity<>("서버 오류 발생", errorHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
