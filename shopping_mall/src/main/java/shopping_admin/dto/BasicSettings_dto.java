package shopping_admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSettings_dto {
    private int id;
    private String companyName;
    private String businessRegistrationNumber;
    private String representativeName;
    private String representativePhone;
    private String telecommunicationSalesNumber;
    private String additionalTelecommunicationNumber;
    private String businessPostcode;
    private String businessAddress;
    private String informationManagerName;
    private String informationManagerEmail;

    // Getters and Setters
}
