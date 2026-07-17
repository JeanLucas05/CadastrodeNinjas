<script setup>
import { ref, onMounted, computed } from 'vue'
import MetricCard from '../components/MetricCard.vue'
import MissionsTable from '../components/MissionsTable.vue'
import { ninjaService, missaoService } from '../services/api'

const ninjas = ref([])
const missoes = ref([])
const carregando = ref(true)
const erro = ref('')

const totalNinjas = computed(() => ninjas.value.length)
const totalMissoes = computed(() => missoes.value.length)
const totalGenins = computed(() => ninjas.value.filter((n) => n.rank === 'Genin').length)
const missoesAltaPrioridade = computed(() => missoes.value.filter((m) => ['S', 'A'].includes(m.dificuldade)).length)

async function carregarDados() {
  carregando.value = true
  erro.value = ''
  try {
    const [listaNinjas, listaMissoes] = await Promise.all([ninjaService.listar(), missaoService.listar()])
    ninjas.value = listaNinjas
    missoes.value = listaMissoes
  } catch (e) {
    erro.value = e.message
  } finally {
    carregando.value = false
  }
}

onMounted(carregarDados)
</script>

<template>
  <div class="mb-xl flex justify-between items-end">
    <div>
      <h2 class="font-headline-lg text-headline-lg text-on-surface">Relatório Tático Geral</h2>
      <p class="font-body-md text-body-md text-on-surface-variant">Monitoramento em tempo real do efetivo ninja e progresso das missões.</p>
    </div>
  </div>

  <p v-if="erro" class="text-error mb-md">{{ erro }}</p>

  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-lg mb-xl">
    <MetricCard
      titulo="Total de Ninjas"
      :valor="carregando ? '...' : totalNinjas"
      icone="groups"
      cor-borda="border-primary"
      cor-icone="text-primary"
      legenda="cadastrados"
    />
    <MetricCard
      titulo="Missões Cadastradas"
      :valor="carregando ? '...' : totalMissoes"
      icone="task_alt"
      cor-borda="border-primary-container"
      cor-icone="text-primary-container"
      legenda="no total"
    />
    <MetricCard
      titulo="Genins"
      :valor="carregando ? '...' : totalGenins"
      icone="school"
      cor-borda="border-secondary"
      cor-icone="text-secondary"
      legenda="base da força"
    />
    <MetricCard
      titulo="Missões Alta Prioridade"
      :valor="carregando ? '...' : missoesAltaPrioridade"
      icone="warning"
      cor-borda="border-error"
      cor-icone="text-error"
      legenda="rank A/S"
      cor-legenda="text-error font-bold"
    />
  </div>

  <div class="grid grid-cols-1 lg:grid-cols-12 gap-lg">
    <div class="lg:col-span-8">
      <MissionsTable :missoes="missoes" />
    </div>

    <section class="lg:col-span-4 space-y-lg">
      <div class="bg-surface-container-lowest p-md rounded-xl border border-error/30 flex items-center gap-md">
        <div class="w-12 h-12 rounded-lg bg-error/10 flex items-center justify-center text-error">
          <span class="material-symbols-outlined text-2xl">security</span>
        </div>
        <div>
          <p class="font-label-md text-label-md font-bold text-on-surface">Alerta de Fronteira</p>
          <p class="text-body-sm text-on-surface-variant">Movimentação suspeita no País do Vento.</p>
        </div>
      </div>
    </section>
  </div>
</template>
