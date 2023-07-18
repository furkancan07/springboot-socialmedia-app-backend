package com.rf.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;

// veri tabanına bağlandığımız interface
public interface UserRepository extends JpaRepository<User,Long> {

}
