package shopping_admin.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_siteinfo_dto {
	private int idx;
	private String siteTitle;
    private String adminEmail;
    private boolean usePoints;
    private int signupPoints;
    private int signupLevel;
    private String companyName;
    private String businessRegistrationNumber;
    private String representativeName;
    private String representativeTel;
    private String telecommunicationSalesNumber;
    private String additionalTelecommunicationNumber;
    private String businessPostcode;
    private String businessAddress;
    private String informationManagerName;
    private String informationManagerEmail;
    private String bankName;
    private String bankAccountNumber;
    private boolean useCreditCard;
    private boolean useMobilePayment;
    private boolean useBookVoucher;
    private int minPoints;
    private int maxPoints;
    private boolean useCashReceipt;
    private String deliveryCompany;
    private int deliveryFee;
    private boolean useDesiredDeliveryDate;
    private Timestamp updated_at;
    
}
