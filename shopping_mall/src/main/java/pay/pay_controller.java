package pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class pay_controller {
	
	@GetMapping("/pay/coupon_list.do")
	public String coupon_list(Model m) {
		try(Connection con = new dbinfo().info();
				PreparedStatement ps=con.prepareStatement("SELECT * FROM coupon ORDER BY cidx DESC LIMIT ?,?");
				) {
			int pageno=2;
			ps.setInt(1, 0);
			ps.setInt(2, pageno);
			try(ResultSet rs=ps.executeQuery()){	
				 List<Map<String, Object>> coupons = new ArrayList<>();
	                while (rs.next()) {
	                    Map<String, Object> coupon = new HashMap<>();
	                    coupon.put("cidx", rs.getInt("cidx"));
	                    coupon.put("cpname", rs.getString("cpname"));
	                    coupon.put("cprate", rs.getString("cprate"));
	                    coupon.put("cpuse", rs.getString("cpuse"));
	                    coupon.put("cpdate", rs.getString("cpdate"));
	                    coupons.add(coupon);
	                }
	                m.addAttribute("coupons", coupons);
	            }
		}catch(Exception e) {
			e.getMessage();
		}
		return "/pay/coupon_list";
	}
	
	@GetMapping("/pay/pay1.do")
	public String pay1() {return "/pay/pay1";}
	
	@PostMapping("/pay/pay3.do")
	public String pay3(@ModelAttribute("payinfo") pay_dto dto, HttpServletRequest req ) {
		req.setAttribute("data", dto);
		return "/pay/pay3";
	}
	
	@PostMapping("/pay/pay2.do")
	public String pay2(@ModelAttribute("product") pay_dto dto, Model m) throws Exception {
		Map<String, String> as=new HashMap<String, String>();
		as.put("product_code",dto.getProduct_code());
		as.put("product_nm",dto.getProduct_nm());
		as.put("product_money",dto.getProduct_money());
		as.put("product_ea",dto.getProduct_ea());
		as.put("price",dto.getPrice());
		//Collection 을 쓰는 경우도 있음
		//addAllAttributes : key 가 없음, 단 배열명 + 자료형을 기반으로 jsp 출력
		m.addAllAttributes(as); //jsp에서 출력을 하기 위해서 get, []로 출력이 가능
		return "/pay/pay2"; //WEB-INF/views
	}

}
