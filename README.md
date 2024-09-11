---

# san-giorgio-api

## Introdução

Este projeto implementa uma funcionalidade para validar e processar pagamentos realizados por vendedores. A funcionalidade recebe um objeto contendo o código do vendedor e uma lista de pagamentos realizados. O sistema valida a existência do vendedor e o código da cobrança, determina se o pagamento é parcial, total ou excedente, e envia o objeto para uma fila SQS distinta com base na situação de pagamento. O sistema retorna o mesmo objeto com a informação do status de pagamento preenchida.

O banco de dados utilizado é o PostgreSQL.

## Requisitos Mínimos

- **Java:** 17+
- **Gradle:** 8.10+
- **Localstack:** Para emular serviços da AWS localmente
- **Docker:** Para rodar o Localstack
- **Banco de Dados:** PostgreSQL

## Requisitos Funcionais

1. **Receber Objeto de Entrada:**
   - O sistema deve receber um objeto contendo o código do vendedor e uma lista de pagamentos realizados.

2. **Validação do Vendedor:**
   - O sistema deve verificar se o vendedor informado no objeto existe na base de dados. Caso contrário, deve retornar uma mensagem de erro informando que o vendedor não foi encontrado.

3. **Validação do Código da Cobrança:**
   - Para cada pagamento na lista, o sistema deve verificar se o código da cobrança existe na base de dados. Caso contrário, deve retornar uma mensagem de erro informando que o código da cobrança não foi encontrado.

4. **Validação do Valor do Pagamento:**
   - O sistema deve comparar o valor do pagamento com o valor original cobrado para determinar se o pagamento é parcial, total ou excedente.

5. **Envio para Fila SQS:**
   - O objeto deve ser enviado para uma fila SQS distinta dependendo da situação do pagamento (parcial, total ou excedente).

6. **Preenchimento do Status de Pagamento:**
   - Após o processamento, o sistema deve preencher a informação do status de pagamento no objeto recebido.

## Requisitos Não Funcionais

1. **Testes Unitários:**
   - O sistema deve ser testável através de testes unitários.

2. **Tratamento de Resposta e Status Code:**
   - O sistema deve retornar uma resposta com status code 200 em caso de sucesso e 4XX em caso de erro.

## Endpoints da Aplicação

### Vendedores

- **POST /sellers**
   - **Descrição:** Cria um novo vendedor.
   - **Body:**
     ```json
     {
       "name": "nome vendedor"
     }
     ```
   - **Resposta:**
     ```json
     {
       "id": "uuid",
       "name": "nome vendedor"
     }
     ```

- **GET /sellers**
   - **Descrição:** Retorna uma lista de todos os vendedores.
   - **Resposta:**
     ```json
     [
       {
         "id": "uuid",
         "name": "nome vendedor"
       }
     ]
     ```

- **GET /sellers/{uuid}**
   - **Descrição:** Retorna um vendedor específico pelo ID.
   - **Resposta:**
     ```json
     {
       "id": "uuid",
       "name": "nome vendedor"
     }
     ```

### Cobranças

- **POST /api/charges**
   - **Descrição:** Cria uma nova cobrança.
   - **Body:**
     ```json
     {
       "value": 1500.00
     }
     ```
   - **Resposta:**
     ```json
     {
       "id": "uuid",
       "value": 1500.00
     }
     ```

- **GET /api/charges**
   - **Descrição:** Retorna uma lista de todas as cobranças.
   - **Resposta:**
     ```json
     [
       {
         "id": "uuid",
         "value": 1500.00
       }
     ]
     ```

- **GET /api/charges/{uuid}**
   - **Descrição:** Retorna uma cobrança específica pelo ID.
   - **Resposta:**
     ```json
     {
       "id": "uuid",
       "value": 1500.00
     }
     ```

### Pagamentos

- **POST /api/payments**
   - **Descrição:** Registra um novo pagamento.
   - **Body:**
     ```json
     {
       "seller_id": "7998c674-e74b-4789-9a20-600995a39f7b",
       "payment_items": [
         {
           "charge_id": "8fa72fb9-e4f3-4be6-b973-a95003dc6ad4",
           "value": 100
         }
       ]
     }
     ```
   - **Resposta:** 204

---