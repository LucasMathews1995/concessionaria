<img width="700" height="467" alt="image" src="https://github.com/user-attachments/assets/ccffe233-ba2f-40b5-a81a-27a17d9ec0fc" />
<br></br>
Nome do Projeto: Backend Concessionária 🚗
<div align="center">
<img src="https://blog.deltafiat.com.br/wp-content/uploads/2023/08/carro-completo-700x467.jpeg" alt="Logo do projeto ou ícone de carro" width="150">
</div>

📝 Descrição do Projeto
Este é o backend de uma aplicação de gestão de uma concessionária de veículos. O sistema gerencia o estoque de veículos, informações de clientes, vendas, manutenções e agendamentos. Ele fornece uma API RESTful para que o frontend possa interagir com o banco de dados de forma segura e eficiente.

🚀 Tecnologias Utilizadas
Linguagem: Java 17+

Framework: Spring Boot 3+

Banco de Dados: PostgreSQL (ou MySQL, H2, etc.)

Acesso a Dados: Spring Data JPA

Segurança: Spring Security (Opcional, mas recomendado)

Documentação da API: OpenAPI / Swagger

Build Tool: Maven (ou Gradle)

Testes: JUnit 5, Mockito

⚙️ Funcionalidades da API
A API foi projetada para ser intuitiva e seguir os princípios REST. Abaixo estão as principais funcionalidades:

🚘 Veículos
GET /api/veiculos: Lista todos os veículos em estoque.

GET /api/veiculos/{id}: Detalhes de um veículo específico.

POST /api/veiculos: Adiciona um novo veículo.

PUT /api/veiculos/{id}: Atualiza os dados de um veículo.

DELETE /api/veiculos/{id}: Remove um veículo do estoque.

GET /api/veiculos/buscar?marca=...: Busca veículos por marca, modelo ou ano.

👤 Clientes
GET /api/clientes: Lista todos os clientes.

POST /api/clientes: Cadastra um novo cliente.

GET /api/clientes/{id}: Busca um cliente por ID.

💰 Vendas
GET /api/vendas: Lista todas as vendas realizadas.

POST /api/vendas: Registra uma nova venda.

🛠️ Manutenções/Serviços
GET /api/servicos: Lista todos os serviços registrados.

POST /api/servicos: Registra um novo serviço/manutenção.

<br>

Nota: Para uma documentação completa e interativa da API, acesse http://localhost:8080/swagger-ui.html após rodar o projeto.

🖥️ Pré-requisitos
Antes de rodar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

JDK 17 ou superior

Maven 3.6+

Docker (recomendado para rodar o banco de dados facilmente)


 Como Rodar o Projeto
Siga estes passos para configurar e executar o backend:

1. Clone o repositório:

Bash

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
2. Configure o Banco de Dados:

Se você usa Docker, pode iniciar o banco de dados com este comando:

Bash

docker-compose up -d
Isso irá criar e iniciar um container com o banco de dados PostgreSQL.

3. Configure o arquivo application.properties:

Crie um arquivo src/main/resources/application.properties (se ele não existir) e adicione as configurações do seu banco de dados.

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/concessionaria_db
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
4. Compile e Execute o Projeto:

Bash

mvn clean install
mvn spring-boot:run
O servidor estará disponível em http://localhost:8080.

🧪 Como Rodar os Testes
Para executar os testes automatizados, use o seguinte comando:

Bash

mvn test
🤝 Contribuição
Contribuições são sempre bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

📄 Licença
