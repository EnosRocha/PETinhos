<script lang="ts" setup>
import { onMounted, ref, computed } from 'vue';
import { useAnimalStore } from '@/store/animalsStore';
import Card from '@/components/Card.vue';
import { useRouter } from 'vue-router';

const animalStore = useAnimalStore();
const router = useRouter();

const tipos = [
    { value: 'CACHORRO', label: 'Cachorro' },
    { value: 'GATO', label: 'Gato' },
    { value: 'PASSARO', label: 'Pássaro' },
    { value: 'PORCO', label: 'Porco' },
    { value: 'CAVALO', label: 'Cavalo' },
    { value: 'VACA', label: 'Vaca' },
    { value: 'PORCO_DA_INDIA', label: 'Porquinho-da-índia' },
]

const filtroAtivo = ref<string | null>(null)

const petsFiltrados = computed(() => {
    if (!filtroAtivo.value) return animalStore.pets
    return animalStore.pets.filter(p => p.tipoAnimal === filtroAtivo.value)
})

function toggleFiltro(tipo: string) {
    filtroAtivo.value = filtroAtivo.value === tipo ? null : tipo
}

onMounted(async () => {
    await animalStore.fetchPets();
})
</script>

<template>
    <div class="page">

        <div v-if="animalStore.loading" class="loading">
            <span class="paw">🐾</span>
            <p>Buscando pets disponíveis...</p>
        </div>

        <div v-else class="layout">
            <aside class="filtro">
                <h3>Filtrar por tipo</h3>
                <button v-for="tipo in tipos" :key="tipo.value" class="filtro-btn"
                    :class="{ ativo: filtroAtivo === tipo.value }" @click="toggleFiltro(tipo.value)">
                    {{ tipo.label }}
                </button>
                <button v-if="filtroAtivo" class="limpar" @click="filtroAtivo = null">
                    Limpar filtro
                </button>
            </aside>

            <div class="content">
                <div class="page-header">
                    <h1>Pets disponíveis</h1>
                    <p>{{ petsFiltrados.length }} animais esperando por você</p>
                </div>

                <div class="grid">
                    <Card v-for="pet in petsFiltrados" :key="pet.registro" :registro="pet.registro" :image="pet.image"
                        :title="pet.name" :description="pet.descricao" />
                </div>

                <p v-if="petsFiltrados.length === 0" class="vazio">
                    Nenhum pet encontrado para esse filtro.
                </p>
            </div>
        </div>

        <div class="top-ntm">
            <button class="top-btn" @click="router.push('/cadastrar')">
                Cadastrar pet
            </button>

            <button class="top-btn" @click="router.push('/minhasDoacoes')">
                Minhas doações
            </button>
        </div>

    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');

.page {
    font-family: 'Poppins', sans-serif;
    min-height: 100vh;
    background: rgb(251, 141, 101);
    padding: 48px 32px;
}

.top-ntm {
    position: absolute;
    top: 16px;
    right: 32px;
    display: flex;
    gap: 12px;
    z-index: 10;
}

.top-btn {
    padding: 8px 16px;
    border: none;
    border-radius: 8px;
    background: #fff9f5;
    color: #5a3a28;
    font-family: inherit;
    font-size: 0.85rem;
    cursor: pointer;
    transition: all 0.2s;
}
.layout {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    gap: 32px;
    align-items: flex-start;
}

.filtro {
    width: 200px;
    flex-shrink: 0;
    background: #fff;
    border-radius: 16px;
    padding: 20px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
    display: flex;
    flex-direction: column;
    gap: 8px;
    position: sticky;
    top: 24px;
}

.filtro h3 {
    font-size: 0.95rem;
    font-weight: 700;
    color: #1a1a1a;
    margin-bottom: 4px;
}

.filtro-btn {
    text-align: left;
    padding: 10px 12px;
    border: 1px solid #ffd2b3;
    border-radius: 10px;
    background: #fff9f5;
    font-family: inherit;
    font-size: 0.85rem;
    cursor: pointer;
    transition: all 0.2s;
    color: #5a3a28;
}

.filtro-btn:hover {
    background: #ffe8d6;
}

.filtro-btn.ativo {
    background: orangered;
    color: #fff;
    border-color: orangered;
    font-weight: 600;
}

.limpar {
    margin-top: 4px;
    padding: 8px 12px;
    border: none;
    border-radius: 10px;
    background: #ffe3d8;
    color: #a12600;
    font-family: inherit;
    font-size: 0.8rem;
    cursor: pointer;
    font-weight: 600;
}

.content {
    flex: 1;
}

.page-header {
    margin-bottom: 36px;
}

.page-header h1 {
    font-size: 2rem;
    font-weight: 800;
    color: #1a1a1a;
    line-height: 1.2;
}

.page-header p {
    margin-top: 6px;
    font-size: 0.95rem;
    color: rgba(26, 26, 26, 0.5);
    font-weight: 400;
}

.grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
    gap: 28px;
}

.vazio {
    text-align: center;
    color: rgba(26, 26, 26, 0.5);
    margin-top: 48px;
    font-size: 1rem;
}

.loading {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 60vh;
    gap: 16px;
    color: rgba(26, 26, 26, 0.45);
    font-size: 1rem;
}

.paw {
    font-size: 2.8rem;
    animation: bounce 1s infinite alternate;
}

@keyframes bounce {
    from {
        transform: translateY(0);
    }

    to {
        transform: translateY(-12px);
    }
}

@media (max-width: 700px) {
    .layout {
        flex-direction: column;
    }

    .filtro {
        width: 100%;
        position: static;
        flex-direction: row;
        flex-wrap: wrap;
    }

    .filtro h3 {
        width: 100%;
    }

    .page {
        padding: 32px 16px;
    }

    .page-header h1 {
        font-size: 1.5rem;
    }

    .grid {
        gap: 18px;
    }
}
</style>