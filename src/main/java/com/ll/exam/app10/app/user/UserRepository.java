package com.ll.exam.app10.app.user;

import com.ll.exam.app10.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByloginId(String loginId);

}
