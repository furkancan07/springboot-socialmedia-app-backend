package com.rf.backend.user;

import com.rf.backend.error.ApiError;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@RestController // çalışmasını sağlar
@RequestMapping("/api") // bu sınıfın alt tarafı şu şekilde çalışır localhost:8080/api
public class UserController {
    private  static  final  Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired // Bir constructorumuz var diyelim uzun uzun new ClassAdi(paramatre) yazmamıza gereke kalmıyor
    UserService userService;
    @CrossOrigin // forntend kısmı başka bir localde çalıştığı için bu onun önğne geçer

    @PostMapping("/users") // kaydetme işlemlerinde kullanılır
    public Mesagge createUser(@Valid /*BeAN VALİDİMİZ KULLANACAĞIMIZ SÖYLÜYORUZ*/ @RequestBody User user){// RequestBody adı üzerinde body gönderir
        // user sifre vs bos olursa nje yapacağımız

        userService.save(user);

        return new Mesagge(user.toString());





        /* String username=user.getUsername();
        String display=user.getDisplay();
        String sifre=user.getSifre();
        String tekrar=user.getTekrar();

        if(username==null || username.isEmpty()){
            return  hataYolla(user,0);
        } else if(display==null || display.isEmpty()){
            return  hataYolla(user,1);
        } else if(sifre==null || sifre.isEmpty()){
            return  hataYolla(user,2);
        }else if(tekrar==null || tekrar.isEmpty()){

            return  hataYolla(user,3);
        }

        else{
            userService.save(user);

            return ResponseEntity.ok(new Mesagge(user.toString()) );
        }*/

        /*if(username==null || username.isEmpty()){
            ApiError apiError=new ApiError(400,"Validation Hatası","/api/users");
            Map<String,String> validationErrors=new HashMap<>();
            validationErrors.put("username","kullanici adi boş olamaz");
            apiError.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError); // kullanıcı boş gelirse status 400 hatasını yolluyoruz
        }

        else{
            userService.save(user);
            return  ResponseEntity.ok(new Mesagge(user.toString()) );
        }*/

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError degistirValidationException(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Validation Hatası","/api/users");
        Map<String,String> validationErrors=new HashMap<>();
        for (FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErrors);
        }
        return  apiError;
    }

    /*public ResponseEntity<?> hataYolla(User user,int index){

        ApiError apiError=new ApiError(400,"Validation Hatası","/api/users");
        Map<String,String> validationErrors=new HashMap<>();

            switch (index){
                case 0:
                    validationErrors.put("username","kullanici adi boş olamaz");
                    apiError.setValidationErrors(validationErrors);


                case 1: validationErrors.put("display","ikinci adin boş olamaz");
                    apiError.setValidationErrors(validationErrors);

                case 2: validationErrors.put("sifre","şifre kısmı boş olamaz");
                    apiError.setValidationErrors(validationErrors);
                 case 3: validationErrors.put("tekrar","şifre kısmını boş bıraktınız ya da şifreleriniz uyuşmuyor");
                 apiError.setValidationErrors(validationErrors);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }*/

}
