<script setup>
import { ref, onMounted, computed } from 'vue'
import MissaoFormModal from '../components/MissaoFormModal.vue'
import { missaoService } from '../services/api'

const missoes = ref([])
const carregando = ref(true)
const erro = ref('')
const filtro = ref(null) // null = todas, ou 'A' | 'B' | 'C' | 'S'
const modalAberto = ref(false)
const missaoEditando = ref(null)

const estilosPorDificuldade = {
  S: { indicador: 'bg-error', badge: 'bg-error/10 text-error border-error/20' },
  A: { indicador: 'bg-error', badge: 'bg-error/10 text-error border-error/20' },
  B: { indicador: 'bg-primary', badge: 'bg-primary/10 text-primary border-primary/20' },
  C: { indicador: 'bg-on-primary-fixed-variant', badge: 'bg-on-primary-fixed-variant/10 text-on-primary-fixed-variant border-on-primary-fixed-variant/20' },
}

function estiloDe(dificuldade) {
  return estilosPorDificuldade[dificuldade] || { indicador: 'bg-secondary', badge: 'bg-secondary/10 text-secondary border-secondary/20' }
}

const missoesFiltradas = computed(() => {
  if (!filtro.value) return missoes.value
  return missoes.value.filter((m) => m.dificuldade === filtro.value)
})

const totalAltaPrioridade = computed(() => missoes.value.filter((m) => ['A', 'S'].includes(m.dificuldade)).length)

async function carregarDados() {
  carregando.value = true
  erro.value = ''
  try {
    missoes.value = await missaoService.listar()
  } catch (e) {
    erro.value = e.message
  } finally {
    carregando.value = false
  }
}

function abrirNova() {
  missaoEditando.value = null
  modalAberto.value = true
}

function abrirEdicao(missao) {
  missaoEditando.value = missao
  modalAberto.value = true
}

async function salvarMissao(dados) {
  try {
    if (missaoEditando.value) {
      await missaoService.atualizar(missaoEditando.value.id, dados)
    } else {
      await missaoService.criar(dados)
    }
    modalAberto.value = false
    await carregarDados()
  } catch (e) {
    erro.value = e.message
  }
}

async function deletarMissao(missao) {
  if (!confirm(`Remover a missão "${missao.nomedamissao}"?`)) return
  try {
    await missaoService.deletar(missao.id)
    await carregarDados()
  } catch (e) {
    erro.value = e.message
  }
}

onMounted(carregarDados)
</script>

