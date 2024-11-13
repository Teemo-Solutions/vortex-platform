# Usa la imagen base de Eclipse Temurin para Java 23
FROM eclipse-temurin:23-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración de Maven y el código fuente
COPY pom.xml .
COPY src ./src

# Ejecuta Maven para compilar el proyecto y generar el archivo JAR
RUN ./mvnw clean package -DskipTests

# Copia el archivo JAR generado al contenedor
COPY target/vortex_platform-0.0.1.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
