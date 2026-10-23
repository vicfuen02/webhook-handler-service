FROM eclipse-temurin:25-jdk-alpine AS builder

WORKDIR /home/app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
RUN chmod +x mvnw

COPY pom.xml .
COPY ./common-service/pom.xml ./common/
COPY ./consumer-service/pom.xml ./service/

COPY ./common-service/src ./common/src
COPY ./consumer-service/src ./service/src


RUN sed -i 's/\r$//' mvnw
RUN ./mvnw install -N
RUN cd common/ && ../mvnw dependency:go-offline -B \
    && ../mvnw clean package install -DskipTests
RUN cd service/ && ../mvnw dependency:go-offline -B \
    && ../mvnw clean package -DskipTests



FROM eclipse-temurin:25-jre-alpine

WORKDIR /home/app

COPY --from=builder /home/app/service/target/consumer-service-*.jar consumer-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/app/consumer-service.jar"]