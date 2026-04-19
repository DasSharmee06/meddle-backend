# Stage 1: Build the app using Official Cloud Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# We use 'mvn' directly instead of your local './mvnw' script
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copy the built file from Stage 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]