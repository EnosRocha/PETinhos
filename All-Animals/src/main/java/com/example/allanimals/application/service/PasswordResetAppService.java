package com.example.allanimals.application.service;

import com.example.allanimals.domain.exceptions.PasswordResetException;
import com.example.allanimals.domain.model.entities.PasswordResetToken;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.service.PasswordResetService;
import com.example.allanimals.infrastructure.persistence.jpa.impl.PasswordResetTokenRepositoryImpl;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PasswordResetAppService {

    private final TutorRepositoryImpl tutorRepository;
    private final PasswordResetTokenRepositoryImpl tokenRepository;
    private final PasswordResetService passwordResetService;
    private final MailAppService mailAppService;
    private final RateLimitService rateLimitService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetAppService(TutorRepositoryImpl tutorRepository,
                                   PasswordResetTokenRepositoryImpl tokenRepository,
                                   PasswordResetService passwordResetService,
                                   MailAppService mailAppService,
                                   RateLimitService rateLimitService,
                                   PasswordEncoder passwordEncoder) {
        this.tutorRepository = tutorRepository;
        this.tokenRepository = tokenRepository;
        this.passwordResetService = passwordResetService;
        this.mailAppService = mailAppService;
        this.rateLimitService = rateLimitService;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${app.frontend-url:http://localhost:5173}")
    private String frontendUrl;

    @Value("${app.password-reset.ttl-minutes:30}")
    private long ttlMinutes;

    @Transactional
    public void solicitarRecuperacao(String email, String ip) {
        Email emailValue;

        try {
            emailValue = new Email(email);
        } catch (IllegalArgumentException exception) {
            return;
        }

        boolean permitidoPorEmail = rateLimitService.permitir("email:" + emailValue.getEmail());
        boolean permitidoPorIp = rateLimitService.permitir("ip:" + ip);

        if (!permitidoPorEmail || !permitidoPorIp) {
            return;
        }

        Tutor tutor = tutorRepository.findByEmail(emailValue);

        if (tutor == null) {
            return;
        }

        if (tutor.getProvider() == Tutor.AuthProvider.GOOGLE || tutor.getPassword() == null) {
            mailAppService.enviarContaGoogle(emailValue.getEmail(), tutor.getName());
            return;
        }

        LocalDateTime agora = LocalDateTime.now();
        tokenRepository.invalidateAllByTutorId(tutor.getTutorId(), agora);

        String token = passwordResetService.generateToken();
        PasswordResetToken registro = new PasswordResetToken(
                tutor.getTutorId(),
                passwordResetService.hash(token),
                agora.plusMinutes(ttlMinutes),
                agora,
                ip
        );

        tokenRepository.save(registro);

        String link = frontendUrl + "/redefinir-senha?token=" + token;
        mailAppService.enviarRecuperacaoSenha(emailValue.getEmail(), tutor.getName(), link, ttlMinutes);
    }

    @Transactional(readOnly = true)
    public boolean tokenValido(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }

        PasswordResetToken registro = tokenRepository.findByTokenHash(passwordResetService.hash(token));

        return registro != null && registro.isUsable(LocalDateTime.now());
    }

    @Transactional
    public void redefinirSenha(String token, String novaSenha, String confirmacao) {
        if (!novaSenha.equals(confirmacao)) {
            throw new PasswordResetException("As senhas não coincidem");
        }

        LocalDateTime agora = LocalDateTime.now();
        String hash = passwordResetService.hash(token);
        PasswordResetToken registro = tokenRepository.findByTokenHash(hash);
        passwordResetService.ensureUsable(registro, agora);

        Tutor tutor = buscarTutor(registro.getTutorId());

        if (tutor.getPassword() != null && passwordEncoder.matches(novaSenha, tutor.getPassword())) {
            throw new PasswordResetException("A nova senha deve ser diferente da senha atual");
        }

        if (!tokenRepository.consume(hash, agora)) {
            throw new PasswordResetException("Token inválido ou expirado");
        }

        tutor.setPassword(passwordEncoder.encode(novaSenha));
        tutorRepository.update(tutor);
        tokenRepository.invalidateAllByTutorId(tutor.getTutorId(), agora);

        mailAppService.enviarSenhaAlterada(tutor.getEmail().getEmail(), tutor.getName());
    }

    private Tutor buscarTutor(java.util.UUID tutorId) {
        try {
            return tutorRepository.getTutor(tutorId);
        } catch (RuntimeException exception) {
            throw new PasswordResetException("Token inválido ou expirado");
        }
    }
}
