<script lang="ts" setup>
import { computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAnimalStore } from '@/store/animalsStore'
import { ref } from 'vue'

const route = useRoute()
const router = useRouter()
const animalStore = useAnimalStore()
const imagemAtual = ref(0)

function proximo() {
    if (pet.value && imagemAtual.value < pet.value.image.length - 1) {
        imagemAtual.value++
    } else {
        imagemAtual.value = 0
    }
}

function anterior() {
    if (imagemAtual.value > 0) {
        imagemAtual.value--
    } else if (pet.value) {
        imagemAtual.value = pet.value.image.length - 1
    }
}

onMounted(async () => {
    if (animalStore.pets.length === 0) {
        await animalStore.fetchPets()
    }
})

const pet = computed(() =>
    animalStore.pets.find(p => p.registro === Number(route.params.id))

)

console.log("printando o valor do pet id dentro do viewPet - >", pet.value?.registro
)
</script>

<template>
    <div class="viewPetMainDiv">

        <div v-if="pet" class="content">

            <button class="back" @click="router.back()"> Voltar</button>

            <div class="card">
                <div class="image-wrap">
                    <div class="carousel" v-if="pet.image && pet.image.length > 0">
                        <img :src="pet.image[imagemAtual]?.url" :alt="pet.name" />
                        <div class="carousel-controls" v-if="pet.image.length > 1">
                            <button class="arrow left" @click="anterior">‹</button>
                            <span class="counter">{{ imagemAtual + 1 }} / {{ pet.image.length }}</span>
                            <button class="arrow right" @click="proximo">›</button>
                        </div>
                    </div>
                    <div v-else class="sem-imagem">Sem foto</div>
                </div>

                <div class="info">
                    <h1>{{ pet.name }}</h1>
                    <p class="descricao">{{ pet.descricao }}</p>

                    <div class="details">
                        <div class="detail">
                            <span class="label">Tipo</span>
                            <span>{{ pet.tipoAnimal }}</span>
                        </div>
                        <div class="detail">
                            <span class="label">Raça</span>
                            <span>{{ pet.raca }}</span>
                        </div>
                        <div class="detail">
                            <span class="label">Cor</span>
                            <span>{{ pet.cor }}</span>
                        </div>
                        <div class="detail">
                            <span class="label">Peso</span>
                            <span>{{ pet.peso }} kg</span>
                        </div>
                        <div class="detail">
                            <span class="label">Idade</span>
                            <span>{{ pet.idade }} anos</span>
                        </div>
                        <div class="detail">
                            <span class="label">Endereço</span>
                            <span>{{ pet.endereco }}</span>
                        </div>
                    </div>

                    <button class="adotar">Quero adotar 🐾</button>
                </div>
            </div>

        </div>
        <div class="backgroundContainer">
            <img src="../assets/images/viewPetBackGround.jpeg" alt="background">
        </div>

        <!-- <input type="file" multiple @change="handleFiles" accept="image/*" /> -->


    </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');

* {
    font-family: 'Poppins', sans-serif;

}

.viewPetMainDiv {
    min-height: 100vh;
    width: 100vw;
    background: transparent;
    padding: 48px 32px;
    position: relative;

}

.loading {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 60vh;
    gap: 16px;
    color: rgba(26, 26, 26, 0.45);
}

.paw {
    font-size: 2.8rem;
    animation: bounce 1s infinite alternate;
}


.backgroundContainer {
    height: 100vh;
    width: 100vw;
    position: fixed;
    top: 0;
    left: 0;
    z-index: -1;

}

.backgroundContainer img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center;
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
    max-width: 900px;
    margin: 0 auto;
    z-index: 9999;
}

.back {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 0.95rem;
    color: rgba(26, 26, 26, 0.5);
    margin-bottom: 24px;
    padding: 10px;
    transition: color 0.2s;
    border: 1px solid black;
    border-radius: 10px;
}

.back:hover {
    color: #1a1a1a;
}

.card {
    display: flex;
    gap: 40px;
    background: #fff;
    border-radius: 20px;
    padding: 36px;
    box-shadow: 0 4px 24px rgba(0, 0, 0, 0.06);
}

.image-wrap {
    flex-shrink: 0;
    width: 320px;
    height: 320px;
    border-radius: 16px;
    overflow: hidden;
    background: #f0eeec;
}

.image-wrap img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.carousel {
    position: relative;
    width: 100%;
    height: 100%;
}

.carousel img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.carousel-controls {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    pointer-events: none;
}

.arrow {
    pointer-events: all;
    background: rgba(0, 0, 0, 0.5);
    color: #fff;
    border: none;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    font-size: 1.4rem;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 8px;
    transition: background 0.2s;
}

.arrow:hover {
    background: rgba(0, 0, 0, 0.75);
}

.counter {
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
    color: #fff;
    font-size: 0.85rem;
    background: rgba(0, 0, 0, 0.4);
    padding: 2px 8px;
    border-radius: 10px;
    pointer-events: none;
}

.info {
    display: flex;
    flex-direction: column;
    gap: 16px;
    flex: 1;
}

.info h1 {
    font-size: 2rem;
    font-weight: 800;
    color: #1a1a1a;
    margin: 0;
}

.descricao {
    color: rgba(26, 26, 26, 0.6);
    font-size: 0.95rem;
    line-height: 1.6;
    margin: 0;
}

.details {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 12px;
    margin-top: 8px;
}

.detail {
    display: flex;
    flex-direction: column;
    gap: 2px;
}

.label {
    font-size: 0.75rem;
    font-weight: 600;
    color: rgba(26, 26, 26, 0.4);
    text-transform: uppercase;
    letter-spacing: 0.05em;
}

.detail span:last-child {
    font-size: 0.95rem;
    color: #1a1a1a;
    font-weight: 500;
}

.adotar {
    margin-top: auto;
    background: #ff7043;
    color: #fff;
    border: none;
    border-radius: 12px;
    padding: 14px 28px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: opacity 0.2s;
}

.adotar:hover {
    opacity: 0.8;
}

@media (max-width: 700px) {
    .card {
        flex-direction: column;
    }

    .image-wrap {
        width: 100%;
        height: 240px;
    }
}
</style>