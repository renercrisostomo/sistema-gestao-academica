# sistema-gestao-academica

Projeto de um Sistema de Gestão Acadêmica para a disciplina Tópicos de Java para a WEB (TJW) do curso Ciências da Computação do IFCE Maracanaú.

## Visão Geral

Este projeto consiste em um sistema de gestão acadêmica com um backend desenvolvido em Java com Spring Boot e um frontend renderizado no servidor utilizando Thymeleaf.

## Pré-requisitos

Antes de começar, certifique-se de ter o seguinte instalado:

* Java JDK (versão 21 ou superior)
* Apache Maven (versão 3.6 ou superior)
* PostgreSQL

## Configuração

1. **Banco de Dados**:
   As configurações do banco de dados estão localizadas no arquivo `src/main/resources/application.properties`.

   ```properties
   # Configurações do banco de dados PostgreSQL
   spring.datasource.url=jdbc:postgresql://localhost:5432/tjw20251_db
   spring.datasource.username=postgres
   spring.datasource.password=admin
   ```

   > **Nota**: Crie um banco de dados no PostgreSQL com o nome `tjw20251_db` ou altere o valor de `spring.datasource.url` para o nome do seu banco. Ajuste o `username` and `password` de acordo com a sua instalação do PostgreSQL.

## Executando a Aplicação

1. Certifique-se de estar no diretório raiz do projeto.

2. Execute o servidor de desenvolvimento Spring Boot:

   ```bash
   ./mvnw spring-boot:run
   ```

   No Windows, se `./mvnw` não funcionar diretamente no PowerShell, você pode usar:

   ```powershell
   cmd /c "mvnw.cmd spring-boot:run"
   ```

   Ou, se você tiver o Maven instalado globalmente:

   ```bash
   mvn spring-boot:run
   ```

   A aplicação estará disponível em `http://localhost:8080` (ou a porta configurada em `application.properties`).

## Estrutura do Projeto (Simplificada)

```text
sistema-gestao-academica/
└── src/
    ├── main/
    │   ├── java/br/edu/ifce/meuprimeirospringboot/
    │   │   ├── beans/         # Entidades JPA
    │   │   ├── controller/    # Controladores Spring MVC
    │   │   ├── repository/    # Repositórios Spring Data JPA
    │   │   ├── service/       # Lógica de Negócio (Interfaces)
    │   │   └── serviceImpl/   # Lógica de Negócio (Implementações)
    │   └── resources/
    │       ├── static/        # Arquivos estáticos (CSS, JS, Imagens)
    │       ├── templates/     # Templates Thymeleaf
    │       └── application.properties
    └── test/
```
