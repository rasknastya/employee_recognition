package it.sevenbits.courses.example.auth.core.service.user;

import it.sevenbits.courses.example.auth.core.model.User;
import it.sevenbits.courses.example.auth.core.repository.users.UserRepository;
import it.sevenbits.courses.example.auth.core.security.BCryptPasswordEncoder;
import it.sevenbits.courses.example.auth.core.security.PasswordEncoder;
import it.sevenbits.courses.example.auth.core.service.marks.MarkService;
import it.sevenbits.courses.example.auth.web.controller.exception.BadRequestException;
import it.sevenbits.courses.example.auth.web.controller.exception.ForbiddenException;
import it.sevenbits.courses.example.auth.web.controller.exception.NotAvailableException;
import it.sevenbits.courses.example.auth.web.model.RegisterUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(MarkService.class);

    public UserService(RestTemplateBuilder restTemplateBuilder, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Map<String, BigDecimal[]> getEmbeddings() {
        return userRepository.getEmbeddings();
    }

    public User registerUser(RegisterUserRequest request) throws IOException, SQLException {
        if (userRepository.findUserByEmail(request.getEmail()) != null) {
            throw new ForbiddenException("User with this email already registered");
        }

        String url = "http://127.0.0.1:8000/getembedding";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();

        for (MultipartFile file : request.getFiles()) {
            if (file.isEmpty()) {
                logger.error("Images are not found");
                throw new BadRequestException("Images are not found");
            }
            body.add("files", file.getBytes());
        }

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        BigDecimal[] embedding = this.restTemplate.postForObject(url, entity, BigDecimal[].class);

        System.out.println("2");
        List<String> roles = new ArrayList<>();

        User user = new User(UUID.randomUUID().toString(), request.getEmail(), request.getFullName(),
                roles, embedding, passwordEncoder.hashPassword(request.getPassword()));

        return userRepository.addUser(user);
    }
}
