FROM maven:3.8.4-openjdk-11
WORKDIR /station-entry-api
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run