FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/demo_dacs343w-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/app/app.jar"]
