FROM openjdk:17

WORKDIR /app

COPY ../common/build/libs/common-0.0.1-SNAPSHOT.jar /app/common.jar

COPY /build/libs/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar

EXPOSE 8084

ENTRYPOINT ["java", "-cp", "common.jar:user-service.jar", "com.example.user_service.UserServiceApplication"]