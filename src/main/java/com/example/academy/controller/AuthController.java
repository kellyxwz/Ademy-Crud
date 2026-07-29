package com.example.academy.controller;

import com.example.academy.config.TokenJwt;
import com.example.academy.dto.request.RegisterRequest;
import com.example.academy.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenJwt jwt;
    private final UsuarioService service;

    public AuthController(AuthenticationManager authenticationManager, TokenJwt jwt, UsuarioService service) {
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
        this.service = service;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body ){
        try {
            String username = body.get("username");
            String password = body.get("password");
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        String role = auth.getAuthorities().stream().findFirst().map(a -> a.getAuthority().replace("ROLE_", "")).orElse("USER");
        String token = jwt.generateToken(username, role);
        Map<String, String> response = new HashMap<>();

            response.put("token", token);
            response.put("role", role);

        return response;

        }catch (AuthenticationException e){
            throw new RuntimeException("Usuario não autenticado");
        }
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest body){
        String username = body.username();
        String password = body.password();
        String role = body.role() == null || body.role().isBlank() ? "ALUNO" : body.role() ;

        if (!role.equals("ALUNO") && !role.equals("PROFESSOR")){
            throw new RuntimeException("Role inválida! Use ALUNO ou PROFESSOR");
        }

        service.userSave(username,password,role);

        return Map.of("message","Usuário salvo com sucesso");
    }


}
