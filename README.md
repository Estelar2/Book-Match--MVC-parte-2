Book MatchMVC

Sistema de recomendação de livros desenvolvido em Java utilizando o padrão MVC, JSP, Servlets, MySQL e API REST com autenticação JWT.

Descrição

O BookMatchMVC é uma aplicação web que permite o gerenciamento de livros através de uma interface gráfica e também por meio de uma API REST.

O sistema possui funcionalidades de:

* Cadastro de livros
* Listagem de livros
* Edição de livros
* Exclusão de livros
* Busca de livro por ID
* Recomendação de livros por gênero
* Cadastro de usuários
* Login com autenticação JWT
* API protegida por Token

---

Tecnologias Utilizadas

* Java 17
* JSP
* Servlets Jakarta EE
* Apache Tomcat 10
* Maven
* MySQL
* Gson
* JWT (JSON Web Token)
* BCrypt

---

Estrutura do Projeto

BookMatchMVC
│
├── controller
├── model
├── dao
├── service
├── security
├── util
│
├── cadastrar.jsp
├── listar.jsp
├── editar.jsp
├── recomendacoes.jsp
└── index.jsp

---

Configuração do Banco de Dados

Criar o banco:

sql
CREATE DATABASE bookmatch;

Executar o script SQL disponibilizado no projeto para criar e popular a tabela de livros.

---

Como Executar

1. Importar o projeto no NetBeans.
2. Configurar o Apache Tomcat 10.
3. Configurar o MySQL.
4. Executar o script do banco de dados.
5. Realizar Clean and Build.
6. Executar o projeto.

URL:

text
http://localhost:9090/BookMatchMVC/

---

Autenticação

A API utiliza JWT para autenticação.

Primeiro faça login:

http
POST /api/login

Exemplo:

json
{
  "email": "usuario@email.com",
  "senha": "123456"
}

Resposta:

json
{
  "mensagem": "Login realizado com sucesso.",
  "token": "SEU_TOKEN_JWT"
}

Utilize o token nas próximas requisições:

http
Authorization: Bearer TOKEN

---

Endpoints da API

## 1. Cadastrar Usuário

http
POST /api/usuarios

Body:

json
{
  "nome": "Matheus",
  "email": "matheus@email.com",
  "senha": "123456"
}

---

2. Login

http
POST /api/login

Body:

json
{
  "email": "matheus@email.com",
  "senha": "123456"
}

---

3. Listar Todos os Livros

http
GET /api/livros/

---

4. Buscar Livro por ID

http
GET /api/livros/{id}

Exemplo:

http
GET /api/livros/1

---

5. Recomendar Livros por Gênero

http
GET /api/livros?genero=Fantasia

Exemplos:

http
GET /api/livros?genero=Romance

http
GET /api/livros?genero=Suspense

http
GET /api/livros?genero=Ficção Científica

---

6. Cadastrar Livro

http
POST /api/livros/

Body:

json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "genero": "Tecnologia",
  "avaliacao": 9.5,
  "descricao": "Boas práticas de programação."
}

---

7. Atualizar Livro

http
PUT /api/livros/{id}

Exemplo:

http
PUT /api/livros/1

Body:

json
{
  "titulo": "Novo Título",
  "autor": "Novo Autor",
  "genero": "Fantasia",
  "avaliacao": 9.0,
  "descricao": "Descrição atualizada."
}

---

8. Excluir Livro

http
DELETE /api/livros/{id}

Exemplo:

http
DELETE /api/livros/1

---

Regras de Negócio

* Título obrigatório.
* Autor obrigatório.
* Gênero obrigatório.
* Descrição obrigatória.
* Avaliação deve estar entre 0 e 10.

Projeto acadêmico desenvolvido para a disciplina de Desenvolvimento Web utilizando Java MVC, JSP, Servlets, MySQL e API REST.
