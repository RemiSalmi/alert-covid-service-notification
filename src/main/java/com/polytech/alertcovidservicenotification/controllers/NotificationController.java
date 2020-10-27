package com.polytech.alertcovidservicenotification.controllers;

import com.polytech.alertcovidservicenotification.models.MailSender;
import com.polytech.alertcovidservicenotification.models.User;
import com.polytech.alertcovidservicenotification.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/notification/")
public class NotificationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String sendEmail(@RequestBody final List<Long> users){
        users.forEach(id_user -> {
            if(!userRepository.findById(id_user).isEmpty()){
                try {
                    MailSender.getInstance().sendmail(userRepository.getOne(id_user).getEmail());
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return "Mail send";
    }

}
