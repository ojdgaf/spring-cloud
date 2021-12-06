# spring-cloud
Learning the basics of Spring Cloud Config, Eureka, OpenFeign, Load Balancer

## Content

1. `config-server` (8888) - provides properties required for all services. it [must](https://stackoverflow.com/a/40354254/9232349) be the first service to start 
2. `eureka-server` (8000) - service discovery that the following services are registered with
3. `name-service` (8081/8082) - has the `GET /` endpoint that returns a string. at least 2 instances of the application should be running at the same time in order to test the load balancer
4. `greeting-service` (8080) - utilizes `name-service` using client-side load balancer. circuit breaker logic is implemented via OpenFeign fallbacks
