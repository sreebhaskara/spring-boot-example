# java:8 is 600M+, alpine < 200M
# FROM java:8

FROM frolvlad/alpine-oraclejdk8:slim

EXPOSE 8080
COPY target/spring-boot-example-1.0-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
