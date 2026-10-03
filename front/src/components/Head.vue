<script setup lang="ts">
import { ref } from 'vue'

const videos: string[] = [
    "/src/assets/videos/video1.mp4",
    "/src/assets/videos/video2.mp4",
    "/src/assets/videos/video3.mp4"
]

const currentIndex = ref(0)

function nextVideo() {
    currentIndex.value = (currentIndex.value + 1) % videos.length
}
</script>

<template>
    <header>
        <div class="headerSite">

            
            <div class="videoContainer">
                <video :src="videos[currentIndex]" autoplay muted @ended="nextVideo" />
                <div class="overlay" />
            </div>

            <nav class="navBar">
                <div class="navLogo">
                    🐾 PETinhos
                </div>
                <ul class="navLinks">
                    <li><Router-link to="/">Home</Router-link></li>
                    <li><Router-link to="/about">Sobre</Router-link></li>
                </ul>
                <div class="navAuth">
                    <Router-link to="/login" class="btnLogin">Entrar</Router-link>
                    <Router-link to="/cadastrarTutor" class="btnSignup">Cadastrar</Router-link>
                </div>
            </nav>

            
            <div class="hero">
                <h1>Encontre seu novo <span class="highlight">melhor amigo</span></h1>
                <p>Adote um pet e transforme duas vidas</p>
                <router-link to="/ShowPets" class="btnCta">Ver animais disponíveis</router-link>
            </div>

        </div>
    </header>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700;800&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.headerSite {
    width: 100%;
    height: 100vh;
    position: relative;
    overflow: hidden;
    font-family: 'Poppins', sans-serif;
}


.videoContainer {
    position: absolute;
    inset: 0;
    z-index: 0;
}

.videoContainer video {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.overlay {
    position: absolute;
    inset: 0;
    background: linear-gradient(to bottom, rgba(0,0,0,0.4), rgba(0,0,0,0.6));
}


.navBar {
    position: relative;
    z-index: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 24px 48px;
}

.navLogo {
    font-size: 1.6rem;
    font-weight: 800;
    color: #fff;
    letter-spacing: 1px;
}

.navLinks {
    display: flex;
    list-style: none;
    gap: 32px;
}

.navLinks a {
    color: rgba(255,255,255,0.85);
    text-decoration: none;
    font-size: 1rem;
    font-weight: 500;
    transition: color 0.2s;
}

.navLinks a:hover {
    color: #fff;
}

.navAuth {
    display: flex;
    gap: 12px;
    align-items: center;
}

.btnLogin {
    color: #fff;
    text-decoration: none;
    font-size: 0.95rem;
    font-weight: 500;
    padding: 8px 20px;
    border: 2px solid rgba(255,255,255,0.6);
    border-radius: 50px;
    transition: all 0.2s;
}

.btnLogin:hover {
    background: rgba(255,255,255,0.15);
    border-color: #fff;
}

.btnSignup {
    color: #1a1a1a;
    text-decoration: none;
    font-size: 0.95rem;
    font-weight: 600;
    padding: 8px 20px;
    background: #fff;
    border-radius: 50px;
    transition: all 0.2s;
}

.btnSignup:hover {
    background: #f0f0f0;
    transform: scale(1.03);
}


.hero {
    position: relative;
    z-index: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: calc(100vh - 90px);
    text-align: center;
    padding: 0 24px;
    gap: 16px;
}

.hero h1 {
    font-size: clamp(2.2rem, 5vw, 4rem);
    font-weight: 800;
    color: #fff;
    line-height: 1.2;
    text-shadow: 0 2px 12px rgba(0,0,0,0.3);
}

.highlight {
    color: #ffcc70;
}

.hero p {
    font-size: 1.2rem;
    color: rgba(255,255,255,0.85);
    font-weight: 400;
}

.btnCta {
    margin-top: 8px;
    padding: 14px 36px;
    background: #ff7043;
    color: #fff;
    text-decoration: none;
    font-size: 1rem;
    font-weight: 600;
    border-radius: 50px;
    transition: all 0.2s;
    box-shadow: 0 4px 20px rgba(255,112,67,0.4);
}

.btnCta:hover {
    background: #f4511e;
    transform: translateY(-2px);
    box-shadow: 0 6px 24px rgba(255,112,67,0.5);
}
</style>