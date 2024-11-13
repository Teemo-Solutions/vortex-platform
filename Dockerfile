FROM eclipse-temurin:23-jdk
WORKDIR /app
COPY target/vortex_platform.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]