# 🥷 Cadastro de Ninjas - API REST

Uma API REST robusta desenvolvida com **Spring Boot** para gerenciar um cadastro completo de ninjas e suas missões. O projeto foi desenvolvido como parte do curso **Java10x**.

## 📋 Sobre o Projeto

O **Cadastro de Ninjas** é uma aplicação backend que permite gerenciar informações de ninjas, associando-os a missões específicas com diferentes níveis de dificuldade. A API fornece operações CRUD completas para ninjas e missões, com validações robustas, tratamento de exceções centralizado e suporte a testes automatizados.

## 🎯 Funcionalidades

### Ninjas
- ✅ Criar novo ninja
- ✅ Listar todos os ninjas
- ✅ Buscar ninja por ID
- ✅ Atualizar informações de ninja
- ✅ Deletar ninja
- ✅ Validação de email único
- ✅ Associar ninjas a missões
- ✅ Campos: Nome, Email, Idade, Rank, Imagem URL

### Missões
- ✅ Criar nova missão
- ✅ Listar todas as missões
- ✅ Buscar missão por ID
- ✅ Atualizar detalhes da missão
- ✅ Deletar missão
- ✅ Associar múltiplos ninjas a uma missão
- ✅ Classificação por níveis de dificuldade

## 🛠 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17+ | Linguagem principal |
| **Spring Boot** | 4.0.3 | Framework web e IoC |
| **Spring Data JPA** | Latest | Camada de persistência |
| **H2 Database** | Latest | Banco de dados em memória (desenvolvimento) |
| **MySQL** | Suportado | Banco de dados em produção |
| **Lombok** | Latest | Geração automática de boilerplate |
| **Flyway** | Latest | Versionamento de banco de dados |
| **Maven** | 3.8+ | Gerenciador de dependências |
| **Jakarta Persistence** | Latest | JPA implementation |

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com separação clara de responsabilidades:

### Camadas da Aplicação

```
┌─────────────────────────────────────────┐
│        Controllers                      │
│    (NinjaController, MissoesController) │
└────────────┬────────────────────────────┘
             │
┌────────────▼────────────────────────────┐
│        Services                         │
│    (NinjaService, MissoesService)       │
└────────────┬────────────────────────────┘
             │
┌────────────▼────────────────────────────┐
│        Repositories                     │
│    (NinjaRepository, MissoesRepository) │
└────────────┬────────────────────────────┘
             │
┌────────────▼────────────────────────────┐
│        Models/Entities                  │
│    (NinjaModel, MissoesModel)           │
└────────────┬────────────────────────────┘
             │
┌────────────▼────────────────────────────┐
│        Database (H2/MySQL)              │
└─────────────────────────────────────────┘
```

### Componentes Principais

#### 1. **Controllers** (REST Endpoints)
- `NinjaController`: Gerencia endpoints para ninjas
- `MissoesController`: Gerencia endpoints para missões
- Utilizam `ResponseEntity` para respostas HTTP adequadas
- Retornam objetos `ApiResponse` padronizados

#### 2. **DTOs (Data Transfer Objects)**
- `NinjaDTO`: Transferência de dados de ninjas
- `MissoesDTO`: Transferência de dados de missões
- Implementam validações de negócio
- Isolam a camada de apresentação das entidades

#### 3. **Services**
- `NinjaService`: Lógica de negócio para ninjas
- `MissoesService`: Lógica de negócio para missões
- Implementam as regras de negócio
- Utilizam Repositories para acesso aos dados
- Lançam exceções customizadas

#### 4. **Repositories**
- `NinjaRepository`: Acesso aos dados de ninjas
- `MissoesRepository`: Acesso aos dados de missões
- Estendem `JpaRepository` do Spring Data JPA
- Implementam consultas customizadas quando necessário

#### 5. **Models/Entities**
- `NinjaModel`: Entidade persistida no banco de dados
- `MissoesModel`: Entidade persistida no banco de dados
- Mapeamento de relacionamento `ManyToOne` e `OneToMany`

#### 6. **Exception Handling**
- `GlobalExceptionHandler`: Tratamento centralizado de exceções
- `EmailAlreadyExistsException`: Email duplicado
- `NinjaNotFoundException`: Ninja não encontrado
- `MissaoNotFoundException`: Missão não encontrada

## 📊 Modelo de Dados

### Relacionamentos

```
NinjaModel ──(ManyToOne)──> MissoesModel
MissoesModel ──(OneToMany)──> NinjaModel
```

### Tabelas

#### tb_cadastro_de_ninjas
```sql
- id (PK)
- nome
- email (UNIQUE)
- idade
- rank
- imgurl
- missoes_id (FK)
```

#### tb_missoes
```sql
- id (PK)
- nomedamissao
- dificuldade
```

