# java:8 containers are 600M+, alpine < 200M
FROM openjdk/17-jdk-slim

EXPOSE 8080
COPY target/spring-boot-example-1.0-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
