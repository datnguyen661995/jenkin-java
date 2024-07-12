FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} java-strategy.jar

ENV PORT 8082
EXPOSE $PORT

ENTRYPOINT ["java","-jar", "-Dserver.port=${PORT}","/java-strategy.jar"]