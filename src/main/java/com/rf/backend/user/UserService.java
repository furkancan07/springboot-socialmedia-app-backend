package com.rf.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder; // paswwordu görmemizi sağlar

   // @Autowired // sadece 1 constructoe varsa auto vired kullanmak zorunda değiliz
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=new BCryptPasswordEncoder();

    }


    public void  save(User user){
        user.setSifre(this.passwordEncoder.encode(user.getSifre()));
        userRepository.save(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }

}
