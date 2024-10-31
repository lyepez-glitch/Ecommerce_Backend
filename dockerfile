# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app
#
# Copy the JAR file into the container at /app
COPY target/Employee-Management-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (default for Spring Boot)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
