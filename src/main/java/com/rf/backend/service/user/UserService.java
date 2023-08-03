package com.rf.backend.service.user;

import com.rf.backend.dto.DUser;
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
   public PasswordEncoder passwordEncoder; // paswwordu görmememizi sağlar
    List<User> users=new ArrayList<>();

    @Autowired // sadece 1 constructoe varsa auto vired kullanmak zorunda değiliz
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder=new BCryptPasswordEncoder();
    }
    public List<User> getAllUsers(){
        return  users;

    }

    public boolean isFind(List<DUser> dUsers){
        for(int i=0;i<dUsers.size();i++){
            for(int j=0;j<dUsers.size();j++){
                if(i!=j && dUsers.get(i).getUsername().equals(dUsers.get(j).getUsername())){

                    return  false;
                }
            }

        }
        return  true;
    }

    public void kaydet(User user) {
        userRepository.save(user);
    }
    public void sil(String username){
        User user=userRepository.findByUsername(username);
        userRepository.delete(user);
    }
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
    public boolean kullaniciVarMi(String username){
        return  userRepository.existsByUsername(username);
    }
    public User bulKullanici(String username){
        User user=userRepository.findByUsername(username);
        return  user;
    }

}
