package com.alaythiaproductions.hike_and_go.utility;

import com.alaythiaproductions.hike_and_go.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailConstructor {

    @Autowired
    private Environment environment;

    public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user, String password) {
        String url = contextPath + "/register?token=" + token;
        String message = "Hello, " + user.getUsername() + ",\n Please click on this link: " + url + " to " +
        " verify your email and edit your personal information.\n\n" + "Your temporary password is: " + password +
                "\n\nThis will be valid for 24 hours after receiving this email. \n\n\nThank you,\n\nHike & Go Co.";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Hike & Go Co. - New User");
        email.setText(message);
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }
}
