package shopping_admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationSettings_dto {
    private int id;
    private String siteTitle;
    private String adminEmail;
    private boolean usePoints;
    private int signupPoints;
    private int signupLevel;
}
