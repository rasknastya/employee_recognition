package it.sevenbits.courses.example.auth.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The type Json web token service.
 */
@Service
public class JsonWebTokenService implements JwtTokenService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtSettings settings;
    private final String roles = "roles";
    private final UserRepository userRepository;

    /**
     * Instantiates a new Json web token service.
     *
     * @param settings       the settings
     * @param userRepository the user repository
     */
    public JsonWebTokenService(final JwtSettings settings, final UserRepository userRepository) {
        this.settings = settings;
        this.userRepository = userRepository;
    }

    @Override
    public String createToken(final User user) {
        logger.debug("Generating token for {}", user.getEmail());
        Instant now = Instant.now();

        Claims claims = Jwts.claims()
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now))
                .setSubject(user.getEmail())
                .setExpiration(Date.from(now.plus(settings.getTokenExpiredIn())));
        claims.put(roles, user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserCredentials parseToken(final String token) {
        logger.debug("Parsing token {}", token);

        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(settings.getTokenSigningKey()).parseClaimsJws(token);

        String subject = claims.getBody().getSubject();
        List<String> roles = claims.getBody().get(this.roles, List.class);
        String userId = userRepository.findUserByEmail(subject).getUserId();
        System.out.println(userId);

        return new UserCredentialsImpl(userId, subject, Collections.unmodifiableList(roles));
    }
}


