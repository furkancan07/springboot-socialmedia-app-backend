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

    // ...
}
