package com.org.userinfo.Repositories;


import com.org.userinfo.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailIgnoreCase(String email);
    User getUserById(Long id);
    User findByUsernameIgnoreCase(String username);
    Page<User> findAll(Pageable pageable);
}
