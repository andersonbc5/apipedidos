# API de Gestão de Pedidos

Este projeto é uma **API REST desenvolvida com Java e Spring Boot** para gerenciar clientes, produtos e pedidos.
O objetivo do projeto é **praticar conceitos de desenvolvimento backend**, como arquitetura em camadas, uso de DTOs, tratamento de exceções e integração com banco de dados.

---

# Tecnologias utilizadas

* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok


---

# Estrutura do projeto

O projeto foi organizado seguindo uma arquitetura em camadas:

```
controller  -> recebe as requisições HTTP
service     -> contém as regras de negócio
repository  -> comunicação com o banco de dados
model       -> entidades do sistema
dto         -> objetos de entrada e saída da API
mapper      -> conversão entre entidade e DTO
exceptions  -> tratamento global de erros
```

# Banco de dados

O projeto utiliza **PostgreSQL**.

Principais entidades:

* Cliente
* Produto
* Pedido
* ItemPedido


Relacionamentos :

* Cliente → Pedido (1:N)
* Pedido → ItemPedido (1:N)
* Produto → ItemPedido (1:N)


# Objetivo do projeto

Este projeto faz parte dos meus estudos para **transição para a área de desenvolvimento backend com Java**.






