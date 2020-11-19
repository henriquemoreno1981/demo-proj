# Demo-proj

## Compilando e executando 

**Linux/UNIX/OSX**

```
./mvnw clean install spring-boot::run
```

**Windows**

```
mvnw clean install spring-boot::run
```

Neste comando irá baixar o `apache maven`, além das dependencias e compilará todos os módulos, depois irá instalar os módulos, além de iniciar o servidor.

O Swagger se encontra em:

http://localhost:8080/swagger-ui.html

## Ferramentas

- Banco de dados: [H2](https://h2database.com/html/main.html)
- Teste Unitário: [Junit 5](https://junit.org/junit5/)
- Framework: [Spring Boot 2.4.0](https://start.spring.io/)
- Documentação da API:  [SpringDoc](https://springdoc.org/)
- IDE: [IntelliJ CE](https://www.jetbrains.com/idea/download/)
- Gerenciamento de versão: [GIT](https://git-scm.com/)
- Controle de Software: [Maven](https://maven.apache.org/)

## Estrutura

```
.
├── README.md
├── main/
├── misc/
├── models/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── rest/
├── services/
├── test/
└── web/
```

A raiz é um projeto POM que é responsavel por compilar todos os outros módulos.

O projeto foi divido em módulos para separação dos dominios técnicos, além de possibilitar a substituição de um módulo ou remoção, se necessário. Possibilitando que uma equipe possa fazer manutenção dos módulos individualmentes.



### Main

É o módulo principal responsavel por rodar o spring-boot:run ou a classe `Main`.

### Models

Aonde todas as entidades e repositórios (DAO) se encontram, assim como os testes unitários.

### Rest

Todas as classes de controle `rest` se encontram nesse diretório e os testes unitários estão no local.

### Service

Neste diretório todos os serviços, assim como os testes unitários.  