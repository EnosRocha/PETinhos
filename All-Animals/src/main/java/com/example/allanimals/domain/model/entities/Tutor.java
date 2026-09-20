package com.example.allanimals.domain.model.entities;

import com.example.allanimals.domain.model.objectValue.Email;
import com.example.allanimals.domain.model.objectValue.Telefone;

import java.time.LocalDate;
import java.util.UUID;

public class Tutor {

    private UUID tutorId;
    private String name;
    private String password;
    private Telefone phone;
    private LocalDate birthday;
    private Email email;
    private AuthProvider provider;

    public enum AuthProvider {
        LOCAL, GOOGLE
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Telefone getPhone() {
        return phone;
    }

    public void setPhone(Telefone phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public Tutor(UUID tutorId, String name, String password, Telefone phone, LocalDate birthday, Email email, AuthProvider provider) {
        this.tutorId = tutorId;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.provider = provider;
    }

    public Tutor() {

    }
}
