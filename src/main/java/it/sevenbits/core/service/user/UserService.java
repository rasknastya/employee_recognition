package it.sevenbits.core.service.user;

import it.sevenbits.core.model.User;
import it.sevenbits.core.repository.users.UserRepository;
import it.sevenbits.core.security.PasswordEncoder;
import it.sevenbits.core.service.marks.MarkService;
import it.sevenbits.web.controller.exception.BadRequestException;
import it.sevenbits.web.controller.exception.ForbiddenException;
import it.sevenbits.web.controller.exception.NotAvailableException;
import it.sevenbits.web.model.RegisterUserRequest;
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
import java.math.BigDecimal;
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
            throw new NotAvailableException();
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

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        User user = new User(UUID.randomUUID().toString(), request.getEmail(), request.getFullName(),
                roles, embedding, passwordEncoder.hashPassword(request.getPassword()));

        return userRepository.addUser(user);
    }
}
