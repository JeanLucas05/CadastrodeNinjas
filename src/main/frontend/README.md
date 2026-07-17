# Konoha Command (Vue 3 + Vite)

Frontend em Vue consumindo a API Spring Boot de cadastro de ninjas e missões.

## Rodando

```bash
npm install
cp .env.example .env   # ajusta VITE_API_URL se a API não estiver em localhost:8080
npm run dev
```

## Estrutura

- `src/services/api.js` — chamadas HTTP (fetch nativo) para `/ninjas` e `/missoes`, sem dependências extras
- `src/components/Sidebar.vue`, `TopBar.vue` — layout fixo
- `src/components/NinjaCard.vue` — card do ninja, cor do badge muda conforme o rank
- `src/components/NinjaFormModal.vue` — formulário de criar/editar, com select de missão
- `src/App.vue` — busca os dados no `onMounted`, controla busca, modal e CRUD

## CORS

Se a API Spring Boot não tiver CORS liberado para `http://localhost:5173` (porta padrão do Vite),
adiciona um `@CrossOrigin` no controller ou uma configuração global de CORS, senão as requisições vão falhar no navegador.

## Próximos passos sugeridos

- Trocar o `confirm()` de deletar por um modal de confirmação (mais consistente com o resto do design)
- Extrair um composable `useNinjas.js` se a lógica do `App.vue` crescer
- Se quiser paginação de verdade, a API precisa expor isso (hoje o resumo não menciona paginação nos endpoints)
