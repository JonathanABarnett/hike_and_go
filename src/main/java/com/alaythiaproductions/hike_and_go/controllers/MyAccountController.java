package com.alaythiaproductions.hike_and_go.controllers;

import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.security.Role;
import com.alaythiaproductions.hike_and_go.security.UserRole;
import com.alaythiaproductions.hike_and_go.service.implementation.UserSecurityService;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import com.alaythiaproductions.hike_and_go.utility.MailConstructor;
import com.alaythiaproductions.hike_and_go.utility.PasswordResetToken;
import com.alaythiaproductions.hike_and_go.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Controller
public class MyAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConstructor mailConstructor;

    /**
     * Handles incoming request (GET) to the myAccount page
     *
     * @param model populate view page fields: title
     * @return the view page - myAccount.html
     */
    @GetMapping(value = "/myAccount")
    public String myAccount(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    /**
     * User is found by token that relates to that User. Username is established from User which loads the UserSecurityDetails
     * that is obtained from the username and loads up the user's authentication and privileges.
     *
     * @param model  load title of page and tab to edit users information
     * @param locale
     * @param token  Specific token used to identify user to allow registration to continue
     * @return the view page either the token is invalid and is redirected to access-denied or user is directed to myAccount
     */
    @GetMapping(value = "/register")
    public String register(Model model, Locale locale, @RequestParam("token") String token) {
        model.addAttribute("title", "Register");
        model.addAttribute("classActiveRegister", true);
        System.out.println("In Register GET");

        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);

        if (passwordResetToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/access-denied";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);

        return "myProfile";
    }

    @PostMapping(value = "/register")
    public String registerPost(Model model, HttpServletRequest request, @ModelAttribute("email") String userEmail, @ModelAttribute("username") String username) throws Exception {
        model.addAttribute("title", "Register");
        model.addAttribute("classActiveEdit", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);
        System.out.println("In Register POST " + username);

        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExists", true);
            model.addAttribute("classActiveRegister", true);
            System.out.println("Invalid Username");
            return "myAccount";
        }

        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);
            model.addAttribute("classActiveRegister", true);
            System.out.println("Invalid Email");
            return "myAccount";
        }

        User user1 = new User();
        user1.setUsername(username);
        user1.setEmail(userEmail);
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user1.setPassword(encryptedPassword);
        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user1, role));
        userService.createUser(user1, userRoles);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user1, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage simpleMailMessage = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user1, password);

        javaMailSender.send(simpleMailMessage);

        model.addAttribute("emailSent", true);
        model.addAttribute("classActiveRegister", true);

        return "myAccount";
    }

    @RequestMapping(value = "/forgot")
    public String forgot(Model model, HttpServletRequest request, @ModelAttribute("email") String email) {
        model.addAttribute("title", "Forgot Password");
        model.addAttribute("classActiveForgot", true);

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExist", true);
            model.addAttribute("classActiveForgot", true);
            System.out.println("Invalid Username");
            return "myAccount";
        }

        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        userService.save(user);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage simpleMailMessage = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        javaMailSender.send(simpleMailMessage);

        model.addAttribute("emailSent", true);
        model.addAttribute("classActiveForgot", true);

        return "myAccount";
    }
}
