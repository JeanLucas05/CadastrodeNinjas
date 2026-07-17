<script setup>
import { ref, provide } from 'vue'
import Sidebar from './components/Sidebar.vue'
import TopBar from './components/TopBar.vue'

const busca = ref('')
provide('busca', busca)
</script>

<template>
  <Sidebar />
  <TopBar v-model:busca="busca" />

  <main class="ml-60 p-lg w-[calc(100%-240px)] min-h-[calc(100vh-64px-48px)]">
    <router-view />
  </main>
</template>
