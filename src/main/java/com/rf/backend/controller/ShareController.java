package com.rf.backend.controller;

import com.rf.backend.entity.Share;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.ShareService;
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
    @PostMapping("/createShare")
    public Mesagge createNewShare(@Valid @RequestBody Share share){
        shareService.Kaydet(share);
        shareService.getAllShares().add(share);

        return new Mesagge(share.toString());
    }
    @GetMapping("/getShares")
    public List<Share> getShares(){


        return shareService.getAllShares();
    }
    @PutMapping("/updatedShare/{id}") // istek dönerken /updatedShared/2 diye yazılacak
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

        @DeleteMapping("/deleteShare/{id}")
        public ResponseEntity<?> deleteShare(@PathVariable Long id){
        if(shareService.existingShare(id)){
            shareService.sil(id);
return ResponseEntity.ok("Paylaşım Başari ile silindi");

        }else{
            System.out.println("Böyle bir kullanici yok ");
            ApiError apiError=new ApiError(404,"Böyle bir paylaşım bulunamdadi","api/deleteShare");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }

        }

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
