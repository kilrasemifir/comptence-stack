package models

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Competence struct {
	ID          primitive.ObjectID `json:"id,omitempty" bson:"_id,omitempty"`
	Nom         string             `json:"nom,omitempty" bson:"nom,omitempty"`
	Description string             `json:"description,omitempty" bson:"description,omitempty"`
}
