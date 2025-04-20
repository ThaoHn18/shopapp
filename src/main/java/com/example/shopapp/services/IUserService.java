package com.example.shopapp.services;

import com.example.shopapp.dtos.userDTO;
import com.example.shopapp.models.User;

public interface IUserService {
    User createUser(userDTO userDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;
}
