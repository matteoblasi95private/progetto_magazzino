FROM eclipse-temurin:21-jdk

COPY target/ServiceDiscovery-0.0.1-SNAPSHOT.jar ServiceDiscovery.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "ServiceDiscovery.jar"]