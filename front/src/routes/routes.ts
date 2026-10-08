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

const routes = [
  { path: '/', component: Head },
  { path: '/login', component: Login },
  { path: '/cadastrarTutor', component: SingUpTutor },
  { path: '/oauth2/callback', component: OAuth2Callback },
  { path: '/showPets', component: ShowPets, meta: { requiresAuth: true } },
  { path: '/viewPet/:id', component: viewPet, meta: { requiresAuth: true } },
  { path: '/cadastrar', component: CadastrarPet, meta: { requiresAuth: true } },
  { path: '/adotar', component: AdotarPet, meta: { requiresAuth: true } },
  { path: '/adotarOuCadastrar', component: AdotarOuCadastrar, meta: { requiresAuth: true } },
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