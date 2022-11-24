package main

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"os"
	"time"

	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type application struct {
	errorLog    *log.Logger
	infoLog     *log.Logger
	competences *CompetenceModel
}

func main() {
	const PORT = "8080"
	const MONGO_URI = "mongodb://localhost:27017"
	const MONGO_DB = "competences"

	infoLog := log.New(os.Stdout, "INFO\t", log.Ldate|log.Ltime)
	errorLog := log.New(os.Stderr, "ERROR\t", log.Ldate|log.Ltime|log.Lshortfile)

	co := options.Client().ApplyURI(MONGO_URI)

	client, err := mongo.NewClient(co)
	if err != nil {
		errorLog.Fatal(err)
	}

	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	err = client.Connect(ctx)
	if err != nil {
		errorLog.Fatal(err)
	}

	defer func() {
		if err = client.Disconnect(ctx); err != nil {
			panic(err)
		}
	}()

	infoLog.Printf("Connected to MongoDB on %s", MONGO_URI)

	app := &application{
		errorLog: errorLog,
		infoLog:  infoLog,
		competences: &CompetenceModel{
			C: client.Database(MONGO_DB).Collection("competences"),
		},
	}

	serverURI := fmt.Sprintf(":%s", PORT)

	srv := &http.Server{
		Addr:     serverURI,
		ErrorLog: errorLog,
		Handler:  app.routes(),
	}

	infoLog.Printf("Starting server on %s", serverURI)
	err = srv.ListenAndServe()
	errorLog.Fatal(err)
}
