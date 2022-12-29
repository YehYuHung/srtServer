FROM openjdk:17
EXPOSE 8080
ADD ./target/report-0.0.1-SNAPSHOT.jar report-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/report-0.0.1-SNAPSHOT.jar"]
