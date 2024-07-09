FROM openjdk:22
FROM maven:latest
ARG JARFILE=out/artifacts/attendance_system_jar/*.jar
COPY ./out/artifacts/attendance_system_jar/attendance-system.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8083