## 🔌 Endpoints da API

### Ninjas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/boasVindas` | Mensagem de boas-vindas |
| POST | `/ninjas` | Criar novo ninja |
| GET | `/ninjas` | Listar todos os ninjas |
| GET | `/ninjas/{id}` | Buscar ninja por ID |
| PATCH | `/ninjas/{id}` | Atualizar ninja |
| DELETE | `/ninjas/{id}` | Deletar ninja |

### Missões

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/missoes` | Criar nova missão |
| GET | `/missoes` | Listar todas as missões |
| GET | `/missoes/{id}` | Buscar missão por ID |
| PATCH | `/missoes/{id}` | Atualizar missão |
| DELETE | `/missoes/{id}` | Deletar missão |

## 📝 Exemplo de Requisições

### Criar Ninja
```json
POST /ninjas
Content-Type: application/json

{
  "nome": "Naruto Uzumaki",
  "email": "naruto@village.com",
  "idade": 17,
  "rank": "Genin",
  "imgurl": "https://example.com/naruto.jpg"
}
```

### Criar Missão
```json
POST /missoes
Content-Type: application/json

{
  "nomedamissao": "Resgatar Princesa",
  "dificuldade": "A"
}
```

### Atualizar Ninja
```json
PATCH /ninjas/1
Content-Type: application/json

{
  "rank": "Chunin"
}
```

## 🧪 Testes

O projeto inclui suporte completo para testes automatizados:

### Tipos de Testes Implementados

#### 1. **Testes Unitários**
- Framework: JUnit 5
- Mocking: Mockito
- Cobertura: Camada de Service e Controller
- Cenários testados:
  - Sucesso nas operações CRUD
  - Tratamento de exceções
  - Validações de negócio

#### 2. **Testes de Integração**
- Cobertura end-to-end
- Testes dos endpoints completos
- Validação de persistência no banco de dados

### Executar Testes
```bash
# Executar todos os testes
mvn test

# Executar com cobertura
mvn test jacoco:report

# Executar teste específico
mvn test -Dtest=NinjaServiceTest
```

## 🐳 Docker

O projeto inclui suporte a containerização:

### Dockerfile
```dockerfile
FROM openjdk:17-slim
COPY target/CadastrodeNinjas-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Executar com Docker
```bash
# Build
docker build -t cadastro-ninjas .

# Run
docker run -p 8080:8080 cadastro-ninjas
```

## 📦 Instalação e Configuração

### Pré-requisitos
- Java 17+
- Maven 3.8+
- MySQL (opcional, para produção)

### Passos de Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/JeanLucas05/CadastrodeNinjas.git
cd CadastrodeNinjas
```

2. **Instale as dependências**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a API**
```
http://localhost:8080/boasVindas
```

## 🔧 Configuração do Banco de Dados

### H2 (Desenvolvimento)
Por padrão, a aplicação usa H2 em memória. Acesse o console:
```
http://localhost:8080/h2-console
```

### MySQL (Produção)
Configure as variáveis de ambiente ou `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cadastro_ninjas
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=validate
```

## 📈 Status do Projeto

### Issues Completadas ✅
- #1: Configurar o SpringBoot
- #2: Configurar classe ninja
- #3: Implementar Ninja repository
- #4: Implementar Ninja Service
- #5: Implementar Ninja Controller
- #6: Criar Endpoints Ninja
- #7: Configurar a classe ninja como entidade
- #8: Substituir PUT por PATCH
- #9: Implementar DTOs (Data Transfer Objects)
- #10: Refatorar Exceptions
- #11: Criar testes Unitários - JUnit
- #12: Criar testes de integração
- #13: Incluir ResponseEntity nos Controllers
- #14: Migrar o Banco de Dados (H2 para MySQL)
- #15: Dockerizar a aplicação
- #16: Refatoração: Implementação do Mapper e do DTO

### Issues em Aberto 🔄
- #17: Implementar o frontend para a aplicação

## 🚀 Roadmap Futuro

- [ ] Autenticação e Autorização (JWT)
- [ ] Documentação Swagger/OpenAPI
- [ ] Cache com Redis
- [ ] Logging centralizado
- [ ] Monitoring e Observabilidade
- [ ] Implementar frontend (React/Vue)
- [ ] CI/CD com GitHub Actions
- [ ] Containerização completa (Docker Compose)

## 👨‍💻 Desenvolvedor

**JeanLucas05** - Criador e Mantenedor

## 📄 Licença

Este projeto é de código aberto e está disponível sob a licença MIT.

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📞 Suporte

Para dúvidas ou problemas, abra uma issue no repositório ou entre em contato através do GitHub.

---

**Desenvolvido com ❤️ como parte do curso Java10x**
