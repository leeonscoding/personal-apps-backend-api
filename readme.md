# Step by step details what technologies are used in this project

## Loan tracing app
* postgresql with neon db
* h2 datbase for dev with details config in properties file
* spring profies (dev, prod)
* db migration setup with liquibase where there is one master changelog which contains child changelogs. The scripts are configured as sql
* Persisting enums in JPA
* Annotation-based auditing metadata (@CreatedDate)
* lombook for less code
* builder pattern using lombook
* @DataJpaTest
* use record for dto objects
* Use JPA annotation processor
* Jpa sorting, specification
* Global exception handler