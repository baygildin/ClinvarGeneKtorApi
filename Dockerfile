FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY build/libs/ClinvarGeneKtorApi-0.0.1-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

