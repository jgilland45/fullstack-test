# steps to test locally
# docker build -t backend . && docker run -p 8080:8080 backend

# Build stage - uses JDK 25
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Copy maven wrapper and pom for dependency download/caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Pre-download dependencies into the layer cache
RUN ./mvnw dependency:go-offline

# Copy source and build the app
COPY src ./src
RUN ./mvnw -DskipTests clean package

# Runtime stage - uses JRE 25
FROM eclipse-temurin:25-jre

WORKDIR /app

# Create a non-root user
RUN addgroup --system demo && adduser --system --ingroup demo demo
USER demo

EXPOSE 8080

# Copy the built jar from the builder stage and give it a fixed name
COPY --from=builder /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
