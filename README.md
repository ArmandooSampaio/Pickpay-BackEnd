# PickPay Backend

## Introdução

O PickPay Backend é uma implementação simplificada de um sistema de pagamento inspirado no PicPay, desenvolvido com Spring Boot. Este projeto foi baseado em um desafio lançado pelo PicPay para selecionar programadores. O desafio consistia em criar uma API para atender aos seguintes requisitos de negócio:

- Existem dois tipos de usuários: comuns e lojistas, ambos com uma carteira de dinheiro que permite transferências entre eles. Este documento se concentra no fluxo de transferência entre dois usuários.

### Requisitos

#### Cadastro de Usuários

- Para ambos os tipos de usuários, é necessário coletar as seguintes informações: Nome Completo, CPF, e-mail e Senha.
- O CPF/CNPJ e endereços de e-mail devem ser únicos no sistema, ou seja, não é permitido cadastrar mais de um usuário com o mesmo CPF ou endereço de e-mail.

#### Transferências de Dinheiro

- Usuários podem enviar dinheiro, ou seja, realizar transferências, para lojistas ou entre usuários.
- Lojistas apenas recebem transferências e não podem enviar dinheiro para outros usuários.
- Antes de concluir uma transferência, o sistema deve validar se o usuário possui saldo suficiente para a transação.
- Antes de finalizar a transferência, é necessário consultar um serviço autorizador externo. Utilize o seguinte mock para simular essa consulta: [Mock de Autorização](https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6).
- A operação de transferência deve ser tratada como uma transação, o que significa que, em caso de inconsistência, o dinheiro deve ser revertido para a carteira do usuário que realizou a transferência.

#### Notificações de Pagamento

- Quando um usuário ou lojista recebe um pagamento, eles devem ser notificados por meio de um serviço de terceiros. Este serviço pode estar indisponível ou instável, e você deve simular o envio de notificações usando o seguinte mock: [Mock de Notificação](http://o4d9z.mocklab.io/notify).
- A API deve ser RESTful, seguindo os princípios de design de APIs.

## Tecnologias Utilizadas

O PickPay Backend foi desenvolvido utilizando as seguintes tecnologias:

- Java 17
- Spring Boot 3.1.2
- Spring MVC
- Spring Data JPA
- Banco de Dados: H2
- Ferramenta de Build: Maven

## Funcionalidades Principais

### Gerenciamento de Usuários

#### Descrição

O sistema é responsável por criar e gerenciar os usuários na base de dados.

#### Endpoints

- `POST "/users"`: Cria um novo usuário.
- `GET "/users"`: Retorna todos os usuários cadastrados.

### Gerenciamento de Transações

#### Endpoints

- `POST "/transactions"`: Cria uma nova transação.
- `GET "/transactions"`: Retorna todas as transações cadastradas.

## Exemplos de Uso

Aqui estão alguns exemplos de como usar os endpoints da API:

### Criação de Novo Usuário

**Requisição POST** `"/users"`

```json
{
  "nomeCompleto": "João da Silva",
  "cpf": "123.456.789-00",
  "email": "joao@example.com",
  "senha": "senha123"
}



