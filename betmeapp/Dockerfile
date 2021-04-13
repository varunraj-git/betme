FROM openjdk:8-jdk-alpine
COPY target/bet-me-app-1.0.0.jar bet-me-app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=${connection}","-Djava.security.egd=file:/dev/./urandom","-jar","/bet-me-app.jar"]