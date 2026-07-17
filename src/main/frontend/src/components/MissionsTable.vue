<script setup>
defineProps({ missoes: Array })

const estilosPorDificuldade = {
  S: { indicador: 'bg-error', badge: 'bg-error/10 text-error border-error/20' },
  A: { indicador: 'bg-primary', badge: 'bg-primary/10 text-primary border-primary/20' },
  B: { indicador: 'bg-tertiary', badge: 'bg-tertiary/10 text-tertiary border-tertiary/20' },
  C: { indicador: 'bg-emerald-500', badge: 'bg-emerald-500/10 text-emerald-400 border-emerald-500/20' },
}

function estiloDaMissao(dificuldade) {
  return estilosPorDificuldade[dificuldade] || { indicador: 'bg-secondary', badge: 'bg-secondary/10 text-secondary border-secondary/20' }
}
</script>

<template>
  <section class="bg-surface-container-high rounded-xl border border-outline-variant overflow-hidden">
    <div class="px-lg py-md border-b border-outline-variant flex justify-between items-center bg-surface-bright/20">
      <h3 class="font-headline-sm text-headline-sm flex items-center gap-base">
        <span class="material-symbols-outlined text-primary">assignment</span>
        Missões Recentes
      </h3>
      <router-link to="/missoes" class="text-primary font-label-md hover:underline">Ver todas</router-link>
    </div>

    <div v-if="!missoes.length" class="px-lg py-lg text-on-surface-variant">Nenhuma missão cadastrada ainda.</div>

    <div v-else class="overflow-x-auto">
      <table class="w-full text-left border-collapse">
        <thead>
          <tr class="bg-surface-container text-on-surface-variant">
            <th class="px-lg py-sm font-label-md uppercase">Dificuldade</th>
            <th class="px-lg py-sm font-label-md uppercase">Nome da Missão</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-outline-variant/50">
          <tr v-for="missao in missoes.slice(0, 5)" :key="missao.id" class="relative hover:bg-surface-bright/10 transition-colors">
            <td class="px-lg py-md">
              <div class="mission-row-indicator" :class="estiloDaMissao(missao.dificuldade).indicador"></div>
              <span class="px-sm py-xs rounded-full font-label-md border" :class="estiloDaMissao(missao.dificuldade).badge">
                RANK {{ missao.dificuldade }}
              </span>
            </td>
            <td class="px-lg py-md">
              <p class="font-bold text-on-surface">{{ missao.nomedamissao }}</p>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</template>

<style scoped>
.mission-row-indicator {
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  width: 4px;
}
</style>
