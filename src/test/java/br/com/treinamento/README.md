# Testes unitários com Junit, Coverage e Mockito

Os testes unitários são testes desenvolvidos pelo programador, aqui são testados os cenários que esperamos que funcione e os cenários em que ocorre algo inesperado, como por exemplo:

- Um cadastro onde faltam campos obrigatórios;
- Uma busca em api externa que não foi possível ser usada;
- Uma validação de documento que não passou nos requisitos mínimos.

## Junit

O Junit é o responsável por executar os métodos de teste e também por dizer ao Java que esses métodos precisam ser validados para indicar se o cenário que estamos testando foi realizado com sucesso, ele irá buildar nossas classes de teste em paralelo a nossa aplicação real e poderemos ver quais teste passaram e quais precisamos revisar no caso de falha.

## Coverage

Para um controle melhor da qualidade do código, temos o `coverage`, com ele nós conseguimos ver a porcentagem de sucesso de cobertura nas classes que foram testadas e manter a qualidade da aplicação o melhor possível, quanto mais uma classe for coberta por um teste, teremos mais garantia de que aquela classe vá funcionar da forma que arquitetamos no inicio. 

Nem todas as classes precisam ter uma cobertura de 100%, com exceção das camadas `UseCase, DataProvider e Entrypoint` que são essenciais para a aplicação como um todo, classes como `Entity, Table, HttpModel` fazem parte dessas camadas mas nem sempre usamos todos os métodos disponíveis, um exemplo, podemos ter o cadastro de um usuário onde o campo `foto de perfil` é opcional, neste caso podemos realizar o teste desse cadastro sem passar uma imagem a ser cadastrada.

## Mockito

Por fim temos o Mockito, que será o responsável por garantir que possamos testar todos os métodos de forma que as operações realizadas não impactem no nosso sistema real, assim podemos simular diversos cenários sem nos preocupar, por exemplo, em testar um crud completo de alguma tabela sem se preocupar se o usuário que estamos criando vai ser registrado de verdade no nosso banco.

## Anotações importantes

Aqui temos a lista de algumas anotações que frequentemente seram utilizadas nesse processo de testes:

`@RunWith` : Serve para indicarmos que a classe será testada ao subirmos o build do JUnit, garantindo que os testes sejam mapeados.

```java
  @RunWith(MockitoJUnitRunner.class)
  public class MinhaClasseTest {
    /* Código de teste aqui */
  }
```

`@InjecjMocks` : Usamos essa anotação para instanciarmos a classe que será testada de fato por nós, ou seja, se vamos testar uma classe `Agenda.java` que desenvolvemos teremos o seguinte código:

```java
  @InjectMocks
  private Agenda minhaAgenda;
```

`@Mock` : Se na nossa classe `Agenda` precisarmos instanciar alguma outra classe para realizarmos suas operações, nós usamos o @Mock, ele será responsável por instanciar essa classe com o mesmo princípio que vimos com o Mockito, sem que suas operações impliquem em alterações reais dentro do sistema, suponhamos que nossa agenda receba os contatos que serão testados, para gravarmos esses contatos temos uma classe `BDContatos.java`, teriamos o seguinte código:

```java
  @InjectMocks // Classe que esta sendo testada
  private Agenda minhaAgenda;

  @Mock // Dependemos da classe BDContatos para acessar a base de dados
  private BDContatos contatosDatabase;
```

`@Test` : Por fim temos a anotação @Test, como o nome já diz, ela é responsável por indicar que o método void que estamos criando se trata de um teste, ou seja, é uma classe que não faz parte da aplicação principal.

## Exemplo prático

Para exemplificar os conceitos que foram tratados acima, vamos construir um teste bem simples para a classe Agenda:

```java
  @InjectMocks // Classe que esta sendo testada
  private Agenda minhaAgenda;

  @Mock // Dependemos da classe BDContatos para acessar a base de dados
  private BDContatos contatosDatabase;

  @Test // Indicamos que essa função void será executada como um teste no JUnit
  public void cadastrandoNovoContatoNaBaseDeDados(){

    /* contato novo com nome e telefone */
    Contato novoContato = new Contato("Victor","13 99774444");

    /* aqui representamos como esperamos que o contato retorne após ser salvo, no caso ele agora tem um ID dentro da base de dados */
    Contato contatoSalvo = new Contato(1,"Victor","13 99774444");

    /* Aqui estamos garantindo que o método que irá gravar os contatos na base de dados não seja executado no nosso banco de dados real, o Mockito irá simular toda a operação pra gente */
    Mockito.when(contatosDatabase.saveContato(Mockito.any(Contato.class))).thenReturn(contatoSalvo);

    /* o método cadastrarContato da classe agenda irá retornar um objeto novo. Nesse caso estamos passando um contato com nome e telefone e esperamos que esse mesmo contato retorne com um ID */
    Contato response = minhaAgenda.cadastrarContato(novoContato);

    /* Para garantirmos que o objeto response retornou com um ID após ser salvo na base de dados, usamos o Assert, ele irá comparar o campo ID do objeto e retornará true caso o campo não seja null */
    Assert.assertThat(response.getId(), Matchers.notNullValue());
  }
```
