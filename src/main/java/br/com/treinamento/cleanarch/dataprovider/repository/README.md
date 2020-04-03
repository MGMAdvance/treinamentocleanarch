# DataProvider / Repository

Ele implementa outra `interface` chamada `JpaRepository` do pacote `JPA`.

Passe para o `JpaRepository` qual *entidade* e tipo do campo que utiliza a notação `@Id`:
```java
public interface EnderecoRepository extends JpaRepository<EnderecoTable, Long> {

}
```
Como no exemplo ele é um *Repository* para o `EnderecoRepository`, passando o *EnderecoTable* e *Long* (Porque o Id do EnderecoTable utiliza o Id com tipo Long)..

Podemos criar novas consultas, exemplo:
```java
List<EnderecoTable> findByCep(Integer cep);
```
Estamos falando para o Jpa que queremos encontrar registros buscando por *CEP*, também podemos acrescentar `And` e alterar essa consulta, como por exemplo `findByCepAndBairro` ou até usando `Or` que ficaria `findByCepOrBairro`, utilize o campos existentes nas entidades do DataProvider.