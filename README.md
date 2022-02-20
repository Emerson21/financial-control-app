# financial-control-app

## Features

 - Singup
 - Login and Password
 - Forgot my password
 - Bank Account
   - Credit Card
   - Debit Card
   - Credit/Debit Card
 - Expense and Income entries
 - Wallets
   - Wallet money
   - Credit cards
   - Debit cards
 - Tickets 
  - Vale-Alimentação
  - Vale-Refeição
 

## Goals to build this app

- [X] Use TDD;
- [ ] Applying SOLID principles
- [ ] Not only apply design patterns, but search best designs patterns and your consciously use; For example, when use Builder and why instead to use Factory?
- [ ] Write an article and post this solution at medium or other blogpost website.

## Designs used

- [X] Strategy;
- [ ] Builder;
- [X] Factory;
- [X] S - Single Responsability Principle;
- [X] O - Open / Closed principle;
- [ ] L - Liskov Substitution Principle;
- [ ] I - Interface Segregation Principle;
- [ ] D - Dependency Inversion Principle;

## Tools
- [X] Java 11?
- [ ] EDA - Event Driven Architecture
- [ ] Kafka
- [ ] keycloack
- [ ] Security integrated with keycloack -> Identity Manager
- [ ] Actuator, Prometheus and Grafana -> Monitoring
- [ ] Hateoas -> Restful
- [ ] Config -> configure times to process doc / ted and card invoice time
- [ ] Batch -> batches to process doc / ted and card invoice time
- [ ] Eureka? API Gateway
- [ ] Service Registration and Discovery with Netflix Eureka and Spring cloud
- [ ] Cloud Load Balancer
- [ ] Distributed tracing and Log aggregation patterns and visualization with Elasticsearch, Logstash and Kibana
- [ ] Spring Cloud Sleuth and Zipkin
- [X] Mysql
- [X] Docker
- [ ] Kubernetes (kind - local cluster)
- [ ] Elasticsearch
- [ ] Logstash
- [ ] Kibana
- [ ] Prometheus
- [ ] Grafana


## Installing Keycloak Docker Image

```docker run --name keycloack-authenticator -p 8180:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:15.0.2```
