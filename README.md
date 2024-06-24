# King-ICT-Intership-Task-2024-NET

- **How to configure the db**

Open the terminal in the solution root directory, and type the following command:
`sudo docker compose -f product_db.yml up --build`
This will run the docker compose file, and it will create the db.
The docker compose file is located in the properties directory of the project.
Start the project and the Spring JPA will automatically create all the necessary tables for the db.

- **Authentication and authorization**

When the project is started, two test users will be created(user and admin).
They can be used for testing the projects endpoints. The endpoint for authentication is `http://localhost:8080/api/auth/login`
In order to log in as user:
```
email: test_user@test.com
password: test 
```
In order to log in as admin:
```
email: test_admin@test.com
password: test 
```
- **Testing the application**

In order to test the applications functionalities, run the ProductTests located in the test directory.