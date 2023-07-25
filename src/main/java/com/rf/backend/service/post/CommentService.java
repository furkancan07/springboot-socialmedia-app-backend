package com.rf.backend.service.post;

import com.rf.backend.entity.post.Comment;
import com.rf.backend.entity.post.Share;
import com.rf.backend.repository.post.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    CommentRepository commentRepository;
    List<Comment> comments=new ArrayList<>();
    public List<Comment> getAllComment(){
        return comments;
    }

@Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;

    }
    public void kaydet(Comment comment){
        commentRepository.save(comment);
    }


}
