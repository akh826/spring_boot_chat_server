# Spring Boot Chat Server

**Build:**

1. Deploy to AWS Elastic Beanstalk (Recommended for Most Users)
   Steps:

Package your app:
Build your Spring Boot app as a JAR file:

```
./mvnw clean package
```

Login to AWS Console and go to Elastic Beanstalk.

Create a new application and choose "Java" as the platform.

Upload your JAR file (found in target/your-app.jar).

Deploy. Elastic Beanstalk will handle the server setup for you.
