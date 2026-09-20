package com.example.allanimals.infrastructure.persistence.jpa.entities;


import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;
import com.example.allanimals.infrastructure.persistence.jpa.converters.EmailConverter;
import com.example.allanimals.infrastructure.persistence.jpa.converters.TelefoneConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tutor_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TutorEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true, unique = true)
    @Convert(converter = TelefoneConverter.class)
    private Telefone phone;

    @Column(nullable = true)
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    @Convert(converter = EmailConverter.class)
    private Email email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider = AuthProvider.LOCAL;

    public enum AuthProvider {
        LOCAL, GOOGLE
    }


    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Telefone getPhone() {
        return phone;
    }

    public void setPhone(Telefone phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
