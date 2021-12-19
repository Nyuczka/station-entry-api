FROM openjdk:11
ADD target/station-entry-api.jar station-entry-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "station-entry-api.jar"]
