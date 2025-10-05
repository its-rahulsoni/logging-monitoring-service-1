# 1. Use base image
FROM openjdk:17-jdk-slim

# 2. Set working dir
WORKDIR /app

# 3. Copy jar into container
COPY target/logging-monitoring-0.0.1-SNAPSHOT.jar app.jar

# 4. Run app
ENTRYPOINT ["java","-jar","app.jar"]
