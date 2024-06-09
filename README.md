# INTERVIEW SIMULATOR

The project was created to improve interview skills. 
At the moment, it contains 432 questions. There are also tests with sample codes and answer options for the result of code execution. 
The project envisages that the user enters the section with questions for the interview and will be presented with 15 questions in turn, 
to which he must verbally give answers and click on the “know”, “don't know” button. 
You can also see the correct answer if you are in doubt about your answer. 
All questions to which the user does not know the answer will be recorded in the list to be studied and displayed on the main page.

### Stack of technologies used in the project

* Java 17
* Spring Boot
* Spring Data
* Spring MVC
* Spring Security
* PostgreSQL
* Thymeleaf
* Rest API
* Docker
* Maven
* HTML
* CSS
* JS
* Integration Testing
* Unit Testing

### Tools used in development

* IntelliJ IDEA
* Postman
* Docker
* GitHub
* Git

### Run the project

- Clone the project to your computer.
- Start the database server locally (PostgreSQL). I recommend doing this through docker.

Related command:

```
  docker run --name InterviewSimulator -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres 
```

Connecting to DB
```
  url: jdbc:postgresql://localhost:5432/postgres
  username: postgres
  password: postgres
```
- Start the Spring Boot application `InterviewSimulatorApplication`



### To log in without registration, you can use one of the following accounts:

| Role     | ADMIN                 | USER                |
|----------|-----------------------|---------------------|
| Login    | Johnny_Depp@gmail.com | demo_test@gmail.com |
| Password | password              | password            |

### You can also register yourself. 

To save your account permanently:
- You need to change the `spring.sql.init.mode` parameter in the `application.properties` file to `never`, so that the data in your docker container is not overwritten after each program startup. 
- You can also manually add account data to the `data.sql` file. Even after overwriting the data in the docker container, the data will not be lost.