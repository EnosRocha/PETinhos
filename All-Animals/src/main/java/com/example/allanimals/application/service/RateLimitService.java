package com.example.allanimals.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private static final Duration JANELA = Duration.ofHours(1);

    private final Map<String, Deque<Instant>> registros = new ConcurrentHashMap<>();
    private final int maximoPorJanela;

    public RateLimitService(@Value("${app.password-reset.max-requests-per-hour:3}") int maximoPorJanela) {
        this.maximoPorJanela = maximoPorJanela;
    }

    public boolean permitir(String chave) {
        Instant agora = Instant.now();
        Deque<Instant> tentativas = registros.computeIfAbsent(chave, chaveCriada -> new ArrayDeque<>());

        synchronized (tentativas) {
            while (!tentativas.isEmpty() && Duration.between(tentativas.peekFirst(), agora).compareTo(JANELA) >= 0) {
                tentativas.pollFirst();
            }

            if (tentativas.size() >= maximoPorJanela) {
                return false;
            }

            tentativas.addLast(agora);
            return true;
        }
    }
}
