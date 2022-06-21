# Read Me First
The following was discovered as part of building this project:

* The original package name 'br.com.vr.development.emerson.chapter.financial-control-app' is invalid and this project uses 'br.com.vr.development.emerson.chapter.financialcontrolapp' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#using-boot-devtools)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-spring-hateoas)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)


### Commands

###### TODO - criar um docker compose com todos os containers 

    docker start es7 local-mongo sad_swirles rabbitmq local-mysql
    docker stop es7 local-mongo sad_swirles rabbitmq local-mysql

#### Installing keycloak container 
    docker run --name keycloack-authenticator -p 8180:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:15.0.2
