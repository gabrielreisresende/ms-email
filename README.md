# Email Sending Microservice

## Description
This repository is an asynchronous email sending microservice.
This service consumes a RabbitMQ queue and uses Gmail's SMTP service to send emails.
There is also a Dead Letter Queue to handle possible errors in reading and deserializing the queue.

## Technologies Used
- Java 21
- Spring Boot 3.3.2
- Docker
- RabbitMQ
- Gmail SMTP Email Service

## How to Use
Just clone the project or download the zip file and access the file directory.

To clone, run the following command:
```
git clone https://github.com/gabrielreisresende/ms-email.git
```

To run the project, execute the following command:
```
docker compose up -d --build
```
Done, the service will be running on port 8080 of your machine, and the RabbitMQ management panel will be available on port 15672.
