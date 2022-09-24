FROM maven:3-openjdk-18-slim as builder


ARG ENVIRON

ENV DATABASE_CONNECTION=$DATABASE_CONNECTION \
    DATABASE_NAME=$DATABASE_NAME \
    DATABASE_PASSWORD=$DATABASE_PASSWORD \
    SECRET_KEY=$SECRET_KEY  \
    ENVIRON=$ENVIRON \
    DATABASE_USER=$DATABASE_USER \
    DATABASE_URL=$DATABASE_URL

WORKDIR /app
COPY pom.xml .
COPY src ./src


RUN mvn package  -Dspring.profiles.active=$ENVIRON -Dmaven.test.skip

FROM maven:3-openjdk-18-slim

COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8080


CMD  ["java", "-jar", "/app.jar", "--spring.profiles.active=$ENVIRON"]
