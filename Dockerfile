FROM openjdk:23
ARG JAR_FILE=target/vortex_platform-0.0.1.jar
COPY ${JAR_FILE} app_vortex.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_vortex.jar"]