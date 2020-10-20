package pe.yeilinux.identity.proxy.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pe.yeilinux.identity.controller.request.EmailTemplate;
import pe.yeilinux.identity.controller.request.SmsTemplate;
import pe.yeilinux.identity.proxy.NotificationProxy;

@Component
public class NotificationProxyImpl implements NotificationProxy {
    @Value("${daily-notification.email-url-api}")
    private String emailApiUrl;
    @Value("${daily-notification.sms-url-api}")
    private String smsApiUrl;
    @Value("${daily-notification.sms-url-api}")
    private String token;

    @Autowired
    private NotificationProxy notificationProxy;

    @Override
    public void sendEmail(String to, String subject, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add("Authorization", "Bearer " + token);

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setTo(to);
        emailTemplate.setMessage(this.customMessage(message));
        emailTemplate.setSubject(subject);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<EmailTemplate> request = new HttpEntity<>(emailTemplate, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(emailApiUrl,request,String.class);
    }

    @Override
    public void sendSms(String to, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add("Authorization", "Bearer " + token);

        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setTo(to);
        smsTemplate.setMessage(this.customMessage(message));

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<SmsTemplate> request = new HttpEntity<>(smsTemplate, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(smsApiUrl,request,String.class);
    }

    @Override
    public String customMessage(String message) {
        return "Por favor entra a este link para cambiar de contraseña: http://localhost:4200/public/reset/" + message + "/password/";
    }
}
