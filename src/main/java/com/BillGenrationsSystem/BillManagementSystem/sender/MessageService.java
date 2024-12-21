package com.BillGenrationsSystem.BillManagementSystem.sender;

import com.BillGenrationsSystem.BillManagementSystem.model.Order;
import com.BillGenrationsSystem.BillManagementSystem.config.TwilloConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final TwilloConfig twilioConfig;

    public static String buildOrderConfirmationMessage(Order order) {
        return String.format(
                "Order Confirmation\n" +
                        "------------------\n" +
                        "Customer: %s\n" +
                        "Product: %s\n" +
                        "Price: %.2f\n" +
                        "GST Rate: %.2f%%\n" +
                        "Quantity: %d\n" +
                        "Total Amount: %.2f\n" +
                        "Order Date: %s\n" +
                        "\nThank you for your order!",
                order.getCustomer().getCustomerName(),
                order.getProduct().getProductName(),
                order.getProduct().getPrice(),
                order.getProduct().getGstRate(),
                order.getProductCount(),
                order.getTotalAmount(),
                order.getOdrerDate().toString()
        );
    }

    @Async
    public void sendWhatsAppMessage(Order order) {
        System.out.println(("whatsapp to:+91" + order.getCustomer().getMobileNumber())+"  body:"+buildOrderConfirmationMessage(order)+"  watsapp - from :"+twilioConfig.getWhatsapp_number());
        Message.creator(
                new PhoneNumber("whatsapp:+91" + order.getCustomer().getMobileNumber()),
                new PhoneNumber(twilioConfig.getWhatsapp_number()),
                buildOrderConfirmationMessage(order)
        ).create();
    }

    @Async
    public void sendSms(Order order) {
        Message.creator(
                new PhoneNumber("+91"+order.getCustomer().getMobileNumber()),
                new PhoneNumber(twilioConfig.getSms_number()),
                buildOrderConfirmationMessage(order)
        ).create();
    }

}
