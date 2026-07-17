<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()

function ativo(nome) {
  return route.name === nome
}
</script>

<template>
  <aside class="fixed h-full left-0 top-0 w-60 flex flex-col py-md bg-surface-container border-r border-outline-variant shadow-sm z-50">
    <div class="px-md mb-xl">
      <h1 class="font-headline-md text-headline-md font-bold text-primary">Konoha Command</h1>
      <p class="font-label-md text-label-md text-on-surface-variant opacity-70">Kage Administration</p>
    </div>

    <nav class="flex-1 space-y-xs px-sm">
      <router-link
        to="/"
        class="flex items-center gap-md px-md py-sm rounded-lg font-medium transition-colors"
        :class="ativo('dashboard') ? 'text-primary font-bold border-r-4 border-primary bg-secondary-container/20' : 'text-on-surface-variant hover:bg-secondary-container/10 hover:text-primary'"
      >
        <span class="material-symbols-outlined">dashboard</span>
        <span class="font-label-md text-label-md">Dashboard</span>
      </router-link>
      <router-link
        to="/ninjas"
        class="flex items-center gap-md px-md py-sm rounded-lg font-medium transition-colors"
        :class="ativo('ninjas') ? 'text-primary font-bold border-r-4 border-primary bg-secondary-container/20' : 'text-on-surface-variant hover:bg-secondary-container/10 hover:text-primary'"
      >
        <span class="material-symbols-outlined">groups</span>
        <span class="font-label-md text-label-md">Ninjas</span>
      </router-link>
      <router-link
        to="/missoes"
        class="flex items-center gap-md px-md py-sm rounded-lg font-medium transition-colors"
        :class="ativo('missoes') ? 'text-primary font-bold border-r-4 border-primary bg-secondary-container/20' : 'text-on-surface-variant hover:bg-secondary-container/10 hover:text-primary'"
      >
        <span class="material-symbols-outlined">assignment</span>
        <span class="font-label-md text-label-md">Missões</span>
      </router-link>
    </nav>

    <div class="px-md mt-auto pt-md border-t border-outline-variant/30">
      <router-link to="/missoes" class="w-full block text-center bg-primary-container text-on-primary-container py-sm rounded-lg font-headline-sm text-headline-sm font-bold hover:brightness-110 transition-all active:scale-95">
        Nova Missão
      </router-link>
    </div>
  </aside>
</template>
