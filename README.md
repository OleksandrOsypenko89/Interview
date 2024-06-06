# My project - INTERVIEW SIMULATOR

The project was created to improve interviewing skills. 
At the moment, it contains 432 questions. There are also tests with sample codes and answer options for the result of the code. 
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
You need to download it. </br>
Then create a docker container using the command 

> docker run --name Interview_simulator -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres

Now you can run the project in your code editor and open the http://localhost:8080 tab in your browser

<p>To log in without registration, you can use one of the following accounts:

| Role     | ADMIN                 | USER                |
|----------|-----------------------|---------------------|
| Login    | Johnny_Depp@gmail.com | demo_test@gmail.com |
| Password | password              | password            |


You can also register yourself. In order for your account to be saved on a permanent basis,
you need to add your account details to the `data.sql` file or remove the `spring.sql.init.mode=always` 
parameter in the `application.properties`file so that the data in your docker container is not overwritten 
after each program restart.
