package main

import (
	"net/http"

	"github.com/gorilla/mux"
)

func (app *application) routes() http.Handler {
	r := mux.NewRouter()
	r.HandleFunc("/api/competences", app.findAll).Methods("GET")
	r.HandleFunc("/api/competences", app.insert).Methods("POST")
	r.HandleFunc("/api/competences/{id}", app.deleteById).Methods("DELETE")

	return r
}
