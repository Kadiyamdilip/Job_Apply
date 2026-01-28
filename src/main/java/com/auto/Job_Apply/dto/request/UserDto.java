package com.auto.Job_Apply.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
