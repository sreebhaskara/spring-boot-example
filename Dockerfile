from java:8

EXPOSE 8080
COPY target/spring-boot-example-1.0-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]