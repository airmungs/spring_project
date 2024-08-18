package shopping_client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class shopping_clients_dto {
    private String userId;
    private String password;
    private String passwordConfirm;
    private String name;
    private String email;
    private String phone;
    private boolean eventMail;
    private boolean eventSms;
    private String createdAt;
    private String updatedAt;
}
