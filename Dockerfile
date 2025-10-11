# se base image
FROM openjdk:17-jdk-slim

# Set working dir
WORKDIR /app

# Copy jar into container
COPY target/logging-monitoring-0.0.1-SNAPSHOT.jar app.jar

# Create a directory for logs
RUN mkdir -p /app/logs/logging-monitoring-service-1
# Make sure the directory is writable
RUN chmod -R 755 /app/logs/

# Expose port
EXPOSE 8080

# Run app
ENTRYPOINT ["java","-jar","app.jar"]
