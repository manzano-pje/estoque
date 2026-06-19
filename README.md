## 📦 Sistema de Controle de Estoque  
Este projeto é uma API REST desenvolvida como um **estudo prático de fixação** de conceitos fundamentais de desenvolvimento Backend com **Java 17 e Spring Boot 3**. O objetivo principal foi simular um cenário real de gerenciamento de inventário, aplicando princípios de organização arquitetural e validação rigorosa de regras de negócio.

> **Nota:** Este projeto representa um marco de aprendizado técnico e não possui *roadmap* para futuras atualizações.

## 🏗️ Arquitetura
O sistema foi estruturado para garantir a separação de responsabilidades (*Separation of Concerns*), organizado em camadas:

* **Controllers**: Exposição de endpoints REST seguindo padrões de verbos HTTP.
* **Services**: Camada central de regras de negócio e validações críticas.
* **Repositories**: Camada de persistência, utilizando Spring Data JPA/Hibernate.
* **Entities/DTOs**: Definição do domínio e transferência de dados.
* **Exceptions**: Tratamento centralizado de erros.

## ✨Funcionalidades  
🛒 Produtos  
✅ CRUD completo: Criar, listar, atualizar e excluir produtos.  
✅ Listagem geral de produtos.  
✅ Filtros por nome e código do produto.  
✅ Controle de estoque mínimo e atualização automática de saldo.   
   
## 👤 Usuários  
✅ CRUD completo: Criar, listar, atualizar e excluir usuários.  
✅ Permite alteração de senha.  
✅ Controle de função do usuário por meio de enumeração (ADMIN, USUARIO_COMUM, etc.).  
  
## 🔄 Movimentação de Estoque  
✅ Cadastro de entrada e saída de produtos.  
✅ Bloqueia movimentações quando a quantidade solicitada for maior do que a disponível.  
✅ Filtragem de movimentações por período específico ou listagem total.  
✅ Registro do usuário e horário da movimentação para rastreabilidade.  
✅ Exibe alerta quando o estoque estiver abaixo do nível mínimo cadastrado.  
  
## 🛠 Estrutura do Banco de Dados  
### 🛒 Tabela `tb_produtos`
| Campo         | Tipo de Dado       | Restrições             |
|--------------|------------------|------------------------|
| `idProduto`  | `int`            | Chave primária (PK)   |
| `codProduto` | `String`         | Único                 |
| `produto`    | `String`         | Único                 |
| `qtdMinima`  | `int`            |                        |
| `estoque`    | `int`            |                        |
| `dataCadastro` | `LocalDate`    |                        |
| `valorCusto`  | `double`        |                        |


### 👤 Tabela `tb_usuarios`
| Campo       | Tipo de Dado       | Restrições              |
|------------|------------------|------------------------|
| `idUsuario` | `int`          | Chave primária (PK)   |
| `usuario`   | `String`       | Único, Não Nulo       |
| `senha`     | `String`       | Não Nulo              |
| `funcao`    | `Enum(Funcao)` | Define permissões     |


### 🔄 Tabela `tb_movimentacao`
| Campo             | Tipo de Dado         | Restrições              |
|------------------|------------------|------------------------|
| `idMovimentacao` | `int`           | Chave primária (PK)   |
| `TipoMovimentacao` | `Enum(TipoMovimentacao)` | Entrada/Saída        |
| `dataMovimentacao` | `LocalDate`    | Não Nulo              |
| `quantidade`      | `int`           | Não Nulo              |  


## 🚀 Desafios Técnicos Superados
1. **Mapeamento JPA**: Implementação de relacionamentos entre entidades e gestão do ciclo de vida dos dados.
2. **Tratamento de Exceções**: Criação de uma estrutura personalizada para tornar a API mais resiliente.
3. **Programação Funcional**: Uso de funções Lambda para manipulação de coleções e simplificação de fluxos.

## 🛠 Tecnologias Utilizadas  
* Java 17  
* Spring Boot 3.1.3  
* Spring Data JPA  
* Hibernate  
* Docker  
* Banco de Dados MySQL  
* Swagger para documentação da API

## 📦 Como rodar o projeto
1. Clone o repositório:
   ```bash
   git clone [https://github.com/manzano-pje/estoque.git](https://github.com/manzano-pje/estoque.git)
Configure o seu MySQL e atualize o application.properties.

Execute o projeto via Maven:

Bash
./mvnw spring-boot:run
