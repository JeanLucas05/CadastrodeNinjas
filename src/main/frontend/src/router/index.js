import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '../views/DashboardView.vue'
import NinjasView from '../views/NinjasView.vue'
import MissoesView from '../views/MissoesView.vue'
import NinjaPerfilView from '../views/NinjaPerfilView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', name: 'dashboard', component: DashboardView },
    { path: '/ninjas', name: 'ninjas', component: NinjasView },
    { path: '/ninjas/:id', name: 'ninja-perfil', component: NinjaPerfilView, props: true },
    { path: '/missoes', name: 'missoes', component: MissoesView },
  ],
})

export default router
