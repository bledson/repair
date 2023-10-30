# SISTEMA DE MANUTENÇÃO DE EQUIPAMENTOS

O sistema é dividido em dois microserviços:
* **service-orders**: serviço que gerencia as ordens de serviço e alimenta o...
* **supports**: serviço que gerencia a atuação dos técnicos na manutenção

## Rodando o projeto

Com o Docker em execução, rode o comando a seguir dentro da pasta de cada serviço (começando pelo **service-orders**):

```
mvn spring-boot:run
```

No projeto **supports** existe um usuário padrão criado, com usuário/senha "agent" que pode ser usado para obter o token de autenticação para uso nos endpoints protegidos
