Powerset - workout tracker

- RESTful interface for users to track workouts (port 8080):
  - create user with information such as Name, Weight, Age, Email, Username, and Password
     - user sessions are defined by creating a JWT token for auth purposes and tokens expire after 1 hour
     - users are subclasses of UserDetails interface from Spring Security framework
  - user login process consists of:
    - user entering username and password
    - auth filter retrieves userDetails and compares JWT tokens to determing if user is authorized (valid JWT and credentials)
  - for each user:
    - add, find, delete, and update workout sets
    - can find specific groups of sets based on date entered, excersize type, or user
    - these CRUD functionalities are performed through Spring Boot framework with PostgreSQL DB run on docker instance

- simple vuejs frontend for visualization (port 8081)
  - text fields and separate pages for entering and viewing results
  - styled with bootstrap
