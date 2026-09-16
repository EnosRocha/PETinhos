package com.example.allanimals.infrastructure.security;


import com.example.allanimals.application.service.JwtService;
import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.repositories.TutorRepository;
import com.example.allanimals.infrastructure.persistence.jpa.entities.TutorEntity;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import com.example.allanimals.infrastructure.persistence.jpa.mappers.TutorMapperInfra;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final TutorRepositoryImpl tutorRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        Tutor tutor = tutorRepository.findByEmail(new Email(email));

        boolean isPresent = tutor.getTutorId() != null ? true : false;

        if (!isPresent) {
            TutorEntity novo = new TutorEntity();
            novo.setName(name);
            novo.setEmail(new Email(email));
            novo.setProvider(TutorEntity.AuthProvider.GOOGLE);

            Tutor tutorDomain = TutorMapperInfra.toDomain(novo);
            tutorRepository.save(tutorDomain);
        };

        String token = jwtService.gerarToken(email);

        response.sendRedirect("http://localhost:5173/oauth2/callback?token=" + token);
    }
}

