Simple Spring Boot app
======================

<a href="https://travis-ci.org/eis/spring-boot-example" title="Build Status"><img src="https://api.travis-ci.org/eis/spring-boot-example.svg?branch=master"></a>
<a href="https://coveralls.io/github/eis/spring-boot-example?branch=master" title="Coverage Status"><img src="http://img.shields.io/coveralls/eis/spring-boot-example/master.svg"></a>

Started from tutorial at https://spring.io/guides/gs/spring-boot/ but with added
  - Thymeleaf MVC
  - counter operation
  - Swagger api documentation
  - "runme" scripts
  - integration tests
  - resteasy tests
  - robot acceptance test
  - Travis configurations
  - Coveralls integration
  - Dockerfile
  - Vagrantfile

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
