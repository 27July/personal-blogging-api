package com.zhcode.personalbloggingapi.controller;

import com.zhcode.personalbloggingapi.dto.RegisterRequest;
import com.zhcode.personalbloggingapi.dto.UserResponse;
import com.zhcode.personalbloggingapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest req){
        return userService.register(req);
    }
}
