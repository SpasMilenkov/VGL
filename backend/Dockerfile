#FROM  maven:3.8.4-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
##FROM debian:11-slim
#EXPOSE 8080
#COPY --from=build target/vgl-1.0.0.jar vgl-1.0.0.jar
#ENTRYPOINT ["java", "-jar", "./vgl-1.0.0.jar"]

# Start with a base image that includes Maven and JDK 17.
FROM maven:3.8.4-openjdk-17 as builder

# Set the working directory inside the container.
WORKDIR /backend

# Copy the Maven executable and the project's pom.xml file.
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code of your application.
COPY src src

# Build the application by compiling the source code and packaging it into a JAR file.
# The '-DskipTests' option is used to skip running tests.
RUN mvn clean package -DskipTests

# Start a new stage with a clean JDK 17 image for running the application.
FROM openjdk:17-slim

# Copy the JAR file from the build stage to the production image.
COPY --from=builder /backend/target/*.jar app.jar

# Define the command to run your application.
ENTRYPOINT ["java","-jar","/app.jar"]