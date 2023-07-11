package com.example.bhp.services;

import com.example.bhp.auth.LoginDTO;
import com.example.bhp.auth.UserDTO;
import com.example.bhp.entity.Users;

public interface UserServices
{
    public String addUser(UserDTO employeeDTO);
    public UserDTO loginUser(LoginDTO loginDTO);
    public Users findUser(String email);
}