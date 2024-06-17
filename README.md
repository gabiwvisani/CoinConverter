## Coin Converter Application
A Coin Converter Application é uma aplicação Java desenvolvida com Spring Boot que permite o registro de clientes e a realização de compras de moedas estrangeiras. A aplicação utiliza Feign Client para consumir uma API de cotação de moedas, permitindo calcular o valor das transações em diferentes moedas.

#Funcionalidades
Registro de clientes
Listagem de todos os clientes
Consulta de cliente por ID
Registro de compras de moedas estrangeiras
Listagem de todas as compras
Consulta de compra por ID
Consumo de API externa para obtenção de cotações de moedas
#Tecnologias Utilizadas
Java
Spring Boot
Spring Data JPA
Spring Cloud OpenFeign
Hibernate
Lombok
ModelMapper
Jakarta Validation
H2 Database (para ambiente de desenvolvimento)
#Estrutura do Projeto
#Pacotes Principais
controller: Contém os controladores REST que expõem os endpoints da aplicação.
model: Contém as entidades JPA que representam os modelos de dados.
repository: Contém as interfaces de repositórios JPA para acessar o banco de dados.
request: Contém as classes de requisição que representam os dados enviados pelos clientes.
service: Contém as classes de serviço que encapsulam a lógica de negócios.
CoinConverterApplication: Classe principal para inicializar a aplicação Spring Boot.
#Endpoints
Cliente
Registrar Cliente

POST /registrar/cliente
Request Body: ClienteRequest
Response: ID do cliente criado
Buscar Todos os Clientes

GET /buscar/clientes
Response: Lista de todos os clientes
Buscar Cliente por ID

GET /buscar/cliente/{id}
Path Variable: id - ID do cliente
Response: Dados do cliente
Compra
Registrar Compra

POST /compra
Request Body: CompraRequest
Response: ID da compra criada
Buscar Todas as Compras

GET /buscar/compras
Response: Lista de todas as compras
Buscar Compra por ID

GET /buscar/compra/{id}
Path Variable: id - ID da compra
Response: Dados da compra
