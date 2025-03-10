# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set work directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/Billing-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application with memory optimizations
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
