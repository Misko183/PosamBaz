package com.example.backend.proSecurity.rest;


import com.example.backend.proSecurity.configuration.AppConfig;
import com.example.backend.proSecurity.user.UserEntity;
import com.example.backend.proSecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    public UserRepository repository;

    @Autowired
    public AppConfig config;


@PostMapping("/userRegister")
public void userRegister(@RequestBody UserEntity user) {

    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(user.getUsername());
    userEntity.setPassword(config.passwordEncoder().encode(user.getPassword()));
    userEntity.setAuthority("USER");
    userEntity.setAddress(user.getAddress());
    userEntity.setPhone(user.getPhone());
    userEntity.setEmail(user.getEmail());

    repository.save(userEntity);
}


}
