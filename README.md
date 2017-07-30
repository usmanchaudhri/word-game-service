# WordGame

Description
---
Service API to power lenda word game application.

Assumption
---
The following assumptions were taken into consideration.
1. dictionary.text provided has unique words.
1. multiple word detection

Build Info
---
This project is build and compiled using Java 1.7

How to start the WordGame application
---
1. Run `mvn clean install` to build your application. This will create a fat jar under the 'target/' folder. 
1. Update the config.yml file with the DB configurations, make sure you create the schema in DB first.
1. Run migration with `java -jar target/word-game-service-1.0-SNAPSHOT.jar db migrate config.yml`
1. Start application with `java -jar target/word-game-service-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

API:
---
1. Create a new game      (POST) - http://localhost:8080/api/v1/game
1. Get an existing game   (GET)  - http://localhost:8080/api/v1/game/{game_id}
1. Play an existing word  (POST) - http://localhost:8080/api/v1/game/{game_id}
`{"word": "ros", "score": 3}`

Health Check
---
To see your applications health enter url `http://localhost:8081/healthcheck`


