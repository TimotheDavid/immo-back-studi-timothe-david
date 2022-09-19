FROM maven:3-openjdk-18-slim as builder


ENV DATABASE_CONNECTION=database_connection \
    DATABASE_NAME=database_name \
    DATABASE_PASSWORD=database_password \
    SECRET_KEY=secret_key  \
    ENVIRON=environ

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package  -Dspring.profiles.active=$ENVIRON -Dmaven.test.skip

FROM maven:3-openjdk-18-slim

COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8080

CMD  ["java", "-jar", "/app.jar", "--spring.profiles.active=${ENVIRON}"]
