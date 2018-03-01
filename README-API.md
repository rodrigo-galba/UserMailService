# User API

- Creates a user (login not required)
```sh
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
```sh
curl --url http://localhost:8080/users/ \
  --header 'x-auth-token: 6626745c-e482-4d2f-8551-7aa8483e3961'
```

- Update user details by ID:

Example:
> user_id: 19
```sh
curl --request PATCH \
  --url http://localhost:8080/users/19 \
  --header 'content-type: application/json' \
  --header 'x-auth-token: 6626745c-e482-4d2f-8551-7aa8483e3961' \
  --data '{
		"name": "User 19",
		"login": "user19",
	  "email": "user19@email.com"
}'
```

- Delete user by ID
Example:
> user_id: 19
```sh
curl --request DELETE \
  --url http://localhost:8080/users/19 \
  --header 'x-auth-token: 6626745c-e482-4d2f-8551-7aa8483e3961'
```

- Find user by ID
Example:
> user_id: 19
```sh
curl --url http://localhost:8080/users/19 \
  --header 'x-auth-token: 6626745c-e482-4d2f-8551-7aa8483e3961'
```

- Perform login
```sh
curl --request POST \
  --url http://localhost:8080/login \
  --user johndoe:s3cr3t
```

- Perform logout
```sh
curl --request POST \
  --url http://localhost:8080/logout \
  --header 'content-type: application/json' \
  --header 'x-auth-token: 178b4005-b72b-49b3-8ad2-28a0353ab74b'
```

- Send email

```sh
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
```sh
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
```sh
curl --request PATCH \
 --url http://localhost:8080/admin/permission/ \
 --header 'content-type: application/json' \
 --header 'x-auth-token: ae0c3d37-0d92-45e7-aa38-d04b12cf4cd7' \
 --data '{
   "userId": 33,
   "admin": true
}'
```