<template>
  <div class="flex flex-col md:flex-row md:items-end justify-between gap-md mb-xl">
    <div>
      <h2 class="font-headline-lg text-headline-lg text-on-surface">Gestão de Missões</h2>
      <p class="font-body-md text-body-md text-on-surface-variant">Centro de comando tático para coordenação da Vontade do Fogo.</p>
    </div>
    <button
      @click="abrirNova"
      class="bg-primary-container text-on-primary-container px-lg py-sm rounded-lg flex items-center gap-sm font-headline-sm text-headline-sm hover:shadow-[0_0_15px_rgba(255,107,0,0.4)] transition-all"
    >
      <span class="material-symbols-outlined">add</span>
      Nova Missão
    </button>
  </div>

  <p v-if="erro" class="text-error mb-md">{{ erro }}</p>

  <div class="grid grid-cols-1 md:grid-cols-4 gap-md mb-lg">
    <div class="bg-surface-container border border-outline-variant rounded-xl p-md flex flex-col gap-xs">
      <span class="text-label-md text-on-surface-variant uppercase tracking-wider">Filtro de Dificuldade</span>
      <div class="flex gap-sm mt-xs">
        <button
          @click="filtro = filtro === 'A' ? null : 'A'"
          class="flex-1 py-xs rounded border text-label-md font-bold transition-colors"
          :class="filtro === 'A' ? 'bg-error/10 border-error text-error' : 'border-error/50 text-error/70 hover:bg-error/10'"
        >A</button>
        <button
          @click="filtro = filtro === 'B' ? null : 'B'"
          class="flex-1 py-xs rounded border text-label-md font-bold transition-colors"
          :class="filtro === 'B' ? 'bg-primary/10 border-primary text-primary' : 'border-primary/50 text-primary/70 hover:bg-primary/10'"
        >B</button>
        <button
          @click="filtro = filtro === 'C' ? null : 'C'"
          class="flex-1 py-xs rounded border text-label-md font-bold transition-colors"
          :class="filtro === 'C' ? 'bg-on-primary-fixed-variant/10 border-on-primary-fixed-variant text-on-primary-fixed-variant' : 'border-on-primary-fixed-variant/50 text-on-primary-fixed-variant/70 hover:bg-on-primary-fixed-variant/10'"
        >C</button>
      </div>
    </div>
    <div class="bg-surface-container border border-outline-variant rounded-xl p-md flex items-center justify-between">
      <div>
        <span class="text-label-md text-on-surface-variant uppercase">Total de Missões</span>
        <p class="text-headline-md font-bold text-on-surface">{{ carregando ? '...' : missoes.length }}</p>
      </div>
      <span class="material-symbols-outlined text-primary text-4xl opacity-50">rocket_launch</span>
    </div>
    <div class="bg-surface-container border border-outline-variant rounded-xl p-md flex items-center justify-between">
      <div>
        <span class="text-label-md text-on-surface-variant uppercase">Alta Prioridade</span>
        <p class="text-headline-md font-bold text-on-surface">{{ carregando ? '...' : totalAltaPrioridade }}</p>
      </div>
      <span class="material-symbols-outlined text-error text-4xl opacity-50">warning</span>
    </div>
    <div class="bg-surface-container border border-outline-variant rounded-xl p-md flex items-center justify-between">
      <div>
        <span class="text-label-md text-on-surface-variant uppercase">Exibindo</span>
        <p class="text-headline-md font-bold text-on-surface">{{ missoesFiltradas.length }}</p>
      </div>
      <span class="material-symbols-outlined text-secondary text-4xl opacity-50">filter_list</span>
    </div>
  </div>

  <div class="bg-surface-container-low rounded-xl border border-outline-variant overflow-hidden shadow-xl">
    <p v-if="carregando" class="px-lg py-lg text-on-surface-variant">Carregando missões...</p>
    <p v-else-if="!missoesFiltradas.length" class="px-lg py-lg text-on-surface-variant">Nenhuma missão encontrada.</p>

    <div v-else class="overflow-x-auto">
      <table class="w-full text-left border-collapse">
        <thead class="bg-surface-container-high">
          <tr>
            <th class="px-lg py-md font-label-md text-label-md text-on-surface-variant uppercase tracking-widest border-b border-outline-variant">ID</th>
            <th class="px-lg py-md font-label-md text-label-md text-on-surface-variant uppercase tracking-widest border-b border-outline-variant">Missão</th>
            <th class="px-lg py-md font-label-md text-label-md text-on-surface-variant uppercase tracking-widest border-b border-outline-variant">Rank</th>
            <th class="px-lg py-md font-label-md text-label-md text-on-surface-variant uppercase tracking-widest border-b border-outline-variant text-right">Ações</th>
          </tr>
        </thead>
        <tbody class="divide-y divide-outline-variant/30">
          <tr v-for="missao in missoesFiltradas" :key="missao.id" class="hover:bg-surface-bright/30 transition-colors group relative">
            <td class="px-lg py-md relative">
              <div class="difficulty-indicator" :class="estiloDe(missao.dificuldade).indicador"></div>
              <span class="font-label-md text-on-surface-variant">#{{ missao.id }}</span>
            </td>
            <td class="px-lg py-md">
              <span class="font-bold text-on-surface group-hover:text-primary transition-colors">{{ missao.nomedamissao }}</span>
            </td>
            <td class="px-lg py-md">
              <span class="px-sm py-xs rounded border font-bold text-label-md" :class="estiloDe(missao.dificuldade).badge">
                RANK {{ missao.dificuldade }}
              </span>
            </td>
            <td class="px-lg py-md text-right">
              <button @click="abrirEdicao(missao)" class="p-sm hover:bg-surface-bright rounded-lg transition-all text-on-surface-variant hover:text-primary">
                <span class="material-symbols-outlined text-[18px]">edit</span>
              </button>
              <button @click="deletarMissao(missao)" class="p-sm hover:bg-error/10 rounded-lg transition-all text-error">
                <span class="material-symbols-outlined text-[18px]">delete</span>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <MissaoFormModal v-if="modalAberto" :missao="missaoEditando" @salvar="salvarMissao" @fechar="modalAberto = false" />
</template>

<style scoped>
.difficulty-indicator {
  width: 4px;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
}
</style>
