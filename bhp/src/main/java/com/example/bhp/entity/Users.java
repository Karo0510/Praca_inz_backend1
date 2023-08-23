package com.example.bhp.entity;

import com.sun.jdi.LongType;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    //@OneToOne(fetch = FetchType.LAZY, optional = true)
    //private Employees employee;

    public Users(Long id, String username, String email, String encode)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = encode;
        this.role = "ROLE_ADMIN";
    }


}