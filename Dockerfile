# Use a base image with JDK
FROM openjdk:22-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file to the container
COPY target/TextCache-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the app will run
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
