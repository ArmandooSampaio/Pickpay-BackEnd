# PickPay Backend Project

##Introdução
Um picpay bem simplificado com Spring Boot, foi feito com base em um desafio que
o propio pickpay Lançou para selecionar programadores um tempo atrás.
O desefio Consistia basicamente criar uma Api para o Seguinte requisito de negocio: 
Temos 2 tipos de usuários, os comuns e lojistas, ambos têm carteira com dinheiro e realizam transferências entre eles. Vamos nos atentar somente ao fluxo de transferência entre dois usuários.

Requisitos:

Para ambos tipos de usuário, precisamos do Nome Completo, CPF, e-mail e Senha. CPF/CNPJ e e-mails devem ser únicos no sistema. Sendo assim, seu sistema deve permitir apenas um cadastro com o mesmo CPF ou endereço de e-mail.

Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.

Lojistas só recebem transferências, não enviam dinheiro para ninguém.

Validar se o usuário tem saldo antes da transferência.

Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, use este mock para simular (https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6).

A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) e o dinheiro deve voltar para a carteira do usuário que envia.

No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de email, sms) enviada por um serviço de terceiro e eventualmente este serviço pode estar indisponível/instável. Use este mock para simular o envio (http://o4d9z.mocklab.io/notify).

Este serviço deve ser RESTFul.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.2
- Spring MVC
- Spring Data JPA
- Banco de Dados: H2
- Ferramenta de Build: Maven

## Funcionalidades Principais

### Gerenciamento de Usuários
**Descrição:** o Sistema Cria e gerencia usuários na base de dados.

**Endpoints:** 
- `POST "/users" o Sistema Cria um Novo Usuário
- `Get "/users" o Sistema retorna todos os usuários cadastrados

### Gerenciamento de Transações
**Endpoints:** 
- `POST "/transactions" o Sistema Cria uma Nova Transação 
- `Get "/transactions" o Sistema retorna todos as Transações



