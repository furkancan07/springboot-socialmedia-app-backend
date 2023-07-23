package com.rf.backend.controller.user.auth;

import com.rf.backend.entity.user.ChangePassword;
import com.rf.backend.error.ApiError;
import com.rf.backend.entity.user.User;
import com.rf.backend.service.user.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    UserService userService;

    // kullanici girisi
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
          if(userService.kullaniciVarMi(username)){
              user=userService.bulKullanici(username);
            //  log.info(user.toString());
              if(passwordEncoder.matches(sifre,user.getSifre())){
                  // bAŞARILI Giriş
                  return ResponseEntity.ok(user.getUsername());
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
    // şifre değiştirme
    @PostMapping("/forgot")
    @CrossOrigin
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ChangePassword changePassword) {
        User user1;
     if(userService.kullaniciVarMi(changePassword.getUsername())){
         user1=userService.bulKullanici(changePassword.getUsername());
         user1.setSifre(changePassword.getYeniSifre());
         userService.kaydet(user1);
         return ResponseEntity.ok("Şifre Değiştirildi");
     }
     else{
         ApiError apiError = new ApiError(401, "Böyle Bir Kullanici Bulunmamakta", "/api/forgot");
         Map<String,String > validationErrors=new HashMap<>();
         validationErrors.put("username","Sistemde Kayitli değilsiniz Kaydolun");
         apiError.setValidationErrors(validationErrors);
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
     }
// hata dönüştürme
    } @ExceptionHandler(MethodArgumentNotValidException.class)// bu hatada api erroru dönüştür
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400 hatsaını döndür
    public ApiError degistirValidationException(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"YeniSifre","/api/forgot");
        Map<String,String> validationErrors=new HashMap<>();
        for (FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErrors);
        }
        return  apiError;
    }


}
