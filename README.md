# Fifa 19 API

Just an API to expose some data taken from Fifa 19 dataset from Kaggle.

## TODO
[] Try to remove Spring boot.
[] Try Oracle DB
[] Add Spring security (JWT?)
[] Add user model.

## Tech stack

- Spring Boot
- Hibernate 5.x
- Jpa 2.1
- MySQL 8
- Kotlin
- Swagger 2

Integration tests are run with the H2 in-memory DB.
Liquibase migrations are run by the database module `pom.xml`.

## How to run
- Create a database with the `docker-compose.yml` file in /database module folder.
- You can customize inside the yml file exposed port, username, password etc.
- You can use the ansible `developer_environment.yml` to create a virtual machine with Tomcat 9.

Author
---
Matteo Muscella

