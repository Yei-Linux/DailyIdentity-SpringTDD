package pe.yeilinux.identity.controller.request;

import lombok.Data;

@Data
public class NotificationRequest {
    private String to;
    private String subject;
    private String message;
}
