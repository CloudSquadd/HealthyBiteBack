FROM openjdk:8-jre-alpine
COPY target/pidev-1.0.jar /app.jar
WORKDIR /app
EXPOSE 8090
CMD ["java", "-jar", "/app.jar"]





