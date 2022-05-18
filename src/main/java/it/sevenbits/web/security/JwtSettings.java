package it.sevenbits.web.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Settings to the JWT token.
 */
@Component
public class JwtSettings {

    //TODO: add work with refresh token
    private final String tokenIssuer;
    private final String tokenSigningKey;
    private final int aTokenDuration;

    /**
     * Instantiates a new Jwt settings.
     *
     * @param tokenIssuer     the token issuer
     * @param tokenSigningKey the token signing key
     * @param aTokenDuration  the a token duration
     */
    public JwtSettings(@Value("${jwt.issuer}") final String tokenIssuer,
                       @Value("${jwt.signingKey}") final String tokenSigningKey,
                       @Value("${jwt.aTokenDuration}") final int aTokenDuration) {
        this.tokenIssuer = tokenIssuer;
        this.tokenSigningKey = tokenSigningKey;
        this.aTokenDuration = aTokenDuration;
    }

    /**
     * Gets token issuer.
     *
     * @return the token issuer
     */
    public String getTokenIssuer() {
        return tokenIssuer;
    }

    /**
     * Get token signing key byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getTokenSigningKey() {
        return tokenSigningKey.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Gets token expired in.
     *
     * @return the token expired in
     */
    public Duration getTokenExpiredIn() {
        return Duration.ofMinutes(aTokenDuration);
    }

}
