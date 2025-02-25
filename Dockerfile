FROM gradle:jdk21 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./

COPY src ./src

RUN gradle clean build

FROM eclipse-temurin:21-alpine

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar

COPY --from=build /app/${JAR_FILE} app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

