# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download project dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an OpenJDK runtime image for the application
FROM openjdk:22

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/attendance-system-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8083

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

