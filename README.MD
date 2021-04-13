# Bet Me App
## assignment test code

This app is an interface to bet.me

## Features

- Real time in play updates
- Updated odds for UK market

## Tech

Tech stack used to build the app
The app is build with Java 8. Framework used are:
- [MongoDB] - database to store all available games and odds for games
- [SpringBoot] - REST api to interact with bet.me
- [Docker] - containerizing the app

|application|port|
|----|----|
|spring boot app| 8080|
|mongo db| 27017|

'Feign Client' is used to access the bet.me api - com.bet.me.client
Spring's 'MongoRepository' api is used to access MongoDb database - com.bet.me.dao
Spring 'Schedulers' are used to schedule the batch jobs such as hourly update and real time update - com.bet.me.schedulers
Spring caching is used to cache the real time data - com.bet.me.service.SportsCacheService.java

 

## Design & Architecture

The application exposes multiple end points
 - GET http://localhost:8080/betmeapp/v1/getallsports
        - Gets all available sports from DB
- GET http://localhost:8080/betmeapp/v1/getodds?sport=sport&region=uk&mkt=h2h
        - although it accepts parameters, as of now it returns only UK H2H data
- GET http://localhost:8080/betmeapp/v1/getupcoming
        - gets cached upcoming data from all sports. Realtime updated.
- GET http://localhost:8080/betmeapp/v1/getupcomingsport?sport=xxx
        - gets real time cached data given the sport key

The Spring Boot app is dockerized and wired with MongoDB using 'docker-compose.yml'.
'.env' file is also provided that contains environment data such as :
| env variable | value | description|
| ------ | ------ | ------ |
| apiKey | xxxxxxx | key of the bet.me api |
|connection| mongodb://admin:admin@mongo:27017/betme?authSource=admin |connection string to connect with mongo db|
| realtimedelayms | 300000 | delay to fetch in play games in ms |
| mongo_username | admin | username of mongo db |
| mongo_password | admin | password of mongo db |
| mongo_database | betme | database name |

- The app connects with the bet.me api, gets the list of all games, saves to DB during app start.
- For each games the app writes the odds to the DB.
- All the in-play games are fetched and cached during app start.
- The cache updating is configurable in milliseconds using environment variable  'realtimedelayms'
- All the other games are updated every hour in the database


## Installation and Run

The application requires Docker to be installed in the machine.


```cmd
# From parent directory
cd betmeapp
mvnw clean package -DskipTests
#go back to parent directory once the build is complete
cd ..
docker-compose up
```

## Exceptions
- Only odds for UK H2H are updated and retrieved in the DB
- Unit tests are skipped in the code base because of time limitations
  
   [Docker]: <https://www.docker.com/>
   [MongoDB]: <https://www.mongodb.com>
   [SpringBoot]: <https://spring.io/projects/spring-boot>