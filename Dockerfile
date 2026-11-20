# CI дээр Maven-ээр үүсгэсэн target/book-api.jar-ийг ажиллуулах имиж
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/book-api.jar app.jar
RUN useradd --system --uid 10001 appuser
USER 10001
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
