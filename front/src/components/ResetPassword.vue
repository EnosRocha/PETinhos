<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { redefinirSenha, validarTokenRecuperacaoSenha } from '@/http/requests'

const route = useRoute()
const router = useRouter()

const token = ref('')
const validando = ref(true)
const linkInvalido = ref(false)
const concluido = ref(false)
const erro = ref('')
const enviando = ref(false)

const form = reactive({
    password: '',
    confirmPassword: '',
})

onMounted(async () => {
    const valor = route.query.token
    token.value = typeof valor === 'string' ? valor : ''

    if (!token.value) {
        validando.value = false
        linkInvalido.value = true
        return
    }

    try {
        const resposta = await validarTokenRecuperacaoSenha(token.value)
        linkInvalido.value = !resposta.valid
    } catch (e) {
        linkInvalido.value = true
    } finally {
        validando.value = false
    }
})

async function enviar() {
    erro.value = ''

    if (form.password !== form.confirmPassword) {
        erro.value = 'As senhas não coincidem'
        return
    }

    enviando.value = true

    try {
        await redefinirSenha(token.value, form.password, form.confirmPassword)
        concluido.value = true
    } catch (e: any) {
        erro.value = e.response?.data?.message || 'Não foi possível redefinir a senha.'
    } finally {
        enviando.value = false
    }
}
</script>

<template>
    <div class="login-div">
        <div class="card">
            <div class="brand">🐾 PETinhos</div>

            <div v-if="validando" class="heading">
                <h1>Validando o link</h1>
                <p>Aguarde um instante enquanto conferimos o seu link de recuperação</p>
            </div>

            <template v-else-if="linkInvalido">
                <div class="heading">
                    <h1>Link inválido</h1>
                    <p>Este link de recuperação expirou ou já foi utilizado</p>
                </div>

                <div class="aviso">
                    <p>Os links de recuperação valem por 30 minutos e só podem ser usados uma vez.</p>
                </div>

                <button type="button" class="btnSubmit" @click="router.push('/esqueci-senha')">
                    Pedir um novo link
                </button>
            </template>

            <template v-else-if="concluido">
                <div class="heading">
                    <h1>Senha alterada</h1>
                    <p>Sua nova senha já está valendo</p>
                </div>

                <div class="aviso">
                    <p>Enviamos um aviso para o seu e-mail. Se não reconhece esta alteração, peça um novo link de recuperação imediatamente.</p>
                </div>

                <button type="button" class="btnSubmit" @click="router.push('/login')">
                    Ir para o login
                </button>
            </template>

            <form v-else @submit.prevent="enviar">
                <div class="heading">
                    <h1>Criar nova senha</h1>
                    <p>Defina uma senha com pelo menos 6 caracteres</p>
                </div>

                <div class="input-group">
                    <label for="password">Nova senha</label>
                    <input id="password" v-model="form.password" type="password" placeholder="••••••••"
                        autocomplete="new-password" minlength="6" required />
                </div>

                <div class="input-group">
                    <label for="confirmPassword">Confirmar senha</label>
                    <input id="confirmPassword" v-model="form.confirmPassword" type="password" placeholder="••••••••"
                        autocomplete="new-password" minlength="6" required />
                </div>

                <p v-if="erro" class="erro" role="alert">{{ erro }}</p>

                <button type="submit" class="btnSubmit" :disabled="enviando">
                    {{ enviando ? 'Salvando...' : 'Salvar nova senha' }}
                </button>
            </form>

            <p class="signup">
                <button type="button" class="btnLink" @click="router.push('/login')">Voltar para o login</button>
            </p>
        </div>
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

form {
    display: flex;
    flex-direction: column;
    gap: 18px;
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
