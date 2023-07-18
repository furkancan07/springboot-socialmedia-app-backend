package com.rf.backend.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController // çalışmasını sağlar
@RequestMapping("/api") // bu sınıfın alt tarafı şu şekilde çalışır localhost:8080/api
public class UserController {
    private  static  final  Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired // genelde service repositpry olunca çağrılır
    UserService userService;
    @CrossOrigin // forntend kısmı başka bir localde çalıştığı için bu onun önğne geçer

    @PostMapping("/users") // kaydetme işlemlerinde kullanılır
    public Mesagge createUser(@RequestBody User user){// RequestBody adı üzerinde body gönderir
       userService.save(user);
       return  new Mesagge("kral naber");

    }



}
