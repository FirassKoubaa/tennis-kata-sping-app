# Utilise l'image officielle Eclipse Temurin Java 21
FROM eclipse-temurin:21-jdk

# Répertoire de travail
WORKDIR /app

# Copie du fichier jar généré
COPY target/tennis-kata-0.0.1-SNAPSHOT.jar app.jar

# Expose le port utilisé par Spring Boot
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

