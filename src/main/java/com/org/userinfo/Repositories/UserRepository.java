package com.org.userinfo.Repositories;


import com.org.userinfo.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailIgnoreCase(String email);

    User getUserById(Long id);

    User findByUsernameIgnoreCase(String username);

    Page<User> findAll(Pageable pageable);

    @Query("select user.password from User user where user.username = :username")
    String findPasswordWithOptionalUsernameString(@Param("username") Optional<String> username);

    User findOneByUsername(Optional<String> username);

    @Query("select user from User user where user.username = :username")
    User findUserDetails(@Param("username") Optional<String> username);
}
