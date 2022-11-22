# Stack des compétences en microservices

## Introduction

Ce projet est un exemple de stack de microservices. Il est composé de 3 microservices :

- [equipes](./equipes/README.md) : gère les équipes
- [personnes](./personnes/README.md) : gère les personnes
- `competences` : gère les compétences [WIP]

## Installation

### Prérequis

- [Docker](https://www.docker.com/)

Si vous n'avez pas docker, vous pouvez installer les prérequis suivants :
- [NodeJS](https://nodejs.org/en/) 14+
- [MongoDB](https://www.mongodb.com/) 4.4+
- [Java](https://www.java.com/fr/) 11+

### Lancement

Lancer la commande suivante :

```bash
docker-compose up -d
```

Pour lancer le microserivce `personnes`, il faut lancer la commande suivante :

```bash
cd personnes
gradlew run
```

Pour lancer le microserivce `equipes`, il faut lancer la commande suivante :

```bash
cd equipes
npm i
npm run start
```

## Utilisation

Les services:
- `equipes` est disponible sur le port `3000` avec l'uri `http://localhost:3000/api/equipes`
- `personnes` est disponible sur le port `8080` avec l'uri `http://localhost:8080/api/personnes`