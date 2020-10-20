package pe.yeilinux.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.identity.controller.request.NotificationRequest;
import pe.yeilinux.identity.controller.response.GeneralResponse;
import pe.yeilinux.identity.proxy.NotificationProxy;

@RestController
@RequestMapping("notification")
public class NotificationController {
    @Autowired
    private NotificationProxy notificationProxy;

    @PostMapping("send")
    public ResponseEntity<?> sendNotification(@RequestParam String type, @RequestBody NotificationRequest notificationRequest){
        if(type.equals("EMAIL")){
            this.notificationProxy.sendEmail(notificationRequest.getTo(),notificationRequest.getSubject(),notificationRequest.getMessage());
        }
        if(type.equals("SMS")){
            this.notificationProxy.sendSms(notificationRequest.getTo(),notificationRequest.getMessage());
        }
        return new ResponseEntity<>(new GeneralResponse("Correctly send"),HttpStatus.OK);
    }
}
