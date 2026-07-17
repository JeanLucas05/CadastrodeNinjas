<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ninjaService } from '../services/api'

const route = useRoute()
const router = useRouter()

const carregando = ref(true)
const salvando = ref(false)
const erro = ref('')
const form = ref({ nome: '', email: '', idade: null, rank: 'Genin', imgurl: '' })

const estilosPorRank = {
  Genin: 'bg-green-900/30 text-green-400',
  Chunin: 'bg-blue-900/30 text-blue-400',
  Jounin: 'bg-primary-container text-on-primary-container',
}

const estiloBadge = computed(() => estilosPorRank[form.value.rank] || 'bg-secondary-container text-on-secondary-container')

async function carregar() {
  carregando.value = true
  erro.value = ''
  try {
    const ninja = await ninjaService.buscar(route.params.id)
    form.value = { ...ninja }
  } catch (e) {
    erro.value = e.message
  } finally {
    carregando.value = false
  }
}

async function salvar() {
  salvando.value = true
  erro.value = ''
  try {
    await ninjaService.atualizar(route.params.id, form.value)
    router.push('/ninjas')
  } catch (e) {
    erro.value = e.message
  } finally {
    salvando.value = false
  }
}

onMounted(carregar)
</script>

<template>
  <div class="mb-lg flex items-center justify-between">
    <button @click="router.push('/ninjas')" class="flex items-center gap-2 text-on-surface-variant hover:text-primary transition-colors active:scale-95">
      <span class="material-symbols-outlined">arrow_back</span>
      <span class="font-label-md text-label-md uppercase tracking-wider">Voltar para listagem</span>
    </button>
  </div>

  <h1 class="font-headline-lg text-headline-lg text-on-surface mb-xl">
    Registro de Shinobi <span class="text-primary" v-if="!carregando">#{{ route.params.id }}</span>
  </h1>

  <p v-if="erro" class="text-error mb-md">{{ erro }}</p>
  <p v-if="carregando" class="text-on-surface-variant">Carregando ninja...</p>

  <div v-else class="grid grid-cols-1 lg:grid-cols-12 gap-lg">
    <!-- Coluna esquerda: preview -->
    <div class="lg:col-span-4 space-y-lg">
      <div class="bg-surface-container border border-outline-variant rounded-xl overflow-hidden shadow-sm">
        <div class="aspect-[3/4] w-full bg-surface-container-low flex items-center justify-center relative overflow-hidden">
          <img
            :src="form.imgurl || 'https://placehold.co/400x500?text=Ninja'"
            class="w-full h-full object-cover"
            :alt="form.nome"
          />
          <div class="absolute bottom-0 left-0 w-full p-lg bg-gradient-to-t from-black/80 to-transparent">
            <div class="inline-block px-3 py-1 rounded-full font-label-md text-[10px] uppercase tracking-tighter mb-2" :class="estiloBadge">
              {{ form.rank }}
            </div>
            <h2 class="font-headline-sm text-headline-sm text-white">{{ form.nome || 'Novo Recruta' }}</h2>
          </div>
        </div>
      </div>
    </div>

    <!-- Coluna direita: formulário -->
    <div class="lg:col-span-8">
      <div class="bg-surface-container border border-outline-variant rounded-xl p-xl shadow-sm">
        <div class="flex items-center gap-4 mb-xl">
          <div class="w-10 h-10 rounded-lg bg-primary-container/20 flex items-center justify-center text-primary">
            <span class="material-symbols-outlined">edit_note</span>
          </div>
          <div>
            <h3 class="font-headline-sm text-headline-sm">Dados Cadastrais</h3>
            <p class="font-body-sm text-body-sm text-on-surface-variant">Informações oficiais para os registros da ANBU.</p>
          </div>
        </div>

        <form class="space-y-xl" @submit.prevent="salvar">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-lg">
            <div class="space-y-2">
              <label class="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">Nome Completo</label>
              <input v-model="form.nome" class="w-full bg-surface-container-low border border-outline-variant rounded-lg py-3 px-md text-body-md" placeholder="Ex: Kakashi Hatake" type="text" />
            </div>
            <div class="space-y-2">
              <label class="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">E-mail</label>
              <input v-model="form.email" class="w-full bg-surface-container-low border border-outline-variant rounded-lg py-3 px-md text-body-md" placeholder="ninja@konoha.gov" type="email" />
            </div>
            <div class="space-y-2">
              <label class="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">Idade</label>
              <input v-model.number="form.idade" class="w-full bg-surface-container-low border border-outline-variant rounded-lg py-3 px-md text-body-md" placeholder="00" type="number" />
            </div>
            <div class="space-y-2">
              <label class="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">Patente (Rank)</label>
              <select v-model="form.rank" class="w-full bg-surface-container-low border border-outline-variant rounded-lg py-3 px-md text-body-md">
                <option>Genin</option>
                <option>Chunin</option>
                <option>Jounin</option>
              </select>
            </div>
          </div>

          <div class="space-y-2">
            <label class="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">URL da Imagem</label>
            <input v-model="form.imgurl" class="w-full bg-surface-container-low border border-outline-variant rounded-lg py-3 px-md text-body-md" placeholder="https://..." type="text" />
          </div>

          <div class="pt-xl flex flex-col sm:flex-row gap-md items-center justify-end border-t border-outline-variant">
            <button @click="router.push('/ninjas')" type="button" class="w-full sm:w-auto px-xl py-md rounded-lg border border-outline-variant font-label-md text-label-md uppercase tracking-widest hover:bg-surface-bright transition-all active:scale-95">
              Descartar Alterações
            </button>
            <button :disabled="salvando" type="submit" class="w-full sm:w-auto px-xl py-md rounded-lg bg-primary-container text-on-primary-container font-headline-sm text-headline-sm hover:opacity-90 shadow-lg active:scale-95 transition-all flex items-center justify-center gap-2 disabled:opacity-60">
              <span class="material-symbols-outlined">save</span>
              {{ salvando ? 'Salvando...' : 'Salvar Alterações' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
