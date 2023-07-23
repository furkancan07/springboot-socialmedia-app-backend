package com.rf.backend.repository.post;
import com.rf.backend.entity.post.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share,Long>{



}
