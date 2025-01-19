# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven POM and the source code to the container
COPY pom.xml .
COPY src ./src

# Build the application with Maven (package it as a jar)
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Use an OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory for the running container
WORKDIR /app

# Copy the jar file from the build stage into the container
COPY --from=build /app/target/EmployeeRecordsManagementSystem-0.0.1-SNAPSHOT.jar /app/EmployeeRecordsManagementSystem.jar

# Expose the port the application will run on (default Spring Boot port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "EmployeeRecordsManagementSystem.jar"]
