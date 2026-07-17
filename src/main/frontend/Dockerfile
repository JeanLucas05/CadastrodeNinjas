# Estágio 1: Build da aplicação frontend
FROM node:20-alpine AS builder

WORKDIR /app

# Copia os arquivos de dependências primeiro (aproveita o cache do Docker)
COPY package*.json ./

# Instala as dependências do projeto
RUN npm install

# Copia o restante dos arquivos do projeto para o container
COPY . .

# Executa o comando de build (gera a pasta 'dist')
RUN npm run build


# Estágio 2: Servidor de produção com Nginx
FROM nginx:alpine

# Remove a configuração padrão que vem nativa no Nginx
RUN rm /etc/nginx/conf.d/default.conf

# Copia a sua configuração customizada do Nginx para dentro do container
COPY nginx.conf /etc/nginx/conf.d/

# Copia os arquivos gerados no build (geralmente na pasta 'dist') para a pasta que o Nginx lê
COPY --from=builder /app/dist /usr/share/nginx/html

# Informa que o container vai escutar na porta 80
EXPOSE 3000

# Inicia o Nginx em primeiro plano para manter o container ativo
CMD ["nginx", "-g", "daemon off;"]