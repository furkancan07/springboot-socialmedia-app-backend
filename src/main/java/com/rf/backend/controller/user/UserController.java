package com.rf.backend.controller.user;
import com.rf.backend.dto.DUser;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.user.UserService;
import com.rf.backend.dto.StatusOkMessagge;
import com.rf.backend.entity.user.User;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private  static  final  Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @CrossOrigin

    @PostMapping("/users") // kaydetme işlemlerinde kullanılır

    public StatusOkMessagge createUser(@Valid @RequestBody User user){
        userService.getAllUsers().add(user);
            userService.kaydet(user);

           return new StatusOkMessagge("isim: "+ user.getUsername()+" id: " + user.getId());
    }
    @GetMapping("/getAllUsers")
    @CrossOrigin
    public List<DUser> getUsers(){
        List<DUser> dUsers=new ArrayList<>();
        for(User user : userService.getAllUsers()){
            DUser dUser=new DUser();
            dUser.setId((long)user.getId());
            dUser.setUsername(user.getUsername());
            dUsers.add(dUser);
        }
        return dUsers;
    }
    public String getUsername(@RequestBody User user){
        return  user.getUsername();
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

}



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
/*
*   if(userService.kullaniciVarmi(user.getUsername())){
            ApiError apiError=new ApiError(400,"Validation Hatası","/api/users");
            Map<String,String> validationErrors=new HashMap<>();
            validationErrors.put("username","bu kullanici adi kullanilmiş tekrar dene");
            apiError.setValidationErrors(validationErrors);
            return  new Mesagge(apiError.toString());
        }
*
* */
