FROM amd64/openjdk:18.0.2.1-jdk



ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar


ENV DATABASE_CONNECTION=database_connection \
    DATABASE_NAME=database_name \
    DATABASE_PASSWORD=database_password \
    SECRET_KEY=secret_key  \
    ENVIRON=environ


RUN echo $DATABASE_CONNECTION
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=${ENVIRON}"]
