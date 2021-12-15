### Sistema para o gerenciamento de pessoas



​	Neste projeto, foi desenvolvido um sistema para o gerenciamento de pessoas de uma empresa através de uma API REST e utilizando Spring Boot.



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

Para adicionar um usuário use o POST em:

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



Para visualizar a lista se usuários use o GET em:

```
http://localhost:8080/api/v1/people/ (Lista de usuários)
http://localhost:8080/api/v1/people/{id} (Busca por id)
```

Para atualizar um usuário use o PUT em:

```
http://localhost:8080/api/v1/people/{id}
```

Para deletar um usuário use o DELETE em:

```
http://localhost:8080/api/v1/people/{id}
```

