package shopping_admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSettings_dto {
    private int id;
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
}
