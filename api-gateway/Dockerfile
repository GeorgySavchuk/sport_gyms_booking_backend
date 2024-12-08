FROM openjdk:17

WORKDIR /app

COPY /build/libs/api-gateway-0.0.1-SNAPSHOT-plain.jar /app/api-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]