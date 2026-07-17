<script setup>
defineProps({
  titulo: String,
  valor: [String, Number],
  icone: String,
  corBorda: { type: String, default: 'border-primary' },
  corIcone: { type: String, default: 'text-primary' },
  legenda: String,
  corLegenda: { type: String, default: 'text-on-surface-variant' },
})
</script>

<template>
  <div
    class="bg-surface-container-high p-lg rounded-xl border-l-4 shadow-sm hover:translate-y-[-4px] transition-all"
    :class="corBorda"
  >
    <div class="flex justify-between items-start mb-md">
      <span class="font-label-md text-label-md uppercase tracking-wider text-on-surface-variant">{{ titulo }}</span>
      <span class="material-symbols-outlined" :class="corIcone">{{ icone }}</span>
    </div>
    <div class="flex items-baseline gap-base">
      <span class="font-display-lg text-display-lg text-on-surface">{{ valor }}</span>
      <span class="font-label-md" :class="corLegenda">{{ legenda }}</span>
    </div>
  </div>
</template>
