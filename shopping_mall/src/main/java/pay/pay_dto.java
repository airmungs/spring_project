package pay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class pay_dto {
	private String product_code, product_ea, product_money, price, product_nm;
	private String goodcode, goodname, goodea, goodprice;
	private String buyername, buyertel, buyeremail, rec_post, rec_way, rec_addr,gopaymethod;
}
