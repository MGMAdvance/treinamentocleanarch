# Entrypoint / Entity

Utilize algumas desses _Annotations_ para atribuir um novo nome ao campo:
- `@JsonProperty("nome_cliente")`, define o nome que será passado pelo JSON;
- `@JsonAlias({"nome_cliente","nomeDoGrandioso","nome"})`, crie uma ou várias aliás referente ao campo, use uma Array para colocar suas alias `{"dessa_forma"}`.

Contrato do JSON:

```json
{
  "nome_cliente": "Fulano de tal",
  "doc_cliente": "321313132",
  "email_cliente": "cliente@cliente.com",
  "data_nacimento": "24/12/1990",
  "telefone_cliente": "13974236026",
  "enderecos": [
    {
      "id": 1,
      "nome_logradouro": "Rua do Profissional",
      "numero": 555,
      "bairro": "Vila dos Profissionais",
      "cep": 11706370,
      "cidade": "Praia Grande",
      "estado": "São Paulo"
    },
    {
      "id": 2,
      "nome_logradouro": "do Feriado",
      "numero": 555,
      "bairro": "Vila dos Feriado",
      "cep": 11706373,
      "cidade": "Praia Grande",
      "estado": "São Paulo"
    }
  ]
}
```