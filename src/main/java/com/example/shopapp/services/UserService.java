package com.example.shopapp.services;

import com.example.shopapp.dtos.userDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Role;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.RoleRepository;
import com.example.shopapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User createUser(userDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number alreadly exists");
        }
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facobookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        try{
            Role role = roleRepository.findById(Long.valueOf(userDTO.getRoleId()))
                    .orElseThrow(() -> new DataNotFoundException("role not found"));
            newUser.setRole(role);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }



        if(userDTO.getFacebookAccountId() ==0 && userDTO.getGoogleAccountId() ==0) {
            String password = userDTO.getPassword();
//            String encodedPassword = passwordEncoder.encode(password);
//            newUser.setPassword(encodedPassword);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        // lam trong spring sercurity
        return null;
    }
}
