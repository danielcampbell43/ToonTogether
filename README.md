# ToonTogether

Toon together is 

The application uses:
  - `maven` to build the project
  - `thymeleaf` for templating
  - `flyway` to manage `postgres` db migrations
  - `selenium` for feature testing
  - `faker` to generate fake names for testing
  - `junit4` for unit testing
  - `spring-security` for authentication and user management
  
Below, you'll find specific learning objectives for each tool.

## QuickStart Instructions

- Fork and clone this repository to your machine
- Open the codebase in an IDE like InteliJ or VSCode
- Create a new Postgres database called `toon_together_dev`
- Install Maven `brew install maven`
- Build the app and start the server, using the Maven command `mvn spring-boot:run`
> The database migrations will run automatically at this point
- Visit `http://localhost:8080/users/new` to sign up

## Running the tests

- Install chromedriver using `brew install chromedriver`
- Start the server in a terminal session `mvn spring-boot:run`
- Open a new terminal session and navigate to the Acebook directory
- Run your tests in the second terminal session with `mvn test`

> All the tests should pass. If one or more fail, read the next section.

## Common Setup Issues

### The application is not running

For the feature tests to execute properly, you'll need to have the server running in one terminal session and then use a second terminal session to run the tests.

### Chromedriver is in the wrong place

Selenium uses Chromedriver to interact with the Chrome browser. If you're on a Mac, Chromedriver needs to be in `/usr/local/bin`. You can find out where it is like this `which chromedriver`. If it's in the wrong place, move it using `mv`.

### Chromedriver can't be opened

Your Mac might refuse to open Chromedriver because it's from an unidentified developer. If you see a popup at that point, dismiss it by selecting `Cancel`, then go to `System Preferences`, `Security and Privacy`, `General`. You should see a message telling you that Chromedriver was blocked and, if so, there will be an `Open Anyway` button. Click that and then re-try your tests.


## Design

This app uses the repository pattern. The repository pattern separates the business logic of models from the responsibility of connecting to the database and making queries. Take a look in the `src/main/java/repository` and you'll find `PostRepository` which generates and executes queries to Create, Read, Update and Delete (CRUD) posts. Depending on what you've built in the past, it might or might not feel familiar to you.
