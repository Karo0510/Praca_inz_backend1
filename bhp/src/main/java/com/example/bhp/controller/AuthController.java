package com.example.bhp.controller;
import com.example.bhp.auth.CustomUserDetailsService;
import com.example.bhp.auth.LoginDTO;
import com.example.bhp.auth.UserDTO;
import com.example.bhp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@EntityScan(basePackages = {"com.example"})
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping(value = "/save")
    public String saveUser(@RequestBody UserDTO userDTO) {
        String id = userServices.addUser(userDTO);
        return id;
    }


    @PostMapping(value = "/home")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {

        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication());
        return ResponseEntity.ok(context.getAuthentication().isAuthenticated());
    }


    @GetMapping(value = "/login")
    public ResponseEntity<?> login() {

        SecurityContext context = SecurityContextHolder.getContext();

        boolean ans = context.getAuthentication().isAuthenticated();

        System.out.println(ans);

        if (ans == false)
        {
            return new ResponseEntity<String>("", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

}
