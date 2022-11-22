# Microservice Equipes

Ce microservice permet de gérer les équipes et leurs persistance en base de données.

## Installation

### Prérequis

- Une instance mongoDB sur le port 27017
- NodeJS 14+

### Lancement

```bash
npm i
npm run start
```

## API

### Création d'une équipe

```http
POST localost:3000/api/equipes
{
    "nom": "Equipe 1"
}
```

### Récupération d'une équipe

```http
GET localost:3000/api/equipes/{id}
```

Ou `{id}` représente l'identifiant de l'équipe.

### Récupération de toutes les équipes

```http
GET localost:3000/api/equipes
```

### Mise à jour d'une équipe

```http
PUT localost:3000/api/equipes
{
    "nom": "Equipe 1"
}
```

### Suppression d'une équipe

```http
DELETE localost:3000/api/equipes/{id}
```

Ou `{id}` représente l'identifiant de l'équipe.

### Ajout d'une personne à une équipe

```http
POST localost:3000/api/equipes/{id}/personnes
{
    "id": "5f9f1b9b9b9b9b9b9b9b9b9b"
}
```

Ou `{id}` représente l'identifiant de l'équipe.

### Suppression d'une personne d'une équipe

```http
DELETE localost:3000/api/equipes/{id}/personnes/{idPersonne}
```