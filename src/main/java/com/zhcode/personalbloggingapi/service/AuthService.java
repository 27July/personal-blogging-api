package com.zhcode.personalbloggingapi.service;

import com.zhcode.personalbloggingapi.domain.User;
import com.zhcode.personalbloggingapi.exception.UnauthorisedException;
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
            throw new UnauthorisedException("Missing authentication token");
        }

        if(!token.startsWith("token-")){
            throw new UnauthorisedException("Invalid token format");
        }

        String idPart = token.substring("token-".length());

        long userId;

        try{
            userId = Long.parseLong(idPart);
        }catch (NumberFormatException e){
            throw new UnauthorisedException("Invalid token user id");
        }

        return userRepository.findById(userId).orElseThrow(() -> new UnauthorisedException("User not found for token"));
    }
}
