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

To perform tests:
```
mvn test
```

## Web API

- Get all users
```sh
curl --url http://localhost:8080/users/
```

- Create a user
```sh
curl --request POST \
  --url http://localhost:8080/users/ \
  --header 'content-type: application/json' \
  --data '{
		"name": "User",
		"login": "user",
		"email": "user@email.com",
		"password": "password"
}'
```

- Patch user details by ID:

Example:
> user_id: 19
```
curl --request PATCH \
  --url http://localhost:8080/users/19 \
  --header 'content-type: application/json' \
  --data '{
		"name": "User 19",
		"login": "user19",
	  "email": "user19@email.com"
}'
```

- Delete user with by ID
Example:
> user_id: 19
```sh
curl --request DELETE \
  --url http://localhost:8080/users/19
```

- Find user by ID
Example:
> user_id: 19
```sh
curl --url http://localhost:8080/users/19
```



[Spring boot]: https://projects.spring.io/spring-boot/
[Maven]: https://maven.apache.org/