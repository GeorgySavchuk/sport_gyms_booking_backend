FROM openjdk:17

WORKDIR /app

COPY ../common/build/libs/common-0.0.1-SNAPSHOT.jar /app/common.jar

COPY /build/libs/facility-service-0.0.1-SNAPSHOT.jar /app/facility-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-cp", "common.jar:facility-service.jar", "com.example.facility_service.FacilityServiceApplication"]