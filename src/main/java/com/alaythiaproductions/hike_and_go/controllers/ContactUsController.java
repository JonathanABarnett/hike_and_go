package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ContactUsController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/contactUs")
    public String contactUs(Model model) {
        model.addAttribute("title", "Contact Us");
        return "contactUs";
    }

    @PostMapping(value = "/contactUs")
    public String contactUsPost(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Contact Us");

        String customer = request.getParameter("firstName") + " " + request.getParameter("lastName");


        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        String orderNumber = request.getParameter("orderNumber");

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("hike.and.go.co@gmail.com");
        email.setTo("hike.and.go.co@gmail.com");
        email.setSubject(subject);

        String emailResponse = request.getParameter("emailResponse");

        String body = "From:  " + customer + "\n\n"
                + "Email: " + emailResponse + "\n\n"
                + "Concerning: " + subject + "\n\n"
                + "Order Number: " + orderNumber + "\n\n"
                + "Message: " + message;

        email.setText(body);

        System.out.println("From: " + customer);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);

        javaMailSender.send(email);

        model.addAttribute("contactSentSuccess", true);
        return "contactUs";
    }

}
