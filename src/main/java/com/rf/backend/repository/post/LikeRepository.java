package com.rf.backend.repository.post;

import com.rf.backend.entity.post.Like;
import com.rf.backend.entity.post.Share;
import com.rf.backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    public Like findByShare(Share share);
}
