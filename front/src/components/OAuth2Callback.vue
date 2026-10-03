<script lang="ts" setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/store/storeAuth'

const router = useRouter()
const auth = useAuthStore()

onMounted(() => {
    const params = new URLSearchParams(window.location.search)
    const token = params.get('token')

    if (token) {
        auth.salvarToken(token)
        router.push('/adotarOuCadastrar')
    } else {
        router.push('/login')
    }
})
</script>

<template>
    <div>Carregando...</div>
</template>