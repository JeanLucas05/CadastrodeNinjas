<script setup>
import { ref, watch } from 'vue'

const props = defineProps({ missao: Object })
const emit = defineEmits(['salvar', 'fechar'])

const vazio = () => ({ nomedamissao: '', dificuldade: 'C' })
const form = ref(vazio())

watch(
  () => props.missao,
  (novo) => {
    form.value = novo ? { ...novo } : vazio()
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
      <h3 class="font-headline-sm text-headline-sm text-on-surface">{{ missao ? 'Editar Missão' : 'Nova Missão' }}</h3>

      <div class="space-y-sm">
        <input
          v-model="form.nomedamissao"
          placeholder="Nome da missão"
          class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface"
        />
        <select v-model="form.dificuldade" class="w-full bg-surface-container-lowest border border-outline-variant rounded-lg px-3 py-2 text-on-surface">
          <option value="C">Rank C</option>
          <option value="B">Rank B</option>
          <option value="A">Rank A</option>
          <option value="S">Rank S</option>
        </select>
      </div>

      <div class="flex gap-sm justify-end pt-sm">
        <button @click="$emit('fechar')" class="px-4 py-2 rounded-lg text-on-surface-variant hover:bg-surface-bright">Cancelar</button>
        <button @click="salvar" class="px-4 py-2 rounded-lg bg-primary-container text-on-primary-container font-bold">Salvar</button>
      </div>
    </div>
  </div>
</template>
