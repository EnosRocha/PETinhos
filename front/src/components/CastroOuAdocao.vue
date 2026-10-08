<script lang="ts" setup>
import { useRouter } from 'vue-router'

const router = useRouter()
</script>

<template>
    <div id="main">
        <div id="buttonsOpetions">
            <div class="buttons">
                <button id="cadastrarPet" @click="router.push('/cadastrar')">
                    🐾 Cadastrar Pet
                </button>
                <button id="adotarPet" @click="router.push('/showPets')">
                    ❤️ Adotar Pet
                </button>
            </div>

            <div class="messageInformativa">
                <h3>Todo pet merece um lar 🏡</h3>
                <p>
                    Aqui você ajuda a transformar vidas. Com poucos cliques, um
                    bichinho que precisa de carinho encontra uma família.
                </p>
                <p>
                    <strong>Tem um pet para doar?</strong> Toque em
                    <em>Cadastrar Pet</em>.<br />
                    <strong>Quer ganhar um novo melhor amigo?</strong> Toque em
                    <em>Adotar Pet</em>.
                </p>
            </div>
        </div>
    </div>
</template>

<style scoped>
#main {
    width: 100%;
    min-height: 100vh;
    padding: 24px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-family: 'Poppins', sans-serif;
    background:
        radial-gradient(circle at 20% 20%, rgba(255, 255, 255, 0.35) 0%, rgba(255, 255, 255, 0) 55%),
        linear-gradient(135deg, #ff7043 0%, #ffa05a 45%, #ffcc70 100%);
}

#buttonsOpetions {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 28px;
    min-height: 60vh;
    width: 40vw;
    padding: 30px;
    box-sizing: border-box;
    background: #fff;
    border-radius: 20px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.12);
}

.buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
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

.messageInformativa {
    width: 100%;
    box-sizing: border-box;
    padding: 20px 24px;
    text-align: center;
    color: #5a3a28;
    line-height: 1.6;
    background-color: #fff1e6;
    border: 1px solid #ffd2b3;
    border-radius: 14px;
}

.messageInformativa h3 {
    margin: 0 0 8px;
    color: orangered;
    font-size: 1.25rem;
}

.messageInformativa p {
    margin: 0 0 10px;
    font-size: 0.98rem;
}

.messageInformativa p:last-child {
    margin-bottom: 0;
}

.messageInformativa em {
    font-style: normal;
    font-weight: 600;
    color: #c43a00;
}

/* celular: botões empilhados */
@media (max-width: 480px) {
    #buttonsOpetions {
        width: 90%;
        padding: 20px;
    }

    .buttons {
        flex-direction: column;
        width: 100%;
    }

    #buttonsOpetions button {
        width: 100%;
    }
}
</style>