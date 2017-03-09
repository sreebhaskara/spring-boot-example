Simple Spring Boot app
======================

[![Build Status](https://api.travis-ci.org/eis/spring-boot-example.svg?branch=master)]
(https://travis-ci.org/eis/spring-boot-example)
[![Coverage Status](http://img.shields.io/coveralls/eis/spring-boot-example/master.svg)]
(https://coveralls.io/github/eis/spring-boot-example?branch=master)

Started from tutorial at https://spring.io/guides/gs/spring-boot/ but with added
  - counter operation
  - "runme" scripts
  - integration test
  - resteasy tests
  - robot acceptance test
  - Travis configurations
  - Swagger documentation

Answers to [http://localhost:8080/](http://localhost:8080/) (GET) and
[http://localhost:8080/add](http://localhost:8080/add) (POST), latter being
JSON interface.

See [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) for usage.

Docker stuff
------------

```bash
# build container from Dockerfile
docker build -t spring-boot-example .

# run container
docker run -ti -p 0.0.0.0:8080:8080 --rm spring-boot-example

# call your container
curl `docker-machine ip default`:8080

# stop your container
docker stop CONTAINERNAME

# debug something
docker ps
```
