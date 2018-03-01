# Challenge Java-Spring boot

Cloud solution using [Spring boot].

This solution applies the architectural concepts of micro services, to provide the following features:

- User management (regular and admin users).
- Asynchronous email sending.
- Centralized configuration management.
- User session control independent of the application server (stateless).

---

## Architecture

![Image of architecture](/doc/architecture.jpg)

The project consists of three micro services:

- User Service (this repository)
- [Mail Service]
- [Configuration Service]

All projects are based on Spring Boot. Since projects have distinct lifecycles, they must have different repositories. But for the sake of simplicity, the other projects were added as modules to the main repository.

They are available in the `services` directory.

#### User service

It is basically an API for user control, with authentication and authorization handling, as well as providing email sending and password + permission change (for admins).

For more details and documentation, go to [USER API GUIDE].

#### Mail Service

It is a micro service that consumes template messages and transforms into e-mail messages.

#### Configuration Service

It is a micro service that manages configurations of several clients, based on a main GIT repository. In addition it performs the control by environment profile, as well as access control.

---

## Provisioning

Infrastructure provisioning has been implemented with Ansible and Vagrant in instances based on VirtualBox.

Install both:

- [Vagrant] 2+
- [VirtualBox] 5+

To perform provisioning, access the project directory and run:
```sh
cd PROJECT_ROOT
vagrant up
```

With this, all the services necessary for the operation of the application will be installed and configured.

---

## Building projects for the first time

Access the virtual machine:
```
vagrant ssh
```

All projects are built with [Maven]. So, just run the `package` command in each directory respectively:

- configuration server
```sh
cd ~/project/services/config-server
mvn package
java -jar target/configuration-service-0.0.1-SNAPSHOT.jar &
```

- mail-service
```sh
cd ~/project/services/mail-service
mvn package
java -jar target/mailapp-0.0.1-SNAPSHOT.jar &
```

- userapp
```sh
cd ~/project/
mvn package -Dmaven.test.skip=true
java -jar target/userapp-0.0.1-SNAPSHOT.jar &
```

## Usage

After provisioning and constructing the projects, they should already be available for use.

### Scenario: Sending an email
#### Step 1 - Create regular user
To use the application, we need a valid user. To create one:

```sh
curl -i --request POST \
  --url http://localhost:8080/users/create \
  --header 'content-type: application/json' \
  --data '{
	"name": "John Doe",
	"login": "johndoe",
	"email": "johndoe@email.com",
	"password": "s3cr3t"
}'
```

#### Step 2 - Perform login
You will then need to log in with the credentials informed:
```sh
curl -i --request POST \
  --url http://localhost:8080/login \
  --user johndoe:s3cr3t
```
response information:

> X-Content-Type-Options: nosniff
X-Application-Context: challenge-java-spring:development
`x-auth-token: 6626745c-e482-4d2f-8551-7aa8483e3961`
Content-Length: 0
Date: Thu, 01 Mar 2018 03:21:28 GMT

The token `x-auth-token` will be used to identify the user in the following requests.

#### Step 3 - Send and email

To send an email, simply send the information according to the request template.

```sh
curl -i --request POST \
  --url http://localhost:8080/users/email \
  --header 'content-type: application/json' \
  --header 'x-auth-token: 6626745c-e482-4d2f-8551-7aa8483e3961' \
  --data '{
	"body": "Admins can see this.",
	"subject": "Hello spring boot.",
	"recipient": "friend_of_mine@email.com"
}'
```

To check the email box, access http://localhost:1080/:

---

## TODO List
- [ ] Automate build projects during provisioning
- [ ] Improve code coverage
- [ ] Centralized logging
- [ ] Docker for dependencies
- [ ] API documentation with Swagger
- [ ] Database migrations (flyway?)

[Spring boot]: https://projects.spring.io/spring-boot/
[Maven]: https://maven.apache.org/
[Mail Service]: https://bitbucket.org/rgalba/mailapp
[Configuration Service]: https://bitbucket.org/rgalba/java-spring-config-server
[USER API GUIDE]: README-API.md
[Vagrant]: https://www.vagrantup.com/
[VirtualBox]: https://www.virtualbox.org/