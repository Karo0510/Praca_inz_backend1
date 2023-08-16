package com.example.bhp.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Data
public class LoginDTO
{
    private String email;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
