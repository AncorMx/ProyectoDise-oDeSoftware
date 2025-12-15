/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Singleton para manejar la conexión a MongoDB
 * @author System
 */
public class ConexionMongoDB {
    
    private static ConexionMongoDB instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;
    
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "lavidaesbella";
    
    private ConexionMongoDB() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexión a MongoDB establecida exitosamente");
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
            throw new RuntimeException("No se pudo establecer conexión con MongoDB", e);
        }
    }
    
    public static ConexionMongoDB getInstancia() {
        if (instancia == null) {
            synchronized (ConexionMongoDB.class) {
                if (instancia == null) {
                    instancia = new ConexionMongoDB();
                }
            }
        }
        return instancia;
    }
    
    public MongoDatabase getDatabase() {
        return database;
    }
    
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada");
        }
    }
}
