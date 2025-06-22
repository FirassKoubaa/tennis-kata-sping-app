# Tennis Kata – Spring Boot

## Résumé du Kata

Ce kata consiste à implémenter un calculateur de score de tennis pour un seul jeu. Chaque joueur commence à 0, puis marque 15, 30, 40 selon les points gagnés. À 40-40, on passe en "deuce". Le gagnant du point suivant prend l'avantage, et s'il gagne encore, il remporte le jeu. Sinon, retour à deuce. Le score s'affiche après chaque point et le gagnant est annoncé à la fin. Plus de détails sur les règles : [Wikipedia - Tennis Scoring](http://en.wikipedia.org/wiki/Tennis#Scoring).

Exemple d'entrée : `ABABAA`

Sortie attendue :
```
Player A : 15 / Player B : 0
Player A : 15 / Player B : 15
Player A : 30 / Player B : 15
Player A : 30 / Player B : 30
Player A : 40 / Player B : 30
Player A wins the game
```

---

## Installation et Lancement

### Prérequis
- Java 21
- Maven 3.8+

### Cloner le dépôt
```bash
git clone <votre-url-git>
cd tennis-kata
```

### Compilation et lancement de l'application
```bash
mvn clean install
mvn spring-boot:run
```

L'application démarre sur [http://localhost:8080](http://localhost:8080)

---

## Accès à la documentation Swagger

Après démarrage, ouvrez votre navigateur à l'adresse :

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Vous pouvez y tester l'API REST `/api/tennis/score/{sequence}` (ex : `/api/tennis/score/ABABAA`).

---

## Lancer les tests unitaires

```bash
mvn test
```

Les tests couvrent la logique métier et l'API REST.

---

## Utilisation rapide

- Pour tester en ligne de commande :
  - Modifier la méthode `main` de `TennisGame.java` pour changer la séquence.
- Pour tester via l'API :
  - Utiliser Swagger ou un outil comme Postman/curl sur l'endpoint `/api/tennis/score/{sequence}`.

---

## Auteur

Kata adapté et implémenté en Java Spring Boot.

