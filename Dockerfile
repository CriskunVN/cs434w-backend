FROM openjdk:17-jdk-slim
WORKDIR /app

COPY target/demo_dacs343w-0.0.1-SNAPSHOT.jar-SNAPSHOT.jar /app/demo_dacs343w.jar
CMD ["java", "-jar", "/app/demo_dacs343w.jar"]
