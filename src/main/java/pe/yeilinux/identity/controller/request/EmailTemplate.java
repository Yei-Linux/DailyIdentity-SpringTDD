package pe.yeilinux.identity.controller.request;

import lombok.Data;

@Data
public class EmailTemplate {
    private String to;
    private String subject;
    private String message;
}
