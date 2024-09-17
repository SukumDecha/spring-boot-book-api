package me.sukumdev.bookapi.controller;

import lombok.AllArgsConstructor;
import me.sukumdev.bookapi.dto.UserAuthDto;
import me.sukumdev.bookapi.dto.UserRegisterDto;
import me.sukumdev.bookapi.entity.User;
import me.sukumdev.bookapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService service;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.ok(service.authenticate(userAuthDto));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(service.register(userRegisterDto));
    }
}
