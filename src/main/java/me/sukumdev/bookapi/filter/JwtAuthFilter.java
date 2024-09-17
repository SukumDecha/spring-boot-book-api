package me.sukumdev.bookapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.sukumdev.bookapi.entity.User;
import me.sukumdev.bookapi.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if(jwtToken.startsWith("Bearer")) {
            jwtToken = jwtToken.substring(7);
            // Find username from token
            String username = jwtService.extractSubject(jwtToken);
            SecurityContext context = SecurityContextHolder.getContext();

            // If user is not authenticated and username is not null
            if(context.getAuthentication() == null && username != null) {
                // Load user from database
                User user = (User) userDetailsService.loadUserByUsername(username);

                // If token is valid, set authentication
                if(jwtService.isTokenValid(jwtToken)) {
                    // Set authentication
                    var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    context.setAuthentication(authToken);
                }

                filterChain.doFilter(request, response);
            }

        }
    }
}
