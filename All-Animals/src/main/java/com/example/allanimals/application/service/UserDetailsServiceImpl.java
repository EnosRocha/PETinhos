package com.example.allanimals.application.service;


import com.example.allanimals.domain.model.entities.Tutor;
import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.infrastructure.persistence.jpa.impl.TutorRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TutorRepositoryImpl tutorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tutor tutor = tutorRepository.findByEmail(new Email(email));

        return User.builder()
                .username(tutor.getEmail().getEmail())
                .password(tutor.getPassword() != null ? tutor.getPassword() : "")
                .roles("USER")
                .build();
    }
}

