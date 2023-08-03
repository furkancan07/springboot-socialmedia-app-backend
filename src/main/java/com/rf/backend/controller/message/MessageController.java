package com.rf.backend.controller.message;

import com.rf.backend.dto.DMessage;
import com.rf.backend.dto.DUser;
import com.rf.backend.entity.message.Message;
import com.rf.backend.entity.user.User;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.message.MessageService;
import com.rf.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @PostMapping("/addMessage/{sender}/{receiver}")
    @CrossOrigin
    public ResponseEntity<?> addMessage(@PathVariable String sender, @PathVariable String receiver, @RequestBody Message message){

User gonder;
User alici;
        if(userService.kullaniciVarMi(sender) && userService.kullaniciVarMi(receiver)){
            gonder=userService.findByUserName(sender);
            alici=userService.findByUserName(receiver);
            message.setSender(gonder);
            message.setReceiver(alici);
             messageService.save(message);
             messageService.getAllMessages().add(message);
             return ResponseEntity.ok("Mesaj gönderildi");
        }else{
            ApiError apiError=new ApiError(404,"Kullanıcı bulunamadı","api/addMessage");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }
    }
    @GetMapping("/getMessages/{sender}/{receiver}")
    @CrossOrigin
    public List<DMessage> getMessages(@PathVariable String sender, @PathVariable String receiver){
        List<DMessage> dmessages=new ArrayList<>();

        for(Message message : messageService.getAllMessages()){
            if((message.getSender().getUsername().equals(sender) && message.getReceiver().getUsername().equals(receiver))
            || (message.getSender().getUsername().equals(receiver) && message.getReceiver().getUsername().equals(sender))
            ){
               DMessage dMessage=new DMessage();
               dMessage.setId(message.getId());
               dMessage.setContent(message.getContent());
               dMessage.setDate(message.getDate());

               DUser gonderen=new DUser();
               gonderen.setId((long)message.getSender().getId());
               gonderen.setUsername(message.getSender().getUsername());
               gonderen.setImage(message.getSender().getImage());

               DUser alici=new DUser();
               alici.setId((long)message.getReceiver().getId());
               alici.setUsername(message.getReceiver().getUsername());
               alici.setImage(message.getReceiver().getImage());

               dMessage.setSender(gonderen);
               dMessage.setReceiver(alici);

               dmessages.add(dMessage);
            }
        }
        return dmessages;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError degistirValidationException(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"Validation Hatası","/api/Message");
        Map<String,String> validationErrors=new HashMap<>();
        for (FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErrors);
        }
        return  apiError;
    }

}

