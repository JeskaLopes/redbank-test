# Desafio Back-End-Test
Teste realizado para a F1RST DIGITAL SERVICES

Oi gente, tudo bem por ai ?
Essa é a minha versão do desafio back-end, o REDBank, tomei a liberdade de escolher até um nome(quase nada sugestivo rs).
Durante a construção me aventurei retomando conceitos de Java, e pesquisei bastante por várias formas de deixar o código o mais legível possível, pois sei que doi o coração ler aquele código que só você e Deus sabiam, e agora só Deus sabe como ler.

Utilizei as tecnologias Java 11+, Spring Boot, JPA, e segui a orientação de usar o banco de dados h2 para facilitar tanto o teste manual da API quanto os testes unitários com JUnit.


## CRIAÇÃO
Tentei fazer a criação da forma mais simples possível, focando bastante em funcionalidade e atender os requisitos, então precisei pesquisar um pouco mais sobre DTO's para que o código respeitasse as validações esperadas, como a verificação de status e saldo(balance) da conta antes de fazer as transferências.
Prezei tambem pela tratativa de erros e resposta ao usuário, onde validei o porque de uma transfência não ter sido realizada, e exibi junto com a mensagem de erro o motivo, para que ficasse mais fácil de retornar até mesmo  em consumo com um Front-End, pois ninguem merece um "Erro 500 Sucesso"!

## PAYLOADS

## Clients
### Cadastro de clientes

POST /client
```json
{
	"name":"Jessiquinha",
	"address":"Rua capi 230",
	"cpfCnpj":"4567893567",
	"senha":"ssenhacomdoiss"
}
```

## Account
### Cadastro de nova conta

POST /account
```json
{
	"agency":"003",
	"balance":5000,
	"status":"Ativa"
}
```

## Transfer
### Nova transferencia

POST /account
```json
{
	"idOriginAccount":2,
	"idDestinyAccount":3,
	"value":100
}
```

## SUGESTÃO DE MELHORIAS ( PROXIMOS PASSOS )
- Validações de senha, como quantidade de caracteres para se tornar uma senha segura
- ENUM de status de conta, ja que é um valor fixo, podendo ser até um boolean transformado em ativo/inativo
- Validação de cpfCnpj fazendo o switch de validação usando uma regra especifica para cpf e uma especifica para cnpj
- Testes unitários mais abrangentes e completos incluindo a aplicação completa 
- Utilizar uma versão mais atual do Java, mas ai seria um sonho alto, sei que nem todas as empresas aplicam pois está em fase de testes, mas o Java tem se tornado cada vez menos verboso, coisa linda de ver!

## PARA RODAR O PROJETO 
Para iniciar a aplicação somente é necessário escolher a IDE de sua preferência, e rodar o projeto como SpringBoot mesmo.

## PARA RODAR O PROJETO(MODO TESTE)
Para abrir o modo teste da aplicação, a interface do JUnit é bem intuitiva, e na maioria das IDE's mesmo já aparece a opção `run as JUnit test` assim que você seleciona uma classe dentro do package de tests


