
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { buscarPetsDisponiveis } from '../http/requests'
import type { Animal } from '@/types/animal'  


export const useAnimalStore = defineStore('animal', () => {
    const pets = ref<Animal[]>([])
    const loading = ref(false)

    async function fetchPets() {
        loading.value = true
        pets.value = await buscarPetsDisponiveis()
        loading.value = false
    }

    return { pets, loading, fetchPets }
})