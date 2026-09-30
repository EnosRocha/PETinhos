<script lang="ts" setup>
import { useRouter } from 'vue-router'

const router = useRouter()
</script>

<template>
    <div id="main">
        <div id="buttonsOpetions">
            <button id="cadastrarPet" @click="router.push('/CadastrarPet')">
                🐾 Cadastrar Pet
            </button>
            <button id="adotarPet" @click="router.push('/AdotarPet')">
                ❤️ Adotar Pet
            </button>
        </div>
    </div>
</template>

<style scoped>
#main {
    position: absolute;
    inset: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    background: linear-gradient(135deg, #fff4ec, #ffd9c2);
}

#buttonsOpetions {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
    padding: 40px 50px;
    background: #fff;
    border-radius: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.12);
}

#buttonsOpetions button {
    padding: 14px 28px;
    font-size: 1.1rem;
    font-weight: 600;
    color: #fff;
    background-color: orangered;
    border: none;
    border-radius: 12px;
    cursor: pointer;
    box-shadow: 0 4px 10px rgba(255, 69, 0, 0.35);
    transition: transform 0.25s ease, box-shadow 0.25s ease, background-color 0.25s ease;
}

#buttonsOpetions button:hover {
    transform: translateY(-3px) scale(1.06);
    box-shadow: 0 8px 18px rgba(255, 69, 0, 0.45);
    background-color: #ff5a1f;
}

#buttonsOpetions button:active {
    transform: scale(0.97);
}

#adotarPet {
    background-color: #2e9e6b;
    box-shadow: 0 4px 10px rgba(46, 158, 107, 0.35);
}

#adotarPet:hover {
    background-color: #36b47b;
    box-shadow: 0 8px 18px rgba(46, 158, 107, 0.45);
}

/* celular: botões empilhados */
@media (max-width: 480px) {
    #buttonsOpetions {
        flex-direction: column;
        width: 80%;
        padding: 30px 20px;
    }
    #buttonsOpetions button {
        width: 100%;
    }
}
</style>