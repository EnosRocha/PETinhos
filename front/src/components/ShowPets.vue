<script lang="ts" setup>
import { onMounted } from 'vue';
import { useAnimalStore } from '@/store/animalsStore';
import Card from '@/components/Card.vue';

const animalStore = useAnimalStore();

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

        <div v-else class="content">
            <div class="page-header">
                <h1>Pets disponíveis</h1>
                <p>{{ animalStore.pets.length }} animais esperando por você</p>
            </div>

            <div class="grid">
                <Card v-for="pet in animalStore.pets" :key="pet.id" :registro="pet.registro" :image="pet.image" :title="pet.name"
                    :description="pet.descricao" />
            </div>
        </div>

    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');

.page {
    font-family: 'Poppins', sans-serif;
    min-height: 100vh;
    background: #faf9f8;
    padding: 48px 32px;
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


.content {
    max-width: 1200px;
    margin: 0 auto;
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

@media (max-width: 600px) {
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