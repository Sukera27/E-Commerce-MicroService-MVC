FROM azul/zulu-openjdk:17

WORKDIR /app
COPY target/products-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]