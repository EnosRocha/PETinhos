<script lang="ts" setup>
import { useRouter } from 'vue-router'

const router = useRouter()
</script>

<template>
    <div class="sobre-page">
        <div class="hero">
            <span class="paw-big">🐾</span>
            <h1>Sobre o PETinhos</h1>
            <p class="subtitulo">Conectando pets a lares cheios de amor</p>
        </div>

        <div class="content">
            <section class="card">
                <h2>O que é o PETinhos?</h2>
                <p>
                    O PETinhos é uma plataforma de adoção de animais que conecta tutores responsáveis a pessoas
                    que desejam dar um lar para um pet. Aqui você encontra cães, gatos, pássaros e muito mais
                    esperando por uma família.
                </p>
            </section>

            <section class="card">
                <h2>Como funciona?</h2>
                <div class="steps">
                    <div class="step">
                        <span class="step-icon">📋</span>
                        <div>
                            <strong>Cadastre-se</strong>
                            <p>Crie sua conta e complete seu perfil com telefone e data de nascimento.</p>
                        </div>
                    </div>
                    <div class="step">
                        <span class="step-icon">🐶</span>
                        <div>
                            <strong>Explore os pets</strong>
                            <p>Navegue pelos animais disponíveis e filtre pelo tipo que preferir.</p>
                        </div>
                    </div>
                    <div class="step">
                        <span class="step-icon">💬</span>
                        <div>
                            <strong>Entre em contato</strong>
                            <p>Clique em "Quero adotar" e fale direto com o tutor pelo WhatsApp.</p>
                        </div>
                    </div>
                    <div class="step">
                        <span class="step-icon">🏠</span>
                        <div>
                            <strong>Adote com amor</strong>
                            <p>Combine a visita e leve seu novo companheiro para casa.</p>
                        </div>
                    </div>
                </div>
            </section>

            <section class="card">
                <h2>Quer colocar um pet para adoção?</h2>
                <p>
                    Cadastre-se como tutor, adicione fotos e informações do seu pet e aguarde
                    o contato de famílias interessadas.
                </p>
                <button class="btn-primary" @click="router.push('/cadastrarTutor')">
                    Cadastrar como tutor
                </button>
            </section>

            <section class="card missao">
                <h2>Nossa missão</h2>
                <p>
                    Acreditamos que todo animal merece um lar seguro e cheio de carinho.
                    O PETinhos nasceu para tornar o processo de adoção mais simples, humano
                    e acessível para todos.
                </p>
            </section>
        </div>
    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');

.sobre-page {
    font-family: 'Poppins', sans-serif;
    min-height: 100vh;
    background:
        radial-gradient(circle at 20% 20%, rgba(255, 255, 255, 0.35) 0%, rgba(255, 255, 255, 0) 55%),
        linear-gradient(135deg, #ff7043 0%, #ffa05a 45%, #ffcc70 100%);
    padding: 48px 32px;
}

.hero {
    text-align: center;
    margin-bottom: 48px;
}

.paw-big {
    font-size: 4rem;
    display: block;
    margin-bottom: 12px;
}

.hero h1 {
    font-size: 2.8rem;
    font-weight: 800;
    color: #fff;
    margin: 0;
    text-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

.subtitulo {
    margin-top: 8px;
    font-size: 1.1rem;
    color: rgba(255, 255, 255, 0.85);
    font-weight: 400;
}

.content {
    max-width: 760px;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    gap: 24px;
}

.card {
    background: #fff;
    border-radius: 20px;
    padding: 32px;
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.08);
}

.card h2 {
    font-size: 1.3rem;
    font-weight: 700;
    color: orangered;
    margin: 0 0 16px;
}

.card p {
    font-size: 0.95rem;
    color: rgba(26, 26, 26, 0.7);
    line-height: 1.7;
    margin: 0;
}

.steps {
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.step {
    display: flex;
    gap: 16px;
    align-items: flex-start;
}

.step-icon {
    font-size: 1.8rem;
    flex-shrink: 0;
}

.step strong {
    display: block;
    font-size: 0.95rem;
    color: #1a1a1a;
    margin-bottom: 4px;
}

.step p {
    margin: 0;
    font-size: 0.88rem;
}

.btn-primary {
    margin-top: 20px;
    padding: 12px 28px;
    background: orangered;
    color: #fff;
    border: none;
    border-radius: 50px;
    font-family: inherit;
    font-size: 0.95rem;
    font-weight: 600;
    cursor: pointer;
    transition: opacity 0.2s, transform 0.2s;
    box-shadow: 0 4px 16px rgba(255, 69, 0, 0.35);
}

.btn-primary:hover {
    opacity: 0.88;
    transform: translateY(-2px);
}

.missao {
    background: linear-gradient(135deg, #ff7043, #ffcc70);
}

.missao h2 {
    color: #fff;
}

.missao p {
    color: rgba(255, 255, 255, 0.9);
}

@media (max-width: 600px) {
    .sobre-page {
        padding: 32px 16px;
    }

    .hero h1 {
        font-size: 2rem;
    }

    .card {
        padding: 24px;
    }
}
</style>