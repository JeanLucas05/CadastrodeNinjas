<script setup>
import { ref, onMounted, computed, inject } from 'vue'
import NinjaCard from '../components/NinjaCard.vue'
import NinjaFormModal from '../components/NinjaFormModal.vue'
import { ninjaService, missaoService } from '../services/api'

const busca = inject('busca')

const ninjas = ref([])
const missoes = ref([])
const carregando = ref(true)
const erro = ref('')
const modalAberto = ref(false)
const ninjaEditando = ref(null)

const ninjasFiltrados = computed(() => {
  const termo = (busca?.value || '').toLowerCase()
  return ninjas.value.filter((n) => n.nome.toLowerCase().includes(termo))
})

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

function abrirNovo() {
  ninjaEditando.value = null
  modalAberto.value = true
}

function abrirEdicao(ninja) {
  ninjaEditando.value = ninja
  modalAberto.value = true
}

async function salvarNinja(dados) {
  try {
    if (ninjaEditando.value) {
      await ninjaService.atualizar(ninjaEditando.value.id, dados)
    } else {
      await ninjaService.criar(dados)
    }
    modalAberto.value = false
    await carregarDados()
  } catch (e) {
    erro.value = e.message
  }
}

async function deletarNinja(ninja) {
  if (!confirm(`Remover ${ninja.nome} do registro?`)) return
  try {
    await ninjaService.deletar(ninja.id)
    await carregarDados()
  } catch (e) {
    erro.value = e.message
  }
}

onMounted(carregarDados)
</script>

<template>
  <div class="flex flex-col md:flex-row md:items-center justify-between gap-md mb-xl">
    <div>
      <h2 class="font-headline-lg text-headline-lg text-on-surface">Diretório de Shinobis</h2>
      <p class="font-body-md text-body-md text-on-surface-variant">Gerencie ninjas e suas missões</p>
    </div>
    <button
      @click="abrirNovo"
      class="bg-primary-container text-on-primary-container px-lg py-sm rounded-lg flex items-center gap-sm font-headline-sm text-headline-sm hover:shadow-[0_0_15px_rgba(255,107,0,0.4)] transition-all"
    >
      <span class="material-symbols-outlined">person_add</span>
      Novo Ninja
    </button>
  </div>

  <p v-if="erro" class="text-error mb-md">{{ erro }}</p>
  <p v-if="carregando" class="text-on-surface-variant mb-md">Carregando ninjas...</p>
  <p v-else-if="!ninjasFiltrados.length" class="text-on-surface-variant mb-md">Nenhum ninja encontrado.</p>

  <div class="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-lg">
    <NinjaCard
      v-for="ninja in ninjasFiltrados"
      :key="ninja.id"
      :ninja="ninja"
      @editar="abrirEdicao"
      @deletar="deletarNinja"
    />
  </div>

  <NinjaFormModal
    v-if="modalAberto"
    :ninja="ninjaEditando"
    :missoes="missoes"
    @salvar="salvarNinja"
    @fechar="modalAberto = false"
  />
</template>
