package com.rf.backend;

import com.rf.backend.user.User;
import com.rf.backend.user.UserRepository;
import com.rf.backend.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


// aşağıda security login işlemini iptal ettik
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
@Bean
    CommandLineRunner hayaliKullanici(UserService userService){
        return  new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user=new User();
                user.setUsername("kral1");
                user.setDisplay("kral1");
                user.setSifre("Ef123456789");
                userService.save(user);
            }
        };
    }

}
