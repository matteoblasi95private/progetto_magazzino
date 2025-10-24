FROM eclipse-temurin:21-jdk

COPY target/ClientiService-0.0.1-SNAPSHOT.jar ClientiService.jar

EXPOSE 8080
EXPOSE 5005

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "ClientiService.jar"]