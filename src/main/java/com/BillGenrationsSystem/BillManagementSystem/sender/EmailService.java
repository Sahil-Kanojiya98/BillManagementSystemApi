package com.BillGenrationsSystem.BillManagementSystem.sender;

import com.BillGenrationsSystem.BillManagementSystem.model.Bill;
import com.BillGenrationsSystem.BillManagementSystem.model.Customer;
import com.BillGenrationsSystem.BillManagementSystem.model.Order;
import com.BillGenrationsSystem.BillManagementSystem.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;


//    sendOrderSuccessEmail
    private final String subject = "Order Placed Successfully";
    private final String bodyTemplate = "<div style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; line-height: 1.6;\">" +
            "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\">" +
            "<div style=\"background-color: #28a745; color: #fff; padding: 10px 0; text-align: center; border-radius: 8px 8px 0 0;\">" +
            "<h2>Order Placed Successfully</h2></div>" +
            "<div style=\"padding: 20px;\"><p>Dear $CUSTOMER_NAME$,</p>" +
            "<p>Thank you for your order. Here are the details of your purchase:</p>" +
            "<p><strong>Product Name:</strong> $PRODUCT_NAME$</p>" +
            "<p><strong>Price:</strong> $PRICE$</p>" +
            "<p><strong>GST Rate:</strong> $GST_RATE$%</p>" +
            "<p><strong>Quantity:</strong> $PRODUCT_COUNT$</p>" +
            "<p><strong>Total Amount:</strong> $TOTAL_AMOUNT$</p>" +
            "<p><strong>Order Date:</strong> $ORDER_DATE$</p>" +
            "<p>We hope you enjoy your purchase! If you have any questions, feel free to contact us.</p>" +
            "</div><div style=\"text-align: center; color: #666;\"><p>Thank you,</p><p>[Your Application Team]</p></div></div></div>";

    @Async
    public void sendOrderSuccessEmail(Order order) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(order.getCustomer().getEmail());
            helper.setSubject(subject);
            String body = bodyTemplate
                    .replace("$CUSTOMER_NAME$", order.getCustomer().getCustomerName())
                    .replace("$PRODUCT_NAME$", order.getProduct().getProductName())
                    .replace("$PRICE$", String.format("%.2f", order.getProduct().getPrice()))
                    .replace("$GST_RATE$", String.format("%.2f", order.getProduct().getGstRate()))
                    .replace("$PRODUCT_COUNT$", String.valueOf(order.getProductCount()))
                    .replace("$TOTAL_AMOUNT$", String.format("%.2f", order.getTotalAmount()))
                    .replace("$ORDER_DATE$", order.getOdrerDate().toString());
            helper.setText(body, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



//    sendLowStockAlertEmail
    private final String subjectLowStockAlert = "Low Stock Alert";
    private final String bodyTemplateLowStockAlert = "<div style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; line-height: 1.6;\">" +
            "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\">" +
            "<div style=\"background-color: #dc3545; color: #fff; padding: 10px 0; text-align: center; border-radius: 8px 8px 0 0;\">" +
            "<h2>Low Stock Alert</h2></div>" +
            "<div style=\"padding: 20px;\"><p>Dear Admin,</p>" +
            "<p>The stock for the following product has fallen below the threshold level:</p>" +
            "<p><strong>Product Name:</strong> $PRODUCT_NAME$</p>" +
            "<p><strong>Current Stock:</strong> $CURRENT_STOCK$</p>" +
            "<p><strong>Threshold Level:</strong> $THRESHOLD_LEVEL$</p>" +
            "<p>Please restock this product as soon as possible.</p>" +
            "</div><div style=\"text-align: center; color: #666;\"><p>Thank you,</p><p>[Your Application Team]</p></div></div></div>";

    @Value("${admin.email}")
    String adminEmail;

    @Async
    public void sendLowStockAlertEmail(Product product) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(adminEmail);
            helper.setSubject(subjectLowStockAlert);
            String body = bodyTemplateLowStockAlert
                    .replace("$PRODUCT_NAME$", product.getProductName())
                    .replace("$CURRENT_STOCK$", String.valueOf(product.getStock().getStockQuantity()))
                    .replace("$THRESHOLD_LEVEL$", String.valueOf(product.getStock().getThresholdLevel()));

            helper.setText(body, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



//    buildDailyReportBody
    private final String emailTemplate = "<div style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; line-height: 1.6;\">" +
            "<div style=\"max-width: 600px; margin: 0 auto; background-color: #fff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\">" +
            "<div style=\"background-color: #007bff; color: #fff; padding: 10px 0; text-align: center; border-radius: 8px 8px 0 0;\">" +
            "<h2 style=\"margin: 0; padding: 20px;\">Daily Report: Orders Summary</h2></div>" +
            "<div>" +
            "<p style=\"padding: 20px;\">Dear Admin,</p>" +
            "<p style=\"padding: 20px;\">Here is the summary of today's orders:</p>" +
            "<table style=\"width: 100%; border-radius: 8px; padding: 10px; border-collapse: collapse;\">" +
            "<thead style=\"background-color: #007bff; color: #fff;\">" +
            "<tr><th style=\"border: 1px solid #ddd; padding: 8px;\">Order ID</th><th style=\"border: 1px solid #ddd; padding: 8px;\">Customer Name</th><th style=\"border: 1px solid #ddd; padding: 8px;\">Product Name</th><th style=\"border: 1px solid #ddd; padding: 8px;\">Order Date</th><th style=\"border: 1px solid #ddd; padding: 8px;\">Total Amount</th></tr>" +
            "</thead>" +
            "<tbody style=\"font-size: 14px;\">" +
            "$TABLE_BODY$" +
            "</tbody>" +
            "</table>" +
            "<p style=\"padding: 20px;\">Thank you for your attention.</p>" +
            "</div>" +
            "<div style=\"text-align: center; color: #666; padding: 20px;\">" +
            "<p>Best Regards,</p>" +
            "<p>Your Application Team</p>" +
            "</div>" +
            "</div>" +
            "</div>";
    private final String tableRowTemplate = "<tr>" +
            "<td style=\"border: 1px solid #ddd; padding: 8px;\">$ORDER_ID$</td>" +
            "<td style=\"border: 1px solid #ddd; padding: 8px;\">" +
            "<span style=\"display: inline-block; max-width: 200px; overflow: hidden; text-overflow: ellipsis;\">$CUSTOMER_NAME$</span>" +
            "</td>" +
            "<td style=\"border: 1px solid #ddd; padding: 8px;\">$PRODUCT_NAME$</td>" +
            "<td style=\"border: 1px solid #ddd; padding: 8px;\">$ORDER_DATE$</td>" +
            "<td style=\"border: 1px solid #ddd; padding: 8px;\">$TOTAL_AMOUNT$</td>" +
            "</tr>";

    private String buildDailyReportBody(List<Bill> bills) {
        StringBuilder tableBody = new StringBuilder();
        for (Bill bill : bills) {
            String row = tableRowTemplate
                    .replace("$ORDER_ID$", String.valueOf(bill.getOrder().getOrderId()))
                    .replace("$CUSTOMER_NAME$", bill.getOrder().getCustomer().getCustomerName())
                    .replace("$PRODUCT_NAME$", bill.getOrder().getProduct().getProductName())
                    .replace("$ORDER_DATE$", String.valueOf(bill.getBillDate()))
                    .replace("$TOTAL_AMOUNT$", String.valueOf(bill.getBillAmount()));

            tableBody.append(row);
        }
        String emailBody = emailTemplate.replace("$TABLE_BODY$", tableBody.toString());
        return emailBody;
    }
    private final String subjectDailyReport = "Daily Report: Orders Summary";

    @Async
    public void sendDailyReportEmail(List<Bill> bills) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(adminEmail);
            helper.setSubject(subjectDailyReport);
            String body = buildDailyReportBody(bills);
            helper.setText(body, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}