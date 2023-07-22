package com.rf.backend.repository;
import com.rf.backend.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShareRepository extends JpaRepository<Share,Long>{



}
