package com.rf.backend.controller.post;

import com.rf.backend.entity.post.Comment;
import com.rf.backend.entity.post.Share;
import com.rf.backend.entity.user.User;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.post.CommentService;
import com.rf.backend.service.post.ShareService;
import com.rf.backend.service.user.UserService;
import com.rf.backend.user.Mesagge;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @CrossOrigin
    public ResponseEntity<?> addComment(@PathVariable("username") String username, @PathVariable("postId") Long postId, @Valid @RequestBody Comment comment){
        Share share=null;
        User user=null;
        if(shareService.existingShare(postId) && userService.kullaniciVarMi(username)){
           share=shareService.findById(postId);
           comment.setShare(share);
           user=userService.bulKullanici(username);
           comment.setUser(user);
           commentService.kaydet(comment);
           return ResponseEntity.ok("yorum eklendi");
       }else{
            ApiError apiError=new ApiError(404,"kullanici veya paylaşım bulunamadi","api/addComment");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
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
