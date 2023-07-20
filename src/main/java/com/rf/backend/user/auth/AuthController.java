package com.rf.backend.user.auth;

import com.rf.backend.error.ApiError;
import com.rf.backend.user.User;
import com.rf.backend.user.UserController;
import com.rf.backend.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private  static  final Logger log= LoggerFactory.getLogger(AuthController.class);
    private  User user;

private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/auth")
    @CrossOrigin
     ResponseEntity<?> login(@RequestHeader(name = "Authorization",required = false) String authorization){
log.info(authorization);
       if(authorization==null){
           ApiError apiError=new ApiError(401,"hata","/api/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }
          String[] authSplit=authorization.split("Basic");
          String basic=authSplit[0];
          String decoder=authSplit[1];
          String decode=decoder.split(" ")[1];
          String a=new String(Base64.getDecoder().decode(decode));
          log.info(a);
          String[] path=a.split(":");
          String username=path[0];
          String sifre=path[1];
          log.info(String.valueOf(sifre.length()));
          log.info(username);
          // kullanici var ise yapılacak işlemler
          if(userRepository.existsByUsername(username)){
              user=userRepository.findByUsername(username);
              log.info(user.toString());
              if(passwordEncoder.matches(sifre,user.getSifre())){
                  // bAŞARILI Giriş
                  return ResponseEntity.ok("Giriş başarılı");
              }else{
                  // yanlış şifre
                  ApiError apiError=new ApiError(401,"Yanliş Şifre","/api/auth");
                  Map<String,String > validationErrors=new HashMap<>();
                  validationErrors.put("sifre","Şifreniz Yanlış Tekrar Deneyin");
                  apiError.setValidationErrors(validationErrors);
                  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
              }

          }else{
              ApiError apiError = new ApiError(401, "Hata", "/api/auth");
              Map<String,String > validationErrors=new HashMap<>();
              validationErrors.put("username","Sistemde Kayitli değilsiniz Kaydolun");
              apiError.setValidationErrors(validationErrors);
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
          }












    }
}
