📦 Sistema de Controle de Estoque
Este projeto é uma API REST desenvolvida para gerenciar o cadastro de produtos e usuários, além de realizar o controle de estoque com regras de movimentação de itens.

✨ Funcionalidades  
🛒 Produtos  
✅ CRUD completo: Criar, listar, atualizar e excluir produtos.  
✅ Listagem geral de produtos.  
✅ Filtros por nome e código do produto.  
✅ Controle de estoque mínimo e atualização automática de saldo.   
   
👤 Usuários  
✅ CRUD completo: Criar, listar, atualizar e excluir usuários.  
✅ Permite alteração de senha.  
✅ Controle de função do usuário por meio de enumeração (ADMIN, USUARIO_COMUM, etc.).  
  
🔄 Movimentação de Estoque  
✅ Cadastro de entrada e saída de produtos.  
✅ Bloqueia movimentações quando a quantidade solicitada for maior do que a disponível.  
✅ Filtragem de movimentações por período específico ou listagem total.  
✅ Registro do usuário e horário da movimentação para rastreabilidade.  
✅ Exibe alerta quando o estoque estiver abaixo do nível mínimo cadastrado.  
  
🛠 Estrutura do Banco de Dados  
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


🛠 Tecnologias Utilizadas  
* Java 17  
* Spring Boot 3.1.3  
* Spring Data JPA  
* Hibernate  
* Docker  
* Banco de Dados MySQL  
* Swagger para documentação da API  
