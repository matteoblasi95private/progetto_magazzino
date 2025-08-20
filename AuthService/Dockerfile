FROM eclipse-temurin:21-jdk

COPY target/AuthService-0.0.1-SNAPSHOT.jar AuthService.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "AuthService.jar"]