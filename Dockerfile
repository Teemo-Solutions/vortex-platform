FROM eclipse-temurin:23-jdk

WORKDIR /app

# Copia el archivo JAR correcto
COPY target/vortex_platform-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
