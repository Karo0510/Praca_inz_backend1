package com.example.bhp.controller;
import com.example.bhp.auth.CustomUserDetailsService;
import com.example.bhp.auth.LoginDTO;
import com.example.bhp.auth.UserDTO;
import com.example.bhp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    //@PostMapping(value="/login")
    // public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    //{
        /*String username = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        UserDTO res = userServices.loginUser(loginDTO);

        if (res != null)
        {
            Authentication authentication = new UsernamePasswordAuthenticationToken(res, res.getPassword(), res.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }*/

    //return null;
    //}


    @PostMapping(value = "/home")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {
        //System.out.println("vskdjhcf;jlas");
        return ResponseEntity.ok(loginDTO.getEmail());
    }

}
