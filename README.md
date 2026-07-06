# Locadora de Veículos

Sistema de gerenciamento de locação de veículos, desenvolvido como Projeto Final da disciplina de Programação Orientada a Objetos.

## 👥 Integrantes

- [Nome completo do integrante 1]
- [Nome completo do integrante 2]
- [Nome completo do integrante 3]

## 📖 Sobre o projeto

O sistema resolve um problema comum de pequenas e médias locadoras de veículos que ainda controlam sua frota, clientes e contratos manualmente (planilhas, papel ou WhatsApp): a falta de um sistema simples para gerenciar clientes, frota (carros, motos, vans), locações e formas de pagamento. O sistema permite cadastrar clientes (com CNH), funcionários, veículos de diferentes categorias e criar locações completas, acompanhando o status desde a reserva até a devolução, com cálculo automático de multa por atraso.

Consulte a documentação completa em [`docs/documentacao.md`](docs/documentacao.md), com requisitos funcionais, casos de uso e diagrama de classes.

## 🛠️ Tecnologias

- Java 17+
- Maven
- Persistência simulada em memória (`Map`), sem banco de dados externo
- Interface textual via terminal (`Scanner`)

## 📂 Estrutura do projeto

```
locadora-veiculos/
├── pom.xml
├── README.md
├── docs/
│   └── documentacao.md
└── src/main/java/com/locadora/
    ├── aplicacao/     → Main.java (ponto de entrada)
    ├── modelo/        → Classes de domínio (Cliente, Veiculo, Locacao, etc.)
    ├── servico/       → Regras de negócio (casos de uso)
    ├── persistencia/  → Repositórios (CRUD simulado em memória)
    └── ui/            → Menu textual interativo
```

## ▶️ Como executar

### Pré-requisitos
- JDK 17 ou superior instalado
- Maven instalado (ou uso da IDE de sua preferência, como IntelliJ ou VS Code)

### Opção 1 — Usando Maven

```bash
# Clonar o repositório
git clone <URL-DO-REPOSITORIO>
cd locadora-veiculos

# Compilar
mvn compile

# Executar
mvn exec:java
```

### Opção 2 — Usando javac/java diretamente

```bash
# A partir da raiz do projeto
mkdir -p out
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.locadora.aplicacao.Main
```

### Opção 3 — Gerando um .jar executável

```bash
mvn package
java -jar target/locadora-veiculos.jar
```

## 🧪 Dados de exemplo

Ao iniciar, o sistema já carrega automaticamente um cliente, um funcionário, um carro e uma moto cadastrados, para facilitar os testes sem precisar digitar tudo manualmente. Os IDs gerados são exibidos no início da execução.

## ✅ Funcionalidades

- Cadastro e listagem de clientes (com CNH)
- Cadastro de funcionários
- Cadastro de veículos em 3 categorias (Carro, Moto, Van), cada uma com cálculo de diária próprio
- Listagem de veículos disponíveis para locação
- Criação de locações com período, itens adicionais (seguro, GPS, etc.) e forma de pagamento
- Escolha entre 3 formas de pagamento (Cartão, Pix, Dinheiro)
- Ciclo de vida da locação: Reservada → Em andamento → Finalizada (ou Cancelada)
- Cálculo automático de multa por atraso na devolução
- Liberação automática do veículo (fica disponível novamente) ao finalizar ou cancelar a locação

## 🎯 Conceitos de POO aplicados

| Conceito | Onde aparece |
|---|---|
| Encapsulamento | Atributos privados com getters/setters em todas as classes de modelo |
| Herança | `Cliente` e `Funcionario` herdam de `Usuario`; `Carro`, `Moto` e `Van` herdam de `Veiculo` (ambas abstratas) |
| Polimorfismo | `calcularValorDiaria()` e `exibirFicha()` sobrescritos de forma diferente em cada subtipo de `Veiculo`; interface `Pagamento` com 3 implementações |
| Interfaces | `Pagamento` e `Repositorio<T>` (genérica) |
| Composição | `Locacao` contém `Adicional` (itens extras) |
| Coleções | `List` e `Map` usados em toda a camada de persistência e nas listas de adicionais/histórico |
