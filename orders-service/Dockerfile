FROM azul/zulu-openjdk:17

WORKDIR /app
COPY target/orders-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]