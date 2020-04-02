# DataProvider / Entity

As entidades do Dataprovider serão utilizadas para gerar as tabelas do banco.

## Annotations

- `@Entity(name = "cliente")` diz para o Hibernate que essa entidade é um tabela;
    - `name = "cliente"` atribua um nome personalizado para a tabela.
- `@Id` para definir o atributo como chave primaria (PK);
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` utilize para adicionar auto incremento no ID;
    - `strategy = GenerationType.IDENTITY` é para utilizar um tipo de estratégia para gerar o ID. 
- `@Column(name = "nm_bairro")` faz com que o Hibernate use esse nome defino do que o nome do atributo que está na entidade;
- `@JsonBackReference` evita o loop infinito [link sobre ele](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion);
- `@ManyToOne`, `@OneToMany` e `@ManyToMany` basicamente é a relação entre as tabelas `N:1`, `1:N` e `N:N`, como por exemplo um *Cliente* pode ter vários *Endereços* então será `1:N`;
    - `@OneToMany(mappedBy = "cliente")` use-o para dizer aonde está a chave estrangeira (FK);
    - `@JoinColumn(name = "id_cliente")` pegar a chave estrangeira (FK);
        - `name = "id_cliente` ele faz igual o `@Column` ele da o nome para esse atributo.