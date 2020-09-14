FROM gradle:6.6.1-jdk8 AS build

COPY build.gradle /build/
COPY src /build/src/
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk8

WORKDIR /app

COPY --from=build /build/build/libs/*.jar /app/GrowthCalculator.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/GrowthCalculator.jar"]