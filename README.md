# Encurtador de URLs

Este repositório contém um projeto Java que implementa um encurtador de URLs. A ideia principal é permitir que o usuário informe uma URL longa e o sistema retorne uma versão mais curta, que redirecione para o endereço original quando acessada.

Este projeto foi desenvolvido como parte dos meus estudos em Java com foco no desenvolvimento de aplicações backend simples e utilitárias.

---

## Tecnologias utilizadas


- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco de dados H2 (em memória)
- Lombok
- Maven

---

## Funcionalidades

- Receber uma URL longa como entrada  
- Gerar uma URL curta única que expira depois de uma hora.  
- Redirecionar quem acessa a URL curta para a URL original.  


---

## Como funciona

1. O usuário envia uma **URL longa** para o endpoint `/shorten_url`.  
2. O sistema gera um identificador único para essa URL.  
3. A URL encurtada é retornada e é usada para redirecionar para o endereço original quando acessada.

---

## Como Executar

Siga estas instruções para obter uma cópia do projeto funcionando em sua máquina local.

1. **Clone o repositório:**  
    ```sh
    git clone https://github.com/AntonioNeto18/encurtador-url-java.git
    cd encurtador-url-java
    ```

2.  **Execute o projeto**
    ```sh
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

---
