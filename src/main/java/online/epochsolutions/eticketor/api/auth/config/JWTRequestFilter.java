package online.epochsolutions.eticketor.api.auth.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.epochsolutions.eticketor.api.auth.repositories.UserRepository;
import online.epochsolutions.eticketor.api.auth.services.JWTService;
import online.epochsolutions.eticketor.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserRepository userRepository;

    public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            try{
                String email = jwtService.getEmail(token);
                Optional<User> opUser = userRepository.findByEmailIgnoreCase(email);
                if (opUser.isPresent()){
                    User user = opUser.get();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }catch (JWTDecodeException ex){

            }
        }

        filterChain.doFilter(request,response);
    }
}
