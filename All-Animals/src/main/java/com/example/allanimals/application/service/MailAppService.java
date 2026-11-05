package com.example.allanimals.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class MailAppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailAppService.class);
    private static final String TEMPLATE = "email/recuperacao-senha";
    private static final Locale LOCALE = Locale.forLanguageTag("pt-BR");

    private final SpringTemplateEngine templateEngine;
    private final RestClient restClient;
    private final String remetenteEmail;
    private final String remetenteNome;

    public MailAppService(SpringTemplateEngine templateEngine,
                          @Value("${app.mail.brevo.api-key:}") String apiKey,
                          @Value("${app.mail.from:no-reply@petinhos.app}") String remetenteEmail,
                          @Value("${app.mail.from-name:PETinhos}") String remetenteNome) {
        this.templateEngine = templateEngine;
        this.remetenteEmail = remetenteEmail;
        this.remetenteNome = remetenteNome;

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(10000);

        this.restClient = RestClient.builder()
                .baseUrl("https://api.brevo.com/v3")
                .requestFactory(factory)
                .defaultHeader("api-key", apiKey)
                .defaultHeader("accept", "application/json")
                .build();
    }

    @Async("mailExecutor")
    public void enviarRecuperacaoSenha(String destino, String nome, String link, long minutos) {
        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("nome", nome);
        variaveis.put("titulo", "Recuperação de senha");
        variaveis.put("mensagem", "Recebemos um pedido para redefinir a senha da sua conta no PETinhos. Clique no botão abaixo para criar uma nova senha.");
        variaveis.put("link", link);
        variaveis.put("textoBotao", "Criar nova senha");
        variaveis.put("aviso", "Se você não fez este pedido, ignore este e-mail. Sua senha continua a mesma.");
        variaveis.put("prazoMinutos", minutos);

        enviar(destino, "Recuperação de senha - PETinhos", variaveis);
    }

    @Async("mailExecutor")
    public void enviarSenhaAlterada(String destino, String nome) {
        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("nome", nome);
        variaveis.put("titulo", "Sua senha foi alterada");
        variaveis.put("mensagem", "A senha da sua conta no PETinhos foi alterada com sucesso. Você já pode entrar com a nova senha.");
        variaveis.put("link", null);
        variaveis.put("textoBotao", null);
        variaveis.put("aviso", "Se você não reconhece esta alteração, solicite uma nova recuperação de senha imediatamente.");
        variaveis.put("prazoMinutos", null);

        enviar(destino, "Sua senha foi alterada - PETinhos", variaveis);
    }

    @Async("mailExecutor")
    public void enviarContaGoogle(String destino, String nome) {
        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("nome", nome);
        variaveis.put("titulo", "Sua conta usa o login do Google");
        variaveis.put("mensagem", "Recebemos um pedido de recuperação de senha, mas sua conta no PETinhos foi criada com o login do Google e não possui senha cadastrada.");
        variaveis.put("link", null);
        variaveis.put("textoBotao", null);
        variaveis.put("aviso", "Para entrar, use o botão Entrar com Google na tela de login.");
        variaveis.put("prazoMinutos", null);

        enviar(destino, "Como entrar na sua conta PETinhos", variaveis);
    }

    private void enviar(String destino, String assunto, Map<String, Object> variaveis) {
        try {
            Context context = new Context(LOCALE, variaveis);
            String html = templateEngine.process(TEMPLATE, context);

            Map<String, Object> payload = new HashMap<>();
            payload.put("sender", Map.of("name", remetenteNome, "email", remetenteEmail));
            payload.put("to", List.of(Map.of("email", destino)));
            payload.put("subject", assunto);
            payload.put("htmlContent", html);

            restClient.post()
                    .uri("/smtp/email")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception exception) {
            LOGGER.warn("Falha ao enviar e-mail transacional: {}", exception.getMessage());
        }
    }
}
