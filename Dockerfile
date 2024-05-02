FROM openjdk:21

COPY target/commerce-0.0.1-SNAPSHOT.jar /app/commerce-api.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/commerce-api.jar"]