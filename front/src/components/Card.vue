<script lang="ts" setup>
import { useRouter } from 'vue-router'
const router = useRouter()


const props = defineProps<{
    registro?: number
    image?: string
    title?: string
    description?: string
}>()



console.log("printando id do pet no card -> ", props.registro)

</script>

<template>
    <div class="cardDiv" @click="router.push(`/viewPet/${props.registro}`)">
        <div class="cardImage">
            <img :src="image" :alt="title" />
        </div>
        <div class="info">
            <span class="title">{{ title }}</span>
            <p class="description">{{ description }}</p>
        </div>
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