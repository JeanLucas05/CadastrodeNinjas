<script setup>
import { ref, watch } from 'vue'

const props = defineProps({ ninja: Object, missoes: Array })
const emit = defineEmits(['salvar', 'fechar'])

const vazio = () => ({ nome: '', email: '', idade: null, rank: 'Genin', imgurl: '', missoesId: null })
const form = ref(vazio())

watch(
  () => props.ninja,
  (novo) => {
    form.value = novo ? { ...novo, missoesId: novo.missoesId ?? null } : vazio()
  },
  { immediate: true }
)

function salvar() {
  emit('salvar', { ...form.value })
}
</script>

<template>
  <div class="fixed inset-0 bg-black/60 flex items-center justify-center z-50 p-md" @click.self="$emit('fechar')">
    <div class="bg-surface-container-high rounded-xl p-lg w-full max-w-md space-y-md">
      <h3 class="font-headline-sm text-headline-sm text-on-surface">{{ ninja ? 'Editar Ninja' : 'Novo Ninja' }}</h3>

      <div class="space-y-sm">
        <input v-model="form.nome" placeholder="Nome" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface" />
        <input v-model="form.email" type="email" placeholder="Email" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface" />
        <input v-model.number="form.idade" type="number" placeholder="Idade" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface" />
        <select v-model="form.rank" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface">
          <option>Genin</option>
          <option>Chunin</option>
          <option>Jounin</option>
        </select>
        <input v-model="form.imgurl" placeholder="URL da imagem" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface" />
        <select v-model="form.missoesId" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface">
          <option :value="null">Sem missão</option>
          <option v-for="m in missoes" :key="m.id" :value="m.id">{{ m.nomedamissao }} ({{ m.dificuldade }})</option>
        </select>
      </div>

      <div class="flex gap-sm justify-end pt-sm">
        <button @click="$emit('fechar')" class="px-4 py-2 rounded-lg text-on-surface-variant hover:bg-surface-bright">Cancelar</button>
        <button @click="salvar" class="px-4 py-2 rounded-lg bg-primary-container text-on-primary-container font-bold">Salvar</button>
      </div>
    </div>
  </div>
</template>
