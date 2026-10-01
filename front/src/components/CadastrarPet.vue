<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { cadastrarPet } from "@/http/requests"

const router = useRouter()


const tipos = [
    { value: 'CACHORRO', label: 'Cachorro' },
    { value: 'GATO', label: 'Gato' },
    { value: 'PASSARO', label: 'Pássaro' },
    { value: 'PORCO', label: 'Porco' },
    { value: 'CAVALO', label: 'Cavalo' },
    { value: 'VACA', label: 'Vaca' },
    { value: 'PORCO_DA_INDIA', label: 'Porquinho-da-índia' },
]

const form = reactive({
    name: '',
    tipoAnimal: '',
    raca: '',
    peso: null as number | null,
    cor: '',
    idade: null as number | null,
    donoId: '',
    descricao: '',
    endereco: '',
})

const files = ref<File[]>([])
const previews = ref<string[]>([])
const erro = ref('')
const enviando = ref(false)

function onFiles(e: Event) {
    const input = e.target as HTMLInputElement
    const novos = Array.from(input.files ?? [])
    novos.forEach((f) => {
        files.value.push(f)
        previews.value.push(URL.createObjectURL(f))
    })
    input.value = ''
}

function removerImagem(i: number) {
    URL.revokeObjectURL(previews.value[i])
    files.value.splice(i, 1)
    previews.value.splice(i, 1)
}

async function enviar() {

    erro.value = ''
    enviando.value = true

    try {
        await cadastrarPet(form, files.value)
        router.push('/')
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
            <h2>🐾 Cadastrar Pet</h2>

            <label>Nome
                <input v-model="form.name" maxlength="150" required />
            </label>

            <div class="row">
                <label>Tipo
                    <select v-model="form.tipoAnimal" required>
                        <option value="" disabled>Selecione</option>
                        <option v-for="t in tipos" :key="t.value" :value="t.value">{{ t.label }}</option>
                    </select>
                </label>
                <label>Raça
                    <input v-model="form.raca" required />
                </label>
            </div>

            <div class="row">
                <label>Peso (kg)
                    <input v-model.number="form.peso" type="number" min="0.1" step="0.1" required />
                </label>
                <label>Idade
                    <input v-model.number="form.idade" type="number" min="0" />
                </label>
                <label>Cor
                    <input v-model="form.cor" />
                </label>
            </div>

            <label>Endereço
                <input v-model="form.endereco" required />
            </label>

            <label>Descrição ({{ form.descricao.length }}/500)
                <textarea v-model="form.descricao" maxlength="500" rows="3"></textarea>
            </label>

            <label>Fotos
                <input type="file" accept="image/*" multiple @change="onFiles" />
            </label>

            <div v-if="previews.length" class="previews">
                <div v-for="(src, i) in previews" :key="src" class="thumb">
                    <img :src="src" alt="prévia" />
                    <button type="button" @click="removerImagem(i)">✕</button>
                </div>
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
select,
textarea {
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
select:focus,
textarea:focus {
    border-color: orangered;
    box-shadow: 0 0 0 3px rgba(255, 69, 0, 0.15);
}

.row {
    display: flex;
    gap: 12px;
}

.previews {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.thumb {
    position: relative;
    width: 84px;
    height: 84px;
}

.thumb img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 10px;
    border: 1px solid #ffd2b3;
}

.thumb button {
    position: absolute;
    top: -6px;
    right: -6px;
    width: 22px;
    height: 22px;
    padding: 0;
    border: none;
    border-radius: 50%;
    background: orangered;
    color: #fff;
    cursor: pointer;
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