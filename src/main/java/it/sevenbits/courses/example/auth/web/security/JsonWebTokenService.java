package it.sevenbits.courses.example.auth.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Service to generate and parse JWT tokens.
 */
public class JsonWebTokenService implements JwtTokenService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtSettings settings;
    private static final String ROLES = "roles";
    private final UserRepository userRepository;

    public JsonWebTokenService(final JwtSettings settings, UserRepository userRepository) {
        this.settings = settings;
        this.userRepository = userRepository;
    }

    @Override
    public String createToken(User user) {
        logger.debug("Generating token for {}", user.getEmail());

        Instant now = Instant.now();

        Claims claims = Jwts.claims()
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now))
                .setSubject(user.getEmail())
                .setExpiration(Date.from(now.plus(settings.getTokenExpiredIn())));
        claims.put(ROLES, user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserCredentials parseToken(String token) {
        logger.debug("Parsing token {}", token);

        Jws<Claims> claims = Jwts.parser().setSigningKey(settings.getTokenSigningKey()).parseClaimsJws(token);

        String subject = claims.getBody().getSubject();
        List<String> roles = claims.getBody().get(ROLES, List.class);

        return new UserCredentialsImpl(subject, Collections.unmodifiableList(roles));
    }

}
