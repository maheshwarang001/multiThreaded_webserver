FROM openjdk:17
LABEL maintainer = "swipe.io"
ARG JAR_FILE=target/*.jar
COPY ./target/authentication-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

