/**
 * Microserivce de gestion des personnes
 * 
 * Ce microservice permet de gérer les personnes leurs persistance dans une base de données MongoDB.
 * 
 * C'est un code d'exemple minimaliste utilisant Express et MongoDB.
 * 
 * - Express est une librairie Node.js permettant de créer un serveur HTTP et gérer les routes.
 * - MongoDB est une base de données NoSQL orientée document. 
 *      MongoDB se base sur des documents
*/
// Imports des librairies
const express = require('express');
const mongo = require('mongodb');

const server = express();
server.use(express.json());

const main = async () => {
    const client = await mongo.MongoClient.connect('mongodb://localhost:27017');
    const db = client.db('equipes');
    const equipes = db.collection('equipes');

    server.get('/api/equipes', async (req, res) => {

        // SELECT * FROM equipes <=> equipes.find({})
        const result = await equipes.find().toArray();
        res.json(result);
    });

    server.get('/api/equipes/:id', async (req, res) => {
        
        const id = req.params.id;

        // SELECT * FROM equipes WHERE _id = ? <=> equipes.find({ _id: id })
        const result = await equipes.findOne({ _id: mongo.ObjectId(id) });
        console.log(result, id);
        res.json(result);
    });

    server.post('/api/equipes', async (req, res) => {

        const body = req.body;
        const newDocument = { 
            nom: body.nom
        };

        // INSERT INTO equipes (nom, ville, pays) VALUES (?, ?, ?) <=> equipes.insertOne({ nom: body.nom, ville: body.ville, pays: body.pays })
        const result = await equipes.insertOne(newDocument);
        res.json(result);
    });

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
        
    server.delete('/api/equipes/:id', async (req, res) => {
            
        const id = req.params.id;

        // DELETE FROM equipes WHERE id = ? <=> equipes.deleteOne({ _id
        const reulst = await equipes.deleteOne({ _id: mongo.ObjectId(id) });
        res.json(result);
    });

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
        

    server.listen(3000, () => {
        console.log('Server started on port 3000');
    });
}

main();