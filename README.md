# Challenge Java-Spring boot

Cloud soluction using [Spring boot].

This solution applies the architectural concepts of micro-services, to provide the following features:

- User management (regular and admin users).
- Asynchronous email sending.
- Centralized configuration management.
- User session control independent of the application server (stateless).

---

## Architecture

![Image of architecture](/doc/architecture.jpg)

The project consists of three micro-services:

- User Service
- [Mail Service]
- [Configuration Service]

All projects are based on Spring Boot and have different repositories.

## User service

It is basically an API for user control, with authentication and authorization handling, as well as providing email sending and password + permission change (for admins).

## Mail Service

## Configuration Service

---

## Provisioning

---

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

---

## User API

- Creates a user (login not required)
```json
curl --request POST \
  --url http://localhost:8080/users/create \
  --header 'content-type: application/json' \
  --data '{
	"name": "Jane Doe",
	"login": "janedoe",
	"email": "janedoe@email.com",
	"password": "ultra-secret"
}'
```

- Get all users
```json
curl --url http://localhost:8080/users/
```

- Updates user details by ID:

Example:
> user_id: 19
```json
curl --request PATCH \
  --url http://localhost:8080/users/19 \
  --header 'content-type: application/json' \
  --data '{
		"name": "User 19",
		"login": "user19",
	  "email": "user19@email.com"
}'
```

- Deletes user by ID
Example:
> user_id: 19
```json
curl --request DELETE \
  --url http://localhost:8080/users/19
```

- Find user by ID
Example:
> user_id: 19
```json
curl --url http://localhost:8080/users/19
```

- Performs login
```json
curl --request POST \
  --url http://localhost:8080/login \
  --header 'authorization: Basic amFuZWRvZTp1bHRyYS1zZWNyZXQ='
```

- Performs logout
```json
curl --request POST \
  --url http://localhost:8080/logout \
  --header 'content-type: application/json' \
  --header 'x-auth-token: 178b4005-b72b-49b3-8ad2-28a0353ab74b'
```

- Sends an email

```json
curl --request POST \
  --url http://localhost:8080/users/email \
  --header 'content-type: application/json' \
  --header 'x-auth-token: 2a9dadd7-65b2-4a3c-afaf-05e55174207e' \
  --data '{
	"body": "Admins can see this.",
	"subject": "Hello spring boot.",
	"recipient": "friend_of_mine@email.com"
}'
```

- Change user password (only by admins)
__If the user updates himself, his session will be terminated.__
```json
curl --request PATCH \
  --url http://localhost:8080/admin/password/ \
  --header 'content-type: application/json' \
  --header 'x-auth-token: 178b4005-b72b-49b3-8ad2-28a0353ab74b' \
  --data '{
    "id": "33",
    "password": "new-password"
}'
```

- Change user permission (only by admins)
__If the user updates himself, his session will be terminated.__
```json
curl --request PATCH \
 --url http://localhost:8080/admin/permission/ \
 --header 'content-type: application/json' \
 --header 'x-auth-token: ae0c3d37-0d92-45e7-aa38-d04b12cf4cd7' \
 --data '{
   "userId": 33,
   "admin": true
}'
```

---

## TODO List
- [ ] Improve code coverage
- [ ] Centralized logging
- [ ] Docker for dependencies
- [ ] API documentation with Swagger
- [ ] Database migrations (flyway?)

[Spring boot]: https://projects.spring.io/spring-boot/
[Maven]: https://maven.apache.org/
[Mail Service]: https://bitbucket.org/rgalba/mailapp
[Configuration Service]: https://bitbucket.org/rgalba/java-spring-config-server