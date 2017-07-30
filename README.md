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
This project is build and compiled using Java 1.7. This project is using Dropwizard (0.8.2) Java framework which has 
an embedded Jersey server. The embedded jersey starts when the `java -jar` command is supplied on the fat JAR. 
The far JAR has all the third party libraries with-in and is self-contained.

How to start the WordGame application
---
1. Get project locally running git command `git clone https://github.com/usmanchaudhri/word-game-service.git` 
1. Browse in the git directory above.
1. Run `mvn clean install` to build your application. This will create a fat jar under the 'target/' folder. 
1. Update the config.yml file with the DB configuration, make sure you create the schema in DB first. Below is a sample
configuration to connect to a postgresql DB.

```
database:
  driverClass: org.postgresql.Driver
  user: username
  password: password
  url: jdbc:postgresql://localhost:5432/{database_schema_name}
```

1. Run migration to create tables in the DB with `java -jar target/word-game-service-1.0-SNAPSHOT.jar db migrate config.yml`
1. Start application with `java -jar target/word-game-service-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

API
---
1. Create a new game      (POST) - http://localhost:8080/api/v1/game
1. Get an existing game   (GET)  - http://localhost:8080/api/v1/game/{game_id}
1. Play an existing word  (POST) - http://localhost:8080/api/v1/game/{game_id}
`{"word": "ros", "score": 3}`

CORS
---
CORS is enabled for the following: 

```
Access-Control-Allow-Origin: *
Access-Control-Allow-Headers: Content-Type
```


