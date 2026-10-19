<script lang="ts" setup>
import { useRouter } from 'vue-router'
const router = useRouter()
import { finalizarAdocao } from '@/http/requests'


function isMinhaAdocao() {
    return router.currentRoute.value.path === '/minhasDoacoes'
}

async function onFinalizarAdocao() {
    if (props.registro == null) return

    if (!confirm('Tem certeza que deseja finalizar esta adoção? O pet será removido da lista.')) return

    try {
        await finalizarAdocao(props.registro)
        emit('finalizado', props.registro)
        alert('Adoção finalizada com sucesso!')
    } catch (error) {
        console.error('Erro ao finalizar a adoção:', error)
        alert('Não foi possível finalizar a adoção. Tente novamente.')
    }
}


const props = defineProps<{
    registro?: number
    image?: { id: number, url: string, ordem: number }[]
    title?: string
    description?: string
}>()

const emit = defineEmits<{
    (e: 'finalizado', registro: number): void
}>()



console.log("printando id do pet no card -> ", props.registro)

</script>

<template>
    <div class="cardDiv" @click="router.push(`/viewPet/${props.registro}`)">
        <div class="cardImage">
            <img :src="image?.[0]?.url" :alt="title" />
        </div>
        <div class="info">
            <span class="title">{{ title }}</span>
            <p class="description">{{ description }}</p>
        </div>

        <button v-if="isMinhaAdocao()" class="btnFinalizar" @click.stop="onFinalizarAdocao">
            Finalizar adoção
        </button>

    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap');

.cardDiv {
    font-family: 'Poppins', sans-serif;
    width: 260px;
    border-radius: 20px;
    overflow: hidden;
    background: #fff;
    box-shadow: 0 8px 32px rgba(255, 112, 67, 0.15);
    transition: transform 0.2s, box-shadow 0.2s;
    cursor: pointer;
}

.btnFinalizar {
    display: block;
    width: calc(100% - 36px);
    margin: 0 18px 18px;
    padding: 12px 20px;
    font-family: inherit;
    font-size: 0.9rem;
    font-weight: 600;
    color: #fff;
    background: linear-gradient(135deg, #ff7043 0%, #ffa05a 100%);
    border: none;
    border-radius: 50px;
    cursor: pointer;
    box-shadow: 0 4px 16px rgba(255, 112, 67, 0.4);
    transition: transform 0.2s, box-shadow 0.2s, filter 0.2s;
}

.btnFinalizar:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 20px rgba(255, 112, 67, 0.55);
    filter: brightness(1.05);
}

.btnFinalizar:active {
    transform: translateY(0);
    box-shadow: 0 2px 8px rgba(255, 112, 67, 0.4);
}

.cardDiv:hover {
    transform: translateY(-6px);
    box-shadow: 0 16px 40px rgba(255, 112, 67, 0.28);
}

.cardImage {
    width: 100%;
    height: 200px;
    overflow: hidden;
    position: relative;
}

.cardImage img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: transform 0.35s ease;
    display: block;
}

.cardDiv:hover .cardImage img {
    transform: scale(1.06);
}

.info {
    padding: 16px 18px 20px;
    display: flex;
    flex-direction: column;
    gap: 6px;
}

.title {
    font-size: 1.05rem;
    font-weight: 700;
    color: #1a1a1a;
    line-height: 1.2;
}

.description {
    font-size: 0.85rem;
    font-weight: 400;
    color: rgba(26, 26, 26, 0.55);
    line-height: 1.5;
    margin: 0;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
</style>