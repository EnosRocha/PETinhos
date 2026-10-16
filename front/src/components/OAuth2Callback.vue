<script lang="ts" setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/store/storeAuth'
import { buscarMeuPerfil } from '@/http/requests'

const router = useRouter()
const auth = useAuthStore()

onMounted(async () => {
    const params = new URLSearchParams(window.location.search)
    const token = params.get('token')

    if (!token) {
        router.push('/login')
        return
    }

    auth.salvarToken(token)

    try {
        const tutor = await buscarMeuPerfil()
        router.push(tutor.phone ? '/adotarOuCadastrar' : '/completarPerfil')
    } catch (error) {
        console.error('Erro ao buscar perfil:', error)
        router.push('/login')
    }
})
</script>

<template>
    <div>Carregando...</div>
</template>