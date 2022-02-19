FROM maven:3.6.3-jdk-11 as build
WORKDIR /workspace/app

COPY pom.xml .
COPY src src

RUN mvn install -DskipTests

FROM openjdk:11-slim
WORKDIR /app
ARG DEPENDENCY=/workspace/app/target
COPY --from=build ${DEPENDENCY}/sspc-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]