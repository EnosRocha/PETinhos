<script lang="ts" setup>
import { ref } from 'vue';
import { useAuthStore } from '@/store/storeAuth'
import { useRouter } from 'vue-router';
import { login } from '@/http/requests'
import video4 from '@/assets/videos/video4.mp4'

const email = ref('')
const password = ref('')

const router = useRouter()
const auth = useAuthStore()

async function onSubmit() {
    try {
        const token = await login(email.value, password.value)
        auth.salvarToken(token)
        router.push('/adotarOuCadastrar')
    } catch (e) {
        console.error('Erro no login:', e)
    }
}

function loginGoogle() {
    const apiUrl = import.meta.env.VITE_API_URL || 'http://localhost:8080'
    window.location.href = `${apiUrl}/oauth2/authorization/google`
}
</script>

<template>
    <div class="login-div">

        <!-- Vídeo de fundo (fora do form) -->
        <div class="videoContainer">
            <video :src="video4" autoplay muted loop playsinline />
            <div class="overlay" />
        </div>

        <form class="card" @submit.prevent="onSubmit">

            <div class="brand">🐾 PETinhos</div>

            <div class="heading">
                <h1>Bem-vindo de volta</h1>
                <p>Entre para continuar a sua adoção</p>
            </div>

            <div class="input-group">
                <label for="email">Email</label>
                <input id="email" v-model="email" type="email" name="email" placeholder="voce@email.com"
                    autocomplete="email" required />
            </div>

            <div class="input-group">
                <label for="password">Senha</label>
                <input id="password" v-model="password" type="password" name="password" placeholder="••••••••"
                    autocomplete="current-password" required />
            </div>

            <button type="button" class="btnGoogle" @click="loginGoogle">Entrar com Google</button>

            <a class="forgot" href="#">Esqueceu a senha?</a>

            <button type="submit" class="btnSubmit">Entrar</button>

            <p class="signup">
                Não tem conta?
                <button type="button" class="btnLink" @click="router.push('/cadastrarTutor')">Cadastre-se</button>
            </p>
        </form>
    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.login-div {
    position: relative;
    overflow: hidden;
    width: 100%;
    min-height: 100vh;
    padding: 24px;
    display: flex;
    justify-content: center;
    align-items: center;
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
    background: linear-gradient(to bottom, rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.6));
}

.card {
    position: relative;
    z-index: 1;
    width: 100%;
    max-width: 420px;
    display: flex;
    flex-direction: column;
    gap: 18px;
    padding: 40px 36px;
    background: #fff;
    border-radius: 24px;
    box-shadow: 0 20px 60px rgba(244, 81, 30, 0.35);
    animation: slideUp 0.6s ease-out both;

}

.brand {
    font-size: 1.4rem;
    font-weight: 800;
    letter-spacing: 1px;
    color: #ff7043;
}

.heading h1 {
    font-size: 1.9rem;
    font-weight: 800;
    color: #1a1a1a;
    line-height: 1.2;
}

.heading p {
    margin-top: 6px;
    font-size: 0.95rem;
    color: rgba(26, 26, 26, 0.55);
}

.input-group {
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.input-group label {
    font-size: 0.9rem;
    font-weight: 600;
    color: #1a1a1a;
}

.input-group input {
    width: 100%;
    padding: 13px 16px;
    font-family: inherit;
    font-size: 0.95rem;
    color: #1a1a1a;
    background: #faf9f8;
    border: 2px solid #ece9e7;
    border-radius: 12px;
    outline: none;
    transition: border-color 0.2s, background 0.2s, box-shadow 0.2s;
}

.input-group input::placeholder {
    color: rgba(26, 26, 26, 0.35);
}

.input-group input:focus {
    background: #fff;
    border-color: #ff7043;
    box-shadow: 0 0 0 4px rgba(255, 112, 67, 0.15);
}

.forgot {
    align-self: flex-end;
    margin-top: -6px;
    font-size: 0.85rem;
    font-weight: 500;
    color: #f4511e;
    text-decoration: none;
    transition: color 0.2s;
}

.forgot:hover {
    color: #ff7043;
}

.btnSubmit {
    margin-top: 4px;
    padding: 14px 24px;
    font-family: inherit;
    font-size: 1rem;
    font-weight: 600;
    color: #fff;
    background: #ff7043;
    border: none;
    border-radius: 50px;
    cursor: pointer;
    transition: background 0.2s, transform 0.2s, box-shadow 0.2s;
    box-shadow: 0 4px 20px rgba(255, 112, 67, 0.4);
}

.btnSubmit:hover {
    background: #f4511e;
    transform: translateY(-2px);
    box-shadow: 0 6px 24px rgba(255, 112, 67, 0.5);
}

.btnSubmit:active {
    transform: translateY(0);
}

.btnGoogle {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    padding: 13px 24px;
    font-family: inherit;
    font-size: 0.95rem;
    font-weight: 600;
    color: #1a1a1a;
    background: #fff;
    border: 2px solid #ece9e7;
    border-radius: 50px;
    cursor: pointer;
    transition: border-color 0.2s, box-shadow 0.2s, transform 0.2s;
}

.btnGoogle:hover {
    border-color: #d0ccc9;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
    transform: translateY(-2px);
}

.signup {
    text-align: center;
    font-size: 0.9rem;
    color: rgba(26, 26, 26, 0.6);
}

.btnLink {
    font-family: inherit;
    font-size: inherit;
    font-weight: 600;
    color: #ff7043;
    background: none;
    border: none;
    cursor: pointer;
    transition: color 0.2s;
}

.btnLink:hover {
    color: #f4511e;
}

@media (max-width: 480px) {
    .card {
        padding: 32px 24px;
    }

    .heading h1 {
        font-size: 1.6rem;
    }
}


@keyframes slideUp {
    from {
        opacity: 0;
        transform: translateY(40px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

@media (prefers-reduced-motion: reduce) {
    .card {
        animation: none;
    }
}
</style>