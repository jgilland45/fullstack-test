# steps to test locally
# docker build -t backend . && docker run -p 8080:8080 backend

FROM eclipse-temurin:25
RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
USER demo

FROM eclipse-temurin:25 AS builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
 
FROM eclipse-temurin:25
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]