package shopping_admin.service;

import shopping_admin.dto.BasicSettings_dto;
import shopping_admin.dto.PaymentSettings_dto;
import shopping_admin.dto.RegistrationSettings_dto;

public interface siteInfo_service {
    void saveRegistrationSettings(RegistrationSettings_dto settings);
    RegistrationSettings_dto getRegistrationSettings();

    void saveBasicSettings(BasicSettings_dto settings);
    BasicSettings_dto getBasicSettings();

    void savePaymentSettings(PaymentSettings_dto settings);
    PaymentSettings_dto getPaymentSettings();
}
