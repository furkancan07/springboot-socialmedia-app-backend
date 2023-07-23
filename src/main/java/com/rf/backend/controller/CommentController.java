package com.rf.backend.controller;

import com.rf.backend.entity.Comment;
import com.rf.backend.entity.Share;
import com.rf.backend.entity.User;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.CommentService;
import com.rf.backend.service.ShareService;
import com.rf.backend.service.UserService;
import com.rf.backend.user.Mesagge;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    ShareService shareService;
    @Autowired
    UserService userService;
    @PostMapping("/addComment/{username}/{postId}")
    public Mesagge addComment(@PathVariable("username") String username,@PathVariable("postId") Long postId, @Valid @RequestBody Comment comment){
        Share share=null;
        User user=null;
        if(shareService.existingShare(postId) && userService.kullaniciVarMi(username)){
           share=shareService.findById(postId);
           comment.setShare(share);
           user=userService.bulKullanici(username);
           comment.setUser(user);
           commentService.kaydet(comment);
           return new Mesagge("eklendi");
       }else{
            return  null;
        }

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validationException(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError(400,"yorum hatası","api/addComment");
        Map<String ,String> validationErrors=new HashMap<>();
        for (FieldError fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            apiError.setValidationErrors(validationErrors);
        }
        return  apiError;
    }

}
