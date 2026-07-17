<script setup>
defineProps({ ninja: Object })
defineEmits(['editar', 'deletar'])

const estilosPorRank = {
  Genin: { borda: 'bg-green-500', badge: 'bg-green-100 text-green-800' },
  Chunin: { borda: 'bg-blue-500', badge: 'bg-blue-100 text-blue-800' },
  Jounin: { borda: 'bg-purple-500', badge: 'bg-purple-100 text-purple-800' },
}

function estiloDoRank(rank) {
  return estilosPorRank[rank] || { borda: 'bg-gray-500', badge: 'bg-gray-100 text-gray-800' }
}
</script>

<template>
  <div class="glass-card rounded-xl overflow-hidden relative shadow-lg group hover:-translate-y-1 transition-all duration-300">
    <div class="rank-indicator" :class="estiloDoRank(ninja.rank).borda"></div>
    <div class="p-lg flex gap-md items-start">
      <img
        :src="ninja.imgurl || 'https://placehold.co/150x150?text=Ninja'"
        :alt="ninja.nome"
        class="w-24 h-24 rounded-lg object-cover shadow-md border border-outline-variant/20"
      />
      <div class="flex-1 flex flex-col justify-between h-24">
        <div>
          <div class="flex justify-between items-start">
            <router-link :to="`/ninjas/${ninja.id}`" class="font-headline-sm text-headline-sm text-on-surface hover:text-primary transition-colors">{{ ninja.nome }}</router-link>
            <span
              class="text-[10px] font-bold px-2 py-1 rounded-full uppercase tracking-widest"
              :class="estiloDoRank(ninja.rank).badge"
            >{{ ninja.rank }}</span>
          </div>
          <p class="font-label-md text-label-md text-on-surface-variant mt-1">{{ ninja.email }}</p>
        </div>
        <div class="flex gap-sm">
          <button
            @click="$emit('editar', ninja)"
            class="flex-1 bg-surface-container-highest hover:bg-primary-container hover:text-on-primary-container text-on-surface-variant py-1 rounded transition-colors text-xs font-bold uppercase tracking-tighter"
          >Editar</button>
          <button @click="$emit('deletar', ninja)" class="p-1 text-error hover:bg-error/10 rounded transition-colors">
            <span class="material-symbols-outlined text-[18px]">delete</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
