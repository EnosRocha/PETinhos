import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/store/storeAuth'

import Head from '@/components/Head.vue'
import Login from '@/components/Login.vue'
import ShowPets from '@/components/ShowPets.vue'
import viewPet from '@/components/viewPet.vue'
import CadastrarPet from '@/components/CadastrarPet.vue'
import AdotarPet from '@/components/AdotarPet.vue'
import AdotarOuCadastrar from '@/components/CastroOuAdocao.vue'
import SingUpTutor from '@/components/SingUpTutor.vue'
import OAuth2Callback from '@/components/OAuth2Callback.vue'
import CompletarPerfil from '@/components/CompletarPerfil.vue'
import MinhasDoacoes from '@/components/MinhasDoacoes.vue'
import Favoritos from '@/components/MeusFavoritos.vue'
import Sobre from '@/components/Sobre.vue'
import ForgotPassword from '@/components/ForgotPassword.vue'
import ResetPassword from '@/components/ResetPassword.vue'

const routes = [
  { path: '/', component: Head },
  { path: '/login', component: Login },
  { path: '/cadastrarTutor', component: SingUpTutor },
  { path: '/esqueci-senha', component: ForgotPassword },
  { path: '/redefinir-senha', component: ResetPassword },
  { path: '/oauth2/callback', component: OAuth2Callback },
  { path: '/completarPerfil', component: CompletarPerfil, meta: { requiresAuth: true } },
  { path: '/showPets', component: ShowPets, meta: { requiresAuth: true } },
  { path: '/viewPet/:id', component: viewPet, meta: { requiresAuth: true } },
  { path: '/cadastrar', component: CadastrarPet, meta: { requiresAuth: true } },
  { path: '/adotar', component: AdotarPet, meta: { requiresAuth: true } },
  { path: '/adotarOuCadastrar', component: AdotarOuCadastrar, meta: { requiresAuth: true } },
  { path: '/minhasDoacoes', component: MinhasDoacoes, meta: { requiresAuth: true } },
  { path: '/sobre', component: Sobre, meta: { requiresAuth: false } },
  { path: '/favoritos', component: Favoritos, meta: { requiresAuth: true } }
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach((to) => {
  const auth = useAuthStore()
  if (to.meta.requiresAuth && !auth.token) {
    return '/login'
  }
})