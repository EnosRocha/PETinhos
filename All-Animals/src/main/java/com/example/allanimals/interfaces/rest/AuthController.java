package com.example.allanimals.interfaces.rest;


import com.example.allanimals.application.dto.TutorRequestDto;
import com.example.allanimals.application.dto.TutorResponseDto;
import com.example.allanimals.application.service.JwtService;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.repositories.TutorRepository;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TutorRepositoryImpl tutorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody TutorRequestDto request) {
        Tutor tutor = new Tutor();
        tutor.setName(request.name());
        tutor.setEmail(new Email(request.email()));
        tutor.setPassword(passwordEncoder.encode(request.password()));
        tutor.setProvider(Tutor.AuthProvider.LOCAL);
        tutorRepository.save(tutor);

        String token = jwtService.gerarToken(request.email());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody TutorRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        String token = jwtService.gerarToken(request.email());
        return ResponseEntity.ok(token);
    }

    public record RegisterRequest(String name, String email, String password) {
    }

    public record LoginRequest(String email, String password) {
    }
}

