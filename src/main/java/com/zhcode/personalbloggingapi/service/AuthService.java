package com.zhcode.personalbloggingapi.service;

import com.zhcode.personalbloggingapi.domain.User;
import com.zhcode.personalbloggingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User requireUserFromToken(String token){
        if(token == null || token.isBlank()){
            throw new RuntimeException("Missing authentication token");
        }

        if(!token.startsWith("token-")){
            throw new RuntimeException("Invalid token format");
        }

        String idPart = token.substring("token-".length());

        long userId;

        try{
            userId = Long.parseLong(idPart);
        }catch (NumberFormatException e){
            throw new RuntimeException("Invalid token user id");
        }

        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found for token"));
    }
}
