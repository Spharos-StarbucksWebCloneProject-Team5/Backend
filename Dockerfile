FROM adoptopenjdk/openjdk11 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM adoptopenjdk/openjdk11
COPY --from=builder build/libs/*.jar app.jar

ARG ENVINRONMENT
ENV SPRING_PORFILES_ACTIVE=dev

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
VOLUME /tmp