# Library backend API Documentation

## Get authentified

All endpoints in this project were secured using Spring Security and a JWT-based authorization, where for every endpoint you want to call, you should have a certain cookie set in the request header, that shows that you were successfully authenticated.

In case of authentication failure, that gets checked for every request using a custom ___OncePerRequestFilter___, a Status Code of ```403 FORBIDDEN``` and a ```An authentication error has occurred, please refer to the Support Team``` message will be returned.


However, as it was out of this project scope, i did ___not___ create a role based authorization that restricts users from accessing certain endpoints according to their roles,

and i did ___not___ create an endpoint that creates a user, but you have to manually add the user into the database, with the password being encrypted using ___BCryptPasswordEncoder___.

Kindly access the ```Login``` API interaction within this [login.md](https://github.com/Code-png/library-backend-system/blob/master/login.md).

## Book API Interaction

For every endpoint developed for the books API, please find the documentation within this [book.md](https://github.com/Code-png/library-backend-system/blob/master/book.md)

## Patron API Interaction

For every endpoint developed for the patrons API, please find the documentation within this [patron.md](https://github.com/Code-png/library-backend-system/blob/master/patron.md)

## Borrowing API Interaction

For every endpoint developed for the borrowing API, please find the documentation within this [borrowing.md](https://github.com/Code-png/library-backend-system/blob/master/borrowing.md)

## Caching Mechanism

A caching mechanism using ```Spring Boot Starter Cache``` has been implemented in this project, and used for 2 endpoints:
- ```PUT``` ```/api/books/{id}``` which retrieves a book's information by its id
- ```PUT``` ```/api/patrons/{id}``` which retrieves a patron's information by the id

The caching has been implemented using a ___ConcurrentMapCacheManager___ which I find good for development. However we could migrate to a more reliable mechanism (i.e: ___Redis___) for production environments.

## Aspect Orientation

For Aspect Logging, ```Spring Boot Starter AOP``` has been used for:
- Logging the entry and exit of every method present in the package.
- Logging the metrics of the following endpoints, using ___Actuator metrics___:

  
  - ```POST``` ```/api/books```
  - ```PUT``` ```/api/books/{id}```
  - ```POST``` ```/api/patrons```
  - ```PUT``` ```/api/patrons/{id}```
 
    
- Logging all exceptions thrown in the package.


## Logging Mechanism

To save all triggered logs in a file, ___Log4j2___ has been added to the project, configured in ```log4j2.xml```, that writes all the logs in the ```logs/app.log``` file.

## Database and Persistence

For database interactions, ___Spring data JPA___ and ___Hibernate___ were used for backend, and ___PostgreSQL___ for database management.

## Testing
For testing, ```Spring Boot Starter Test``` has been used, and the tests can be found in ```src/test/java```.

## Global Exception Handling

To avoid redundancy, a global exception handler ```GlobalExceptionHandler.java``` has been added to catch all expected exceptions, and return the appropriate response body and status to client side.


