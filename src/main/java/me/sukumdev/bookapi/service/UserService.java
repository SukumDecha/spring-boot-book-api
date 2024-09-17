package me.sukumdev.bookapi.service;

import lombok.RequiredArgsConstructor;
import me.sukumdev.bookapi.dto.UserAuthDto;
import me.sukumdev.bookapi.dto.UserRegisterDto;
import me.sukumdev.bookapi.entity.User;
import me.sukumdev.bookapi.entity.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final UserDetailsService userDetailsService;
    private final AuthenticationManager authManager;

    public String authenticate(UserAuthDto authDto) {
        System.out.println("authDto = " + authDto.getEmail());
        User user = (User) userDetailsService.loadUserByUsername(authDto.getEmail());
        if(user == null) {
            throw new UsernameNotFoundException("User with the provided email is not found");
        }

        if(!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Authenticate user by set authentication to SecurityContext
        authManager.authenticate(new UsernamePasswordAuthenticationToken
                (authDto.getEmail(), authDto.getPassword()));

        return jwtService.generateToken(Map.of("sub", authDto.getEmail()));
    }

    public User register(UserRegisterDto userRegisterDto) {
        if(repository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with the provided email already exists");
        }

        User user = User.builder()
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .age(userRegisterDto.getAge())
                .build();

        return repository.save(user);
    }
}
