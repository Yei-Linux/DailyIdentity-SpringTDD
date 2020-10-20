package pe.yeilinux.identity.proxy;

public interface NotificationProxy {
    public void sendEmail(String to,String subject,String message);
    public void sendSms(String to, String message);
    public String customMessage(String message);
}
