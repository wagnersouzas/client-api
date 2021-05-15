# Projeto com Spring Boot 2.4.5

## Enunciado

Você deverá entregar um projeto Spring Boot 2.4.x contendo um CRUD completo de web services REST para acessar um recurso de clientes, contendo as cinco operações básicas:

* Busca paginada de recursos
* Busca de recurso por id
* Inserir novo recurso
* Atualizar recurso
* Deletar recurso

Um cliente possui nome, CPF, renda, data de nascimento, e quantidade de filhos. A especificação da entidade Client.

## Busca paginada de clientes
`GET /clients?page=0&linesPerPage=6&direction=ASC&orderBy=name`

## Busca de client por id
`GET /clients/1`

## Inserção de novo cliente
`POST /clients`

```json
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}

```
## Atualização de cliente
`PUT /clients`
```json
{
  "name": "Maria Silva Atualização",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}

```

## Deleção de cliente
`DELETE /clients/1`
