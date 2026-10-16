<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { completarMeuPerfil } from '@/http/requests'

const router = useRouter()
const form = reactive({ phone: '', birthday: '' })
const erro = ref('')
const enviando = ref(false)

async function enviar() {
    erro.value = ''
    enviando.value = true
    try {
        await completarMeuPerfil({
            phone: form.phone.replace(/\D/g, ''),
            birthday: form.birthday,
        })
        await router.replace('/adotarOuCadastrar')
    } catch (e) {
        console.error('Erro ao completar perfil:', e)
        erro.value = 'Não foi possível salvar. Confira os dados e tente novamente.'
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
                <h1>Quase lá!</h1>
                <p>Precisamos de mais duas informações para continuar</p>
            </div>

            <div class="input-group">
                <label for="phone">Telefone</label>
                <input id="phone" v-model="form.phone" type="tel" placeholder="(84) 99999-9999"
                    autocomplete="tel" required />
            </div>

            <div class="input-group">
                <label for="birthday">Data de nascimento</label>
                <input id="birthday" v-model="form.birthday" type="date" autocomplete="bday" required />
            </div>

            <p v-if="erro" class="erro" role="alert">{{ erro }}</p>

            <button type="submit" class="btnSubmit" :disabled="enviando">
                {{ enviando ? 'Salvando...' : 'Salvar e continuar' }}
            </button>
        </form>
    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');

* { margin: 0; padding: 0; box-sizing: border-box; }

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

.brand { font-size: 1.4rem; font-weight: 800; letter-spacing: 1px; color: #ff7043; }
.heading h1 { font-size: 1.9rem; font-weight: 800; color: #1a1a1a; line-height: 1.2; }
.heading p { margin-top: 6px; font-size: 0.95rem; color: rgba(26, 26, 26, 0.55); }

.input-group { display: flex; flex-direction: column; gap: 8px; }
.input-group label { font-size: 0.9rem; font-weight: 600; color: #1a1a1a; }
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
.input-group input:focus {
    background: #fff;
    border-color: #ff7043;
    box-shadow: 0 0 0 4px rgba(255, 112, 67, 0.15);
}

.erro { padding: 10px 12px; font-size: 0.9rem; color: #a12600; background: #ffe3d8; border-radius: 12px; }

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
.btnSubmit:hover:not(:disabled) { background: #f4511e; transform: translateY(-2px); }
.btnSubmit:disabled { opacity: 0.6; cursor: not-allowed; }
</style>