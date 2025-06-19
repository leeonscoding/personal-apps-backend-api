# Step by step details what technologies are used in this project

## Loan tracing app
* postgresql with neon db
* h2 database for dev with details config in properties file
* spring profiles (dev, prod)
* db migration setup with liquibase where there is one master changelog which contains child changelogs. The scripts are configured as sql
* Persisting enums in JPA
* Annotation-based auditing metadata (@CreatedDate)
* lombok for less code
* builder pattern using lombok
* @DataJpaTest
* use record for dto objects
* Use JPA annotation processor
* Jpa sorting, specification
* Global exception handler
* AOP annotations - Aspect, Pointcut, Before, After, AfterReturning, AfterThrowing, Around
* Log4j2 (lombok)