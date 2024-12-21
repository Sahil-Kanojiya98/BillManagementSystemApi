package com.BillGenrationsSystem.BillManagementSystem.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "twilio")
public class TwilloConfig {

    private String account_sid;
    private String auth_token;
    private String sms_number;
    private String whatsapp_number;

    @PostConstruct
    public void run() {
        Twilio.init(account_sid, auth_token);
    }

}
