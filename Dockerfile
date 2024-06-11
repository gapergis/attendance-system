FROM openjdk:22
ARG JARFILE=target/*.jar
COPY ./target/attendance-system-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8083