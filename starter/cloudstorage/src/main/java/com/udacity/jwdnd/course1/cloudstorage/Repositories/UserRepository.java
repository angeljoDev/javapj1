package com.udacity.jwdnd.course1.cloudstorage.Repositories;


import com.udacity.jwdnd.course1.cloudstorage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
}
