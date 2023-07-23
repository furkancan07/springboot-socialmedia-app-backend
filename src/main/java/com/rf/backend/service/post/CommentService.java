package com.rf.backend.service.post;

import com.rf.backend.entity.post.Comment;
import com.rf.backend.repository.post.CommentRepository;
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
