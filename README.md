Simple Spring Boot app
======================

<a href="https://github.com/eis/spring-boot-example/actions/workflows/github-actions-build.yml" title="Build Status"><img src="https://github.com/eis/spring-boot-example/actions/workflows/github-actions-build.yml/badge.svg"></a>
<a href="https://codecov.io/gh/eis/spring-boot-example" title="Coverage Status"><img src="https://codecov.io/gh/eis/spring-boot-example/branch/master/graph/badge.svg?token=6PUI9CYUZR"></a>

Started from tutorial at https://spring.io/guides/gs/spring-boot/ but with added
  - Thymeleaf MVC
  - counter operation
  - Swagger api documentation
  - "runme" scripts
  - integration tests
  - resteasy tests
  - robot acceptance test
  - GitHub Actions build
  - AWS CodeBuild build
  - CodeCov integration
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
