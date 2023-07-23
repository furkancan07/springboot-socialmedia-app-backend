package com.rf.backend.service;

import com.rf.backend.entity.Comment;
import com.rf.backend.entity.Share;
import com.rf.backend.repository.CommentRepository;
import com.rf.backend.repository.ShareRepository;
import com.rf.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    CommentRepository commentRepository;

@Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;

    }




    public void kaydet(Comment comment){
        commentRepository.save(comment);
    }

}
