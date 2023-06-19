package com.example.backend.proSecurity.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUsernameIgnoreCase(String username);
    UserEntity findByEmailIgnoreCase(String email);
}
