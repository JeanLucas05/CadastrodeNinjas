const BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080'

async function request(path, options = {}) {
  const res = await fetch(`${BASE_URL}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  })

  if (!res.ok) {
    const texto = await res.text().catch(() => '')
    throw new Error(`Erro ${res.status}: ${texto || res.statusText}`)
  }

  if (res.status === 204) return null
  return res.json()
}

export const ninjaService = {
  listar: () => request('/ninjas'),
  buscar: (id) => request(`/ninjas/${id}`),
  criar: (dados) => request('/ninjas', { method: 'POST', body: JSON.stringify(dados) }),
  atualizar: (id, dados) => request(`/ninjas/${id}`, { method: 'PATCH', body: JSON.stringify(dados) }),
  deletar: (id) => request(`/ninjas/${id}`, { method: 'DELETE' }),
}

export const missaoService = {
  listar: () => request('/missoes'),
  buscar: (id) => request(`/missoes/${id}`),
  criar: (dados) => request('/missoes', { method: 'POST', body: JSON.stringify(dados) }),
  atualizar: (id, dados) => request(`/missoes/${id}`, { method: 'PATCH', body: JSON.stringify(dados) }),
  deletar: (id) => request(`/missoes/${id}`, { method: 'DELETE' }),
}
