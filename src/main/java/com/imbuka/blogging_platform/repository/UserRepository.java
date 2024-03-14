package com.imbuka.blogging_platform.repository;

import com.imbuka.blogging_platform.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * a method that will be used to find user by email
     */
    Optional<User> findByEmail(String email);
}
