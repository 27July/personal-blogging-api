package com.zhcode.personalbloggingapi.service;

import com.zhcode.personalbloggingapi.domain.User;
import com.zhcode.personalbloggingapi.dto.RegisterRequest;
import com.zhcode.personalbloggingapi.dto.UserResponse;
import com.zhcode.personalbloggingapi.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(RegisterRequest req){
        if (userRepository.existsByUsername(req.getUsername())){
            throw new RuntimeException("Username already taken");
        }

        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPasswordHash(hash);

        User saved = userRepository.save(u);

        return new UserResponse(saved.getId(), saved.getUsername(), saved.getCreatedAt());
    }
}
