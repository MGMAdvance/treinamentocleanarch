# DataProvider

Ele é a camada que faz a conversar com o banco de dados, sistema de arquivos, etc. Pode ser solicitado pela camada [*Core/UseCase*](/src/main/java/br/com/treinamento/cleanarch/core/usecase).

Exemplo: O *UseCase* chama o `ClienteDataProvider` para utilizar uma busca do cliente no banco, o DataProvider faz a sua funcionalidade e retorna com o resultado.

- *Entity*, entidades para gerar as tabelas;
- *Feign*, _não definido temporariamente_;
- *Mapper*, faz o mapeamento necessario para a comunicação;
- *Repository*, ele faz a persistência e também pode criar novos tipos de consulta.
