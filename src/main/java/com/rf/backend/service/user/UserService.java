package com.rf.backend.service.user;

import com.rf.backend.entity.user.User;
import com.rf.backend.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder; // paswwordu görmememizi sağlar
    List<User> users=new ArrayList<>();

    @Autowired // sadece 1 constructoe varsa auto vired kullanmak zorunda değiliz
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }
    public List<User> getAllUsers(){
        return  users;

    }



    public void kaydet( User user){
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
