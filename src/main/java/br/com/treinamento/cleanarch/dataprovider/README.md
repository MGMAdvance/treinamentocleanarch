# DataProvider

Ele é a camada que faz a conversar com o banco de dados, sistema de arquivos, etc. Pode ser solicitado pela camada *Core/UseCase*.

Exemplo: O *UseCase* chama o `ClienteDataProvider` para utilizar uma busca do cliente no banco, o DataProvider faz a sua funcionalidade e retorna com o resultado.