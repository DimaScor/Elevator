package org.myproject.repositories;

import org.myproject.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long l);

    User findUserByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    List<User> findUsersById(@Param("userId") Long userId);

    Page<User> findAll(Pageable pageable);

}
