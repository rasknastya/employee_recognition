package it.sevenbits.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring interceptor for JWT based authentication and authorization
 */
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The constant USER_CREDENTIALS.
     */
    public static String USER_CREDENTIALS = "userCredentialsAttr";

    private final JwtTokenService jwtService;

    /**
     * Instantiates a new Jwt auth interceptor.
     *
     * @param jwtService the jwt service
     */
    public JwtAuthInterceptor(
        final JwtTokenService jwtService
    ) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            return checkAuthorization(method, request, response);
        }
        return true;
    }

    private UserCredentials getUserCredentials(final HttpServletRequest request) {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith("Bearer ")) {
                return null;
            }

            String token = header.substring(7);
            UserCredentials credentials = jwtService.parseToken(token);
            logger.debug("Found credentials in Authorization header: {}", credentials.getEmail());
            request.setAttribute(USER_CREDENTIALS, credentials);
            return credentials;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean checkAuthorization(
        final Method method,
        final HttpServletRequest request,
        final HttpServletResponse response
    ) {
        try {
            UserCredentials userCredentials = getUserCredentials(request);

            if (method.isAnnotationPresent(AuthRoleRequired.class)) {
                if (userCredentials == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return false;
                }
                AuthRoleRequired annotation = method.getAnnotation(AuthRoleRequired.class);
                String requiredRole = annotation.value();
                Set<String> userRoles = new HashSet<>();
                if (userCredentials.getRoles() != null) {
                    userRoles.addAll(userCredentials.getRoles());
                }
                boolean isAuthorized = userRoles.contains(requiredRole);
                if (!isAuthorized) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Not enough permissions");
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
