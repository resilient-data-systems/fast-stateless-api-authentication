Fast and stateless API authentication with Spring Security
===================================


This project has been created as part of a tutorial: [Fast and stateless API authentication with Spring Security]

It is a simple Java web application which demonstrates how to configure Spring Security for HTTP Basic Authentication with UserCache for fast and easy to implement API authentication. Take a look at SecurityConfig.java and WebConfig.java

**About the app:**
- Spring Security with HTTP Basic Authentication; H2 as in-memory DB for storing user credentials
- Once the app is started you can access the DB via http://localhost:8082/ and specify JDBC URL 
- A sample endpoint http://localhost:9090/secure/aa where aa is any string
- Application populated with sample data on startup - 'users' and 'authorities' - see src/main/resources
- Configured to run on port 9090
- EHCache as a UserDetail cache configured via ehcache.xml

**Running the application:**
- either import the app into your favourite IDE (i.e. Eclipse). Once you do that, start the application by running Jetty.java from src/test/java under package uk.co.resilientdatasystems.faststatelesapiauth.web.jetty
- or run mvn jetty:run from command line which will start the app on port 9090


**Calling endpoint:**
- The endpoint respond to GET requests
- Call via curl, web browser, Postman (Chrome plugin) or via provided scripts (see below)
- Requires Authorization header to be set, i.e. Authorization: Basic dXNlcjpwYXNzd29yZA== corresponds to user:password


**Availavle scripts:**
- In *scripts* directory there are several scripts you may find useful for testing the application
             - requestNoAuth.sh - the script calls endpoint with no Authorization header, results in HTTP 401
             - requestWithBasicAuth.sh - the script calls endpoint with Authorization header set to user:password
             - requestWithBasicAuth2.sh - the script calls endpoint with Authorization header set to user:password2; encoded value of password2 is 49f89052eca6f49f31534c5abc21dcf14574459a857b9ca9c714b0e98c9f8b7ecd22c340dc7fb3ec

**Sample usage:**
```sh
git clone git@github.com:resilient-data-systems/fast-stateless-api-authentication.git
cd fast-stateless-api-authentication
mvn clean install jetty:run
#in another terminal window...
cd fast-stateless-api-authentication/scripts
./requestNoAuth.sh
./requestWithBasicAuth.sh
check terminal in which you run Jetty to confirm that DB has been queried for UserDetails
run ./requestWithBasicAuth.sh several times (again)
check terminal in which you run Jetty to confirm UserDetails has been taken from Cache
```



[Fast and stateless API authentication with Spring Security]:http://www.resilientdatasystems.co.uk/spring/fast-stateless-api-authentication-spring-security/