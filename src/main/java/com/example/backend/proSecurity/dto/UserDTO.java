package com.example.backend.proSecurity.dto;

public class UserDTO {
    private String username;
    private String password;
    private String authority;
    private String email;
    private String address;
    private Long phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
