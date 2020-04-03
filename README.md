Treinamento com Clean Architecture
===

<p align="center">
  <img alt="HitCount" src="http://hits.dwyl.com/MGMAdvance/treinamentocleanarch.svg">
</p>

Treinamento em andamento, alguns links não funcionam pois não existem por enquanto.

- [Core](src/main/java/br/com/treinamento/cleanarch/core)
  - [Entity](src/main/java/br/com/treinamento/cleanarch/core/entity)
  - [Gateway](src/main/java/br/com/treinamento/cleanarch/core/gateway)
  - [Usecase](src/main/java/br/com/treinamento/cleanarch/core/usecase)
- [DataProvider](src/main/java/br/com/treinamento/cleanarch/dataprovider)
  - [Entity](src/main/java/br/com/treinamento/cleanarch/dataprovider/entity)
  - [Feign](src/main/java/br/com/treinamento/cleanarch/dataprovider/feign)
  - [Mapper](src/main/java/br/com/treinamento/cleanarch/dataprovider/mapper)
  - [Repository](src/main/java/br/com/treinamento/cleanarch/dataprovider/repository)
- [Entrypoint](src/main/java/br/com/treinamento/cleanarch/entrypoint)
  - [Entity](src/main/java/br/com/treinamento/cleanarch/dataprovider/entity)
  - [Mapper](src/main/java/br/com/treinamento/cleanarch/dataprovider/mapper)
- [Exception](src/main/java/br/com/treinamento/cleanarch/configuration/exception)
- Resources
  - [Configuração da Aplicação](/src/main/resources)

## Estrutura do projeto

```sh
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── treinamento
│   │   │               └── cleanarch
│   │   │                   ├── configuration
│   │   │                   ├── core
│   │   │                   │   ├── entity
│   │   │                   │   ├── gateway
│   │   │                   │   └── usecase
│   │   │                   ├── dataprovider
│   │   │                   │   ├── entity
│   │   │                   │   ├── feign
│   │   │                   │   ├── mapper
│   │   │                   │   └── repository
│   │   │                   └── entrypoint
│   │   │                       ├── entity
│   │   │                       └── mapper
│   │   └── resources
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── br
│               └── com
│                   └── treinamento
│                       └── cleanarch
└...
```
