# Usa la imagen base de OpenJDK para Java 23
FROM openjdk:23-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Otorga permisos de ejecución al archivo mvnw
RUN chmod +x mvnw


# Copia Maven Wrapper y archivos de configuración
COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml .

# Otorga permisos de ejecución al archivo mvnw
RUN chmod +x mvnw


# Instala dependencias y construye el proyecto
RUN ./mvnw clean package -DskipTests

# Verifica si el archivo JAR está presente
RUN ls -l target/

# Copia el archivo JAR generado al contenedor
COPY target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
