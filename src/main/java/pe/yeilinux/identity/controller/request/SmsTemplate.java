package pe.yeilinux.identity.controller.request;

import lombok.Data;

@Data
public class SmsTemplate {
    private String to;
    private String message;
}
