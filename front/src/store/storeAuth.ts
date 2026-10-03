import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') ?? '')

    function salvarToken(t: string) {
        token.value = t
        localStorage.setItem('token', t)
    }

    function logout() {
        token.value = ''
        localStorage.removeItem('token')
    }

    return { token, salvarToken, logout }
})