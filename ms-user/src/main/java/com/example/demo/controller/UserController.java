package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;


import com.example.demo.dto.NewUser;
import com.example.demo.model.business.UserBusiness;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;

// fat model
// fat controller (Anti-Pattern: o que não fazer)

// thin controller e um fat model
// controller deve ter apenas o essencial
// para lidar com a requisição
// as regras de negócio ficam no model (business, service, entidade)

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AbstractController {

    private final UserRepository userRepository;
    private final UserBusiness userBusiness;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${chamado.api.url}")
    private String chamadoApiUrl;

    public UserController(UserRepository userRepository,
                          UserBusiness userBusiness) {
        this.userRepository = userRepository;
        this.userBusiness = userBusiness;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<User>  createNewUser(
        @Valid
        @RequestBody
        NewUser newUser) {

        User novoUsuario = userBusiness.criarUsuario(newUser);

        try {
            System.out.println("--- MS-USER: Enviando requisicao para " + chamadoApiUrl);
            restTemplate.postForEntity(chamadoApiUrl, novoUsuario, String.class);
            System.out.println("--- MS-USER: Chamado de onboarding solicitado com sucesso!");
        } catch (RestClientException e) {
            System.err.println("--- MS-USER: FALHA ao solicitar chamado de onboarding: " + e.getMessage());
        }

        return ResponseEntity.status(201).body(novoUsuario);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
