package com.rf.backend.controller.post;

import com.rf.backend.entity.post.Like;
import com.rf.backend.entity.post.Share;
import com.rf.backend.entity.user.User;
import com.rf.backend.error.ApiError;
import com.rf.backend.service.post.CommentService;
import com.rf.backend.service.post.LikeService;
import com.rf.backend.service.post.ShareService;
import com.rf.backend.service.user.UserService;
import com.rf.backend.user.Mesagge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    ShareService shareService;
    @Autowired
    UserService userService;

    // ...

    @PutMapping("/plusLikeCount/{postId}")
    @CrossOrigin
    public ResponseEntity<?> plusLikeCount(@PathVariable(name = "postId") Long postId) {
      Like uLike=null;
      Share exShare=null;
      for(Share share:shareService.getAllShares()){
          if(share.getId().equals(postId)){
              exShare=share;
              break;
          }
      }
      if(exShare!=null){
          uLike=likeService.findByShare(exShare);
          uLike.setCount(uLike.getCount()+1);
          likeService.save(uLike);
          return ResponseEntity.ok(exShare.getId()+" numaralı paylaşımın beğeni sayisi : " + uLike.getCount());
      }else{
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paylaşım bulunamadi");
      }

    }
    @CrossOrigin
    @PutMapping("/minusLikeCount/{postId}")
    public ResponseEntity<?> minusLikeCount(@PathVariable Long postId){
        Like like=null;
        Share share=null;
        for(Share post : shareService.getAllShares()){
            if(post.getId().equals(postId)){
                share=post;
                break;
            }
        }
        if(share!=null){
            like=likeService.findByShare(share);
            like.setCount(like.getCount()-1);
            likeService.save(like);
            return ResponseEntity.ok(share.getId()+" numaralı paylaşımın beğeni sayisi : " + like.getCount());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paylaşım bulunamadi");
        }
    }
    @CrossOrigin
    @GetMapping("/getLikeCount/{id}")
    public int getLikeCount(@PathVariable Long id){
      Like like=likeService.findById(id);
        return  like.getCount();
    }

    // ...
}
