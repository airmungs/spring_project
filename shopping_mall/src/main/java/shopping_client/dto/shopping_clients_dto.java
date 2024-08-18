package shopping_client.dto;

import java.util.Date;

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
    private String login;
    private boolean eventMail;
    private boolean eventSms;
    private Date createdAt;
    private Date updatedAt;
}
