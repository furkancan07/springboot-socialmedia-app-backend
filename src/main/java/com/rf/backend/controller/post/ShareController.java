package com.rf.backend.controller.post;

import com.rf.backend.entity.post.Like;
import com.rf.backend.entity.post.Share;
import com.rf.backend.entity.user.User;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.post.LikeService;
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

import java.util.*;

@RestController
@RequestMapping("/api")
public class ShareController {

    @Autowired
    ShareService shareService;
    @Autowired
    UserService userService;
    @Autowired
    LikeService likeService;

    // yeni post oluşturma
    @CrossOrigin
    @PostMapping("/createShare/{username}")
    public ResponseEntity<?> createNewShare(@PathVariable(name = "username")String username , @Valid @RequestBody Share share, Like like){
        User user=null;
        if(userService.kullaniciVarMi(username)){
           user=userService.bulKullanici(username);
           share.setUser(user);
           shareService.Kaydet(share);
            like.setShare(share);
           likeService.save(like);

           shareService.getAllShares().add(share);
           return ResponseEntity.ok(user.getUsername()+" ' e ait paylaşım eklendi");
       }
       else{
           ApiError apiError=new ApiError(404,"kullanici bulunamadi","api/createShare");
            Map<String,String> validationErrors=new HashMap<>();
            validationErrors.put("username","Kullanici bulunamadi");
            apiError.setValidationErrors(validationErrors);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }



    }
    // tüm postları getirme
    @GetMapping("/getShares")
    @CrossOrigin
    public List<Share> getShares(){


        return shareService.getAllShares();
    }
    // paylaşımı güncelleme
    @PutMapping("/updatedShare/{id}") // istek dönerken /updatedShared/2 diye yazılacak
    @CrossOrigin
    public Mesagge updateShare(@PathVariable Long id, @Valid @RequestBody Share updateShare){
        Share existingShare=null;
        for (Share share:shareService.getAllShares()) {
          if(share.getId().equals(id)){
              existingShare=share;
              break;
          }
        }
        if(existingShare!=null){
            // title güncelleme
            existingShare.setTitle(updateShare.getTitle());
            // description güncelleme
            existingShare.setDescription(updateShare.getDescription());
            shareService.Kaydet(existingShare);
            return new Mesagge("Paylaşım güncellendi: " + existingShare.toString());
        }else{
            return new Mesagge("Kimlik numarasına sahip paylaşım bulunamadı: " + id);
        }
        }
        // kullaniciya ait paylaşımı getirme
        @CrossOrigin
        @GetMapping("/getUserPost/{username}")
        public List<Share> getUserPost(@PathVariable String username){
        List<Share> newShareList=new ArrayList<>();
               for (Share share : shareService.getAllShares()){
                    if(share.getUser().getUsername().equals(username)){
                        newShareList.add(share);
                    }
               }
               return newShareList;

        }

       // paylaşımı sil
        @DeleteMapping("/deleteShare/{id}")
        @CrossOrigin
        public ResponseEntity<?> deleteShare(@PathVariable Long id){
        Like like=null;
        Share share=null;
        if(shareService.existingShare(id)){
           share=shareService.findById(id);
           like=likeService.findByShare(share);
           likeService.delete(like);
           shareService.sil(id);

return ResponseEntity.ok("Paylaşım Başari ile " +
        "silindi");

        }else{
            System.out.println("Böyle bir kullanici yok ");
            ApiError apiError=new ApiError(404,"Böyle bir paylaşım bulunamdadi","api/deleteShare");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }

        }

   // hata döndürme
    @ExceptionHandler(MethodArgumentNotValidException.class)// bu hatada api erroru dönüştür
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400 hatsaını döndür
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
