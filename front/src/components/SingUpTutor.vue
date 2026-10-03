<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { cadastrarTutor } from "@/http/requests"

const router = useRouter()

const form = reactive({
    name: '',
    password: '',
    confirmPassword: '',
    phone: '',
    birthday: '',
    email: '',
})

const erro = ref('')
const enviando = ref(false)

async function enviar() {
    erro.value = ''

    if (form.password !== form.confirmPassword) {
        erro.value = 'As senhas não coincidem'
        return
    }

    enviando.value = true

    try {
        await cadastrarTutor({
            name: form.name,
            password: form.password,
            phone: form.phone,
            birthday: form.birthday,
            email: form.email,
        })
        router.push('/adotarOuCadastrar')
    } catch (e: any) {
        erro.value = e.response?.data?.message || 'Erro ao cadastrar.'
    } finally {
        enviando.value = false
    }
}
</script>

<template>
    <div id="main">
        <form class="card" @submit.prevent="enviar">
            <h2> Cadastrar Tutor</h2>

            <label>Nome
                <input v-model="form.name" maxlength="150" required />
            </label>

            <label>Email
                <input v-model="form.email" type="email" required />
            </label>

            <label>Telefone
                <input v-model="form.phone" type="tel" required />
            </label>

            <label>Data de nascimento
                <input v-model="form.birthday" type="date" required />
            </label>

            <div class="row">
                <label>Senha
                    <input v-model="form.password" type="password" minlength="6" required />
                </label>
                <label>Confirmar senha
                    <input v-model="form.confirmPassword" type="password" minlength="6" required />
                </label>
            </div>

            <p v-if="erro" class="erro">{{ erro }}</p>

            <div class="actions">
                <button type="button" class="voltar" @click="router.back()">Voltar</button>
                <button type="submit" class="enviar" :disabled="enviando">
                    {{ enviando ? 'Enviando...' : 'Cadastrar' }}
                </button>
            </div>
        </form>
    </div>
</template>

<style scoped>
#main {
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
    max-width: 560px;
    display: flex;
    flex-direction: column;
    gap: 14px;
    padding: 28px;
    box-sizing: border-box;
    background: #fff;
    border-radius: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.12);
}

h2 {
    margin: 0;
    color: orangered;
}

label {
    display: flex;
    flex-direction: column;
    gap: 6px;
    flex: 1;
    font-size: 0.9rem;
    font-weight: 600;
    color: #5a3a28;
}

input,
select {
    padding: 10px 12px;
    font: inherit;
    font-weight: 400;
    border: 1px solid #ffd2b3;
    border-radius: 10px;
    background: #fff9f5;
    outline: none;
    transition: border-color 0.2s, box-shadow 0.2s;
}

input:focus,
select:focus {
    border-color: orangered;
    box-shadow: 0 0 0 3px rgba(255, 69, 0, 0.15);
}

.row {
    display: flex;
    gap: 12px;
}

.erro {
    margin: 0;
    padding: 10px 12px;
    color: #a12600;
    background: #ffe3d8;
    border-radius: 10px;
}

.actions {
    display: flex;
    gap: 12px;
    justify-content: flex-end;
}

.actions button {
    padding: 12px 24px;
    font-weight: 600;
    border: none;
    border-radius: 12px;
    cursor: pointer;
    transition: transform 0.2s, background-color 0.2s;
}

.actions button:hover:not(:disabled) {
    transform: translateY(-2px);
}

.enviar {
    color: #fff;
    background: orangered;
}

.enviar:disabled {
    opacity: 0.6;
    cursor: not-allowed;
}

.voltar {
    color: #5a3a28;
    background: #fff1e6;
}

@media (max-width: 480px) {
    .row {
        flex-direction: column;
    }
}
</style>