
# Microsserviço de Envio de Email

[Read this page in English](https://github.com/gabrielreisresende/ms-email/blob/main/README.md)

## Descrição
Esse repositório é um microsserviço de envio de email assíncrono.
Esse serviço consome uma fila do RabbitMQ e utiliza o serviço SMTP do Gmail para enviar os emails.
Também existe um Dead Letter Queue para trtar possíveis erros na leitura e desserialização da fila.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.3.2
- Docker
- RabbitMQ
- Serviço de Email SMTP do Gmail

## Como utilizar
Basta clonar o projeto ou baixar o arquivo zip e acessar o diretório do arquivo.

Para clonar execute o seguinte comando:

```
git clone https://github.com/gabrielreisresende/ms-email.git
```

Para rodar o projeto execute o seguinte comando:

```
docker compose up -d --build
```

Pronto, o serviço estará rodando na porta 8080 da sua máquina e o painel de gerenciamento do RabbitMQ estará disponível na porta 15672.
