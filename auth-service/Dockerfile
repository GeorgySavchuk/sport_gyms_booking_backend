FROM openjdk:17

WORKDIR /app

COPY /build/libs/auth-service-0.0.1-SNAPSHOT-plain.jar /app/auth-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]