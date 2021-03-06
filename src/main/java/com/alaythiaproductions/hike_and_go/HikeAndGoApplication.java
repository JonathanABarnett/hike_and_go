package com.alaythiaproductions.hike_and_go;

import com.alaythiaproductions.hike_and_go.model.User;
import com.alaythiaproductions.hike_and_go.security.Role;
import com.alaythiaproductions.hike_and_go.security.UserRole;
import com.alaythiaproductions.hike_and_go.service.service.UserService;
import com.alaythiaproductions.hike_and_go.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.tools.jar.CommandLine;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HikeAndGoApplication  /*implements CommandLineRunner */{

//    @Autowired
//    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(HikeAndGoApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        User admin = new User();
//        admin.setUsername("Admin");
//        admin.setFirstName("Admin");
//        admin.setLastName("Admin");
//        admin.setPassword(SecurityUtility.passwordEncoder().encode("123"));
//        admin.setEmail("admin@mail.com");
//        admin.setPhone("888-555-5555");
//        Set<UserRole> userRole = new HashSet<>();
//        Role role1 = new Role();
//        role1.setRoleId(1);
//        role1.setName("ROLE_ADMIN");
//        userRole.add(new UserRole(admin, role1));
//
//        userService.createUser(admin, userRole);
//
//        User user = new User();
//        user.setUsername("User");
//        user.setFirstName("User");
//        user.setLastName("User");
//        user.setPassword(SecurityUtility.passwordEncoder().encode("123"));
//        user.setEmail("user@mail.com");
//        user.setPhone("888-555-5550");
//        Set<UserRole> userRoles = new HashSet<>();
//        Role role = new Role();
//        role.setRoleId(2);
//        role.setName("ROLE_USER");
//        userRoles.add(new UserRole(user, role));
//
//        userService.createUser(user, userRoles);
//
//    }

}
