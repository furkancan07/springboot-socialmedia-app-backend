package com.rf.backend.service.user;

import com.rf.backend.entity.user.User;
import com.rf.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder; // paswwordu görmemizi sağlar

    @Autowired // sadece 1 constructoe varsa auto vired kullanmak zorunda değiliz
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }


    public void kaydet(User user){
        user.setSifre(this.passwordEncoder.encode(user.getSifre()));
        userRepository.save(user);
    }
    public void sil(String username){
        User user=userRepository.findByUsername(username);
        userRepository.delete(user);
    }
    public boolean kullaniciVarMi(String username){
        return  userRepository.existsByUsername(username);
    }
    public User bulKullanici(String username){
        User user=userRepository.findByUsername(username);
        return  user;
    }

}
