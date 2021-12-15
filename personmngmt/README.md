### Sistema para o gerenciamento de pessoas



​	Neste projeto foi desenvolvido um sistema para cadastro e gerenciamento de pessoas de uma empresa, através de uma API REST, utilizando Spring Boot. Ainda, foi feita a implementação do sistema na nuvem através do Heroku e o desenvolvimento de testes unitários para validação das funcionalidades.



Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Para visualizar a execução do projeto:

```
http://localhost:8080/api/v1/people
```



### Interagindo com o projeto

Para visualizar, adicionar, deletar e atualizar um usuário utilize o Postman ou Insomnia:

- Para adicionar um usuário use o POST em:

```
http://localhost:8080/api/v1/people
```

Passando as propriedades:

```
{   "firstName": "",
    "lastName": "",
    "cpf": "",
    "gender": "",
    "birthDate": "",
    "phones":[{
        "type": "",
        "number": ""
    }]
}
```

Sendo o formato das propriedades:

- cpf: "123.456.789-00"
- birthDate: "01-01-1999"
- number: "(11)987655432"

E o número do CPF deve ser verdadeiro.



- Para visualizar a lista se usuários use o GET em:

```
http://localhost:8080/api/v1/people/ (Lista de usuários)
http://localhost:8080/api/v1/people/{id} (Busca por id)
```

- Para atualizar um usuário use o PUT em:

```
http://localhost:8080/api/v1/people/{id}
```

- Para deletar um usuário use o DELETE em:

```
http://localhost:8080/api/v1/people/{id}
```



### Utilizando o Heroku

A interação com o projeto também pode ser feita através do Heroku. Após realizar o deploy da aplicação na plataforma:

- Para adicionar um usuário use o POST em:

```
http://personmngmt.herokuapp.com/api/v1/people
```

​	Passando as mesmas propriedades anteriores.



- Para visualizar a lista se usuários use o GET em:

```
http://personmngmt.herokuapp.com/api/v1/people/ (Lista de usuários)
http://personmngmt.herokuapp.com/api/v1/people/{id} (Busca por id)
```

- Para atualizar um usuário use o PUT em:

```
http://personmngmt.herokuapp.com/api/v1/people/{id}
```

- Para deletar um usuário use o DELETE em:

```
http://personmngmt.herokuapp.com/api/v1/people/{id}
```



### Executando Testes

Para executar os testes desenvolvidos, basta executar o seguinte comando:

```shell script
mvn clean test
```

