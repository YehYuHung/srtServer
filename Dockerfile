FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} report-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/report-0.0.1-SNAPSHOT.jar"]
