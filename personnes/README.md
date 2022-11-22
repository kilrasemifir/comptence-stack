# Microservice des personnes

Ce microservice permet de gérer les personnes et leurs persistance en base de données.

## Installation

### Prérequis

- Une instance mongoDB sur le port 27017
- Besoin du service de gestion des équipes

### Lancement

Lancer la commande suivante :

```bash
gradlew run
```

ou depuis un IDE, lancer la classe `PersonnesApplication`

## API

### Création d'une personne

```http
POST localost:8080/api/personnes
{
    "nom": "Dupont",
    "prenom": "Jean"
}
```

### Récupération d'une personne

```http
GET localost:8080/api/personnes/{id}
```

Ou `{id}` représente l'identifiant de la personne.

### Récupération de toutes les personnes

```http
GET localost:8080/api/personnes
```

### Mise à jour d'une personne

```http
PUT localost:8080/api/personnes
{
    "nom": "Dupont",
    "prenom": "Jean"
}
```

### Suppression d'une personne

```http
DELETE localost:8080/api/personnes/{id}
```
