# WordGame

Description
---
This is the lenda word game application API.

Build Info
---
This project is build and compiled using Java 1.7

How to start the WordGame application
---
1. Run `mvn clean install` to build your application
1. Update the config.yml file with the DB configurations, make sure you create the schema in DB.
1. Run migration with `java -jar target/word-game-service-1.0-SNAPSHOT.jar db migrate config.yml`
1. Start application with `java -jar target/word-game-service-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


