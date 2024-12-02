FROM openjdk:21-jdk-slim

WORKDIR /app

ARG JAR_FILE=target/demo_dacs343w-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/demo_dacs343w.jar

ENTRYPOINT ["java", "-jar", "/app/demo_dacs343w.jar"]


