FROM openjdk:17
EXPOSE 8087
ADD target/srtserver.jar srtserver.jar
ENTRYPOINT ["java", "-jar", "/srtserver.jar"]
