import { createRouter, createWebHistory } from 'vue-router'

import Head from '@/components/Head.vue'
import Login from '@/components/Login.vue'
import ShowPets from '@/components/ShowPets.vue'
import viewPet from '@/components/viewPet.vue'
import CadastrarPet from '@/components/CadastrarPet.vue'
import AdotarPet from '@/components/AdotarPet.vue'
import AdotarOuCadastrar from '@/components/CastroOuAdocao.vue'
import SingUpTutor from '@/components/SingUpTutor.vue'
import OAuth2Callback from '@/components/OAuth2Callback.vue'


const routes = [
  { path: '/', component: Head },
  { path: '/login', component: Login },
  { path: '/showPets', component: ShowPets },
  { path: '/viewPet/:id', component: viewPet },
  { path: '/cadastrar', component: CadastrarPet },
  { path: '/adotar', component: AdotarPet },
  { path: '/adotarOuCadastrar', component: AdotarOuCadastrar },
  { path: '/cadastrarTutor', component: SingUpTutor },
  { path: '/oauth2/callback', component: OAuth2Callback }
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})