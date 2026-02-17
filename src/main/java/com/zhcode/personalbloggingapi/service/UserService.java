package com.zhcode.personalbloggingapi.service;

import com.zhcode.personalbloggingapi.domain.User;
import com.zhcode.personalbloggingapi.dto.LoginRequest;
import com.zhcode.personalbloggingapi.dto.LoginResponse;
import com.zhcode.personalbloggingapi.dto.RegisterRequest;
import com.zhcode.personalbloggingapi.dto.UserResponse;
import com.zhcode.personalbloggingapi.exception.ConflictException;
import com.zhcode.personalbloggingapi.exception.ForbiddenException;
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
            throw new ConflictException("Username already taken");
        }

        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPasswordHash(hash);

        User saved = userRepository.save(u);

        return new UserResponse(saved.getId(), saved.getUsername(), saved.getCreatedAt());
    }

    public LoginResponse login(LoginRequest req){
        User u = userRepository.findByUsername(req.getUsername()).orElseThrow(()-> new ForbiddenException("Invalid username or password"));

        boolean ok = BCrypt.checkpw(req.getPassword(), u.getPasswordHash());
        if (!ok){
            throw new ForbiddenException("Invalid username or password");
        }

        String token = "token-" + u.getId();

        return new LoginResponse(u.getId(), u.getUsername(), token);
    }
}
