FROM amazoncorretto:11

WORKDIR /app/financial-app

COPY ./target/financial-control-app-0.0.1-SNAPSHOT.jar ./financial-app.jar

CMD ["java", "-jar", "financial-app.jar"]
