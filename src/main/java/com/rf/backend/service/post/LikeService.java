package com.rf.backend.service.post;

import com.rf.backend.entity.post.Like;
import com.rf.backend.entity.post.Share;
import com.rf.backend.repository.post.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    LikeRepository likeRepository;
@Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    public Like findByShare(Share share){
    return likeRepository.findByShare(share);
    }
    public Like findById(Long id){return  likeRepository.findById(id).orElse(null);}
    public void save(Like like){

    likeRepository.save(like);
    }
    public void delete(Like like){
    likeRepository.delete(like);
    }
}
