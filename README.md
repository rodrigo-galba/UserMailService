# Challenge Java-Spring boot

Project using [Spring boot] to build  applications.

## Usage
To build project using [Maven], just run:

```sh
mvn package
```

To run main application:
```sh
mvn spring-boot:run
```

To test web-api:
```sh
curl http://localhost:8080/
```

expected response:
> Hello World!

To run using a fully `executable-jar`:
```
java -jar target/poc-springboot-0.0.1-SNAPSHOT.jar
```

To gracefully shutdown application:
```
ctrl+c
```

[Spring boot]: https://projects.spring.io/spring-boot/
[Maven]: https://maven.apache.org/