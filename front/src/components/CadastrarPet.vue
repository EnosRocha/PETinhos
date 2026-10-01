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