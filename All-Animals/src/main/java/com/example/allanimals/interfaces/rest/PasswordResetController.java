package com.example.allanimals.interfaces.rest;

import com.example.allanimals.application.dto.ForgotPasswordRequestDto;
import com.example.allanimals.application.dto.MessageResponseDto;
import com.example.allanimals.application.dto.ResetPasswordRequestDto;
import com.example.allanimals.application.dto.TokenValidationResponseDto;
import com.example.allanimals.application.service.PasswordResetAppService;
import com.example.allanimals.domain.exceptions.PasswordResetException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
public class PasswordResetController {

    private static final String MENSAGEM_GENERICA =
            "Se este e-mail estiver cadastrado, enviaremos as instruções de recuperação em alguns minutos.";

    private final PasswordResetAppService service;

    public PasswordResetController(PasswordResetAppService service) {
        this.service = service;
    }

    @PostMapping("/forgot")
    public ResponseEntity<MessageResponseDto> solicitar(@RequestBody @Valid ForgotPasswordRequestDto dto,
                                                        HttpServletRequest request) {
        service.solicitarRecuperacao(dto.email(), obterIp(request));
        return ResponseEntity.ok(new MessageResponseDto(MENSAGEM_GENERICA));
    }

    @GetMapping("/validate")
    public ResponseEntity<TokenValidationResponseDto> validar(@RequestParam(required = false) String token) {
        return ResponseEntity.ok(new TokenValidationResponseDto(service.tokenValido(token)));
    }

    @PostMapping("/reset")
    public ResponseEntity<MessageResponseDto> redefinir(@RequestBody @Valid ResetPasswordRequestDto dto) {
        service.redefinirSenha(dto.token(), dto.newPassword(), dto.confirmPassword());
        return ResponseEntity.ok(new MessageResponseDto("Senha redefinida com sucesso."));
    }

    @ExceptionHandler(PasswordResetException.class)
    public ResponseEntity<MessageResponseDto> tratarPasswordReset(PasswordResetException exception) {
        return ResponseEntity.badRequest().body(new MessageResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponseDto> tratarValidacao(MethodArgumentNotValidException exception) {
        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("Dados inválidos");

        return ResponseEntity.badRequest().body(new MessageResponseDto(mensagem));
    }

    private String obterIp(HttpServletRequest request) {
        String encaminhado = request.getHeader("X-Forwarded-For");

        if (encaminhado != null && !encaminhado.isBlank()) {
            return encaminhado.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }
}
