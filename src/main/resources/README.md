# Configuração do Projeto

Utilize o `application.properties` para alterar as propriedades da aplicação, utilize informações que são constantes e que dificilmente vão sofre alterações.

## Exemplos

Você pode alterar o `server.port` e utilizar a porta que quiser por padrão o Spring utiliza o `8080`, porém está configurado para utilizar o `8083`:
```js
server.port=8083
```
Aqui ele permite utilizar o H2 e você pode utilizar o `/h2-console` para acessar via Browser, exemplo `http://localhost:8083/h2-console`:
```js
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

E as demais configurações:
```js
spring.datasource.url=jdbc:h2:file:~/treinamento
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create-drop
```
- `datasource.url` fala aonde vai ficar salvo nosso banco;
- `username` e `password` já são autoexplicativos;
- `driver-class-name` diz qual drive estamos utilizando;
- `show-sql` é para apresentar o SQL via Console;
- `format_sql` faz com que seja indentado no formato SQL;
- `ddl-auto` utiliza a estratégia de Dropar as tabelas e depois criar.