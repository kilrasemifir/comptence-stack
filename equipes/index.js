/**
 * Microserivce de gestion des équipes
 * 
 * Ce microservice permet de gérer les équipes leurs persistance dans une base de données MongoDB.
 * 
 * C'est un code d'exemple minimaliste utilisant Express et MongoDB.
 * 
 * - Express est une librairie Node.js permettant de créer un serveur HTTP et gérer les routes.
 * - MongoDB est une base de données NoSQL orientée document. 
 *      MongoDB se base sur des documents et non des tables. Vous pouvez donc avoir des données
 *     très différentes dans une même collection et avec de la profondeur.
 * 
 * Format d'un document dans la collection équipes :
 * {
 *     _id: ObjectId("5e8b9b9b9b9b9b9b9b9b9b9b"),
 *     nom: "Equipe 1",
 *     personnes: [
 *         { id: 1, nom: "Jean" },
 *         { id: 2, nom: "Pierre" }
 *     ] 
 * }
*/
// Imports des librairies
const express = require('express'); // express pour créer un serveur HTTP
const mongo = require('mongodb');  // mongodb pour se connecter à la base de données

// Création du serveur HTTP
const server = express();
// Utilisation d'un midleware pour récupérer le contenu des requêtes POST et PUT au format JSON
server.use(express.json());

/**
 * Main function
 */
const main = async () => {
    // Création de la connexion à la base de données
    const client = await mongo.MongoClient.connect('mongodb://localhost:27017');
    // Récupération de la base de données "equipes"
    const db = client.db('equipes');
    // Récupération de la collection "equipes"
    const equipes = db.collection('equipes');

    /**
     * Route GET /equipes
     * 
     * Récupère la liste des équipes
     */
    server.get('/api/equipes', async (req, res) => {

        // SELECT * FROM equipes <=> equipes.find({})
        const result = await equipes.find().toArray();
        res.json(result);
    });

    /**
     * Route GET /equipes/:id
     * 
     * Récupère une équipe par son id
     * 
     * @param {string} id L'id de l'équipe
     * @returns {object} L'équipe
     */
    server.get('/api/equipes/:id', async (req, res) => {
        
        // Récupération de l'id dans les paramètres de la requête
        const id = req.params.id;

        // SELECT * FROM equipes WHERE _id = ? <=> equipes.find({ _id: id })
        const result = await equipes.findOne({ _id: mongo.ObjectId(id) });
        console.log(result, id);
        res.json(result);
    });

    /**
     * Route POST /equipes
     * 
     * Crée une équipe
     * @returns {object} L'équipe créée
     */
    server.post('/api/equipes', async (req, res) => {

        const body = req.body;
        const newDocument = { 
            nom: body.nom
        };

        // INSERT INTO equipes (nom, ville, pays) VALUES (?, ?, ?) <=> equipes.insertOne({ nom: body.nom, ville: body.ville, pays: body.pays })
        const result = await equipes.insertOne(newDocument);
        res.json(result);
    });

    /**
     * Route PUT /equipes/:id
     * 
     * Met à jour une équipe
     * @returns {object} L'équipe mise à jour
     */
    server.put('/api/equipes/:id', async (req, res) => {
        
        const id = req.params.id;
        const body = req.body;
        const newDocument = { 
            nom: body.nom,
            personnes: []
        }

        // UPDATE equipes SET nom = ?, ville = ?, pays = ? WHERE id = ? <=> equipes.update
        const result = await equipes.updateOne({ _id: mongo.ObjectId(id) }, newDocument);
        res.json(result);
    });
    
    /**
     * Route DELETE /equipes/:id
     * 
     * Supprime une équipe
     * @returns {object} L'équipe supprimée
     */
    server.delete('/api/equipes/:id', async (req, res) => {
            
        const id = req.params.id;

        // DELETE FROM equipes WHERE id = ? <=> equipes.deleteOne({ _id
        const reulst = await equipes.deleteOne({ _id: mongo.ObjectId(id) });
        res.json(result);
    });

    /**
     * Route POST /equipes/:id/personnes
     * 
     * Ajoute une personne à une équipe
     */
    server.put("/api/equipes/:id/personnes", async (req, res) => {
        const id = req.params.id;
        const personne = req.body;

        console.log("id", id);
        const result = await equipes.updateOne(
            { _id: mongo.ObjectId(id)}, { 
                $push: { 
                    personnes: {"id":personne.id, "nom":personne.nom} 
                } 
            });
        res.json(result);
    });

    /**
     * Route DELETE /equipes/:id/personnes/:idPersonne
     * 
     * Supprime une personne d'une équipe
     */
    server.delete("/api/equipes/:id/personnes/:idPersonne", async (req, res) => {
        const id = req.params.id;
        const idPersonne = req.params.idPersonne;
        const result = await equipes.updateOne(
            { _id: mongo.ObjectId(id)}, {
                $pull: {
                    personnes: { id: idPersonne }
                }
            });
        res.json(result);
    });  
        

    // Lancement du serveur HTTP sur le port 3000
    server.listen(3000, () => {
        console.log('Server started on port 3000');
    });
}

main();