# Teste Estágio Backend Developer - Loja Simples em Java

## Descrição

Este projeto é uma implementação simples de um sistema de loja online em Java, desenvolvido para o teste de estágio Backend Developer.

Contém as classes principais:

- **Usuário** — dados de cadastro (nome, CPF, e-mail, etc.)
- **Produto** — informações do produto (nome, preço, quantidade)
- **Venda** e **ItemVenda** — para gerenciar compras e itens em vendas

O sistema simula um banco de dados em memória, cria usuários e produtos, realiza vendas e exibe um resumo final.

## Tecnologias

- Java 11+
- JUnit 5 + Mockito para testes
- Spring Boot (para facilitar execução com CommandLineRunner, opcional)

## Funcionalidades

- Cadastro simulado de usuários e produtos
- Realização de compras com validação de estoque
- Resumo das vendas exibido no console
- Tratamento básico de exceções

## Como executar

1. Clone o repositório
2. Execute a classe `Simulacao` que implementa `CommandLineRunner`
3. Confira as saídas no console com o resumo das vendas
