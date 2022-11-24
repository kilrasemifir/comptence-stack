package main

import (
	"context"
	"main/src/models"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
)

type CompetenceModel struct {
	C *mongo.Collection
}

func (m *CompetenceModel) findAll() ([]*models.Competence, error) {
	var competences []*models.Competence

	cur, err := m.C.Find(context.Background(), bson.M{})
	if err != nil {
		return nil, err
	}
	err = cur.All(context.Background(), &competences)
	if err != nil {
		return nil, err
	}
	return competences, err
}

func (m *CompetenceModel) insert(competence models.Competence) (*mongo.InsertOneResult, error) {
	return m.C.InsertOne(context.Background(), competence)
}

func (m *CompetenceModel) deleteById(id string) error {
	_, err := m.C.DeleteOne(context.Background(), bson.M{"_id": id})
	return err
}
