# Usa la imagen base de OpenJDK para Java 23
FROM openjdk:23-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración de Maven y el código fuente
COPY pom.xml .
COPY src ./src

# Compila el proyecto y genera el archivo JAR
RUN ./mvnw clean package -DskipTests

# Copia el archivo JAR generado
COPY target/vortex_platform-0.0.1.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
