FROM eclipse-temurin:21-jdk

COPY target/apigateway-0.0.1-SNAPSHOT.jar apigateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apigateway.jar"]