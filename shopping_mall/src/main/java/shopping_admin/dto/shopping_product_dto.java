package shopping_admin.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_product_dto {
    private int idx;
    private String productCode;
    private String mainCategory;
    private String productName;
    private String productDescription;
    private BigDecimal salePrice;
    private int discountRate;
    private BigDecimal discountedPrice;
    private int stockQuantity;
    private String saleStatus;
    private String earlySoldOut;
    private String mainImage;
    private String additionalImage1;
    private String additionalImage2;
    private String productDetails;
}
