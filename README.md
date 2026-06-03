# QA Portfolio — Automação de Testes
![QA Tests](https://github.com/brenixn/qa-portfolio/actions/workflows/tests.yml/badge.svg)

Projeto de automação de testes desenvolvido com Java, Selenium e Rest Assured,
cobrindo testes de API e testes de interface web.

## 🛠️ Tecnologias utilizadas

- Java 17
- Selenium WebDriver 4
- Rest Assured 5
- JUnit 5
- Maven
- WebDriverManager

## 📁 Estrutura do projeto

```
src/test/java/
├── api/
│   └── UsuarioApiTest.java   # Testes de API REST
└── web/
    └── LoginTest.java        # Testes de interface web
```
## 🧪 Testes de API (Rest Assured)

Testes automatizados contra a API pública JSONPlaceholder:

- ✅ GET — listar posts e validar quantidade
- ✅ GET — buscar post por ID e validar campos
- ✅ GET — validar retorno 404 para recurso inexistente
- ✅ POST — criar novo post e validar resposta

## 🌐 Testes Web (Selenium)

Testes automatizados de login no site The Internet (Herokuapp):

- ✅ Login com credenciais válidas
- ✅ Login com senha inválida — validação de mensagem de erro
- ✅ Login com usuário inexistente — validação de mensagem de erro

## ▶️ Como executar

### Pré-requisitos
- JDK 17+
- Maven
- Google Chrome instalado

### Rodando os testes
```bash
# Todos os testes
mvn test

# Só os testes de API
mvn test -Dtest=UsuarioApiTest

# Só os testes web
mvn test -Dtest=LoginTest
```

## 📌 Padrões utilizados

- **Given/When/Then** — estrutura dos testes de API
- **WebDriverWait** — espera explícita para estabilidade dos testes web
- **@BeforeEach / @AfterEach** — setup e teardown isolados por teste