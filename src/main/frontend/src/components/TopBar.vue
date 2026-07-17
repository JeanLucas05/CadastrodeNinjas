<script setup>
defineProps({ busca: String })
defineEmits(['update:busca'])
</script>

<template>
  <header class="flex items-center justify-between px-lg h-16 ml-60 w-[calc(100%-240px)] bg-surface-container-high border-b border-outline-variant shadow-md sticky top-0 z-40">
    <div class="relative w-full max-w-md group">
      <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant/50 group-focus-within:text-primary transition-colors">search</span>
      <input
        :value="busca"
        @input="$emit('update:busca', $event.target.value)"
        class="w-full bg-surface-container-lowest border border-outline-variant rounded-full py-2 pl-10 pr-4 font-body-md text-body-md text-on-surface focus:outline-none focus:ring-2 focus:ring-primary transition-all"
        placeholder="Buscar shinobi por nome..."
        type="text"
      />
    </div>
    <p class="font-body-md text-body-md text-on-surface font-medium">Konoha Command</p>
  </header>
</template>
