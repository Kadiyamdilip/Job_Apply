package com.auto.Job_Apply.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private UserDto user;
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for user
    public UserDto getUser() {
        return user;
    }

    // Setter for user
    public void setUser(UserDto user) {
        this.user = user;
    }
}
