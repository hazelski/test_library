📚 Biblioteca App
Aplicação desenvolvida para gerenciar uma biblioteca, incluindo cadastro e manipulação de livros, autores, e seus respectivos detalhes. 
Com este sistema, é possível registrar novos livros, vincular a um autor, e listar ou buscar livros por autor, além de outros recursos.

📜 Visão Geral
Este projeto foi desenvolvido com o objetivo de fornecer uma solução para o gerenciamento de bibliotecas, 
permitindo controle detalhado sobre os livros e autores. A aplicação foi construída utilizando Java com o framework Spring Boot, 
garantindo uma arquitetura modular e escalável. Também são aplicadas práticas de validação para assegurar consistência dos dados, 
como evitar a exclusão de autores com livros cadastrados.

🔧 Tecnologias Utilizadas
Java 21: Linguagem principal do projeto, com suporte a recursos modernos e performance aprimorada.
Spring Boot 3.3.5: Framework para construção de aplicações Java robustas e escaláveis.
Jakarta Validation API: Responsável pela validação de dados, com anotações customizadas.
Banco de Dados: Suporte a bancos relacionais (ex.: PostgreSQL ou MySQL).
Maven: Gerenciador de dependências e build da aplicação.
Docker: Para containerização da aplicação.
⚙ Funcionalidades Principais
CRUD de Livros: Criação, leitura, atualização e exclusão de livros.
Associação de Autores: Vinculação de livros a autores específicos.
Validação Customizada: Uso de uma anotação personalizada para impedir a exclusão de autores que possuem livros cadastrados.
Busca por Autor: Obtenção de lista de livros de um autor específico.

📂 Estrutura do Projeto

├── src/main/java

│   ├── com.test.library

│   │   ├── controller          # Controllers REST da aplicação

│   │   ├── model               # Modelos de domínio e DTOs

│   │   ├── repository          # Repositórios JPA

│   │   ├── service             # Serviços de negócios

│   │   └── validation          # Validações customizadas

└── src/test/java               # Testes unitários e de integração

🛠️ Configuração e Execução
Clone o Repositório:
git clone https://github.com/hazelski/test_library
cd biblioteca-app

Executando a Aplicação com Maven:
mvn clean install
mvn spring-boot:run

🚀 Testes e Qualidade de Código
Testes Unitários: O projeto conta com testes unitários que cobrem a lógica das principais funcionalidades.

Executando Testes
Para rodar todos os testes, use o comando:
mvn test

🖥️ Endpoints da API
Abaixo estão alguns dos principais endpoints expostos pela aplicação:
Livros
POST /livro: Cria um novo livro.

GET /livro: Lista todos os livros.

GET /livro/{id}: Busca um livro por ID.

PUT /livro/{id}: Atualiza as informações de um livro.

DELETE /livro/{id}: Remove um livro.

GET /livro/byautor/{autorId}: Lista todos os livros de um autor específico.

Autores
POST /autor: Cadastra um novo autor.

GET /autor: Lista todos os autores.

GET /autor/{id}: Busca um autor pelo ID.

PUT /autor/{id}: Edita as informações de um autor.

DELETE /autor/{id}: Remove um autor, desde que ele não possua livros cadastrados.