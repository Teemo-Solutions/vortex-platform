# Usa la imagen base de OpenJDK para Java 23
FROM openjdk:23-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo de configuración de Maven y el código fuente
COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml .
COPY src ./src

# Ejecuta Maven para compilar el proyecto y generar el JAR
RUN ./mvnw clean package -DskipTests

# Verifica que el archivo JAR se ha generado
RUN ls -l target/

# Copia el archivo JAR generado
COPY target/vortex_platform-0.0.1.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
