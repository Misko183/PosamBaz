package com.example.backend.proSecurity.rest;


import com.example.backend.proSecurity.dto.ResponseDTO;
import com.example.backend.proSecurity.dto.UserDTO;
import com.example.backend.proSecurity.session.InMemorySessionRegistry;
import com.example.backend.proSecurity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public InMemorySessionRegistry sessionRegistry;

    @Autowired
    public UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        System.out.println(user.getUsername());
        System.out.println(userRepository.findByUsername(user.getUsername()).getAuthority());

        final String autority = userRepository.findByUsername(user.getUsername()).getAuthority();
        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);
        response.setAuthority(autority);
        return ResponseEntity.ok(response);
    }
}
