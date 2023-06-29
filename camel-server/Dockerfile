FROM amazoncorretto:17.0.7-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app.jar"]
