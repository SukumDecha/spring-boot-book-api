package me.sukumdev.bookapi.config;


import lombok.RequiredArgsConstructor;
import me.sukumdev.bookapi.entity.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepo
                .findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var daoProvider = new DaoAuthenticationProvider(passwordEncoder());

        // Set the UserDetailsService to retrieve user details from the database
        daoProvider.setUserDetailsService(userDetailsService());

        return daoProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
