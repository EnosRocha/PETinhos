<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { solicitarRecuperacaoSenha } from '@/http/requests'

const router = useRouter()

const email = ref('')
const erro = ref('')
const enviando = ref(false)
const enviado = ref(false)

async function enviar() {
    erro.value = ''
    enviando.value = true

    try {
        await solicitarRecuperacaoSenha(email.value)
        enviado.value = true
    } catch (e: any) {
        erro.value = e.response?.data?.message || 'Não foi possível enviar as instruções. Tente novamente.'
    } finally {
        enviando.value = false
    }
}
</script>

<template>
    <div class="login-div">
        <form class="card" @submit.prevent="enviar">
            <div class="brand">🐾 PETinhos</div>

            <div class="heading">
                <h1>Recuperar senha</h1>
                <p v-if="!enviado">Informe o e-mail da sua conta e enviaremos um link para você criar uma nova senha</p>
                <p v-else>Confira a sua caixa de entrada</p>
            </div>

            <template v-if="!enviado">
                <div class="input-group">
                    <label for="email">Email</label>
                    <input id="email" v-model="email" type="email" placeholder="voce@email.com"
                        autocomplete="email" required />
                </div>

                <p v-if="erro" class="erro" role="alert">{{ erro }}</p>

                <button type="submit" class="btnSubmit" :disabled="enviando">
                    {{ enviando ? 'Enviando...' : 'Enviar link de recuperação' }}
                </button>
            </template>

            <div v-else class="aviso">
                <p>Se este e-mail estiver cadastrado, as instruções chegarão em alguns minutos.</p>
                <p>O link vale por 30 minutos e só pode ser usado uma vez.</p>
            </div>

            <p class="signup">
                <button type="button" class="btnLink" @click="router.push('/login')">Voltar para o login</button>
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
    width: 100%;
    min-height: 100vh;
    padding: 24px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-family: 'Poppins', sans-serif;
    background:
        radial-gradient(circle at 20% 20%, rgba(255, 255, 255, 0.35) 0%, rgba(255, 255, 255, 0) 55%),
        linear-gradient(135deg, #ff7043 0%, #ffa05a 45%, #ffcc70 100%);
}

.card {
    width: 100%;
    max-width: 420px;
    display: flex;
    flex-direction: column;
    gap: 18px;
    padding: 40px 36px;
    background: #fff;
    border-radius: 24px;
    box-shadow: 0 20px 60px rgba(244, 81, 30, 0.35);
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
    line-height: 1.5;
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

.erro {
    padding: 10px 12px;
    font-size: 0.9rem;
    color: #a12600;
    background: #ffe3d8;
    border-radius: 12px;
}

.aviso {
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 16px;
    font-size: 0.9rem;
    line-height: 1.5;
    color: #5a3a28;
    background: #fff1e6;
    border: 1px solid #ffd2b3;
    border-radius: 14px;
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

.btnSubmit:hover:not(:disabled) {
    background: #f4511e;
    transform: translateY(-2px);
}

.btnSubmit:disabled {
    opacity: 0.6;
    cursor: not-allowed;
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
</style>
