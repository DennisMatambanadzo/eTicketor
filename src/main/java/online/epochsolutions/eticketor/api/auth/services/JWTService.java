package online.epochsolutions.eticketor.api.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;
    public static final String EMAIL_KEY = "EMAIL";
    public static final String ROLE_KEY = "ROLES";
    public static final String TICKET_KEY = "USERS";

    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(User user){
        return JWT.create()
                .withClaim(EMAIL_KEY,user.getEmail())
                .withClaim(ROLE_KEY, user.getRole().ordinal())
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000 + expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateJWT( Ticket ticket){
        return JWT.create()
                .withClaim(TICKET_KEY, ticket.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000 + expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getEmail(String token){
        return JWT.decode(token).getClaim(EMAIL_KEY).asString();
    }
}
