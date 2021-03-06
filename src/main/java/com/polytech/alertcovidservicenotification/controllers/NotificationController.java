package com.polytech.alertcovidservicenotification.controllers;

import com.polytech.alertcovidservicenotification.models.ContactLocationRepository;
import com.polytech.alertcovidservicenotification.models.MailSender;
import com.polytech.alertcovidservicenotification.models.User;
import com.polytech.alertcovidservicenotification.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/notification/")
public class NotificationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactLocationRepository contactLocationRepository;

    @PostMapping
    public Boolean sendEmail(@RequestBody final List<Long> users, HttpServletResponse response){
        users.forEach(id_user -> {
            if(!userRepository.findById(id_user).isEmpty()){
                try {
                    var user = userRepository.getOne(id_user);
                    var contactLocation = contactLocationRepository.getLastLocationHistory(id_user);
                    if(contactLocation != null){
                        var location =  contactLocation.getLatitude()+","+contactLocation.getLongitude();
                        var date = (String) new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss").format(contactLocation.getDate());
                        MailSender.getInstance().sendmail(user.getEmail(),user.getFirstname(),location,date);
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } catch (IOException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        });
        response.setStatus(HttpServletResponse.SC_CREATED);
        return true;
    }

}
