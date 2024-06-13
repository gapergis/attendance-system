FROM openjdk:22
FROM maven:latest
ARG JARFILE=target/*.jar
COPY ./target/attendance-system-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8083

