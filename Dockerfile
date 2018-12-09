FROM openjdk:8-jdk-alpine
COPY ./target/comexport-0.0.1-SNAPSHOT.jar /usr/src/comexport/
WORKDIR /usr/src/comexport/
EXPOSE 8080
CMD ["java", "-jar", "comexport-0.0.1-SNAPSHOT.jar"]