package main

import (
	"encoding/json"
	"main/src/models"
	"net/http"

	"github.com/gorilla/mux"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
)

func (app *application) findAll(w http.ResponseWriter, r *http.Request) {
	competences, err := app.competences.findAll()
	if err != nil {
		app.serverError(w, err)
		return
	}
	println("competences", competences)
	b, err := json.Marshal(competences)
	if err != nil {
		app.serverError(w, err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	w.Write(b)
}

func (app *application) insert(w http.ResponseWriter, r *http.Request) {
	var competence models.Competence
	var created *mongo.InsertOneResult
	err := json.NewDecoder(r.Body).Decode(&competence)
	if err != nil {
		app.serverError(w, err)
		return
	}
	created, err = app.competences.insert(competence)
	if err != nil {
		app.serverError(w, err)
		return
	}
	competence.ID = created.InsertedID.(primitive.ObjectID)

	app.returnJson(competence, w, http.StatusCreated)
}

func (app *application) deleteById(w http.ResponseWriter, r *http.Request) {

	vars := mux.Vars(r)
	id := vars["id"]

	err := app.competences.deleteById(id)
	if err != nil {
		app.serverError(w, err)
		return
	}
	app.returnJson(nil, w, http.StatusNoContent)
}
