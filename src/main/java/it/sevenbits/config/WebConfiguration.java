package it.sevenbits.config;

import it.sevenbits.web.security.JwtAuthInterceptor;
import it.sevenbits.web.security.JwtTokenService;
import it.sevenbits.web.security.UserCredentialsResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * The type Web configuration.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final JwtTokenService jwtTokenService;

    /**
     * Instantiates a new Web configuration.
     *
     * @param jwtTokenService the jwt token service
     */
    public WebConfiguration(
        final JwtTokenService jwtTokenService
    ) {
        this.jwtTokenService = jwtTokenService;
    }


    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserCredentialsResolver());
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(
            new JwtAuthInterceptor(jwtTokenService)
        );
    }

}
