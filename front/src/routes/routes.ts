import { createRouter, createWebHistory } from 'vue-router'

import Head from '@/components/Head.vue'
import Login from '@/components/Login.vue'
import ShowPets from '@/components/ShowPets.vue'
import viewPet from '@/components/viewPet.vue'


const routes = [
  { path: '/', component: Head },
  { path: '/login', component: Login },
  { path: '/showPets', component: ShowPets },
  { path: '/viewPet/:id', component: viewPet }
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})