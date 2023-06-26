package com.example.bhp.services;

import com.example.bhp.auth.LoginDTO;
import com.example.bhp.auth.UserDTO;
import com.example.bhp.auth.Users;
import com.example.bhp.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String addUser(UserDTO userDTO)
    {
        Users user = new Users(userDTO.getId(), userDTO.getUsername(), userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
        usersRepository.save(user);

        return user.getUsername();
    }

    public Users findUser(String email)
    {
        return usersRepository.findByEmail(email);
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) {

        Users user = usersRepository.findByEmail(loginDTO.getEmail());

        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();

            Boolean isRight = passwordEncoder.matches(password, encodedPassword);
            if (isRight) {
                Optional<Users> employee = usersRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new UserDTO(employee.get());
                }

            }
        }
        return null;
    }
}
